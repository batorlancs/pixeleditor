package sprites.io.UI;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.menupanel.MenuPanel;
import sprites.io.UI.toolpanel.ToolPanel;
import sprites.io.driver.Driver;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI
{
    JFrame mainFrame = new JFrame();

    Driver driver = new Driver();

    Canvas canvas = new Canvas(70, 6, 500, 500, driver);
    JPanel canvasPanel = new JPanel();
    ToolPanel toolPanel = new ToolPanel(0, 64, 128, 512, driver);
    JPanel layerPanel = new JPanel();
    MenuPanel menuPanel = new MenuPanel(0, 0, 1024, 64, canvas);

    public void CreateDisplay()
    {
        mainFrame.setSize(1024,640);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        canvasPanel.setBackground(Color.BLACK);
        canvasPanel.setBounds(128,64,640,512);
        canvasPanel.setLayout(null);
        canvasPanel.add(canvas);

        layerPanel.setBackground(Color.blue);
        layerPanel.setBounds(640,64,384,512);

        mainFrame.add(canvasPanel);
        mainFrame.add(toolPanel);
        mainFrame.add(layerPanel);
        mainFrame.add(menuPanel);

        mainFrame.setVisible(true);
    }

}