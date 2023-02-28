package sprites.io.UI.buttonStyles;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class StyledButton extends JButton {

    public StyledButton(String iconName, String title) {
        ImageIcon icon = new ImageIcon(iconName);
        createButton(title, 15);
    }

    public StyledButton(String title) {
        createButton(title, 15);
    }

    public StyledButton(int posx, int posy, int width, int height, String title) {
        int marginX = 10;
        this.setBounds(posx + 10, posy, width-20, height);
        createButton(title, 10);
    }

    private void createButton(String title, int fontSize) {
        this.setText(title);
        this.setFocusable(false);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setFont(new Font("Comic Sans", Font.BOLD, fontSize));
        this.setIconTextGap(-15);
        this.setForeground(Color.lightGray);
        this.setBackground(Color.gray);
        this.setBorder(BorderFactory.createEtchedBorder());
    }
}
