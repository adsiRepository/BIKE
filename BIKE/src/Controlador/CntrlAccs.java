
                                    /*  SENA BRETAÑA CALI   */


package Controlador;

import java.util.Calendar;

/*
 * creation    : 26/11/2014, 08:52:20 AM
 * author      : Aprendiz
 */

public class CntrlAccs {
    
    String user,pass,role;
    int day,yr,mes,hr,min;
    String fchstr,hrstr;
    
    public CntrlAccs(String user, String pass, String role){
        this.user = user;
        this.pass = pass;
        this.role = role;
    }

    public void captInstant(){
        //Capturar fecha
        Calendar date = Calendar.getInstance();
        yr = date.get(Calendar.YEAR);
        mes = date.get(Calendar.MONTH+1);
        day = date.get(Calendar.DATE);
        this.fchstr = (day+"/"+mes+"/"+yr);
        //Capturar hora
        hr = date.get(Calendar.HOUR_OF_DAY);
        min = date.get(Calendar.MINUTE);
        this.hrstr = (hr+":"+min);
    }
    
    public String getUser(){
        return user;
    }
    
    public String getPass(){
        return pass;
    }
    
    public String getRole(){
        return role;
    }
    
    public String getDate(){
        return fchstr;
    }
    
    public String getHour(){
        return hrstr;
    }
    
}
