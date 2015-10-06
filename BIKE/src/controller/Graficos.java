/*
en esta clase es donde se controla los graficos en general de la aplicacion
desde los fondos de los menues hasta los graficos estadisticos y de reportes si los hay.
 */
package controller;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*@author bik*/

public class Graficos {
    
    public static class Escritorio extends JDesktopPane{
        
        private final Image fondo_escritorio;//variable de imagen de fondo
        
        public Escritorio(){
            super();  
            //justo en la siguiente linea, mediante codigo se aplica el fondo del escritorio señalando la ruta, el nombre y la extension del archivo.
            fondo_escritorio = new ImageIcon(getClass().getResource("../sources/imgs/fondo_principal.png")).getImage();//hay que tener en cuenta la extension del archivo
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
        
        private transient PanelFondoVentanaInterna panelImagenFondo;
        
        public VentanaInterna(){

        }
        
        public void plasmarMiFondo(String nombreImagen_sinExtension){
            this.panelImagenFondo = new PanelFondoVentanaInterna(nombreImagen_sinExtension);
            setContentPane(panelImagenFondo);
        }

    }
    
    
    public static class PanelFondoVentanaInterna extends JPanel {

        private Image fondo;
        
        public PanelFondoVentanaInterna(){
            this.fondo = new ImageIcon(getClass().getResource("../sources/imgs/fondo_ord_prod.png")).getImage();
        }
        
        public PanelFondoVentanaInterna(String nombreImagen) {
            Image iconoPrograma;
            try {
                if (nombreImagen != null) {
                    iconoPrograma = ImageIO.read(new File("mis_imagenes/" + nombreImagen + ".png"));
                    this.fondo = iconoPrograma;
                } else {
                    iconoPrograma = ImageIO.read(new File("mis_imagenes/default_window_internal.png"));
                    this.fondo = iconoPrograma;
                }
            } catch (IOException ex) {
                
            }
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            //super.paint(g);
        }
        
        public void setImage(String nombreImagen) {
            Image iconoPrograma;
            try {
                if (nombreImagen != null) {
                    iconoPrograma = ImageIO.read(new File("mis_imagenes/" + nombreImagen + ".png"));
                    this.fondo = iconoPrograma;
                } else {
                    iconoPrograma = ImageIO.read(new File("mis_imagenes/default_window_internal.png"));
                    this.fondo = iconoPrograma;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido cambiar la Imagen de Fondo Error: " + ex.toString());
            }
            repaint();
        }
        
    }
    
}
