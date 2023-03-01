package sprites.io.driver.tools;

import sprites.io.UI.canvaspanel.Canvas;
import java.awt.*;

public class PenTool extends Tool {
    public void draw(Canvas canvas, Color color, boolean isMousePressed, int mousePressLocation, int mouseCurrentLocation) {
        if (isMousePressed) {
            canvas.setPixel(mouseCurrentLocation, color);
        }
    }
}
