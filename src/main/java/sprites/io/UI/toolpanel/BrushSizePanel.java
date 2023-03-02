package sprites.io.UI.toolpanel;

import sprites.io.UI.buttonStyles.StyledButton;
import sprites.io.driver.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrushSizePanel extends JPanel implements ActionListener {

    private StyledButton[] buttons = new StyledButton[3];
    private String[] buttonToolTips = {"Small", "Medium", "Large"};
    private Driver driver;
    private ToolPanel toolPanel;

    public BrushSizePanel(int posx, int posy, int width, int height, Driver driver, ToolPanel toolPanel) {
        this.driver = driver;
        this.toolPanel = toolPanel;

        this.setBounds(posx, posy, width, height);
        this.setBackground(Color.darkGray);
        this.setLayout(new GridLayout(1, 3));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new StyledButton("brushSizeIcon.png", (i*5)+15, buttonToolTips[i]);
            buttons[i].setBackground(Color.gray);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }

        setButtonHighlighted(0);
    }

    private void setButtonHighlighted(int index) {
        for (int i = 0; i < buttons.length; i++) {
            if (i == index) {
                buttons[i].setBackground(Color.lightGray);
            } else {
                buttons[i].setBackground(Color.gray);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                driver.setBrushSize(i+1);
                setButtonHighlighted(i);
            }
        }
    }
}
