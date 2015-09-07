
//code

package Modelo;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Miguel
 */
public class ModeloTabla extends AbstractTableModel implements TableCellRenderer, TableCellEditor{
    
    private Class[] TIPOS_COLUMNAS_TABLA_DESPACHOS;
    private boolean[] columnas_editables;
    Object[][] data;

    public ModeloTabla(){
        TIPOS_COLUMNAS_TABLA_DESPACHOS = new Class[]{
            String.class, JComboBox.class, Integer.class //esto quiere decir que las celdas de la tabla serán: String, un combo con las partes seleccionables y la cantidad de estas 
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

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return (Component)value;
    }

    @Override
    public Object getCellEditorValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean stopCellEditing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelCellEditing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return (Component)value;
    }
    
    
    
    
    public class EditorComponenteCelda extends AbstractCellEditor implements TableCellEditor{

        private Component componente_celda;
        
        public EditorComponenteCelda(Component component){
            
        }
        
        @Override
        public Object getCellEditorValue() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    
    
}
