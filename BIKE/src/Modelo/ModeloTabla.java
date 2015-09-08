
//code

package Modelo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 
 */
public class ModeloTabla extends AbstractTableModel{
    
    // <editor-fold defaultstate="collapsed" desc="Codigo que Define un Modelo Abstracto de Tabla implementado por el programador">
    private Class[] TIPOS_COLUMNAS_TABLA_DESPACHOS;
    private boolean[] columnas_editables;
    Object[][] data;

    /**
     * Constructor de la Clase que se esta heredando de AbstractTableModel
     */
    public ModeloTabla() {
        TIPOS_COLUMNAS_TABLA_DESPACHOS = new Class[]{
            String.class, JComboBox.class, JSpinner.class //esto quiere decir que las celdas de la tabla serán: String, un combo con las partes seleccionables y la cantidad de estas 
        };
        
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        return TIPOS_COLUMNAS_TABLA_DESPACHOS[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return col > 0;
    }
    
    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

// </editor-fold>

    //SPINNER EDITOR
    /**
     * esta clase redefine un spinner haciendo que no se le puedan ingresar
     * letras por ejemplo, o cuaquier configuracion que el programador quiera
     * dar
     */
    public static class SpinnCellTable extends JSpinner {
        // <editor-fold defaultstate="collapsed" desc="Codigo de la Clase SpinnCellTable">

        private SpinnerModel myspinnerNumModel;
        private JSpinner.DefaultEditor editor;
        
        private static class VigiaTecleoDigitos implements KeyListener {//clase regular que implementa el escucha o vigia de teclas oprimidas. Aqui se usa para evitar el ingreso de letras en un campo numerico 
            // <editor-fold defaultstate="collapsed" desc="metodos abstractos del keylistener implementados y sobreescritos">

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

// </editor-fold>
        }
        
        /**
         * Constructor Spinner
         */
        public SpinnCellTable() {
            super();
            editor = ((JSpinner.DefaultEditor) getEditor());
            editor.getTextField().addKeyListener(new VigiaTecleoDigitos());
        }
        
        /**
         * Constructor Spinner con Modelo
         * @param spinnerModel
         */
        public SpinnCellTable(SpinnerModel spinnerModel) {
            super();
            if (spinnerModel == null) {
                throw new NullPointerException("model cannot be null");
            }
            this.setModel(spinnerModel);
            this.myspinnerNumModel = spinnerModel;
            editor = ((JSpinner.DefaultEditor) getEditor());
            editor.getTextField().addKeyListener(new VigiaTecleoDigitos());
        }

// </editor-fold>
    }
   
}
