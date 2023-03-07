package sprites.io.UI.canvaspanel;

import java.awt.Color;

public class LayerState {
    private final Color[] pixels;

    public LayerState(Layer layer) {
        this.pixels = layer.getAllPixels();
    }

    public Color[] getPixels() {
        return pixels;
    }

}
