
package controller.componentes;

// <editor-fold defaultstate="collapsed" desc="imports">

import controller.ConsultaSQL;
import controller.Tiempo;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import model.MainClass;
import model.componentes.ItemDeLista;
import static view.MenuPrincipal.escritorio;
import view.OrdenProduccion;

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="TUTORIALES Y FUENTES">
//tuto perfecto para aprender a insertar botones en una celda de cualquier tabla -> https://www.youtube.com/watch?v=bVknuhawXsI
// </editor-fold>

/**
 * TABLA IMPLEMENTADA Y ADECUADA PARA LA VISUALIZACION Y CONTROL DE LAS ORDENES
 * DE PRODUCCION. Constructor de tabla simple. Para formulario OrdenProduccion.
 * @author Miguel
 */
public class TablaProduccion extends JTable {

    // <editor-fold defaultstate="collapsed" desc="DECLARACION DE VARIABLES">
    private Class[] CLASES_COLUMNAS;
    private String[] TITULOS_COLUMNAS;
    private boolean[] COLS_EDITABLES;

    private Object[][] data;
    private final ModeloTablaProduccion mi_modelo_tabla;

    /**
     * ESTAS SON CONSTANTES QUE AYUDAN A CONTROLAR EL TIPO DE TABLA A CREAR
     */
    public static final String TABLA_VISUALIZACION = "tabla_de_visualizacion";
    public static final String TABLA_CONTROL = "tabla_de_control";
    private final String mi_modelo;
    // </editor-fold>

    /*public TablaProduccion() {
     super();
    }*/
    
    /**TABLA IMPLEMENTADA Y ADECUADA PARA LA VISUALIZACION Y CONTROL DE LAS ORDENES DE PRODUCCION
     * @param modelo String que indica que modelo ya establecido sera aplicado*/
    public TablaProduccion(String modelo) {
        super();
        mi_modelo = modelo;
        if (mi_modelo.equals(TABLA_CONTROL)) {
            CLASES_COLUMNAS = new Class[]{
                Integer.class, String.class, String.class, String.class,
                String.class, String.class, Integer.class, PanelBotonesCeldaProduccion.class
            };
            TITULOS_COLUMNAS = new String[]{
                "No. Orden", "Ensamblador", "Hora Despacho", "Hora Entrega",
                "Produccion", "Tamaño", "Cantidad", "Opciones"
            };
            COLS_EDITABLES = new boolean[]{
                false, false, false, false, false, false, false, true
            };
        }
    
        
        if (mi_modelo.equals(TABLA_VISUALIZACION)) {
            CLASES_COLUMNAS = new Class[]{
                Boolean.class, Integer.class, String.class,      String.class,             String.class,   Integer.class,  String.class
            };  
            TITULOS_COLUMNAS = new String[]{
                "Seleccionado", "No. Orden", "Ensamblador", "Articulo Producido",   "Talla",   "Cantidad",    "Hora Despacho"
            };
            COLS_EDITABLES = new boolean[]{
                true, false, false, false, false, false, false
            };
            /*anchos = new int[]{
                ((anchoContenedor * 16) / 100), ((anchoContenedor * 9) / 100), 
                ((anchoContenedor * 30) / 100), ((anchoContenedor * 15) / 100), 
                ((anchoContenedor * 15) / 100)};
            alturaCol = 20;*/
        }
        
        mi_modelo_tabla = new ModeloTablaProduccion();
        this.setModel(mi_modelo_tabla);
        this.setDefaultRenderer(Component.class, new ComponentCellRenderer());
        this.setDefaultEditor(Component.class, new ComponentCellEditor());

    }
    //FIN CONSTRUCTOR
    
    /**METODO PARA FORMATEAR LOS TAMAÑOS DE LAS COLUMNAS DESDE LA CLASE QUE INSTANCIE ESTA TABLA.
     * @param anchosCols
     * @param altoFila*/
    public void formatearTabla(int[] anchosCols, int altoFila){
        //int anchoContenedor = contenedor.getWidth();
        int[] anchos = anchosCols;
        //int alturaCol = 10;
        for (int i = 0; i < this.getColumnCount(); i++) {
            //break;
            this.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            this.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
            this.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
        }
        this.setRowHeight(altoFila);
    }

    /**
     * Método publico utilizado para cambiar la informacion de la tabla
     *
     * @throws java.lang.Exception
     */
    public void actualizarTabla(/*ArrayList<Object[]> new_data*/) throws Exception {
        // <editor-fold defaultstate="collapsed" desc="CODIGO DEL METODO">
        try {
            data = new Object[][]{};
            mi_modelo_tabla.fireTableDataChanged();
            if (mi_modelo.equals(TABLA_CONTROL)) {
                ArrayList<Object[]> new_data = ConsultaSQL.revisarProduccionActual();
                Iterator it = new_data.iterator();
                if (it.hasNext()) {
                    int i = 0;
                    Object[] registro;
                    data = new Object[new_data.size()][8];
                    String fecha_despacho, hora_despacho;
                    String fecha_entrega, hora_entrega;
                    while (it.hasNext()) {
                        registro = (Object[]) it.next();//el parentesis con la clase indica parseo o casteo de variables
                        fecha_despacho = Tiempo.calendarFechaToString((java.sql.Timestamp)registro[2]);
                        hora_despacho = Tiempo.calendarHoraToString((java.sql.Timestamp)registro[2]);
                        fecha_entrega = Tiempo.calendarFechaToString((java.sql.Timestamp)registro[3]);
                        hora_entrega = Tiempo.calendarHoraToString((java.sql.Timestamp)registro[3]);
                        data[i] = new Object[]{
                            /*no_orden*/registro[0], 
                            /*nombre completo*/registro[1], 
                            /*momento despacho*/fecha_despacho + "   " + hora_despacho/*registro[2]*/, 
                            /*momento entrega*/fecha_entrega + "   " + hora_entrega/*registro[3]*/,
                            /*articulo producido*/registro[4], 
                            /*tamaño*/registro[5], 
                            /*cantidad*/registro[6], 
                            /*opciones*/new PanelBotonesCeldaProduccion()
                        };
                        i++;
                    }
                    mi_modelo_tabla.fireTableDataChanged();
                }
            }
            
            if (mi_modelo.equals(TABLA_VISUALIZACION)) {
                ArrayList<Object[]> new_data = ConsultaSQL.revisarProduccionActual();
                Iterator it = new_data.iterator();
                if (it.hasNext()) {
                    int i = 0;
                    Object[] registro;
                    data = new Object[new_data.size()][7];
                    String fecha_despacho, hora_despacho;
                    while (it.hasNext()) {
                        registro = (Object[]) it.next();//el parentesis con la clase indica parseo o casteo de variables
                        fecha_despacho = Tiempo.calendarFechaToString((java.sql.Timestamp) registro[2]);
                        hora_despacho = Tiempo.calendarHoraToString((java.sql.Timestamp) registro[2]);
                        data[i] = new Object[]{
                            /*check seleccion*/false,
                            /*no_orden*/registro[0],
                            /*nombre completo*/ registro[1],
                            /*articulo producido*/ registro[4],
                            /*tamaño*/ registro[5],
                            /*cantidad*/ registro[6],
                            /*momento despacho*/ fecha_despacho + "   " + hora_despacho/*registro[2]*/
                        };
                        i++;
                    }
                    mi_modelo_tabla.fireTableDataChanged();
                }
            }

        } catch (Exception e) {
            throw new Exception("Error al Actualizar la Tabla de Alistamiento.\nExcepción: " + e.getMessage());
        }
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
            if(COLS_EDITABLES.length > 0){
                return COLS_EDITABLES[col];
            }
            return false;
        }

        @Override
        public int getColumnCount() {
            if(TITULOS_COLUMNAS.length > 0){
                return TITULOS_COLUMNAS.length;
            }
            return 0;
        }

        @Override
        public Object getValueAt(int row, int col) {
            if (data != null) {
                if (col == 7) {
                    ((PanelBotonesCeldaProduccion) data[row][7]).miFila = row;
                }
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
    
    /***/
    private class PanelBotonesCeldaProduccion extends JPanel {
        // <editor-fold defaultstate="collapsed" desc="JPANEL PARA CELDA DE TABLA">
        
        // Variables declaration                    
        private final BotonCeldaProduccion btncell_cancel_orden_;
        private final BotonCeldaProduccion btncell_edit_orden_;
        private final BotonCeldaProduccion btncell_ok_orden_;
        public int miFila;//, miCol;
        // End of variables declaration 
        
        /**
         * Creates new form PanelBotonesCelda
         */
        public PanelBotonesCeldaProduccion() {
            btncell_ok_orden_ = new BotonCeldaProduccion(1);
            btncell_edit_orden_ = new BotonCeldaProduccion(2);
            btncell_cancel_orden_ = new BotonCeldaProduccion(3);
            diseñar();
        }

        /**
         * este metodo es llamado al inicializar el panel
         * este configura las cosas basicas
         */
        @SuppressWarnings("unchecked")
        private void diseñar() {
            // <editor-fold defaultstate="collapsed" desc="Codigo Generado Automaticamente por el Disenador Netbeans">                          
            try {
                setLayout(new java.awt.GridLayout());
                
                btncell_ok_orden_.setIcon(new ImageIcon(TablaProduccion.class.getResource("/sources/mis_imagenes/imgbtn_ok.png")));
                btncell_ok_orden_.setPreferredSize(new java.awt.Dimension(35, 35));
                add(btncell_ok_orden_);
                
                btncell_edit_orden_.setIcon(new ImageIcon(TablaProduccion.class.getResource("/sources/mis_imagenes/imgbtn_edit.png"))); 
                btncell_edit_orden_.setPreferredSize(new java.awt.Dimension(35, 35));
                add(btncell_edit_orden_);
                
                btncell_cancel_orden_.setIcon(new ImageIcon(TablaProduccion.class.getResource("/sources/mis_imagenes/imgbtn_cancel.png")));
                btncell_cancel_orden_.setPreferredSize(new java.awt.Dimension(35, 35));
                add(btncell_cancel_orden_);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }// </editor-fold>
        }                       

        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="CLASES INTERNAS DEL PANEL">
        private class BotonCeldaProduccion extends JButton /*implements ActionListener*/ {
            // <editor-fold defaultstate="collapsed" desc="CLASE EXTENDIDA DE JBUTTON CONFIGURADA PARA INSERTAR BOTONES EN CELDAS DE UNA TABLA">
            //fuentes
            //http://www.edu4java.com/es/swing/swing4.html
            //http://stackoverflow.com/questions/1475543/how-to-add-button-in-a-row-of-jtable-in-swing-java

            private final int miAccion;//1, 2 o 3 segun sea entregar orden(1), modificarla(2) o cancelar(3) 

            //<editor-fold defaultstate="collapsed" desc="METODOS DE CONSTRUCCION Y CONFIGURACION DE ESTA CLASE DE BOTON">
            /***/
            public BotonCeldaProduccion(int accion) {
                super();
                this.miAccion = accion;
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accionarBoton();
                    }
                });
            }
            
            /**
             * 
             */
            private void accionarBoton() {

                switch (miAccion) {

                    case 1:
                        try {
                            boolean hecho = ConsultaSQL.registrarEntregaProduccion((int) data[miFila][0]);
                            if (hecho) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Se ha registrado correctamente la entrega de "
                                        + "la orden de Producción no. " + (int) data[miFila][0],
                                        "Entrega de Producción",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                TablaProduccion.this.actualizarTabla();
                            } else {
                                JOptionPane.showMessageDialog(TablaProduccion.this, "No se pudo Borrar la Orden");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
                        }
                        //
                        break;

                    case 2:
                        try {
                            if (MainClass.menu.ordenProduccion != null && MainClass.menu.ordenProduccion.isVisible()) {
                                if (MainClass.menu.ordenProduccion.isIcon()) {
                                    MainClass.menu.ordenProduccion.setIcon(false);
                                }
                            } else {
                                MainClass.menu.ordenProduccion = new OrdenProduccion();
                                escritorio.add(MainClass.menu.ordenProduccion);
                                MainClass.menu.ordenProduccion.setVisible(true);
                            }
                            MainClass.menu.ordenProduccion.editarOrdenProduccion((int) data[miFila][0]);
                            MainClass.menu.ordenProduccion.toFront();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al abrir la ventana de Alistamiento.\n"
                                    + ex.getMessage(),
                                    "Editar Orden", 0);
                        }
                        break;

                    case 3:
                        try {
                            int decision = JOptionPane.showOptionDialog(
                                    null,
                                    "¿Estás seguro de cancelar la Orden de Producción no. " + (int) data[miFila][0] + "?",
                                    "Cancelar Orden", // título del JOptionPane
                                    JOptionPane.OK_CANCEL_OPTION, // tipo input
                                    JOptionPane.QUESTION_MESSAGE, // tipo mensaje (determina el icono por defecto)
                                    null, // icono, si es nulo aparecerá el por defecto
                                    new Object[]{"Si", "No"}, //opciones => estos serán los botones
                                    new Object[]{}//dialogo o texto mostrado
                            );

                            if (decision == 0) {
                                boolean hecho = ConsultaSQL.borrarOrdenProduccion((int) data[miFila][0]);
                                if (hecho) {
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Se ha cancelado la Orden de Producción no. " + (int) data[miFila][0],
                                            "Cancelar Orden",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    TablaProduccion.this.actualizarTabla();
                                    MainClass.menu.ordenProduccion.actualizarDesdeFuera();
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se pudo Borrar la Orden");
                                }
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
                        }
                        //JOptionPane.showMessageDialog(null, "Entregar la fila " + (miFila + 1));
                        break;
                    default:
                        break;
                }
            }

            /*@Override
            public Dimension getPreferredSize() {
                return new Dimension(15, 10);
            }*/
            //</editor-fold>

            //Action Listener => metodo que se desata al accionar el boton u oprimirlo
            /*@Override
            public void actionPerformed(ActionEvent ae) {
                switch (miAccion) {
                    case 1:
                        
                        //JOptionPane.showMessageDialog(null, "Haz Cancelado la orden de la fila " + (miFila + 1));
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Realmente Quieres Modificar la fila " + (miFila + 1));
                        break;
                    case 3:
                        
                        //JOptionPane.showMessageDialog(null, "Entregar la fila " + (miFila + 1));
                        break;
                    default:
                        break;
                }
            }*/
            // </editor-fold>
        }
        // </editor-fold>
    }
    
    /***/
    private static class ComponentCellRenderer implements TableCellRenderer {
        // <editor-fold defaultstate="collapsed" desc="CLASE QUE DIBUJA EL COMPONENTE DENTRO DE LA CELDA DE LA TABLA">

        JTextField label;
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof String || value instanceof Integer) {
                label = new JTextField();
                label.setHorizontalAlignment(JTextField.CENTER);
                label.setText(value.toString());
                return label;
            }
            return (Component) value;
        }

        // </editor-fold>
    }
    
    /***/
    private static class ComponentCellEditor extends AbstractCellEditor implements TableCellEditor/*, ActionListener*/ {
        // <editor-fold defaultstate="collapsed" desc="CLASE QUE LE DA LA CAPACIDAD AL COMPONENTE DE EDITAR EL VALOR DE LA CELDA">

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
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
