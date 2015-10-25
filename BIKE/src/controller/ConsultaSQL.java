//code
package controller;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import model.componentes.ItemDeLista;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;//Maneja el ArrayList
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class ConsultaSQL {

    //ENSAMBLADORES
    
    /**
     * METODO QUE OBTIENE EL LISTADO DE ENSAMBLADORES ACTUALES.
     *
     * @return => retorna un ArrayList
     * @throws java.lang.Exception
     */
    public static ArrayList<ItemDeLista>/*HashMap<String, HashMap<String, Object>>*/ obtenerListaEnsambladores() throws Exception {

        try (Connection connbd = ConexionBD.obtenerConexion()) {

            ArrayList<ItemDeLista> items_retorno = new ArrayList<>();
            HashMap<String, Object> atributos_item; // ESTE HASHMAP AUXILIAR ES EL QUE SE USARA EN EL CONSTRUCTOR DE CADA ITEMCOMBOBOX

            try (Statement sentencia = connbd.createStatement()) {
                try (ResultSet resultados = sentencia.executeQuery(
                        "select id_emp, nom_emp, ape_emp from ensambladores;")) {
                    while (resultados.next()) {
                        atributos_item = new HashMap<>();//LO REDEFINO COMO UN NUEVO OBJETO PARA EVITAR INTERFERENCIAS CON EL PROXIMO ITEMCOMBOBOX
                        // EL PRIMER PUT DENTRO DEL HASHMAP SIEMPRE SERA EL TEXTO A MOSTRAR EN EL ITEM DEL COMBOBOX
                        atributos_item.put(ItemDeLista.TEXTO_MOSTRADO, resultados.getString(2) + " " + resultados.getString(3));
                        items_retorno.add(new ItemDeLista(resultados.getString(1), atributos_item));
                    }
                }
            }
            return items_retorno;
        } catch (Exception e) {
            throw new Exception("Error al Intentar obtener los registros de Empleados\n"
                    + "Problema: " + e.getLocalizedMessage());
        }
    }
    
    
    
    
    
    
    
    //FIN ENSAMBLADORES
    
    //ARTICULOS
    
    /**
     * METODO QUE DEVUELVE EL LISTADO DE TODOS ARTICULOS O PRODUCTOS QUE SON PRODUCIDOS
     * EN LA TIENDA. Estos son Agregados o elimindados por el usuario en una
     * ventana dedicada.
     *
     * @return retorna un ArrayList con todos los elementos encontrados en la
     * consulta.
     * @throws java.lang.Exception
     */
    public static ArrayList<ItemDeLista> obtenerCatalogoArticulos() throws Exception {

        try (Connection connbd = ConexionBD.obtenerConexion()) {

            ArrayList<ItemDeLista> retorno = new ArrayList<>();
            HashMap<String, Object> atrbs_item = new HashMap<>();

            try (java.sql.PreparedStatement sentencia = connbd.prepareStatement(
                    "select a.id_articulo, a.articulo, a.descripcion, ta.talla "
                    + "from articulos a left join talla_articulo ta "
                    + "on ta.articulo = a.id_articulo;")) {

                try (ResultSet resultados = sentencia.executeQuery()) {

                    String cod_temporal;

                    if (resultados.next()) {
                        cod_temporal = resultados.getString(1);
                        ArrayList<Object> tallas = new ArrayList<>();
                        resultados.previous();

                        while (resultados.next()) {
                            if (resultados.getString(1).equals(cod_temporal)) {
                                tallas.add(resultados.getString(4));
                            } else {
                                resultados.previous();
                                atrbs_item.put(ItemDeLista.TEXTO_MOSTRADO, resultados.getString(2));
                                atrbs_item.put("descripcion", resultados.getString(3));
                                atrbs_item.put("tallas", tallas);
                                retorno.add(new ItemDeLista(resultados.getString(1), atrbs_item));
                                atrbs_item = new HashMap<>();
                                tallas = new ArrayList<>();

                                resultados.next();
                                cod_temporal = resultados.getString(1);
                                resultados.previous();
                            }
                        }
                        
                    }
                   return retorno; 
                }
            }
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Exception("Error Consulta de Catalogo" + ex.getLocalizedMessage());
        }
    }
    
    
    /**
     * METODO PARA INGRESAR O REGISTRAR UN NUEVO ARTICULO.
     *
     * @param detalles nombre, descripcion, tallas.
     * @param componentes el listado de componentes vinculados al articulo.
     * @return verdadero o falso segun el resultado.
     * @throws java.lang.Exception
     */
    public static boolean registrarNuevoArticulo(Object[] detalles, Object[] componentes) throws Exception {
        try (Connection con = ConexionBD.obtenerConexion()) {
            
            int comprobante = 1;
            
            String cod_generado = Crypt.generarContraseña(Crypt.ARTICULOS);
            boolean cod_exist = true;
            while(cod_exist){
                try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                        "select * from articulos where id_articulo = ?;")) {
                    sentencia.setString(1, cod_generado);
                    try (ResultSet resultados = sentencia.executeQuery()) {
                        if(resultados.next()){
                            cod_generado = Crypt.generarContraseña(Crypt.ARTICULOS);
                        }
                        else{
                            cod_exist = false;
                        }
                    }
                }
            }
            
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "insert into articulos (id_articulo, articulo, descripcion) values "
                    + "(?, ?, ?);")) {
                sentencia.setString(1, cod_generado);
                sentencia.setString(2, detalles[0].toString());
                sentencia.setString(3, detalles[1].toString());
                comprobante = comprobante * sentencia.executeUpdate();
            }

            StringBuilder sb;
            
            // <editor-fold defaultstate="collapsed" desc="Escritura de la Sentencia">
            sb = new StringBuilder();
            sb.append("insert into componente_articulo values ");
            for (int i = 1; i <= componentes.length; i++) {
                if (i == componentes.length) {
                    sb.append("(?, ?);");
                } else {
                    sb.append("(?, ?), ");
                }
            }
            // </editor-fold>
            try (java.sql.PreparedStatement sentencia_insert = con.prepareStatement(sb.toString())) {
                for (int i = 1; i <= componentes.length; i++) {
                    sentencia_insert.setString(((i * 2) - 1), cod_generado);
                    sentencia_insert.setString((i * 2), (String) componentes[i - 1]);
                }
                comprobante = comprobante * sentencia_insert.executeUpdate();
                //return comprobante > 0;
            }
            
            Object[] tallas = (Object[]) detalles[2];

            if (tallas.length > 0) {
                /*try (java.sql.PreparedStatement borrar_tallas = con.prepareStatement(
                        "delete from talla_articulo where articulo = ?;")) {
                    borrar_tallas.setString(1, cod_articulo);
                    //comprobante = comprobante * borrar_tallas.executeUpdate();
                    if (!(borrar_tallas.executeUpdate() > 0)) {
                        JOptionPane.showMessageDialog(null, "No existian tallas asiganadas al articulo.");
                    }
                }*/

                sb = new StringBuilder();
                sb.append("insert into talla_articulo values ");
                for (int i = 1; i <= tallas.length; i++) {
                    if (i == tallas.length) {
                        sb.append("(?, ?);");
                    } else {
                        sb.append("(?, ?), ");
                    }
                }
                try (java.sql.PreparedStatement sentencia_insert = con.prepareStatement(sb.toString())) {
                    for (int i = 1; i <= tallas.length; i++) {
                        sentencia_insert.setString(((i * 2) - 1), cod_generado);
                        sentencia_insert.setString((i * 2), (String) tallas[i - 1]);
                    }
                    comprobante = comprobante * sentencia_insert.executeUpdate();
                    return comprobante > 0;
                }
            }

            return comprobante > 0;

        } catch (Exception ex) {
            throw new Exception("Error Consulta de Catalogo\n" + ex.getLocalizedMessage());
        }
    }
    
    
    
    /**
     * METODO PARA MODIFICAR LOS DATOS DE UN ARTICULO.
     *
     * @param cod_articulo codigo del articulo que será eliminado.
     * @param detalles nombre, descripcion, tallas.
     * @return verdadero o falso segun el resultado.
     * @throws java.lang.Exception
     */
    public static boolean modificarArticulo(String cod_articulo, Object[] detalles) throws Exception {
        try (Connection con = ConexionBD.obtenerConexion()) {
            
            int comprobante = 1;
            
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "update articulos "
                    + "set articulo = ?, descripcion = ? "
                    + "where id_articulo = ?;")) {
                sentencia.setString(1, detalles[0].toString());
                sentencia.setString(2, detalles[1].toString());
                sentencia.setString(3, cod_articulo);
                comprobante = comprobante * sentencia.executeUpdate();
            }
            
            Object[] tallas_nuevas = (Object[]) detalles[2];

            if (tallas_nuevas.length > 0) {

                try (java.sql.PreparedStatement borrar_tallas = con.prepareStatement(
                        "delete from talla_articulo where articulo = ?;")) {
                    borrar_tallas.setString(1, cod_articulo);
                    //comprobante = comprobante * borrar_tallas.executeUpdate();
                    if (!(borrar_tallas.executeUpdate() > 0)) {
                        JOptionPane.showMessageDialog(null, "No existian tallas asiganadas al articulo.");
                    }
                }

                StringBuilder sb = new StringBuilder();
                sb.append("insert into talla_articulo values ");
                for (int i = 1; i <= tallas_nuevas.length; i++) {
                    if (i == tallas_nuevas.length) {
                        sb.append("(?, ?);");
                    } else {
                        sb.append("(?, ?), ");
                    }
                }
                try (java.sql.PreparedStatement sentencia_insert = con.prepareStatement(sb.toString())) {
                    for (int i = 1; i <= tallas_nuevas.length; i++) {
                        sentencia_insert.setString(((i * 2) - 1), cod_articulo);
                        sentencia_insert.setString((i * 2), (String) tallas_nuevas[i - 1]);
                    }
                    comprobante = comprobante * sentencia_insert.executeUpdate();
                    return comprobante > 0;
                }
            }

            return comprobante > 0;

        } catch (Exception ex) {
            throw new Exception("Error Consulta de Catalogo\n" + ex.getLocalizedMessage());
        }
    }
    
    
    /**
     * METODO PARA BORRAR UN ARTICULO EXISTENTE.
     *
     * @param cod_articulo codigo del articulo que será eliminado.
     * @return verdadero o falso segun el resultado.
     * @throws java.lang.Exception
     */
    public static boolean eliminarArticulo(String cod_articulo) throws Exception {
        try (Connection con = ConexionBD.obtenerConexion()) {
            try (java.sql.PreparedStatement sentencia_delete = con.prepareStatement(
                    "delete from articulos where id_articulo = ?;")) {
                sentencia_delete.setString(1, cod_articulo);
                return sentencia_delete.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            throw new Exception("Error Consulta de Catalogo\n" + ex.getLocalizedMessage());
        }
    }

    
    /**
     * METODO PARA OBTENER EL CATALOGO DE TALLAS DE ARTICULOS USADAS EN LA
     * TIENDA
     *
     * @return
     * @throws java.lang.Exception
     */
    public static Object[] tallasUsadas() throws Exception {
        try (Connection con = ConexionBD.obtenerConexion()) {
            try (Statement sentencia = con.createStatement()) {
                try (ResultSet resultados = sentencia.executeQuery(
                        "select talla from tallas;")) {
                    ArrayList<String> tallas = new ArrayList<>();
                    while (resultados.next()) {
                        tallas.add(resultados.getString(1));
                    }
                    if(tallas.size() > 0){
                        return tallas.toArray();
                    }
                    else{
                        throw new Exception("No hay tallas registradas por el momento.");
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("Error Consulta de Catalogo\n" + ex.getLocalizedMessage());
        }
    }
    
    //FAMILIAS COMPONENTES
    
    /**
     * METODO PARA OBTENER TODOS LOS REGISTROS DE FAMILIAS DE COMPONENTES.
     * @return arreglo de objetos de dos dimensiones con los registros
     * @throws java.lang.Exception
     */
    public static Object[][] obtenerFamilias() throws Exception{
        try (Connection con = ConexionBD.obtenerConexion()) {

            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "select cod_fam, familia, descripcion, comp_x_par from familia_componente;")) {
                
                try (ResultSet resultados = sentencia.executeQuery()) {
                    ArrayList<Object[]> caja = new ArrayList<>();
                    while (resultados.next()) {
                        caja.add(new Object[]{
                            resultados.getString(1), 
                            resultados.getString(2), 
                            resultados.getString(3),
                            resultados.getBoolean(4)
                        });
                    }
                    if (caja.size() > 0) {
                        Object[][] retorno = new Object[caja.size()][4];
                        Iterator it = caja.iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            retorno[i] = (Object[]) it.next();
                            i++;
                        }
                        return retorno;
                    } else {
                        throw new Exception("No existen familias registradas.");
                    }
                }
            }

        } catch (Exception e) {
            throw new Exception("Problemas al obtener las Familias de Componentes.\n"
                    + "Error: " + e.getLocalizedMessage());
        }
    }
    
    
    /**
     * METODO PARA REGISTRAR UNA NUEVA FAMILIA.
     * @param info los datos del registro en un arreglo de objetos
     * @return boolean con la confirmacion de la inserción
     * @throws java.lang.Exception
     */
    public static boolean insertarFamilia(Object[] info) throws Exception{
        try (Connection con = ConexionBD.obtenerConexion()) {
            String s_cod_fami;
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "select max(cod_fam) from familia_componente limit 1;")) {
                try (ResultSet resultados = sentencia.executeQuery()) {
                    resultados.next();
                    s_cod_fami = resultados.getString(1);
                }
            }
            int cod = Integer.parseInt(s_cod_fami);
            s_cod_fami = String.valueOf(cod + 1);
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "insert into familia_componente values (?, ?, ?, ?)")) {
                sentencia.setString(1, s_cod_fami);
                sentencia.setString(2, info[0].toString());
                if(info[1] != null && (info[1].toString().length() > 0)){
                    sentencia.setString(3, info[1].toString());
                }
                else{
                    sentencia.setNull(3, java.sql.Types.NULL);
                }
                sentencia.setBoolean(4, (boolean)info[2]);
                
                return sentencia.executeUpdate() > 0;
            }
            
        } catch (Exception e) {
            throw new Exception("Problemas para registrar la nueva familia.\n"
                    + "Error: " + e.toString());
        }
    }
    
    
    /**
     * METODO QUE ATUALIZA UN REGISTRO DE FAMILIAS DE COMPONENTES.
     * @param info los datos del registro en un arreglo de objetos
     * @return boolean con la confirmacion de la inserción
     * @throws java.lang.Exception
     */
    public static boolean modificarFamilia(Object[] info) throws Exception{
        try (Connection con = ConexionBD.obtenerConexion()) {
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "update familia_componente set familia = ?, "
                    + "descripcion = ?, comp_x_par = ? "
                    + "where cod_fam = ?;")) {
                sentencia.setString(1, info[1].toString());
                if(info[2] != null && (info[2].toString().length() > 0)){
                    sentencia.setString(2, info[2].toString());
                }
                else{
                    sentencia.setNull(2, java.sql.Types.NULL);
                }
                sentencia.setBoolean(3, (boolean)info[3]);
                sentencia.setString(4, info[0].toString());
                
                return sentencia.executeUpdate() > 0;
            }
        } catch (Exception e) {
            throw new Exception("Problemas al modificar el registro de la familia.\n"
                    + "Error: " + e.toString());
        }
    }
    
    
    /**
     * METODO PARA ELIMINAR EL REGISTRO DE UNA FAMILIA.
     * @param cod_fam
     * @return boolean con la confirmacion de la eliminación
     * @throws java.lang.Exception
     */
    public static boolean eliminarFamilia(String cod_fam) throws Exception{
        try (Connection con = ConexionBD.obtenerConexion()) {
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "delete from familia_componente where cod_fam = ?;")) {
                sentencia.setString(1, cod_fam);
                
                return sentencia.executeUpdate() > 0;
            }
        } catch (Exception e) {
            throw new Exception("Problemas para el registro de la familia .\n"
                    + "Error: " + e.toString());
        }
    }
    
    
    
    //FIN FAMILIA ***
    
    
    //COMPONENTES
    
    /**
     * METODO PARA OBTENER TODOS LOS COMPONENTES ACTUALES.
     * @return ArrayList con todos los componentes registrados.
     * @throws java.lang.Exception
     */
    public static ArrayList<ItemDeLista> obtenerTodosComponentes() throws Exception {
        try (Connection con = ConexionBD.obtenerConexion()) {
            ArrayList<ItemDeLista> items_retorno = new ArrayList<>();
            HashMap<String, Object> atributos_item; // ESTE HASHMAP AUXILIAR ES EL QUE SE USARA EN EL CONSTRUCTOR DE CADA ITEMCOMBOBOX

            try (Statement sentencia = con.createStatement()) {
                try (ResultSet resultados = sentencia.executeQuery(
                        "select id_comp, componente from componentes c order by c.familia;")) {
                    while (resultados.next()) {
                        atributos_item = new HashMap<>();//LO REDEFINO COMO UN NUEVO OBJETO PARA EVITAR INTERFERENCIAS CON EL PROXIMO ITEMCOMBOBOX
                        // EL PRIMER PUT DENTRO DEL HASHMAP SIEMPRE SERA EL TEXTO A MOSTRAR EN EL ITEM DEL COMBOBOX
                        atributos_item.put(ItemDeLista.TEXTO_MOSTRADO, resultados.getString(2));
                        items_retorno.add(new ItemDeLista(resultados.getString(1), atributos_item));
                    }
                }
            }
            return items_retorno;
        } catch (Exception e) {
            throw new Exception("Problemas al obtener el listado de Componentes del articulo.\n"
                    + "Error: " + e.getLocalizedMessage());
        }
    }
    
    
    /**
     * METODO PARA OBTENER TODOS REPUESTOS DE UN COMPONENTE.
     * @param cod_comp codigo del componente para ejecutar la busqueda
     * @return ArrayList con todos los componentes registrados.
     * @throws java.lang.Exception
     */
    public static Object[][] obtenerRepuestosComponente(String cod_comp) throws Exception {
        try (Connection con = ConexionBD.obtenerConexion()) {
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "select cod_rep, repuesto, articulo, talla, cant_disp "
                    + "from repuestos where componente = ?;")) {
                sentencia.setString(1, cod_comp);
                try (ResultSet resultados = sentencia.executeQuery()) {
                    ArrayList<Object[]> registros = new ArrayList<>();
                    while (resultados.next()) {
                        registros.add(new Object[]{
                            resultados.getString(1), 
                            resultados.getString(2), 
                            resultados.getString(3),
                            resultados.getString(4),
                            resultados.getInt(5)
                        });
                    }
                    if (registros.size() > 0) {
                        Object[][] retorno = new Object[registros.size()][5];
                        Iterator it = registros.iterator();
                        int i = 0;
                        Object[] reg;
                        while (it.hasNext()) {
                            reg = (Object[]) it.next();
                            if (reg[2] != null) {
                                try (java.sql.PreparedStatement sentencia_b = con.prepareStatement(
                                        "select articulo from articulos where id_articulo = ?;")) {
                                    sentencia_b.setString(1, reg[2].toString());
                                    try (ResultSet resultados_b = sentencia_b.executeQuery()) {
                                        while(resultados_b.next()){
                                            reg[2] = resultados_b.getString(1);
                                        }
                                    }
                                }
                            }
                            retorno[i] = reg;
                            i++;
                        }
                        return retorno;
                    } else {
                        throw new Exception("Al parecer este Componente no tiene repuestos registrados.");
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Problemas al obtener el listado de Componentes del articulo.\n"
                    + "Error: " + e.getLocalizedMessage());
        }
    }
    
    
    /**
     * CON ESTE METODO SE OBTENDRA EL LISTADO DE COMPONENTES ASIGNADOS A UN ARTICULO.
     * @param cod_articulo
     * @return 
     * @throws java.lang.Exception
     */
    public static Object[][] obtenerComponentesDeArticulo(String cod_articulo) throws Exception{
        try (Connection con = ConexionBD.obtenerConexion()) {

            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "select f.familia, c.id_comp, c.componente, c.desc_comp "
                    + "from componentes c inner join familia_componente f "
                    + "inner join componente_articulo ca "
                    + "where f.cod_fam = c.familia and c.id_comp = ca.componente "
                    + "and ca.articulo = ?;")) {
                sentencia.setString(1, cod_articulo);
                try (ResultSet resultados = sentencia.executeQuery()) {
                    ArrayList<Object[]> caja = new ArrayList<>();
                    while (resultados.next()) {
                        caja.add(new Object[]{
                            resultados.getString(1), 
                            resultados.getString(2), 
                            resultados.getString(3),
                            resultados.getString(4)
                        });
                    }
                    if (caja.size() > 0) {
                        Object[][] retorno = new Object[caja.size()][4];
                        Iterator it = caja.iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            retorno[i] = (Object[]) it.next();
                            i++;
                        }
                        return retorno;
                    } else {
                        throw new Exception("Al parecer este Articulo no Tiene Componentes Registrados.");
                    }
                }
            }

        } catch (Exception e) {
            throw new Exception("Problemas al obtener el listado de Componentes del articulo.\n"
                    + "Error: " + e.getLocalizedMessage());
        }
    }
    
    
    
    /**
     * METODO QUE OBTIENE LA TOTALIDAD DE COMPONENTES.
     *
     * @return
     * @throws java.lang.Exception
     */
    public static Object[][] obtenerSoloComponentes() throws Exception {
        try (Connection con = ConexionBD.obtenerConexion()) {

            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "select id_comp, componente, desc_comp from componentes order by familia;")) {
                
                try (ResultSet resultados = sentencia.executeQuery()) {
                    ArrayList<Object[]> caja = new ArrayList<>();
                    while (resultados.next()) {
                        caja.add(new Object[]{
                            resultados.getString(1), 
                            resultados.getString(2), 
                            resultados.getString(3)
                        });
                    }
                    if (caja.size() > 0) {
                        Object[][] retorno = new Object[caja.size()][3];
                        Iterator it = caja.iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            retorno[i] = (Object[]) it.next();
                            i++;
                        }
                        return retorno;
                    } else {
                        throw new Exception("No existen componentes registrados en el Sistema.");
                    }
                }
            }

        } catch (Exception e) {
            throw new Exception("Problemas al obtener el listado de Componentes.\n"
                    + "Error: " + e.getLocalizedMessage());
        }
    }
    
    
    
    /**
     * METODO QUE OBTIENE LA TOTALIDAD DE COMPONENTES QUE HACEN PARTE DE 
     * LOS ARTICULOS.
     *
     * @return
     * @throws java.lang.Exception
     */
    public static Object[][] obtenerTodosLosComponentes() throws Exception {
        try (Connection con = ConexionBD.obtenerConexion()) {

            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "select distinct f.familia, c.id_comp, c.componente, c.desc_comp "
                    + "from componentes c inner join familia_componente f "
                    + "inner join componente_articulo ca "
                    + "where f.cod_fam = c.familia and c.id_comp = ca.componente;")) {
                
                try (ResultSet resultados = sentencia.executeQuery()) {
                    ArrayList<Object[]> caja = new ArrayList<>();
                    while (resultados.next()) {
                        caja.add(new Object[]{
                            resultados.getString(1), 
                            resultados.getString(2), 
                            resultados.getString(3),
                            resultados.getString(4)
                        });
                    }
                    if (caja.size() > 0) {
                        Object[][] retorno = new Object[caja.size()][4];
                        Iterator it = caja.iterator();
                        int i = 0;
                        while (it.hasNext()) {
                            retorno[i] = (Object[]) it.next();
                            i++;
                        }
                        return retorno;
                    } else {
                        throw new Exception("No existen componentes registrados en el Sistema.");
                    }
                }
            }

        } catch (Exception e) {
            throw new Exception("Problemas al obtener el listado de Componentes.\n"
                    + "Error: " + e.getLocalizedMessage());
        }
    }
    
    
    /**
     * METODO QUE MODIFICARÁ LOS COMPONENTES VINCULADOS A UN ARTICULO.
     * 
     * @param cod_articulo
     * @param componentes
     * @return
     * @throws java.lang.Exception */
    public static boolean modificarComponentesArticulo(String cod_articulo, Object[] componentes) throws Exception{
        try (Connection con = ConexionBD.obtenerConexion()) {
            
            int comprobante = 1;
    
            try (java.sql.PreparedStatement sentencia_delete = con.prepareStatement(
                    "delete from componente_articulo where articulo = ?;")) {

                sentencia_delete.setString(1, cod_articulo);
                comprobante = comprobante * sentencia_delete.executeUpdate();
                
                StringBuilder sb;
                // <editor-fold defaultstate="collapsed" desc="Escritura de la Sentencia">
                sb = new StringBuilder();
                sb.append("insert into componente_articulo values ");
                for (int i = 1; i <= componentes.length; i++) {
                    if (i == componentes.length) {
                        sb.append("(?, ?);");
                    } else {
                        sb.append("(?, ?), ");
                    }
                }
                // </editor-fold>
                try (java.sql.PreparedStatement sentencia_insert = con.prepareStatement(sb.toString())) {
                    for (int i = 1; i <= componentes.length; i++) {
                        sentencia_insert.setString(((i * 2) - 1), cod_articulo);
                        sentencia_insert.setString((i * 2), (String)componentes[i - 1]);
                    }
                    comprobante = comprobante * sentencia_insert.executeUpdate();
                    return comprobante > 0;
                }
            }

        } catch (Exception e) {
            throw new Exception("Problemas al obtener el listado de Componentes del articulo.\n"
                    + "Error: " + e.getLocalizedMessage());
        }
    }
    
    //FIN COMPONENTES 
    
    //REPUESTOS
    
    /**
     * METODO PARA INGRESAR O REGISTRAR UN NUEVO REPUESTO A LA TIENDA.
     *
     * @param info datos para la insercion a la base de datos.
     * @return boolean Confirmacion de Exito de insercion
     * @throws java.lang.Exception
     */
    public static boolean agregarRepuesto(Object[] info) throws Exception {
        try (Connection conbd = ConexionBD.obtenerConexion()) {
            
            try (java.sql.PreparedStatement sentencia = conbd.prepareStatement(
                    "insert into repuestos values (?, ?, ?, ?, ?, ?);")) {
           
                for (int i = 1; i <= info.length; i++) {
                    if (i == 6) {
                        if ((int) info[i - 1] > 0) {
                            sentencia.setInt(i, (int) info[i - 1]);
                        } else {
                            sentencia.setNull(i, java.sql.Types.NULL);
                        }
                    } else {
                        if (info[i - 1] == null || !(info[i - 1].toString().length() > 0)) {
                            sentencia.setNull(i, java.sql.Types.NULL);
                        }
                        else{
                            sentencia.setString(i, info[i - 1].toString());
                        }
                    }
                }
                
                return sentencia.executeUpdate() > 0;
            }
            
        } catch (Exception e) {
            throw new Exception("Problemas al intentar registrar el nuevo repuesto.\n" + e.toString());
        }
    }
    
    //FIN REPUESTOS
    
    /**
     * Segunda Version Obtencion de Repuestos Disponibles por Articulo.
     *
     * @param cod_articulo para buscar los repuestos por ese codigo.
     * @param talla y por la talla dada.
     * @return
     * @throws java.lang.Exception
     */
    public static LinkedHashMap<Object[]/*String*/, ArrayList<ItemDeLista>> obtenerRepuestos_Articulo(String cod_articulo, String talla) throws Exception {
        // <editor-fold defaultstate="collapsed" desc="CODIGO OBTENCION DE COMPONENTES Y REPUESTOS">

        try (Connection connbd = ConexionBD.obtenerConexion()) {

            try (java.sql.PreparedStatement sentencia_articulos = connbd.prepareStatement(
                    "select c.id_comp, c.componente, fc.comp_x_par from "
                    + "componentes c inner join familia_componente fc inner join componente_articulo ca "
                    + "where c.familia = fc.cod_fam and c.id_comp = ca.componente and ca.articulo = ? " //porque el signo pregunta -> https://es.wikipedia.org/wiki/Inyecci%C3%B3n_SQL
                    + "order by c.familia;")) {

                sentencia_articulos.setString(1, cod_articulo);

                try (ResultSet result_componentes = sentencia_articulos.executeQuery()) {

                    LinkedHashMap<Object[], ArrayList<ItemDeLista>> retorno = new LinkedHashMap<>();
                    ArrayList<ItemDeLista> items_componente;//ESTE ARRAYLIST ALMACENA LOS ITEMS QUE APARECERAN EN EL COMBOBOX DE LA CELDA DE LA TABLA
                    HashMap<String, Object> atributos_item;
                    //boolean hay_componentes = false;
                    
                    Object[] componente = null;
                    //boolean par;
                    
                    while (result_componentes.next()) {
                        
                        items_componente = new ArrayList<>();
                        
                        componente = new Object[3];
                        componente[0] = result_componentes.getString(1);
                        componente[1] = result_componentes.getString(2);
                        componente[2] = result_componentes.getBoolean(3);

                        try (java.sql.PreparedStatement sentencia_repuestos = connbd.prepareStatement(
                                "select cod_rep, repuesto, cant_disp from repuestos "
                                + "where componente = ? and talla is null "
                                + "or componente = ? and talla = ?;")) {

                            sentencia_repuestos.setString(1, result_componentes.getString(1));
                            sentencia_repuestos.setString(2, result_componentes.getString(1));
                            sentencia_repuestos.setString(3, talla);

                            try (ResultSet results_repuestos = sentencia_repuestos.executeQuery()) {
                                
                                while (results_repuestos.next()) {//SI HAY REPUESTOS, SE LLENA; SINO, EVITA ESTE WHILE Y SE VA VACIO
                                    atributos_item = new HashMap<>();
                                    atributos_item.put(ItemDeLista.TEXTO_MOSTRADO, results_repuestos.getString(2));//ESTOS NUMEROS EQUIVALEN AL ORDEN EN QUE SE 
                                    atributos_item.put("stock", results_repuestos.getInt(3));// PIDIERON LAS COLUMNAS EN EL SELECT
                                    items_componente.add(new ItemDeLista(results_repuestos.getString(1), atributos_item));// LA 1 ES "cod_rep"
                                }
                                
                                retorno.put(componente, items_componente);
                                
                                /*par = result_componentes.getBoolean(3);
                                if (par) {
                                    retorno.put(result_componentes.getString(2), new Object[]{items_componente, 1});
                                } else {
                                    retorno.put(result_componentes.getString(2), new Object[]{items_componente, 0});
                                }*/
                            }
                        }
                    }
                    if (componente != null) {
                        return retorno;
                    } else {
                        throw new Exception("Al parecer el Articulo no tiene ningun componente asignado aún.");
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("Se ha presentado un problema cuando se Buscaban los Componentes.\n"
                    + ex.getMessage() + "\n");
            //+ "Cod Error SQL: " + (((SQLException)ex != null)?((SQLException)ex).getSQLState():""));
        }
        // </editor-fold>
    }

    /**
     * METODO QUE OBTENDRA EL LISTADO DE TODOS LOS REPUESTOS DESPACHADOS EN UNA
     * ORDEN DE PRODUCCION
     *
     * @param no_orden entero numero de orden
     * @return
     * @throws java.lang.Exception
     */
    public static /*boolean*/HashMap<Object[], LinkedHashMap<String, ItemDeLista>> obtenerListadoDespachoOrden(int no_orden) throws Exception {
        try (Connection conbd = ConexionBD.obtenerConexion()) {
            try (java.sql.PreparedStatement sentencia = conbd.prepareStatement(
                    "select e.id_emp, a.id_articulo, p.talla, p.cantidad "
                    + "from ordenes_produccion op inner join ensambladores e inner join articulos a "
                    + "inner join produccion p "
                    + "where op.ensamblador = e.id_emp and a.id_articulo = p.articulo "
                    + "and p.no_ord_prod = op.no_ord and op.no_ord = ?;")) {
                sentencia.setInt(1, no_orden);
                try (ResultSet resultados = sentencia.executeQuery()) {
                    Object[] detalle_produccion = null; // es null por si no entra al while
                    while (resultados.next()) {
                        /*detalle de la orden*/
                        detalle_produccion = new Object[5]; // si entra al while, es decir si hay resultados, detalle_produccion ya no sera null
                        /*numero orden*/
                        detalle_produccion[0] = no_orden;
                        /*ensamblador*/
                        detalle_produccion[1] = resultados.getString(1);
                        /*articulo*/
                        detalle_produccion[2] = resultados.getString(2);
                        /*talla*/
                        detalle_produccion[3] = resultados.getString(3);
                        /*cantidad*/
                        detalle_produccion[4] = resultados.getInt(4);
                    }
                    if (detalle_produccion != null && (detalle_produccion.length > 0)) {
                        try (java.sql.PreparedStatement sentencia_b = conbd.prepareStatement(
                                "select c.componente, r.cod_rep, r.repuesto, r.cant_disp, dp.cant_desp "
                                + "from detalle_despacho dp inner join repuestos r inner join componentes c "
                                + "where c.id_comp = r.componente and r.cod_rep = dp.repuesto and dp.orden = ? "
                                + "order by c.familia;")) {
                            sentencia_b.setInt(1, no_orden);
                            
                            try (ResultSet resultados_b = sentencia_b.executeQuery()) {
                                
                                LinkedHashMap<String, ItemDeLista> lista = new LinkedHashMap<>();
                                HashMap<String, Object> atributos_item = null;
                                
                                while (resultados_b.next()) {
                                    atributos_item = new HashMap<>();
                                    atributos_item.put(ItemDeLista.TEXTO_MOSTRADO, resultados_b.getString(3));
                                    atributos_item.put("stock", resultados_b.getInt(4));
                                    atributos_item.put("cant_desp", resultados_b.getInt(5));
                                    lista.put(resultados_b.getString(1), new ItemDeLista(resultados_b.getString(2), atributos_item));
                                }
                                
                                if (atributos_item != null && (atributos_item.size() > 0)) {
                                    HashMap<Object[], LinkedHashMap<String, ItemDeLista>> retorno = new HashMap<>();
                                    retorno.put(detalle_produccion, lista);
                                    return retorno;
                                    //return true;
                                    
                                } else {
                                    throw new Exception("No se despacharon repuestos para esta orden de produccion.");
                                }
                            }
                        }
                    } else {
                        throw new Exception("No hay registros de produccion con ese numero de orden.");
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener el listado de la Orden de Produccion requerida.\n" + e.getMessage());
        }
    }
    
    
    /**
     * METODO PARA BORRAR EL REPUESTO VINCULADO A COMPONENTE.
     *
     * @param cod_rep codigo del repuesto a borrar de la base de datos.
     * @return boolean Confirmacion de Exito de Borrado.
     * @throws java.lang.Exception
     */
    public static boolean borrarRepuesto(String cod_rep) throws Exception {
        try (Connection conbd = ConexionBD.obtenerConexion()) {
            try (java.sql.PreparedStatement sentencia = conbd.prepareStatement(
                    "delete from repuestos where cod_rep = ?;")) {
                sentencia.setString(1, cod_rep);
                return sentencia.executeUpdate() > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al Eliminar la Orden de Produccion.\n" + e.getLocalizedMessage());
        }
    }
    
    
    
    /**
     * METODO QUE ME PERMITE BORRAR EL LISTADO DE REPUESTOS DESPACHADOS EN UNA
     * ORDEN SIN BORRAR LA ORDEN.
     *
     * @param no_orden
     * @param produccion
     * @param listado
     * @return
     * @throws java.lang.Exception
     */
    public static boolean actualizarListadoDespachoOrden(int no_orden, Object[] produccion, Object[][] listado) throws Exception{
        try(Connection con = ConexionBD.obtenerConexion()){
            int comprobante = 1;
      
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "update ordenes_produccion "
                    + "set ensamblador = ?, "
                    + "hora_despacho = ? "
                    + "where no_ord = ?;")) {
                sentencia.setString(1, produccion[0].toString());
                sentencia.setString(2, Tiempo.obtenerInstanteMySQL());
                sentencia.setInt(3, no_orden);
                comprobante = comprobante * sentencia.executeUpdate();
            }

            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "delete from produccion where no_ord_prod = ?;")) {
                sentencia.setInt(1, no_orden);
                comprobante = comprobante * sentencia.executeUpdate();
            }
            
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "insert into produccion values (?, ?, ?, ?);")) {
                sentencia.setInt(1, no_orden);
                sentencia.setString(2, produccion[1].toString());//cod articulo
                sentencia.setString(3, produccion[2].toString());//talla
                sentencia.setInt(4, (int) produccion[3]);//cantidad
                comprobante = comprobante * sentencia.executeUpdate();
            }

            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "delete from detalle_despacho where orden = ?;")) {
                sentencia.setInt(1, no_orden);
                comprobante = comprobante * sentencia.executeUpdate();
            }

            StringBuilder sb;
            // <editor-fold defaultstate="collapsed" desc="Escritura de la Sentencia">
            sb = new StringBuilder();
            sb.append("insert into detalle_despacho values ");
            for (int i = 1; i <= listado.length; i++) {
                if (i == listado.length) {
                    sb.append("(?, ?, ?);");
                } else {
                    sb.append("(?, ?, ?), ");
                }
            }
            // </editor-fold>
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(sb.toString())) {
                for (int i = 1; i <= listado.length; i++) {
                    sentencia.setInt((i * 3) - 2, no_orden);
                    sentencia.setString((i * 3) - 1, listado[i - 1][0].toString());
                    sentencia.setInt((i * 3), (int) listado[i - 1][1]);
                }
                comprobante = comprobante * sentencia.executeUpdate();
            }
            return comprobante > 0;
        } catch (Exception e) {
            if (e instanceof MySQLIntegrityConstraintViolationException) {
                throw new Exception("No puedes seleccionar dos veces el mismo repuesto para cada componente.\n"
                        + "Verifica en la lista si algun componente tiene seleccionado el mismo\n"
                        + "repuesto mas de una vez. Intentelo nuevamente.\n"
                        + "Detalle: " + e.getMessage());
            } else {
                throw new Exception("No se ha podido actualizar el listado de la orden.\n" + e.toString());
            }
        }
    }

    /**
     * METODO QUE OBTIENE EL NUMERO DE LA ULTIMA ORDEN DE PRODUCCION DESPACHADA.
     * Retorna el ultimo numero de registro de orden de detalle_produccion.
     *
     * @return int - entero con el numero de detalle_produccion actual
     * @throws java.lang.Exception
     */
    public static int obtenerUltimoNumProduccion() throws Exception {
        try (Connection con = ConexionBD.obtenerConexion()) {
            try (java.sql.PreparedStatement sentencia = con.prepareStatement(
                    "select max(no_ord) from ordenes_produccion limit 1;")) {
                try (ResultSet resultado = sentencia.executeQuery()) {
                    while (resultado.next()) {//si hay resultados (por lo menos 1 registro)
                        return resultado.getInt(1);
                    }
                    return 1;
                }
            }
        } catch (Exception e) {
            throw new Exception("Problemas al obtener el numero del registro de las Ordenes de Produccion.\n"
                    + "Error:   " + e.toString());
        }
    }

    /**
     * METODO PARA REGISTRAR UNA NUEVA ORDEN DE PRODUCCION
     *
     * @param cod_empleado
     * @param produccion
     * @param listado
     * @return boolean
     * @throws java.lang.Exception
     */
    public static boolean registrarNuevaOrden(String cod_empleado, Object[] produccion, Object[][] listado) throws Exception {
        // <editor-fold defaultstate="collapsed" desc="CODIGO DE CONSULTA SQL QUE PERMITE REGISTRAR UNA NUEVA ORDEN DE PRODUCCION">
        if (listado.length > 0) {
            try (Connection connbd = ConexionBD.obtenerConexion()) {
                int comprobante = 1;
                int no_orden;
                try (java.sql.PreparedStatement sentencia = connbd.prepareStatement(
                        "select numeroNuevaOrden(?, ?);")) {//aqui se utiliza una funcion almacenada en la base de datos que me arroja el 
                    //codigo de registro con que puedo insertar las tablas relacionadas.
                    sentencia.setString(1, cod_empleado); //estos de aca reemplazaran los signos de interrogacion en su orden
                    sentencia.setString(2, Tiempo.obtenerInstanteMySQL());//la funcion lo que hace es registrar una nueva orden de detalle_produccion,
                    try (ResultSet result = sentencia.executeQuery()) {//recibe el codigo del empleado y la hora de despacho. Devuelve el numero
                        result.next();                          //de registro para poder insertar la orden en las tablas relacionadas
                        no_orden = result.getInt(1);//aqui recibo el numero de registro.
                    }
                }

                StringBuilder sb;
                // <editor-fold defaultstate="collapsed" desc="Escritura de la Sentencia">
                sb = new StringBuilder();
                sb.append("insert into detalle_despacho values ");
                for (int i = 1; i <= listado.length; i++) {
                    if (i == listado.length) {
                        sb.append("(?, ?, ?);");
                    } else {
                        sb.append("(?, ?, ?), ");
                    }
                }
                // </editor-fold>
                try (java.sql.PreparedStatement sentencia = connbd.prepareStatement(sb.toString())) {
                    for (int i = 1; i <= listado.length; i++) {
                        sentencia.setInt((i * 3) - 2, no_orden);
                        sentencia.setString((i * 3) - 1, listado[i - 1][0].toString());
                        sentencia.setInt((i * 3), (int) listado[i - 1][1]);
                    }
                    comprobante = comprobante * sentencia.executeUpdate();
                }

                try (java.sql.PreparedStatement sentencia = connbd.prepareStatement(
                        "insert into produccion values (?, ?, ?, ?);")) {
                    sentencia.setInt(1, no_orden);
                    sentencia.setString(2, produccion[0].toString());//cod articulo
                    sentencia.setString(3, produccion[1].toString());//talla
                    sentencia.setInt(4, (int) produccion[2]);//cantidad

                    comprobante = comprobante * sentencia.executeUpdate();
                }

                return comprobante > 0;
         
            } catch (Exception e) {
                if (e instanceof MySQLIntegrityConstraintViolationException) {
                    throw new Exception("No puedes seleccionar dos veces el mismo repuesto para cada componente.\n"
                            + "Verifica en la lista si algun componente tiene seleccionado el mismo\n"
                            + "repuesto mas de una vez. Intentelo nuevamente.\n"
                            + "Detalle: " + e.getMessage());
                } else {
                    throw new Exception("Error presentado al intentar registrar la nueva orden.\n"
                            + "Detalle: " + e.getLocalizedMessage());
                }
            }
        } else {
            throw new Exception("Antes, debe elegir la Cantidad de Repuestos a Despachar.\n"
                    + "Debe especificar la cantidad de por lo menos 1 articulo.\n"
                    + "Intentelo nuevamente.");
        }

// </editor-fold>
    }

    /**
     * METODO PARA BORRAR LA ORDEN DE PRODUCCION INDICADA. Si esta habilitado el
     * conteo de Repuestos su cantidad sera restaurada.
     *
     * @param no_orden Numero de orden a Borrar.
     * @return boolean Confirmacion de Exito de Borrado.
     * @throws java.lang.Exception
     */
    public static boolean borrarOrdenProduccion(int no_orden) throws Exception {
        try (Connection conbd = ConexionBD.obtenerConexion()) {
            try (java.sql.PreparedStatement sentencia = conbd.prepareStatement(
                    "delete from ordenes_produccion where no_ord = ?;")) {
                sentencia.setInt(1, no_orden);
                return sentencia.executeUpdate() > 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al Eliminar la Orden de Produccion.\n" + e.getLocalizedMessage());
        }
    }

    /**
     * METODO PARA OBTENER LA INFORMACION DE LA PRODUCCION ACTUAL. Retorna todos
 los registros de ordenes de detalle_produccion efectuados en el sistema en un
 ArrayList de Arreglo de Objetos.
     *
     * @return ArrayList
     * @throws java.lang.Exception
     */
    public static ArrayList<Object[]> revisarProduccionActual() throws Exception {
        try (Connection conbd = ConexionBD.obtenerConexion()) {
            try (java.sql.PreparedStatement sentencia = conbd.prepareStatement(
                    //LA SGTE CONSULTA ME ARROJARÁ LOS DETALLES MAS IMPORTANTES DE LAS PRODUCCIONES DESPACHADAS
                    "select op.no_ord, e.nom_emp, e.ape_emp, op.hora_despacho, op.hora_entrega, "
                    + "a.articulo, p.talla, p.cantidad "
                    + "from ordenes_produccion op inner join produccion p "
                    + "inner join articulos a inner join ensambladores e "
                    + "where op.ensamblador = e.id_emp and p.articulo = a.id_articulo "
                    + "and p.no_ord_prod = op.no_ord order by op.no_ord asc;")) {

                ArrayList<Object[]> retorno = new ArrayList<>();
                try (ResultSet resultados = sentencia.executeQuery()) {

                    while (resultados.next()) {

                        retorno.add(new Object[]{//este nuevo objeto sera cada nuevo registro obtenido de la consulta
                            resultados.getInt(1), //no_orden
                            resultados.getString(2) + " " + resultados.getString(3),//res 2 = nombre; res 3 = apellido
                            resultados.getTimestamp(4), //fecha despacho
                            resultados.getTimestamp(5), // fecha entrega
                            resultados.getString(6), // producion
                            resultados.getString(7), // talla
                            resultados.getInt(8) // cantidad
                        });
                    }
                }
                return retorno;
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener los detalles de Produccion.\n" + e.getLocalizedMessage());
        }
    }

    /**
     * METODO PARA REGISTRAR LA ENTREGA DE UNA ORDEN DE PRODUCCION. Recibe el
     * numero de orden que se va a entregar, esto habilita los articulos
     * producidos como disponibles.
     *
     * @param no_orden
     * @return boolean confirmando si se ejecuto el registro
     * @throws java.lang.Exception
     */
    public static boolean registrarEntregaProduccion(int no_orden) throws Exception {
        try (Connection conbd = ConexionBD.obtenerConexion()) {

            try (java.sql.PreparedStatement sentencia = conbd.prepareStatement(
                    "update ordenes_produccion set hora_entrega = ? where no_ord = ?;")) {
                sentencia.setString(1, Tiempo.obtenerInstanteMySQL());
                sentencia.setInt(2, no_orden);
                return sentencia.executeUpdate() > 0;
            }

        } catch (Exception e) {
            throw new Exception("Error al obtener los detalles de Produccion.\n" + e.getLocalizedMessage());
        }
    }

    /**
     * METODO PUBLICO QUE ME PERMITE CONFIGURAR SI SE LLEVA A CABO EL CONTEO DE
     * LOS REPUESTOS REGISTRADOS. Ejecuta las sentencias DDL respectivas que
     * configuran los triggers en la Base de Datos para que controlen las
     * cantidades o no.
     *
     * @param contar
     * @return
     * @throws java.lang.Exception
     */
    public static boolean setConteoRepuestos(boolean contar) throws Exception {
        // <editor-fold defaultstate="collapsed" desc="CODIGO">
        try (Connection conbd = ConexionBD.obtenerConexion()) {
            int i;
            int comprobante = 0;
            if (contar) {
                for (i = 0; i < TRIGGERS_CONTEO_ON.length; i++) {
                    try (java.sql.PreparedStatement sentencia = conbd.prepareStatement(TRIGGERS_CONTEO_ON[i])) {
                        comprobante = comprobante + sentencia.executeUpdate();
                    }
                }
                return comprobante == 0;
            } else {
                for (i = 0; i < TRIGGERS_CONTEO_OFF.length; i++) {
                    try (java.sql.PreparedStatement sentencia = conbd.prepareStatement(TRIGGERS_CONTEO_OFF[i])) {
                        comprobante = comprobante + sentencia.executeUpdate();
                    }
                }
                return comprobante == 0;
            }
        } catch (Exception e) {
            throw new Exception("Error al Configurar el Conteo de Repuestos\n" + e.getLocalizedMessage());
        }
    }

    private static final String[] TRIGGERS_CONTEO_ON = new String[]{
        // <editor-fold defaultstate="collapsed" desc="TRIGGERS QUE ESTABLECEN EL CONTEO">
        ("CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`ordenes_produccion_BEFORE_DELETE` BEFORE DELETE ON `ordenes_produccion` FOR EACH ROW "
        + "BEGIN "
        + " declare r_cod_rep varchar(6); "
        + " declare r_cant_desp int(11); "
        + " declare fin boolean; "
        + " declare reps cursor for "
        + " select d.repuesto, d.cant_desp from detalle_despacho d where d.orden = old.no_ord; "
        + " declare continue handler for not found set fin = true; "
        + " open reps; "
        + " loop_ : loop "
        + "  fetch reps into r_cod_rep, r_cant_desp; "
        + "  if fin then "
        + "   leave loop_; "
        + "  end if; "
        + "  update repuestos set cant_disp = cant_disp + r_cant_desp where cod_rep = r_cod_rep; "
        + " end loop loop_; "
        + " close reps; "
        + "END;"),
        ("CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`detalle_despacho_AFTER_INSERT` AFTER INSERT ON `detalle_despacho` FOR EACH ROW "
        + "BEGIN "
        + "update repuestos set cant_disp = (cant_disp - new.cant_desp) where cod_rep = new.repuesto; "
        + "END;"),
        ("CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`detalle_despacho_AFTER_UPDATE` AFTER UPDATE ON `detalle_despacho` FOR EACH ROW "
        + "BEGIN "
        + " declare cant_actual int(11); "
        + " declare acumulado int(11); "
        + " if (old.repuesto <> new.repuesto) then "
        + "     update repuestos "
        + "     set cant_disp = (cant_disp + old.cant_desp) "
        + "     where cod_rep = old.repuesto; "
        + "         if (old.cant_desp <> new.cant_desp)then "
        + "		update repuestos "
        + "		set cant_disp = (cant_disp - new.cant_desp) "
        + "		where cod_rep = new.repuesto; "
        + "         else "
        + "		update repuestos "
        + "		set cant_disp = (cant_disp - old.cant_desp) "
        + "		where cod_rep = new.repuesto; "
        + "        end if; "
        + " elseif (old.cant_desp <> new.cant_desp) then "
        + "	select cant_disp from repuestos where cod_rep = old.repuesto into cant_actual; "
        + "     set acumulado = cant_actual + old.cant_desp; "
        + "	update repuestos "
        + "	set cant_disp = (acumulado - new.cant_desp) "
        + "	where cod_rep = old.repuesto; "
        + " end if; "
        + "END;"),
        ("CREATE DEFINER = CURRENT_USER TRIGGER `storebike`.`detalle_despacho_AFTER_DELETE` AFTER DELETE ON `detalle_despacho` FOR EACH ROW "
        + "BEGIN "
        + " update repuestos "
        + "	set cant_disp = (cant_disp + old.cant_desp)  "
        + "	where cod_rep = old.repuesto; "
        + "END;")
    // </editor-fold>    
    };

    private static final String[] TRIGGERS_CONTEO_OFF = new String[]{
        ("DROP TRIGGER IF EXISTS ordenes_produccion_BEFORE_DELETE;"),
        ("DROP TRIGGER IF EXISTS detalle_despacho_AFTER_INSERT;"),
        ("DROP TRIGGER IF EXISTS detalle_despacho_AFTER_UPDATE;"),
        ("DROP TRIGGER IF EXISTS detalle_despacho_AFTER_DELETE;")
    };

// </editor-fold>
   
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            //SETEO DE VARIABLES ENCARGADAS DE LA CONEXION A LA BASE DE DATOS
            ConexionBD.setUsuario("user_storebike");
            ConexionBD.setPassword("user_storebike");
            ConexionBD.setHost("localhost");
            ConexionBD.setPort("3306");

            //PRUEBA REGISTRO ORDEN
            /*Object[] detalle_produccion = new Object[3];
             detalle_produccion[0] = "MTB";
             detalle_produccion[1] = "26";
             detalle_produccion[2] = 5;
             Object[][] data = new Object[2][2];
             data[0] = new Object[]{"403436", 4};
             data[1] = new Object[]{"690733", 3};
             boolean f = ConsultorBD.registrarNuevaOrden("1190375460", detalle_produccion, data);*/ //aqui va el astrisco
            //boolean f = setConteoRepuestos(true);
            //boolean f = obtenerListadoDespachoOrden(4);
            /*LinkedHashMap<String, ItemDeLista> h;
            Object[] ob;
            for (HashMap.Entry reg : obtenerListadoDespachoOrden(2).entrySet()) {
                ob = (Object[]) reg.getKey();
                System.out.println(ob[0] + " " + ob[1] + " " + ob[2] + " " + ob[3] + " " + ob[4]);
                h = (LinkedHashMap<String, ItemDeLista>) reg.getValue();
                for (HashMap.Entry eg : h.entrySet()) {
                    System.out.println(eg.getKey() + " : " + ((ItemDeLista) eg.getValue()).obtenerCodigoId() + " "
                            + ((ItemDeLista) eg.getValue()).getAtributos().get(ItemDeLista.TEXTO_MOSTRADO));
                }
            }*/ // aca *

            /*Object[] produccion = new Object[]{"RDP", "26", 5};
            Object[][] listado = new Object[2][2];
            listado[0] = new Object[]{"403436", 5};
            listado[1] = new Object[]{"690733", 10};
            boolean f = ConsultaSQL.actualizarListadoDespachoOrden(5, produccion, listado);*/
            //Object[] f = tallasUsadas();
            //Object[][] f = obtenerComponentesDeArticulo("MTB");
            //System.out.println(f[1][0]+" "+f[1][1]);
            
            //Object[] comps = new Object[]{"001", "003", "019"};
            //boolean f = modificarComponentesArticulo("RIN", comps);
            //boolean f = eliminarArticulo("RDP");
            //Object[] tallas = new Object[]{"16","20","26"};
            //Object[] detalles = new Object[]{"Ultraracer", "Bicicleta voladora.", tallas};
            //Object[] componentes = new Object[]{"001","076","003"};
            //boolean f = registrarNuevoArticulo(detalles, componentes);
            //boolean f = modificarArticulo("TT", detalles);
            //Object[][] reps = obtenerRepuestosComponente("050");
            //for (Object[] rep : reps) {
            //    System.out.println(rep[0]+" "+rep[1]+" "+rep[2]+" "+rep[3]+" "+rep[4]);
            //}
            
            Object[] g = new Object[]{"gonzalez", null, false};
            boolean f = insertarFamilia(g);
            System.out.println(f);
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
