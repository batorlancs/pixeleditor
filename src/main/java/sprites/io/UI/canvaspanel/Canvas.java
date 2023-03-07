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
     * Create the canvas
     * @param posx position x of the canvas
     * @param posy position y of the canvas
     * @param width width of the canvas
     * @param height height of the canvas
     * @param mainUI reference of the main ui
     * @param fileLayers all the layers to be displayed (if opened from file)
     */
    public Canvas(int posx, int posy, int width, int height, MainUI mainUI, ArrayList<Layer> fileLayers) {
        this.mainUI = mainUI;
        this.setBounds(posx, posy, width, height);
        this.setLayout(new GridLayout(50, 50, 0, 0));
        this.setBackground(Color.gray);
        this.addMouseListener(this);

        // create the first layer
        layers = new ArrayList<>();


        // if you opened a project display all the layers, if not just create blank layer
        if (fileLayers == null)
            layers.add(new Layer("Layer 1"));
        else {
            layers.addAll(fileLayers);
        }

        // set the current pixels based on the color of the first layer
        for (int i = 0; i < pixelNumber; i++) {
            currentPixels[i] = new JLabel();
            Color currentPixelBackground = layers.get(0).getPixel(i);
            if (currentPixelBackground == null) {
                currentPixelBackground = Color.white;
            }
            currentPixels[i].setBackground(currentPixelBackground);
            currentPixels[i].setOpaque(true);
            this.add(currentPixels[i]);
            currentPixels[i].addMouseListener(this);
        }

        updateCanvas();
        this.repaint();

    }

    /**
     * These methods are required for the layer implementation.
     */
    public void addLayer() {

        // create a new layer in the list
        layers.add(new Layer("Layer " + (layers.size() + 1) + ""));
        currentLayer = layers.size() - 1;

        updateCanvas();
        this.repaint();
    }

    public void removeLayer() {
        boolean isCurrentOnSelectedLayer = false;

        // temp arraylist to store the layers that are selected
        ArrayList<Layer> temp = new ArrayList<>();
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isSelected()) {
                if (layers.get(i).isVisible()) isCurrentOnSelectedLayer = true;
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
            if (isCurrentOnSelectedLayer) {
                for (int i = 0; i < layers.size(); i++) {
                    if (!layers.get(i).isSelected()) {
                        currentLayer = i;
                        layers.get(currentLayer).setVisible(true);
                        break;
                    }
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
        mainUI.updateLayers();
        updateCurrentLayer();
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
        Layer mergedLayer = new Layer("Merging Layer");
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

    public void moveLayerUp(Layer layer) {
        int layerPos = 0;
        // search for specific layer
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i) == layer) {
                layerPos = i;
                break;
            }
        }
        // check bounds
        if (layerPos < 1) return;
        // move layer up
        Layer tempLayer = layers.get(layerPos - 1);
        layers.set(layerPos - 1, layer);
        layers.set(layerPos, tempLayer);

        updateCurrentLayer();
        updateCanvas();
        this.repaint();
    }

    public void moveLayerDown(Layer layer) {
        int layerPos = 0;
        // search for specific layer
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i) == layer) {
                layerPos = i;
                break;
            }
        }
        // check bounds
        if (layerPos >= layers.size()-1) return;
        // move layer up
        Layer tempLayer = layers.get(layerPos + 1);
        layers.set(layerPos + 1, layer);
        layers.set(layerPos, tempLayer);

        updateCurrentLayer();
        updateCanvas();
        this.repaint();
    }

    public void updateCurrentLayer() {
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isVisible()) {
                currentLayer = i;
                break;
            }
        }
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

    public int getCurrentLayerNumber () {
        return currentLayer;
    }

    public Layer getCurrentLayer() {
        return layers.get(currentLayer);
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

        // add current pixels to the undo array in layer
        if (!driver.isMousePressed() && !driver.isCurrToolColorPicker()) {
            this.getCurrentLayer().addToUndoArray();
        }

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
        mainUI.setCursorToCurrent();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mainUI.setCursorToDefault();
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

        layers.get(layer).addToUndoArray();

        // clear the pixels
        for (int i = 0; i < pixelNumber; i++) {
            layers.get(layer).setPixel(i, null);
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

    public boolean[] getTransparentPixels() {
        boolean[] result = new boolean[2500];
        // fill everything with true
        for (int i = 0; i < 2500; i++) {
            result[i] = true;
        }

        // get the selected layers
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isSelected()) {
                selectedLayers.add(layers.get(i));
            }
        }

        // iterate through each layer to find non-transparent pixels
        for (int i = 0; i < selectedLayers.size(); i++) {
            Layer tempLayer = selectedLayers.get(i);
            for (int j = 0; j < 2500; j++) {
                if (tempLayer.getPixel(j) != null) {
                    result[j] = false;
                }
            }
        }

        selectedLayers.clear();
        return result;
    }

    public Color getCurrentPixel(int number) {
        return this.currentPixels[number].getBackground();
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
        selectedLayers.clear();

        // get the selected layers
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).isSelected()) {
                selectedLayers.add(layers.get(i));
            }
        }

        // if there is no layer selected, then get the current layer
        if (selectedLayers.size() == 0) {
            for (int i = 0; i < layers.size(); i++) {
                if (layers.get(i).isVisible()) {
                    selectedLayers.add(layers.get(i));
                    break;
                }
            }
            // set the current layer as selected
            selectedLayers.get(0).setSelected(true);
            // recall this method to update the layers
            updateCanvas();

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
                if (selectedLayers.size() > 0)
                    currentPixels[i].setBackground(selectedLayers.get(0).getPixel(i));
            }

        } else { // if there is more than one layer selected, then merge the layers
            // create a new layer with the merged layers
            Layer mergedLayer = new Layer("Merged Layer");
            mergedLayer.makeTransparentBackground();

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
