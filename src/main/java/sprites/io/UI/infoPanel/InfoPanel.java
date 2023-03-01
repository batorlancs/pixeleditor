package sprites.io.UI.infoPanel;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;


public class InfoPanel extends JPanel {
    private JComponent colorPanel;
    private JPanel currentColorPanel = new JPanel();
    private JLabel brushSizeLabel;
    private Color color = Color.black;

    public InfoPanel(int x, int y){
//        setPreferredSize(new Dimension(128, 128));
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(null);
        this.setBounds(x, y, 128, 64);
        this.setBackground(Color.darkGray);

        brushSizeLabel = new JLabel("Brush Size: " + "Small");
        brushSizeLabel.setForeground(Color.white);
        brushSizeLabel.setBounds(10, 0, 118, 32);
        this.add(brushSizeLabel);


        currentColorPanel.setLayout(new BoxLayout(currentColorPanel, BoxLayout.X_AXIS));
        currentColorPanel.setBackground(Color.darkGray);
        currentColorPanel.setBounds(10, 20, 118, 32);

        JLabel colorLabel = new JLabel("Current Color:");
        colorLabel.setForeground(Color.white);
        currentColorPanel.add(colorLabel);


        colorPanel = new JComponent(){
            @Override
            public void paintComponent(Graphics g){
                g.setColor(color);
                g.fillRect(10, 8, 15, 15);
                g.setColor(Color.lightGray);
                g.drawRect(10, 8, 15, 15);
            }
        };

        currentColorPanel.add(colorPanel);
        this.add(currentColorPanel);
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setBrushSize(int size){
        switch (size) {
            case 1:
                brushSizeLabel.setText("Brush Size: " + "Small");
                break;
            case 2:
                brushSizeLabel.setText("Brush Size: " + "Medium");
                break;
            case 3:
                brushSizeLabel.setText("Brush Size: " + "Large");
                break;
        }
    }

}
