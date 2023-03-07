package sprites.io.UI.canvaspanel;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;


public class Layer extends JPanel {

    private ArrayList<Color[]> undoArray = new ArrayList<>();
    private ArrayList<Color[]> redoArray = new ArrayList<>();

    private Color[] pixels = new Color[2500];
    private boolean visible = true;
    private boolean selected = true;
    private String name;

    /**
     * Create an empty layer
     * @param name name of layer
     */
    public Layer(String name) {
        this.setBounds(0, 0, 500, 500);
        this.name = name;
        // set grid layout of 50x50
        this.setLayout(new GridLayout(50, 50, 0, 0));
    }

    /**
     * Create a Layer with the given pixels
     * @param name name of layer
     * @param pixels layer content
     */
    public Layer(String name, int[] pixels) {
        this.setBounds(0, 0, 500, 500);
        this.visible = false;
        this.name = name;
        // set grid layout of 50x50
        this.setLayout(new GridLayout(50, 50, 0, 0));
        for (int i = 0; i < 2500; i++) {
            if (pixels[i] != 0) {
                this.pixels[i] = new Color(pixels[i]);
            }
        }

    }

    /**
     * Create a Layer with the given pixels and make it current
     * @param name name of layer
     * @param pixels layer content
     * @param isVisible if yes make this visible, the "current" layer
     */
    public Layer(String name, int[] pixels, boolean isVisible) {
        this.setBounds(0, 0, 500, 500);
        this.visible = isVisible;
        this.name = name;
        // set grid layout of 50x50
        this.setLayout(new GridLayout(50, 50, 0, 0));
        for (int i = 0; i < 2500; i++) {
            // 0 is transparent color
            if (pixels[i] != 0) {
                this.pixels[i] = new Color(pixels[i]);
            }
        }

    }

    public void makeTransparentBackground() {
        boolean isOdd = false;
        int lightCol = 220;
        int darkCol = 180;
        Color light = new Color(lightCol, lightCol, lightCol);
        Color dark = new Color(darkCol, darkCol, darkCol);

        for (int i = 0; i < 2500; i++) {
            if (i % 50 == 0) isOdd = !isOdd;

            if (isOdd) {
                if (i % 2 == 0) pixels[i] = light;
                else pixels[i] = dark;
            } else {
                if (i % 2 == 1) pixels[i] = light;
                else pixels[i] = dark;
            }

        }
    }

    public void setPixel(int pixelNumber, Color color) {
        pixels[pixelNumber] = color;
    }

    public Color getPixel(int pixelNumber) {
        return pixels[pixelNumber];
    }

    public Color[] getAllPixels() {
        return pixels;
    }

    public void setAllPixels(Color[] pixels) {
        for (int i = 0; i < 2500; i++) {
            this.pixels[i] = pixels[i];
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getPixelSize() {
        return pixels.length;
    }

    /**
     * Merge the given on top of this layer
     * @param layer this will be merged on top
     */
    public void merge(Layer layer) {
        for (int i = 0; i < pixels.length; i++) {
            if (layer.getPixel(i) != null) {
                pixels[i] = layer.getPixel(i);
            }
        }
        undoArray.clear();
        redoArray.clear();
    }

    public void addToUndoArray() {
        if (undoArray.size() > 9) undoArray.remove(0);

        // create new pixels array
        Color[] newPixels = new Color[2500];
        for (int i = 0; i < 2500; i++) {
            newPixels[i] = this.pixels[i];
        }

        undoArray.add(newPixels);
        redoArray.clear();
    }

    public void undo() {
        if (undoArray.size() == 0) return;

        // pixels from the undo Array
        Color[] newPixels = new Color[2500];
        // current pixels on the layer
        Color[] currPixels =  new Color[2500];
        for (int i = 0; i < 2500; i++) {
            newPixels[i] = this.undoArray.get(undoArray.size()-1)[i];
            currPixels[i] = pixels[i];
        }

        redoArray.add(currPixels);
        setAllPixels(newPixels);
        undoArray.remove(undoArray.size()-1);
    }

    public void redo() {
        if (redoArray.size() == 0) return;

        // pixels from the redo Array
        Color[] newPixels = new Color[2500];
        // current pixels on the layer
        Color[] currPixels =  new Color[2500];
        for (int i = 0; i < 2500; i++) {
            newPixels[i] = this.redoArray.get(redoArray.size()-1)[i];
            currPixels[i] = pixels[i];
        }

        undoArray.add(currPixels);
        setAllPixels(newPixels);
        redoArray.remove(redoArray.size()-1);
    }
}
