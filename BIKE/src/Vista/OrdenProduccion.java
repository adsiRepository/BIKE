
//code

package Vista;

import static Vista.MenuPrincipal.escritorio;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.EventObject;
import java.awt.Component;


/**
 *
 * @author Miguel
 */
public class OrdenProduccion extends javax.swing.JInternalFrame {
    
    //atributos
    private DefaultTableModel dtm;
    private final String[] COLUMNAS_TABLA_DESPACHO;
    private final Class[] TIPOS_COLUMNAS_TABLA_DESPACHOS;
    private int fila_tabla, col_tabla;
    //JComboBox combo;
    
    //constructor de ésta ventana
    public OrdenProduccion() {
        initComponents();
        
//configuraciones de esta ventana
        this.title = "Despacho de Ordenes de Ensamble";
        this.closable = true;
        this.iconable = true;
        Dimension ScreenSpace = escritorio.getSize(), mySpc = this.getSize();
        this.setLocation( ( ScreenSpace.width / 18 ), ( ( ScreenSpace.height - mySpc.height ) / 2 ) );
        //this.setFrameIcon(new ImageIcon(getClass().getResource("/Recursos/Imagenes/iconUsers.png")));
        this.setToolTipText("Modulo de Control de Ordenes de Ensamble");
//--
        //inicializacion de variables
        COLUMNAS_TABLA_DESPACHO = new String[]{"Componentes", "Item Disponible", "Cantidad"};
        TIPOS_COLUMNAS_TABLA_DESPACHOS = new Class[]{
            String.class, JComboBox.class, Integer.class //esto quiere decir que las celdas de la tabla serán: String, un combo con las partes seleccionables y la cantidad de estas 
        };
        fila_tabla = 0;  col_tabla = 0;
        
        plasmarTablaDespacho();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Despachar_Orden = new javax.swing.JButton();
        btn_Cancelar_Despacho = new javax.swing.JButton();
        scroll_items_selec = new javax.swing.JScrollPane();
        tabla_despacho_ = new javax.swing.JTable();
        btn_prueba_ = new javax.swing.JButton();
        btn_retirar_accesorio = new javax.swing.JButton();
        panel_primer_filtro_emsamble = new javax.swing.JPanel();
        combo_tipo_ensamble = new javax.swing.JComboBox();
        lbl_tipo_ensamble_ = new javax.swing.JLabel();
        combo_ref_tamaño = new javax.swing.JComboBox();
        lbl_ref_tamaño_ = new javax.swing.JLabel();
        lbl_genero_ = new javax.swing.JLabel();
        slider_cant_genero = new javax.swing.JSlider();
        lbl_cantidad_ensamble_ = new javax.swing.JLabel();
        txt_cant_ensamble = new javax.swing.JTextField();
        txt_cant_f = new javax.swing.JTextField();
        lbl_cant_f_ = new javax.swing.JLabel();
        txt_cant_m = new javax.swing.JTextField();
        lbl_cant_m_ = new javax.swing.JLabel();
        scroll_txtArea_detalles_ = new javax.swing.JScrollPane();
        txtArea_detalles_ = new javax.swing.JTextArea();
        lbl_fecha_ = new javax.swing.JLabel();
        lbl_hora_ = new javax.swing.JLabel();
        combo_ensamblador_selec = new javax.swing.JComboBox();
        lbl_ensamblador_ = new javax.swing.JLabel();
        btn_retirar_accesorio1 = new javax.swing.JButton();
        btn_add_accesorio1 = new javax.swing.JButton();

        setClosable(true);
        setName("GClientes"); // NOI18N

        btn_Despachar_Orden.setText("Ingresar");
        btn_Despachar_Orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Despachar_OrdenActionPerformed(evt);
            }
        });

        btn_Cancelar_Despacho.setText("Modificar");
        btn_Cancelar_Despacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancelar_DespachoActionPerformed(evt);
            }
        });

        tabla_despacho_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tabla_despacho_.getTableHeader().setReorderingAllowed(false);
        tabla_despacho_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_despacho_MouseClicked(evt);
            }
        });
        scroll_items_selec.setViewportView(tabla_despacho_);

        btn_prueba_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prueba_ActionPerformed(evt);
            }
        });

        btn_retirar_accesorio.setToolTipText("Despachar Mercancía para Orden Seleccionada");
        btn_retirar_accesorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_retirar_accesorioActionPerformed(evt);
            }
        });

        panel_primer_filtro_emsamble.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        combo_tipo_ensamble.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rines o Aros", "Bicicleta Tradicional", "Tipo Cross", "Todo Terreno", "Turismo", "Playero", "Alta Gama", "Importado", "Otro" }));
        combo_tipo_ensamble.setToolTipText("Al Seleccionar se filtraran en la lista los Items correspondientes a esta clase de Articulo");
        combo_tipo_ensamble.setMaximumSize(new java.awt.Dimension(32767, 25));
        combo_tipo_ensamble.setMinimumSize(new java.awt.Dimension(74, 25));
        combo_tipo_ensamble.setPreferredSize(new java.awt.Dimension(74, 25));

        lbl_tipo_ensamble_.setText("Ensamble: ");

        combo_ref_tamaño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12", "16", "20", "24", "26", "27", "28", "29" }));
        combo_ref_tamaño.setToolTipText("Referencia de Tamaño");
        combo_ref_tamaño.setMaximumSize(new java.awt.Dimension(32767, 25));
        combo_ref_tamaño.setMinimumSize(new java.awt.Dimension(74, 25));
        combo_ref_tamaño.setPreferredSize(new java.awt.Dimension(74, 25));

        lbl_ref_tamaño_.setText("Tamaño:");

        lbl_genero_.setText("Género:");

        slider_cant_genero.setToolTipText("Desliza para Repartir Cantidad");

        lbl_cantidad_ensamble_.setText("Cantidad:");

        txt_cant_ensamble.setMaximumSize(new java.awt.Dimension(2147483647, 25));
        txt_cant_ensamble.setMinimumSize(new java.awt.Dimension(6, 25));
        txt_cant_ensamble.setPreferredSize(new java.awt.Dimension(6, 25));

        txt_cant_f.setMaximumSize(new java.awt.Dimension(2147483647, 25));
        txt_cant_f.setMinimumSize(new java.awt.Dimension(6, 25));
        txt_cant_f.setPreferredSize(new java.awt.Dimension(6, 25));

        lbl_cant_f_.setText("F:");

        txt_cant_m.setMaximumSize(new java.awt.Dimension(2147483647, 25));
        txt_cant_m.setMinimumSize(new java.awt.Dimension(6, 25));
        txt_cant_m.setPreferredSize(new java.awt.Dimension(6, 25));

        lbl_cant_m_.setText("M:");

        txtArea_detalles_.setColumns(1);
        txtArea_detalles_.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtArea_detalles_.setRows(3);
        txtArea_detalles_.setText("Detalles:\nSe Clasificará el tipo de Articulo\n(Bicicleta / Aro / Otro)\nen la lista de disponibilidad\npara la venta, según los Componentes\nelegidos en la siguiente lista.\nEs decir (ejemplo):\nUsted seleccionaría Bicicleta \ntipo Cross, al seleccionar el tipo\nde Marco determinaría\nla carasterística de la Bicicleta\ntipo Cross (marco GW, Nacional, etc) \ny asi se clasificarán en la lista\nde Articulos disponibles para\nla Venta.   \n\n ");
        scroll_txtArea_detalles_.setViewportView(txtArea_detalles_);

        javax.swing.GroupLayout panel_primer_filtro_emsambleLayout = new javax.swing.GroupLayout(panel_primer_filtro_emsamble);
        panel_primer_filtro_emsamble.setLayout(panel_primer_filtro_emsambleLayout);
        panel_primer_filtro_emsambleLayout.setHorizontalGroup(
            panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_primer_filtro_emsambleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_primer_filtro_emsambleLayout.createSequentialGroup()
                        .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_cantidad_ensamble_)
                            .addComponent(lbl_genero_))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cant_ensamble, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_primer_filtro_emsambleLayout.createSequentialGroup()
                                .addComponent(lbl_cant_m_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cant_m, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(slider_cant_genero, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_cant_f_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cant_f, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(panel_primer_filtro_emsambleLayout.createSequentialGroup()
                        .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scroll_txtArea_detalles_)
                            .addGroup(panel_primer_filtro_emsambleLayout.createSequentialGroup()
                                .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_tipo_ensamble_)
                                    .addComponent(lbl_ref_tamaño_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combo_ref_tamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo_tipo_ensamble, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        panel_primer_filtro_emsambleLayout.setVerticalGroup(
            panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_primer_filtro_emsambleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tipo_ensamble_)
                    .addComponent(combo_tipo_ensamble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ref_tamaño_)
                    .addComponent(combo_ref_tamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cant_ensamble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_cantidad_ensamble_))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slider_cant_genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_cant_f_)
                        .addComponent(txt_cant_f, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_primer_filtro_emsambleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_cant_m_)
                        .addComponent(txt_cant_m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_genero_)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll_txtArea_detalles_)
                .addContainerGap())
        );

        lbl_fecha_.setText("Fecha:  15 Noviembre 2015");

        lbl_hora_.setText("Hora:  03:30 p.m.");

        combo_ensamblador_selec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_ensamblador_.setText("Ensamblador:");

        btn_retirar_accesorio1.setToolTipText("Despachar Mercancía para Orden Seleccionada");
        btn_retirar_accesorio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_retirar_accesorio1ActionPerformed(evt);
            }
        });

        btn_add_accesorio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_accesorio1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Despachar_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Cancelar_Despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(578, 578, 578))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel_primer_filtro_emsamble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_ensamblador_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combo_ensamblador_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_retirar_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_add_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_prueba_, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_retirar_accesorio, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_fecha_, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_hora_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btn_retirar_accesorio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_prueba_, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_ensamblador_)
                                        .addComponent(combo_ensamblador_selec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn_retirar_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_add_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panel_primer_filtro_emsamble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Despachar_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Cancelar_Despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_fecha_)
                            .addComponent(lbl_hora_))
                        .addGap(38, 38, 38)
                        .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //metodos del programador para esta clase (OrdenProduccion) ->
    //metodo para llenar la tabla de despachos
    @SuppressWarnings("Convert2Lambda")
    private void plasmarTablaDespacho(){
        
        JComboBox combo_1 = new JComboBox(new Object[]{"Marco Trad '26 disco", "Marco sin disco", "Marco Suspension"});
        JComboBox combo_2 = new JComboBox(new Object[]{"GW Suspension", "Exceed Suspension", "Logan"});
        
        Object[][] datosFict = new Object[][]{
            {"Marco", combo_1, 5},
            {"Tenedor", combo_2, 3}
        };
        
        //FUENTES
        //insertar componentes en celdas de tabla ->
        //http://www.java2s.com/Tutorial/Java/0240__Swing/UsingaJComboBoxinaCellinaJTableComponent.htm
        //http://www.chuidiang.com/java/tablas/tablaeditor/tablaeditor.php
        dtm = new DefaultTableModel(datosFict, COLUMNAS_TABLA_DESPACHO){//instanciamos un nuevo DefaultTableModel y redifinimos sus metodos basicos
            @Override//este metodo lo utiliza la propia clase(DefaultTableModel) para definir el tipo de campo para sus columnas  
            public Class getColumnClass(int columnIndex) {
                return TIPOS_COLUMNAS_TABLA_DESPACHOS[columnIndex];
            }
            @Override// en este definimos las celdas editables y las no
            public boolean isCellEditable(int row, int column) {
                return !(this.getColumnClass(column).equals(String.class));
            }
        };
        tabla_despacho_.setModel(dtm);//aplicamos el modelo a la tabla
        //render = interprete => es el que dibujar el componente graficamente en la celda
        tabla_despacho_.setDefaultRenderer(JComboBox.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return (Component)value;
            }
        });
        //editor es el que hace que el combobox tenga la capacidad de determinar o editar el valor de la celda
        tabla_despacho_.setDefaultEditor(JComboBox.class, new TableCellEditor(){

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                return (Component)value;
            }

            @Override
            public Object getCellEditorValue() {
                //System.out.print(combo.getSelectedItem());
                return "CellEditorValue";//combo.getSelectedItem().toString();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                System.out.println("Edicion Cancelada");
            }

            @Override
            public void addCellEditorListener(CellEditorListener l) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //System.out.println("addCellEditorListener");
            }

            @Override
            public void removeCellEditorListener(CellEditorListener l) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                System.out.println("removeCellEditorListener");
            }
            
        });
    }
    //--

//--
    
    public void LimpiarPantalla(){
            combo_tipo_ensamble.setSelectedIndex(0);
        try{
            for( int i=0; i < tabla_despacho_.getRowCount(); i++ ){
//                dtm.removeRow(i);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error: "+e.getLocalizedMessage(), title, 0);
        }
    }
    
    
    private void btn_Despachar_OrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Despachar_OrdenActionPerformed
            
    }//GEN-LAST:event_btn_Despachar_OrdenActionPerformed

    private void btn_Cancelar_DespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancelar_DespachoActionPerformed
    
    }//GEN-LAST:event_btn_Cancelar_DespachoActionPerformed

    private void btn_retirar_accesorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_retirar_accesorioActionPerformed
        
        System.out.println("fila 1 -> "+((JComboBox)dtm.getValueAt(0, 1)).getSelectedItem());
        
    }//GEN-LAST:event_btn_retirar_accesorioActionPerformed

    private void btn_prueba_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prueba_ActionPerformed
        
        System.out.println("fila 2 -> "+((JComboBox)dtm.getValueAt(1, 1)).getSelectedItem());
        
    }//GEN-LAST:event_btn_prueba_ActionPerformed

    private void btn_retirar_accesorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_retirar_accesorio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_retirar_accesorio1ActionPerformed

    private void btn_add_accesorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_accesorio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_add_accesorio1ActionPerformed

    private void tabla_despacho_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_despacho_MouseClicked
        fila_tabla = tabla_despacho_.rowAtPoint(evt.getPoint());
        col_tabla = tabla_despacho_.columnAtPoint(evt.getPoint());
        /*if( fila_tabla > -1 && col_tabla > -1 ){
            this.obs = dtm.getValueAt(fila, 0);
            txtcodpro.setText(""+obs);
        }*/
    }//GEN-LAST:event_tabla_despacho_MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancelar_Despacho;
    private javax.swing.JButton btn_Despachar_Orden;
    private javax.swing.JButton btn_add_accesorio1;
    private javax.swing.JButton btn_prueba_;
    private javax.swing.JButton btn_retirar_accesorio;
    private javax.swing.JButton btn_retirar_accesorio1;
    private javax.swing.JComboBox combo_ensamblador_selec;
    private javax.swing.JComboBox combo_ref_tamaño;
    private javax.swing.JComboBox combo_tipo_ensamble;
    private javax.swing.JLabel lbl_cant_f_;
    private javax.swing.JLabel lbl_cant_m_;
    private javax.swing.JLabel lbl_cantidad_ensamble_;
    private javax.swing.JLabel lbl_ensamblador_;
    private javax.swing.JLabel lbl_fecha_;
    private javax.swing.JLabel lbl_genero_;
    private javax.swing.JLabel lbl_hora_;
    private javax.swing.JLabel lbl_ref_tamaño_;
    private javax.swing.JLabel lbl_tipo_ensamble_;
    private javax.swing.JPanel panel_primer_filtro_emsamble;
    private javax.swing.JScrollPane scroll_items_selec;
    private javax.swing.JScrollPane scroll_txtArea_detalles_;
    private javax.swing.JSlider slider_cant_genero;
    private javax.swing.JTable tabla_despacho_;
    private javax.swing.JTextArea txtArea_detalles_;
    private javax.swing.JTextField txt_cant_ensamble;
    private javax.swing.JTextField txt_cant_f;
    private javax.swing.JTextField txt_cant_m;
    // End of variables declaration//GEN-END:variables
}
