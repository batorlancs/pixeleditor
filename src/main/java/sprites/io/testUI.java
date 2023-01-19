package sprites.io;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testUI
{
    JFrame mainFrame = new JFrame();

    Canvas canvas = new Canvas(70, 6, 500, 500);
    JPanel canvasPanel = new JPanel();
    JPanel toolPanel = new JPanel();
    GridLayout toolbarLayout = new GridLayout(8,2);
    JPanel menuPanel = new JPanel();
    JPanel layerPanel = new JPanel();
   
    JButton open = new JButton();

    JButton draw = new JButton("DRAW");
    JButton size = new JButton("SIZE");
    JButton line = new JButton("LINE");


    public void CreateDisplay()
    {
        mainFrame.setSize(1024,640);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);

        menuPanel.setBackground(Color.GREEN);
        menuPanel.setBounds(0,0,1024,64);
        
        menuPanel.add(open);

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