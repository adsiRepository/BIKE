
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
        actualizarFecha();
        return calendario.get(Calendar.DAY_OF_MONTH) + "/" + (calendario.get(Calendar.MONTH) + 1) + "/" + calendario.get(Calendar.YEAR);
    }

    public static Calendar getNow(){
        actualizarFecha();
        return calendario;
    }
    
    /**FECHA FORMATEADA PARA LENGUAJE SQL
     * @return String*/
    public static String obtenerInstanteMySQL(){
        actualizarFecha();
        Date hoy = calendario.getTime();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formato.format(hoy);
    }
 
    /**
     * RETORNA UN STRING CON LA FECHA OBTENIDA DE LA CONSULTA SQL
     *
     * @param date java.sql.Date
     * @return String
     * @throws java.lang.Exception
     */
    public static String calendarFechaToString(java.sql.Timestamp date) throws Exception {
        //fuentes formatos => http://www-01.ibm.com/support/knowledgecenter/SSHEB3_3.3.2/com.ibm.tap.doc_3.3.2/loc_topics/c_custom_date_formats.html?lang=es
        if (date == null) {
            return "Sin entregar";
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MMM/yyyy");
        java.util.Date dt = new java.util.Date(date.getTime());
        String r = formato.format(dt);
        return r;
        //return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
    }

    /**
     * METODO PARA CASTEAR LA FECHA OBTENIDA DEL JDateChooser o control para seleccionar la fecha 
     * a String válido para la base de datos.
     * 
     * @param date
     * @return 
     * @throws java.lang.Exception*/
    public static String ingresarFechaSQL(Date date) throws Exception {
        //fuentes formatos => http://www-01.ibm.com/support/knowledgecenter/SSHEB3_3.3.2/com.ibm.tap.doc_3.3.2/loc_topics/c_custom_date_formats.html?lang=es
        if (date == null) {
            actualizarFecha();
            Date hoy = calendario.getTime();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.format(hoy);
        }
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String r = formato.format(date);
        return r;
        //return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
    }
    
    
    /**
     * RETORNA UN STRING CON LA HORA OBTENIDA DE LA CONSULTA SQL
     *
     * @param date
     * @return
     * @throws java.lang.Exception
     */
    public static String calendarHoraToString(java.sql.Timestamp date) throws Exception{
        if(date == null){
            return "";
        }
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss a");
        java.util.Date dt = new java.util.Date(date.getTime());
        String r = formato.format(dt);
        return r;
        //return calendar.get(Calendar.HOUR_OF_DAY) +":"+calendar.get(Calendar.MINUTE);
    }
    
    
    /**
     * METODO PARA CASTEAR LA FECHA OBTENIDA DE LA BASE DATOS ADECUADAMENTE PARA LA APLICACION.
     * 
     * @param date
     * @return 
     * @throws java.lang.Exception*/
    public static String fechaObtenidaSQL(java.sql.Date date) throws Exception {
        //fuentes formatos => http://www-01.ibm.com/support/knowledgecenter/SSHEB3_3.3.2/com.ibm.tap.doc_3.3.2/loc_topics/c_custom_date_formats.html?lang=es
        if (date == null) {
            return "";
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MMM/yyyy");
        String r = formato.format(date);
        return r;
        //return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
    }
    
    private static void actualizarFecha(){
        calendario = GregorianCalendar.getInstance();
    }
      
    public static void main(String[] args) {
        /*System.out.println(Tiempo.getStringFechaActual());
        System.out.println(Tiempo.getNow().get(Calendar.HOUR));*/
        System.out.println(Tiempo.obtenerInstanteMySQL());
    }
}