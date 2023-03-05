package sprites.io.driver.tools;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.driver.Driver;

import java.awt.*;

public class Eraser extends Brush {

    public Eraser(Driver driver) {
        super(driver);
    }

    @Override
    public void draw(Canvas canvas, Color color, boolean isMousePressed, int mousePressLocation, int mouseCurrentLocation) {
        super.draw(canvas, null, isMousePressed, mousePressLocation, mouseCurrentLocation);
    }
}
