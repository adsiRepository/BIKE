/*
en esta clase es donde se controla los graficos en general de la aplicacion
desde los fondos de los menues hasta los graficos estadisticos y de reportes si los hay.
 */
package controller.componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*@author bik*/

public class Paneles {
    
    public static class Escritorio extends JDesktopPane{
        
        private transient Image fondo_escritorio;//variable de imagen de fondo
        
        public Escritorio(){
            super();  
            try {
                //justo en la siguiente linea, mediante codigo se aplica el fondo del escritorio señalando la ruta, el nombre y la extension del archivo.
                //fondo_escritorio = new ImageIcon(getClass().getResource("../sources/imgs/fondo_principal.png")).getImage();//hay que tener en cuenta la extension del archivo
                fondo_escritorio = ImageIO.read(new File("mis_imagenes/fondo_principal.png"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
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
            super();
        }
        
        public void plasmarMiFondo(String nombreImagen_sinExtension){
            this.panelImagenFondo = new PanelFondoVentanaInterna(nombreImagen_sinExtension);
            setContentPane(panelImagenFondo);
        }

    }
    
    
    public static class PanelFondoVentanaInterna extends JPanel {

        private transient Image fondo;
        
        /*public PanelFondoVentanaInterna(){
            this.fondo = new ImageIcon(getClass().getResource("../sources/imgs/fondo_ord_prod.png")).getImage();
        }*/
        
        public PanelFondoVentanaInterna(String nombreImagen) {
            try {
                if (nombreImagen != null) {
                    this.fondo = ImageIO.read(new File("mis_imagenes/" + nombreImagen + ".png"));
                } else {
                    this.fondo = ImageIO.read(new File("mis_imagenes/fondo_comun.png"));
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            //super.paint(g);
        }
        
        public void setImage(String nombreImagen) {
            try {
                if (nombreImagen != null) {
                    this.fondo = ImageIO.read(new File("mis_imagenes/" + nombreImagen + ".png"));
                } else {
                    this.fondo = ImageIO.read(new File("mis_imagenes/fondo_comun.png"));
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido cambiar la Imagen de Fondo Error: " + ex.toString());
            }
            repaint();
        }
        
    }
    
}
