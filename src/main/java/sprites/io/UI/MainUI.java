package sprites.io.UI;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.canvaspanel.Layer;
import sprites.io.UI.menupanel.MenuPanel;
import sprites.io.UI.toolpanel.ToolPanel;
import sprites.io.UI.infoPanel.InfoPanel;
import sprites.io.UI.layerspanel.LayersPanel;
import sprites.io.driver.Driver;


import java.awt.*;
import java.awt.image.BufferedImage;
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

    private Cursor currCursor;
    private Cursor brushSizeCursor;

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

    public void setCursorToCrossHair() {
        BufferedImage cursorImg = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImg.createGraphics();

        // Draw a filled circle with a white border
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, 32, 32);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(8, 16, 24, 16);
        g2d.drawLine(16, 8, 16, 24);
        g2d.dispose();

        // create a new cursor with the custom image
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(16, 16), "circle");
        currCursor = cursor;
    }

    public void setCursorToCircle() {
        if (!driver.isCurrToolBrushOrEraser()) return;
        if (driver.getBrushSize() == 1) {
            currCursor = setCursorToCircleSmall();
        } else if (driver.getBrushSize() == 2) {
            currCursor = setCursorToCircleMedium();
        } else {
            currCursor = setCursorToCircleLarge();
        }
    }

    private Cursor setCursorToCircleSmall() {

        BufferedImage cursorImg = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImg.createGraphics();

        // Draw a filled circle with a white border
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, 32, 32);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(8, 8, 16, 16);
        g2d.setColor(Color.WHITE);
        g2d.drawOval(10, 10, 12, 12);
        g2d.dispose();

        // create a new cursor with the custom image
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(16, 16), "circle");
        return cursor;
    }

    private Cursor setCursorToCircleMedium() {

        BufferedImage cursorImg = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImg.createGraphics();

        // Draw a filled circle with a white border
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, 32, 32);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(4, 4, 24, 24);
        g2d.setColor(Color.WHITE);
        g2d.drawOval(6, 6, 20, 20);
        g2d.dispose();

        // create a new cursor with the custom image
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(12, 12), "circle");
        return cursor;
    }

    private Cursor setCursorToCircleLarge() {

        BufferedImage cursorImg = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImg.createGraphics();

        // Draw a filled circle with a white border
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.fillRect(0, 0, 32, 32);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(1, 1, 30, 30);
        g2d.setColor(Color.WHITE);
        g2d.drawOval(3, 3, 26, 26);
        g2d.dispose();

        // create a new cursor with the custom image
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(16, 16), "circle");
        return cursor;
    }

    public void setCursorToFill() {
        Image cursorImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/fillIcon.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(25, 20), "fill");
        currCursor = cursor;
    }

    public void setCursorToColorPicker() {
        Image cursorImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/colorPickerIcon.png"))).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(4, 25), "colorPicker");
        currCursor = cursor;
    }

    public void setCursorToCurrent() {
        this.setCursor(currCursor);
    }

    public void setCursorToDefault() {
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void updateLayers() {
        layerPanel.updateLayers();
    }

    public void updatePrevColors() {
        toolPanel.updatePrevColors();
    }

}