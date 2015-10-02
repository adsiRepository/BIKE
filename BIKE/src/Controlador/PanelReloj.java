
//code

package Controlador;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Miguel
 */
public class PanelReloj extends JPanel{
    
       private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private int millis = 0;

    private static final int espacio_entre_borde = 8;
    private static final float twoPi = (float) (2.0 * Math.PI);
    private static final float threePi = (float) (3.0 * Math.PI);
    private static final float radPerSecMin = (float) (Math.PI / 30.0);

    private int size;
    private int centerX;
    private int centerY;
    private BufferedImage clockImage;
    private final javax.swing.Timer t;

    public PanelReloj() {
        //this.setPreferredSize(new Dimension(210, 210));
        //this.setBackground(Color.BLACK);
        //this.setForeground(Color.black);

        t = new javax.swing.Timer(1000,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        update();
                    }
                });
    }

    public void update() {
        this.repaint();
    }

    public void start() {
        t.start();
    }

    public void stop() {
        t.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //indicaciones de graficacion
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        size = ((w < h) ? w : h) - 2 * espacio_entre_borde; 
        centerX = size / 2 + espacio_entre_borde;
        centerY = size / 2 + espacio_entre_borde;

        if (clockImage == null || clockImage.getWidth() != w || clockImage.getHeight() != h) {
            clockImage = (BufferedImage) (this.createImage(w, h));
            Graphics2D gc = clockImage.createGraphics();
            gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            cara_delReloj(gc);
        }

        Calendar now = Calendar.getInstance();
        hours = now.get(Calendar.HOUR);
        minutes = now.get(Calendar.MINUTE);
        seconds = now.get(Calendar.SECOND);
        millis = now.get(Calendar.MILLISECOND);

        g2.drawImage(clockImage, null, 0, 0);

        dibujarManecillas(g2);

    }

    private void dibujarManecillas(Graphics2D g) {
        int secondRadius = size / 2 - 25;
        int minuteRadius = secondRadius * 3 / 4;
        int hourRadius = secondRadius / 2;

        g.setStroke(new BasicStroke(2));
        float fseconds = seconds + (float) millis / 1000;
        float secondAngle = threePi - (radPerSecMin * fseconds);
        drawRadius(g, centerX, centerY, secondAngle, 0, secondRadius);

        g.setStroke(new BasicStroke(3));
        float fminutes = (float) (minutes + fseconds / 60.0);
        float minuteAngle = threePi - (radPerSecMin * fminutes);
        drawRadius(g, centerX, centerY, minuteAngle, 0, minuteRadius);

        g.setStroke(new BasicStroke(5));
        float fhours = (float) (hours + fminutes / 60.0);
        float hourAngle = threePi - (5 * radPerSecMin * fhours);
        drawRadius(g, centerX, centerY, hourAngle, 0, hourRadius);

        numerales(g);
    }

    /**
     */
    private void cara_delReloj(Graphics2D g) {

        g.setColor(Color.red);
        //g.drawImage(clockImage, size, size, this);
        g.fillOval(espacio_entre_borde, espacio_entre_borde, size, size);
        g.setColor(Color.black);
        g.drawOval(espacio_entre_borde, espacio_entre_borde, size, size);

        for (int sec = 0; sec < 60; sec++) {
            int ticStart;
            if (sec % 5 == 0) {
                ticStart = size / 2 - 10;
            } else {
                ticStart = size / 2 - 5;
            }
            drawRadius(g, centerX, centerY, radPerSecMin * sec, ticStart, size / 2);
        }
    }

    private void drawRadius(Graphics g, int x, int y, double angle, int minRadius, int maxRadius) {

        float seno = (float) Math.sin(angle);
        float coseno = (float) Math.cos(angle);

        int dxmin = (int) (minRadius * seno);
        int dymin = (int) (minRadius * coseno);

        int dxmax = (int) (maxRadius * seno);
        int dymax = (int) (maxRadius * coseno);

        g.drawLine(x + dxmin, y + dymin, x + dxmax, y + dymax);

    }

    
    private void numerales(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 16);
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

    
    
    private static class PruebaReloj extends JFrame {

        PanelReloj clockFace;

        public PruebaReloj() {
            Container content = this.getContentPane();
            content.setLayout(new BorderLayout());
            clockFace = new PanelReloj();
            content.add(clockFace, BorderLayout.CENTER);

            this.setTitle("Analog Clock");
            this.pack();

            clockFace.start();
        }

    }

    public static void main(String[] args) {
            JFrame windo = new PruebaReloj();
            windo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            windo.setVisible(true);
    }
    
}
