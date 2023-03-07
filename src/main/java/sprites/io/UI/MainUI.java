package sprites.io.UI;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.canvaspanel.Layer;
import sprites.io.UI.menupanel.MenuPanel;
import sprites.io.UI.toolpanel.ToolPanel;
import sprites.io.UI.infoPanel.InfoPanel;
import sprites.io.UI.layerspanel.LayersPanel;
import sprites.io.driver.Driver;


import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

public class MainUI extends JFrame {


    private Canvas canvas  = new Canvas(70, 6, 500, 500, this, null);
    private InfoPanel infoPanel = new InfoPanel(0, 500);
    private MenuPanel menuPanel = new MenuPanel(128, 0, 640, 64, canvas, this);
    private JPanel canvasPanel = new JPanel();
    private Driver driver = new Driver(canvas, infoPanel, this);
    private ToolPanel toolPanel = new ToolPanel(0, 10, 128, 600, driver, this, canvas);
    private LayersPanel layerPanel = new LayersPanel(canvas, this);

    public MainUI() {
        this.createDisplay();
    }

    public MainUI(ArrayList<Layer> fileContent) {
        canvas = new Canvas(70, 6, 500, 500, this, fileContent);
        infoPanel = new InfoPanel(0, 500);
        menuPanel = new MenuPanel(128, 0, 640, 64, canvas, this);
        canvasPanel = new JPanel();
        driver = new Driver(canvas, infoPanel, this);
        toolPanel = new ToolPanel(0, 10, 128, 600, driver, this, canvas);
        layerPanel = new LayersPanel(canvas, this);

        this.createDisplay();
    }

    public void createDisplay()
    {

        this.setSize(1070, 620);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.darkGray);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/AppIcon.png"))).getImage());
        this.setTitle("Sprites.io Pixel Editor");

        canvas.addDriver(driver);

        canvasPanel.setBackground(Color.BLACK);
        // 512
        canvasPanel.setBounds(128,64,640,550);
        canvasPanel.setLayout(null);
        canvasPanel.add(canvas);

        layerPanel.setCanvas(canvas);

        toolPanel.add(infoPanel);

        this.add(canvasPanel);
        this.add(toolPanel);
        this.add(layerPanel);
        this.add(menuPanel);

        this.setVisible(true);
    }

    public void updateLayers() {
        layerPanel.updateLayers();
    }

    public void updatePrevColors() {
        toolPanel.updatePrevColors();
    }

}