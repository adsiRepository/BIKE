
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
    static Connection conex;
    
    public static Connection connect_BD(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");//libreria mysql connector
            String user_bd = "root", passbd = "89102360029", 
                    server_bd = "jdbc:mysql://192.168.0.18:3306/pruebas";//url y base de datos
            conex = DriverManager.getConnection(server_bd, user_bd, passbd);
            return conex;
        }
        catch(ClassNotFoundException ex){//
            JOptionPane.showMessageDialog(null, "Excepcion "+ex.toString()+" que no permiten la Conexión a Base de Datos\n"
                    + "Detalle: "+ex.getMessage(), "ConnBD", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Exepcion "+ex.toString()+"\nDetalle: s"+ex.getMessage(), "ConnBD", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");//libreria mysql connector
            String    user_bd = "root",
                    passbd = "89102360029", 
                    server_bd = "jdbc:mysql://192.168.0.18/pruebas";//url y base de datos
            conex = DriverManager.getConnection(server_bd, user_bd, passbd);
            JOptionPane.showMessageDialog(null, "Conexion OK");
        }
        catch(ClassNotFoundException ex){//
            JOptionPane.showMessageDialog(null, "Excepcion "+ex.toString()+" que no permiten la Conexión a Base de Datos\n"
                    + "Detalle: "+ex.getMessage(), "ConnBD", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException ex){
            if (ex.getErrorCode() == 0) {
                JOptionPane.showMessageDialog(null, "No se Estableció la Conexion con el Servidor", "ConnBD", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }
}
