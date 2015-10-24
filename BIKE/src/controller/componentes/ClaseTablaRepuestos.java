
package controller.componentes;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Miguel
 */
public class ClaseTablaRepuestos {
    
    /***/
    public static class TablaRepuestos extends JTable {

        private Object[][] data;
        private final MiModeloTabla mi_modelo_tabla;

        public TablaRepuestos() {
            super();
            mi_modelo_tabla = new MiModeloTabla();
            this.setModel(mi_modelo_tabla);
            this.setDefaultRenderer(Component.class, new RenderComponenteCelda());
            this.setDefaultEditor(Component.class, new EditorComponenteCelda());
            //int anchoContenedor = scroll_items_selec.getWidth();
            int[] anchos = new int[]{60, 180, 140, 60, 70};
            //int[] max_anchos = new int[]{200, 300, 38};
            /*int[] anchos = new int[]{ ((int)((anchoContenedor*30)/100)), ((int)((anchoContenedor*50)/100)), ((int)((anchoContenedor*20)/100)) };*/
            for (int i = 0; i < this.getColumnCount(); i++) {
                this.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setResizable(true);
            }
            this.setRowHeight(35);
        }


        /**
         * Método publico utilizado para cambiar la informacion de la tabla
         *
         * @param repuestos
         * @throws java.lang.Exception
         */
        public void actualizaTabla(Object[][] repuestos) throws Exception {
            // <editor-fold defaultstate="collapsed" desc="CODIGO DEL METODO">
            try {
                limpiarTabla();
                data = new Object[repuestos.length][5];
                //String aux = "";
                for (int i = 0; i < repuestos.length; i++) {
                    //if()
                    data[i] = new Object[]{
                        repuestos[i][0].toString(),
                        repuestos[i][1].toString(),
                        repuestos[i][2],
                        repuestos[i][3],
                        (int)repuestos[i][4]
                    };
                }
                mi_modelo_tabla.fireTableDataChanged();
            } catch (Exception er) {
                throw new Exception("Error al Actualizar la Tabla de Alistamiento.\nError: " + er.toString());
            }
            // </editor-fold>
        }

        /**
         * 
         */
        public void limpiarTabla() {
            data = new Object[][]{};
            mi_modelo_tabla.fireTableDataChanged();
        }

        
        /**
         * 
         */
        private class MiModeloTabla extends DefaultTableModel {
            // <editor-fold defaultstate="collapsed" desc="MODELO DE LA TABLA">

            private final Class[] CLASES_COLUMNAS = new Class[]{String.class, String.class, String.class, String.class, Integer.class};
            private final String[] TITULOS_COLUMNAS = new String[]{"Codigo", "Referencia", "Articulo que Compone", "Talla", "Cantidad"};
            private final boolean[] COLS_EDITABLES = new boolean[]{false, false, false, false, false};

            /**
             * Constructor del Modelo de la TablaAlistamiento de Alistamiento.
             */
            public MiModeloTabla() {
                super();
                data = new Object[][]{};
                this.setColumnIdentifiers(TITULOS_COLUMNAS);
            }

            @Override//determina la clase de componentes que iran en cada celda
            public Class getColumnClass(int noCol) {
                return CLASES_COLUMNAS[noCol];
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                if (COLS_EDITABLES.length > 0) {
                    return COLS_EDITABLES[col];
                }
                return false;
            }

            @Override
            public int getColumnCount() {
                if (TITULOS_COLUMNAS.length > 0) {
                    return TITULOS_COLUMNAS.length;
                }
                return 0;
            }

            @Override
            public Object getValueAt(int row, int col) {
                if (data != null) {
                    return data[row][col];
                }
                return null; // no necesita estar dentro del else porque un return solo se ejecuta 1 vez
            }

            @Override
            public int getRowCount() {
                if (data == null) {
                    return 0;
                }
                return data.length;
            }

            @Override
            public void setValueAt(Object value, int row, int col) {
                data[row][col] = value;
                fireTableCellUpdated(row, col);//notifica a todos los listeners que el valor de la celda ha sido editado
            }
        }

        // <editor-fold defaultstate="collapsed" desc="CLASES PARA LA GRAFICACION DE COMPONENTES (combobox, boton..) DENTRO DE TABLA Y SU CAPACIDAD DE DETERMINAR EL VALOR DE LA CELDA (edicion)">
        private static class RenderComponenteCelda extends JLabel implements TableCellRenderer {

            public RenderComponenteCelda() {
                super();
                this.setBorder(null);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return (Component) value;
            }
        }

        private static class EditorComponenteCelda extends AbstractCellEditor implements TableCellEditor {

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                return (Component) value;
            }

            @Override
            public Object getCellEditorValue() {
                //return data[fil][col];
                return null;
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
                //this.fireEditingStopped();//
                return true;
            }

            @Override
            public void cancelCellEditing() {
                this.fireEditingCanceled();
            }

            @Override
            public void addCellEditorListener(CellEditorListener l) {
                super.addCellEditorListener(l);
            }

            @Override
            public void removeCellEditorListener(CellEditorListener l) {
                super.removeCellEditorListener(l);
            }
        }// </editor-fold>

    }
    
}
