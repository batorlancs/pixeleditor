package sprites.io.driver.tools;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.driver.Driver;

import java.awt.*;

public class LineTool extends Tool {

    int startXvalue;
    int startYvalue;
    int endXvalue;
    int endYvalue;

    public Color[] startingPixels;

    boolean isDrawingHorizontal = false;
    boolean isDrawingVertical = false;

    int flag;
    Driver driver;
    int brushSize;

    public LineTool(int flag, Driver driver) {
        super();
        this.flag = flag;
        this.driver = driver;
        this.brushSize = driver.getBrushSize();


    }

    @Override
    public void draw(Canvas canvas, Color color, boolean isMousePressed, int mousePressLocation, int mouseCurrentLocation) {
        // update brush size
        brushSize = driver.getBrushSize();
        
        if (isMousePressed) {
            if (startingPixels == null) {
                startingPixels = new Color[canvas.getPixels().length];
                for (int i = 0; i < canvas.getPixels().length; i++) {
                    startingPixels[i] = canvas.getPixel(i);
                }
            } else {
                canvas.updateCanvasArray(startingPixels);
                startXvalue = getXValue(mousePressLocation);
                startYvalue = getYValue(mousePressLocation);

                switch(flag){
                    case 1:
                        drawVerticalLine(canvas, color, startXvalue, startYvalue, mouseCurrentLocation);
                        break;
                    case 2:
                        drawHorizontalLine(canvas, color, startXvalue, startYvalue, mouseCurrentLocation);
                        break;
                }
            }

            startXvalue = getXValue(mousePressLocation);
            startYvalue = getYValue(mousePressLocation);
            
        }
    }

    @Override
    public void release(Canvas canvas, Driver driver, Color color, int mouseCurrentLocation) {
        // save the pixels that were drawn
        canvas.updateCanvasArray(startingPixels);
        drawPermanentLine(canvas, color, mouseCurrentLocation);
        startingPixels = null;
    }
    

    public void drawHorizontalLine(Canvas canvas, Color color, int xValue, int yValue, int mouseCurrentLocation) {

        // get current location of mouse
        int endingXValue = getXValue(mouseCurrentLocation);

        // calculate the index of the pixel
        int pixelNum = getPixelNum(xValue, yValue);

        // calculate the number of pixels to draw
        int repeatCount = Math.abs(endingXValue - startXvalue) + 1;

        drawHorizontalLineToSize(canvas, color, startXvalue, endingXValue, pixelNum, repeatCount);
    }

    public void drawVerticalLine(Canvas canvas, Color color, int xValue, int yValue, int mouseCurrentLocation) {

        int endingYValue = getYValue(mouseCurrentLocation);
        int pixelNum = getPixelNum(xValue, yValue);
        int repeatCount = Math.abs(endingYValue - startYvalue) + 1;

        drawVerticalLineToSize(canvas, color, startYvalue, endingYValue, pixelNum, repeatCount);
    }

    public void drawPermanentLine(Canvas canvas, Color color, int mouseCurrentLocation){

        switch(flag){
            case 1:
                drawVerticalLine(canvas, color, startXvalue, startYvalue, mouseCurrentLocation);
                break;
            case 2:
                drawHorizontalLine(canvas, color, startXvalue, startYvalue, mouseCurrentLocation);
                break;
        }
    }

    public void drawHorizontalLineToSize(Canvas canvas, Color color, int startXvalue, int endXvalue, int pixelNum, int repeatCount){

        switch(brushSize){
            case 1:
                if (endXvalue < startXvalue) {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum - i, color);
                    }
                } else {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum + i, color);
                    }
                }
                break;
            case 2:
                if (endXvalue < startXvalue) {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum - i, color);
                        // color the pixel above and below
                        canvas.setPixel(pixelNum - i - 50, color);
                        canvas.setPixel(pixelNum - i + 50, color);
                    }
                } else {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum + i, color);
                        // color the pixel above and below
                        canvas.setPixel(pixelNum + i - 50, color);
                        canvas.setPixel(pixelNum + i + 50, color);
                    }
                }
                break;
            case 3:
                if (endXvalue < startXvalue) {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum - i, color);
                        canvas.setPixel(pixelNum - i - 50, color);
                        canvas.setPixel(pixelNum - i + 50, color);
                        canvas.setPixel(pixelNum - i - 100, color);
                        canvas.setPixel(pixelNum - i + 100, color);

                    }
                } else {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum + i, color);
                        canvas.setPixel(pixelNum + i - 50, color);
                        canvas.setPixel(pixelNum + i + 50, color);
                        canvas.setPixel(pixelNum + i - 100, color);
                        canvas.setPixel(pixelNum + i + 100, color);
                    }
                }
                break;
        }
    }

    public void drawVerticalLineToSize(Canvas canvas, Color color, int startYvalue, int endYvalue, int pixelNum, int repeatCount){

        switch(brushSize){
            case 1:
                if (endYvalue < startYvalue) {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum - (i * 50), color);
                    }
                } else {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum + (i * 50), color);
                    }
                }
                break;
            case 2:
                if (endYvalue < startYvalue) {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum - (i * 50), color);
                        // color pixel left and right
                        canvas.setPixel(pixelNum - (i * 50) - 1, color);
                        canvas.setPixel(pixelNum - (i * 50) + 1, color);
                    }
                } else {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum + (i * 50), color);
                        // color pixel left and right
                        canvas.setPixel(pixelNum + (i * 50) - 1, color);
                        canvas.setPixel(pixelNum + (i * 50) + 1, color);
                    }
                }
                break;
            case 3:
                if (endYvalue < startYvalue) {
                    for (int i = 0; i < repeatCount; i++) {
                        canvas.setPixel(pixelNum - (i * 50), color);
                        canvas.setPixel(pixelNum - (i * 50) - 1, color);
                        canvas.setPixel(pixelNum - (i * 50) + 1, color);
                        canvas.setPixel(pixelNum - (i * 50) - 2, color);
                        canvas.setPixel(pixelNum - (i * 50) + 2, color);
                    }
                    } else {
                        for (int i = 0; i < repeatCount; i++) {
                            canvas.setPixel(pixelNum + (i * 50), color);
                            canvas.setPixel(pixelNum + (i * 50) - 1, color);
                            canvas.setPixel(pixelNum + (i * 50) + 1, color);
                            canvas.setPixel(pixelNum + (i * 50) - 2, color);
                            canvas.setPixel(pixelNum + (i * 50) + 2, color);
                        }
                    }
                break;
        }
    }

    public int[] orderXValues(int xValue1, int xValue2) {
        int[] orderedXValues = new int[2];
        if (xValue1 < xValue2) {
            orderedXValues[0] = xValue1;
            orderedXValues[1] = xValue2;
        } else {
            orderedXValues[0] = xValue2;
            orderedXValues[1] = xValue1;
        }
        return orderedXValues;
    }

    public int[] orderYValues(int yValue1, int yValue2) {
        int[] orderedYValues = new int[2];
        if (yValue1 < yValue2) {
            orderedYValues[0] = yValue1;
            orderedYValues[1] = yValue2;
        } else {
            orderedYValues[0] = yValue2;
            orderedYValues[1] = yValue1;
        }
        return orderedYValues;
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

    public void setFlag(int flag){
        this.flag = flag;
    }
}
