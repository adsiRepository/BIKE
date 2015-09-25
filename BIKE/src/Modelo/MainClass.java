//sena 2015 -- miguel gonzalez & ...

package Modelo;

import Controlador.ConexionBD;
import Vista.MenuPrincipal;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class MainClass {
    
    //VARIABLES GLOBALES JAVA => http://www.davidmarco.es/articulo/ambito-de-variables-en-java
    
    private static MenuPrincipal menu;

    public static MenuPrincipal getMenuPrincipal() {
        return menu;
    }
    
    //metodo principal o inicial de la aplicacion
    public static void main(String[] args) {

        ConexionBD.setUsuario("user_storebike");
        ConexionBD.setPassword("user_storebike");
        ConexionBD.setHost("localhost");
        ConexionBD.setPort("3306");
        
        menu = new MenuPrincipal();
        //AQUI VA LA IMAGEN PARA EL ICONO DE LA APLICACION        //directorio     //nombre, al cambiar hay que tener en cuenta la extension del archivo
        //Image iconoPrograma = Toolkit.getDefaultToolkit().getImage(menu.getClass().getResource("../Recursos/imgs/icon_program.png"));
        Image iconoPrograma;
        try {
            iconoPrograma = ImageIO.read(new File("mis_imagenes/icon_program.png"));
            menu.setIconImage(iconoPrograma);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        menu.setTitle("BIKEWORK");
        menu.setVisible(true);
 
    }
   
}
