package model;

import java.util.ArrayList;
import java.util.Iterator;
import model.componentes.ItemDeLista;

/**
 *
 * @author Miguel
 */
public class DetalleProduccion {

    private final String componente;
    private final String detalle;

    public DetalleProduccion(String componente, ItemDeLista repuesto) {
        this.componente = componente;
        if (repuesto != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(repuesto.getAtributos().get(ItemDeLista.TEXTO_MOSTRADO));
                sb.append("\t").append(repuesto.getAtributos().get("cant_desp")).append("\t")
                        .append(repuesto.getAtributos().get("stock"));
                sb.append("\n");
            this.detalle = sb.toString();
        } else {
            this.detalle = "";
        }
    }
    
    public DetalleProduccion(String componente, ArrayList<ItemDeLista> lista) {
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
    }

    public String getComponente() {
        return componente;
    }

    public String getDetalle() {
        return detalle;
    }

}
