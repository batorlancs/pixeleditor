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
     * @param layers all the layers to be saved
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

            // first line which layer is current
            for (int i = 0; i < layers.size(); i++) {
                if (layers.get(i).isVisible()) {
                    fileWriter.write(i + "\n");
                    break;
                }
            }
//
//            // next 6 lines which layers are selected
            for (Layer layer: layers) {
                if (layer.isSelected())
                    fileWriter.write(1 + "\n");
                else
                    fileWriter.write(0 + "\n");
            }
            if (layers.size() < 6) {
                int remainder = 6-layers.size();
                for (int i = 0; i < remainder; i++) {
                    fileWriter.write(0 + "\n");
                }
            }

            // fill with each layer
            int i = 0;
            for (Layer layer: layers) {
                i++;
                fileWriter.write(i + "\n");
                for (Color pixel: layer.getAllPixels()) {
                    int rgb = 0;
                    if (pixel != null) {
                        rgb = pixel.getRGB();
                    }
                    fileWriter.write(rgb + "\n");
                }
            }
            fileWriter.close();
            System.out.println("file writing successful");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file writing unsuccessful");
        }
    }

    /**
     * Add Png extension to a string
     * @param filePath string to put png on
     * @return filename with png extension
     */
    private String addPngToString(String filePath){
        if(!filePath.endsWith(".png")){
            return filePath + ".png";
        }
        return filePath;
    }

    /**
     * export project as png
     * @param pixels current pixels that are displayed on the canvas
     * @param width width of canvas
     * @param height height of canvas
     * @param size size of image (multiplies the width/height of canvas)
     */
    public void exportAsPng(JLabel[] pixels, boolean[] transparentPixels, int width, int height, int size) {
        int transparent = 0x00FFFFFF;
        File chosenFile;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filterPng = new FileNameExtensionFilter("PNG files","png");
        fileChooser.setFileFilter(filterPng);
        int result = fileChooser.showSaveDialog(null);

        if(result == JFileChooser.APPROVE_OPTION){
            chosenFile = new File(addPngToString(fileChooser.getSelectedFile().getAbsolutePath()));
        }else  return;

        try {
            BufferedImage image = new BufferedImage(width*size, height*size, BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < pixels.length; i++) {
                int x = (i % width) * size;
                int y = (i / width) * size;
                for (int posx = x; posx < x + size; posx++) {
                    for (int posy = y; posy < y + size; posy++) {
                        if (transparentPixels[i]) image.setRGB(posx, posy, transparent);
                        else image.setRGB(posx, posy, pixels[i].getBackground().getRGB());
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
     * Opens a file and reads in all the layers and contents of a saved project
     * @param mainUI reference of the MainUI
     * @return the layers read into a list
     */
    public ArrayList<Layer> getLayersFromFile(MainUI mainUI) {

        ArrayList<Layer> fileLayers = new ArrayList<>();
        int visible = 0;
        boolean[] selected = new boolean[6];


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

            // first line layer visible
            if (scan.hasNextLine()) {
                visible = Integer.parseInt(scan.nextLine());
            }

            // next 6 lines are which layers are selected
            int k = 0;
            while (scan.hasNextLine() && k < 6) {
                selected[k] = Integer.parseInt(scan.nextLine()) != 0;
                k++;
            }

            int layerNum = 0;
            while (scan.hasNextLine()) {
                String layerName = scan.nextLine();
                int i = 0;
                int[] results = new int[2500];
                while (scan.hasNextLine() && i < 2500) {
                    results[i] = Integer.parseInt(scan.nextLine());
                    i++;
                }
                fileLayers.add(new Layer("Layer " + layerName, results, visible == layerNum, selected[layerNum]));
                layerNum++;
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
