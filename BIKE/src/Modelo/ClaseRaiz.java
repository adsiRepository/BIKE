//sena 2015 -- miguel gonzalez & ...

package Modelo;

import Vista.MenuPrincipal;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ClaseRaiz {
    
    //public static MenuPrincipal menu;
    
    //metodo principal o inicial de la aplicacion
    public static void main(String[] args) {
        // TODO code application logic here
        MenuPrincipal menu = new MenuPrincipal();
        //AQUI VA LA IMAGEN PARA EL ICONO DE LA APLICACION        //directorio     //nombre, al cambiar hay que tener en cuenta la extension del archivo
        //Image iconoPrograma = Toolkit.getDefaultToolkit().getImage(menu.getClass().getResource("../Recursos/imgs/icon_program.png"));
        Image iconoPrograma;
        try {
            iconoPrograma = ImageIO.read(new File("icon_program.png"));
            menu.setIconImage(iconoPrograma);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        menu.setTitle("BIKEWORK");
        menu.setVisible(true);
    }
   
}
