package sprites.io.driver.tools;

import java.awt.*;
import sprites.io.UI.canvaspanel.Canvas;

public class FillTool extends Tool {

    public void draw(Canvas canvas, Color color, boolean isMousePressed, int mousePressLocation, int mouseCurrentLocation){
        if(isMousePressed){
            //get the color of the pixel that was clicked
            Color originalColor = canvas.getPixel(mousePressLocation);

            //if the color of the pixel that was clicked is not the same as the color that is being used to fill
            if(originalColor == null || !originalColor.equals(color)){
                //call the recursive function
                fill(canvas, color, originalColor, mousePressLocation);
            }

            // if the color of the pixel that was clicked is the same as the color that is being used to fill
            else{
                // do nothing
            }

        }

    }

    // recursive function to fill the canvas
    public void fill(Canvas canvas, Color color, Color originalColor, int mousePressLocation){
        // if the pixel is within the canvas
        if(mousePressLocation >= 0 && mousePressLocation < 2500) {
            // if the pixel is the same color as the original color
            if((canvas.getPixel(mousePressLocation) == null && originalColor == null) ||
                    (canvas.getPixel(mousePressLocation) != null && canvas.getPixel(mousePressLocation).equals(originalColor))){
                // color the pixel
                canvas.setPixel(mousePressLocation, color);

                // call the function again for the pixel to the right
                if (mousePressLocation % 50 < 49)
                    fill(canvas, color, originalColor, mousePressLocation + 1);

                // call the function again for the pixel to the left
                if (mousePressLocation % 50 > 0)
                    fill(canvas, color, originalColor, mousePressLocation - 1);

                // call the function again for the pixel above
                if (mousePressLocation / 50 > 0)
                    fill(canvas, color, originalColor, mousePressLocation - 50);

                // call the function again for the pixel below
                if (mousePressLocation / 50 < 49)
                    fill(canvas, color, originalColor, mousePressLocation + 50);
            }
        }
    }

}