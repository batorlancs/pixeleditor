package sprites.io.file;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


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

    private String addPngToString(String filePath){
        if(!filePath.endsWith(".png")){
            return filePath + ".png";
        }
        return filePath;
    }

    public void exportAsPng(JLabel[] pixels, int width, int height) {
        File chosenFile;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filterPng = new FileNameExtensionFilter("PNG files","png");
        fileChooser.setFileFilter(filterPng);
        int result = fileChooser.showSaveDialog(null);

    if(result == JFileChooser.APPROVE_OPTION){
            chosenFile = new File(addPngToString(fileChooser.getSelectedFile().getAbsolutePath()));
        }else  return;

    try {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i =0; i<pixels.length; i++){
            JLabel pixel = pixels[i];
            int x = i % width;
            int y = i / width;
            image.setRGB(x,y,pixel.getBackground().getRGB());
        }
        ImageIO.write(image, "png", chosenFile);
        System.out.println("file exported as png");
    } catch (IOException e){
        e.printStackTrace();
        System.out.println("file export fail");
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

        } catch (Exception e) {
            System.out.println("There was a problem opening the file");
        }
    }

    /**
     * This is used to open a file from the main menu as this has to create a canvas that is already filled in
     * @return this returns the values that are the pixels so it can be filled in.
     */
    public int[] getRGB() {

        int results[] = new int[2500];

        File chosenFile;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt only", "txt", "text");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            chosenFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
        } else return null;

        int i = 0;
        try {
            Scanner scan = new Scanner(chosenFile);
            while (scan.hasNextLine()) {
                results[i] = Integer.parseInt(scan.nextLine());
                i++;
                System.out.println(results);
            }

        } catch (Exception e) {
            System.out.println("There was a problem opening the file");
        }
        return results;
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
