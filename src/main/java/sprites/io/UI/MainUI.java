package sprites.io.UI;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.menupanel.MenuPanel;
import sprites.io.UI.toolpanel.ToolPanel;
import sprites.io.UI.infopanel.InfoPanel;
import sprites.io.driver.Driver;


import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI extends JFrame {


    private Canvas canvas = new Canvas(70, 6, 500, 500);
    private Driver driver = new Driver(canvas);
    private InfoPanel infoPanel = new InfoPanel();
    private JPanel canvasPanel = new JPanel();
    private ToolPanel toolPanel = new ToolPanel(0, 64, 128, 512, driver);
    private JPanel layerPanel = new JPanel();
    private MenuPanel menuPanel = new MenuPanel(0, 0, 1024, 64, canvas, this);

    public MainUI() {
        this.createDisplay();
    }

    public void createDisplay()
    {

        this.setSize(1024,640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        canvas.addDriver(driver);

        canvasPanel.setBackground(Color.BLACK);
        canvasPanel.setBounds(128,64,640,512);
        canvasPanel.setLayout(null);
        canvasPanel.add(canvas);

        layerPanel.setBackground(Color.blue);
        layerPanel.setBounds(640,64,384,512);

        this.add(canvasPanel);
        this.add(toolPanel);
        this.add(layerPanel);
        this.add(menuPanel);

        this.setVisible(true);
    }

}