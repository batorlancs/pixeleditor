package sprites.io.UI.canvaspanel;

import sprites.io.driver.Driver;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Canvas extends JPanel implements MouseListener {

    private ArrayList<Layer> layers;
    private int currentLayer = 0;
    final int pixelNumber = 2500;
    private JLabel[] currentPixels = new JLabel[pixelNumber];
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

        // create the first layer
        layers = new ArrayList<>();
        layers.add(new Layer());
        // add the pixels to the canvas
        for (int i = 0; i < pixelNumber; i++) {
            currentPixels[i] = layers.get(0).getPixel(i);
            this.add(currentPixels[i]);
            currentPixels[i].addMouseListener(this);
        }

    }

    /**
     * Add a new layer to the canvas.
     */
    public Layer addLayer() {
        
        // create a new layer in the list
        layers.add(new Layer());
        currentLayer = layers.size() - 1;

        // remove the current pixels from the canvas
        for (int i = 0; i < pixelNumber; i++) {
            // remove the mouse listener from the old pixel
            currentPixels[i].removeMouseListener(this);
            this.remove(currentPixels[i]);    
        }

        // set the visibility of the previous layer to false
        layers.get(layers.size() - 2).setVisible(false);
        
        // update the canvas with the new layer
        for (int i = 0; i < pixelNumber; i++) {
            currentPixels[i] = layers.get(layers.size() - 1).getPixel(i);
            this.add(currentPixels[i]);
            currentPixels[i].addMouseListener(this);
        }
        
        return layers.get(layers.size() - 1);

    }

    /**
     * Remove the last layer from the canvas.
     */

    public void removeLayer() {
        if (layers.size() > 1) {
            layers.remove(layers.size() - 1);
            
            //get the next visible layer
            int layer = 0;
            for (int i = layers.size() - 1; i >= 0; i--) {
                if (layers.get(i).isVisible()) {
                    layer = i;
                    break;
                }
            }


            // replace the pixels in the canvas with the new layer
            for (int i = 0; i < pixelNumber; i++) {
                currentPixels[i] = layers.get(layer).getPixel(i);
                currentPixels[i].addMouseListener(this);
            }

        }
    }

    /**
     * Get the number of layers in the canvas.
     * @return the number of layers in the canvas.
     */
    public int getLayerNumber() {
        return layers.size();
    }

    /**
     * Get the layer at the specified index.
     * @param index the index of the layer to return.
     * @return the layer at the specified index.
     */
    public Layer getLayer(int index) {
        return layers.get(index);
    }

    public void addDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Clears all pixels on the canvas
     */
    public void clearCanvas() {
        
        // get the first visible layer
        int layer = 0;
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isVisible()) {
                layer = i;
                break;
            }
        }

        // clear the pixels
        for (int i = 0; i < pixelNumber; i++) {
            layers.get(layer).setPixel(i, Color.white);
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
            if (e.getSource() == currentPixels[i]) {
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
            if (e.getSource() == currentPixels[i]) {
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
            if (e.getSource() == currentPixels[i]) {
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
        
        // get the current layer in the canvas
        return this.currentPixels;
    }

    public JLabel getPixel(int number) {

        // get the first visible layer
        int layer = 0;
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isVisible()) {
                layer = i;
                break;
            }
        }

        // get the pixel
        currentPixels[number] = layers.get(layer).getPixel(number);
        return this.currentPixels[number];
    }

    public int getPixelSize() {

        // get the first visible layer
        int layer = 0;
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isVisible()) {
                layer = i;
                break;
            }
        }

        // get the pixel size
        int pixelSize = layers.get(layer).getPixelSize();
        return pixelSize;
    }

    public void updateCanvas(Color[] pixels) {
        
        // get the first visible layer
        int layer = 0;
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isVisible()) {
                layer = i;
                break;
            }
        }

        // update the pixels
        for (int i = 0; i < pixelNumber; i++) {
            this.currentPixels[i] = layers.get(layer).getPixel(i);
            this.currentPixels[i].setBackground(pixels[i]);
        }
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public int getCurrentLayer () {
        return currentLayer;
    }

    public void setCurrentLayer(int layer) {
        currentLayer = layer;

        // remove the mouse listener from the old pixel
        for (int i = 0; i < pixelNumber; i++) {
            currentPixels[i].removeMouseListener(this);
            this.remove(currentPixels[i]);    
        }

        // set the new pixels
        for (int i = 0; i < pixelNumber; i++) {
            currentPixels[i] = layers.get(currentLayer).getPixel(i);
            this.add(currentPixels[i]);
            currentPixels[i].addMouseListener(this);
        }
    }

}
