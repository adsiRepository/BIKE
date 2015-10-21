//code
package controller;

import model.componentes.ItemDeLista;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;//Maneja el ArrayList
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author migue
 */
public class ConsultaSQL {

    /**
     * @return => retorna un ArrayList con todos los elementos encontrados en la
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
                }
            }
            return retorno;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Exception("Error Consulta de Catalogo" + ex.getLocalizedMessage());
        }
    }

    /**
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

    /**
     * Segunda Version Obtencion de Repuestos Disponibles por Articulo.
     *
     * @param cod_articulo
     * @param talla
     * @return
     * @throws java.lang.Exception
     */
    public static LinkedHashMap<String, Object[]> obtenerRepuestos_Articulo(String cod_articulo, String talla) throws Exception {
        // <editor-fold defaultstate="collapsed" desc="CODIGO OBTENCION DE COMPONENTES Y REPUESTOS">

        try (Connection connbd = ConexionBD.obtenerConexion()) {

            LinkedHashMap<String, Object[]> retorno = new LinkedHashMap<>();
            ArrayList<ItemDeLista> items;
            HashMap<String, Object> atributos_item_temp;

            try (java.sql.PreparedStatement sentencia_articulos = connbd.prepareStatement(
                    "select c.id_comp, c.componente, fc.comp_x_par from "
                    + "componentes c inner join familia_componente fc inner join componente_articulo ca "
                    + "where c.familia = fc.cod_fam and c.id_comp = ca.componente and ca.articulo = ? " //porque el signo pregunta -> https://es.wikipedia.org/wiki/Inyecci%C3%B3n_SQL
                    + "order by c.familia;")) {

                sentencia_articulos.setString(1, cod_articulo);

                try (ResultSet result_componentes = sentencia_articulos.executeQuery()) {

                    boolean par;
                    
                    while (result_componentes.next()) {

                        try (java.sql.PreparedStatement sentencia_repuestos = connbd.prepareStatement(
                                "select cod_rep, repuesto, cant_disp from repuestos "
                                + "where componente = ? and talla is null "
                                + "or componente = ? and talla = ?;")) {

                            sentencia_repuestos.setString(1, result_componentes.getString(1));
                            sentencia_repuestos.setString(2, result_componentes.getString(1));
                            sentencia_repuestos.setString(3, talla);

                            try (ResultSet results_repuestos = sentencia_repuestos.executeQuery()) {
                                items = new ArrayList<>();//ESTE ARRAYLIST ALMACENA LOS ITEMS QUE APARECERAN EN EL COMBOBOX DE LA CELDA DE LA TABLA
                                while (results_repuestos.next()) {//SI HAY REPUESTOS, SE LLENA; SINO, EVITA ESTE WHILE Y SE VA VACIO
                                    atributos_item_temp = new HashMap<>();
                                    atributos_item_temp.put(ItemDeLista.TEXTO_MOSTRADO, results_repuestos.getString(2));//ESTOS NUMEROS EQUIVALEN AL ORDEN EN QUE SE 
                                    atributos_item_temp.put("stock", results_repuestos.getInt(3));// PIDIERON LAS COLUMNAS EN EL SELECT
                                    items.add(new ItemDeLista(results_repuestos.getString(1), atributos_item_temp));// LA 1 ES "cod_rep"
                                }
                                par = result_componentes.getBoolean(3);
                                if (par) {
                                    retorno.put(result_componentes.getString(2), new Object[]{items, 1});
                                }
                                else{
                                    retorno.put(result_componentes.getString(2), new Object[]{items, 0});
                                }
                            }
                        }
                    }
                }
            }
            return retorno;
        } catch (Exception ex) {
            throw new Exception("Se ha presentado un problema cuando se Buscaban los Componentes.\n"
                    + "Detalle: " + ex.getLocalizedMessage() + "\n");
            //+ "Cod Error SQL: " + (((SQLException)ex != null)?((SQLException)ex).getSQLState():""));
        }
        // </editor-fold>
    }

    /**
     * METODO PARA REGISTRAR EL DESPACHO A PRODUCCION DE UN ARTICULO
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
                    sentencia.setString(2, Tiempo.obtenerInstanteMySQL());//la funcion lo que hace es registrar una nueva orden de produccion,
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
                throw new Exception("Error presentado al intentar registrar la nueva orden.\n"
                        + "Detalle: " + e.getLocalizedMessage());
            }
        } else {
            throw new Exception("Debes de Elegir Items que Registrar. Intentalo nuevamente.");
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
     * los registros de ordenes de produccion efectuados en el sistema en un
     * ArrayList de Arreglo de Objetos.
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
                    + "and p.no_ord_prod = op.no_ord;")) {

                ArrayList<Object[]> retorno = new ArrayList<>();
                try (ResultSet resultados = sentencia.executeQuery()) {
                    while (resultados.next()) {
                        retorno.add(new Object[]{//este nuevo objeto sera cada nuevo registro obtenido de la consulta
                            resultados.getInt(1), //no_orden
                            resultados.getString(2) + " " + resultados.getString(3),//res 2 = nombre; res 3 = apellido
                            resultados.getDate(4), //fecha despacho
                            resultados.getDate(5), // fecha entrega
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
     * METODO PARA REGISTRAR LA ENTREGA DE UNA ORDEN DE PRODUCCION.
     * Recibe el numero de orden que se va a entregar, esto habilita los articulos producidos
     * como disponibles.
     * @param no_orden
     * @return boolean confirmando si se ejecuto el registro
     * @throws java.lang.Exception
     */
    /*public static boolean registrarEntregaProduccion(int no_orden) throws Exception {
        
    }*/

    
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
     * @param args*/
    /*public static void main(String[] args) {
        try {
            //SETEO DE VARIABLES ENCARGADAS DE LA CONEXION A LA BASE DE DATOS
            ConexionBD.setUsuario("user_storebike");
            ConexionBD.setPassword("user_storebike");
            ConexionBD.setHost("localhost");
            ConexionBD.setPort("3306");

            //PRUEBA REGISTRO ORDEN
            /*Object[] produccion = new Object[3];
            produccion[0] = "MTB";
            produccion[1] = "26";
            produccion[2] = 5;
            Object[][] data = new Object[2][2];
            data[0] = new Object[]{"403436", 4};
            data[1] = new Object[]{"690733", 3};
            boolean f = ConsultorBD.registrarNuevaOrden("1190375460", produccion, data);/
            
            boolean f = setConteoRepuestos(true);
            System.out.println(f);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }*/
}