
package controller.componentes;

// <editor-fold defaultstate="collapsed" desc="imports">

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="TUTORIALES Y FUENTES">
//tuto perfecto para aprender a insertar botones en una celda de cualquier tabla -> https://www.youtube.com/watch?v=bVknuhawXsI
// </editor-fold>
/**
 *
 * @author Miguel
 */
public class TablaProduccion extends JTable{

    // <editor-fold defaultstate="collapsed" desc="DECLARACION DE VARIABLES">
    private final Class[] CLASES_COLUMNAS;
    private final String[] TITULOS_COLUMNAS;
    private final boolean[] COLS_EDITABLES;
    
    private Object[][] data;
    private ModeloTablaProduccion mi_modelo;
    // </editor-fold>
    
    /**TABLA IMPLEMENTADA Y ADECUADA PARA LA VISUALIZACION Y CONTROL DE LAS ORDENES DE PRODUCCION\n
     * Constructor que determina sus proporciones en base a su contenedor.
     * @param my_scroll el contenedor de una tabla por lo general en un JScrollPane*/
    public TablaProduccion(JScrollPane my_scroll) {
        super();
        CLASES_COLUMNAS = new Class[]{
            Integer.class,   String.class,    String.class,   String.class,
            TextArea.class,  String.class,   Integer.class,  PanelBotonesCelda.class
        };
        TITULOS_COLUMNAS = new String[]{
            "No. Orden",     "Ensamblador", "Hora Despacho", "Hora Entrega", 
            "Producción de", "Tamaño",      "Cantidad",       "Opciones"
        };
        COLS_EDITABLES = new boolean[]{
            false, false, false, false, false, false, false, true
        };
        mi_modelo = new ModeloTablaProduccion();
        this.setModel(mi_modelo);
        this.setDefaultRenderer(Component.class, new ComponentCellRenderer());
        this.setDefaultEditor(Component.class, new ComponentCellEditor());
        formatearTabla(my_scroll);
    }
    
    /***/
    private void formatearTabla(JScrollPane my_scroll){
        // <editor-fold defaultstate="collapsed" desc="FORMATEO DE LA TABLA (TAMAÑOS DE FILAS Y COLUMNAS)">
        int anchoContenedor = my_scroll.getWidth();
        int[] anchos = new int[]{((anchoContenedor * 16) / 100), ((anchoContenedor * 9) / 100), ((anchoContenedor * 30) / 100), ((anchoContenedor * 15) / 100), ((anchoContenedor * 15) / 100), ((anchoContenedor * 15) / 100)};
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            this.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
            this.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
        }
        this.setRowHeight(40);
        // </editor-fold>
    }

    /**
     * Modelo de la Tabla.
     */
    private class ModeloTablaProduccion extends DefaultTableModel {
        // <editor-fold defaultstate="collapsed" desc="MODELO DE LA TABLA">

        /**
         * Constructor del Modelo de la TablaAlistamiento de Alistamiento.
         */
        public ModeloTablaProduccion() {
            super();
            this.setColumnIdentifiers(TITULOS_COLUMNAS);
            data = new Object[][]{};
        }

        @Override//determina la clase de componentes que iran en cada celda
        public Class getColumnClass(int noCol) {
            return CLASES_COLUMNAS[noCol];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return COLS_EDITABLES[col];
        }

        @Override
        public int getColumnCount() {
            return TITULOS_COLUMNAS.length;
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

        // </editor-fold>
    }
    
    private class PanelBotonesCelda extends JPanel {
        // <editor-fold defaultstate="collapsed" desc="JPANEL PARA CELDA DE TABLA">
        
        // Variables declaration                    
        private final CellTableButton btncell_cancel_orden_;
        private final CellTableButton btncell_edit_orden_;
        private final CellTableButton btncell_ok_orden_;
        public int miFila, miCol;
        // End of variables declaration 
        
        /**
         * Creates new form PanelBotonesCelda
         */
        public PanelBotonesCelda() {
            btncell_cancel_orden_ = new CellTableButton(1);
            btncell_edit_orden_ = new CellTableButton(2);
            btncell_ok_orden_ = new CellTableButton(3);
            inicializacion();
        }

        /**
         * este metodo es llamado al inicializar el panel
         * este configura las cosas basicas
         */
        @SuppressWarnings("unchecked")
        private void inicializacion() {
            // <editor-fold defaultstate="collapsed" desc="Codigo Generado Automaticamente por el Disenador Netbeans">                          
            try {
                setLayout(new java.awt.GridLayout());
                
                btncell_cancel_orden_.setIcon(new ImageIcon("mis_imagenes/imgbtn_cancel.png"));
                btncell_cancel_orden_.setPreferredSize(new java.awt.Dimension(35, 35));
                add(btncell_cancel_orden_);

                btncell_edit_orden_.setIcon(new ImageIcon("mis_imagenes/imgbtn_edit.png")); 
                btncell_edit_orden_.setPreferredSize(new java.awt.Dimension(35, 35));
                add(btncell_edit_orden_);

                btncell_ok_orden_.setIcon(new ImageIcon("mis_imagenes/imgbtn_ok.png"));
                btncell_ok_orden_.setPreferredSize(new java.awt.Dimension(35, 35));
                add(btncell_ok_orden_);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }// </editor-fold>
        }                       

        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="CLASES INTERNAS DEL PANEL">
        private class CellTableButton extends JButton implements /*TableCellEditor,*/ ActionListener {
            // <editor-fold defaultstate="collapsed" desc="CLASE EXTENDIDA DE JBUTTON CONFIGURADA PARA INSERTAR BOTONES EN CELDAS DE UNA TABLA">
            //fuentes
            //http://www.edu4java.com/es/swing/swing4.html
            //http://stackoverflow.com/questions/1475543/how-to-add-button-in-a-row-of-jtable-in-swing-java

            //private int fil, col;
            private int miAccion;//1, 2 o 3 segun sea cancelar orden(1), modificarla(2) o entregar(3) 
            
            //<editor-fold defaultstate="collapsed" desc="METODOS DE CONSTRUCCION Y CONFIGURACION DE ESTA CLASE DE BOTON">
            public CellTableButton(int accion) {
                super();
                metodoConstructorGeneral(accion);
            }
            
            public CellTableButton(int accion, String text) {
                super(text);
                metodoConstructorGeneral(accion);
            }
            
            public CellTableButton(int accion, Icon icon) {
                super(icon);
                metodoConstructorGeneral(accion);
            }
            
            private void metodoConstructorGeneral(int accion) {
                addActionListener(this);
                this.miAccion = accion;
            }
            
            @Override
            public void paint(Graphics grf) {
                super.paint(grf);
                /*switch(miAccion){
                    case 1:
                        
                }*/
            }
    
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(15, 10);
            }
//</editor-fold>

            //Action Listener => metodo que se desata al accionar el boton u oprimirlo
            @Override
            public void actionPerformed(ActionEvent ae) {
                switch (miAccion) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Haz Cancelado la orden de la fila " + (miFila+1));
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Realmente Quieres Modificar la fila " + (miFila+1));
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Entregar la fila " + (miFila+1));
                        break;
                    default:
                        break;
                }
                
            }

            // <editor-fold defaultstate="collapsed" desc="METODOS IMPLEMENTADOS DE LA INTERFAZ TableCellEditor del Boton">
        /*@Override
             public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
             MenuPrincipal.fila_tabla = row;
             MenuPrincipal.col_tabla = column;
             return this;
             }

             @Override
             public Object getCellEditorValue() {
             return "Configurado";
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
             }

             @Override
             public void addCellEditorListener(CellEditorListener l) {
             }

             @Override
             public void removeCellEditorListener(CellEditorListener l) {
             }
             */
            // </editor-fold>
            // </editor-fold>
        }

// </editor-fold>
    }
    
    private static class ComponentCellRenderer implements TableCellRenderer {
        // <editor-fold defaultstate="collapsed" desc="CLASE QUE DIBUJA EL COMPONENTE DENTRO DE LA CELDA DE LA TABLA">

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            /*if(hasFocus == true){
             table.repaint();
             }*/
            return (Component) value;
        }

        // </editor-fold>
    }
    
    private static class ComponentCellEditor extends AbstractCellEditor implements TableCellEditor/*, ActionListener*/ {
        // <editor-fold defaultstate="collapsed" desc="CLASE QUE LE DA LA CAPACIDAD AL COMPONENTE DE EDITAR EL VALOR DE LA CELDA">

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if(column == 5){
                ((PanelBotonesCelda)value).miFila = row;
                ((PanelBotonesCelda)value).miCol = column;
                return (Component) value;
            }
            return (Component) value;
        }

        @Override
        public Object getCellEditorValue() {
            return "CellEditorValue";//combo.getSelectedItem().toString();
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
            System.out.println("Edicion Cancelada");
        }

        @Override
        public void addCellEditorListener(CellEditorListener l) {
        
        }

        @Override
        public void removeCellEditorListener(CellEditorListener l) {
            System.out.println("removeCellEditorListener");
        }

// </editor-fold>
    }

}
