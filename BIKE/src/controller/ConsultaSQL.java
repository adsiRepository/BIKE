//code
package controller;

import model.componentes.ItemOfCollection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;//Maneja el ArrayList
import java.util.HashMap;
import javax.swing.JOptionPane;

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
        /*ArrayList<Object> aux = new ArrayList<>();
        //String[] reg = new String[datos.size()];
        for (int i=0; i<datos.size(); i++) {
         aux.add(i) = "'"+datos.get(i)+"'";
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

    
    public static class ConsultorBD {

        /**
         * @param cod_articulo
         * @return => retorna un HashMap (un Arreglo clave-valor), cada item es uno de un combobox.
         * El primer String es el codigo o id del Item, este a su vez es la clave dentro del HashMap.
         * El HashMap anidado seria el valor de esa clave, este HashMap contiene los atributos del Item.
         * 
         * ESTE METODO EN PARTICULAR OBTIENE EL LISTADO DE REPUESTOS DISPONIBLES PARA CADA COMPONENTE 
         * DEL ARTICULO, O MANUFATURA O ENSAMBLE QUE SE REALICE.
         */
        public static HashMap<String, ArrayList<ItemOfCollection>> obtenerComponentesDeArticulo(String cod_articulo){
            
            Connection connbd = ConexionBD.obtenerConexion();
            HashMap<String, ArrayList<ItemOfCollection>> retorno = new HashMap<>();
            ArrayList<ItemOfCollection> items;
            HashMap<String, String> aux;
          
            if (connbd != null) {
                
                ResultSet result_a;
                try (java.sql.PreparedStatement sentencia = connbd.prepareStatement("select c.id_comp, c.nom_comp from componentes c inner join componente_articulo ca "
                        + "where ca.componente = c.id_comp and ca.articulo = '" + cod_articulo + "';")) {

                    result_a = sentencia.executeQuery();

                    while (result_a.next()) {
                        ResultSet result_b;
                        try (java.sql.PreparedStatement sentencia_b = connbd.prepareStatement(
                                    "select cod_rep, repuesto from repuestos where componente = '" + result_a.getString(1) + "';")) {
                            result_b = sentencia_b.executeQuery();
                            items = new ArrayList<>();
                            
                            while (result_b.next()) {        //al hacer getString(n) al resultset. n = 1 será, en este caso, cod_rep
                                //es decir el indice del resultset empieza por 1 sobre el primer campo que pedimos en el select
                                aux = new HashMap<>();
                                //un ItemOfCollection tiene como propiedades el id o cod del objeto, mas un HashMap (que es un arraylist a modo de
                                //  llave - valor; tanto la llave como el valor pueden ser de cualquier clase, pero la primera siempre referenciará
                                // y será la unica "llave" de acceso a la segunda, su referencia), que contiene los atributos de cada item.
                                aux.put(ItemOfCollection.TEXTO_A_MOSTRAR, result_b.getString(2));//aquí lleno el HashMap de atributos
                                //en el cosntructor de ItemOfCollection va el String id o cod, y el HashMap de atributos
                                items.add(new ItemOfCollection(result_b.getString(1), aux));
                            }
                            /*items = new ArrayList<>();
                             aux = new HashMap<>();
                             aux.put(ComboBoxCeldaTabla.TEXTO_A_MOSTRAR, "..su madre.");
                             items.add(new ItemOfCollection("item1", aux));*/
                            retorno.put(result_a.getString(2), items);
                        }
                        result_b.close();
                    }

                    result_a.close();
                    connbd.close();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString(), "Error en Consulta Componentes", 0);
                    items = new ArrayList<>();
                    aux = new HashMap<>();
                    aux.put(ItemOfCollection.TEXTO_A_MOSTRAR, "Problemas en BD.");
                    items.add(new ItemOfCollection("error", aux));
                    retorno.put("error", items);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No Existe Conexión a la Base de Datos", "Error Busquda Repuestos", 0);
            }
            
            return retorno;
        }
        
        
        
        /**
         * @return => retorna un HashMap (un Arreglo clave-valor), cada item es uno de un combobox.
         * El primer String es el codigo o id del Item, este a su vez es la clave dentro del HashMap.
         * El HashMap anidado seria el valor de esa clave, este HashMap contiene los atributos del Item.
         */
        public static HashMap<String, HashMap<String, String>> obtenerCatalogoArticulos() {

            Connection connbd = ConexionBD.obtenerConexion();
            HashMap<String, HashMap<String, String>> contenedor = new HashMap<>();
            HashMap<String, String> ox = new HashMap<>();
            
            if (connbd != null) {
                ResultSet resultados;
                try (java.sql.PreparedStatement sentencia = connbd.prepareStatement("select id_articulo, articulo, descripcion from articulos;")) {
                    resultados = sentencia.executeQuery();
                    while (resultados.next()) {
                        ox.put(ItemOfCollection.TEXTO_A_MOSTRAR, resultados.getString(2));
                        ox.put("descripcion", resultados.getString(3));
                        contenedor.put(resultados.getString(1), ox);
                        ox = new HashMap<>();
                    }
                    resultados.close();
                    connbd.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString(), "Error Consulta de Catalogo", 0);
                    ox.put("error", "Problemas en BD.");
                    contenedor.put("Error", ox);
                }
            } else {
                ox.put("error", "No hay Conexion a BD.");
                contenedor.put("Error", ox);
            }
            return contenedor;
        }
        
        
        /**
         * @return => retorna un HashMap (un Arreglo clave-valor), cada item es uno de un combobox.
         * El primer String es el codigo o id del Item, este a su vez es la clave dentro del HashMap.
         * El HashMap anidado seria el valor de esa clave, este HashMap contiene los atributos del Item.
         */
        public static HashMap<String, HashMap<String, String>> obtenerListaEnsambladores() {

            Connection connbd = ConexionBD.obtenerConexion();
            HashMap<String, HashMap<String, String>> retorno = new HashMap<>();
            HashMap<String, String> ox = new HashMap<>(); // ESTE HASHMAP AUXILIAR ES EL QUE SE USARA EN EL CONSTRUCTOR DE CADA ITEMCOMBOBOX
            
            if(connbd != null){
                ResultSet resultados;
                try (Statement sentencia = connbd.createStatement()) {
                    resultados = sentencia.executeQuery("select id_emp, nom_emp, ape_emp from ensambladores;");
                    while (resultados.next()) {
                        // EL PRIMER PUT DENTRO DEL HASHMAP SIEMPRE SERA EL TEXTO A MOSTRAR EN EL ITEM DEL COMBOBOX
                        ox.put(ItemOfCollection.TEXTO_A_MOSTRAR,  resultados.getString(2) + " " + resultados.getString(3));
                        retorno.put(resultados.getString(1),  ox);
                        ox = new HashMap<>();//AL TERMINAR DE AGREGAR EL HASHMAP, LO REDEFINO COMO UN NUEVO OBJETO PARA EVITAR INTERFERENCIAS CON EL PROXIMO ITEMCOMBOBOX
                    }
                    resultados.close();
                    connbd.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString(), "Error en Consulta Ensambladores", 0);
                    ox.put(ItemOfCollection.TEXTO_A_MOSTRAR, "Problemas en BD.");
                    retorno.put("Error", ox);
                }
            }
            else{
                ox.put(ItemOfCollection.TEXTO_A_MOSTRAR, "No hay Conexion a BD.");
                retorno.put("Error", ox);
            }
            return retorno;
        }

    }

}
