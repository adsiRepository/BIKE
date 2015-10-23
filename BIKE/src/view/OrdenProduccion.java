//code
package view;

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

/**
 *
 */
public class OrdenProduccion extends VentanaInterna {

    /**NOMBRE ARCHIVO IMAGEN DE FONDO PARA ESTA VENTANA. Solo nombre sin extension (obligatorio archivos png)*/
    private static final String NOMBRE_MI_IMAGEN_FONDO = "fondo_orden_produccion";
    /***/
    
    public static final String COD_CMBOX_ENSAMBLADORES = "ensambladores";
    public static final String COD_CMBOX_ARTICULOS = "articulos";

    private int fila_tabla_produccion, col_tabla_produccion;

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
        fila_tabla_produccion = 0;
        col_tabla_produccion = 0;
        
        try {
            ((ComboBoxItem) combo_ensambladores_).seleccionaComboBox(COD_CMBOX_ENSAMBLADORES);
            ((ComboBoxItem) combo_catalogo_ensamble_).seleccionaComboBox(COD_CMBOX_ARTICULOS);
            Object items = (((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).getAtributos()).get("tallas");
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
        btn_esamblador_2_ = new javax.swing.JButton();
        combo_ensambladores_ = new controller.componentes.ComboBoxItem();
        panel_primer_filtro_emsamble_ = new Paneles.PanelContenedorControles();
        combo_catalogo_ensamble_ = new controller.componentes.ComboBoxItem();
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
        btn_guardar_Orden_Alistada_ = new javax.swing.JButton();
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

        lbl_ensamblador_.setText("Ensamblador:");

        btn_retirar_accesorio1.setToolTipText("Despachar Mercancía para Orden Seleccionada");
        btn_retirar_accesorio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_retirar_accesorio1ActionPerformed(evt);
            }
        });

        btn_esamblador_2_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_esamblador_2_ActionPerformed(evt);
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

        btn_guardar_Orden_Alistada_.setText("aceptar y alistar otra");
        btn_guardar_Orden_Alistada_.setToolTipText("Despachar la orden actual y preparar otra");
        btn_guardar_Orden_Alistada_.setEnabled(false);
        btn_guardar_Orden_Alistada_.setMaximumSize(new java.awt.Dimension(167, 23));
        btn_guardar_Orden_Alistada_.setMinimumSize(new java.awt.Dimension(167, 23));
        btn_guardar_Orden_Alistada_.setPreferredSize(new java.awt.Dimension(167, 23));
        btn_guardar_Orden_Alistada_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_Orden_Alistada_ActionPerformed(evt);
            }
        });

        btn_borrar_Orden_Registrada_.setText("cancelar orden seleccionada");
        btn_borrar_Orden_Registrada_.setToolTipText("Borraras la(s) orden que selecciones en la tabla inferior");
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
        lbl_n_orden_.setText("Orden de Producción no.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_ensamblador_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(combo_ensambladores_, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_retirar_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_esamblador_2_, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel_primer_filtro_emsamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_alistar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_cancelar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbl_n_orden_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_n_orden_, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(btn_guardar_Orden_Alistada_, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_borrar_Orden_Registrada_, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(20, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_guardar_salir_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_cancelar_alistamiento_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(59, 59, 59)
                        .addComponent(scroll_tabla_produccion_, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_ensamblador_)
                                .addComponent(combo_ensambladores_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_retirar_accesorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_esamblador_2_, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(scroll_items_selec, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_borrar_Orden_Registrada_, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_guardar_Orden_Alistada_, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scroll_tabla_produccion_, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panel_primer_filtro_emsamble_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(btn_alistar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_cancelar_despacho_, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(63, 63, 63)
                                .addComponent(btn_guardar_salir_, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_cancelar_alistamiento_, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            ((ComboBoxItem) combo_catalogo_ensamble_).seleccionarItem(detalles_orden[2].toString());
            ((ComboTallas)combo_ref_tamaño_).seleccionarItem(detalles_orden[3].toString());
            //((ModeloComboTallas)combo_ref_tamaño_.getModel()).seleccionarItem(detalles_orden[3].toString());
            //combo_ref_tamaño_.setSelectedItem(detalles_orden[3].toString());
            txt_cant_ensamble_.setText(detalles_orden[4].toString());
            
            LinkedHashMap<Object[], ArrayList<ItemDeLista>> stock_db = 
                    ConsultaSQL.obtenerRepuestos_Articulo(detalles_orden[2].toString(), detalles_orden[3].toString());
            
            ((TablaAlistamiento) tabla_alistamiento_).actualizaTabla(stock_db, (int)detalles_orden[4]);
            
           // ((TablaAlistamiento)tabla_alistamiento_).actualizaTablaParaEdicion(detalles_orden, lista_despacho/*lista_despacho*/);

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
                produccion[0] = ((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).obtenerCodigoId();
                produccion[1] = combo_ref_tamaño_.getSelectedItem().toString();
                produccion[2] = Integer.parseInt(txt_cant_ensamble_.getText());
                Object[][] listado = ((TablaAlistamiento) tabla_alistamiento_).obtenerListadoDespacho();

                boolean hecho = ConsultaSQL.registrarNuevaOrden(ensamblador, produccion, listado);
                if (hecho) {
                    JOptionPane.showOptionDialog(OrdenProduccion.this,
                            "Orden Guardada Correctamente.",
                            "Orden de Producción", // título del JOptionPane
                            JOptionPane.OK_OPTION, // tipo input
                            JOptionPane.INFORMATION_MESSAGE, // tipo mensaje
                            new ImageIcon("mis_imagenes/icon_jop_correcto.png"), // icono, si es nulo aparecerá el por defecto
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

    private void btn_retirar_accesorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_retirar_accesorio1ActionPerformed

        JOptionPane.showMessageDialog(null, ((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).obtenerCodigoId());

    }//GEN-LAST:event_btn_retirar_accesorio1ActionPerformed

    private void btn_esamblador_2_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_esamblador_2_ActionPerformed

        /*JOptionPane.showMessageDialog(null, "ancho scroll: " + scroll_items_selec.getWidth() + "\n"
                + "30% = " + ((int) ((scroll_items_selec.getWidth() * 30) / 100)) + "\n"
                + "50% = " + ((int) ((scroll_items_selec.getWidth() * 50) / 100)) + "\n"
                + "20% = " + ((int) ((scroll_items_selec.getWidth() * 20) / 100))
        );*/
        
        //MainClass.getMenuPrincipal().setCol_tabla(5);
        //MainClass.menu.getCol_tabla();
        //MainClass.setUsuario("jorge");
        //int lista = ((TablaAlistamiento) tabla_alistamiento_).getRowCount();
        //JOptionPane.showMessageDialog(OrdenProduccion.this, lista);

    }//GEN-LAST:event_btn_esamblador_2_ActionPerformed

    private void tabla_alistamiento_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_alistamiento_MouseClicked

        fila_tabla_produccion = tabla_alistamiento_.rowAtPoint(evt.getPoint());
        col_tabla_produccion = tabla_alistamiento_.columnAtPoint(evt.getPoint());
        /*if( fila_tabla_produccion > -1 && col_tabla_produccion > -1 ){
         this.obs = dtm.getValueAt(fila, 0);
         txtcodpro.setText(""+obs);
         }*/

    }//GEN-LAST:event_tabla_alistamiento_MouseClicked

    private void btn_guardar_Orden_Alistada_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_Orden_Alistada_ActionPerformed
     
        // <editor-fold defaultstate="collapsed" desc="CODIGO QUE REGISTRA Y DESPACHA LA ORDEN EN ALISTAMIENTO Y LLENA LA TABLA INFERIOR">
        try {
            String ensamblador = ((ItemDeLista) combo_ensambladores_.getSelectedItem()).obtenerCodigoId();
            Object[] produccion = new Object[3];
            produccion[0] = ((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).obtenerCodigoId();
            produccion[1] = combo_ref_tamaño_.getSelectedItem().toString();
            produccion[2] = Integer.parseInt(txt_cant_ensamble_.getText());
            Object[][] listado = ((TablaAlistamiento) tabla_alistamiento_).obtenerListadoDespacho();
            
            boolean hecho = ConsultaSQL.registrarNuevaOrden(ensamblador, produccion, listado);
            if (hecho) {
                
                btn_guardar_Orden_Alistada_.setEnabled(false);
                ((TablaAlistamiento) tabla_alistamiento_).vaciarTabla();
                
                if((MenuPrincipal.getTabla_actividades()) instanceof TablaProduccion){
                   ((TablaProduccion)MenuPrincipal.getTabla_actividades()).actualizarTabla();
                }
                
                ((TablaProduccion)tabla_produccion_).actualizarTabla();
                
                JOptionPane.showOptionDialog(OrdenProduccion.this,
                        "Orden Guardada Correctamente.",
                        "Orden de Producción", // título del JOptionPane
                        JOptionPane.OK_OPTION, // tipo input
                        JOptionPane.INFORMATION_MESSAGE, // tipo mensaje
                        new ImageIcon("mis_imagenes/icon_jop_correcto.png"), // icono, si es nulo aparecerá el por defecto
                        new Object[]{"Aceptar"}, //opciones => estos serán los botones
                        new Object[]{});
                
                combo_ensambladores_.setEnabled(true);
                panel_primer_filtro_emsamble_.setEnabled(true);
                txt_n_orden_.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(OrdenProduccion.this, e.getMessage(), "Guardar Orden", 0);
        }

// </editor-fold>
        
    }//GEN-LAST:event_btn_guardar_Orden_Alistada_ActionPerformed

    private void btn_borrar_Orden_Registrada_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_borrar_Orden_Registrada_ActionPerformed
       
        // <editor-fold defaultstate="collapsed" desc="CODIGO DE BORRAR ORDENES SELECCIONADAS EN LA TABLA INFERIOR">
        try {
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
            
            //ArrayList<Integer> fallidos = new ArrayList<>();
            int ok = 0;
            boolean hecho;
            for(Integer cod : cods_filas_selec){
                try {
                    hecho = ConsultaSQL.borrarOrdenProduccion(cod);
                    if (hecho) {
                        //fallidos.add(cod);
                        ok++;
                    }
                    else{
                        throw new Exception("No fue posible eliminar el registro.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            OrdenProduccion.this,
                            "Problemas para borrar la orden no. " + cod + ".\n" + e.getLocalizedMessage(),
                            "Borrar Ordenes Seleccionadas", 
                            0);
                    //fallidos.add(cod);
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

            /*if (fallidos.size() > 0) {
             StringBuilder z = new StringBuilder();
             for (int i = 0; i < fallidos.size(); i++) {
             if (i == (fallidos.size() - 1)) {
             z.append(fallidos.get(i));
                    } else {
                        z.append(fallidos.get(i)).append(", ");
                    }
                }
                JOptionPane.showMessageDialog(OrdenProduccion.this,
                        "Hubo registros que no se borraron correctamente.\nRegistros sin borrar: " + z.toString(),
                        "Borrar Ordenes Seleccionadas", 0);
            } else {
                JOptionPane.showMessageDialog(OrdenProduccion.this, "Los registros seleccionados fueron borrados correctamente.\n",
                        "Borrar Ordenes Seleccionadas", JOptionPane.INFORMATION_MESSAGE);
            }*/
            
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
        
        fila_tabla_produccion = tabla_produccion_.rowAtPoint(evt.getPoint());
        col_tabla_produccion = tabla_produccion_.columnAtPoint(evt.getPoint());
        
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

                    String cod_objeto_ensamble = ((ItemDeLista) combo_catalogo_ensamble_.getSelectedItem()).obtenerCodigoId();
                    String talla = combo_ref_tamaño_.getSelectedItem().toString();
                    int cantidad = Integer.parseInt(txt_cant_ensamble_.getText());
                    
                    LinkedHashMap<Object[], ArrayList<ItemDeLista>>/**/ informacion_bd 
                            = ConsultaSQL.obtenerRepuestos_Articulo(cod_objeto_ensamble, talla);

                    //if (informacion_bd != null) {
                    panel_primer_filtro_emsamble_.setEnabled(false);
                    combo_ensambladores_.setEnabled(false);
                    ((TablaAlistamiento) tabla_alistamiento_).actualizaTabla(informacion_bd, cantidad);
                    int n_ord_actual = ConsultaSQL.obtenerUltimoNumProduccion();
                    txt_n_orden_.setText("" + (n_ord_actual + 1));
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

// </editor-fold>
        
    }//GEN-LAST:event_btn_alistar_despacho_ActionPerformed

    private void combo_catalogo_ensamble_ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_catalogo_ensamble_ItemStateChanged

        Object itemSelec;
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            itemSelec = evt.getItem();
            txtArea_detalles_.setText(String.valueOf((((ItemDeLista) itemSelec).getAtributos()).get("descripcion")));//lo que se hace aqui es parsear el objeto (el paréntesis con el nombre de la clase parsea el objeto hacia ese tipo o esa clase)
            
            Object items = (((ItemDeLista) itemSelec).getAtributos()).get("tallas");
            ((ComboTallas)combo_ref_tamaño_).mi_modelo_tallas.cambiarItems(items);
            //((ModeloComboTallas)combo_ref_tamaño_.getModel()).cambiarItems(items);
        }

    }//GEN-LAST:event_combo_catalogo_ensamble_ItemStateChanged

    private void btn_cancelar_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_despacho_ActionPerformed

        ((TablaAlistamiento) tabla_alistamiento_).vaciarTabla();
        panel_primer_filtro_emsamble_.setEnabled(true);
        combo_ensambladores_.setEnabled(true);
        txt_n_orden_.setText("");

    }//GEN-LAST:event_btn_cancelar_despacho_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_alistar_despacho_;
    private javax.swing.JButton btn_borrar_Orden_Registrada_;
    private javax.swing.JButton btn_cancelar_alistamiento_;
    private javax.swing.JButton btn_cancelar_despacho_;
    private javax.swing.JButton btn_esamblador_2_;
    private javax.swing.JButton btn_guardar_Orden_Alistada_;
    private javax.swing.JButton btn_guardar_salir_;
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
    private javax.swing.JScrollPane scroll_tabla_produccion_;
    private javax.swing.JScrollPane scroll_txtArea_detalles_;
    private javax.swing.JTable tabla_alistamiento_;
    private javax.swing.JTable tabla_produccion_;
    private javax.swing.JTextArea txtArea_detalles_;
    private javax.swing.JTextField txt_cant_ensamble_;
    private javax.swing.JTextField txt_n_orden_;
    // End of variables declaration//GEN-END:variables

    
}
