package sprites.io.driver.tools;

import sprites.io.UI.canvaspanel.Canvas;

import java.awt.*;

public class Eraser extends Brush {
    
    public Eraser(int size) {
        super(size);
    }

    @Override
    public void draw(Canvas canvas, Color color, boolean isMousePressed, int mousePressLocation, int mouseCurrentLocation) {
        super.draw(canvas, Color.WHITE, isMousePressed, mousePressLocation, mouseCurrentLocation);
    }
}
