package model.componentes;

import java.util.HashMap;

/**
 *
 * @author Miguel
 */
public class ItemOfCollection{
    public static final String TEXTO_A_MOSTRAR = "texto";
  
    private String cod;
    private HashMap<String, String> atributos;

    public ItemOfCollection(String codigoId, HashMap<String, String> atributos) {
        this.cod = codigoId;
        this.atributos = atributos;
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
