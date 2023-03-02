package sprites.io.UI.canvaspanel;

import sprites.io.UI.MainUI;
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
    private MainUI mainUI;

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
    public Canvas(int posx, int posy, int width, int height, MainUI mainUI, int[] pixels) {
        this.mainUI = mainUI;
        this.setBounds(posx, posy, width, height);
        this.setLayout(new GridLayout(50, 50, 0, 0));
        this.setBackground(Color.gray);

        // create the first layer
        layers = new ArrayList<>();
        if (pixels == null)
            layers.add(new Layer("Layer 1"));
        else
            layers.add(new Layer("Layer 1", pixels));
        
        // set the current pixels based on the color of the first layer
        for (int i = 0; i < pixelNumber; i++) {
            currentPixels[i] = new JLabel();
            currentPixels[i].setBackground(layers.get(0).getPixel(i));
            currentPixels[i].setOpaque(true);
            this.add(currentPixels[i]);
            currentPixels[i].addMouseListener(this);
        }

    }

    /**
     * These methods are required for the layer implementation.
     */
    public void addLayer() {
        
        // if there is already a selected layer, deselect it
//        if (layers.get(currentLayer).isSelected()) {
//            layers.get(currentLayer).setSelected(false);
//        }

        // create a new layer in the list
        layers.add(new Layer("Layer " + (layers.size() + 1) + ""));
        currentLayer = layers.size() - 1;

        updateCanvas();
        this.repaint();
    }

    public void removeLayer() {
        
        // temp arraylist to store the layers that are selected
        ArrayList<Layer> temp = new ArrayList<>();
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isSelected()) {
                temp.add(layers.get(i));
            }
        }

        // if the temp array is equal to the layers array, then remove all the layers
        if (temp.size() == layers.size()) {
            layers.clear();
            layers.add(new Layer("Layer 1"));
            currentLayer = 0;
        } else {
            // set the current layer to the first non-selected layer
            for (int i = 0; i < layers.size(); i++) {
                if (!layers.get(i).isSelected()) {
                    currentLayer = i;
                    layers.get(currentLayer).setVisible(true);
                    break;
                }
            }
            
            // remove the selected layers
            for (int i = 0; i < layers.size(); i++) {
                if (layers.get(i).isSelected()) {
                    layers.remove(i);
                    i--;
                }
            }
        }

        temp.clear();

        updateCanvas();
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

        // get the visible layer and replace it with the merged layer
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isVisible()) {
                layers.set(i, mergedLayer);
            }
        }

        // remove the merged layers except the new merged layer
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isSelected()) {
                if (!layers.get(i).isVisible()) {
                    layers.remove(i);
                    i--;
                }
            }
        }

        // set the current layer to the merged layer
        currentLayer = layers.indexOf(mergedLayer);
        updateCanvas();
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

        // set this layer to be selected
        layers.get(currentLayer).setSelected(true);
        updateCanvas();
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
        mainUI.updateLayers();
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

        updateCanvas();
        this.repaint();
        mainUI.updateLayers();
    }
    /**
     * Returns all pixels of the canvas.
     * @return The pixels of the canvas.
     */
    public JLabel[] getPixels() {
        
        // get the current layer in the canvas
        return this.currentPixels;
    }

    public Color getPixel(int number) {

        // get the first visible layer
        int layer = 0;
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isVisible()) {
                layer = i;
                break;
            }
        }

        // get the pixel
        return layers.get(layer).getPixel(number);
    }

    public void setPixel(int number, Color color) {

        // get the first visible layer
        int layer = 0;
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isVisible()) {
                layer = i;
                break;
            }
        }

        // set the pixel
        layers.get(layer).setPixel(number, color);
        updateCanvas();
        this.repaint();
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

    public void updateCanvasArray(Color[] newColors) {
        
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
            layers.get(layer).setPixel(i, newColors[i]);
        }

        updateCanvas();
        this.repaint();
    }

    public void updateCanvas() {
        
        int onlyLayer = 0;
        // get the selected layers
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isSelected()) {
                selectedLayers.add(layers.get(i));
                onlyLayer = i;
            }
        }

        // if there is no layer selected, then get the current layer
        if (selectedLayers.size() == 0) {
            for (int i = 0; i < layers.size(); i++) {
                if (layers.get(i).isVisible()) {
                    selectedLayers.add(layers.get(i));
                    onlyLayer = i;
                    break;
                }
            }
            // set the current layer as selected
            selectedLayers.get(0).setSelected(true);
            // recall this method to update the layers
            updateCanvas();
        } else if (selectedLayers.size() == 1) {

            // if current pixels are null then create them
            if (currentPixels[0] == null) {
                currentPixels = new JLabel[pixelNumber];
                for (int i = 0; i < pixelNumber; i++) {
                    currentPixels[i] = new JLabel();
                    currentPixels[i].setOpaque(true);
                    currentPixels[i].setBackground(Color.white);
                    currentPixels[i].addMouseListener(this);
                    this.add(currentPixels[i]);
                }
            }

            // update the current pixels with the selected layer
            for (int i = 0; i < pixelNumber; i++) {
                currentPixels[i].setBackground(layers.get(onlyLayer).getPixel(i));
            }

        } else { // if there is more than one layer selected, then merge the layers
            // create a new layer with the merged layers
            Layer mergedLayer = new Layer("Merged Layer");
            for (int i = 0; i < selectedLayers.size(); i++) {
                mergedLayer.merge(selectedLayers.get(i));
            }

            // update the current pixels with the merged layer
            for (int i = 0; i < pixelNumber; i++) {
                currentPixels[i].setBackground(mergedLayer.getPixel(i));
            }
        }

        // remove all layers from temp
        selectedLayers.clear();
        this.repaint();

    }

    public void updatePrevColors() {
        mainUI.updatePrevColors();
    }
}
