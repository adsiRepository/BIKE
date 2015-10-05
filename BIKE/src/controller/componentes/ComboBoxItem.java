package controller.componentes;

import model.componentes.ItemOfCollection;
import controller.ConsultaSQL;
import view.OrdenProduccion;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

public class ComboBoxItem extends JComboBox<ItemOfCollection> {

    /**
     * Constructor
     * @param combobox => constante aplicada en el constructor, dependiente del combobox a construir*/
    public ComboBoxItem(String combobox) {
        super();
        if (combobox.equals(OrdenProduccion.COD_CMBOX_ENSAMBLADORES)) {
            ConsultaSQL.ConsultorBD.obtenerListaEnsambladores().entrySet().stream().map((registro) -> new ItemOfCollection(registro.getKey(), registro.getValue())).forEach((item) -> {
                this.addItem(item);
            });
        }
        if (combobox.equals(OrdenProduccion.COD_CMBOX_ARTICULOS)) {
            ConsultaSQL.ConsultorBD.obtenerCatalogoArticulos().entrySet().stream().map((registro) -> new ItemOfCollection(registro.getKey(), registro.getValue())).forEach((item) -> {
                this.addItem(item);
            });
        }
        constructor();
    }

    /**
     * Constructor
     * @param objItems
     * Recibe el objeto valor del HashMap obtenido en la consulta SQL.
     */
    public ComboBoxItem(Object objItems) {
        super();
        if (((ArrayList) objItems).size() > 0) {
            Iterator it = ((ArrayList) objItems).iterator();
            while (it.hasNext()) {
                this.addItem((ItemOfCollection) it.next());
            }
        }
        constructor();
    }

    private void constructor(){
        this.setRenderer(new RenderItemComboBox());
    } 

    // <editor-fold defaultstate="collapsed" desc="RENDERIZADOR (O DIBUJANTE) DE LOS ITEMS Y SUS CARACTERISTICAS VISUALES DENTRO DEL COMBOBOX">

    public static class RenderItemComboBox extends JTextField implements ListCellRenderer<ItemOfCollection>{
     
        public RenderItemComboBox() {
            this.setBorder(null);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ItemOfCollection> list, ItemOfCollection value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                HashMap<String, String> attrs = ((ItemOfCollection) value).getAtributos();
                setText(attrs.get(ItemOfCollection.TEXTO_A_MOSTRAR));
            }
            return this;
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" IMPLEMENTACION DE COMPONENTE (COMO TEXTFIELD) PARA EDITAR LOS VALORES DE UN ITEM DE COMBOBOX ">
    
    private static class EditorComboBox implements ComboBoxEditor {
        
        private ItemOfCollection itcombo;
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
        
    }

// </editor-fold>

// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="MODELO COMBOBOX, NO IMPLEMENTADO">

    /*public static class ModeloComboBox extends AbstractListModel<ItemOfCollection> implements ComboBoxModel<ItemOfCollection> {

        private static ItemOfCollection item_actual;
        private static int pos_actual;
        private static ArrayList<ItemOfCollection> items;
        
        public ModeloComboBox() {
            items = new ArrayList<>();
            HashMap<String, String> im = new HashMap<>();
            im.put(ItemOfCollection.TEXTO_A_MOSTRAR, "tu madre");
            im.put(ItemOfCollection.TEXTO_A_MOSTRAR, "la tuya");
            items.add(new ItemOfCollection("primer", im));
        }

        public ModeloComboBox(ArrayList<ItemOfCollection> itms) {
            items = itms;
        }
        
        public void añadirElemento(ItemOfCollection item){
            items.add(item);
            fireContentsChanged(this, -1, -1);
        }
        
        @Override
        public int getSize() {
            return items.size();
        }

        @Override
        public ItemOfCollection getElementAt(int index) {
            pos_actual = index;
            item_actual = items.get(pos_actual);
            return item_actual;
        }

        @Override
        public void setSelectedItem(Object anItem) {
        }

        @Override
        public Object getSelectedItem() {
            return item_actual.getCod();
        }

        @Override
        public void addListDataListener(ListDataListener l) {
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
        }

    }*/
// </editor-fold>

}
