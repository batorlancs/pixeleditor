package sprites.io;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Canvas extends JPanel implements MouseListener {

    final int pixelNumber = 2500;
    JLabel[] pixels = new JLabel[pixelNumber];

    boolean isMousePressed = false;

    public Canvas(int posx, int posy, int width, int height) {

        this.setBounds(posx, posy, width, height);
        this.setLayout(new GridLayout(50, 50, 0, 0));
        this.setBackground(Color.gray);

        for (int i = 0; i < pixelNumber; i++) {
            pixels[i] = new JLabel();
            pixels[i].setBackground(Color.white);
            pixels[i].setOpaque(true);
            pixels[i].addMouseListener(this);
            this.add(pixels[i]);
        }

        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        isMousePressed = true;

        for (int i = 0; i < pixelNumber; i++) {
            if (e.getSource() == pixels[i]) {
                pixels[i].setBackground(Color.BLUE);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isMousePressed) {
            for (int i = 0; i < pixelNumber; i++) {
                if (e.getSource() == pixels[i]) {
                    pixels[i].setBackground(Color.BLUE);
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
