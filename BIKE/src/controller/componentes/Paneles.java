/*
en esta clase es donde se controla los graficos en general de la aplicacion
desde los fondos de los menues hasta los graficos estadisticos y de reportes si los hay.
 */
package controller.componentes;

//<editor-fold defaultstate="collapsed" desc="imports">
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//</editor-fold>

public class Paneles {
    
    public static class Escritorio extends JDesktopPane{
        
        private transient Image fondo_escritorio;//variable de imagen de fondo
        
        public Escritorio(){
            super();  
            try {
                //justo en la siguiente linea, mediante codigo se aplica el fondo del escritorio señalando la ruta, el nombre y la extension del archivo.
                //fondo_escritorio = ImageIO.read(new File("mis_imagenes/fondo_principal.png"));
                fondo_escritorio = new ImageIcon(Escritorio.class.getResource("/sources/mis_imagenes/fondo_principal.png")).getImage();
            } catch (/*IO*/Exception ex) {
                JOptionPane.showMessageDialog(null, "Error "+ex.toString());
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

        /**
         * COSNTRUCTOR REGULAR.  Hereda Exactamente un JInternalFrame
         */
        public VentanaInterna() {
            super();
        }

        /**
         * @param nombre_mi_fondo
         */
        public VentanaInterna(String nombre_mi_fondo) {
            super();
            this.panelImagenFondo = new PanelFondoVentanaInterna(nombre_mi_fondo);
            this.setContentPane(panelImagenFondo);
        }

        /***/
        private static class PanelFondoVentanaInterna extends JPanel {

            private transient Image fondo;

            public PanelFondoVentanaInterna(String nombreImagen) {
                try {
                    //this.fondo = ImageIO.read(new File("mis_imagenes/" + nombreImagen + ".png"));
                    this.fondo = new ImageIcon(VentanaInterna.class.getResource("/sources/mis_imagenes/" + nombreImagen + ".png")).getImage();
                } catch (Exception ex) {
                    this.fondo = new ImageIcon(VentanaInterna.class.getResource("/sources/mis_imagenes/fondo_comun.png")).getImage();
                }
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }

            /*public void setImage(String nombreImagen) {
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
            }*/
        }
    }
 
    /*public static class VentanaEmergente extends JOptionPane implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Paneles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        @Override
        public void setVisible(boolean aFlag) {
            super.setVisible(aFlag); //To change body of generated methods, choose Tools | Templates.
            if(aFlag){
                Thread h = new Thread(new VentanaEmergente());
                h.start();
            }
        }
    }*/
    
    public static class PanelContenedorControles extends JPanel{

        public PanelContenedorControles() {
            super();
        }

        @Override
        public void setEnabled(boolean enabled) {
            super.setEnabled(enabled); 
            habilitarComponentes(this, enabled);
        }

        private void habilitarComponentes(Container contenedor, boolean habilitado){
            Component[] mis_componentes = contenedor.getComponents();
            for (Component componente : mis_componentes) { // para cada componente de mis_componentes
                if(componente instanceof Container){
                    habilitarComponentes((Container)componente, habilitado);
                }
                componente.setEnabled(habilitado);
            }
        }
        
    }
    
}
