//code
package controller;

import model.componentes.ItemDeLista;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;//Maneja el ArrayList
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author migue
 */
public class ConsultaSQL {

    ArrayList<Object> datos;
    String tabla;

    public ConsultaSQL(String tabla) {
        //this.miCndb = ConexionBD.GetConnection();
        this.tabla = tabla;
    }

    /*public void DataToSave(ArrayList<Object> vals){
     this.datos = vals;
     }*/
    public String implode(String[] array) {
        String fill;
        StringBuilder sb = new StringBuilder();
        sb.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(",");
            sb.append(array[i]);
        }
        fill = sb.toString();
        return fill;
    }

    public String implodeArL(ArrayList<Object> inn) {
        String fill = "(";
        for (int i = 0; i < inn.size(); i++) {
            if (i == inn.size() - 1) {
                fill = fill.concat("'" + inn.get(i) + "')");
            } else {
                fill = fill.concat("'" + inn.get(i) + "',");
            }
        }
        return fill;
    }

    public String arrayToBD(ArrayList<Object> newreg) {
        this.datos = newreg;
        /*ArrayList<Object> atrbs_item = new ArrayList<>();
        //String[] reg = new String[datos.size()];
        for (int i=0; i<datos.size(); i++) {
         atrbs_item.add(i) = "'"+datos.get(i)+"'";
         }*/
        String query = "insert into " + tabla + " values " + implodeArL(datos) + "";
        return query;
    }

    public String extWhere(String camp, String search) {
        String qs = "select * from " + tabla + " where " + camp + " = '" + search + "'";
        return qs;
    }

    public String changeReg(String camp, String ref, ArrayList<Object> newdata, String[] camps) {
        String[] reg = new String[camps.length];
        for (int i = 0; i < camps.length; i++) {
            reg[i] = camps[i] + "='" + newdata.get(i) + "'";
        }
        String qry = "update " + tabla + " set " + implode(reg) + " where " + camp + "='" + ref + "'";
        return qry;
    }

    public String eraseIt(String camp, String ref) {
        String qry = "delete from " + tabla + " where " + camp + "='" + ref + "'";
        return qry;
    }

    public String extractAll() {
        String qry = "select * from " + tabla;
        return qry;
    }

    /**
     * Clase de acceso publico dedicada a las ejecuciones de consultas a la Base de Datos.
     */
    public static class ConsultorBD {

        
        /**
         * @return => retorna un ArrayList con todos los elementos encontrados en la consulta.
         * @throws java.lang.Exception
         */
        public static ArrayList<ItemDeLista> obtenerCatalogoArticulos() throws Exception{
            Connection connbd = ConexionBD.obtenerConexion();
            ArrayList<ItemDeLista> retorno = new ArrayList<>();
            HashMap<String, Object> atrbs_item = new HashMap<>();

            if (connbd != null) {
                ResultSet resultados;
                try (java.sql.PreparedStatement sentencia = connbd.prepareStatement(
                        "select a.id_articulo, a.articulo, a.descripcion, ta.talla from articulos a left join talla_articulo ta "
                        + "on ta.articulo = a.id_articulo;")) {
                    resultados = sentencia.executeQuery();
                    String cod_temporal;
                    
                    if (resultados.next()) {
                        cod_temporal = resultados.getString(1);
                        ArrayList<Object> tallas = new ArrayList<>();
                        resultados.previous();
                        while (resultados.next()) {
                            if(resultados.getString(1).equals(cod_temporal)){
                                tallas.add(resultados.getString(4));
                            }
                            else{
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
                    resultados.close();
                    connbd.close();
                    return retorno;
                } catch (RuntimeException ex) {
                    throw ex;
                } catch (Exception ex) {
                    throw new Exception("Error Consulta de Catalogo"+ex.toString());
                }
            } else {
                throw new Exception("No Hay Conexión al Servidor");
            }
        }
        
        
        /**
         * @return => retorna un HashMap (un Arreglo clave-valor), cada item es uno de un combobox.
         * El primer String es el codigo o id del Item, este a su vez es la clave dentro del HashMap.
         * El HashMap anidado seria el valor de esa clave, este HashMap contiene los atributos del Item.
         * @throws java.lang.Exception
         */
        public static ArrayList<ItemDeLista>/*HashMap<String, HashMap<String, Object>>*/ obtenerListaEnsambladores() throws Exception{

            Connection connbd = ConexionBD.obtenerConexion();
            //HashMap<String, HashMap<String, Object>> retorno = new HashMap<>();
            ArrayList<ItemDeLista> items_retorno = new ArrayList<>();
            HashMap<String, Object> atributos_item = new HashMap<>(); // ESTE HASHMAP AUXILIAR ES EL QUE SE USARA EN EL CONSTRUCTOR DE CADA ITEMCOMBOBOX
            
            if(connbd != null){
                ResultSet resultados;
                try (Statement sentencia = connbd.createStatement()) {
                    resultados = sentencia.executeQuery("select id_emp, nom_emp, ape_emp from ensambladores;");
                    while (resultados.next()) {
                            // EL PRIMER PUT DENTRO DEL HASHMAP SIEMPRE SERA EL TEXTO A MOSTRAR EN EL ITEM DEL COMBOBOX
                            atributos_item.put(ItemDeLista.TEXTO_MOSTRADO, resultados.getString(2) + " " + resultados.getString(3));
                            items_retorno.add(new ItemDeLista(resultados.getString(1), atributos_item));
                            atributos_item = new HashMap<>();//AL TERMINAR DE AGREGAR EL HASHMAP, LO REDEFINO COMO UN NUEVO OBJETO PARA EVITAR INTERFERENCIAS CON EL PROXIMO ITEMCOMBOBOX
                    }
                    resultados.close();
                    connbd.close();
                    return items_retorno;
                } catch (Exception e) {
                    throw new Exception("Error al Intentar obtener los registros de Empleados\n"
                            + "Detalle: "+e.toString());
                }
            }
            else{
                throw new Exception("No Hay Conexión al Servidor");
            }
        }

        /**
         * Segunda Version Obtencion de Repuestos Disponibles por Articulo.
         * @param cod_articulo
         * @param talla
         * @return 
         * @throws java.lang.Exception 
         */
        public static LinkedHashMap<String, Object[]> obtenerRepuestos_Articulo(String cod_articulo, String talla) throws Exception {

            Connection connbd = ConexionBD.obtenerConexion();

            if (connbd != null) {

                LinkedHashMap<String, Object[]> retorno = new LinkedHashMap<>();
                ArrayList<ItemDeLista> items;
                HashMap<String, Object> atributos_item_temp;

                ResultSet result_componentes;
                try (java.sql.PreparedStatement sentencia_articulos = connbd.prepareStatement(
                        "select c.id_comp, c.componente, fc.comp_x_par from "
                        + "componentes c inner join familia_componente fc inner join componente_articulo ca "
                        + "where c.familia = fc.cod_fam and c.id_comp = ca.componente and ca.articulo = ? " //porque el signo pregunta -> https://es.wikipedia.org/wiki/Inyecci%C3%B3n_SQL
                        + "order by c.familia;")) {
                    
                    sentencia_articulos.setString(1, cod_articulo);
                    result_componentes = sentencia_articulos.executeQuery();

                    while (result_componentes.next()) {
                        ResultSet results_repuestos;
                        try (java.sql.PreparedStatement sentencia_repuestos = connbd.prepareStatement(
                                "select cod_rep, repuesto, cant_disp from repuestos "
                                + "where componente = ? and talla is null "
                                + "or componente = ? and talla = ?;")) {
                            
                            sentencia_repuestos.setString(1, result_componentes.getString(1));
                            sentencia_repuestos.setString(2, result_componentes.getString(1));
                            sentencia_repuestos.setString(3, talla);
                            
                            results_repuestos = sentencia_repuestos.executeQuery();
                            
                            items = new ArrayList<>();//ESTE ARRAYLIST ALMACENA LOS ITEMS QUE APARECERAN EN EL COMBOBOX DE LA CELDA DE LA TABLA
                            while (results_repuestos.next()) {//SI HAY REPUESTOS, SE LLENA; SINO, EVITA ESTE WHILE Y SE VA VACIO
                                atributos_item_temp = new HashMap<>();
                                atributos_item_temp.put(ItemDeLista.TEXTO_MOSTRADO, results_repuestos.getString(2));//ESTOS NUMEROS EQUIVALEN AL ORDEN EN QUE SE 
                                atributos_item_temp.put("stock", results_repuestos.getInt(3));// PIDIERON LAS COLUMNAS EN EL SELECT
                                items.add(new ItemDeLista(results_repuestos.getString(1), atributos_item_temp));// LA 1 ES "cod_rep"
                            }
                            retorno.put(result_componentes.getString(2), new Object[]{items, result_componentes.getBoolean(3)});
                        }
                        results_repuestos.close();
                    }
                    result_componentes.close();
                    connbd.close();
                    return retorno;
                } catch (SQLException ex) {
                    throw new Exception("Se ha presentado un problema cuando se Buscaban los Componentes.\n"
                            + "Detalle: " + ex.toString() + "\nCod Error SQL: " + ex.getSQLState());
                }
            } else {
                throw new Exception("No Hay Conexión al Servidor");
            }
        }
        
        /**
         * METODO PARA REGISTRAR EL DESPACHO A PRODUCCION DE UN ARTICULO.
         * @param cod_empleado
         * @param cod_articulo
         * @param listado
         * @return boolean
         * @throws java.lang.Exception
         */
        public static boolean registrarNuevaOrden(String cod_empleado, String cod_articulo, Object[][] listado) throws Exception {
            if (listado.length > 0) {
                try (Connection connbd = ConexionBD.obtenerConexion()) {
                    int no_orden;
                    StringBuilder sb; 
                    try (java.sql.PreparedStatement sentencia = connbd.prepareStatement(
                            "select numeroNuevaOrden(?, ?);")) {//aqui se utiliza una funcion almacenada en la base de datos que me arroja el 
                                                                //codigo de registro con que puedo insertar las tablas relacionadas.
                        sentencia.setString(1, cod_empleado); //estos de aca reemplazaran los signos de interrogacion en su orden
                        sentencia.setString(2, Tiempo.obtenerInstanteMySQL());
                        try (ResultSet result = sentencia.executeQuery()) {
                            result.next();
                            no_orden = result.getInt(1);
                        }
                    }
                    
                    sb = new StringBuilder();
                    sb.append("insert into detalle_despacho values ");
                    for (int i = 1; i <= listado.length; i++) {
                        if (i == listado.length) {
                            sb.append("(?, ?, ?);");
                        } else {
                            sb.append("(?, ?, ?), ");
                        }
                    }
                    try (java.sql.PreparedStatement sentencia = connbd.prepareStatement(sb.toString())) {
                        for (int i = 1; i <= listado.length; i++) {
                            sentencia.setInt((i * 3) - 2, no_orden);
                            sentencia.setString((i * 3) - 1, listado[i - 1][0].toString());
                            sentencia.setInt((i * 3), (int) listado[i - 1][1]);
                        }
                        int result = sentencia.executeUpdate();
                        return result > 0;
                    }
                } catch (Exception e) {
                    throw new Exception("Error presentado al intentar registrar la nueva orden.\n"
                            + "Detalle: " + e.toString());
                }
            } else {
                throw new Exception("Debes de Elegir Items que Registrar. Intentalo nuevamente.");
            }
        }

        // <editor-fold defaultstate="collapsed" desc="ANTIGUO METODO DE OBTENCION DE REPUESTOS">
        /**
         * @Deprecated @param cod_articulo
         * @return => retorna un HashMap (un Arreglo clave-valor), cada item es
         * uno de un combobox. El primer String es el codigo o id del Item, este
         * a su vez es la clave dentro del HashMap. El HashMap anidado seria el
         * valor de esa clave, este HashMap contiene los atributos del Item.
         *
         * ESTE METODO EN PARTICULAR OBTIENE EL LISTADO DE REPUESTOS DISPONIBLES
         * PARA CADA COMPONENTE DEL ARTICULO, O MANUFATURA O ENSAMBLE QUE SE
         * REALICE.
         */
        /*public static HashMap<String, ArrayList<ItemDeLista>> obtenerComponentesDeArticulo(String cod_articulo){
            
         Connection connbd = ConexionBD.obtenerConexion();
         HashMap<String, ArrayList<ItemDeLista>> retorno = new HashMap<>();
         ArrayList<ItemDeLista> items;
         HashMap<String, Object> atrbs_item;
          
         if (connbd != null) {
                
         ResultSet result_a;
         try (java.sql.PreparedStatement sentencia_articulos = connbd.prepareStatement("select c.id_comp, c.nom_comp from componentes c inner join componente_articulo ca "
         + "where ca.componente = c.id_comp and ca.articulo = '" + cod_articulo + "';")) {

         result_a = sentencia_articulos.executeQuery();

         while (result_a.next()) {
         ResultSet result_b;
         try (java.sql.PreparedStatement sentencia_b = connbd.prepareStatement(
         "select cod_rep, repuesto from repuestos where componente = '" + result_a.getString(1) + "';")) {//al hacer getString(n) al resultset. n = 1 será, en este caso, cod_rep
         //es decir el indice del resultset empieza por 1 sobre el primer campo que pedimos en el select
         result_b = sentencia_b.executeQuery();
         items = new ArrayList<>();
                            
         while (result_b.next()) {        
         atrbs_item = new HashMap<>();
         //un ItemDeLista tiene como propiedades el id o cod del objeto, mas un HashMap, que es un arraylist a modo de
         //clave - valor; tanto la llave como el valor pueden ser de cualquier clase, pero la primera siempre referenciará
         // y será la unica "llave" de acceso a la segunda, su referencia. Este HashMap contiene los atributos de cada item.
         atrbs_item.put(ItemDeLista.TEXTO_MOSTRADO, result_b.getString(2));//aquí lleno el HashMap de atributos
         //en el cosntructor de ItemDeLista va el String id o cod, y el HashMap de atributos
         items.add(new ItemDeLista(result_b.getString(1), atrbs_item));
         }
         /*retorno = new ArrayList<>();
         atrbs_item = new HashMap<>();
         atrbs_item.put(ComboBoxCeldaTabla.TEXTO_MOSTRADO, "..su madre.");
         retorno.add(new ItemDeLista("item1", atrbs_item));/
         retorno.put(result_a.getString(2), items);
         }
         result_b.close();
         }

         result_a.close();
         connbd.close();

         } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.toString(), "Error en Consulta Componentes", 0);
         items = new ArrayList<>();
         atrbs_item = new HashMap<>();
         atrbs_item.put(ItemDeLista.TEXTO_MOSTRADO, "Problemas en BD.");
         items.add(new ItemDeLista("error", atrbs_item));
         retorno.put("error", items);
         return retorno;
         }
         } else {
         JOptionPane.showMessageDialog(null, "No Existe Conexión a la Base de Datos", "Error Busquda Repuestos", 0);
         }
         return retorno;
         }*/
// </editor-fold>
        
    }
    
    public static void main(String[] args) {
        try {
            //SETEO DE VARIABLES ENCARGADAS DE LA CONEXION A LA BASE DE DATOS
            ConexionBD.setUsuario("user_storebike");
            ConexionBD.setPassword("user_storebike");
            ConexionBD.setHost("localhost");
            ConexionBD.setPort("3306");
            
            Object[][] data = new Object[2][2];
            
            data[0] = new Object[]{"403436", 8};
            data[1] = new Object[]{"690733", 7};
            
            boolean f = ConsultorBD.registrarNuevaOrden("1190375460", "2015-04-07 13:52:44", data);
            System.out.println(f);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
}