package sprites.io.driver;

import java.awt.Color;

import sprites.io.UI.MainUI;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.infoPanel.InfoPanel;
import sprites.io.driver.tools.*;

/**
 * Manages all the tools to draw on the canvas
 */
public class Driver {

    private MainUI mainUI;
    private Canvas canvas;
    private InfoPanel infoPanel;
    private boolean isMousePressed = false;

    private int mousePressLocation = 0;
    private int mouseCurrentLocation = 0;
    private int brushSize = 1;
    private Tool currTool = new Brush(this);
    private Color currColor = new Color(0, 0, 0);
    private Color[] prevColors = new Color[8];

    public Driver(Canvas canvas, InfoPanel infoPanel, MainUI mainUI) {
        this.mainUI = mainUI;
        this.canvas = canvas;
        this.infoPanel = infoPanel;
    }

    /**
     * call the current tool to draw on the canvas
     */
    public void draw() {
        currTool.draw(canvas, currColor, isMousePressed, mousePressLocation, mouseCurrentLocation);
    }

    public void release() {
        currTool.release(canvas, this, currColor, mouseCurrentLocation);
    }

    public void setCurrToolToSquare() {this.currTool = new SquareTool(this);}
    public void setCurrToolToLine(int flag) { this.currTool = new LineTool(flag, this);}
    public void setCurrToolToPen() {this.currTool = new PenTool();}
    public void setCurrToolToEraser() {this.currTool = new Eraser(this);}
    public void setCurrToolToBrushSize() {this.currTool = new Brush(this);}
    public void setCurrToolToFillTool() {this.currTool = new FillTool();}
    public void setCurrToolToColorPicker() {this.currTool = new ColorPicker();}

    public boolean isCurrToolColorPicker() {
        return "ColorPickerTool".equals(this.currTool.getName());
    }
    public boolean isCurrToolBrushOrEraser() {
        return ("Brush".equals(this.currTool.getName()) || "Eraser".equals(this.currTool.getName()));
    }

    /**
     * change the current color
     * @param currColor the color to be changed
     */
    public void setCurrColor(Color currColor) {
        this.currColor = currColor;
        this.infoPanel.setColor(currColor);
        this.infoPanel.repaint();
        updatePrevColors(currColor);
    }

    public Color getCurrColor() {
        return currColor;
    }

    public void setMousePressed(boolean mousePressed) {
        isMousePressed = mousePressed;
    }

    public boolean isMousePressed() {
        return isMousePressed;
    }

    public void setMousePressLocation(int mousePressLocation) {
        this.mousePressLocation = mousePressLocation;
    }

    public void setMouseCurrentLocation(int mouseCurrentLocation) {
        this.mouseCurrentLocation = mouseCurrentLocation;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
        this.infoPanel.setBrushSize(brushSize);
    }

    public int getBrushSize() {
        return brushSize;
    }

    private void updatePrevColors(Color newColor) {
        int pos = prevColors.length - 1;

        for (int i = 0; i < prevColors.length; i++) {
            if (prevColors[i] == newColor) {
                pos = i;
            }
        }
        for (int i = pos; i > 0; i--) {
            prevColors[i] = prevColors[i-1];
        }
        prevColors[0] = newColor;
    }

    public Color getPrevColor(int num) {
        return prevColors[num];
    }

}
