package sprites.io.driver;

import java.awt.Color;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.driver.tools.*;

/**
 * Manages all the tools to draw on the canvas
 */
public class Driver {

    private Canvas canvas;
    private InfoPanel infoPanel;
    private boolean isMousePressed = false;


    private int mousePressLocation = 0;
    private int mouseCurrentLocation = 0;
    private Tool currTool = new PenTool();
    private Color currColor = new Color(0, 0, 0);
    private int brushSize = 1;

    public Driver(Canvas canvas) {
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
        currTool.release(canvas, currColor, mouseCurrentLocation);
    }

    public void setCurrToolToSquare() {
        this.currTool = new SquareTool();
    }

    public void setCurrToolToPen() {this.currTool = new PenTool();}
    public void setCurrToolToEraser() {this.currTool = new Eraser(brushSize);}
    public void setCurrToolToBrushSize() {this.currTool = new Brush(brushSize);}
    public void setCurrToolToFillTool() {this.currTool = new FillTool();}

    /**
     * change the current color
     * @param currColor the color to be changed
     */
    public void setCurrColor(Color currColor) {
        this.currColor = currColor;
        this.infoPanel.setColor(currColor);
        this.infoPanel.repaint();

    }

    public Color getCurrColor() {
        return currColor;
    }

    public void setMousePressed(boolean mousePressed) {
        isMousePressed = mousePressed;
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

}
