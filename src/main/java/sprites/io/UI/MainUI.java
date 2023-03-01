package sprites.io.UI;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.menupanel.MenuPanel;
import sprites.io.UI.toolpanel.ToolPanel;
import sprites.io.UI.infoPanel.InfoPanel;
import sprites.io.UI.layerspanel.LayersPanel;
import sprites.io.driver.Driver;


import java.awt.*;

import javax.swing.*;

public class MainUI extends JFrame {


    private Canvas canvas = new Canvas(70, 6, 500, 500);
    private InfoPanel infoPanel = new InfoPanel(0, 0);
    private MenuPanel menuPanel = new MenuPanel(128, 0, 640, 64, canvas, this);
    private JPanel canvasPanel = new JPanel();
    private Driver driver = new Driver(canvas, infoPanel);
    private ToolPanel toolPanel = new ToolPanel(0, 64, 128, 600, driver);
    private LayersPanel layerPanel = new LayersPanel(canvas);


    public MainUI() {
        this.createDisplay();
    }

    public void createDisplay()
    {

        this.setSize(1100, 620);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.darkGray);

        canvas.addDriver(driver);

        canvasPanel.setBackground(Color.BLACK);
        // 512
        canvasPanel.setBounds(128,64,640,550);
        canvasPanel.setLayout(null);
        canvasPanel.add(canvas);

        layerPanel.setCanvas(canvas);
//        layerPanel.setBounds(768,64,280,512);

        this.add(canvasPanel);
        this.add(toolPanel);
        this.add(layerPanel);
        this.add(menuPanel);
        this.add(infoPanel);

        this.setVisible(true);
    }

    public void createDisplayOpenFile(int[] openFile)
    {
        this.setVisible(false);
        this.setSize(1024,640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        canvasPanel.setBackground(Color.BLACK);
        canvasPanel.setBounds(128,64,640,512);
        canvasPanel.setLayout(null);

        Canvas openCanvas = new Canvas(70, 6, 500, 500);

        for (int i=0; i<2500; i++)
        {
            canvas.getPixels()[i].setBackground(new Color(openFile[i]));

        }

        canvasPanel.add(canvas);

        layerPanel.setCanvas(canvas);
        layerPanel.setBounds(768,64,280,350);

        this.add(canvasPanel);
        this.add(toolPanel);
        this.add(layerPanel);
        this.add(menuPanel);

        this.setVisible(true);
    }

}