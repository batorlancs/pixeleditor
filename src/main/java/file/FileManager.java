package file;

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
     * @param fileName Save to this filename.
     */
    public void saveFile(JLabel[] pixels, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
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
     * @param fileName Open this filename.
     */
    public void openFile(JLabel[] pixels, String fileName) {
        try {
            File file = new File(fileName);
            Scanner scan = new Scanner(file);
            int counter = 0;
            while (scan.hasNextLine()) {
                int rgb = Integer.parseInt(scan.nextLine());
                System.out.println(rgb);
                pixels[counter].setBackground(new Color(rgb));
                counter++;
            }

        } catch (Exception e) {
            System.out.println("There was a problem opening the file");
        }
    }
}
