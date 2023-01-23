package sprites.io.UI.mainMenu;

import sprites.io.UI.MainUI;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.file.FileManager;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener
{
    JFrame mainFrame = new JFrame();

    JPanel mainPanel = new JPanel();
    JPanel topPanel = new JPanel();

    JLabel mainMenulbl = new JLabel("PAINT");
    JLabel mainMenuPiclbl = new JLabel();
    JLabel mainMenuPic2lbl = new JLabel();
    JButton resumeBtn = new JButton("RESUME");
    JButton openBtn = new JButton("OPEN");
    JButton newBtn = new JButton("NEW");

    GridLayout mainMenLayout = new GridLayout(1,3);
    GridLayout mainMenuLayoutTop = new GridLayout(2,0);

    private FileManager fileManager = new FileManager();
    private Canvas canvasRef;


    public void createDisplay()
    {
        mainFrame.setSize(384,128);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(mainMenuLayoutTop);
        mainFrame.setResizable(false);

        topPanel.setLayout(mainMenLayout);
        topPanel.add(mainMenuPiclbl);
        topPanel.add(mainMenulbl);
        topPanel.add(mainMenuPic2lbl);

        mainFrame.add(topPanel);

        mainPanel.setLayout(mainMenLayout);
        mainPanel.setBackground(Color.black);

        resumeBtn.setSize(128,128);
        openBtn.setSize(128,128);
        newBtn.setSize(128,128);

        newBtn.addActionListener(this);
        openBtn.addActionListener(this);
        resumeBtn.addActionListener(this);

        mainPanel.add(resumeBtn);
        mainPanel.add(openBtn);
        mainPanel.add(newBtn);

        mainFrame.add(mainPanel);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openBtn) {

            System.out.println("opening file");
            //fileManager.openFile(canvasRef.getPixels());

        }

        if (e.getSource() == newBtn)
        {
            mainFrame.setVisible(false);

            MainUI app = new MainUI();
            app.CreateDisplay();
        }

        if (e.getSource() == resumeBtn){

        }
    }
}
