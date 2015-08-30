
                                    /*  SENA BRETAÑA CALI   */


package ConexionBD;
//Importar las librerias que requerimos para la conexion
import java.sql.Connection; // LIBRERIA DE CONEXION BD
import java.sql.DriverManager; //DRIVER DE MANEJO DE DATOS
import java.sql.SQLException; //OMITIR O ENVIAR MENSAJES DE ERROR SQL
import javax.swing.JOptionPane; // ENVIA MENSAJES EMERGENTES EN PANTALLA


/*
 * creation    : 26/11/2014, 07:45:23 AM
 * author      : miguel gonzález
 */

public class ConnBD {
    
    public static Connection connect_BD(){
        Connection conex;
        try{
            Class.forName("com.mysql.jdbc.Driver");//libreria mysql connector
            String user_bd = "root", passbd = "", 
                    server_bd = "jdbc:mysql://localhost/leboutique";//url y base de datos
            conex = DriverManager.getConnection(server_bd, user_bd, passbd);
            return conex;
        }
        catch(ClassNotFoundException ex){//
            JOptionPane.showMessageDialog(null, "Problemas Técnicos que no permiten la Conexión a Base de Datos\n"
                    + "Detalle: "+ex.getMessage(), "ConnBD", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en la consulta efectuada:\n"+ex.getMessage(), "ConnBD", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /*public static void main(String args[]){
    }*/
}
