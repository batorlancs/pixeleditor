package sprites.io.UI.toolpanel;

import sprites.io.driver.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrevColorPanel extends JPanel implements ActionListener {

    private JButton[] buttons = new JButton[8];
    private Driver driver;
    private ToolPanel toolPanel;

    public PrevColorPanel(int posx, int posy, int width, int height, Driver driver, ToolPanel toolPanel) {
        this.toolPanel = toolPanel;
        this.driver = driver;
        this.setBounds(posx, posy, width, height);
        this.setBackground(Color.darkGray);
        this.setLayout(new GridLayout(2, 4));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.darkGray);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }
    }

    public void updatePrevColors() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(driver.getPrevColor(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                driver.setCurrColor(buttons[i].getBackground());
                updatePrevColors();
                break;
            }
        }
    }
}
