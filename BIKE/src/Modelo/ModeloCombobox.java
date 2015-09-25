package Modelo;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

@SuppressWarnings("FieldMayBeFinal")
public class ModeloCombobox extends AbstractListModel<ItemComboBox> implements ComboBoxModel<ItemComboBox> {

    private ItemComboBox item;
    private ItemComboBox[] items;

    public ModeloCombobox() {
        items = new ItemComboBox[]{new ItemComboBox("A01", "Automovil"), new ItemComboBox("A02", "Motocicleta")};
        
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
    }*/

    
    

}
