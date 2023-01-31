package sprites.io.driver.tools;

import java.awt.*;
import sprites.io.UI.canvaspanel.Canvas;

public class Brush extends Tool {

    private Canvas canvas;
    private Color color;
    private boolean isMousePressed;
    private int mousePressLocation;
    private int mouseCurrentLocation;
    private int currentSize;
    
    // constructor
    public Brush(int size) {
        this.currentSize = size;
    }
    

    public void draw(Canvas canvas, Color color, boolean isMousePressed, int mousePressLocation, int mouseCurrentLocation){

        // store all the variables in the class
        this.canvas = canvas;
        this.color = color;
        this.isMousePressed = isMousePressed;
        this.mousePressLocation = mousePressLocation;
        this.mouseCurrentLocation = mouseCurrentLocation;
        
        if(isMousePressed){
            // color the pixel
            canvas.getPixel(mousePressLocation).setBackground(color);
            this.drawToSize();
            
        }

        // color the pixels as the mouse is dragged
        if(mouseCurrentLocation != mousePressLocation && isMousePressed){
            // constantly update the mouse location
            this.mousePressLocation = mouseCurrentLocation;
            canvas.getPixel(mouseCurrentLocation).setBackground(color);
            this.drawToSize();
        }


    }

    // function to color the pixels according to the brush size
    public void drawToSize(){
        
        
            // check the size of the brush
        switch(currentSize){
            case 1:
                break;
            case 2: 
                // color the pixel to the right 
                if(mousePressLocation + 1 < 2500 && mousePressLocation % 50 != 49){
                    canvas.getPixel(mousePressLocation + 1).setBackground(color);
                }
                // color the pixel down
                if(mousePressLocation + 50 < 2500){
                    canvas.getPixel(mousePressLocation + 50).setBackground(color);
                }
                // color the pixel to the down right
                if(mousePressLocation + 51 < 2500 && mousePressLocation % 50 != 49){
                    canvas.getPixel(mousePressLocation + 51).setBackground(color);
                }
                break;
            case 3: // color the pixels in 8x8 square around it
                if(mousePressLocation + 1 < 2500 && mousePressLocation % 50 != 49){
                    canvas.getPixel(mousePressLocation + 1).setBackground(color);
                }
                if(mousePressLocation - 1 >= 0 && mousePressLocation % 50 != 0){
                    canvas.getPixel(mousePressLocation - 1).setBackground(color);
                }
                if(mousePressLocation + 50 < 2500){
                    canvas.getPixel(mousePressLocation + 50).setBackground(color);
                }
                if(mousePressLocation - 50 >= 0){
                    canvas.getPixel(mousePressLocation - 50).setBackground(color);
                }
                if(mousePressLocation + 51 < 2500 && mousePressLocation % 50 != 49){
                    canvas.getPixel(mousePressLocation + 51).setBackground(color);
                }
                if(mousePressLocation - 51 >= 0 && mousePressLocation % 50 != 0){
                    canvas.getPixel(mousePressLocation - 51).setBackground(color);
                }
                if(mousePressLocation + 49 < 2500 && mousePressLocation % 50 != 0){
                    canvas.getPixel(mousePressLocation + 49).setBackground(color);
                }
                if(mousePressLocation - 49 >= 0 && mousePressLocation % 50 != 49){
                    canvas.getPixel(mousePressLocation - 49).setBackground(color);
                }
                break;
            }
    }
    
}
