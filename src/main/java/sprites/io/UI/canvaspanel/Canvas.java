package sprites.io.UI.canvaspanel;

import sprites.io.driver.Driver;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Canvas extends JPanel implements MouseListener {

    // variables for the canvas
    final int pixelNumber = 2500;
    private JLabel[] currentPixels = new JLabel[pixelNumber];
    private Driver driver;

    // variables for layers
    private ArrayList<Layer> layers;
    private int currentLayer = 0;
    private ArrayList<Layer> selectedLayers = new ArrayList<>();

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
        layers.add(new Layer("Layer 1"));
        
        // add the pixels to the canvas
        for (int i = 0; i < pixelNumber; i++) {
            currentPixels[i] = layers.get(0).getPixel(i);
            this.add(currentPixels[i]);
            currentPixels[i].addMouseListener(this);
        }

    }

    /**
     * These methods are required for the layer implementation.
     */
    public void addLayer() {
        
        // if there is already a selected layer, deselect it
        if (layers.get(currentLayer).isSelected()) {
            layers.get(currentLayer).setSelected(false);
        }

        // create a new layer in the list
        layers.add(new Layer("Layer " + (layers.size() + 1) + ""));
        currentLayer = layers.size() - 1;

        // remove the current pixels from the canvas
        for (int i = 0; i < pixelNumber; i++) {
            // remove the mouse listener from the old pixel
            currentPixels[i].removeMouseListener(this);
            this.remove(currentPixels[i]);    
        }

        
        // update the canvas with the new layer
        for (int i = 0; i < pixelNumber; i++) {
            currentPixels[i] = layers.get(layers.size() - 1).getPixel(i);
            this.add(currentPixels[i]);
            currentPixels[i].addMouseListener(this);
        }

    }

    public void removeLayer() {
        // remove all the selected layers
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isSelected()) {
                layers.remove(i);
                i--;
            }
        }

        // if there are no layers, create a new one
        if (layers.size() == 0) {
            layers.add(new Layer("Layer 1"));
        }

        // set the current layer to the last layer
        currentLayer = layers.size() - 1;

        // remove the current pixels from the canvas
        for (int i = 0; i < pixelNumber; i++) {
            // remove the mouse listener from the old pixel
            currentPixels[i].removeMouseListener(this);
            this.remove(currentPixels[i]);    
        }

        // update the canvas with the new layer
        for (int i = 0; i < pixelNumber; i++) {
            currentPixels[i] = layers.get(layers.size() - 1).getPixel(i);
            this.add(currentPixels[i]);
            currentPixels[i].addMouseListener(this);
        }

        this.repaint();
    }

    public void mergeLayers() {
        // merge all the selected layers
        ArrayList<Layer> temp = new ArrayList<>();
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isSelected()) {
                temp.add(layers.get(i));
            }
        }
        
        // create a new layer with the merged layers
        Layer mergedLayer = new Layer("Merged Layer");
        for (int i = 0; i < temp.size(); i++) {
            mergedLayer.merge(temp.get(i));
        }

        // remove the merged layers
        for (int i = 0; i < temp.size(); i++) {
            layers.remove(temp.get(i));
        }

        // get the visible layer and replace it with the merged layer
        // TODO:
        // add the merged layer to the list
        layers.add(mergedLayer);
        this.setCurrentLayer(layers.size() - 1);
        this.repaint();

    }

    public int getLayerNumber() {
        return layers.size();
    }

    public Layer getLayer(int index) {
        return layers.get(index);
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public int getCurrentLayer () {
        return currentLayer;
    }

    public void setCurrentLayer(int layer) {
        currentLayer = layer;
        // set the current layer to be visible
        for (int i = 0; i < layers.size(); i++) {
            if (i == currentLayer) {
                layers.get(i).setVisible(true);
            } else {
                layers.get(i).setVisible(false);
            }
        }

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

        this.repaint();
    }

    /*
     * End of layer methods
     */


    /*
     * These methods are required for the mouse listener implementation.
     */

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

    /*
     * End of mouse listener methods
     */
    
    /**
     * Canvas methods
     */

     public void addDriver(Driver driver) {
        this.driver = driver;
    }

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

    public void updateLayers() {
        
        int onlyLayer = 0;
        // merge all the selected layers
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isSelected()) {
                selectedLayers.add(layers.get(i));
                onlyLayer = i;
            }
        }

        // if there is only one layer selected, then do nothing
        if (selectedLayers.size()  < 2) {
           
            // remove the mouse listener from the old pixel
            for (int i = 0; i < pixelNumber; i++) {
                currentPixels[i].removeMouseListener(this);
                this.remove(currentPixels[i]);    
            }

            // set the new pixels with the selected layer
            for (int i = 0; i < pixelNumber; i++) {
                currentPixels[i] = layers.get(onlyLayer).getPixel(i);
                this.add(currentPixels[i]);
                currentPixels[i].addMouseListener(this);
            }

            this.repaint();
            return;

        } else {
            // create a new layer with the merged layers
            Layer mergedLayer = new Layer("Merged Layer");
            for (int i = 0; i < selectedLayers.size(); i++) {
                mergedLayer.merge(selectedLayers.get(i));
            }

            // get the index of the visible layer
            int index = 0;
            for (int i = 0; i < layers.size(); i++) {
                if (layers.get(i).isVisible()) {
                    index = i;
                    break;
                }
            }

            // store all the pixels of the merged layer
            

            // remove the current pixel from the canvas
            for (int i = 0; i < pixelNumber; i++) {
                currentPixels[i].removeMouseListener(this);
                this.remove(currentPixels[i]);
            }

            // set the new pixels
            for (int i = 0; i < pixelNumber; i++) {
                currentPixels[i] = mergedLayer.getPixel(i);
                this.add(currentPixels[i]);
                currentPixels[i].addMouseListener(this);
            }

            // replace the visible layer with the merged layer
            //layers.set(index, mergedLayer);

            // set the visible layer to the merged layer
            //layers.get(index).setVisible(true);

            // repaint the canvas
            this.repaint();

        }

        // remove all layers from temp
        selectedLayers.clear();

    }
}
