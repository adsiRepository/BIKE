package model;

import model.componentes.ItemDeLista;

/**
 *
 * @author Miguel
 */
public class DetalleProduccion {

    private final String componente;
    private final String repuesto;
    private final int despachado;
    private final int stock;
    
    //private final String detalle;

    public DetalleProduccion(String componente, ItemDeLista repto) {
        this.componente = componente;
        if (repto != null) {
            this.repuesto = repto.getAtributos().get(ItemDeLista.TEXTO_MOSTRADO).toString();
            this.despachado = (int) repto.getAtributos().get("cant_desp");
            this.stock = (int) repto.getAtributos().get("stock");
        } else {
            this.repuesto = "";
            this.despachado = 0;
            this.stock = 0;
        }
    }
    
    /*public DetalleProduccion(String componente, ArrayList<ItemDeLista> lista) {
        this.componente = componente;
        if (lista != null && lista.size() > 0) {
            Iterator it = lista.iterator();
            StringBuilder sb = new StringBuilder();
            int indice_rep = 0;
            ItemDeLista item;
            while (it.hasNext()) {
                indice_rep++;
                item = (ItemDeLista) it.next();
                sb.append(indice_rep).append(" ").append(item.getAtributos().get(ItemDeLista.TEXTO_MOSTRADO));
                sb.append("\t").append(item.getAtributos().get("cant_desp")).append("\t")
                        .append(item.getAtributos().get("stock"));
                sb.append("\n");
            }
            this.detalle = sb.toString();
        } else {
            this.detalle = "";
        }
    }*/

    public String getComponente() {
        return componente;
    }
    
    public String getRepuesto() {
        return repuesto;
    }

    public int getDespachado() {
        return despachado;
    }

    public int getStock() {
        return stock;
    }

    /*public String getDetalle() {
        return detalle;
    }*/
}
