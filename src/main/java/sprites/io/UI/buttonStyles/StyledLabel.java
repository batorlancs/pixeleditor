package sprites.io.UI.buttonStyles;

import javax.swing.*;
import java.awt.*;

public class StyledLabel extends JLabel {
    public StyledLabel(int posx,int posy, int width, int height, String title) {
        this.setText(title);
        this.setBounds(posx, posy, width, height);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("Dialog", Font.BOLD, 15));
        this.setForeground(Color.lightGray);
    }
}
