package controller.reportes;

//<editor-fold defaultstate="collapsed" desc="imports">
import controller.ConexionBD;
import controller.ConsultaSQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import model.DetalleProduccion;
import model.ModeloReporteProduccion;
import model.componentes.ItemDeLista;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
//</editor-fold>

/**
 *
 * @author Miguel
 */
public abstract class GeneradorReportes {

    public static void generarReporteProduccion(int no_orden) throws Exception {
        try /*(Connection conn = ConexionBD.obtenerConexion()) */ {

            HashMap<Object[], ArrayList<DetalleProduccion>> detalles
                    = MiConsultaMySQL.obtenerDetallesOrdenProduccion(no_orden);

            Object[] detalles_generales = null;
            ArrayList<DetalleProduccion> despacho = null;
            for (Map.Entry<Object[], ArrayList<DetalleProduccion>> entrada : detalles.entrySet()) {
                detalles_generales = entrada.getKey();
                despacho = entrada.getValue();
            }

            if (detalles_generales != null && despacho != null) {

                Map<String, Object> datos = new HashMap<>();
                datos.put("no_orden", no_orden);
                datos.put("id_empleado", detalles_generales[0].toString());
                datos.put("ensamblador", detalles_generales[1].toString());
                datos.put("articulo", detalles_generales[3].toString());
                int cant = Integer.parseInt(detalles_generales[5].toString());
                datos.put("cantidad", cant);

                ModeloReporteProduccion data_source = new ModeloReporteProduccion(despacho);

                String path = "src/controller/reportes/ReporteProduccion.jasper";
                JasperReport jasper = (JasperReport) JRLoader.loadObjectFromFile(path);
                JasperPrint jasper_print = JasperFillManager.fillReport(jasper, datos, data_source);
                JasperViewer visor = new JasperViewer(jasper_print);
                visor.setTitle("Detalle de la Orden de Producción");
                visor.setVisible(true);
            }

        } catch (SQLException | JRException e) {
            throw new Exception("Error al generar el Reporte de Producción: \n" + e.toString());
        }
    }

    private static class MiConsultaMySQL {
    //<editor-fold defaultstate="collapsed" desc="code">
        
        public static HashMap<Object[], ArrayList<DetalleProduccion>> obtenerDetallesOrdenProduccion(int no_orden) throws Exception {

            try (Connection conn = ConexionBD.obtenerConexion()) {

                try (java.sql.PreparedStatement sentencia = conn.prepareStatement(
                        "select e.id_emp, e.nom_emp, e.ape_emp, a.id_articulo, a.articulo, p.talla, p.cantidad "
                        + "from ordenes_produccion op inner join ensambladores e inner join articulos a "
                        + "inner join produccion p "
                        + "where op.ensamblador = e.id_emp and a.id_articulo = p.articulo "
                        + "and p.no_ord_prod = op.no_ord and op.no_ord = ?;")) {
                    sentencia.setInt(1, no_orden);

                    try (ResultSet resultados = sentencia.executeQuery()) {

                        Object[] detalle_produccion = null; // es null por si no entra al while
                        
                        while (resultados.next()) {
                            detalle_produccion = new Object[6]; // si entra al while, es decir si hay resultados, detalle_produccion ya no sera null
                            detalle_produccion[0] = resultados.getString(1);//id empleado
                            detalle_produccion[1] = resultados.getString(2) + " " + resultados.getString(3);//nombre completo
                            detalle_produccion[2] = resultados.getString(4);//id articulo
                            detalle_produccion[3] = resultados.getString(5);//articulo
                            detalle_produccion[4] = resultados.getString(6);//talla
                            detalle_produccion[5] = resultados.getInt(7);//cantidad
                        }
                        
                        if (detalle_produccion != null && (detalle_produccion.length > 0)) {

                            try (java.sql.PreparedStatement sentencia_b = conn.prepareStatement(
                                    "select c.componente, r.cod_rep, r.repuesto, r.cant_disp, dp.cant_desp "
                                    + "from detalle_despacho dp inner join repuestos r inner join componentes c "
                                    + "where c.id_comp = r.componente and r.cod_rep = dp.repuesto and dp.orden = ? "
                                    + "order by c.familia;")) {
                                sentencia_b.setInt(1, no_orden);

                                try (ResultSet resultados_b = sentencia_b.executeQuery()) {
                                    
                                    //HashMap<String, ItemDeLista> lista_despacho = new HashMap<>();
                                    HashMap<String, Object> atributos_item = new HashMap<>();
                                    
                                    ArrayList<DetalleProduccion> detalle_compo = new ArrayList<>();
                                    
                                    while (resultados_b.next()) {
                                        
                                        atributos_item.put(ItemDeLista.TEXTO_MOSTRADO, resultados_b.getString(3));
                                        atributos_item.put("stock", resultados_b.getInt(4));
                                        atributos_item.put("cant_desp", resultados_b.getInt(5));
                                        //lista_despacho.put(resultados_b.getString(1), new ItemDeLista(resultados_b.getString(2), atributos_item));
                                        
                                        detalle_compo.add(
                                                new model.DetalleProduccion(
                                                        resultados_b.getString(1),
                                                        new ItemDeLista(resultados_b.getString(2), atributos_item)));
                                        atributos_item = new HashMap<>();
                                    }

                                    HashMap<Object[], ArrayList<DetalleProduccion>> retorno = new HashMap<>();
                                    retorno.put(detalle_produccion, detalle_compo);
                                    return retorno;

                                    /*if (lista_despacho.size() > 0) {
                                        
                                     /*Object[][] componentes
                                                = ConsultaSQL.obtenerComponentesDeArticulo((String) detalle_produccion[2]);/
                                        
                                        
                                        
                                        for (Map.Entry<String, ItemDeLista> entrySet : lista_despacho.entrySet()) {
                                            String key = entrySet.getKey();
                                            ItemDeLista value = entrySet.getValue();
                                            
                                        }*/
                                        
                                        
                                        
                                        /*String llave;
                                        ItemDeLista item;
                                        ArrayList<ItemDeLista> items;
                                        for (Object[] componente : componentes) {
                                            items = new ArrayList<>();
                                            for (Map.Entry<String, ItemDeLista> entrada : lista_despacho.entrySet()) {
                                                llave = entrada.getKey();
                                                if (componente[1].toString().equals(llave)) {
                                                    item = entrada.getValue();
                                                    items.add(item);
                                                }
                                            }
                                            detalle_compo.add(new model.DetalleProduccion(componente[2].toString(), items));
                                        }*/
                                        
                                    /*} else {
                                        throw new Exception("No se despacharon repuestos para esta orden de produccion.");
                                    }*/
                                }
                            }
                            
                        } else {
                            throw new Exception("No hay registros de produccion con ese numero de orden.");
                        }
                    }
                    
                }
                
            } catch (SQLException | JRException e) {
                throw new Exception("Error al generar el Reporte de Producción: \n" + e.toString());
            }
        }
//</editor-fold>
    }

    public static void main(String[] args) {
        try {
            ConexionBD.setUsuario("user_storebike");
            ConexionBD.setPassword("user_storebike");
            ConexionBD.setHost("localhost");
            ConexionBD.setPort("3306");
            generarReporteProduccion(1);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    //FUENTES=>
    //https://community.jaspersoft.com/wiki/jasperreports-library-requirements#XLS
}
