package sprites.io.UI.infopanel;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.BoxLayout;


public class InfoPanel extends JPanel {
    private JComponent colorPanel;
    private JLabel brushSizeLabel;
    private JLabel layerLabel1;
    private JLabel layerLabel2;
    private int layersNum = 1;
    private int currentLayer = 1;
    private Color color = Color.black;

    public InfoPanel(int x, int y){
        setPreferredSize(new Dimension(128, 128));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBounds(x, y, 128, 64);

        brushSizeLabel = new JLabel("Brush size: " + "small");
        add(brushSizeLabel);

        layerLabel1 = new JLabel("No of Layers: " + layersNum);
        layerLabel2 = new JLabel("Current Layer: " + currentLayer);
        add(layerLabel1);
        add(layerLabel2);

        JLabel colorLabel = new JLabel("Current color:");
        add(colorLabel);

        colorPanel = new JComponent(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(color);
                g.fillRect(35, 5, 50, 30);
            }
        };

        colorPanel.setPreferredSize(new Dimension(128, 30));
        add(colorPanel);
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setBrushSize(int size){
        switch (size) {
            case 1:
                brushSizeLabel.setText("Brush Size: " + "small");
                break;
            case 2:
                brushSizeLabel.setText("Brush Size: " + "medium");
                break;
            case 3:
                brushSizeLabel.setText("Brush Size: " + "large");
                break;
        }
    }

    public void setLayersNum(int layersNum){
        this.layersNum = layersNum;
        layerLabel1.setText("No of Layers: " + layersNum);
    }

    public void setCurrentLayer(int currentLayer){
        this.currentLayer = currentLayer;
        layerLabel2.setText("Current Layer: " + currentLayer);
    }
    
}
