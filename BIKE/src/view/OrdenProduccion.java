//code
package view;

import controller.ConsultaSQL;
import controller.componentes.ComboBoxItem;
import controller.componentes.Paneles;
import controller.componentes.Paneles.VentanaInterna;
import controller.componentes.TablaAlistamiento;
import model.componentes.ItemDeLista;
import static view.MenuPrincipal.escritorio;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

/**
 *
 */
public class OrdenProduccion extends VentanaInterna {

    /**NOMBRE ARCHIVO IMAGEN DE FONDO PARA ESTA VENTANA. Solo nombre sin extension (obligatorio archivos png)*/
    private static final String NOMBRE_MI_IMAGEN_FONDO = "fondo_orden_produccion";
    /***/
    
    public static final String COD_CMBOX_ENSAMBLADORES = "ensambladores";
    public static final String COD_CMBOX_ARTICULOS = "articulos";

    private int fila_tabla, col_tabla;

    /**
     * Constructor.
     */
    public OrdenProduccion() {
        //AQUI AL LLAMAR A SUPER LLAMO AL CONSTRUCTOR DE VENTANA INTERNA QUE RECIBE UN STRING CON EL NOMBRE
        //DE LA IMAGEN DE FONDO UBICADA EN LA CARPETA "mis_imagenes" EN EL DIRECTORIO PRINCIPAL DEL PROYECTO (fuera de todas las carpetas) 
        super(NOMBRE_MI_IMAGEN_FONDO);
        initComponents();
        this.title = "Despacho de Ordenes de Ensamble";
        this.closable = true;
        this.iconable = true;
        Dimension ScreenSpace = escritorio.getSize(), mySpc = this.getSize();
        this.setLocation((ScreenSpace.width / 18), ((ScreenSpace.height - mySpc.height) / 2));
        this.setToolTipText("Modulo de Control de Ordenes de Ensamble");
        fila_tabla = 0;
        col_tabla = 0;
        try{
            ((ComboBoxItem)combo_ensambladores_).seleccionaComboBox(COD_CMBOX_ENSAMBLADORES);
            ((ComboBoxItem)combo_catalogo_ensamble_).seleccionaComboBox(COD_CMBOX_ARTICULOS);
            Object items = (((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).getAtributos()).get("tallas");
            ((ModeloComboTallas)combo_ref_tamaño_.getModel()).cambiarItems(items);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Constructor Orden Produccion", 0);
        }
    }

    /**
     * El siguiente metodo es llamado junto con el constructor para inicializar
     * el formulario. ADVERTENCIA: No modifique este codigo. El contenido de
     * este metodo siempre es generado automaticamente por el editor grafico.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_ensamblador_ = new javax.swing.JLabel();
        btn_retirar_accesorio1 = new javax.swing.JButton();
        btn_add_accesorio1 = new javax.swing.JButton();
        combo_ensambladores_ = new controller.componentes.ComboBoxItem();
        panel_primer_filtro_emsamble_ = new Paneles.PanelContenedorControles();
        combo_catalogo_ensamble_ = new controller.componentes.ComboBoxItem();
        lbl_tipo_ensamble_ = new javax.swing.JLabel();
        combo_ref_tamaño_ = new JComboBox(new ModeloComboTallas());
        lbl_ref_tamaño_ = new javax.swing.JLabel();
        lbl_cantidad_ensamble_ = new javax.swing.JLabel();
        txt_cant_ensamble_ = new javax.swing.JTextField();
        scroll_txtArea_detalles_ = new javax.swing.JScrollPane();
        txtArea_detalles_ = new javax.swing.JTextArea();
        btn_alistar_despacho_ = new javax.swing.JButton();
        btn_cancelar_despacho_ = new javax.swing.JButton();
        scroll_items_selec = new javax.swing.JScrollPane();
        tabla_alistamiento_ = new TablaAlistamiento();
        btn_guardar_Orden_Alistada_ = new javax.swing.JButton();
        btn_borrar_Orden_Registrada_ = new javax.swing.JButton();
        scroll_tabla_ordenes_ = new javax.swing.JScrollPane();
        tabla_mesa_ = new javax.swing.JTable();
        btn_Despachar_Orden = new javax.swing.JButton();
        btn_Cancelar_Despacho = new javax.swing.JButton();
        txt_n_orden_ = new javax.swing.JTextField();
        lbl_n_orden_ = new javax.swing.JLabel();

        setClosable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(915, 424));
        setMinimumSize(new java.awt.Dimension(915, 424));
        setName(""); // NOI18N

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
        panel_primer_filtro_emsamble_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        combo_ref_tamaño_.setToolTipText("Referencia de Tamaño");
        combo_ref_tamaño_.setMaximumSize(new java.awt.Dimension(74, 25));
        combo_ref_tamaño_.setMinimumSize(new java.awt.Dimension(74, 25));
        combo_ref_tamaño_.setPreferredSize(new java.awt.Dimension(74, 25));

        lbl_ref_tamaño_.setText("Tamaño:");

        lbl_cantidad_ensamble_.setText("Cantidad:");

        txt_cant_ensamble_.setMaximumSize(new java.awt.Dimension(6, 25));
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

        //http://javapiola.blogspot.com.co/2009/11/tutorial-de-jtextarea-en-java.html
        txtArea_detalles_.setEditable(false);
        txtArea_detalles_.setColumns(1);
        txtArea_detalles_.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtArea_detalles_.setLineWrap(true);
        txtArea_detalles_.setRows(3);
        txtArea_detalles_.setText("Detalles:\nSe Clasificará el tipo de Articulo\n(Bicicleta / Aro / Otro)\nen la lista de disponibilidad\npara la venta, según los Componentes\nelegidos en la siguiente lista.\nEs decir (ejemplo):\nUsted seleccionaría Bicicleta \ntipo Cross, al seleccionar el tipo\nde Marco determinaría\nla carasterística de la Bicicleta\ntipo Cross (marco GW, Nacional, etc) \ny asi se clasificarán en la lista\nde Articulos disponibles para\nla Venta.   \n\n ");
        txtArea_detalles_.setWrapStyleWord(true);
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

        btn_cancelar_despacho_.setToolTipText("Cancelar Alistamiento");
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

        btn_guardar_Orden_Alistada_.setText("nuevo articulo a ensamblar");
        btn_guardar_Orden_Alistada_.setToolTipText("Despachar Mercancía para Orden Seleccionada");
        btn_guardar_Orden_Alistada_.setEnabled(false);
        btn_guardar_Orden_Alistada_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_Orden_Alistada_ActionPerformed(evt);
            }
        });

        btn_borrar_Orden_Registrada_.setText("cancelar orden de articulo");
        btn_borrar_Orden_Registrada_.setToolTipText("Despachar Mercancía para Orden Seleccionada");
        btn_borrar_Orden_Registrada_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_borrar_Orden_Registrada_ActionPerformed(evt);
            }
        });

        tabla_mesa_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tabla_mesa_.getTableHeader().setReorderingAllowed(false);
        tabla_mesa_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_mesa_MouseClicked(evt);
            }
        });
        scroll_tabla_ordenes_.setViewportView(tabla_mesa_);

        btn_Despachar_Orden.setText("Ejecutar Orden");
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

        txt_n_orden_.setEditable(false);
        txt_n_orden_.setBackground(new java.awt.Color(204, 204, 255));
        txt_n_orden_.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        txt_n_orden_.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_n_orden_.setText("0034");
        txt_n_orden_.setToolTipText("Numero de Orden");
        txt_n_orden_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_n_orden_.setMaximumSize(new java.awt.Dimension(30, 20));
        txt_n_orden_.setMinimumSize(new java.awt.Dimension(30, 20));
        txt_n_orden_.setSelectionColor(new java.awt.Color(255, 255, 51));

        lbl_n_orden_.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        lbl_n_orden_.setText("Orden de Producción no.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_ensamblador_)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(combo_ensambladores_, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_retirar_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_primer_filtro_emsamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_alistar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cancelar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btn_Despachar_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btn_Cancelar_Despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(btn_guardar_Orden_Alistada_)
                                .addGap(39, 39, 39)
                                .addComponent(btn_borrar_Orden_Registrada_))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(scroll_tabla_ordenes_, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_n_orden_)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_n_orden_, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_ensamblador_)
                                .addComponent(combo_ensambladores_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_retirar_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_add_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel_primer_filtro_emsamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btn_alistar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cancelar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Despachar_Orden, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Cancelar_Despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_n_orden_)
                            .addComponent(txt_n_orden_, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_guardar_Orden_Alistada_, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_borrar_Orden_Registrada_, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scroll_tabla_ordenes_, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static class ModeloComboTallas extends AbstractListModel<Object> implements ComboBoxModel<Object> {

        private Object[] mis_elementos;
        private int posicion;
        
        public ModeloComboTallas() {
            mis_elementos = new Object[]{""};
        }
        public void cambiarItems(Object arrayList_tallas) {
            //this.removeAllItems();
            Iterator it = ((ArrayList) arrayList_tallas).iterator();
            mis_elementos = new Object[((ArrayList) arrayList_tallas).size()];
            int i = 0;
            while (it.hasNext()) {
                mis_elementos[i] = it.next();
                i++;
            }
            posicion = 0;//esto indica que se posicionará en el primer item
            fireContentsChanged(this, 0, mis_elementos.length);
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
    }

    private void btn_Despachar_OrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Despachar_OrdenActionPerformed

    }//GEN-LAST:event_btn_Despachar_OrdenActionPerformed

    private void btn_Cancelar_DespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancelar_DespachoActionPerformed

    }//GEN-LAST:event_btn_Cancelar_DespachoActionPerformed

    private void btn_retirar_accesorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_retirar_accesorio1ActionPerformed

        JOptionPane.showMessageDialog(null, ((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).obtenerCodigoId());

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

    private void btn_guardar_Orden_Alistada_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_Orden_Alistada_ActionPerformed
        // <editor-fold defaultstate="collapsed" desc="CODE">
        try {
            String ensamblador = ((ItemDeLista) combo_ensambladores_.getSelectedItem()).obtenerCodigoId();
            Object[] produccion = new Object[3];
            produccion[0] = ((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).obtenerCodigoId();
            produccion[1] = combo_ref_tamaño_.getSelectedItem().toString();
            produccion[2] = Integer.parseInt(txt_cant_ensamble_.getText());
            Object[][] listado = ((TablaAlistamiento) tabla_alistamiento_).obtenerListadoDespacho();
            
            boolean hecho = ConsultaSQL.ConsultorBD.registrarNuevaOrden(ensamblador, produccion, listado);
            if (hecho) {
                combo_ensambladores_.setEnabled(true);
                panel_primer_filtro_emsamble_.setEnabled(true);
                ((TablaAlistamiento) tabla_alistamiento_).vaciarTabla();
                btn_guardar_Orden_Alistada_.setEnabled(false);
                //JOptionPane.showMessageDialog(OrdenProduccion.this, );
                JOptionPane.showOptionDialog(OrdenProduccion.this,
                        "Orden Guardada Correctamente.",
                        "Orden de Producción", // título del JOptionPane
                        JOptionPane.OK_OPTION, // tipo input
                        JOptionPane.INFORMATION_MESSAGE, // tipo mensaje
                        new ImageIcon("mis_imagenes/icon_jop_correcto.png"), // icono, si es nulo aparecerá el por defecto
                        new Object[]{"Aceptar"}, //opciones => estos serán los botones
                        new Object[]{});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Guardar Orden", 0);
        }

// </editor-fold>
    }//GEN-LAST:event_btn_guardar_Orden_Alistada_ActionPerformed

    private void btn_borrar_Orden_Registrada_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_borrar_Orden_Registrada_ActionPerformed
        try {
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Borrar Orden", 0);
        }
    }//GEN-LAST:event_btn_borrar_Orden_Registrada_ActionPerformed

    private void txt_cant_ensamble_KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cant_ensamble_KeyTyped

        char typ = evt.getKeyChar();
        if (txt_cant_ensamble_.getText().isEmpty()) {
            if (typ < '1' || typ > '9') {
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

    private void tabla_mesa_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_mesa_MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_mesa_MouseClicked

    private void btn_alistar_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alistar_despacho_ActionPerformed

        try {
            if (!txt_cant_ensamble_.getText().isEmpty()) {

                int decision = JOptionPane.showOptionDialog(this,
                        "Alistarás una nueva Orden de Producción para "
                        + ((ItemDeLista) combo_ensambladores_.getSelectedItem()).getAtributos().get(ItemDeLista.TEXTO_MOSTRADO),
                        "Orden de Producción", // título del JOptionPane
                        JOptionPane.OK_CANCEL_OPTION, // tipo input
                        JOptionPane.INFORMATION_MESSAGE, // tipo mensaje
                        null, // icono, si es nulo aparecerá el por defecto
                        new Object[]{"Aceptar", "Cancelar"}, //opciones => estos serán los botones
                        new Object[]{}); //dialogo o texto mostrado

                if (decision == 0) { // como "Aceptar" está primero en el arreglo tiene el indice 0; -1 es la x de cerrar 

                    String cod_objeto_ensamble = ((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).obtenerCodigoId();
                    String talla = combo_ref_tamaño_.getSelectedItem().toString();
                    int cantidad = Integer.parseInt(txt_cant_ensamble_.getText());

                    LinkedHashMap<String, Object[]>/*ArrayList<ItemDeLista>*/ informacion_bd
                            = ConsultaSQL.ConsultorBD.obtenerRepuestos_Articulo(cod_objeto_ensamble, talla);

                    //if (informacion_bd != null) {
                        panel_primer_filtro_emsamble_.setEnabled(false);
                        combo_ensambladores_.setEnabled(false);
                        ((TablaAlistamiento) tabla_alistamiento_).actualizaTabla(informacion_bd, cantidad);
                        btn_guardar_Orden_Alistada_.setEnabled(true);
                    /*} else {
                        JOptionPane.showMessageDialog(this, "Al parecer no hay Componentes vinculados a este Articulo",
                                "Alistar Orden de Producción", 0);
                    }*/
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Antes, debe especificar la cantidad de Artículos.", "Orden de Producción", 0);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Error en Alistamiento", 0);
        }

    }//GEN-LAST:event_btn_alistar_despacho_ActionPerformed

    private void combo_catalogo_ensamble_ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_catalogo_ensamble_ItemStateChanged

        Object itemSelec;
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            itemSelec = evt.getItem();
            txtArea_detalles_.setText(String.valueOf((((ItemDeLista) itemSelec).getAtributos()).get("descripcion")));//lo que se hace aqui es parsear el objeto (el paréntesis con el nombre de la clase parsea el objeto hacia ese tipo o esa clase)
            
            Object items = (((ItemDeLista) itemSelec).getAtributos()).get("tallas");
            ((ModeloComboTallas)combo_ref_tamaño_.getModel()).cambiarItems(items);
        }

    }//GEN-LAST:event_combo_catalogo_ensamble_ItemStateChanged

    private void btn_cancelar_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_despacho_ActionPerformed

        ((TablaAlistamiento) tabla_alistamiento_).vaciarTabla();
        panel_primer_filtro_emsamble_.setEnabled(true);
        combo_ensambladores_.setEnabled(true);

    }//GEN-LAST:event_btn_cancelar_despacho_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancelar_Despacho;
    private javax.swing.JButton btn_Despachar_Orden;
    private javax.swing.JButton btn_add_accesorio1;
    private javax.swing.JButton btn_alistar_despacho_;
    private javax.swing.JButton btn_borrar_Orden_Registrada_;
    private javax.swing.JButton btn_cancelar_despacho_;
    private javax.swing.JButton btn_guardar_Orden_Alistada_;
    private javax.swing.JButton btn_retirar_accesorio1;
    private javax.swing.JComboBox combo_catalogo_ensamble_;
    private javax.swing.JComboBox combo_ensambladores_;
    private javax.swing.JComboBox combo_ref_tamaño_;
    private javax.swing.JLabel lbl_cantidad_ensamble_;
    private javax.swing.JLabel lbl_ensamblador_;
    private javax.swing.JLabel lbl_n_orden_;
    private javax.swing.JLabel lbl_ref_tamaño_;
    private javax.swing.JLabel lbl_tipo_ensamble_;
    private javax.swing.JPanel panel_primer_filtro_emsamble_;
    private javax.swing.JScrollPane scroll_items_selec;
    private javax.swing.JScrollPane scroll_tabla_ordenes_;
    private javax.swing.JScrollPane scroll_txtArea_detalles_;
    private javax.swing.JTable tabla_alistamiento_;
    private javax.swing.JTable tabla_mesa_;
    private javax.swing.JTextArea txtArea_detalles_;
    private javax.swing.JTextField txt_cant_ensamble_;
    private javax.swing.JTextField txt_n_orden_;
    // End of variables declaration//GEN-END:variables

    
}
