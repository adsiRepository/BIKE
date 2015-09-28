package Modelo;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author Miguel
 */
public class ItemComboBox {

    private String cod;
    private String nombre;
    private String desc;

    public ItemComboBox(String id, String desc) {
        this.cod = id;
        this.desc = desc;
    }

    public String getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    

    public static class RenderItemComboBox extends BasicComboBoxRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value != null) {
                setText(((ItemComboBox) value).getDesc());
            }

            return this;
        }
    }
    
    public static class RenderItemComboBox2 extends BasicComboBoxEditor {

    }
    
}
