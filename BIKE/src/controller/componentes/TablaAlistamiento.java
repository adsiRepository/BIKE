
package controller.componentes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.swing.AbstractCellEditor;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import model.componentes.ItemDeLista;

/**
 *
 * @author Miguel
 */
public class TablaAlistamiento extends JTable{
    
    private ModeloTablaAlistamiento modelo_tabla;
    private Object[][] data;
    //private int fil, col;
    private int cant_item;
    
    /***/
    public TablaAlistamiento(){
        super();
        modelo_tabla = new ModeloTablaAlistamiento();
        this.setModel(modelo_tabla);
        this.setDefaultRenderer(Component.class, new RenderComponenteCelda());
        this.setDefaultEditor(Component.class, new EditorComponenteCelda());
        //this.addMouseListener(new EventosMouse());
        formatearCeldas();
    }

    // <editor-fold defaultstate="collapsed" desc="FORMATEO DE TAMAÑOS DE COLUMNAS">
    private void formatearCeldas() {
        //int anchoContenedor = scroll_items_selec.getWidth();
        int[] anchos = new int[]{150, 250, 38, 42, 80};
        /*int[] anchos = new int[]{ ((int)((anchoContenedor*30)/100)), ((int)((anchoContenedor*50)/100)), ((int)((anchoContenedor*20)/100)) };*/
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            this.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
            this.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
        }
        this.setRowHeight(20);
    }
    // </editor-fold>
    
    public void actualizaTabla(LinkedHashMap<String, Object[]>/*ArrayList<ItemDeLista>*/ new_data, int cantidadSeleccionada) throws Exception{
        try{
            data = new Object[new_data.size()][5];
            SpinnerNumberModel model_spinner;
            int fila = 0;
            for (HashMap.Entry reg : new_data.entrySet()) {
                    boolean comp_par = (boolean)(((Object[])reg.getValue())[1]);
                    if(comp_par == true){
                        model_spinner = new SpinnerNumberModel(0, 0, cantidadSeleccionada * 2, 1);
                    }
                    else{
                        model_spinner = new SpinnerNumberModel(0, 0, cantidadSeleccionada, 1);
                    }
                    data[fila] = new Object[]{reg.getKey().toString(), new My_ComboBox(((Object[])reg.getValue())[0], fila), cant_item, new SpinnerCeldaTabla(model_spinner), new PanelBotonesCelda()};
                fila++;
            }
            // <editor-fold defaultstate="collapsed" desc="metodo largo para llenar a data">
                /*int n_filas  = data_aux.length;
             int n_colms = data_aux[0].length;
             data = new Object[n_filas][n_colms];
             for(fila = 0; fila < n_filas; fila++){
             for(int colm = 0; colm < n_colms; colm++){
             /*if(colm == 2){
             data[fila][2] = ((ItemDeLista)((ComboBoxItem)data_aux[fila][1]).getSelectedItem()).getAtributos().get("stock");
             }
             else{/
             data[fila][colm] = data_aux[fila][colm];
             //}
             }
             }*/
            // </editor-fold>
            modelo_tabla.fireTableDataChanged();
        }
        catch(Exception e){
            throw new Exception("Error al Actualizar la Tabla de Alistamiento.\nExcepción: "+e.getMessage());
        }
    }

    public void vaciarTabla() {
        data = new Object[][]{};
        modelo_tabla.fireTableDataChanged();
    }
    
    private class ModeloTablaAlistamiento extends DefaultTableModel {
        // <editor-fold defaultstate="collapsed" desc="MODELO DE LA TABLA DE ALISTAMIENTO">

        private final Class[] CLASES_COLUMNAS = new Class[]{String.class, My_ComboBox.class, Integer.class, SpinnerCeldaTabla.class, PanelBotonesCelda.class};
        private final String[] TITULOS_COLUMNAS = new String[]{"Componentes", "Seleccion", "Stock", "Out", "Opciones"};
        private final boolean[] COLS_EDITABLES = new boolean[]{false, true, false, true, true};
        
        /**
         * Constructor del Modelo de la TablaAlistamiento de Alistamiento.
         */
        public ModeloTablaAlistamiento() {
            super();
            data = new Object[][]{};
            this.setColumnIdentifiers(TITULOS_COLUMNAS);
        }

        @Override//determina la clase de componentes que iran reg cada celda
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
                if (col == 4) {
                    ((PanelBotonesCelda) data[row][4]).miFila = row;
                    ((PanelBotonesCelda) data[row][4]).miCol = col;
                }
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
            /*if(col == 1){
                data[row][2] = ((ItemDeLista) ((My_ComboBox) data[row][1]).getSelectedItem()).getAtributos().get("stock");
            }*/
            fireTableCellUpdated(row, col);//notifica a todos los listeners que el valor de la celda ha sido editado
        }

        // </editor-fold>
    }
    
    private class PanelBotonesCelda extends JPanel {
        // <editor-fold defaultstate="collapsed" desc="JPANEL PARA CELDA DE TABLA">

        private final BotonCelda btncell_new_rep;
        private final BotonCelda btncell_back_new_rep;
        public int miFila, miCol;

        /**
         * Creates new form PanelBotonesCelda.
         */
        public PanelBotonesCelda() {
            btncell_new_rep = new BotonCelda(1);
            btncell_back_new_rep = new BotonCelda(2);
            inicializacion();
        }

        /**
         * Este metodo es llamado al inicializar el panel, este configura las
         * cosas basicas como tamaño y diseño.
         */
        @SuppressWarnings("unchecked")
        private void inicializacion() {
            // <editor-fold defaultstate="collapsed" desc="Codigo Generado Automaticamente por el Disenador Netbeans">                          
            try {
                setLayout(new java.awt.GridLayout());

                btncell_new_rep.setText("Otro");
                btncell_new_rep.setPreferredSize(new java.awt.Dimension(20, 15));
                add(btncell_new_rep);

                btncell_back_new_rep.setText("Atras");
                btncell_back_new_rep.setPreferredSize(new java.awt.Dimension(20, 15));
                add(btncell_back_new_rep);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }// </editor-fold>
        }

        // </editor-fold>
        private class BotonCelda extends JButton {
            // <editor-fold defaultstate="collapsed" desc="CLASE EXTENDIDA DE JBUTTON CONFIGURADA PARA INSERTAR BOTONES EN CELDAS DE UNA TABLA">

            private final int miAccion;

            public BotonCelda(int ac) {
                super();
                miAccion = ac;
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (((My_ComboBox) data[miFila][1]).getSelectedItem() != null) {
                            switch (miAccion) {
                                case 1:
                                    JOptionPane.showMessageDialog(null, ((ItemDeLista) ((My_ComboBox) data[miFila][1]).getSelectedItem()).getAtributos().get("stock"));
                                    break;
                                case 2:
                                    JOptionPane.showMessageDialog(null, "Realmente Quieres Modificar la fila " + (miFila + 1));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                });
            }

            @Override
            public void paint(Graphics grf) {
                super.paint(grf);
            }
            // </editor-fold>
        }
    }
    
    private static class SpinnerCeldaTabla extends JSpinner {
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
         * @param spinnerModel*/
        public SpinnerCeldaTabla(SpinnerModel spinnerModel) {
            super();
            if (spinnerModel == null) {
                //throw new NullPointerException("model cannot be null");
                this.myspinnerNumModel = new SpinnerNumberModel(1, 0, 10, 1);// Dato visualizado al inicio reg el spinner, minimo, maximo, paso
            } else {
                this.myspinnerNumModel = spinnerModel;
            }
            constructorGeneral();
        }
        
        public SpinnerCeldaTabla(int porDefecto, int min, int max, int paso) {
            super();
            this.myspinnerNumModel = new SpinnerNumberModel(porDefecto, min, max, paso);
            constructorGeneral();
        }
        
        /**
         * Esta clase redefine un spinner haciendo que no se
         * le puedan ingresar letras por ejemplo, o cuaquier configuracion que
         * el programador quiera dar.*/
        private void constructorGeneral(){
            this.setModel(myspinnerNumModel);
            JSpinner.DefaultEditor editor;
            editor = ((JSpinner.DefaultEditor)this.getEditor());
            editor.getTextField().addKeyListener(new VigiaTecleo());//aqui aplico la clase que implementa los listener del teclado para impedir la insercion de letras
        }

        private static class VigiaTecleo implements KeyListener {
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
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="MODELO DEL SPINNER (no implementado aun)">
        /*private static class MyModel extends AbstractSpinnerModel{
            
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

        }*/
        // </editor-fold>
    }

    /**COMBOBOX*/
    private class My_ComboBox extends JComboBox<ItemDeLista> {

        private final int mi_fila;
        private final ItemDeLista[] my_items;
        private ItemDeLista item_actual;
        
        public boolean i_have_items;

        /**
         * Constructor
         * @param objItems Cuando sus my_items vienen en un ArrayList*/
        public My_ComboBox(Object objItems, int miFila) throws Exception{
            super();
            try {
                if (((ArrayList) objItems).size() > 0) {
                    Iterator it = ((ArrayList) objItems).iterator();
                    my_items = new ItemDeLista[((ArrayList) objItems).size()];
                    int i = 0;
                    while (it.hasNext()) {
                        //this.addItem((ItemDeLista) it.next());
                        my_items[i] = (ItemDeLista) it.next();
                        i++;
                    }
                    i_have_items = true;
                } else {
                    //this.addItem(new ItemDeLista());
                    my_items = new ItemDeLista[1];
                    my_items[0] = new ItemDeLista();
                    //this.setKeySelectionManager(keySelectionManager);
                    i_have_items = false;
                }
                
                this.mi_fila = miFila;
                this.setModel(new ModeloComboBox());
                this.setRenderer(new RenderItemComboBox());
                this.setSelectedItem(this.getItemAt(0));
                
            } catch (Exception e) {
                throw new Exception("Error al Construir My_ComboBox\n" + e.toString());
            }
        }

        private class ModeloComboBox extends AbstractListModel<ItemDeLista> implements ComboBoxModel<ItemDeLista> {

            // <editor-fold defaultstate="collapsed" desc="MODELO COMBOBOX, NO IMPLEMENTADO">
            
            //private int pos_actual;

            public ModeloComboBox(/*ItemDeLista[] items*/) {
                //my_items = items;
            }

            @Override
            public int getSize() {
                return my_items.length;
            }

            @Override
            public ItemDeLista getElementAt(int index) {
                //pos_actual = index;
                return my_items[index];
            }

            @Override
            public void setSelectedItem(Object anItem) {
                //System.out.println("Se ha Cambiado el valor de un combobox");
                item_actual = (ItemDeLista) anItem;
                cant_item = (int) item_actual.getAtributos().get("stock");
                modelo_tabla.setValueAt(cant_item, mi_fila, 2);
            }

            @Override
            public Object getSelectedItem() {
                return item_actual;
            }
            // </editor-fold>
        }
    
        private class RenderItemComboBox extends JTextField implements ListCellRenderer<ItemDeLista> {
            // <editor-fold defaultstate="collapsed" desc="RENDERIZADOR (O DIBUJANTE) DE LOS ITEMS Y SUS CARACTERISTICAS VISUALES DENTRO DEL COMBOBOX">

            public RenderItemComboBox() {
                this.setBorder(null);
            }

            @Override
            public Component getListCellRendererComponent(JList<? extends ItemDeLista> list, ItemDeLista value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value != null) {
                    //HashMap<String, String> attrs = ((ItemDeLista) value).getAtributos();
                    HashMap<String, Object> attrs = ((ItemDeLista) value).getAtributos();
                    setText(String.valueOf(attrs.get(ItemDeLista.TEXTO_MOSTRADO)));
                }
                return this;
            }
            // </editor-fold>
        }

    }


    // <editor-fold defaultstate="collapsed" desc="CLASES PARA LA GRAFICACION DE COMPONENTES (combobox, boton..) DENTRO DE TABLA Y SU CAPACIDAD DE DETERMINAR EL VALOR DE LA CELDA (edicion)">
        
    private static class RenderComponenteCelda implements TableCellRenderer{
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

    
    /*private class EventosMouse extends MouseAdapter implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            fil = TablaAlistamiento.this.rowAtPoint(e.getPoint());
            col = TablaAlistamiento.this.columnAtPoint(e.getPoint());
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    } */
    
    
    //FUENTES
    //insertar componentes reg celdas de TablaAlistamiento ->
    //http://www.java2s.com/Tutorial/Java/0240__Swing/UsingaJComboBoxinaCellinaJTableComponent.htm
    //http://www.chuidiang.com/java/tablas/tablaeditor/tablaeditor.php
    //<editor-fold defaultstate="collapsed" desc="COMPONENTES(personalizados) EDITORES DE CELDAS DE TABLA">
    //FUENTES
    //http://swing-facil.blogspot.com.co/2012/01/jbutton-jcheckbox-jcombobox-reg-jtable.html
}
