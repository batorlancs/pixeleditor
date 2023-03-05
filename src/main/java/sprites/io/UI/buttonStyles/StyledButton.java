package sprites.io.UI.buttonStyles;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StyledButton extends JButton {

    /**
     * button with icon and text
     * @param iconName name of image file
     * @param title text to be displayed
     * @param toolTip set toolTip text
     */
    public StyledButton(String iconName, String title, String toolTip) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/" + iconName)));
        Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(image));
        this.setToolTipText(toolTip);
        createButton(title, 15);
    }

    /**
     * button with icon and icon size
     * @param iconName name of image file
     * @param iconSize size of displayed icon
     * @param toolTip set toolTip text
     */
    public StyledButton(String iconName, int iconSize, String toolTip) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/" + iconName)));
        Image image = icon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(image));
        this.setToolTipText(toolTip);
        createButton("", 15);
    }

    /**
     * button with only text
     * @param title text to be displayed
     */
    public StyledButton(String title) {
        createButton(title, 15);
    }

    public StyledButton(String title, boolean isOpaque) {
        createButton(title, 15);
        this.setBackground(new Color(209, 75, 75));
        this.setForeground(Color.white);
    }

    /**
     * button with position, dimensions and a text
     * @param posx position x
     * @param posy position y
     * @param width width of button
     * @param height height of button
     * @param title text to be displayed
     * @param toolTip set toolTip text
     */
    public StyledButton(int posx, int posy, int width, int height, String title, String toolTip) {
        this.setBounds(posx + 10, posy, width-20, height);
        this.setToolTipText(toolTip);
        createButton(title, 12);
    }

    public StyledButton(int posx, int posy, int width, int height, String title, String toolTip, boolean isMenuPanel) {
        if (isMenuPanel)
            this.setBounds(posx, posy, width, height);
        else
            this.setBounds(posx + 10, posy, width-20, height);

        this.setToolTipText(toolTip);
        createButton(title, 12);
        this.setForeground(Color.white);
        this.setBackground(Color.darkGray);
    }

    /**
     * create the styled button format
     * @param title text to be displayed
     * @param fontSize size of text font
     */
    private void createButton(String title, int fontSize) {
        this.setText(title);
        this.setFocusable(false);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setFont(new Font("Comic Sans", Font.PLAIN, fontSize));
        this.setIconTextGap(-15);
        this.setForeground(Color.black);
        this.setBackground(Color.gray);
        this.setBorder(BorderFactory.createEtchedBorder());
    }

    // method to change the button's icon path
    public void setNewIcon(String iconName) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/" + iconName)));
        Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(image));
    }
}
