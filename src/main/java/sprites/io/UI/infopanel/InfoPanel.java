package sprites.io.UI.infopanel;

import sprites.io.driver.Driver;

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
    private Color color = Color.black;

    public InfoPanel(int x, int y){
        setPreferredSize(new Dimension(128, 128));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBounds(x, y, 128, 64);

        brushSizeLabel = new JLabel("Brush size: " + "small");
        add(brushSizeLabel);

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
    
}
