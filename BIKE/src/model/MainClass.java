
//sena 2015 -- Autores: miguel gonzalez, braden velasquez & neverlin santana

package model;

// <editor-fold defaultstate="collapsed" desc="imports">

import controller.ConexionBD;
import view.MenuPrincipal;

// </editor-fold>

public class MainClass {
    
    //VARIABLES GLOBALES JAVA => http://www.davidmarco.es/articulo/ambito-de-variables-en-java
    
    public static final MenuPrincipal menu = new MenuPrincipal();

    private static String usuario;
    private static String password;
    
    private static boolean contar_repuestos;

    /*public static MenuPrincipal getMenuPrincipal() {
        return menu;
    }*/
    
    //metodo principal o inicial de la aplicacion
    public static void main(String[] args) {

        //SETEO DE VARIABLES ENCARGADAS DE LA CONEXION A LA BASE DE DATOS
        ConexionBD.setUsuario("user_storebike");
        ConexionBD.setPassword("user_storebike");
        ConexionBD.setHost("localhost");
        ConexionBD.setPort("3306");
        
        usuario = "miguel";
        password = "jjjj";
        
        menu.setVisible(true);
        
        //AQUI VA LA IMAGEN PARA EL ICONO DE LA APLICACION        //directorio     //nombre, al cambiar hay que tener en cuenta la extension del archivo
        /*Image iconoPrograma;
        try {
            iconoPrograma = ImageIO.read(new File("mis_imagenes/icon_program.png"));
            menu.setIconImage(iconoPrograma);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el Icono de la aplicación."+ex.toString());
        }*/
    }
    
    public static String getUsuario() {
        return usuario;
    }
    public static void setUsuario(String usuario) {
        MainClass.usuario = usuario;
    }
    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        MainClass.password = password;
    }
    public static boolean isContar_repuestos() {
        return contar_repuestos;
    }
    public static void setContar_repuestos(boolean contar_repuestos) {
        MainClass.contar_repuestos = contar_repuestos;
    }
}