/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import RecursosTemporales.ValidUser;

/**
 *
 * @author Miguel
 */
public class ClasePrincipal {
    
    public static String usuario_session;
    public static String rol;
    public static String iduser;
    public static ValidUser start;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        start = new ValidUser();
        
        start.setVisible(true);
        
    }
    
}
