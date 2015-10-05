
package controller.componentes;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Miguel
 */
public class Tabla {
    
       
    //<editor-fold defaultstate="collapsed" desc="COMPONENTES(personalizados) EDITORES DE CELDAS DE TABLA">
    //FUENTES
        //http://swing-facil.blogspot.com.co/2012/01/jbutton-jcheckbox-jcombobox-en-jtable.html
    //<editor-fold defaultstate="collapsed" desc="EDITOR SPINNER">
    /**
     * esta clase redefine un spinner haciendo que no se le puedan ingresar
     * letras por ejemplo, o cuaquier configuracion que el programador quiera
     * darSPINNER 
     */
    public static class SpinnerCeldaTabla extends JSpinner {
        // <editor-fold defaultstate="collapsed" desc="Codigo de la Clase SpinnerCeldaTabla">
        
        private final SpinnerModel myspinnerNumModel;
        
        
        /**
         * Constructor Spinner
         */
        public SpinnerCeldaTabla() {
            super();
            myspinnerNumModel = new SpinnerNumberModel(1, 0, 10, 1);
            constructorGeneral();
        }
        
        /**
         * Constructor Spinner con Modelo
         * @param spinnerModel
         */
        public SpinnerCeldaTabla(SpinnerModel spinnerModel) {
            super();
            if (spinnerModel == null) {
                //throw new NullPointerException("model cannot be null");
                this.myspinnerNumModel = new SpinnerNumberModel(1, 0, 10, 1);// Dato visualizado al inicio en el spinner, minimo, maximo, paso
            }
            else{
                this.myspinnerNumModel = spinnerModel;
            }
            constructorGeneral();
        }
        
        public SpinnerCeldaTabla(int porDefecto, int min, int max, int paso) {
            super();
            this.myspinnerNumModel = new SpinnerNumberModel(porDefecto, min, max, paso);
            constructorGeneral();
        }
        
        private void constructorGeneral(){
            this.setModel(myspinnerNumModel);
            JSpinner.DefaultEditor editor;
            editor = ((JSpinner.DefaultEditor)this.getEditor());
            editor.getTextField().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char typ = e.getKeyChar();
                    if ((typ < '0' || typ > '9')) {
                        e.consume();
                    }
                }
                @Override
                public void keyPressed(KeyEvent e) {
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });//aqui aplico la clase que implementa los listener del teclado para impedir la insercion de letras
        }
        
        // </editor-fold>

        private static class MyModel extends AbstractSpinnerModel{
            // <editor-fold defaultstate="collapsed" desc="MODELO DEL SPINNER (no implementado aun)">
//FUENTES
            //https://docs.oracle.com/javase/tutorial/uiswing/components/spinner.html
            
            @Override
            public Object getValue() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public void setValue(Object value) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public Object getNextValue() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public Object getPreviousValue() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

// </editor-fold>
        }
        
    }
    //</editor-fold>

    //##############
// </editor-fold>    
    
    // <editor-fold defaultstate="collapsed" desc="CLASES PARA LA GRAFICACION DE COMPONENTES (combobox, boton..) DENTRO DE TABLA Y SU CAPACIDAD DE DETERMINAR EL VALOR DE LA CELDA (edicion)">
        //FUENTES
        //insertar componentes en celdas de Tabla ->
        //http://www.java2s.com/Tutorial/Java/0240__Swing/UsingaJComboBoxinaCellinaJTableComponent.htm
        //http://www.chuidiang.com/java/tablas/tablaeditor/tablaeditor.php
    public static class RenderComponenteCelda implements TableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (Component) value;
        }
    }
    
    public static class EditorComponenteCelda extends AbstractCellEditor implements TableCellEditor {
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
