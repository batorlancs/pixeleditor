package sprites.io.driver;

import java.awt.Color;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.driver.tools.PenTool;
import sprites.io.driver.tools.Tool;

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

    public void draw() {
        currTool.draw(canvas, currColor, isMousePressed, mousePressLocation, mouseCurrentLocation);
    }

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
