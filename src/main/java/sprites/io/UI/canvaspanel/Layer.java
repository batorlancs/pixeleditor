package sprites.io.UI.canvaspanel;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class Layer extends JPanel {
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

    public void setPixel(int pixelNumber, Color color) {
        pixels[pixelNumber] = color;
    }

    public Color getPixel(int pixelNumber) {
        return pixels[pixelNumber];
    }

    public Color[] getAllPixels() {
        return pixels;
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
            } else if (layer.getPixel(i) == null && pixels[i] == null) {
                pixels[i] = Color.white;
            }
        }
    }
}
