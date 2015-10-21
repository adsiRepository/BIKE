
//code

package controller;

import java.sql.Connection; // LIBRERIA DE CONEXION BD
import java.sql.DriverManager; //DRIVER DE MANEJO DE DATOS
import java.sql.SQLException; //OMITIR O ENVIAR MENSAJES DE ERROR SQL

/*
 * creation    : 26/11/2014, 07:45:23 AM
 * author      : miguel gonzález
 */

public class ConexionBD {

    private static String usuario;
    private static String password;
    private static String host;
    private static String port;

    public static Connection obtenerConexion() throws Exception{
        try{
            Connection conex;
            Class.forName("com.mysql.jdbc.Driver");//libreria mysql connector
            //ESTAS VARIABLES SE ESTAN SETEANDO EN LA CLASE PRINCIPAL. SE SETEAN EN OTRA PARTE POR SU CONDICION CAMBIANTE
            conex = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/storebike", usuario, password);
            return conex;
        }
        catch(ClassNotFoundException | SQLException ex){//
            /*JOptionPane.showMessageDialog(null, "Excepcion "+ex.toString()+" que no permiten la Conexión a Base de Datos\n"
                    + "Detalle: "+ex.getMessage(), "ConnBD", JOptionPane.ERROR_MESSAGE);*/
            throw new Exception("No se pudo establecer conexión son el Servidor.\nError: " + ex.toString());
            //return null;
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="//   SETTERS Y GETTERS">

    public static void setUsuario(String usuario) {
        ConexionBD.usuario = usuario;
    }
    
    public static void setPassword(String password) {
        ConexionBD.password = password;
    }
    
    public static String getUsuario() {
        return usuario;
    }
    
    public static String getPassword() {
        return password;
    }
    
    public static void setHost(String host) {
        ConexionBD.host = host;
    }
    
    public static String getHost() {
        return host;
    }
    
    public static void setPort(String port) {
        ConexionBD.port = port;
    }
    
    public static String getPort() {
        return port;
    }

// </editor-fold>
    
    
    /*public static void main(String[] args) {
        
        if(ConexionBD.obtenerConexion() != null){
            JOptionPane.showMessageDialog(null, "Conectado");
        }
        else{
            JOptionPane.showMessageDialog(null, "No Conectado");
        }
        
    }*/

}
