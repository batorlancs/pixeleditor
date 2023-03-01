package sprites.io.UI.toolpanel;

import sprites.io.UI.buttonStyles.StyledButton;
import sprites.io.driver.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class ToolsPanel extends JPanel implements ActionListener {


    private StyledButton drawButton = new StyledButton("penIcon.png", "", "Draw Tool");
    private StyledButton eraseButton = new StyledButton("eraserIcon.png", "", "Eraser Tool");

    private StyledButton fillButton = new StyledButton("fillIcon.png", "", "Fill Tool");
    private StyledButton emptyButton = new StyledButton("");

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
        fillButton.addActionListener(this);
        lineButton.addActionListener(this);
        fillButton.addActionListener(this);
        squareButton.addActionListener(this);
        undoButton.addActionListener(this);
        redoButton.addActionListener(this);

        this.add(drawButton);
        this.add(eraseButton);
        this.add(fillButton);
        this.add(emptyButton);
        this.add(lineButton);
        this.add(squareButton);
        this.add(undoButton);
        this.add(redoButton);

        setButtonHighlighted(drawButton);
    }

    private void setButtonHighlighted(JButton button) {
        drawButton.setBackground(Color.gray);
        eraseButton.setBackground(Color.gray);
        lineButton.setBackground(Color.gray);
        squareButton.setBackground(Color.gray);
        fillButton.setBackground(Color.gray);
        button.setBackground(Color.lightGray);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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

        if (e.getSource() == lineButton) {
            driverRef.setCurrToolToSquare();
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

}
