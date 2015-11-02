

package model;

//<editor-fold defaultstate="collapsed" desc="imports">
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
//</editor-fold>

public class ModeloReporteProduccion implements JRDataSource{

    private final ArrayList<DetalleProduccion> registros;
    private int indice = -1;
    
    public ModeloReporteProduccion(ArrayList<DetalleProduccion> regs){
        this.registros = regs;
    }
    
    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        switch (jrf.getName()) {
            case "componente":
                return registros.get(indice).getComponente();
            /*case "detalle":
             return registros.get(indice).getDetalle();*/
            case "repuesto":
                return registros.get(indice).getRepuesto();
            case "despachado":
                return registros.get(indice).getDespachado();
            case "stock":
                return registros.get(indice).getStock();
            default:
                return null;
        }
    }
    
    @Override
    public boolean next() throws JRException {
        return ++indice < registros.size();
    }
    
}
