package sprites.io.driver.tools;

import sprites.io.UI.canvaspanel.Canvas;
import java.awt.*;

import javax.swing.JLabel;

public class PenSizeTool extends Tool {

    private JLabel[] pixels = new JLabel[2500];
    
    public void draw(Canvas canvas, Color color, boolean isMousePressed, int mousePressLocation, int mouseCurrentLocation){
        if(isMousePressed){
            canvas.setPixel(mouseCurrentLocation, color);
            fillSquare(mouseCurrentLocation);
        }
        

    }

    public int getPixelNum(int xValue, int yValue) {
        return (((yValue -1)*50) + xValue);
    }

    public int getYValue(int pixelNum) {
        return (int) Math.floor((pixelNum / 50));
    }

    public int getXValue (int pixelNum) {
        return pixelNum % 50;
    }

    public JLabel tempPixelAssign (int pixelNumber) {
        if (pixelNumber < 0 || pixelNumber > 2500) {
            return null;
        }
        return pixels[pixelNumber];
    }

    public void fillSquare(int pixelNum) {
        int xValue = getXValue(pixelNum) + 1;
        int yValue = getYValue(pixelNum) + 1;

        int xRepeat;

        int brushSize = 2;

        System.out.println(xValue);

        System.out.println(yValue);

        System.out.println("Values");

        System.out.println(getPixelNum(xValue, yValue));

        if (brushSize == 2) {
            if (xValue == 1) {
                xRepeat = 1;
            }
            else if (xValue == 50) {
                xValue = xValue - 1;
                xRepeat = 1;
            }
            else {
                xValue = xValue - 1;
                xRepeat = 2;
            }
            fillRecursion(xValue, yValue - 1, xRepeat, 3, 3);
        }
        if (brushSize == 3) {
            if (xValue == 1) {
                xRepeat = 2;
            } else if (xValue == 2) {
                xRepeat = 3;
            } else if (xValue == 49) {
                xValue = xValue - 2;
                xRepeat = 3;
            } else if (xValue == 50) {
                xValue = xValue - 2;
                xRepeat = 2;
            }
            else {
                xValue = xValue - 2;
                xRepeat = 4;
            }
            fillRecursion(xValue, yValue - 2, xRepeat, 5, 5);
        }
    }

    private void fillRecursion(int xCoordinate, int yCoordinate, int xRepeat, int yRepeat, int columnLength) {



        //exit function once all squares have been revealed
        if (xRepeat == 0 && yRepeat ==0) {
            return;
        }

        //move to the next x coordinate and reset y values
        if (yRepeat == 0) {
            xRepeat--;
            xCoordinate++;

            yCoordinate = yCoordinate - columnLength;
            yRepeat = columnLength;
        }

        JLabel tempPixel = tempPixelAssign(getPixelNum(xCoordinate, yCoordinate) - 1);

        if (tempPixel != null) {
            pixels[(getPixelNum(xCoordinate, yCoordinate) - 1)].setBackground(Color.gray);
        }

        yRepeat--;
        yCoordinate++;

        //recursive function call
        fillRecursion(xCoordinate, yCoordinate, xRepeat, yRepeat, columnLength);
    }
}