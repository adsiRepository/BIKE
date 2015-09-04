/*
en esta clase es donde se controla los graficos en general de la aplicacion
desde los fondos de los menues hasta los graficos estadisticos y de reportes si los hay.
 */
package Controlador;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/*@author bik*/

public class Graficos {
    
    public class Escritorio extends JDesktopPane{
        
        private final Image fondo_escritorio;//variable de imagen de fondo
        
        public Escritorio(){
            super();  
            //justo en la siguiente linea, mediante codigo se aplica el fondo del escritorio señalando la ruta, el nombre y la extension del archivo.
            fondo_escritorio = new ImageIcon(getClass().getResource("../Recursos/Imagenes/fondo_escritorio.png")).getImage();
        }
        
        @Override//mediante metodo propio de JDesktopPane
        protected void paintComponent(Graphics grf){
            super.paintComponent(grf);
            //grf.drawImage(fondoEscritorio, (getWidth()/2)-(getWidth()/4) , 80, getWidth()/2, getHeight()-(getHeight()/5), null, this);
            //drawImage = dibujar imagen => espacio desde arriba, desde la izq, tamaño alto, ancho, relativo a.., contexto
            grf.drawImage(fondo_escritorio, 0 , 0, getWidth(), getHeight(), null, this);//-(getHeight()/8)
        }
    }
    
}
