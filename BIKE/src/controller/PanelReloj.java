
//code

package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author Miguel
 */
public class PanelReloj extends JPanel {
    
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private int millis = 0;

    private static final int espacio_entre_borde = 8;
    //private static final float twoPi = (float) (2.0 * Math.PI);
    private static final float tresPI = (float) (3.0 * Math.PI);
    private static final float radPerSecMin = (float) (Math.PI / 30.0);

    private int size;
    private int centerX;
    private int centerY;
    private transient BufferedImage clockImage;
    private final transient java.util.Timer timer;
    private final transient TimerTask tic_tac;
    private final transient BasicStroke anchoAgujaSeg = new BasicStroke(2);
    private final transient BasicStroke anchoAgujaMin = new BasicStroke(3);
    private final transient BasicStroke anchoAgujaHor = new BasicStroke(5);
    
    /***/
    public PanelReloj() {
        //fuentes temporizadores =>
        //http://foro.chuidiang.com/java-j2se/timer-o-thread/
        timer = new java.util.Timer();
        tic_tac = new TimerTask() {//timer task = tarea programada
            @Override
            public void run() {
                actualizar();
            }
        };
        timer.schedule(tic_tac, 0, 1000); // shedule(TimerTask, retrasoInicial, tiempoPausa)
    }

    private void actualizar() {
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int w = getWidth();
        int h = getHeight();
        size = ((w < h) ? w : h) - 2 * espacio_entre_borde;//http://lineadecodigo.com/java/el-operador-ternario-en-java/ 
        centerX = size / 2 + espacio_entre_borde;
        centerY = size / 2 + espacio_entre_borde;

        if (clockImage == null || clockImage.getWidth() != w || clockImage.getHeight() != h) {
            clockImage = (BufferedImage) (this.createImage(w, h));
            Graphics2D gc = clockImage.createGraphics();
            //indicaciones de graficacion
            gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //metodo que dibuja el reloj
            cara_delReloj(gc);
        }
        
        Graphics2D g2;
    
        if (g instanceof Graphics2D) {
            g2 = (Graphics2D) g;
            //indicaciones de graficacion => 
            //http://swing-facil.blogspot.com.co/2011/12/renderinghints-renderizados-y.html
            //https://docs.oracle.com/javase/tutorial/2d/overview/rendering.html
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            hours = Tiempo.getNow().get(Calendar.HOUR);
            minutes = Tiempo.getNow().get(Calendar.MINUTE);
            seconds = Tiempo.getNow().get(Calendar.SECOND);
            millis = Tiempo.getNow().get(Calendar.MILLISECOND);

            //g2.drawImage(clockImage, null, 0, 0);
            dibujarManecillas(g2);
        }

    }
    
    /***/
    private void dibujarManecillas(Graphics2D g2) {
        int largoSegundero = (size / 2) * 4 / 5; 
        int largoMinutero = largoSegundero * 3 / 4;
        int largoHora = largoSegundero / 2;

        float fseconds = seconds + (float) millis / 1000;
        float secondAngle = tresPI - (radPerSecMin * fseconds);
        float fminutes = (float) (minutes + fseconds / 60.0);
        float minuteAngle = tresPI - (radPerSecMin * fminutes);
        float fhours = (float) (hours + fminutes / 60.0);
        float hourAngle = tresPI - (5 * radPerSecMin * fhours);
        
        g2.setStroke(anchoAgujaHor);
        g2.setColor(new Color(2, 13, 194, 255));
        dibujarLinea(g2, centerX, centerY, hourAngle, 0, largoHora);
        
        g2.setStroke(anchoAgujaMin);
        g2.setColor(new Color(12, 138, 32, 255));
        dibujarLinea(g2, centerX, centerY, minuteAngle, 0, largoMinutero);

        g2.setStroke(anchoAgujaSeg);
        g2.setColor(Color.BLACK);
        dibujarLinea(g2, centerX, centerY, secondAngle, 0, largoSegundero);
        
        //las sgtes 2 lineas dibujan el circulo del centro donde se originan las agujas
        g2.setColor(new Color(167, 32, 23));
        g2.fillOval(centerX - 5, centerY - 5, 10, 10);
        
        numerales(g2);
    }

    /***/
    private void cara_delReloj(Graphics2D g2) {

        g2.setColor(Color.red);
        g2.fillOval(espacio_entre_borde, espacio_entre_borde, size, size);
        g2.setColor(Color.black);
        g2.drawOval(espacio_entre_borde, espacio_entre_borde, size, size);

        for (int sec = 0; sec < 60; sec++) {
            int ticStart;
            if (sec % 5 == 0) {
                ticStart = size / 2 - 10;
            } else {
                ticStart = size / 2 - 5;
            }
            dibujarLinea(g2, centerX, centerY, radPerSecMin * sec, ticStart, size / 2);
        }
    }

    /***/
    private void dibujarLinea(Graphics2D g2, int x, int y, double angle, int minRadius, int maxRadius) {

        float seno = (float) Math.sin(angle);
        float coseno = (float) Math.cos(angle);

        int dxmin = (int) (minRadius * seno);
        int dymin = (int) (minRadius * coseno);

        int dxmax = (int) (maxRadius * seno);
        int dymax = (int) (maxRadius * coseno);

        g2.drawLine(x + dxmin, y + dymin, x + dxmax, y + dymax);

    }
    
    private void numerales(Graphics2D g) {
        Font font = new Font("Arial", Font.BOLD, 16);
        g.setColor(new Color(212, 157, 98, 255));
        g.setFont(font);
        g.drawString("12", getWidth() / 2 - 8, getHeight() / 8 + 6);
        g.drawString("1", (getWidth() / 2 + getWidth() / 4) - 16, getHeight() / 5);
        g.drawString("2", (getWidth() - (getWidth() / 6)) - 6, getHeight() / 4 + 18);
        g.drawString("3", getWidth() - (getWidth() / 8) - 2, getHeight() / 2 + 6);
        g.drawString("4", (getWidth() - (getWidth() / 6)) - 6, getHeight() - (getHeight() / 4) - 8);
        g.drawString("5", (getWidth() / 2 + getWidth() / 4) - 16, getHeight() - (getHeight() / 5) + 10);
        g.drawString("6", getWidth() / 2 - 4, getHeight() - (getHeight() / 8) + 6);
        g.drawString("7", getWidth() / 4 + 8, getHeight() - (getHeight() / 5) + 10);
        g.drawString("8", getWidth() / 6, getHeight() - (getHeight() / 4) - 8);
        g.drawString("9", getWidth() / 8 - 2 , getHeight() / 2 + 6);
        g.drawString("10", getWidth() / 6, getHeight() / 4 + 18);
        g.drawString("11", getWidth() / 4 + 10, getHeight() / 5);
    }

    /*private static class PruebaReloj extends JFrame {

        PanelReloj clockFace;

        public PruebaReloj() {
            Container content = this.getContentPane();
            content.setLayout(new BorderLayout());
            clockFace = new PanelReloj();
            content.add(clockFace, BorderLayout.CENTER);
            content.setBounds(0, 0, 270, 270);
            this.setTitle("Analog Clock");
            this.pack();

            //clockFace.start();
        }

    }

    public static void main(String[] args) {
            JFrame windo = new PruebaReloj();
            windo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            windo.setVisible(true);
    }*/
    
}
