package controller.componentes;

import model.componentes.ItemDeLista;
import controller.ConsultaSQL;
import view.OrdenProduccion;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

public class ComboBoxItem extends JComboBox<ItemDeLista> {

    public boolean i_have_items;
    
    /**
     * Constructor
     * @param combobox
     * constante aplicada en el constructor, dependiente del combobox a construir*/
    public ComboBoxItem(String combobox) {
        super();
        seleccionaComboBox(combobox);
        constructor();
    }

    /**
     * Constructor
     * @param objItems
     * Cuando sus my_items vienen en un ArrayList*/
    public ComboBoxItem(Object objItems) {
        super();
        if (((ArrayList) objItems).size() > 0) {
            Iterator it = ((ArrayList) objItems).iterator();
            while (it.hasNext()) {
                this.addItem((ItemDeLista) it.next());
            }
            i_have_items = true;
        }
        else{
            i_have_items = false;
        }
        constructor();
    }

    private void constructor(){
        this.setRenderer(new RenderItemComboBox());
    } 
    
    public ItemDeLista getMyFirstItem(){
        if(i_have_items){
            return this.getItemAt(0);
        }
        return null;
    }
    
    private void seleccionaComboBox(String combobox){
        if (combobox.equals(OrdenProduccion.COD_CMBOX_ENSAMBLADORES)) {
            ConsultaSQL.ConsultorBD.obtenerListaEnsambladores().entrySet().stream().map((registro) -> new ItemDeLista(registro.getKey(), registro.getValue())).forEach((item) -> {
                this.addItem(item);
            });
        }
        if (combobox.equals(OrdenProduccion.COD_CMBOX_ARTICULOS)) {
            ConsultaSQL.ConsultorBD.obtenerCatalogoArticulos().entrySet().stream().map((registro) -> new ItemDeLista(registro.getKey(), registro.getValue())).forEach((item) -> {
                this.addItem(item);
            });
        }
    }
    
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

    private class RenderItemComboBox extends JTextField implements ListCellRenderer<ItemDeLista>{
        // <editor-fold defaultstate="collapsed" desc="RENDERIZADOR (O DIBUJANTE) DE LOS ITEMS Y SUS CARACTERISTICAS VISUALES DENTRO DEL COMBOBOX">
        public RenderItemComboBox() {
            this.setBorder(null);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ItemDeLista> list, ItemDeLista value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                //HashMap<String, String> attrs = ((ItemDeLista) value).getAtributos();
                HashMap<String, Object> attrs = ((ItemDeLista) value).getAtributos();
                setText(String.valueOf(attrs.get(ItemDeLista.TEXTO_A_MOSTRAR)));
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
