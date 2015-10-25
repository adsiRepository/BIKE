package controller.componentes;

import java.awt.Component;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Miguel
 */
public class ControlTablaArticulos {

    public static class TablaArticulos extends JTable {

        private Object[][] data;
        private final MiModeloTabla mi_modelo_tabla;

        public TablaArticulos() {
            super();
            mi_modelo_tabla = new MiModeloTabla();
            this.setModel(mi_modelo_tabla);
            this.setDefaultRenderer(Component.class, new RenderComponenteCelda());
            this.setDefaultEditor(Component.class, new EditorComponenteCelda());
            //int anchoContenedor = scroll_items_selec.getWidth();
            int[] anchos = new int[]{120, 55, 160, 210, 60};
            //int[] max_anchos = new int[]{200, 300, 38};
            /*int[] anchos = new int[]{ ((int)((anchoContenedor*30)/100)), ((int)((anchoContenedor*50)/100)), ((int)((anchoContenedor*20)/100)) };*/
            for (int i = 0; i < this.getColumnCount(); i++) {
                this.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setResizable(true);
            }
            this.setRowHeight(55);
        }

        /**
         * @param componentes
         * @throws java.lang.Exception*/
        public void actualizarTabla(Object[][] componentes) throws Exception {
            // <editor-fold defaultstate="collapsed" desc="CODIGO DEL METODO">
            try {
                limpiarTabla();
                data = new Object[componentes.length][5];
                String desc;
                for (int i = 0; i < componentes.length; i++) {
                    if (componentes[i][3] != null) {
                        desc = componentes[i][3].toString();
                    } else {
                        desc = "No hay descripcion";
                    }
                    data[i] = new Object[]{
                        componentes[i][0],
                        componentes[i][1].toString(),
                        componentes[i][2].toString(),
                        new ScrollMyTextArea(desc),
                        true
                    };
                }
                mi_modelo_tabla.fireTableDataChanged();
            } catch (Exception er) {
                throw new Exception("Error al Actualizar la Tabla de Alistamiento.\nError: " + er.toString());
            }
            // </editor-fold>
        }
        
        /**
         * @param componentes
         * @throws java.lang.Exception*/
        public void actualizarTablaNuevoArticulo(Object[][] componentes) throws Exception {
            // <editor-fold defaultstate="collapsed" desc="CODIGO DEL METODO">
            try {
                limpiarTabla();
                data = new Object[componentes.length][5];
                String desc;
                for (int i = 0; i < componentes.length; i++) {
                    if (componentes[i][3] != null) {
                        desc = componentes[i][3].toString();
                    } else {
                        desc = "No hay descripcion";
                    }
                    data[i] = new Object[]{
                        componentes[i][0],
                        componentes[i][1].toString(),
                        componentes[i][2].toString(),
                        new ScrollMyTextArea(desc),
                        false
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
         * @return 
         * @throws java.lang.Exception*/
        public Object[] obtenerSeleccion() throws Exception{
            try {
                ArrayList<Object> caja = new ArrayList<>();
                boolean selec;
                int i;
                for(i = 0; i < mi_modelo_tabla.getRowCount(); i++){
                    selec = (boolean)mi_modelo_tabla.getValueAt(i, 4);
                    if(selec){
                        caja.add(mi_modelo_tabla.getValueAt(i, 1));
                    }
                }
                if(caja.size() > 0){
                    Iterator it = caja.iterator();
                    i = 0;
                    Object[] seleccion = new Object[caja.size()];
                    while (it.hasNext()) {
                        seleccion[i] = it.next();
                        i++;
                    }
                    return seleccion;
                }
                else{
                    return new Object[]{};
                    //throw  new Exception("No se han seleccionado Componentes para este articulo.");
                }
            } catch (Exception e) {
                throw new Exception("No se ha podido obtener el listado seleccionado.\n"
                        + "Error: "+e.getLocalizedMessage());
            }
        }
        
        
        /**
         * 
         */
        private class MiModeloTabla extends DefaultTableModel {
            // <editor-fold defaultstate="collapsed" desc="MODELO DE LA TABLA">

            private final Class[] CLASES_COLUMNAS = new Class[]{String.class, String.class, String.class, ScrollMyTextArea.class, Boolean.class};
            private final String[] TITULOS_COLUMNAS = new String[]{"Familia", "Codigo", "Componente", "Descripcion", "Usado"};
            private final boolean[] COLS_EDITABLES = new boolean[]{false, false, false, false, true};

            /**
             * Constructor del Modelo de la TablaAlistamiento de Alistamiento.
             */
            public MiModeloTabla() {
                super();
                data = new Object[1][5];
                data[0] = new Object[]{"familia","","componente",new ScrollMyTextArea("descripcion"),false};
                try {
                    this.setColumnIdentifiers(TITULOS_COLUMNAS);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al construir la tabla de Articulos.\n" + e.toString());
                }
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

        /**
         *
         */
        private static class ScrollMyTextArea extends JScrollPane {

            public String mi_texto;
            private final AreaTexto my_area;

            public ScrollMyTextArea(String contenido) {
                super();
                my_area = new AreaTexto();
                mi_texto = contenido;
                my_area.setText(mi_texto);
                this.setViewportView(my_area);
                this.setWheelScrollingEnabled(true);
                this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            }

            private static class AreaTexto extends JTextArea {

                public AreaTexto() throws HeadlessException {
                    super();
                    this.setLineWrap(true);
                    this.setWrapStyleWord(true);
                }
            }

        }

    }

}
