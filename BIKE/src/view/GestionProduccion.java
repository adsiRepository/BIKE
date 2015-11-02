
package view;

//<editor-fold defaultstate="collapsed" desc="imports">
import controller.ConsultaSQL;
import controller.componentes.ComboBoxItem;
import controller.componentes.ControlProduccion;
import controller.componentes.ControlProduccion.TablaControlProduccion;
import controller.componentes.Paneles;
import controller.reportes.GeneradorReportes;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.componentes.ItemDeLista;
import static view.MenuPrincipal.escritorio;
import static view.OrdenProduccion.MODELO_COMBO_ARTICULOS;
//</editor-fold>

/**
 *
 * @author Miguel
 */
public class GestionProduccion extends Paneles.VentanaInterna {

    private static final String NOMBRE_MI_IMAGEN_FONDO = "fondo_orden_prduccion";
    
    private int fila_tabla;
    
    /**
     * Creates new form Produccion
     */
    public GestionProduccion() {
        super(NOMBRE_MI_IMAGEN_FONDO);
        initComponents();
        this.title = "Inventario de Articulos Producidos";
        this.closable = true;
        this.iconable = true;
        this.resizable = true;
        Dimension ScreenSpace = escritorio.getSize(), mySpc = this.getSize();
        this.setLocation((ScreenSpace.width / 16), ((ScreenSpace.height - mySpc.height) / 15));
        try {
            ((ComboBoxItem) combo_articulos_).llenarme(MODELO_COMBO_ARTICULOS);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Constructor Gestion Produccion", 0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        combo_articulos_ = new controller.componentes.ComboBoxItem();
        jLabel1 = new javax.swing.JLabel();
        txt_cant_producida_ = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        scroll_tabla_control_ = new javax.swing.JScrollPane();
        tabla_control_ = new ControlProduccion.TablaControlProduccion();
        btn_generar_informe_despacho_ = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        combo_tallas_ = new ComboTallas();
        btn_ver_prod_art_ = new javax.swing.JButton();

        combo_articulos_.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_articulos_ItemStateChanged(evt);
            }
        });

        jLabel1.setText("Articulo:");

        txt_cant_producida_.setToolTipText("las ordenes no entregadas no se contarán");

        jLabel2.setText("Cantidad Disponible:");

        tabla_control_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_control_MouseClicked(evt);
            }
        });
        scroll_tabla_control_.setViewportView(tabla_control_);

        btn_generar_informe_despacho_.setText("observar el detalle de despacho de la orden seleccionada");
        btn_generar_informe_despacho_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generar_informe_despacho_ActionPerformed(evt);
            }
        });

        jLabel3.setText("Talla:");

        btn_ver_prod_art_.setText("ver");
        btn_ver_prod_art_.setToolTipText("ver las ordenes despachadas del articulo");
        btn_ver_prod_art_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ver_prod_art_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(scroll_tabla_control_, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(combo_articulos_, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btn_generar_informe_despacho_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(combo_tallas_, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ver_prod_art_)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_cant_producida_, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_articulos_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txt_cant_producida_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(combo_tallas_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btn_ver_prod_art_))
                .addGap(11, 11, 11)
                .addComponent(btn_generar_informe_despacho_)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll_tabla_control_, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static class ComboTallas extends JComboBox<Object> {
        //<editor-fold defaultstate="collapsed" desc="Modelo del Combo de tallas">

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
//</editor-fold>
    }

    private void combo_articulos_ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_articulos_ItemStateChanged
        
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            ItemDeLista itemSelec = (ItemDeLista) evt.getItem();
            Object items = itemSelec.getAtributos().get("tallas");
            ((ComboTallas)combo_tallas_).mi_modelo_tallas.cambiarItems(items);
        }
        
    }//GEN-LAST:event_combo_articulos_ItemStateChanged

    private void btn_ver_prod_art_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ver_prod_art_ActionPerformed
        
        try {
            ItemDeLista articulo = (ItemDeLista) combo_articulos_.getSelectedItem();
            //String talla = combo_tallas_.getSelectedItem().toString();
            Object talla;
            talla = ((talla = combo_tallas_.getSelectedItem()) == null) ? null : talla;
            int cant_producida;
            ArrayList<Object[]> produccion_art;
            if(talla != null){
                cant_producida = ConsultaSQL.totalProducidoArticulo(articulo.obtenerCodigoId(), talla.toString());
                produccion_art = ConsultaSQL.obtenerProduccionDelArticulo(articulo.obtenerCodigoId(), talla.toString());
            }
            else{
                cant_producida = ConsultaSQL.totalProducidoArticulo(articulo.obtenerCodigoId(), null);
                produccion_art = ConsultaSQL.obtenerProduccionDelArticulo(articulo.obtenerCodigoId(), null);
            }
            txt_cant_producida_.setText(""+cant_producida);
            ((TablaControlProduccion) tabla_control_).actualizarTabla(produccion_art);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Ver producciones del Articulo.", 0);
        }
        
    }//GEN-LAST:event_btn_ver_prod_art_ActionPerformed

    private void btn_generar_informe_despacho_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generar_informe_despacho_ActionPerformed
        try {
            Object no_orden;
            no_orden = ((no_orden = tabla_control_.getValueAt(fila_tabla, 0)) == null) ? null : no_orden;
            if (no_orden != null) {
                GeneradorReportes.generarReporteProduccion(Integer.valueOf(no_orden.toString()));
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "No hay ordenes que mostrar", "Ver producciones del Articulo.", 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Ver producciones del Articulo.", 0);
        }
    }//GEN-LAST:event_btn_generar_informe_despacho_ActionPerformed

    private void tabla_control_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_control_MouseClicked
        
        fila_tabla = tabla_control_.rowAtPoint(evt.getPoint());
        
    }//GEN-LAST:event_tabla_control_MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_generar_informe_despacho_;
    private javax.swing.JButton btn_ver_prod_art_;
    private javax.swing.JComboBox combo_articulos_;
    private javax.swing.JComboBox combo_tallas_;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane scroll_tabla_control_;
    private javax.swing.JTable tabla_control_;
    private javax.swing.JTextField txt_cant_producida_;
    // End of variables declaration//GEN-END:variables
}
