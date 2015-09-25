//code
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
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

        private static Connection connbd;

        public static HashMap<String, String> obtenerCatalogoArticulos() {

            connbd = ConexionBD.obtenerConexion();
            HashMap<String, String> contenedor = new HashMap<>();
            ResultSet resultados;

            try (java.sql.PreparedStatement sentencia = connbd.prepareStatement("select id_articulo, articulo from articulos;")) {
                resultados = sentencia.executeQuery();
                while (resultados.next()) {
                    contenedor.put(resultados.getString(1), resultados.getString(2));
                }
                resultados.close();
                connbd.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "Error Consulta de Catalogo", 0);
                contenedor.put("Error", "Error en la Consulta BD.");
            }
            return contenedor;
        }
        
        
        
        public static HashMap<String, String> obtenerListaEnsambladores() {

            connbd = ConexionBD.obtenerConexion();
            HashMap<String, String> retorno = new HashMap<>();
            ResultSet resultados;

            try (Statement sentencia = connbd.createStatement()) {
                resultados = sentencia.executeQuery("select id_emp, nom_emp, ape_emp from ensambladores;");
                while (resultados.next()) {
                    retorno.put(resultados.getString(1), resultados.getString(2) + " " + resultados.getString(3));
                }
                resultados.close();
                connbd.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "Error en Consulta Ensambladores", 0);
                retorno.put("Error", "Errores de Consulta.");
            }
            return retorno;
        }

        
    }

}
