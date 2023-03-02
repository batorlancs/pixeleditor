package sprites.io.UI.menupanel;

import sprites.io.UI.MainUI;
import sprites.io.UI.buttonStyles.StyledButton;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.file.FileManager;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private StyledButton clear = new StyledButton(10, 16, 120, 32, "Clear Canvas", "Clear The Current Layer", true);
    private StyledButton save = new StyledButton(280, 16, 60, 32, "Save", "Save the Project", true);
    private StyledButton open = new StyledButton(350, 16, 60, 32, "Open", "Open Project from File", true);
    private StyledButton newProject = new StyledButton(420, 16, 100, 32, "New Project", "Create a New Project", true);
    private StyledButton exportPng = new StyledButton(530, 16, 100, 32, "Export", "Export PNG File", true);

    private FileManager fileManager = new FileManager();
    private Canvas canvasRef;
    private MainUI mainUI;

    /**
     *
     * @param posx Position X of panel.
     * @param posy Position Y of panel.
     * @param width Width of panel.
     * @param height Height of panel.
     * @param canvas Reference of Canvas to get Pixel values.
     */
    public MenuPanel(int posx, int posy, int width, int height, Canvas canvas, MainUI mainUI) {
        this.mainUI = mainUI;

        this.setBounds(posx, posy, width, height);
        this.setBackground(new Color(100, 100, 100));
        this.setLayout(null);

        this.canvasRef = canvas;

        newProject.addActionListener(this);
        clear.addActionListener(this);
        save.addActionListener(this);
        open.addActionListener(this);
        exportPng.addActionListener(this);

        this.add(newProject);
        this.add(clear);
        this.add(save);
        this.add(open);
        this.add(exportPng);
    }

    /**
     * Save and Open actions.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            System.out.println("saving file..");
            fileManager.saveFile(canvasRef.getPixels());
        }
        else if (e.getSource() == open) {
            System.out.println("opening file..");
            int[] fileContent = fileManager.getRGB(mainUI);
            if (fileContent != null)
                new MainUI(fileContent);

        }
        else if (e.getSource() == clear) {
            canvasRef.clearCanvas();
        }
        else if (e.getSource() == newProject) {
            int result = JOptionPane.showConfirmDialog(null,
                    "Creating a new project will delete your current project if it is not saved! Do you want to continue?",
                    "New Project",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );
            if (result == 0) {
                mainUI.dispose();
                new MainUI();
            }
        }
        /**
         * allows for the exportPng to be used to save the image at different sizes
         */
        else if (e.getSource() == exportPng){

            Object[] options = {"small(50x50)", "medium(250x250)", "large(500x500)"};
            int n = JOptionPane.showOptionDialog(null,
                    "Choose a size for the image",
                    "File Size",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);

            if(n==0)
            {
                fileManager.exportAsPng(canvasRef.getPixels(),50,50,0,0);
            }
            if(n==1)
            {
                fileManager.exportAsPng(canvasRef.getPixels(),50,50,250,250);
            }
            if(n==2)
            {
                fileManager.exportAsPng(canvasRef.getPixels(),50,50,500,500);
            }

        }
    }
}
