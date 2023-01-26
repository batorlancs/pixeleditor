package sprites.io;

import static org.junit.Assert.*;

import javax.swing.JLabel;

import org.junit.Test;
import java.awt.Color;

import sprites.io.UI.canvaspanel.Canvas;

public class CanvasTest {
    
    private Canvas newCanvas = new Canvas(1, 1, 50, 50, null);

    @Test
    public void showCanvas(){
        assertTrue(newCanvas != null);

    }

    @Test
    public void pixelSizeTest(){
        assertTrue(newCanvas.getPixelSize() == 50*50);
    }

    // test that pixels on canvas has white background
    @Test
    public void backgroundColorTest(){
        JLabel[] test = newCanvas.getPixels();
        assertTrue(test[0].getBackground() == Color.WHITE);

    }

    // test that canvas has mouse listener
    @Test
    public void mouseListenerTest(){
        assertTrue(newCanvas.getMouseListeners().length == 1);
    }

}
