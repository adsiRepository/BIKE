
//code

package view;

import controller.ConsultaSQL;
import controller.componentes.Paneles.PanelFondoVentanaInterna;
import model.componentes.ItemDeLista;
import controller.componentes.TablaAlistamiento;
import static view.MenuPrincipal.escritorio;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 */
public class OrdenProduccion extends /*VentanaInterna*/javax.swing.JInternalFrame {
    
    private static final String NOMBRE_MI_IMAGEN_FONDO = "fondo_orden_produccion";
    
    public static final String COD_CMBOX_ENSAMBLADORES = "ensambladores";
    public static final String COD_CMBOX_ARTICULOS = "articulos";
    
    private int fila_tabla, col_tabla;   
    private ModeloComboBoxTallas modelo_combo_tallas;
    
    /**Constructor.*/
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
        fila_tabla = 0;  col_tabla = 0;
    }
    
    /**
     * El siguiente metodo es llamado junto con el constructor para inicializar el formulario.
     * ADVERTENCIA: No modifique este codigo. El contenido de este metodo siempre
     * es generado automaticamente por el editor grafico.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_principal_ = new PanelFondoVentanaInterna(NOMBRE_MI_IMAGEN_FONDO);
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
        txt_cant_ensamble_ = new javax.swing.JTextField();
        scroll_txtArea_detalles_ = new javax.swing.JScrollPane();
        txtArea_detalles_ = new javax.swing.JTextArea();
        btn_alistar_despacho_ = new javax.swing.JButton();
        btn_cancelar_despacho_ = new javax.swing.JButton();
        scroll_items_selec = new javax.swing.JScrollPane();
        tabla_alistamiento_ = new TablaAlistamiento();
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

        txt_cant_ensamble_.setMaximumSize(new java.awt.Dimension(2147483647, 25));
        txt_cant_ensamble_.setMinimumSize(new java.awt.Dimension(6, 25));
        txt_cant_ensamble_.setPreferredSize(new java.awt.Dimension(6, 25));
        txt_cant_ensamble_.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cant_ensamble_KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cant_ensamble_KeyTyped(evt);
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
                            .addComponent(txt_cant_ensamble_, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txt_cant_ensamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        scroll_items_selec.setMaximumSize(new java.awt.Dimension(496, 402));
        scroll_items_selec.setMinimumSize(new java.awt.Dimension(496, 402));

        tabla_alistamiento_.getTableHeader().setReorderingAllowed(false);
        tabla_alistamiento_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_alistamiento_MouseClicked(evt);
            }
        });
        scroll_items_selec.setViewportView(tabla_alistamiento_);

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
                        .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_principal_Layout.createSequentialGroup()
                                .addComponent(lbl_ensamblador_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combo_ensambladores_, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_retirar_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_add_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panel_primer_filtro_emsamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_alistar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cancelar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btn_Despachar_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btn_Cancelar_Despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)
                        .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_principal_Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(btn_selec_repuestos_1)
                                .addGap(39, 39, 39)
                                .addComponent(btn_selec_repuestos_2))
                            .addComponent(scroll_tabla_ordenes_, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panel_principal_Layout.setVerticalGroup(
            panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_principal_Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_principal_Layout.createSequentialGroup()
                        .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_principal_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_selec_repuestos_1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_selec_repuestos_2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scroll_tabla_ordenes_, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal_, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_principal_, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static class ModeloComboBoxTallas extends AbstractListModel<Object> implements ComboBoxModel<Object>{
        // <editor-fold defaultstate="collapsed" desc="Modelo del ComboBox de tamaños">

        private Object[] mis_elementos;
        private int posicion;
        
        @SuppressWarnings("OverridableMethodCallInConstructor")
        public ModeloComboBoxTallas() {
            cambiarItems(((ItemDeLista) combo_catalogo_ensamble_.getItemAt(0)).getAtributos().get("tallas"));
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
        
        public void cambiarItems(Object arrayList_tallas) {
            Iterator it = ((ArrayList) arrayList_tallas).iterator();
            mis_elementos = new Object[((ArrayList) arrayList_tallas).size()];
            int i = 0;
            while (it.hasNext()) {
                mis_elementos[i] = it.next();
                i++;
            }
            posicion = 0;//(mis_elementos.length - 1);
            fireContentsChanged(this, 0, mis_elementos.length);
        }

// </editor-fold>
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

    private void tabla_alistamiento_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_alistamiento_MouseClicked
       
        fila_tabla = tabla_alistamiento_.rowAtPoint(evt.getPoint());
        col_tabla = tabla_alistamiento_.columnAtPoint(evt.getPoint());
        /*if( fila_tabla > -1 && col_tabla > -1 ){
            this.obs = dtm.getValueAt(fila, 0);
            txtcodpro.setText(""+obs);
        }*/
        
    }//GEN-LAST:event_tabla_alistamiento_MouseClicked

    private void btn_selec_repuestos_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selec_repuestos_1ActionPerformed

    }//GEN-LAST:event_btn_selec_repuestos_1ActionPerformed

    private void btn_selec_repuestos_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selec_repuestos_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_selec_repuestos_2ActionPerformed

    private void txt_cant_ensamble_KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cant_ensamble_KeyTyped

        char typ = evt.getKeyChar();
 
        if (txt_cant_ensamble_.getText().isEmpty()) {
            if (typ == '0' || (typ < '1' || typ > '9')) {
                evt.consume();
            }
        } else {
            if ((typ < '0' || typ > '9')) {
                evt.consume();
            }
        }

    }//GEN-LAST:event_txt_cant_ensamble_KeyTyped

    private void txt_cant_ensamble_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cant_ensamble_KeyReleased

    }//GEN-LAST:event_txt_cant_ensamble_KeyReleased

    private void tabla_ordenes_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ordenes_MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ordenes_MouseClicked

    private void btn_alistar_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alistar_despacho_ActionPerformed

        try {

            if (!txt_cant_ensamble_.getText().isEmpty()) {

                panel_primer_filtro_emsamble_.setEnabled(false);

                String cod_objeto_ensamble = ((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).getCod();
                String talla = combo_ref_tamaño_.getSelectedItem().toString();
                int cantidad = Integer.parseInt(txt_cant_ensamble_.getText());
                
                LinkedHashMap<String, Object[]>/*ArrayList<ItemDeLista>*/ informacion_bd = ConsultaSQL.ConsultorBD.obtenerRepuestos_Articulo(cod_objeto_ensamble, talla);

                if (informacion_bd != null) {
                    ((TablaAlistamiento) tabla_alistamiento_).actualizaTabla(informacion_bd, cantidad);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Antes, debe especificar la cantidad de Artículos.", "Orden de Producción", 0);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en Alistamiento", 0);
        }

    }//GEN-LAST:event_btn_alistar_despacho_ActionPerformed

    private void combo_catalogo_ensamble_ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_catalogo_ensamble_ItemStateChanged
        
        Object itemSelec;
        
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            itemSelec = evt.getItem();
            txtArea_detalles_.setText(String.valueOf((((ItemDeLista) itemSelec).getAtributos()).get("descripcion")) );//lo que se hace aqui es parsear el objeto (el paréntesis con el nombre de la clase parsea el objeto hacia ese tipo o esa clase)
           
            modelo_combo_tallas.cambiarItems((((ItemDeLista) itemSelec).getAtributos()).get("tallas"));
        }
        
    }//GEN-LAST:event_combo_catalogo_ensamble_ItemStateChanged

    private void btn_cancelar_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_despacho_ActionPerformed
        
        ((TablaAlistamiento)tabla_alistamiento_).vaciarTabla();
        
    }//GEN-LAST:event_btn_cancelar_despacho_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancelar_Despacho;
    private javax.swing.JButton btn_Despachar_Orden;
    private javax.swing.JButton btn_add_accesorio1;
    private javax.swing.JButton btn_alistar_despacho_;
    private javax.swing.JButton btn_cancelar_despacho_;
    private javax.swing.JButton btn_retirar_accesorio1;
    private javax.swing.JButton btn_selec_repuestos_1;
    private javax.swing.JButton btn_selec_repuestos_2;
    private static javax.swing.JComboBox combo_catalogo_ensamble_;
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
    private javax.swing.JTable tabla_alistamiento_;
    private javax.swing.JTable tabla_ordenes_;
    private javax.swing.JTextArea txtArea_detalles_;
    private javax.swing.JTextField txt_cant_ensamble_;
    // End of variables declaration//GEN-END:variables

}
