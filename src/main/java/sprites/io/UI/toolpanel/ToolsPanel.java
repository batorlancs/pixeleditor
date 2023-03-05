package sprites.io.UI.toolpanel;

import sprites.io.UI.buttonStyles.StyledButton;
import sprites.io.driver.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class ToolsPanel extends JPanel implements ActionListener {

    // line button counter to determine which line tool to use
    private int lineButtonCounter = 0;

    private StyledButton drawButton = new StyledButton("penIcon.png", "", "Draw Tool");
    private StyledButton eraseButton = new StyledButton("eraserIcon.png", "", "Eraser Tool");

    private StyledButton fillButton = new StyledButton("fillIcon.png", "", "Fill Tool");
    private StyledButton colorButton = new StyledButton("colorPickerIcon.png", "", "Color Picker Tool");

    private StyledButton lineButton = new StyledButton("lineIcon.png", "", "Line Tool");
    private StyledButton squareButton = new StyledButton("squareIcon.png", "", "Rectangle Shape Tool");

    private StyledButton undoButton = new StyledButton("undoIcon.png", "", "Undo");
    private StyledButton redoButton = new StyledButton("redoIcon.png", "", "Redo");


    Driver driverRef;

    public ToolsPanel(int posx, int posy, int width, int height, Driver driver) {
        driverRef = driver;

        this.setBackground(Color.darkGray);
        this.setBounds(posx,posy,width,height);
        this.setLayout(new GridLayout(4, 2, 10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        drawButton.addActionListener(this);
        eraseButton.addActionListener(this);
        colorButton.addActionListener(this);
        fillButton.addActionListener(this);
        lineButton.addActionListener(this);
        squareButton.addActionListener(this);
        undoButton.addActionListener(this);
        redoButton.addActionListener(this);

        this.add(drawButton);
        this.add(eraseButton);
        this.add(fillButton);
        this.add(colorButton);
        this.add(lineButton);
        this.add(squareButton);
        this.add(undoButton);
        this.add(redoButton);

        setButtonHighlighted(drawButton);
    }

    private void setButtonHighlighted(JButton button) {
        drawButton.setBackground(Color.gray);
        eraseButton.setBackground(Color.gray);
        fillButton.setBackground(Color.gray);
        colorButton.setBackground(Color.gray);
        lineButton.setBackground(Color.gray);
        squareButton.setBackground(Color.gray);
        button.setBackground(Color.lightGray);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        setLineButton(0);

        if (e.getSource() == drawButton) {
            driverRef.setCurrColor(driverRef.getPrevColor(0));
            driverRef.setCurrToolToBrushSize();
            setButtonHighlighted(drawButton);
        }

        if (e.getSource() == eraseButton) {
            driverRef.setCurrToolToEraser();
            setButtonHighlighted(eraseButton);
        }

        if (e.getSource() == fillButton){
            driverRef.setCurrToolToFillTool();
            setButtonHighlighted(fillButton);
        }

        if (e.getSource() == colorButton) {
            driverRef.setCurrToolToColorPicker();
            setButtonHighlighted(colorButton);
        }

        if (e.getSource() == lineButton) {
            lineButtonCounter++;
            if(lineButtonCounter > 2){
                lineButtonCounter = 1;
            }
            if(lineButtonCounter == 1){
                driverRef.setCurrToolToLine(1);
                setLineButton(1);
            }
            else if(lineButtonCounter == 2){
                driverRef.setCurrToolToLine(2);
                setLineButton(2);
            }
            setButtonHighlighted(lineButton);
        }

        if (e.getSource() == squareButton) {
            driverRef.setCurrToolToSquare();
            setButtonHighlighted(squareButton);
        }

        if (e.getSource() == undoButton) {
            driverRef.undoChange();
        }

        if (e.getSource() == redoButton) {
            driverRef.redoChange();
        }
    }

    public void setLineButton(int flag){
        if(flag == 0){
            // set normal line button icon
            lineButton.setNewIcon("lineIcon.png");
        }
        else if(flag == 1){ //if vertical line is selected
            lineButton.setNewIcon("lineVerticalIcon.png");
        }
        else if(flag == 2){ //if horizontal line is selected
            lineButton.setNewIcon("lineHorizontalIcon.png");
        }
    }

}
