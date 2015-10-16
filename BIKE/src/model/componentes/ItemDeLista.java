package model.componentes;

import java.util.HashMap;

/**
 *
 * @author Miguel
 */
public class ItemDeLista {
    public static final String TEXTO_MOSTRADO = "texto";
  
    private String cod;
    private HashMap<String, Object> atributos;
    
    /**CONSTRUCTOR. ITEM SIN DATOS PREDEFINIDOS (VACIO)*/
    public ItemDeLista(){
        cod = null;
        atributos = new HashMap<>();
        atributos.put(TEXTO_MOSTRADO, "\t----");
        atributos.put("stock", 0);
    }

    public ItemDeLista(String codigoId, HashMap<String, Object> atributos) {
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
    
    public void setAtributos(HashMap<String, Object> atributos) {
        this.atributos = atributos;
    }
    
    public HashMap<String, Object> getAtributos() {
        return atributos;
    }

// </editor-fold>

}
