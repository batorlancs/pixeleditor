package sprites.io.UI.toolpanel;

import sprites.io.UI.buttonStyles.StyledButton;
import sprites.io.UI.buttonStyles.StyledLabel;
import sprites.io.driver.Driver;
import javax.swing.*;
import java.awt.Color;

public class ToolPanel extends JPanel {

    private Driver driverRef;
    private StyledLabel toolsLabel;
    private ToolsPanel toolsPanel;
    private StyledLabel colorPickerLabel;
    private ColorPickerPanel colorPickerPanel;
    private StyledButton colorPickerButton;
    private StyledLabel prevColorLabel;
    private PrevColorPanel prevColorPanel;
//    private InfoPanel infoPanel = new InfoPanel(0, 464);

    public ToolPanel(int posx, int posy, int width, int height, Driver driver) {
        this.driverRef = driver;
        this.toolsLabel = new StyledLabel(0, 0, 128, 50, "TOOLS");
        this.toolsPanel = new ToolsPanel(0, 50, 128, 150, driverRef);
        this.colorPickerLabel = new StyledLabel(0, 200, 128, 50, "COLORS");
        this.colorPickerPanel = new ColorPickerPanel(0, 250, 128, 70, driverRef);
        this.colorPickerButton = new StyledButton(0, 330, 128, 30, "MORE COLORS");
        this.prevColorLabel = new StyledLabel(0, 360,128, 50, "PREVIOUS");
        this.prevColorPanel = new PrevColorPanel(0, 410, 128, 50, driverRef);

        this.setBounds(posx, posy, width, height);
        this.setLayout(null);
        this.setBackground(Color.darkGray);

        this.add(toolsLabel);
        this.add(toolsPanel);
        this.add(colorPickerLabel);
        this.add(colorPickerPanel);
        this.add(colorPickerButton);
        this.add(prevColorLabel);
        this.add(prevColorPanel);
    }
}
