package sprites.io.UI.canvaspanel;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class Layer extends JPanel {
    private Color[] pixels = new Color[2500];
    private boolean visible = true;
    private boolean selected = true;
    private String name;

    public Layer(String name) {
        this.setBounds(0, 0, 500, 500);
        this.name = name;
        // set grid layout of 50x50
        this.setLayout(new GridLayout(50, 50, 0, 0));
        for (int i = 0; i < 2500; i++) {
            pixels[i] = Color.WHITE;
        }
    }

    public void setPixel(int pixelNumber, Color color) {
        pixels[pixelNumber] = color;
    }

    public Color getPixel(int pixelNumber) {
        return pixels[pixelNumber];
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

    public Layer getNameLabel() {
        /* 
        // if name label already exists, remove it
        if (this.getComponentCount() > 0) {
            this.remove(0);
        }
        
        // get the name of the layer
        JLabel nameLabel = new JLabel(name);
        nameLabel.setBounds(0, 0, 500, 20);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.white);
        this.add(nameLabel, 0);
        return this;
    }
        */
        return this;
    }

    public void merge(Layer layer) {
        for (int i = 0; i < pixels.length; i++) {
            if (layer.getPixel(i) != Color.white && pixels[i] == Color.white) {
                pixels[i] = layer.getPixel(i);
            } else if (layer.getPixel(i) != Color.white && pixels[i] != Color.white) {
                // merge the colors
                int red = (pixels[i].getRed() + layer.getPixel(i).getRed()) / 2;
                int green = (pixels[i].getGreen() + layer.getPixel(i).getGreen()) / 2;
                int blue = (pixels[i].getBlue() + layer.getPixel(i).getBlue()) / 2;
                pixels[i] = new Color(red, green, blue);
            }
        }
    }
}
