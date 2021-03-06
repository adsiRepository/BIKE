
package controller.componentes;

import java.awt.Component;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Miguel
 */
public class ControlComponentes {
    
    public static class TablaFamilias extends JTable {

        Object[][] data_madre;
        private Object[][] data;
        private final MiModeloTabla mi_modelo_tabla;

        public TablaFamilias() {
            super();
            mi_modelo_tabla = new MiModeloTabla();
            this.setModel(mi_modelo_tabla);
            this.setDefaultRenderer(Component.class, new RenderComponenteCelda());
            this.setDefaultEditor(Component.class, new EditorComponenteCelda());
            int[] anchos = new int[]{150, 210, 80};
            for (int i = 0; i < this.getColumnCount(); i++) {
                this.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setResizable(true);
            }
            this.setRowHeight(35);
        }

        /**
         * Método publico utizado para cambiar la informacion de la tabla
         * @param componentes
         * @throws java.lang.Exception
         */
        public void actualizaTabla(Object[][] componentes) throws Exception {
            // <editor-fold defaultstate="collapsed" desc="CODIGO DEL METODO">
            try {
                limpiarTabla();
                data_madre = new Object[componentes.length][4];
                data = new Object[componentes.length][3];
                String desc;
                boolean par;
                for (int i = 0; i < componentes.length; i++) {
                    if (componentes[i][2] != null) {
                        desc = componentes[i][2].toString();
                    } else {
                        desc = "No hay descripcion";
                    }
                    par = (boolean)componentes[i][3];
                    data_madre[i] = new Object[]{
                        componentes[i][0],
                        componentes[i][1].toString(),
                        desc,
                        par
                    };
                    data[i] = new Object[]{
                        componentes[i][1].toString(),
                        desc,
                        par
                    };
                }
                mi_modelo_tabla.fireTableDataChanged();
            } catch (Exception er) {
                throw new Exception("Error al Actualizar la Tabla de Familias.\nError: " + er.toString());
            }
            // </editor-fold>
        }

        /**
         * metodo para vaciar la tabla
         * 
         */
        public void limpiarTabla() {
            data = new Object[][]{};
            mi_modelo_tabla.fireTableDataChanged();
        }

        /**
         * @param i indice
         * @return Objeto
         */
        public Object obtenerCodigoFamilia(int i){
            return data_madre[i][0];
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
                    throw  new Exception("No se han seleccionado Componentes para este articulo.");
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

            private final Class[] CLASES_COLUMNAS = new Class[]{String.class, String.class, Boolean.class};
            private final String[] TITULOS_COLUMNAS = new String[]{"Familia", "Descripcion", "Par"};
            private final boolean[] COLS_EDITABLES = new boolean[]{false, false, false};

            /**
             * Constructor del Modelo de la TablaAlistamiento de Alistamiento.
             */
            public MiModeloTabla() {
                super();
                data = new Object[1][3];
                data[0] = new Object[]{"familia", "descripcion", false};
                try {
                    this.setColumnIdentifiers(TITULOS_COLUMNAS);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al construir la tabla de Familias.\n" + e.toString());
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
                    return data[row][col];
                }
                return null;
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
    
    
    /////////TABLA COMPONENTES ****
    
    
    /**
     * TABLA COMPONENTES.
     */
    public static class TablaComponentes extends JTable {

        private Object[][] data;
        private Object[][] data_madre;
        private final MiModeloTabla mi_modelo_tabla;

        public TablaComponentes() {
            super();
            mi_modelo_tabla = new MiModeloTabla();
            this.setModel(mi_modelo_tabla);
            this.setDefaultRenderer(Component.class, new RenderComponenteCelda());
            this.setDefaultEditor(Component.class, new EditorComponenteCelda());
            int[] anchos = new int[]{180, 170};
            for (int i = 0; i < this.getColumnCount(); i++) {
                this.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
                this.getColumnModel().getColumn(i).setResizable(true);
            }
            this.setRowHeight(35);
        }

        /**
         * Método publico utizado para cambiar la informacion de la tabla
         * @param componentes
         * @throws java.lang.Exception
         */
        public void actualizaTabla(Object[][] componentes) throws Exception {
            // <editor-fold defaultstate="collapsed" desc="CODIGO DEL METODO">
            try {
                limpiarTabla();
                data_madre = new Object[componentes.length][4];
                data = new Object[componentes.length][2];
                String desc;
                for (int i = 0; i < componentes.length; i++) {
                    if (componentes[i][2] != null) {
                        desc = componentes[i][2].toString();
                    } else {
                        desc = "No hay descripcion";
                    }
                    data_madre[i] = new Object[]{
                        componentes[i][0].toString(),
                        componentes[i][1].toString(),
                        desc,
                        componentes[i][3]
                    };
                    data[i] = new Object[]{
                        componentes[i][1].toString(),
                        desc,
                    };
                }
                //data = new Object[data_madre.length][2];
                mi_modelo_tabla.fireTableDataChanged();
            } catch (Exception er) {
                throw new Exception("Error al Actualizar la Tabla de Componentes.\nError: " + er.toString());
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
         * @param i indice del item a recuperar
         * @return */
        public Object obtenerCodigoComponente(int i){
            return data_madre[i][0];
        }
        
        public Object obtenerMiCodigoFamilia(int i){
            return data_madre[i][3];
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
                    throw  new Exception("No se han seleccionado Componentes para este articulo.");
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

            private final Class[] CLASES_COLUMNAS = new Class[]{String.class, String.class};
            private final String[] TITULOS_COLUMNAS = new String[]{"Componente", "Descripcion"};
            private final boolean[] COLS_EDITABLES = new boolean[]{false, false};

            /**
             * Constructor del Modelo de la TablaAlistamiento de Alistamiento.
             */
            public MiModeloTabla() {
                super();
                data = new Object[1][2];
                data[0] = new Object[]{"componente", ""};
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

    }
    
}
