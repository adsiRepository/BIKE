
//code

package Modelo;

import Modelo.EditorTabla.SpinnCellTable;
import javax.swing.JComboBox;

/**
 *
 * @author Miguel
 */
public class Parametros {
    
    public static class CadenasFrecuentes{
        
    }
    
    public static class Tablas {

        public static final String[] COLUMNAS_TABLA_DESPACHO = new String[]{"Componentes", "Seleccion", "Cantidad"};
        public static final Class[] TIPOS_COLUMNAS_TABLA_DESPACHOS = new Class[]{
            String.class, JComboBox.class, SpinnCellTable.class //esto quiere decir que las celdas de la tabla serán: String, un combo con las partes seleccionables y la cantidad de estas 
        };
    }
    
}
