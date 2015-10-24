
package controller.componentes;

// <editor-fold defaultstate="collapsed" desc="imports">

import model.componentes.ItemDeLista;
import controller.ConsultaSQL;
import view.OrdenProduccion;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;

// </editor-fold>

/***/
public class ComboBoxItem extends JComboBox<ItemDeLista> {

    private transient ArrayList<ItemDeLista> items;
    
    /**
     * Constructor.
     */
    public ComboBoxItem() /*String comboboxthrows Exception*/{
        super();
        //seleccionaComboBox(combobox);
        this.setRenderer(new ORenderItemComboBox());
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**METODO ME PERMITE SELECCIONAR LOS ITEMS QUE LLEVARA EL COMBOBOX.
     * @param nom_combo Se especifica un String que indicará los items del combobox.
     * @throws java.lang.Exception
     */
    public void llenarme(String nom_combo) throws Exception {
        if (nom_combo != null) {
            if(this.getItemCount() > 0){
                this.removeAllItems();
            }
            items = new ArrayList<>();
            if (nom_combo.equals(OrdenProduccion.MODELO_COMBO_ENSAMBLADORES)) {
                //constructor(ConsultaSQL.ConsultorBD.obtenerListaEnsambladores());
                items = ConsultaSQL.obtenerListaEnsambladores();
            }
            if (nom_combo.equals(OrdenProduccion.MODELO_COMBO_ARTICULOS)) {
                //constructor(ConsultaSQL.ConsultorBD.obtenerCatalogoArticulos());
                items = ConsultaSQL.obtenerCatalogoArticulos();
            }
            if (items.size() > 0) {
                Iterator it = items.iterator();
                while (it.hasNext()) {
                    this.addItem((ItemDeLista) it.next());
                }
            } else {
                this.addItem(new ItemDeLista());
                this.setUI(new DibujoCombobox());
                //this.setKeySelectionManager(keySelectionManager);
            }
            this.setSelectedIndex(0);
        } else {
            throw new Exception("el nombre del modelo del ComboBoxItem no es valido, no puede ser nulo");
        }
    }
    
    /**
     * @param codItemDeLista
     */
    public void seleccionarItem(String codItemDeLista) {
        ItemDeLista item;
        String cod;
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            cod = item.obtenerCodigoId();
            if (cod.equals(codItemDeLista)) {
                this.setSelectedIndex(i);
                break;
            }
        }
    }

    /*private void constructor(ArrayList<ItemDeLista> objItems){
     mis_items = /*(ArrayList) /objItems;
     //if (((ArrayList) objItems).size() > 0) {
        if (mis_items.size() > 0) {
            Iterator it = /*((ArrayList) objItems)/mis_items.iterator();
            while (it.hasNext()) {
                this.addItem((ItemDeLista) it.next());
            }
        }
        else{
            this.addItem(new ItemDeLista());
            this.setUI(new DibujoCombobox());
            //this.setKeySelectionManager(keySelectionManager);
        }
        this.setRenderer(new ORenderItemComboBox());
        this.setSelectedIndex(0);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    } */
    
    public static class DibujoCombobox extends BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            return null;
        }
    }

    /**
     * Constructor
     * @param objItems
     * Cuando sus my_items vienen en un ArrayList*/
    /*public ComboBoxItem() {
        super();
        
        constructor();
    }*/
    
    
    // <editor-fold defaultstate="collapsed" desc="MODELO COMBOBOX, NO IMPLEMENTADO">
    /*private class ModeloComboBox extends AbstractListModel<ItemDeLista> implements ComboBoxModel<ItemDeLista> {
        
        private ItemDeLista item_actual;
        //private static int pos_actual;
        
        public ModeloComboBox() {
            my_items = new ArrayList<>();
        }

        public ModeloComboBox(ArrayList<ItemDeLista> itms) {
            my_items = itms;
        }

        @Override
        public int getSize() {
            return my_items.size();
        }

        @Override
        public ItemDeLista getElementAt(int index) {
            return my_items.get(index);
        }

        @Override
        public void setSelectedItem(Object anItem) {
            item_actual = (ItemDeLista)anItem;
        }

        @Override
        public Object getSelectedItem() {
            return item_actual;
        }
        
    }*/// </editor-fold>

    private static class ORenderItemComboBox extends JTextField implements ListCellRenderer<ItemDeLista>{
        // <editor-fold defaultstate="collapsed" desc="RENDERIZADOR (O DIBUJANTE) DE LOS ITEMS Y SUS CARACTERISTICAS VISUALES DENTRO DEL COMBOBOX">
        public ORenderItemComboBox() {
            this.setBorder(null);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ItemDeLista> list, ItemDeLista value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                HashMap<String, Object> attrs = ((ItemDeLista) value).getAtributos();
                setText(String.valueOf(attrs.get(ItemDeLista.TEXTO_MOSTRADO)));
                if(value.obtenerCodigoId() == null){
                    setHorizontalAlignment(JTextField.CENTER);
                }
            }
            return this;
        }
        // </editor-fold>
    }
    
    // <editor-fold defaultstate="collapsed" desc=" IMPLEMENTACION DE COMPONENTE (COMO TEXTFIELD) PARA EDITAR LOS VALORES DE UN ITEM DE COMBOBOX ">
  /*  
    private static class EditorComboBox implements ComboBoxEditor {
        
        private ItemDeLista itcombo;
        private JTextField field;
        
        public EditorComboBox() {
        }
        
        @Override
        public Component getEditorComponent() {
            return null;
        }
        
        @Override
        public void setItem(Object anObject) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public Object getItem() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        @Override
        public void selectAll() {
        }
        
        @Override
        public void addActionListener(ActionListener l) {
        }
        
        @Override
        public void removeActionListener(ActionListener l) {
        }
        
    }*/

// </editor-fold>

}
