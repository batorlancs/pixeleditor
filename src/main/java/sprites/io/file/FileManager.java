package sprites.io.file;

import sprites.io.UI.MainUI;
import sprites.io.UI.canvaspanel.Layer;

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
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class FileManager {

    /**
     * Saves current project (only the layers and its colors)
     * @param layers all the layers
     */
    public void saveFile(ArrayList<Layer> layers) {

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
            int i = 0;
            for (Layer layer: layers) {
                i++;
                fileWriter.write(i + "\n");
                for (Color pixel: layer.getAllPixels()) {
                    fileWriter.write(pixel.getRGB() + "\n");
                }
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

    /**
     *
     * @param image the image that has been made
     * @param newWidth the new size
     * @param newHeight the new size
     * @return the new image
     */
    public BufferedImage scaleImage(BufferedImage image, int newWidth, int newHeight){
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        return scaledImage;
    }

    public void exportAsPng(JLabel[] pixels, int width, int height, int size) {
        File chosenFile;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filterPng = new FileNameExtensionFilter("PNG files","png");
        fileChooser.setFileFilter(filterPng);
        int result = fileChooser.showSaveDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            chosenFile = new File(addPngToString(fileChooser.getSelectedFile().getAbsolutePath()));
        }else  return;

        try {
            BufferedImage image = new BufferedImage(width*size, height*size, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < pixels.length; i++) {
                int x = (i % width) * size;
                int y = (i / width) * size;
                for (int posx = x; posx < x + size; posx++) {
                    for (int posy = y; posy < y + size; posy++) {
                        image.setRGB(posx, posy, pixels[i].getBackground().getRGB());
                    }
                }

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
                pixels[counter].setBackground(new Color(rgb));
                counter++;
            }

        } catch (Exception e) {
            System.out.println("There was a problem opening the file");
        }
    }

    /**
     * Opens a file and reads in all the layers and contents of a saved project
     * @param mainUI reference of the MainUI
     * @return the layers read into a list
     */
    public ArrayList<Layer> getLayersFromFile(MainUI mainUI) {

        ArrayList<Layer> fileLayers = new ArrayList<>();

        File chosenFile;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt only", "txt", "text");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            chosenFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
        } else return null;

        try {
            Scanner scan = new Scanner(chosenFile);
            boolean isVisible = true;
            while (scan.hasNextLine()) {
                String layerName = scan.nextLine();
                int i = 0;
                int[] results = new int[2500];
                while (scan.hasNextLine() && i < 2500) {
                    results[i] = Integer.parseInt(scan.nextLine());
                    i++;
                }
                fileLayers.add(new Layer("Layer " + layerName, results, isVisible));
                isVisible = false;
            }

            if (mainUI != null) mainUI.dispose();

        } catch (Exception e) {
            System.out.println("There was a problem opening the file");
        }
        return fileLayers;
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
