package sprites.io.UI.mainMenu;

import sprites.io.UI.MainUI;
import sprites.io.UI.buttonStyles.StyledButton;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.canvaspanel.Layer;
import sprites.io.file.FileManager;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenu extends JPanel implements ActionListener
{
    private JFrame mainFrame = new JFrame();

    private JPanel topPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JLabel mainMenulbl = new JLabel();
    private ImageIcon appIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/AppIcon.png")));
    private Color backgroundColor = Color.darkGray;
    private StyledButton openBtn = new StyledButton("Open", false);
    private StyledButton newBtn = new StyledButton("New", false);

    private FileManager fileManager = new FileManager();

    public MainMenu() {
        this.createDisplay();
    }

    public void createDisplay()
    {
        mainFrame.setSize(400,400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(backgroundColor);
        mainFrame.setIconImage(appIcon.getImage());
        mainFrame.setTitle("Sprites.io");

        Image image = appIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        appIcon = new ImageIcon(image);
        mainMenulbl.setIcon(appIcon);

        topPanel.setBounds(50, 25, 300, 225);
        topPanel.setBackground(backgroundColor);
        topPanel.add(mainMenulbl);

        buttonPanel.setBounds(45, 275, 300, 40);
        buttonPanel.setLayout(new GridLayout(1, 2, 50, 0));
        buttonPanel.setBackground(backgroundColor);

        newBtn.addActionListener(this);
        openBtn.addActionListener(this);

        buttonPanel.add(openBtn);
        buttonPanel.add(newBtn);

        mainFrame.add(topPanel);
        mainFrame.add(buttonPanel);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openBtn) {

            System.out.println("opening file..");
            mainFrame.dispose();
            ArrayList<Layer> fileContent = fileManager.getLayersFromFile(null);
            if (fileContent != null)
                new MainUI(fileContent);
            else
                new MainMenu();

        }

        if (e.getSource() == newBtn)
        {
            mainFrame.dispose();
            new MainUI();
        }
    }

}
