package sprites.io.UI.toolpanel;
import sprites.io.driver.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPickerPanel extends JPanel implements ActionListener {

    private JButton[] buttons = new JButton[12];
    private JButton[] prevButtons = new JButton[6];
    private Color[] colors = {Color.blue, Color.yellow, Color.red, Color.green, Color.blue, Color.yellow, Color.red, Color.green, Color.blue, Color.yellow, Color.red, Color.green};
    private Driver driver;

    public ColorPickerPanel(int x, int y, Driver driver) {
        this.driver = driver;
        this.setBounds(x, y, 128, 63);
        this.setBackground(Color.blue);
        this.setLayout(new GridLayout(3, 6));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(colors[i]);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }

        for (int i = 0; i < prevButtons.length; i++) {
            prevButtons[i] = new JButton();
            prevButtons[i].setBackground(Color.black);
            prevButtons[i].addActionListener(this);
            this.add(prevButtons[i]);
        }

    }

    private void updatePrevButtons() {
        for (int i = 0; i < prevButtons.length; i++) {
            prevButtons[i].setBackground(driver.getPrevColor(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                driver.setCurrColor(colors[i]);
                updatePrevButtons();
            }
        }
    }
}
