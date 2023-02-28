package sprites.io.UI.toolpanel;

import sprites.io.UI.buttonStyles.StyledButton;
import sprites.io.driver.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class ToolsPanel extends JPanel implements ActionListener {


    private StyledButton drawButton = new StyledButton("penIcon.png", "");
    private StyledButton eraseButton = new StyledButton("eraserIcon.png", "");
    private StyledButton sizeButton = new StyledButton("SIZE");
    private StyledButton lineButton = new StyledButton("LINE");
    private StyledButton fillButton = new StyledButton("FILL");
    private StyledButton colorButton = new StyledButton("CHANGE COLOR");
    private StyledButton squareButton = new StyledButton("SQUARE");

    private StyledButton undoButton = new StyledButton("UNDO");
    private StyledButton redoButton = new StyledButton("REDO");

    Driver driverRef;

    public ToolsPanel(int posx, int posy, int width, int height, Driver driver) {
        driverRef = driver;

        this.setBackground(Color.darkGray);
        this.setBounds(posx,posy,width,height);
        this.setLayout(new GridLayout(4, 2, 10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        drawButton.addActionListener(this);
        eraseButton.addActionListener(this);
        sizeButton.addActionListener(this);
        fillButton.addActionListener(this);
        lineButton.addActionListener(this);
        fillButton.addActionListener(this);
        colorButton.addActionListener(this);
        squareButton.addActionListener(this);
        undoButton.addActionListener(this);
        redoButton.addActionListener(this);

        this.add(drawButton);
        this.add(eraseButton);
//        this.add(sizeButton);
        this.add(fillButton);
        this.add(lineButton);
        this.add(squareButton);
        this.add(colorButton);
        this.add(undoButton);
        this.add(redoButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == colorButton) {
            Color color = JColorChooser.showDialog(null, "Pick a color", Color.black);
            driverRef.setCurrColor(color);
        }

        if (e.getSource() == eraseButton) {
            driverRef.setCurrToolToEraser();
        }

        if( e.getSource() == drawButton) {
            driverRef.setCurrColor(driverRef.getPrevColor(0));
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

        if(e.getSource() == undoButton) {
            driverRef.undoChange();
        }

        if(e.getSource() == redoButton) {
            driverRef.redoChange();
        }
    }

}
