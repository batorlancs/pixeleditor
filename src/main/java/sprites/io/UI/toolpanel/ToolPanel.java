package sprites.io.UI.toolpanel;

import sprites.io.UI.MainUI;
import sprites.io.UI.buttonStyles.StyledButton;
import sprites.io.UI.buttonStyles.StyledLabel;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.driver.Driver;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel implements ActionListener {

    private Driver driverRef;
    private MainUI mainUI;
    private Canvas canvas;
    private StyledLabel toolsLabel;
    private ToolsPanel toolsPanel;
    private StyledLabel brushSizeLabel;
    private BrushSizePanel brushSizePanel;
    private StyledLabel colorPickerLabel;
    private ColorPickerPanel colorPickerPanel;
    private StyledButton colorPickerButton;
    private StyledLabel prevColorLabel;
    private PrevColorPanel prevColorPanel;
    private StyledLabel currColorLabel;
    private JPanel currColorPanel;

    public ToolPanel(int posx, int posy, int width, int height, Driver driver, MainUI mainUI, Canvas canvas) {
        this.driverRef = driver;
        this.mainUI = mainUI;
        this.canvas = canvas;
        this.toolsLabel = new StyledLabel(0, 0, 128, 30, "Tools");
        this.toolsPanel = new ToolsPanel(0, 30, 128, 150, driverRef, mainUI, canvas);
        this.brushSizeLabel = new StyledLabel(0, 180, 128, 50, "Brush Size");
        this.brushSizePanel = new BrushSizePanel(0, 220, 128, 30, driverRef, this);
        this.colorPickerLabel = new StyledLabel(0, 260, 128, 30, "Colors");
        this.colorPickerPanel = new ColorPickerPanel(0, 290, 128, 75, driverRef, this);
        this.colorPickerButton = new StyledButton(0, 365, 128, 30, "More Colors", "Pick From More Color Options");
        this.prevColorLabel = new StyledLabel(0, 405,128, 30, "Previous");
        this.prevColorPanel = new PrevColorPanel(0, 435, 128, 50, driverRef, this);

        this.setBounds(posx, posy, width, height);
        this.setLayout(null);
        this.setBackground(Color.darkGray);

        colorPickerButton.addActionListener(this);

        this.add(toolsLabel);
        this.add(toolsPanel);
        this.add(brushSizeLabel);
        this.add(brushSizePanel);
        this.add(colorPickerLabel);
        this.add(colorPickerPanel);
        this.add(colorPickerButton);
        this.add(prevColorLabel);
        this.add(prevColorPanel);

        driverRef.setCurrColor(Color.black);
        updatePrevColors();
    }

    public void updatePrevColors() {
        prevColorPanel.updatePrevColors();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == colorPickerButton) {
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "Pick a color", Color.black);
            driverRef.setCurrColor(color);
            updatePrevColors();
        }
    }
}
