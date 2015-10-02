package Modelo;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxRenderer;


public class CellTableComboBox extends JComboBox<ItemComboBox> {
    public static final String TEXTO_MOSTRADO_ITEMCOMBOBOX = "texto";

    /**
     * Constructor
     * @param objItems
     * Recibe el objeto valor del HashMap obtenido en la consulta SQL.
     */
    public CellTableComboBox(Object objItems) {
        super();
        if (((ArrayList) objItems).size() > 0) {
            Iterator it = ((ArrayList) objItems).iterator();
            while (it.hasNext()) {
                this.addItem((ItemComboBox) it.next());
            }
        }
        constructor();
    }

    private void constructor(){
        this.setRenderer(new RenderItemComboBoxNormal());
    } 
    
    // <editor-fold defaultstate="collapsed" desc=" IMPLEMENTACION DE COMPONENTE (COMO TEXTFIELD) PARA EDITAR LOS VALORES DE UN ITEM DE COMBOBOX ">
    
    private static class EditorComboBox implements ComboBoxEditor {
        
        private ItemComboBox itcombo;
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
    
    // <editor-fold defaultstate="collapsed" desc="RENDERIZADOR (O DIBUJANTE) DE LOS ITEMS Y SUS CARACTERISTICAS VISUALES DENTRO DEL COMBOBOX">

    public static class RenderItemComboBoxNormal extends BasicComboBoxRenderer {
        
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null) {
                HashMap<String, String> attrs = ((ItemComboBox) value).getAtributos();
                 setText(attrs.get(CellTableComboBox.TEXTO_MOSTRADO_ITEMCOMBOBOX));
            }
            return this;
        }
    }
    
    
    /*public static class RenderItemComboBoxBusqueda extends BasicComboBoxRenderer {
        
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null) {
                if(index == 0){
                    /*HashMap<String, String> attrs = ((ItemComboBox) value).getAtributos();
                     setText(attrs.get(CellTableComboBox.TEXTO_MOSTRADO_ITEMCOMBOBOX));*
                    ItemComboBox itc = (ItemComboBox) value;
                    itc.setText("");
                }
            }
            
            return (ItemComboBox) value;
            //return this;
        }
    }*/

// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="MODELO COMBOBOX, NO IMPLEMENTADO">
/*@SuppressWarnings("FieldMayBeFinal")
     private class ModeloCombobox extends AbstractListModel<ItemComboBox> implements ComboBoxModel<ItemComboBox> {

     private ItemComboBox item;
     private ItemComboBox[] items;

     public ModeloCombobox() {
     //items = new ItemComboBox[]{new ItemComboBox("A01", "Automovil"), new ItemComboBox("A02", "Motocicleta")};

     }

     public ModeloCombobox(ItemComboBox[] items) {
     this.items = items;
     item = items[0];
     }

     @Override
     public int getSize() {
     return items.length;
     }

     @Override
     public ItemComboBox getElementAt(int index) {
     return items[index];
     }

     @Override
     public void setSelectedItem(Object anItem) {
     item = (ItemComboBox) anItem;
     }

     @Override
     public Object getSelectedItem() {
     return item.getCod();
     }

     /*public void setItems(ItemComboBox[] items) {
     this.items = items;
     }*
     }*/
// </editor-fold>

}
