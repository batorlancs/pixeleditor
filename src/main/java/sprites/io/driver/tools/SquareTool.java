package sprites.io.driver.tools;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.driver.Driver;

import java.awt.*;

public class SquareTool extends Tool{

    int startingXValue;
    int startingYValue;
    int endingXValue;
    int endingYValue;

    public Color[] startingPixels;

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
                drawSquare(canvas, color, mouseCurrentLocation);
            }

            startingXValue = getXValue(mousePressLocation);
            startingYValue = getYValue(mousePressLocation);
        }

    }

    public void release(Canvas canvas, Driver driver, Color color, int mouseCurrentLocation) {
        // calling it again to reduce buggy effects when updating the canvas
        canvas.updateCanvasArray(startingPixels);
        drawSquare(canvas, color, mouseCurrentLocation);
        startingPixels = null;
    }

    private void drawSquare(Canvas canvas, Color color, int mouseCurrentLocation) {
        endingXValue = getXValue(mouseCurrentLocation);
        endingYValue = getYValue(mouseCurrentLocation);

        int[] orderedX = orderXValues(startingXValue, endingXValue);
        int[] orderedY = orderYValues(startingYValue, endingYValue);

        int repeatXCount = Math.abs(endingXValue - startingXValue) + 1;
        int repeatYCount = Math.abs(endingYValue - startingYValue);


        // Draw 2 horizontal lines
        drawHorizontalLine(canvas, color, orderedX[0], orderedY[0], repeatXCount);
        drawHorizontalLine(canvas, color, orderedX[0], orderedY[1], repeatXCount);

        // Draw 2 vertical lines and complete the square
        drawVerticallLine(canvas, color, orderedX[0], orderedY[0], repeatYCount);
        drawVerticallLine(canvas, color, orderedX[1], orderedY[0], repeatYCount);
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