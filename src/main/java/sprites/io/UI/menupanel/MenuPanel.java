package sprites.io.UI.menupanel;

import sprites.io.UI.MainUI;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.file.FileManager;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private JButton save = new JButton("SAVE");
    private JButton open = new JButton("OPEN");
    private JButton clear = new JButton("CLEAR");
    private JButton newProject = new JButton("NEW PROJECT");

    private JButton exportPng = new JButton("EXPORT");

    private FileManager fileManager = new FileManager();
    private Canvas canvasRef;
    private MainUI mainUI;

    /**
     *
     * @param posx Position X of panel.
     * @param posy Position Y of panel.
     * @param width Width of panel.
     * @param height Height of panel.
     * @param canvas Reference of Canvas to get Pixel values.
     */
    public MenuPanel(int posx, int posy, int width, int height, Canvas canvas, MainUI mainUI) {
        this.mainUI = mainUI;

        this.setBounds(posx, posy, width, height);
        this.setBackground(Color.gray);
        this.setLayout(null);

        this.canvasRef = canvas;

        newProject.setBounds(400, 0, 200, 64);
        newProject.addActionListener(this);

        clear.setBounds(600, 0, 100, 64);
        clear.addActionListener(this);

        save.setBounds(700, 0, 100, 64);
        save.addActionListener(this);

        open.setBounds(800, 0, 100, 64);
        open.addActionListener(this);

        exportPng.setBounds(900,0,100,64);
        exportPng.addActionListener(this);

        this.add(newProject);
        this.add(clear);
        this.add(save);
        this.add(open);
        this.add(exportPng);
    }

    /**
     * Save and Open actions.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            System.out.println("saving file..");
            fileManager.saveFile(canvasRef.getPixels());
        }
        else if (e.getSource() == open) {
            System.out.println("opening file..");
            fileManager.openFile(canvasRef.getPixels());
        }
        else if (e.getSource() == clear) {
            canvasRef.clearCanvas();
        }
        else if (e.getSource() == newProject) {
            mainUI.dispose();
            new MainUI();
        }
        else if (e.getSource() == exportPng){
            fileManager.exportAsPng(canvasRef.getPixels(),50,50);
        }
    }
}
