
package controller.componentes;

import controller.Tiempo;
import java.awt.Component;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
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
public class ControlProduccion {
    
    public static class TablaControlProduccion extends JTable {

        private Object[][] data;
        private final MiModeloTabla mi_modelo_tabla;

        public TablaControlProduccion() {
            super();
            mi_modelo_tabla = new MiModeloTabla();
            this.setModel(mi_modelo_tabla);
            this.setDefaultRenderer(Component.class, new RenderComponenteCelda());
            this.setDefaultEditor(Component.class, new EditorComponenteCelda());
            //int anchoContenedor = scroll_items_selec.getWidth();
            int[] anchos = new int[]{100, 160, 80, 180, 180};
            //int[] max_anchos = new int[]{200, 300, 38};
            /*int[] anchos = new int[]{ ((int)((anchoContenedor*30)/100)), ((int)((anchoContenedor*50)/100)), ((int)((anchoContenedor*20)/100)) };*/
            for (int i = 0; i < this.getColumnCount(); i++) {
                this.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setResizable(true);
            }
            this.setRowHeight(25);
        }

        /**
         * @param ordenes
         * @throws java.lang.Exception*/
        public void actualizarTabla(ArrayList<Object[]> ordenes) throws Exception {
            // <editor-fold defaultstate="collapsed" desc="CODIGO DEL METODO">
            try {
                limpiarTabla();
                if (ordenes != null) {
                    data = new Object[ordenes.size()][5];
                    Iterator it = ordenes.iterator();
                    int i = 0;
                    String fecha_despacho, hora_despacho;
                    String fecha_entrega, hora_entrega;
                    while (it.hasNext()) {
                        Object[] orden = (Object[]) it.next();
                        fecha_despacho = Tiempo.calendarFechaToString((java.sql.Timestamp)orden[3]);
                        hora_despacho = Tiempo.calendarHoraToString((java.sql.Timestamp)orden[3]);
                        fecha_entrega = Tiempo.calendarFechaToString((java.sql.Timestamp)orden[4]);
                        hora_entrega = Tiempo.calendarHoraToString((java.sql.Timestamp)orden[4]);
                        data[i] = new Object[]{
                            orden[0],
                            orden[1],
                            orden[2],
                            fecha_despacho + "  " + hora_despacho,
                            fecha_entrega + "  " + hora_entrega
                        };
                        i++;
                    }
                    mi_modelo_tabla.fireTableDataChanged();
                }
                else {
                    data = new Object[1][5];
                    data[0] = new Object[]{
                        "No",
                        "Hay",
                        0,
                        "Ordenes",
                        "Despachadas",
                    };
                    mi_modelo_tabla.fireTableDataChanged();
                }
            } catch (Exception er) {
                throw new Exception("Error al Actualizar la Tabla de Producciones.\nError: " + er.toString());
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

            private final Class[] CLASES_COLUMNAS = new Class[]{
                String.class, String.class, Integer.class, String.class, String.class
            };
            private final String[] TITULOS_COLUMNAS = new String[]{
                "No. Orden", "Ensamblador", "Cantidad", "Despachado", "Entregado"
            };
            private final boolean[] COLS_EDITABLES = new boolean[]{false, false, false, false, false};

            /**
             * Constructor del Modelo de la TablaAlistamiento de Alistamiento.
             */
            public MiModeloTabla() {
                super();
                this.setColumnIdentifiers(TITULOS_COLUMNAS);
                //data = new Object[1][5];
                //data[0] = new Object[]{"familia","","componente",new ScrollMyTextArea("descripcion"),false};
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
                    /*if(col == 1){
                        return data[row][col+1];
                    }*/
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
                /*if(col == 1){
                    data[row][col - 1] = value;
                }*/
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
