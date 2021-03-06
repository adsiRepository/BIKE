//code
package view;

// <editor-fold defaultstate="collapsed" desc="imports">

import controller.ConsultaSQL;
import controller.componentes.ComboBoxItem;
import controller.componentes.Paneles;
import controller.componentes.Paneles.VentanaInterna;
import controller.componentes.TablaAlistamiento;
import controller.componentes.TablaProduccion;
import model.componentes.ItemDeLista;
import static view.MenuPrincipal.escritorio;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

// </editor-fold>

/**
 *
 */
public class OrdenProduccion extends VentanaInterna {

    /**NOMBRE ARCHIVO IMAGEN DE FONDO PARA ESTA VENTANA. Solo nombre sin extension (obligatorio archivos png)*/
    private static final String NOMBRE_MI_IMAGEN_FONDO = "fondo_orden_produccion";
    /***/
    
    public static final String MODELO_COMBO_ENSAMBLADORES = "ensambladores";
    public static final String MODELO_COMBO_ARTICULOS = "articulos";

    private String op_boton_guardar_lista;
    public static final String OP_BTN_INSERT = "insertar";
    public static final String OP_BTN_UPDT = "actualizar";
    
    private String talla_en_cuestion;
    
    //private Object[] detalle_orden_actual;
    
    //private int fila_tabla_produccion, col_tabla_produccion;

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
        this.resizable = true;
        Dimension ScreenSpace = escritorio.getSize(), mySpc = this.getSize();
        this.setLocation((ScreenSpace.width / 18), ((ScreenSpace.height - mySpc.height) / 2));
        this.setToolTipText("Modulo de Control de Ordenes de Ensamble");
        this.setFrameIcon(new ImageIcon(OrdenProduccion.class.getResource("/sources/mis_imagenes/itmnu_nueva_ordenb.png")));
        //fila_tabla_produccion = 0;
        //col_tabla_produccion = 0;        
        
        try {
            ((ComboBoxItem) combo_ensambladores_).llenarme(MODELO_COMBO_ENSAMBLADORES);
            ((ComboBoxItem) combo_articulos_).llenarme(MODELO_COMBO_ARTICULOS);
            Object items = (((ItemDeLista) combo_articulos_.getSelectedItem()).getAtributos()).get("tallas");
            ((ComboTallas)combo_ref_tamaño_).mi_modelo_tallas.cambiarItems(items);
            //((ModeloComboTallas) combo_ref_tamaño_.getModel()).cambiarItems(items);
            ((TablaProduccion) tabla_produccion_).actualizarTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Constructor Orden Produccion", 0);
        }

        
        //int anchoContenedor = scroll_tabla_produccion_.getWidth();
        int[] anchos = new int[]{
            /*((anchoContenedor * 8) / 100), ((anchoContenedor * 6) / 100),
            ((anchoContenedor * 15) / 100), ((anchoContenedor * 15) / 100),
            ((anchoContenedor * 6) / 100), ((anchoContenedor * 6) / 100),
            ((anchoContenedor * 30) / 100)*/
        90, 80, 120, 120, 60, 60, 150
        };
        int alturaCol = 20;
        ((TablaProduccion) tabla_produccion_).formatearTabla(anchos, alturaCol);

        op_boton_guardar_lista = OP_BTN_INSERT;
        talla_en_cuestion = null;
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
        btn_refrecar_lista_edicion_ = new javax.swing.JButton();
        combo_ensambladores_ = new controller.componentes.ComboBoxItem();
        panel_primer_filtro_emsamble_ = new Paneles.PanelContenedorControles();
        combo_articulos_ = new controller.componentes.ComboBoxItem();
        lbl_tipo_ensamble_ = new javax.swing.JLabel();
        combo_ref_tamaño_ = new ComboTallas();
        lbl_ref_tamaño_ = new javax.swing.JLabel();
        lbl_cantidad_ensamble_ = new javax.swing.JLabel();
        txt_cant_ensamble_ = new javax.swing.JTextField();
        scroll_txtArea_detalles_ = new javax.swing.JScrollPane();
        txtArea_detalles_ = new javax.swing.JTextArea();
        btn_alistar_despacho_ = new javax.swing.JButton();
        btn_cancelar_despacho_ = new javax.swing.JButton();
        scroll_items_selec = new javax.swing.JScrollPane();
        tabla_alistamiento_ = new TablaAlistamiento();
        btn_guardar_lista_ = new javax.swing.JButton();
        btn_borrar_Orden_Registrada_ = new javax.swing.JButton();
        scroll_tabla_produccion_ = new javax.swing.JScrollPane();
        tabla_produccion_ = new TablaProduccion(TablaProduccion.TABLA_VISUALIZACION);
        btn_guardar_salir_ = new javax.swing.JButton();
        btn_cancelar_alistamiento_ = new javax.swing.JButton();
        txt_n_orden_ = new javax.swing.JTextField();
        lbl_n_orden_ = new javax.swing.JLabel();

        setClosable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(915, 424));
        setMinimumSize(new java.awt.Dimension(915, 424));
        setName(""); // NOI18N

        lbl_ensamblador_.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_ensamblador_.setForeground(new java.awt.Color(255, 0, 0));
        lbl_ensamblador_.setText("Ensamblador:");

        btn_refrecar_lista_edicion_.setText("refrescar lista");
        btn_refrecar_lista_edicion_.setEnabled(false);
        btn_refrecar_lista_edicion_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refrecar_lista_edicion_ActionPerformed(evt);
            }
        });

        panel_primer_filtro_emsamble_.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_primer_filtro_emsamble_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        combo_articulos_.setToolTipText("Al Seleccionar se filtraran en la lista los Items correspondientes a esta clase de Articulo");
        combo_articulos_.setMaximumSize(new java.awt.Dimension(32767, 25));
        combo_articulos_.setMinimumSize(new java.awt.Dimension(74, 25));
        combo_articulos_.setPreferredSize(new java.awt.Dimension(74, 25));
        combo_articulos_.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_articulos_ItemStateChanged(evt);
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
                            .addComponent(combo_articulos_, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panel_primer_filtro_emsamble_Layout.setVerticalGroup(
            panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_primer_filtro_emsamble_Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_primer_filtro_emsamble_Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tipo_ensamble_)
                    .addComponent(combo_articulos_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        btn_alistar_despacho_.setIcon(new javax.swing.ImageIcon(OrdenProduccion.class.getResource("/sources/mis_imagenes/imgbtn_preparar_mercancia.png")));
        btn_alistar_despacho_.setToolTipText("Alistar Despacho");
        btn_alistar_despacho_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_alistar_despacho_ActionPerformed(evt);
            }
        });

        btn_cancelar_despacho_.setIcon(new ImageIcon(OrdenProduccion.class.getResource("/sources/mis_imagenes/imgbtn_cancelar_despacho.png")));
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

        btn_guardar_lista_.setText("aceptar y alistar otra");
        btn_guardar_lista_.setToolTipText("Despachar la orden actual y preparar otra");
        btn_guardar_lista_.setEnabled(false);
        btn_guardar_lista_.setMaximumSize(new java.awt.Dimension(167, 23));
        btn_guardar_lista_.setMinimumSize(new java.awt.Dimension(167, 23));
        btn_guardar_lista_.setPreferredSize(new java.awt.Dimension(167, 23));
        btn_guardar_lista_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_lista_ActionPerformed(evt);
            }
        });

        btn_borrar_Orden_Registrada_.setText("borrar orden seleccionada");
        btn_borrar_Orden_Registrada_.setToolTipText("Borraras la/s orden que seleccione/s en la tabla inferior");
        btn_borrar_Orden_Registrada_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_borrar_Orden_Registrada_ActionPerformed(evt);
            }
        });

        scroll_tabla_produccion_.setMaximumSize(new java.awt.Dimension(452, 402));
        scroll_tabla_produccion_.setMinimumSize(new java.awt.Dimension(452, 402));

        tabla_produccion_.getTableHeader().setReorderingAllowed(false);
        tabla_produccion_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_produccion_MouseClicked(evt);
            }
        });
        scroll_tabla_produccion_.setViewportView(tabla_produccion_);

        btn_guardar_salir_.setText("Guardar Actual y Salir");
        btn_guardar_salir_.setToolTipText("Guardar el alistamiento actual y salir");
        btn_guardar_salir_.setMaximumSize(new java.awt.Dimension(139, 23));
        btn_guardar_salir_.setMinimumSize(new java.awt.Dimension(139, 23));
        btn_guardar_salir_.setPreferredSize(new java.awt.Dimension(139, 23));
        btn_guardar_salir_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_salir_ActionPerformed(evt);
            }
        });

        btn_cancelar_alistamiento_.setText("Cancelar Actual y Salir");
        btn_cancelar_alistamiento_.setToolTipText("Cancelar el alistamiento actual y salir");
        btn_cancelar_alistamiento_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_alistamiento_ActionPerformed(evt);
            }
        });

        txt_n_orden_.setEditable(false);
        txt_n_orden_.setBackground(new java.awt.Color(204, 204, 255));
        txt_n_orden_.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_n_orden_.setToolTipText("Numero de Orden");
        txt_n_orden_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_n_orden_.setMaximumSize(new java.awt.Dimension(30, 20));
        txt_n_orden_.setMinimumSize(new java.awt.Dimension(30, 20));
        txt_n_orden_.setSelectionColor(new java.awt.Color(255, 255, 51));

        lbl_n_orden_.setBackground(new java.awt.Color(255, 255, 255));
        lbl_n_orden_.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_n_orden_.setForeground(new java.awt.Color(255, 0, 0));
        lbl_n_orden_.setText("Orden de Producción no.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panel_primer_filtro_emsamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lbl_ensamblador_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(combo_ensambladores_, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(btn_guardar_lista_, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_borrar_Orden_Registrada_, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_alistar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_cancelar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_refrecar_lista_edicion_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_n_orden_)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_n_orden_, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50))
                                    .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_guardar_salir_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cancelar_alistamiento_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(59, 59, 59)
                        .addComponent(scroll_tabla_produccion_, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_n_orden_)
                        .addComponent(txt_n_orden_, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_refrecar_lista_edicion_)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_borrar_Orden_Registrada_, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_guardar_lista_, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(combo_ensambladores_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_ensamblador_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel_primer_filtro_emsamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_alistar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_cancelar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_guardar_salir_, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_cancelar_alistamiento_, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scroll_tabla_produccion_, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="METODOS DEL PROGRAMADOR">

    private static class ComboTallas extends JComboBox<Object>{

        public ModeloComboTallas mi_modelo_tallas;
        private ArrayList<Object> mis_elementos;
        private int posicion;
        
        public ComboTallas() {
            super();
            mi_modelo_tallas = new ModeloComboTallas();
            this.setModel(mi_modelo_tallas);
        }

        /**
         * @param codItemDeLista
         */
        public void seleccionarItem(String seleccion) throws Exception {
            Object talla;
            String cod;
            /*for (int i = 0; i < mis_elementos.length; i++) {
             talla = mis_elementos[i];
             cod = talla.toString();
             if (cod.equals(seleccion)) {
             //OrdenProduccion.this.combo_ref_tamaño_.setSelectedIndex(i);
             //setSelectedItem(talla);
             ComboTallas.this.setSelectedIndex(i);
             }
             }*/
            for (int i = 0; i < mis_elementos.size(); i++) {
                talla = mis_elementos.get(i);
                cod = talla.toString();
                if (cod.equals(seleccion)) {
                    this.setSelectedIndex(i);
                    break;
                }
            }
            //return 0;
        }

        private class ModeloComboTallas extends AbstractListModel<Object> implements ComboBoxModel<Object> {
            // <editor-fold defaultstate="collapsed" desc="MODELO DEL COMBOBOX DE TALLAS">

            //private Object[] mis_elementos;
            
            public ModeloComboTallas() {
                //mis_elementos = new Object[]{""};
                mis_elementos = new ArrayList<>();
                mis_elementos.add("");
            }

            public void cambiarItems(Object arrayList_tallas) {
            //mis_elementos = new Object[]{};//limpieza
                //fireContentsChanged(this, 0, 1);

                Iterator it = ((ArrayList) arrayList_tallas).iterator();
                //mis_elementos = new Object[((ArrayList) arrayList_tallas).size()];
                mis_elementos = new ArrayList<>();
                //int i = 0;
                while (it.hasNext()) {
                    //mis_elementos[i] = it.next();
                    mis_elementos.add(it.next());
                    //i++;
                }
                posicion = 0;//esto indica que se posicionará en el primer item
                //fireContentsChanged(this, 0, mis_elementos.length);
                fireContentsChanged(this, 0, mis_elementos.size());
            }

            @Override
            public int getSize() {
                //return mis_elementos.length;
                return mis_elementos.size();
            }

            @Override
            public Object getElementAt(int index) {
                posicion = index;
                return mis_elementos.get(index);
                //return mis_elementos[index];
            }

            @Override
            public void setSelectedItem(Object anItem) {
                mis_elementos.set(posicion, anItem);
                //mis_elementos[posicion] = anItem;
            }

            @Override
            public Object getSelectedItem() {
                return mis_elementos.get(posicion);
                //return mis_elementos[posicion];
            }
// </editor-fold>
        }
    }

    /**
     * @param no_orden
     * @throws java.lang.Exception
     */
    public void editarOrdenProduccion(int no_orden) throws Exception {
        try{
            HashMap<Object[], LinkedHashMap<String, ItemDeLista>> orden_produccion;
            orden_produccion = ConsultaSQL.obtenerListadoDespachoOrden(no_orden);
            
            Object[] detalles_orden = null;
            LinkedHashMap<String, ItemDeLista> lista_despacho = new LinkedHashMap<>();
            //Object lista_despacho = null;
            for (HashMap.Entry reg : orden_produccion.entrySet()) {
                detalles_orden = (Object[])reg.getKey();
                lista_despacho = (LinkedHashMap)reg.getValue();
            }
          
            txt_n_orden_.setText("" + detalles_orden[0]);
            ((ComboBoxItem) combo_ensambladores_).seleccionarItem(detalles_orden[1].toString());
            ((ComboBoxItem) combo_articulos_).seleccionarItem(detalles_orden[2].toString());
            ((ComboTallas) combo_ref_tamaño_).seleccionarItem(detalles_orden[3].toString());
            //((ModeloComboTallas)combo_ref_tamaño_.getModel()).seleccionarItem(detalles_orden[3].toString());
            //combo_ref_tamaño_.setSelectedItem(detalles_orden[3].toString());
            txt_cant_ensamble_.setText(detalles_orden[4].toString());
            
            LinkedHashMap<Object[], ArrayList<ItemDeLista>> stock_db = 
                    ConsultaSQL.obtenerRepuestos_Articulo(detalles_orden[2].toString(), detalles_orden[3].toString());
            
            ((TablaAlistamiento) tabla_alistamiento_).actualizaTabla(stock_db, (int)detalles_orden[4]);
            
            ((TablaAlistamiento) tabla_alistamiento_).actualizaTablaParaEdicion(detalles_orden, lista_despacho);

            //detalle_orden_actual = detalles_orden;
            
            btn_guardar_lista_.setEnabled(true);
            btn_guardar_lista_.setText("Actualizar listado");
            btn_guardar_lista_.setToolTipText("Sobreescribir el listado despachado en la orden.");
            btn_borrar_Orden_Registrada_.setText("Cancelar la Edicion");
            btn_borrar_Orden_Registrada_.setToolTipText("Cancelaras la edicion de la produccion actual.");
            op_boton_guardar_lista = OP_BTN_UPDT;
            btn_alistar_despacho_.setEnabled(false);
            btn_cancelar_despacho_.setEnabled(false);
            btn_refrecar_lista_edicion_.setEnabled(true);
            
        } catch (Exception e) {
            throw new Exception("Problemas al intentar editar la orden.\n" + e.getMessage());
        }
    }

// </editor-fold>
    
    private void btn_guardar_salir_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_salir_ActionPerformed

        // <editor-fold defaultstate="collapsed" desc="CODIGO PARA GUARDAR LA ORDEN ALISTADA Y/O SALIR">
        try {
            int lista = ((TablaAlistamiento) tabla_alistamiento_).getRowCount();
            if (lista > 0) {
                String ensamblador = ((ItemDeLista) combo_ensambladores_.getSelectedItem()).obtenerCodigoId();
                Object[] produccion = new Object[3];
                produccion[0] = ((ItemDeLista) combo_articulos_.getSelectedItem()).obtenerCodigoId();
                produccion[1] = talla_en_cuestion;//combo_ref_tamaño_.getSelectedItem().toString();
                produccion[2] = Integer.parseInt(txt_cant_ensamble_.getText());
                Object[][] listado = ((TablaAlistamiento) tabla_alistamiento_).obtenerListadoDespacho();

                boolean hecho = ConsultaSQL.registrarNuevaOrden(ensamblador, produccion, listado);
                if (hecho) {
                    JOptionPane.showOptionDialog(OrdenProduccion.this,
                            "Orden Guardada Correctamente.",
                            "Orden de Producción", // título del JOptionPane
                            JOptionPane.OK_OPTION, // tipo input
                            JOptionPane.INFORMATION_MESSAGE, // tipo mensaje
                            new ImageIcon(OrdenProduccion.class.getResource("/sources/mis_imagenes/icon_jop_correcto.png")), // icono, si es nulo aparecerá el por defecto
                            new Object[]{"Aceptar"}, //opciones => estos serán los botones
                            new Object[]{});

                    OrdenProduccion.this.dispose();
                    if ((MenuPrincipal.getTabla_actividades()) instanceof TablaProduccion) {
                        ((TablaProduccion) MenuPrincipal.getTabla_actividades()).actualizarTabla();
                    }
                }
            } else {
                int d = JOptionPane.showOptionDialog(OrdenProduccion.this,
                            "No tienes lista de despacho pendiente,\n¿estas seguro que quieres salir?",
                            "Orden de Producción", // título del JOptionPane
                            JOptionPane.YES_NO_OPTION, // tipo input
                            JOptionPane.QUESTION_MESSAGE, // tipo mensaje
                            null,
                            new Object[]{"Salir", "Volver"}, //opciones => estos serán los botones
                            new Object[]{});
                if(d==0){
                    OrdenProduccion.this.dispose();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Guardar Orden", 0);
        }
        // </editor-fold>
        
    }//GEN-LAST:event_btn_guardar_salir_ActionPerformed

    private void btn_cancelar_alistamiento_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_alistamiento_ActionPerformed

        OrdenProduccion.this.dispose();
        
    }//GEN-LAST:event_btn_cancelar_alistamiento_ActionPerformed

    private void btn_refrecar_lista_edicion_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrecar_lista_edicion_ActionPerformed
        try {
            String cod_objeto_ensamble = ((ItemDeLista) combo_articulos_.getSelectedItem()).obtenerCodigoId();
            talla_en_cuestion = combo_ref_tamaño_.getSelectedItem().toString();
            int cantidad = Integer.parseInt(txt_cant_ensamble_.getText());
            LinkedHashMap<Object[], ArrayList<ItemDeLista>>/**/ informacion_bd
                    = ConsultaSQL.obtenerRepuestos_Articulo(cod_objeto_ensamble, talla_en_cuestion);
            ((TablaAlistamiento) tabla_alistamiento_).actualizaTabla(informacion_bd, cantidad);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Refrescar la Lista", 0);
        }
    }//GEN-LAST:event_btn_refrecar_lista_edicion_ActionPerformed

    private void tabla_alistamiento_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_alistamiento_MouseClicked

        //fila_tabla_produccion = tabla_alistamiento_.rowAtPoint(evt.getPoint());
        //col_tabla_produccion = tabla_alistamiento_.columnAtPoint(evt.getPoint());

    }//GEN-LAST:event_tabla_alistamiento_MouseClicked

    private void btn_guardar_lista_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_lista_ActionPerformed
     
        // <editor-fold defaultstate="collapsed" desc="CODIGO QUE REGISTRA Y DESPACHA LA ORDEN EN ALISTAMIENTO Y LLENA LA TABLA INFERIOR">
        try {
            if (op_boton_guardar_lista.equals(OP_BTN_INSERT)) {
                String ensamblador = ((ItemDeLista) combo_ensambladores_.getSelectedItem()).obtenerCodigoId();
                Object[] produccion = new Object[3];
                produccion[0] = ((ItemDeLista) combo_articulos_.getSelectedItem()).obtenerCodigoId();
                produccion[1] = talla_en_cuestion;//combo_ref_tamaño_.getSelectedItem().toString();
                produccion[2] = Integer.parseInt(txt_cant_ensamble_.getText());
                Object[][] listado = ((TablaAlistamiento) tabla_alistamiento_).obtenerListadoDespacho();

                boolean hecho = ConsultaSQL.registrarNuevaOrden(ensamblador, produccion, listado);
                if (hecho) {

                    btn_guardar_lista_.setEnabled(false);
                    ((TablaAlistamiento) tabla_alistamiento_).vaciarTabla();

                    if ((MenuPrincipal.getTabla_actividades()) instanceof TablaProduccion) {
                        ((TablaProduccion) MenuPrincipal.getTabla_actividades()).actualizarTabla();
                    }

                    ((TablaProduccion) tabla_produccion_).actualizarTabla();

                    JOptionPane.showOptionDialog(OrdenProduccion.this,
                            "Orden Guardada Correctamente.",
                            "Orden de Producción", // título del JOptionPane
                            JOptionPane.OK_OPTION, // tipo input
                            JOptionPane.INFORMATION_MESSAGE, // tipo mensaje
                            new ImageIcon(OrdenProduccion.class.getResource("/sources/mis_imagenes/icon_jop_correcto.png")), // icono, si es nulo aparecerá el por defecto
                            new Object[]{"Aceptar"}, //opciones => estos serán los botones
                            new Object[]{});

                    talla_en_cuestion = null;
                    combo_ensambladores_.setEnabled(true);
                    panel_primer_filtro_emsamble_.setEnabled(true);
                    txt_n_orden_.setText("");
                }
            }
            
            if (op_boton_guardar_lista.equals(OP_BTN_UPDT)) {
                
                Object[] produccion = new Object[4];
                produccion[0] = ((ItemDeLista) combo_ensambladores_.getSelectedItem()).obtenerCodigoId();
                produccion[1] = ((ItemDeLista) combo_articulos_.getSelectedItem()).obtenerCodigoId();
                produccion[2] = talla_en_cuestion;//combo_ref_tamaño_.getSelectedItem().toString();
                produccion[3] = Integer.parseInt(txt_cant_ensamble_.getText());
                
                Object[][] listado = ((TablaAlistamiento) tabla_alistamiento_).obtenerListadoDespacho();
                
                int no_orden = Integer.parseInt(txt_n_orden_.getText());
                boolean f = ConsultaSQL.actualizarListadoDespachoOrden(no_orden, produccion, listado);
                if (f) {
                    JOptionPane.showMessageDialog(OrdenProduccion.this, "Orden Actualizada Correctamente",
                            "Modificar Orden", JOptionPane.INFORMATION_MESSAGE);
                    txt_n_orden_.setText("");
                    btn_guardar_lista_.setText("aceptar y alistar otra");
                    btn_guardar_lista_.setToolTipText("Despachar la orden actual y preparar otra");
                    btn_guardar_lista_.setEnabled(false);
                    op_boton_guardar_lista = OP_BTN_INSERT;
                    btn_alistar_despacho_.setEnabled(true);
                    btn_cancelar_despacho_.setEnabled(true);
                    btn_refrecar_lista_edicion_.setEnabled(false);
                    ((TablaAlistamiento) tabla_alistamiento_).vaciarTabla();
                    ((TablaProduccion) tabla_produccion_).actualizarTabla();
                    if ((MenuPrincipal.getTabla_actividades()) instanceof TablaProduccion) {
                        ((TablaProduccion) MenuPrincipal.getTabla_actividades()).actualizarTabla();
                    }
                }
                
                /*Object[] nueva_produccion = new Object[4];
                if (detalle_orden_actual[1] != ensamblador) {
                    /*ensamblador/
                    nueva_produccion[0] = ensamblador;
                }
                else{
                }
                if (detalle_orden_actual[2] != produccion[0]) {
                    /*articulo/
                    nueva_produccion[1] = produccion[0];
                }
                if (detalle_orden_actual[3] != produccion[1]) {
                    /*talla/
                    nueva_produccion[2] = produccion[1];
                }
                if (detalle_orden_actual[4] != produccion[2]) {
                    /*cantidad/
                    nueva_produccion[3] = produccion[2];
                }*/
                //
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Guardar Orden", 0);
        }

// </editor-fold>
        
    }//GEN-LAST:event_btn_guardar_lista_ActionPerformed

    private void btn_borrar_Orden_Registrada_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_borrar_Orden_Registrada_ActionPerformed
       
        // <editor-fold defaultstate="collapsed" desc="CODIGO DE BORRAR ORDENES SELECCIONADAS EN LA TABLA INFERIOR">
        try {
            if (op_boton_guardar_lista.equals(OP_BTN_INSERT)) {
                ArrayList<Integer> cods_filas_selec = new ArrayList<>();
                boolean selec;
                int cod_selec;
                for (int i = 0; i < tabla_produccion_.getRowCount(); i++) {
                    selec = (boolean) (tabla_produccion_.getValueAt(i, 0));
                    if (selec) {
                        cod_selec = (int) tabla_produccion_.getValueAt(i, 1);
                        cods_filas_selec.add(cod_selec);
                    }
                }
                int ok = 0;
                boolean hecho;
                for (Integer cod : cods_filas_selec) {
                    try {
                        hecho = ConsultaSQL.borrarOrdenProduccion(cod);
                        if (hecho) {
                            ok++;
                        } else {
                            throw new Exception("No fue posible eliminar el registro.");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(
                                OrdenProduccion.this,
                                "Problemas para borrar la orden no. " + cod + ".\n" + e.getLocalizedMessage(),
                                "Borrar Ordenes Seleccionadas",
                                0);
                    }
                }
                if (ok > 0) {
                    JOptionPane.showMessageDialog(OrdenProduccion.this, "Los registros seleccionados fueron borrados correctamente.",
                            "Borrar Ordenes Seleccionadas", JOptionPane.INFORMATION_MESSAGE);
                }
                if ((MenuPrincipal.getTabla_actividades()) instanceof TablaProduccion) {
                    ((TablaProduccion) MenuPrincipal.getTabla_actividades()).actualizarTabla();
                }
                ((TablaProduccion) tabla_produccion_).actualizarTabla();
            }
            
            
            if (op_boton_guardar_lista.equals(OP_BTN_UPDT)) {
                txt_n_orden_.setText("");
                btn_borrar_Orden_Registrada_.setText("borrar orden seleccionada");
                btn_borrar_Orden_Registrada_.setToolTipText("Borraras la(s) orden que selecciones en la tabla inferior");
                btn_guardar_lista_.setText("aceptar y alistar otra");
                btn_guardar_lista_.setToolTipText("Despachar la orden actual y preparar otra");
                btn_guardar_lista_.setEnabled(false);
                op_boton_guardar_lista = OP_BTN_INSERT;
                btn_alistar_despacho_.setEnabled(true);
                btn_cancelar_despacho_.setEnabled(true);
                btn_refrecar_lista_edicion_.setEnabled(false);
                ((TablaAlistamiento) tabla_alistamiento_).vaciarTabla();
                //((TablaProduccion) tabla_produccion_).actualizarTabla();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Borrar Orden", 0);
        }
// </editor-fold>
        
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

    private void tabla_produccion_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_produccion_MouseClicked
        
//        fila_tabla_produccion = tabla_produccion_.rowAtPoint(evt.getPoint());
//        col_tabla_produccion = tabla_produccion_.columnAtPoint(evt.getPoint());
        
    }//GEN-LAST:event_tabla_produccion_MouseClicked

    private void btn_alistar_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_alistar_despacho_ActionPerformed
       
        // <editor-fold defaultstate="collapsed" desc="CODIGO DE GENERACION DE ALISTAMIENTO">
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

                    String cod_objeto_ensamble = ((ItemDeLista) combo_articulos_.getSelectedItem()).obtenerCodigoId();
                    Object talla;
                    talla = ((talla = combo_ref_tamaño_.getSelectedItem()) == null) ? null : talla;
                    if(talla != null){
                        talla_en_cuestion = talla.toString();//"talla_en_cuestion" desde el inicio en null
                    }   //asi que si no entra en este if, en la proxima linea se usara como null
                    LinkedHashMap<Object[], ArrayList<ItemDeLista>> informacion_bd 
                            = ConsultaSQL.obtenerRepuestos_Articulo(cod_objeto_ensamble, talla_en_cuestion);
                    if (informacion_bd != null) {
                        int cantidad = Integer.parseInt(txt_cant_ensamble_.getText());
                        ((TablaAlistamiento) tabla_alistamiento_).actualizaTabla(informacion_bd, cantidad);
                        int n_ord_actual = ConsultaSQL.obtenerUltimoNumProduccion();
                        panel_primer_filtro_emsamble_.setEnabled(false);
                        combo_ensambladores_.setEnabled(false);
                        txt_n_orden_.setText("" + (n_ord_actual + 1));
                        btn_guardar_lista_.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Al parecer no hay Componentes vinculados a este Articulo",
                                "Alistar Orden de Producción", 0);
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "Antes, debe especificar la cantidad de Artículos.", "Orden de Producción", 0);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Error en Alistamiento", 0);
        }

// </editor-fold>
        
    }//GEN-LAST:event_btn_alistar_despacho_ActionPerformed

    private void combo_articulos_ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_articulos_ItemStateChanged

        Object itemSelec;
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            itemSelec = evt.getItem();
            txtArea_detalles_.setText(String.valueOf((((ItemDeLista) itemSelec).getAtributos()).get("descripcion")));//lo que se hace aqui es parsear el objeto (el paréntesis con el nombre de la clase parsea el objeto hacia ese tipo o esa clase)
            
            Object items = (((ItemDeLista) itemSelec).getAtributos()).get("tallas");
            ((ComboTallas)combo_ref_tamaño_).mi_modelo_tallas.cambiarItems(items);
            //((ModeloComboTallas)combo_ref_tamaño_.getModel()).cambiarItems(items);
        }
    }//GEN-LAST:event_combo_articulos_ItemStateChanged

    
    public void actualizarDesdeFuera() {
        try {
            ((TablaAlistamiento) tabla_alistamiento_).vaciarTabla();
            ((TablaProduccion) tabla_produccion_).actualizarTabla();
            panel_primer_filtro_emsamble_.setEnabled(true);
            combo_ensambladores_.setEnabled(true);
            txt_n_orden_.setText("");
            btn_refrecar_lista_edicion_.setEnabled(false);
            btn_borrar_Orden_Registrada_.setText("borrar orden seleccionada");
            btn_borrar_Orden_Registrada_.setToolTipText("Borraras la/s orden que seleccione/s en la tabla inferior");
            btn_guardar_lista_.setText("aceptar y alistar otra");
            btn_guardar_lista_.setToolTipText("Despachar la orden actual y preparar otra");
            op_boton_guardar_lista = OP_BTN_INSERT;
            talla_en_cuestion = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Refrescar Ventana", 0);
        }
    }

    private void btn_cancelar_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_despacho_ActionPerformed

        actualizarDesdeFuera();
        
    }//GEN-LAST:event_btn_cancelar_despacho_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alistar_despacho_;
    private javax.swing.JButton btn_borrar_Orden_Registrada_;
    private javax.swing.JButton btn_cancelar_alistamiento_;
    private javax.swing.JButton btn_cancelar_despacho_;
    private javax.swing.JButton btn_guardar_lista_;
    private javax.swing.JButton btn_guardar_salir_;
    private javax.swing.JButton btn_refrecar_lista_edicion_;
    private javax.swing.JComboBox combo_articulos_;
    private javax.swing.JComboBox combo_ensambladores_;
    private javax.swing.JComboBox combo_ref_tamaño_;
    private javax.swing.JLabel lbl_cantidad_ensamble_;
    private javax.swing.JLabel lbl_ensamblador_;
    private javax.swing.JLabel lbl_n_orden_;
    private javax.swing.JLabel lbl_ref_tamaño_;
    private javax.swing.JLabel lbl_tipo_ensamble_;
    private javax.swing.JPanel panel_primer_filtro_emsamble_;
    private javax.swing.JScrollPane scroll_items_selec;
    private javax.swing.JScrollPane scroll_tabla_produccion_;
    private javax.swing.JScrollPane scroll_txtArea_detalles_;
    private javax.swing.JTable tabla_alistamiento_;
    private javax.swing.JTable tabla_produccion_;
    private javax.swing.JTextArea txtArea_detalles_;
    private javax.swing.JTextField txt_cant_ensamble_;
    private javax.swing.JTextField txt_n_orden_;
    // End of variables declaration//GEN-END:variables

}