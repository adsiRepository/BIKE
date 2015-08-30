/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Fechas {
    
    /*
    
    Referencias Bibliograficas:
    
                                    https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
    
   */
    
    String fchn, hrn;
    String fchsql, hrsql;
    
    
    public Fechas(){
        //Instanciamos el objeto Calendar
        //en fchn obtenemos la fchn y hrn del sistema
        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del año, mes, día,
        //hora, minuto y segundo del sistema
        //usando el método get y el parámetro correspondiente
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        this.fchn = ""+ año + "-" + (mes+1) + "-" + dia;
        this.hrn = ""+hora+":"+fecha.get(Calendar.MINUTE)+":"+segundo;//Hora Actual: %02d:%02d:%02d %n
    }
    
    public String getHour(){
        return hrn;
    }
    
    public String getDate(){
        return fchn;
    }
    
    
    public String getTmSql(){
        Date date = new Date();
        /*Calendar fgreg = new GregorianCalendar();
        fgreg.setTime(date);*/
        SimpleDateFormat fhr = new SimpleDateFormat("HH:mm:ss");
        this.fchsql = fhr.format(date);
        return hrsql;
    }
    
    
    
    public String getDtSql(){
        Date date = new Date();
       /* Calendar fgreg = new GregorianCalendar();
      fgreg.setTime(date);*/
        SimpleDateFormat fdt = new SimpleDateFormat("yyyy-MM-dd");
        this.hrsql = fdt.format(date);
        return fchsql;
    }
    
      
}
