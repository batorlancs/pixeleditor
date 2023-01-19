package sprites.io;

import sprites.io.panels.Canvas;
import sprites.io.panels.MenuPanel;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testUI
{
    JFrame mainFrame = new JFrame();

    sprites.io.panels.Canvas canvas = new Canvas(70, 6, 500, 500);
    JPanel canvasPanel = new JPanel();
    JPanel toolPanel = new JPanel();
    GridLayout toolbarLayout = new GridLayout(8,2);
    JPanel layerPanel = new JPanel();

    JButton draw = new JButton("DRAW");
    JButton size = new JButton("SIZE");
    JButton line = new JButton("LINE");

    //other things
    MenuPanel menuPanel = new MenuPanel(0, 0, 1024, 64, canvas);

    public void CreateDisplay()
    {
        mainFrame.setSize(1024,640);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        canvasPanel.setBackground(Color.BLACK);
        canvasPanel.setBounds(128,64,640,512);
        canvasPanel.setLayout(null);
        canvasPanel.add(canvas);

        toolPanel.setBackground(Color.red);
        toolPanel.setBounds(0,64,128,512);
        toolPanel.setLayout(toolbarLayout);
        toolPanel.add(draw);
        toolPanel.add(size);
        toolPanel.add(line);


        layerPanel.setBackground(Color.blue);
        layerPanel.setBounds(640,64,384,512);

        mainFrame.add(canvasPanel);
        mainFrame.add(toolPanel);
        mainFrame.add(layerPanel);
        mainFrame.add(menuPanel);

        mainFrame.setVisible(true);
    }





}