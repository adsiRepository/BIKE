
//code

package Controlador;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author miguel
 */
public class Fechas {

    private static Calendar calendario;
    
    public static String getStringFechaActual() {
        actualizar();
        return calendario.get(Calendar.DAY_OF_MONTH) + "/" + (calendario.get(Calendar.MONTH) + 1) + "/" + calendario.get(Calendar.YEAR);
    }

    private static void actualizar(){
        calendario = GregorianCalendar.getInstance();
    }
      
    public static void main(String[] args) {
        System.out.println(Fechas.getStringFechaActual());
    }
    
}
