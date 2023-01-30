package sprites.io.driver;

import java.awt.Color;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.driver.tools.EraserTool;
import sprites.io.driver.tools.PenTool;
import sprites.io.driver.tools.SquareTool;
import sprites.io.driver.tools.Tool;

/**
 * Manages all the tools to draw on the canvas
 */
public class Driver {

    private Canvas canvas;
    private boolean isMousePressed = false;
    private int mousePressLocation = 0;
    private int mouseCurrentLocation = 0;
    private Tool currTool = new PenTool();
    private Color currColor = new Color(0, 0, 0);

    public Driver(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * call the current tool to draw on the canvas
     */
    public void draw() {
        currTool.draw(canvas, currColor, isMousePressed, mousePressLocation, mouseCurrentLocation);
    }

    public void setCurrToolToPen() {this.currTool = new PenTool();}
    public void setCurrToolToEraser() {this.currTool = new EraserTool();}
    public void setCurrToolToSquare() {this.currTool = new SquareTool();}

    /**
     * change the current color
     * @param currColor the color to be changed
     */
    public void setCurrColor(Color currColor) {
        this.currColor = currColor;
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
}
