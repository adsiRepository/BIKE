
//code

package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author miguel
 */
public class Tiempo {

    private static Calendar calendario;
    
    public static String getStringFechaActual() {
        actualizar();
        return calendario.get(Calendar.DAY_OF_MONTH) + "/" + (calendario.get(Calendar.MONTH) + 1) + "/" + calendario.get(Calendar.YEAR);
    }

    public static Calendar getNow(){
        actualizar();
        return calendario;
    }
    
    /**FECHA FORMATEADA PARA LENGUAJE SQL
     * @return String*/
    public static String obtenerInstanteMySQL(){
        actualizar();
        Date hoy = calendario.getTime();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formato.format(hoy);
    }
    
    /**
     * @param date java.sql.Date
     * @return String
     * @throws java.lang.Exception */
    public static String calendarFechaToString(java.sql.Timestamp date) throws Exception{
        if(date == null){
            return "";
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dt = new java.util.Date(date.getTime());
        String r = formato.format(dt);
        return r;
        //return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
    }
    
    /**
     * @param date
     * @return 
     * @throws java.lang.Exception*/
    public static String calendarHoraToString(java.sql.Timestamp date) throws Exception{
        if(date == null){
            return "";
        }
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        java.util.Date dt = new java.util.Date(date.getTime());
        String r = formato.format(dt);
        return r;
        //return calendar.get(Calendar.HOUR_OF_DAY) +":"+calendar.get(Calendar.MINUTE);
    }
    
    private static void actualizar(){
        calendario = GregorianCalendar.getInstance();
    }
      
    public static void main(String[] args) {
        /*System.out.println(Tiempo.getStringFechaActual());
        System.out.println(Tiempo.getNow().get(Calendar.HOUR));*/
        System.out.println(Tiempo.obtenerInstanteMySQL());
    }
    
    
    
}
