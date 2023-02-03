package sprites.io.UI.toolpanel;

import sprites.io.driver.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel implements ActionListener {


    private JButton drawButton = new JButton("DRAW");
    private JButton eraseButton = new JButton("ERASER");
    private JButton sizeButton = new JButton("SIZE");
    private JButton lineButton = new JButton("LINE");
    private JButton fillButton = new JButton("FILL");
    private JButton colorButton = new JButton("CHANGE COLOR");

    private JButton squareButton = new JButton("SQUARE");

    Driver driverRef;
    private Color prevColor = Color.black;

    public ToolPanel(int posx, int posy, int width, int height, Driver driver) {
        driverRef = driver;

        this.setBackground(Color.red);
        this.setBounds(0,64,128,512);
        this.setLayout(new GridLayout(8, 2));

        drawButton.addActionListener(this);
        eraseButton.addActionListener(this);
        sizeButton.addActionListener(this);
        fillButton.addActionListener(this);
        lineButton.addActionListener(this);
        colorButton.addActionListener(this);
        squareButton.addActionListener(this);

        this.add(drawButton);
        this.add(eraseButton);
        this.add(sizeButton);
        this.add(fillButton);
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
            colorButton.setBackground(color);
        }

        if (e.getSource() == eraseButton) {
            driverRef.setCurrToolToEraser();
        }

        if( e.getSource() == drawButton) {
            driverRef.setCurrColor(this.prevColor);
            driverRef.setCurrToolToBrushSize();
        }

        if(e.getSource() == sizeButton){

            // pop up dialog box to ask for the size
            Object[] options = {"small", "medium", "large"};
            int n = JOptionPane.showOptionDialog(null,
                    "Choose a size",
                    "Size",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);

            // set the size, and set the tool to brush
            driverRef.setBrushSize(n+1);
            driverRef.setCurrToolToBrushSize();
        }

        if(e.getSource() == fillButton){
            driverRef.setCurrToolToFillTool();
        }
        if( e.getSource() == squareButton) {
            driverRef.setCurrToolToSquare();
        }
        if( e.getSource() == squareButton) {
            driverRef.setCurrToolToSquare();
        }
    }

}
