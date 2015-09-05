//sena 2015 -- miguel gonzalez & ...

package Modelo;

import Vista.MenuPrincipal;

public class ClasePrincipal {
    
    public static String TITULO_DE_LA_APLICACION;//barra de titulo en la ventana pricipal
    public static MenuPrincipal menu;
    
    //metodo principal o inicial de la aplicacion
    public static void main(String[] args) {
        // TODO code application logic here
        TITULO_DE_LA_APLICACION = "BIKEWORK";
        menu = new MenuPrincipal();
        menu.setVisible(true);
    }
    
}
