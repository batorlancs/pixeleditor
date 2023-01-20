package sprites.io.file;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    /**
     * Saves file to the given filename.
     * @param pixels Pixels of the canvas to save each RGB value.
     */
    public void saveFile(JLabel[] pixels) {

        File chosenFile;
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            chosenFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
        } else return;

        try {
            FileWriter fileWriter = new FileWriter(chosenFile);
            for (JLabel pixel : pixels) {
                fileWriter.write(pixel.getBackground().getRGB() + "\n");
            }
            fileWriter.close();
            System.out.println("file writing successful");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file writing unsuccessful");
        }
    }

    /**
     * Opens file by the given filename.
     * @param pixels Pixels of the canvas to change the background of each pixel.
     */
    public void openFile(JLabel[] pixels) {
//        try {
//            File file = new File();
//            Scanner scan = new Scanner(file);
//            int counter = 0;
//            while (scan.hasNextLine()) {
//                int rgb = Integer.parseInt(scan.nextLine());
//                System.out.println(rgb);
//                pixels[counter].setBackground(new Color(rgb));
//                counter++;
//            }
//
//        } catch (Exception e) {
//            System.out.println("There was a problem opening the file");
//        }
    }
}
