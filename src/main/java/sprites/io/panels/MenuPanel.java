package sprites.io.panels;

import file.FileManager;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private JButton save = new JButton("SAVE");
    private JButton open = new JButton("OPEN");

    private FileManager fileManager = new FileManager();
    private Canvas canvasRef;

    /**
     *
     * @param posx Position X of panel.
     * @param posy Position Y of panel.
     * @param width Width of panel.
     * @param height Height of panel.
     * @param canvas Reference of Canvas to get Pixel values.
     */
    public MenuPanel(int posx, int posy, int width, int height, Canvas canvas) {
        this.setBounds(posx, posy, width, height);
        this.setBackground(Color.green);
        this.setLayout(null);

        this.canvasRef = canvas;

        save.setBounds(800, 0, 100, 64);
        save.addActionListener(this);
        open.setBounds(900, 0, 100, 64);
        open.addActionListener(this);

        this.add(save);
        this.add(open);
    }

    /**
     * Save and Open actions.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            System.out.println("saving file...");
            fileManager.saveFile(canvasRef.getPixels(), "something.txt");
        }
        else if (e.getSource() == open) {
            System.out.println("opening file");
            fileManager.openFile(canvasRef.getPixels(), "something.txt");
        }
    }
}
