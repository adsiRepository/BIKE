//code
package controller.componentes;

import model.componentes.ItemDeLista;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author
 */
public class CheckBoxItem extends JCheckBox /*implements ListCellRenderer<ItemOfCollection>*/ {

    private final transient ItemDeLista my_item;

    /**
     * @param item
     */
    public CheckBoxItem(ItemDeLista item) {
        this.my_item = item;
    }

    public ItemDeLista getMyItem() {
        return my_item;
    }

    /*@Override
     public Component getListCellRendererComponent(JList<? extends ItemDeLista> list, ItemDeLista value, int index, boolean isSelected, boolean cellHasFocus) {
     this.setText(value.getAtributos().get(ItemDeLista.TEXTO_MOSTRADO));
     this.setSelected(isSelected);
     this.setBackground(list.getBackground());
     return this;
     }*/
    
    
    public static class ListadoCheckBox extends JList<CheckBoxItem> {

        private ModeloListaArticulos my_data_model;

        /**
         *
         */
        public ListadoCheckBox() {
            //FUENTES
            //http://swing-facil.blogspot.com.co/2012/01/jbutton-jcheckbox-jcombobox-en-jtable.html
            my_data_model = new ModeloListaArticulos();
            this.setModel(my_data_model);
            this.setCellRenderer(new ListCellRenderer<CheckBoxItem>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends CheckBoxItem> list, CheckBoxItem value, int index, boolean isSelected, boolean cellHasFocus) {
                    value.setText(String.valueOf(value.getMyItem().getAtributos().get(ItemDeLista.TEXTO_MOSTRADO)));
                    value.setBackground(isSelected ? getSelectionBackground() : getBackground());
                    value.setForeground(isSelected ? getSelectionForeground() : getForeground());
                    return value;
                }
            });
            
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int index = locationToIndex(e.getPoint());
                    if (index != -1) {
                        CheckBoxItem checkbox = getModel().getElementAt(index);
                        checkbox.setSelected(!checkbox.isSelected());
                        if(checkbox.isSelected()){
                            checkbox.setBackground(getSelectionBackground());
                        }
                        else{
                            checkbox.setBackground(getBackground());
                        }
                        repaint();
                    }
                }
            }
            );
            this.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }

    }

    private static class ModeloListaArticulos extends DefaultListModel<CheckBoxItem> {

        public ModeloListaArticulos() {
            try {
                /*HashMap<String, HashMap<String, Object>> map = ConsultaSQL.ConsultorBD.obtenerCatalogoArticulos();
                map.entrySet().stream().map((reg) -> new ItemDeLista(reg.getKey(), reg.getValue())).forEach((item) -> {
                    this.addElement(new CheckBoxItem(item));
                });*/
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Cons ModeloListaArts", 0);
            }
        }

        @Override
        public int getSize() {
            return super.size();
        }

        @Override
        public CheckBoxItem getElementAt(int index) {
            return super.get(index);
        }
    }

}
