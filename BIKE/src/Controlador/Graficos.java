/*
en esta clase es donde se controla los graficos en general de la aplicacion
desde los fondos de los menues hasta los graficos estadisticos y de reportes si los hay.
 */
package Controlador;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/*@author bik*/

public class Graficos {
    
    public static class Escritorio extends JDesktopPane{
        
        private final Image fondo_escritorio;//variable de imagen de fondo
        
        public Escritorio(){
            super();  
            //justo en la siguiente linea, mediante codigo se aplica el fondo del escritorio señalando la ruta, el nombre y la extension del archivo.
            fondo_escritorio = new ImageIcon(getClass().getResource("../Recursos/imgs/fondo_principal.png")).getImage();//hay que tener en cuenta la extension del archivo
        }
        
        @Override//mediante metodo propio de JDesktopPane
        protected void paintComponent(Graphics grf){
            super.paintComponent(grf);
            //grf.drawImage(fondoEscritorio, (getWidth()/2)-(getWidth()/4) , 80, getWidth()/2, getHeight()-(getHeight()/5), null, this);
            //drawImage = dibujar imagen => espacio desde arriba, desde la izq, tamaño alto, ancho, relativo a.., contexto
            grf.drawImage(fondo_escritorio, 0 , 0, getWidth(), getHeight(), null, this);//-(getHeight()/8)
        }
    }
   
    
    public static class VentanaInterna extends JInternalFrame {
        
        private PanelFondoVentanaInterna pi;
        private Image fondo;
        
        public VentanaInterna(){
            //this.fondo = new ImageIcon(getClass().getResource("../Recursos/imgs/fondo_ord_prod.png")).getImage();
            pi = new PanelFondoVentanaInterna();
            setContentPane(pi);
        }
        
        /*public void setImagenFondo(String nomImage){
            pi.setImage(nomImage);
        }*/

        /*@Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            //super.paint(g); //To change body of generated methods, choose Tools | Templates.
        }*/
     
    }
    
    
    public static class PanelFondoVentanaInterna extends JPanel {

        private Image fondo;
        
        public PanelFondoVentanaInterna(){
            this.fondo = new ImageIcon(getClass().getResource("../Recursos/imgs/fondo_ord_prod.png")).getImage();
        }
        
        public PanelFondoVentanaInterna(String nombreImagen){
            if(nombreImagen != null){
                this.fondo = new ImageIcon(getClass().getResource("../Recursos/imgs/"+nombreImagen+".png")).getImage();
            }
            else{
                this.fondo = new ImageIcon(getClass().getResource("../Recursos/imgs/fondo_ord_prod.png")).getImage();
            }
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            //super.paint(g);
        }
        
        public void setImage(String nombreImagen){
            if(nombreImagen != null){
                this.fondo = new ImageIcon(getClass().getResource("../Recursos/imgs/"+nombreImagen+".png")).getImage();
            }
            else{
                this.fondo = new ImageIcon(getClass().getResource("../Recursos/imgs/fondo_ord_prod.png")).getImage();
            }
        }
        
    }
    
    
}
