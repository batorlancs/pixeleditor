package sprites.io.UI.canvaspanel;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class Layer extends JPanel {
    private JLabel[] pixels = new JLabel[2500];
    private boolean visible = true;
    private boolean selected = true;
    private String name;

    public Layer(String name) {
        this.setBounds(0, 0, 500, 500);
        this.name = name;
        // set grid layout of 50x50
        this.setLayout(new GridLayout(50, 50, 0, 0));
        for (int i = 0; i < 2500; i++) {
            pixels[i] = new JLabel();
            pixels[i].setBackground(Color.white);
            pixels[i].setOpaque(true);
            this.add(pixels[i]);
        }
    }

    public void setPixel(int pixelNumber, Color color) {
        pixels[pixelNumber].setBackground(color);
    }

    public JLabel getPixel(int pixelNumber) {
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

}
