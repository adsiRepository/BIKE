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
    
        // <editor-fold defaultstate="collapsed" desc="CLASES PARA LA CONFIGURACION DE COMPONENTES (COMO COMBOBOX O SPINNER) PARA INSERTARLOS EN LAS CELDAS DE LAS TABLAS">
        //FUENTES
        //insertar componentes en celdas de tabla ->
        //http://www.java2s.com/Tutorial/Java/0240__Swing/UsingaJComboBoxinaCellinaJTableComponent.htm
        //http://www.chuidiang.com/java/tablas/tablaeditor/tablaeditor.php
    public static class ComponentCellRenderer implements TableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (Component) value;
        }
    }
    public static class ComponentCellEditor implements TableCellEditor {
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return (Component) value;
        }
        @Override
        public Object getCellEditorValue() {
            return "CellEditorValue";//combo.getSelectedItem().toString();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public boolean isCellEditable(EventObject anEvent) {
            return true;
        }
        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }
        @Override
        public boolean stopCellEditing() {
            return true;
        }
        @Override
        public void cancelCellEditing() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            System.out.println("Edicion Cancelada");
        }
        @Override
        public void addCellEditorListener(CellEditorListener l) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            //System.out.println("addCellEditorListener");
        }
        @Override
        public void removeCellEditorListener(CellEditorListener l) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            System.out.println("removeCellEditorListener");
        }
    }// </editor-fold>
    
}
