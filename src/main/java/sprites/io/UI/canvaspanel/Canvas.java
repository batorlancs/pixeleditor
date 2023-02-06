package sprites.io.UI.canvaspanel;

import sprites.io.driver.Driver;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Canvas extends JPanel implements MouseListener {

    final int pixelNumber = 2500;
    private JLabel[] pixels = new JLabel[pixelNumber];
    private Driver driver;

    /**
     * Create Canvas with 50x50 pixels.
     * @param posx Position X of canvas.
     * @param posy Position Y of canvas.
     * @param width Width of canvas.
     * @param height Height of canvas.
     */
    public Canvas(int posx, int posy, int width, int height) {
        this.setBounds(posx, posy, width, height);
        this.setLayout(new GridLayout(50, 50, 0, 0));
        this.setBackground(Color.gray);

        // set and add each pixel
        for (int i = 0; i < pixelNumber; i++) {
            pixels[i] = new JLabel();
            pixels[i].setBackground(Color.white);
            pixels[i].setOpaque(true);
            pixels[i].addMouseListener(this);
            this.add(pixels[i]);
        }

        this.addMouseListener(this);

    }

    public void addDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Clears all pixels on the canvas
     */
    public void clearCanvas() {
        for (JLabel pixel: pixels) {
            pixel.setBackground(Color.white);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Color the pixel and save that mouse has been pressed.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

        driver.setMousePressed(true);

        for (int i = 0; i < pixelNumber; i++) {
            if (e.getSource() == pixels[i]) {
                driver.setMousePressLocation(i);
            }
        }

        driver.draw();
    }

    /**
     * Save that mouse is not pressed.
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        driver.setMousePressed(false);

        for (int i = 0; i < pixelNumber; i++) {
            if (e.getSource() == pixels[i]) {
                driver.setMousePressLocation(i);
            }
        }

        driver.release();
    }

    /**
     * If mouse entered pixel and mouse is currently pressed, then colour the pixel.
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        for (int i = 0; i < pixelNumber; i++) {
            if (e.getSource() == pixels[i]) {
                driver.setMouseCurrentLocation(i);
            }
        }
        driver.draw();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Returns all pixels of the canvas.
     * @return The pixels of the canvas.
     */
    public JLabel[] getPixels() {
        return this.pixels;
    }

    public JLabel getPixel(int number) {
        return this.pixels[number];
    }

    public int getPixelSize() {
        return pixelNumber;
    }

}
