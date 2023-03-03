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

public class MainMenu extends JPanel implements ActionListener
{
    private JFrame mainFrame = new JFrame();

    private JPanel topPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JLabel mainMenulbl = new JLabel("SPRITES.IO");
    private StyledButton openBtn = new StyledButton("Open");
    private StyledButton newBtn = new StyledButton("New");

    private FileManager fileManager = new FileManager();

    public MainMenu() {
        this.createDisplay();
    }

    public void createDisplay()
    {
        mainFrame.setSize(400,128);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(2, 1));
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(Color.darkGray);

        mainMenulbl.setForeground(Color.white);
        mainMenulbl.setHorizontalAlignment(JLabel.CENTER);
        mainMenulbl.setFont(new Font("Comic Sans", Font.BOLD, 15));

        topPanel.setLayout(new GridLayout(0, 1));
        topPanel.setBackground(Color.darkGray);
        topPanel.add(mainMenulbl);

        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 50));
        buttonPanel.setBackground(Color.darkGray);

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
