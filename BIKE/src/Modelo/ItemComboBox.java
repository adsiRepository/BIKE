package Modelo;

import static Modelo.CellTableComboBox.TEXTO_MOSTRADO_ITEMCOMBOBOX;
import java.util.HashMap;
import javax.swing.JTextField;

/**
 *
 * @author Miguel
 */
public class ItemComboBox extends JTextField{

    private String cod;
    private HashMap<String, String> atributos;

    public ItemComboBox(String id, HashMap<String, String> atributos) {
        this.cod = id;
        this.atributos = atributos;
    }

    @Override
    public String getText() {
        return atributos.get(TEXTO_MOSTRADO_ITEMCOMBOBOX);
    }

    @Override
    public void setText(String t) {
        super.setText(t); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEditable() {
        return super.isEditable(); //To change body of generated methods, choose Tools | Templates.
    }

    
    // <editor-fold defaultstate="collapsed" desc=" GETTERS n SETTERS ">
    public String getCod() {
        return cod;
    }
    
    public void setCod(String cod) {
        this.cod = cod;
    }
    
    public void setAtributos(HashMap<String, String> atributos) {
        this.atributos = atributos;
    }
    
    public HashMap<String, String> getAtributos() {
        return atributos;
    }

// </editor-fold>

}
