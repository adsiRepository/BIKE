
//code

package Modelo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class ModeloTabla extends AbstractTableModel{
    
    // <editor-fold defaultstate="collapsed" desc="Codigo que Define un Modelo Abstracto de Tabla implementado por el programador">
    //private Class[] TIPOS_COLUMNAS;
    //private String[] nombres_columnas;
    //private boolean[] columnas_editables;
    public Object[][] data;

    /**
     * Constructor de la Clase que se esta heredando de AbstractTableModel
     */
    public ModeloTabla(){
        
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;//columnas_editables[col];
    }
    
    @Override
    public int getRowCount() {
        return 1;//data.length;
    }
    
    @Override
    public int getColumnCount() {
        return 1;//nombres_columnas.length;
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        return 1;//data[row][col];
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        //data[row][col] = value;
    }

// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="CLASES INTERNAS QUE SE CLASIFICAN BAJO EL CONTEXTO DE EDITORES Y MODELOS DE TABLAS">
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
         *
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
    
    public static class CellTableButton extends JButton {

        //fuentes -> http://www.edu4java.com/es/swing/swing4.html

        private static class OpresionBoton implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Haz oprimido el boton de la celda");
            }
        }

        /*@Override
        public synchronized void addInputMethodListener(InputMethodListener l) {
            super.addInputMethodListener(l); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public void addActionListener(ActionListener l) {
            //super.addActionListener(l); //To change body of generated methods, choose Tools | Templates.
            JOptionPane.showMessageDialog(null, "Haz oprimido el boton de la celda");
        }*/
        
        public CellTableButton() {
            super();
            this.addActionListener(new OpresionBoton());
        }

        public CellTableButton(String text) {
            super(text);
            this.addActionListener(new OpresionBoton());
        }
        
        @Override
        public void paint(Graphics grf) {
            super.paint(grf);
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(15, 10);
        }
        
    }
//##############
// </editor-fold>    
}
