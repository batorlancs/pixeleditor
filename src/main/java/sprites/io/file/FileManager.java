package sprites.io.file;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt only", "txt", "text");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            chosenFile = new File(addTxtToString(fileChooser.getSelectedFile().getAbsolutePath()));
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

        File chosenFile;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt only", "txt", "text");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            chosenFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
        } else return;

        try {
            Scanner scan = new Scanner(chosenFile);
            int counter = 0;
            while (scan.hasNextLine()) {
                int rgb = Integer.parseInt(scan.nextLine());
                System.out.println(rgb);
                pixels[counter].setBackground(new Color(rgb));
                counter++;
            }
            scan.close();

        } catch (Exception e) {
            System.out.println("There was a problem opening the file");
        }
    }

    /**
     * Get a file name and make it to .txt extension
     * @param fileName the name to add the .txt extension to
     * @return final string with .txt format
     */
    public String addTxtToString(String fileName) {
        if (fileName.equals("")) {
            return "sprites.txt";
        }
        if (fileName.contains(".txt")) {
            return fileName;
        }
        String newFileName = fileName;
        if (fileName.contains(".")) {
            newFileName = newFileName.substring(0, fileName.indexOf("."));
        }
        return newFileName.concat(".txt");
    }

}
