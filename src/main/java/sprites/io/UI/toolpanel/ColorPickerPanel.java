package sprites.io.UI.toolpanel;
import sprites.io.driver.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPickerPanel extends JPanel implements ActionListener {

    private JButton[] buttons = new JButton[12];
    private Color[] colors = {
            Color.black, Color.white, Color.red, Color.green,
            Color.blue, Color.yellow, Color.red, Color.green,
            Color.blue, Color.yellow, Color.red, Color.green
    };
    private Driver driver;

    public ColorPickerPanel(int posx, int posy, int width, int height, Driver driver) {
        this.driver = driver;
        this.setBounds(posx, posy, width, height);
        this.setBackground(Color.darkGray);
        this.setLayout(new GridLayout(3, 4));
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(colors[i]);
            buttons[i].addActionListener(this);
            this.add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                driver.setCurrColor(colors[i]);
            }
        }
    }
}
