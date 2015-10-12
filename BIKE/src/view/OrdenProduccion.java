
//code

package view;

import controller.ConsultaSQL;
import controller.Graficos.PanelFondoVentanaInterna;
import model.componentes.ItemDeLista;
import controller.componentes.ComboBoxItem;
import controller.componentes.Tabla.EditorComponenteCelda;
import controller.componentes.Tabla.RenderComponenteCelda;
import controller.componentes.Tabla.SpinnerCeldaTabla;
import static view.MenuPrincipal.escritorio;

import java.awt.Dimension;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;


/**
 *
 */
public class OrdenProduccion extends /*VentanaInterna*/javax.swing.JInternalFrame {
    
    private static final Class[] TIPOS_COLUMNAS_TABLA_DESPACHOS = new Class[]{String.class, ComboBoxItem.class, SpinnerCeldaTabla.class};
    private static final String[] COLUMNAS_TABLA_DESPACHO = new String[]{"Componentes", "Seleccion", "Cantidad"};

    public static final String COD_CMBOX_ENSAMBLADORES = "ensambladores";
    public static final String COD_CMBOX_ARTICULOS = "articulos";
    
    //declaracion variables =>
    @SuppressWarnings("FieldMayBeFinal")
    private ModeloTabla modeloTabla;
    private Object[][] data; // estos datos son la variable global para el manejo de datos de la Tabla, esta instanciada para todo el formulario no solo para el modelo, asi se puede manipular constantemente
    private int fila_tabla, col_tabla;   
    private ModeloComboBoxTallas modelo_combo_tallas;
    
    //constructor de Esta ventana
    public OrdenProduccion() {
        initComponents();
        // <editor-fold defaultstate="collapsed" desc="CONFIGURACION DE LA VENTANA COMO TAMANO, TITULO..">
        this.title = "Despacho de Ordenes de Ensamble";
        this.closable = true;
        this.iconable = true;
        Dimension ScreenSpace = escritorio.getSize(), mySpc = this.getSize();
        this.setLocation((ScreenSpace.width / 18), ((ScreenSpace.height - mySpc.height) / 2));
        //this.setFrameIcon(new ImageIcon(getClass().getResource("/Recursos/Imagenes/iconUsers.png")));
        this.setToolTipText("Modulo de Control de Ordenes de Ensamble");
    // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="CONFIGURACION DE LA TABLA DE SELECCION DE PARTES">
        data = new Object[][]{};
        modeloTabla = new ModeloTabla();//
        tabla_despacho_.setModel(modeloTabla);
        tabla_despacho_.setDefaultRenderer(Component.class, new RenderComponenteCelda());
        tabla_despacho_.setDefaultEditor(Component.class, new EditorComponenteCelda());
        formatearTablaDespacho();
    // </editor-fold>

        fila_tabla = 0;  col_tabla = 0;
 
        //configCombobox();
        
    }
    
    private void formatearTablaDespacho(){
        //int anchoContenedor = scroll_items_selec.getWidth();
        int[] anchos = new int[]{130, 250, 63};
        /*int[] anchos = new int[]{ ((int)((anchoContenedor*30)/100)), ((int)((anchoContenedor*50)/100)), ((int)((anchoContenedor*20)/100)) };*/
        for(int i = 0; i < tabla_despacho_.getColumnCount(); i++){
            tabla_despacho_.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            tabla_despacho_.getColumnModel().getColumn(i).setMinWidth(anchos[i]);
            tabla_despacho_.getColumnModel().getColumn(i).setMaxWidth(anchos[i]);
        }
        tabla_despacho_.setRowHeight(20);
    }

    /**
     * El siguiente metodo es llamado junto con el constructor para inicializar el formulario.
     * ADVERTENCIA: No modifique este codigo. El contenido de este metodo siempre
     * es generado automaticamente por el editor grafico.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_principal_ = new PanelFondoVentanaInterna();
        lbl_ensamblador_ = new javax.swing.JLabel();
        btn_retirar_accesorio1 = new javax.swing.JButton();
        btn_add_accesorio1 = new javax.swing.JButton();
        combo_ensambladores_ = new controller.componentes.ComboBoxItem(COD_CMBOX_ENSAMBLADORES);
        panel_primer_filtro_emsamble_ = new javax.swing.JPanel();
        combo_catalogo_ensamble_ = new controller.componentes.ComboBoxItem(COD_CMBOX_ARTICULOS);
        lbl_tipo_ensamble_ = new javax.swing.JLabel();
        combo_ref_tamaño_ = new javax.swing.JComboBox();
        lbl_ref_tamaño_ = new javax.swing.JLabel();
        lbl_cantidad_ensamble_ = new javax.swing.JLabel();
        txt_cant_ensamble = new javax.swing.JTextField();
        scroll_txtArea_detalles_ = new javax.swing.JScrollPane();
        txtArea_detalles_ = new javax.swing.JTextArea();
        btn_alistar_despacho_ = new javax.swing.JButton();
        btn_cancelar_despacho_ = new javax.swing.JButton();
        scroll_items_selec = new javax.swing.JScrollPane();
        tabla_despacho_ = new javax.swing.JTable();
        btn_selec_repuestos_1 = new javax.swing.JButton();
        btn_selec_repuestos_2 = new javax.swing.JButton();
        scroll_tabla_ordenes_ = new javax.swing.JScrollPane();
        tabla_ordenes_ = new javax.swing.JTable();
        btn_Despachar_Orden = new javax.swing.JButton();
        btn_Cancelar_Despacho = new javax.swing.JButton();

        setClosable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(915, 424));
        setMinimumSize(new java.awt.Dimension(915, 424));
        setName(""); // NOI18N

        panel_principal_.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_principal_.setMaximumSize(new java.awt.Dimension(915, 424));
        panel_principal_.setMinimumSize(new java.awt.Dimension(915, 424));
        panel_principal_.setPreferredSize(new java.awt.Dimension(935, 400));

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

        panel_primer_filtro_emsamble_.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        combo_catalogo_ensamble_.setToolTipText("Al Seleccionar se filtraran en la lista los Items correspondientes a esta clase de Articulo");
        combo_catalogo_ensamble_.setMaximumSize(new java.awt.Dimension(32767, 25));
        combo_catalogo_ensamble_.setMinimumSize(new java.awt.Dimension(74, 25));
        combo_catalogo_ensamble_.setPreferredSize(new java.awt.Dimension(74, 25));
        combo_catalogo_ensamble_.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_catalogo_ensamble_ItemStateChanged(evt);
            }
        });

        lbl_tipo_ensamble_.setText("Ensamble: ");

        modelo_combo_tallas = new ModeloComboBoxTallas();
        combo_ref_tamaño_.setModel(modelo_combo_tallas);
        combo_ref_tamaño_.setToolTipText("Referencia de Tamaño");
        combo_ref_tamaño_.setMaximumSize(new java.awt.Dimension(74, 25));
        combo_ref_tamaño_.setMinimumSize(new java.awt.Dimension(74, 25));
        combo_ref_tamaño_.setPreferredSize(new java.awt.Dimension(74, 25));

        lbl_ref_tamaño_.setText("Tamaño:");

        lbl_cantidad_ensamble_.setText("Cantidad:");

        txt_cant_ensamble.setMaximumSize(new java.awt.Dimension(2147483647, 25));
        txt_cant_ensamble.setMinimumSize(new java.awt.Dimension(6, 25));
        txt_cant_ensamble.setPreferredSize(new java.awt.Dimension(6, 25));
        txt_cant_ensamble.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cant_ensambleKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cant_ensambleKeyTyped(evt);
            }
        });

        scroll_txtArea_detalles_.setPreferredSize(new Dimension(5, 100));

        txtArea_detalles_.setEditable(false);
        txtArea_detalles_.setColumns(1);
        txtArea_detalles_.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtArea_detalles_.setRows(3);
        txtArea_detalles_.setText("Detalles:\nSe Clasificará el tipo de Articulo\n(Bicicleta / Aro / Otro)\nen la lista de disponibilidad\npara la venta, según los Componentes\nelegidos en la siguiente lista.\nEs decir (ejemplo):\nUsted seleccionaría Bicicleta \ntipo Cross, al seleccionar el tipo\nde Marco determinaría\nla carasterística de la Bicicleta\ntipo Cross (marco GW, Nacional, etc) \ny asi se clasificarán en la lista\nde Articulos disponibles para\nla Venta.   \n\n ");
        txtArea_detalles_.setMaximumSize(new java.awt.Dimension(263, 293));
        scroll_txtArea_detalles_.setViewportView(txtArea_detalles_);

        javax.swing.GroupLayout panel_primer_filtro_emsamble_Layout = new javax.swing.GroupLayout(panel_primer_filtro_emsamble_);
        panel_primer_filtro_emsamble_.setLayout(panel_primer_filtro_emsamble_Layout);
        panel_primer_filtro_emsamble_Layout.setHorizontalGroup(
            panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_primer_filtro_emsamble_Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_txtArea_detalles_, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_primer_filtro_emsamble_Layout.createSequentialGroup()
                        .addGroup(panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_cantidad_ensamble_)
                            .addComponent(lbl_tipo_ensamble_)
                            .addComponent(lbl_ref_tamaño_))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_cant_ensamble, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_ref_tamaño_, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_catalogo_ensamble_, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panel_primer_filtro_emsamble_Layout.setVerticalGroup(
            panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_primer_filtro_emsamble_Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tipo_ensamble_)
                    .addComponent(combo_catalogo_ensamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_ref_tamaño_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ref_tamaño_))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cant_ensamble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_cantidad_ensamble_))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll_txtArea_detalles_, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_alistar_despacho_.setToolTipText("Alistar Despacho");
        btn_alistar_despacho_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alistar_despacho_ActionPerformed(evt);
            }
        });

        btn_cancelar_despacho_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_despacho_ActionPerformed(evt);
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

        btn_selec_repuestos_1.setText("nuevo articulo a ensamblar");
        btn_selec_repuestos_1.setToolTipText("Despachar Mercancía para Orden Seleccionada");
        btn_selec_repuestos_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selec_repuestos_1ActionPerformed(evt);
            }
        });

        btn_selec_repuestos_2.setText("cancelar orden de articulo");
        btn_selec_repuestos_2.setToolTipText("Despachar Mercancía para Orden Seleccionada");
        btn_selec_repuestos_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selec_repuestos_2ActionPerformed(evt);
            }
        });

        tabla_ordenes_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tabla_ordenes_.getTableHeader().setReorderingAllowed(false);
        tabla_ordenes_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ordenes_MouseClicked(evt);
            }
        });
        scroll_tabla_ordenes_.setViewportView(tabla_ordenes_);

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

        javax.swing.GroupLayout panel_principal_Layout = new javax.swing.GroupLayout(panel_principal_);
        panel_principal_.setLayout(panel_principal_Layout);
        panel_principal_Layout.setHorizontalGroup(
            panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principal_Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addComponent(lbl_ensamblador_)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(combo_ensambladores_, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_retirar_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_primer_filtro_emsamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btn_Despachar_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btn_Cancelar_Despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_alistar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cancelar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(btn_selec_repuestos_1)
                        .addGap(39, 39, 39)
                        .addComponent(btn_selec_repuestos_2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_principal_Layout.createSequentialGroup()
                        .addComponent(scroll_tabla_ordenes_, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        panel_principal_Layout.setVerticalGroup(
            panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principal_Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_selec_repuestos_1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_selec_repuestos_2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scroll_tabla_ordenes_, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btn_alistar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cancelar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_ensamblador_)
                                .addComponent(combo_ensambladores_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_retirar_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_add_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_primer_filtro_emsamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Despachar_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Cancelar_Despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal_, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal_, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private class ModeloTabla extends DefaultTableModel {
    // <editor-fold defaultstate="collapsed" desc="Codigo que Define un Modelo Abstracto de Tabla implementado por el programador">

        /**
         * Constructor de la Clase que se esta heredando de AbstractTableModel
         */
        public ModeloTabla() {
            super(data, OrdenProduccion.COLUMNAS_TABLA_DESPACHO);
        }

        public void setDatosTabla(Object[][] datos) {
            data = datos;
            fireTableDataChanged();
        }

        @Override//determina la clase de componentes que iran en cada celda
        public Class getColumnClass(int noCol) {
            return OrdenProduccion.TIPOS_COLUMNAS_TABLA_DESPACHOS[noCol];
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col > 0; // con esto le indico que no dejara modificar la primer columna de la Tabla
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            //return data[0].length;
            return OrdenProduccion.COLUMNAS_TABLA_DESPACHO.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            this.fireTableCellUpdated(row, col);//notifica a todos los listeners que el valor de la celda ha sido editado
        }

    }
    
    private void btn_Despachar_OrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Despachar_OrdenActionPerformed
            
    }//GEN-LAST:event_btn_Despachar_OrdenActionPerformed

    private void btn_Cancelar_DespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancelar_DespachoActionPerformed
    
    }//GEN-LAST:event_btn_Cancelar_DespachoActionPerformed

    private void btn_retirar_accesorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_retirar_accesorio1ActionPerformed
        
        
        JOptionPane.showMessageDialog(null, ((ItemDeLista)combo_catalogo_ensamble_.getSelectedItem()).getCod());
        
        
    }//GEN-LAST:event_btn_retirar_accesorio1ActionPerformed

    private void btn_add_accesorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_accesorio1ActionPerformed

        JOptionPane.showMessageDialog(null, "ancho scroll: " + scroll_items_selec.getWidth() + "\n"
                + "30% = " + ((int) ((scroll_items_selec.getWidth() * 30) / 100)) + "\n"
                + "50% = " + ((int) ((scroll_items_selec.getWidth() * 50) / 100)) + "\n"
                + "20% = " + ((int) ((scroll_items_selec.getWidth() * 20) / 100))
        );

    }//GEN-LAST:event_btn_add_accesorio1ActionPerformed

    private void tabla_despacho_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_despacho_MouseClicked
       
        fila_tabla = tabla_despacho_.rowAtPoint(evt.getPoint());
        col_tabla = tabla_despacho_.columnAtPoint(evt.getPoint());
        /*if( fila_tabla > -1 && col_tabla > -1 ){
            this.obs = dtm.getValueAt(fila, 0);
            txtcodpro.setText(""+obs);
        }*/
        
    }//GEN-LAST:event_tabla_despacho_MouseClicked

    private void btn_selec_repuestos_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selec_repuestos_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_selec_repuestos_1ActionPerformed

    private void btn_selec_repuestos_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selec_repuestos_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_selec_repuestos_2ActionPerformed

    private void txt_cant_ensambleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cant_ensambleKeyTyped

        char typ = evt.getKeyChar();
        if ((typ < '0' || typ > '9')) {
            evt.consume();
        }

    }//GEN-LAST:event_txt_cant_ensambleKeyTyped

    private void txt_cant_ensambleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cant_ensambleKeyReleased

    }//GEN-LAST:event_txt_cant_ensambleKeyReleased

    private void tabla_ordenes_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ordenes_MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ordenes_MouseClicked

    private void btn_alistar_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alistar_despacho_ActionPerformed

        try {

           panel_primer_filtro_emsamble_.setEnabled(false);

            /*int total = Integer.parseInt(txt_cant_ensamble.getText());*/
            
            String cod_objeto_ensamble = ((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).getCod();
            String talla = combo_ref_tamaño_.getSelectedItem().toString();
            
            LinkedHashMap<String, ArrayList<ItemDeLista>> informacion_bd = ConsultaSQL.ConsultorBD.obtenerRepuestos_Articulo(cod_objeto_ensamble, talla);

            data = new Object[informacion_bd.size()][3]; // reinstancio a data como un nuevo arreglo de objetos bidimensional
            int i = 0;
            for (HashMap.Entry en : informacion_bd.entrySet()) {
                data[i] = new Object[]{en.getKey().toString(), new ComboBoxItem(en.getValue()), new SpinnerCeldaTabla()};
                i++;
            }
            modeloTabla.setDatosTabla(data);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "error despachar articulos", 0);
        }

    }//GEN-LAST:event_btn_alistar_despacho_ActionPerformed

    private void combo_catalogo_ensamble_ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_catalogo_ensamble_ItemStateChanged
        
        Object itemSelec;
        
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            itemSelec = evt.getItem();
            txtArea_detalles_.setText(String.valueOf((((ItemDeLista) itemSelec).getAtributos()).get("descripcion")) );//lo que se hace aqui es parsear el objeto (el paréntesis con el nombre de la clase parsea el objeto hacia ese tipo o esa clase)
           
            modelo_combo_tallas.cambiarItems((((ItemDeLista) itemSelec).getAtributos()).get("tallas"));
            //combo_ref_tamaño_.setSelectedIndex(0);
        }
        
    }//GEN-LAST:event_combo_catalogo_ensamble_ItemStateChanged

    private void btn_cancelar_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_despacho_ActionPerformed
        
        data = new Object[][]{};
        modeloTabla.setDatosTabla(data);
        
    }//GEN-LAST:event_btn_cancelar_despacho_ActionPerformed

    /**
     * Modelo ComboBox Tamaños.
     */
    private class ModeloComboBoxTallas extends AbstractListModel<Object> implements ComboBoxModel<Object>{

        private Object[] mis_elementos;
        private int posicion;
        
        @SuppressWarnings("OverridableMethodCallInConstructor")
        public ModeloComboBoxTallas() {
            cambiarItems(((ItemDeLista)combo_catalogo_ensamble_.getItemAt(0)).getAtributos().get("tallas"));
        }
        
        @Override
        public int getSize() {
            return mis_elementos.length;
        }
        
        @Override
        public Object getElementAt(int index) {
            posicion = index;
            return mis_elementos[index];
        }

        @Override
        public void setSelectedItem(Object anItem) {
            mis_elementos[posicion] = anItem;
        }

        @Override
        public Object getSelectedItem() {
            return mis_elementos[posicion];
        }

        public void cambiarItems(Object arrayList_tallas){
            Iterator it = ((ArrayList) arrayList_tallas).iterator();
            mis_elementos = new Object[((ArrayList) arrayList_tallas).size()];
            int i = 0;
            while (it.hasNext()) {
                mis_elementos[i] = it.next();
                i++;
            }
            posicion = (mis_elementos.length - 1);
            //fireContentsChanged(this, 0, mis_elementos.length);
        }
        
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancelar_Despacho;
    private javax.swing.JButton btn_Despachar_Orden;
    private javax.swing.JButton btn_add_accesorio1;
    private javax.swing.JButton btn_alistar_despacho_;
    private javax.swing.JButton btn_cancelar_despacho_;
    private javax.swing.JButton btn_retirar_accesorio1;
    private javax.swing.JButton btn_selec_repuestos_1;
    private javax.swing.JButton btn_selec_repuestos_2;
    private javax.swing.JComboBox combo_catalogo_ensamble_;
    private javax.swing.JComboBox combo_ensambladores_;
    private javax.swing.JComboBox combo_ref_tamaño_;
    private javax.swing.JLabel lbl_cantidad_ensamble_;
    private javax.swing.JLabel lbl_ensamblador_;
    private javax.swing.JLabel lbl_ref_tamaño_;
    private javax.swing.JLabel lbl_tipo_ensamble_;
    private javax.swing.JPanel panel_primer_filtro_emsamble_;
    private javax.swing.JPanel panel_principal_;
    private javax.swing.JScrollPane scroll_items_selec;
    private javax.swing.JScrollPane scroll_tabla_ordenes_;
    private javax.swing.JScrollPane scroll_txtArea_detalles_;
    private javax.swing.JTable tabla_despacho_;
    private javax.swing.JTable tabla_ordenes_;
    private javax.swing.JTextArea txtArea_detalles_;
    private javax.swing.JTextField txt_cant_ensamble;
    // End of variables declaration//GEN-END:variables

}
