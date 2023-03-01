package sprites.io.driver;

import java.awt.Color;
import java.util.ArrayList;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.infoPanel.InfoPanel;
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
    private Color[] prevColors = new Color[8];
    private int brushSize = 1;

    /**
     * For use by the undo function
     */
    private ArrayList<Color[]> undoArray = new ArrayList<Color[]>();
    private ArrayList<Color[]> redoArray = new ArrayList<Color[]>();
    /**
     * To check if an undo is the first undo (after a draw),
     * or if it's a subsequent undo
     * Also to see if we've reached the end of the 3 undo limit
     */
    private int undoFlag = 0;
    private Color[] undoEntry;

    private boolean firstDraw = true;

    public Driver(Canvas canvas, InfoPanel infoPanel) {
        this.canvas = canvas;
        this.infoPanel = infoPanel;
    }

    /**
     * call the current tool to draw on the canvas
     */
    public void draw() {
        if (firstDraw) {
            undoEntry = new Color[canvas.getPixels().length];
            for (int i = 0; i < canvas.getPixels().length; i++) {
                undoEntry[i] = new Color(255, 255, 255);
            }
            undoArray.add(undoEntry);
            firstDraw = false;
        }
        currTool.draw(canvas, currColor, isMousePressed, mousePressLocation, mouseCurrentLocation);
    }

    public void release() {
        currTool.release(canvas, currColor, mouseCurrentLocation);
        // update undo array everytime the mouse is released
        updateUndoArray();
        redoArray = new ArrayList<Color[]>();
        undoFlag = 1;
    }

    public void setCurrToolToSquare() {this.currTool = new SquareTool();}
    public void setCurrToolToPen() {this.currTool = new PenTool();}
    public void setCurrToolToEraser() {this.currTool = new Eraser(this);}
    public void setCurrToolToBrushSize() {this.currTool = new Brush(this);}
    public void setCurrToolToFillTool() {this.currTool = new FillTool();}

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

    /**
     * Update undo array
     */
    public void updateUndoArray(){
        undoEntry = new Color[canvas.getPixels().length];
        for (int i = 0; i < canvas.getPixels().length; i++) {
            undoEntry[i] = canvas.getPixel(i);
        }
        undoArray.add(undoEntry);
    }

    /**
     * Actual undo function
     */
    public void undoChange() {
        // Remove most recent addition to the array if it's the first undo after a draw
        // Otherwise the first press of the undo button will appear to do nothing
        if (undoFlag == 1) {
            redoArray.add(undoArray.get(undoArray.size()-1));
            undoArray.remove(undoArray.size()-1);
        }
        // If it is a subsequent/chained undo
        undoFlag = 2;
        if (undoFlag == 2) {
            canvas.updateCanvasArray(undoArray.get(undoArray.size()-1));

            redoArray.add(undoArray.get(undoArray.size()-1));
            undoArray.remove(undoArray.size()-1);

            if (undoArray.size() == 1) {
                undoFlag = 0;
            }
        }

        // Necessary to make this a proper undo
        // Update the array to store how it was after the last undo
        if (undoFlag == 0) {
            canvas.updateCanvasArray(undoArray.get(undoArray.size()-1));
            undoFlag = 0;
            undoEntry = new Color[canvas.getPixels().length];
            for (int i = 0; i < canvas.getPixels().length; i++) {
                undoEntry[i] = canvas.getPixel(i);
            }
            undoArray.add(undoEntry);
        }
    }

    public void redoChange() {
        if (redoArray.size() != 0) {
            canvas.updateCanvasArray(redoArray.get(redoArray.size()-1));
            undoArray.add((redoArray.get(redoArray.size()-1)));
            redoArray.remove(redoArray.size()-1);
        }
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
