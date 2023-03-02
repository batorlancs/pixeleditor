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


    private Canvas canvas  = new Canvas(70, 6, 500, 500, this, null);
    private InfoPanel infoPanel = new InfoPanel(0, 0);
    private MenuPanel menuPanel = new MenuPanel(128, 0, 640, 64, canvas, this);
    private JPanel canvasPanel = new JPanel();
    private Driver driver = new Driver(canvas, infoPanel, this);
    private ToolPanel toolPanel = new ToolPanel(0, 64, 128, 600, driver);
    private LayersPanel layerPanel = new LayersPanel(canvas, this);


    public MainUI() {
        this.createDisplay();
    }
    public MainUI(int[] fileContent) {
        canvas = new Canvas(70, 6, 500, 500, this, fileContent);
        infoPanel = new InfoPanel(0, 0);
        menuPanel = new MenuPanel(128, 0, 640, 64, canvas, this);
        canvasPanel = new JPanel();
        driver = new Driver(canvas, infoPanel, this);
        toolPanel = new ToolPanel(0, 64, 128, 600, driver);
        layerPanel = new LayersPanel(canvas, this);

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

        this.add(canvasPanel);
        this.add(toolPanel);
        this.add(layerPanel);
        this.add(menuPanel);
        this.add(infoPanel);

        this.setVisible(true);
    }

    public void updateLayers() {
        layerPanel.updateLayers();
    }

    public void updatePrevColors() {
        toolPanel.updatePrevColors();
    }

}