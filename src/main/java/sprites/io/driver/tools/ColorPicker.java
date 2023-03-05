package sprites.io.driver.tools;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.driver.Driver;

import java.awt.*;

public class ColorPicker extends Tool {
    public void release(Canvas canvas, Driver driver, Color color, int mouseCurrentLocation) {
        driver.setCurrColor(canvas.getCurrentPixel(mouseCurrentLocation));
        canvas.updatePrevColors();
    }
}
