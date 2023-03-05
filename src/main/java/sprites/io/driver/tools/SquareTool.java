package sprites.io.driver.tools;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.driver.Driver;

import java.awt.*;

public class SquareTool extends Tool{

    int startingXValue;
    int startingYValue;
    int endingXValue;
    int endingYValue;

    private Driver driver;

    public Color[] startingPixels;

    public SquareTool(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void draw(Canvas canvas, Color color, boolean isMousePressed, int mousePressLocation, int mouseCurrentLocation) {
        if (isMousePressed) {
            if (startingPixels == null) {
                startingPixels = new Color[canvas.getPixels().length];
                for (int i = 0; i < canvas.getPixels().length; i++) {
                    startingPixels[i] = canvas.getPixel(i);
                }
            } else {
                canvas.updateCanvasArray(startingPixels);
                drawToSize(canvas, color, mouseCurrentLocation);
            }

            startingXValue = getXValue(mousePressLocation);
            startingYValue = getYValue(mousePressLocation);
        }

    }

    @Override
    public void release(Canvas canvas, Driver driver, Color color, int mouseCurrentLocation) {
        // calling it again to reduce buggy effects when updating the canvas
        canvas.updateCanvasArray(startingPixels);
        drawToSize(canvas, color, mouseCurrentLocation);
        startingPixels = null;
    }

    private void squareSizeOne(Canvas canvas, Color color, int mouseCurrentLocation, int[] orderedX, int[] orderedY,
                            int repeatXCount, int repeatYCount) {
        // Draw 2 horizontal lines
        drawHorizontalLine(canvas, color, orderedX[0], orderedY[0], repeatXCount);
        drawHorizontalLine(canvas, color, orderedX[0], orderedY[1], repeatXCount);

        // Draw 2 vertical lines and complete the square
        drawVerticallLine(canvas, color, orderedX[0], orderedY[0], repeatYCount);
        drawVerticallLine(canvas, color, orderedX[1], orderedY[0], repeatYCount);
    }

    private void squareSizeTwo(Canvas canvas, Color color, int mouseCurrentLocation, int[] orderedX, int[] orderedY,
                               int repeatXCount, int repeatYCount) {
        // First layer
        drawHorizontalLine(canvas, color, orderedX[0], orderedY[0], repeatXCount);
        drawHorizontalLine(canvas, color, orderedX[0], orderedY[1], repeatXCount);

        // Draw 2 vertical lines and complete the square
        drawVerticallLine(canvas, color, orderedX[0], orderedY[0], repeatYCount);
        drawVerticallLine(canvas, color, orderedX[1], orderedY[0], repeatYCount);


        // Second layer
        if (repeatXCount > 1 && repeatYCount > 1) {
            // numbers changed with addition/subtraction to be inside the first square
            drawHorizontalLine(canvas, color, orderedX[0]+1, orderedY[0]+1, repeatXCount-1);
            drawHorizontalLine(canvas, color, orderedX[0]+1, orderedY[1]-1, repeatXCount-1);

            // numbers changed with addition/subtraction to be inside the first square
            drawVerticallLine(canvas, color, orderedX[0]+1, orderedY[0]+1, repeatYCount-1);
            drawVerticallLine(canvas, color, orderedX[1]-1, orderedY[0]+1, repeatYCount-1);
        }
    }

    private void squareSizeThree(Canvas canvas, Color color, int mouseCurrentLocation, int[] orderedX, int[] orderedY,
                               int repeatXCount, int repeatYCount) {
        // First layer
        drawHorizontalLine(canvas, color, orderedX[0], orderedY[0], repeatXCount);
        drawHorizontalLine(canvas, color, orderedX[0], orderedY[1], repeatXCount);

        // Draw 2 vertical lines and complete the square
        drawVerticallLine(canvas, color, orderedX[0], orderedY[0], repeatYCount);
        drawVerticallLine(canvas, color, orderedX[1], orderedY[0], repeatYCount);


        // Second layer
        if (repeatXCount > 1 && repeatYCount > 1) {
            // numbers changed with addition/subtraction to be inside the first square
            drawHorizontalLine(canvas, color, orderedX[0]+1, orderedY[0]+1, repeatXCount-1);
            drawHorizontalLine(canvas, color, orderedX[0]+1, orderedY[1]-1, repeatXCount-1);

            // numbers changed with addition/subtraction to be inside the first square
            drawVerticallLine(canvas, color, orderedX[0]+1, orderedY[0]+1, repeatYCount-1);
            drawVerticallLine(canvas, color, orderedX[1]-1, orderedY[0]+1, repeatYCount-1);
        }

        // Third layer
        if (repeatXCount > 2 && repeatYCount > 2) {
            // numbers changed with addition/subtraction to be inside the second square
            drawHorizontalLine(canvas, color, orderedX[0]+2, orderedY[0]+2, repeatXCount-2);
            drawHorizontalLine(canvas, color, orderedX[0]+2, orderedY[1]-2, repeatXCount-2);

            // numbers changed with addition/subtraction to be inside the second square
            drawVerticallLine(canvas, color, orderedX[0]+2, orderedY[0]+2, repeatYCount-2);
            drawVerticallLine(canvas, color, orderedX[1]-2, orderedY[0]+2, repeatYCount-2);
        }

    }

    public void drawToSize(Canvas canvas, Color color, int mouseCurrentLocation){
        endingXValue = getXValue(mouseCurrentLocation);
        endingYValue = getYValue(mouseCurrentLocation);

        int[] orderedX = orderXValues(startingXValue, endingXValue);
        int[] orderedY = orderYValues(startingYValue, endingYValue);

        int repeatXCount = Math.abs(endingXValue - startingXValue) + 1;
        int repeatYCount = Math.abs(endingYValue - startingYValue);

        switch(driver.getBrushSize()){
            case 1:
                squareSizeOne(canvas, color, mouseCurrentLocation, orderedX, orderedY, repeatXCount, repeatYCount);
                break;
            case 2:
                squareSizeTwo(canvas, color, mouseCurrentLocation, orderedX, orderedY, repeatXCount, repeatYCount);
                break;
            case 3:
                squareSizeThree(canvas, color, mouseCurrentLocation, orderedX, orderedY, repeatXCount, repeatYCount);
                break;
        }
    }

    private int[] orderXValues(int val1, int val2) {
        int[] orderedX = {0, 0};

        if (val1 < val2) {
            orderedX[0] = val1;
            orderedX[1] = val2;
        }
        else {
            orderedX[0] = val2;
            orderedX[1] = val1;
        }

        return orderedX;
    }

    private int[] orderYValues(int val1, int val2) {
        int[] orderedY = {0, 0};

        if (val1 < val2) {
            orderedY[0] = val1;
            orderedY[1] = val2;
        }
        else {
            orderedY[0] = val2;
            orderedY[1] = val1;
        }

        return orderedY;
    }

    private void drawHorizontalLine(Canvas canvas, Color color, int xValue, int yValue, int repeatXCount) {
        if (repeatXCount == 0) {
            return;
        }
        canvas.setPixel(getPixelNum(xValue, yValue), color);

        xValue++;
        repeatXCount--;

        drawHorizontalLine(canvas, color, xValue, yValue, repeatXCount);
    }

    private void drawVerticallLine(Canvas canvas, Color color, int xValue, int yValue, int repeatYCount) {
        if (repeatYCount == 0) {
            return;
        }
        canvas.setPixel(getPixelNum(xValue, yValue), color);

        yValue++;
        repeatYCount--;

        drawVerticallLine(canvas, color, xValue, yValue, repeatYCount);
    }

    public int getPixelNum(int xValue, int yValue) {
        return (((yValue - 1)*50) + xValue);
    }

    public int getYValue(int pixelNum) {
        return (int) (Math.floor((pixelNum / 50)) + 1);
    }

    public int getXValue (int pixelNum) {
        return pixelNum % 50;
    }

}