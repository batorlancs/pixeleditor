package sprites.io.UI.toolpanel;

import sprites.io.driver.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel implements ActionListener {

    private Color prevColor = Color.BLACK;

    private JButton drawButton = new JButton("DRAW");
    private JButton eraseButton = new JButton("ERASER");
    private JButton sizeButton = new JButton("SIZE");
    private JButton lineButton = new JButton("LINE");
    private JButton colorButton = new JButton("CHANGE COLOR");

    private JButton squareButton = new JButton("SQUARE");

    Driver driverRef;

    public ToolPanel(int posx, int posy, int width, int height, Driver driver) {
        driverRef = driver;

        this.setBackground(Color.red);
        this.setBounds(0,64,128,512);
        this.setLayout(new GridLayout(8, 2));

        drawButton.addActionListener(this);
        eraseButton.addActionListener(this);
        sizeButton.addActionListener(this);
        lineButton.addActionListener(this);
        colorButton.addActionListener(this);
        squareButton.addActionListener(this);

        this.add(drawButton);
        this.add(eraseButton);
        this.add(sizeButton);
        this.add(lineButton);
        this.add(colorButton);
        this.add(squareButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == colorButton) {
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "Pick a color", Color.black);
            driverRef.setCurrColor(color);
            this.prevColor = color;
        }

        if (e.getSource() == eraseButton) {
            driverRef.setCurrToolToEraser();
        }

        if( e.getSource() == drawButton) {
            driverRef.setCurrToolToPen();
        }
        if( e.getSource() == squareButton) {
            driverRef.setCurrToolToSquare();
        }
    }
}
