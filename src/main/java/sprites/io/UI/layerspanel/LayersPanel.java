package sprites.io.UI.layerspanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import sprites.io.UI.MainUI;
import sprites.io.UI.buttonStyles.StyledButton;
import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.canvaspanel.Layer;

public class LayersPanel extends JPanel {
    
    private ArrayList<Layer> layers;
    private Canvas canvas;
    private MainUI mainUI;
    
    private JPanel layersContainer;


    public LayersPanel(Canvas canvas, MainUI mainUI){
        this.mainUI = mainUI;
        this.canvas = canvas;
        this.layers = canvas.getLayers();
        setLayout(new BorderLayout());
        setBounds(768, 0, 280, 560);
        this.setBackground(Color.darkGray);
        TitledBorder mainTitledBorder = new TitledBorder(new EtchedBorder(), "Layers");
        mainTitledBorder.setTitleColor(Color.white);
        setBorder(mainTitledBorder);
        // set title to be centered
        ((TitledBorder)getBorder()).setTitleJustification(TitledBorder.CENTER);

        layersContainer = new JPanel(new GridLayout(0,1));
        layersContainer.setBackground(Color.darkGray);
        //layersScrollPane = new JScrollPane(layersContainer);
        add(layersContainer, BorderLayout.CENTER);

//        JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel buttonsContainer = new JPanel(new GridLayout(0, 3, 10, 10));
        buttonsContainer.setBackground(Color.darkGray);
        
        StyledButton addLayerButton = new StyledButton("Add");
        addLayerButton.addActionListener(new AddLayerAction());
        buttonsContainer.add(addLayerButton);

        StyledButton removeLayerButton = new StyledButton("Remove");
        removeLayerButton.addActionListener(new RemoveLayerAction());
        buttonsContainer.add(removeLayerButton);

        StyledButton mergeButton = new StyledButton("Merge");
        mergeButton.addActionListener(new MergeAction());
        buttonsContainer.add(mergeButton);

        buttonsContainer.setMaximumSize(buttonsContainer.getPreferredSize());
        add(buttonsContainer, BorderLayout.SOUTH);

        updateLayers();
    }


    public void updateLayers(){
        layersContainer.removeAll();
        for(int i = 0; i < layers.size(); i++){
            Layer layer = layers.get(i);

            Color backgroundColor = Color.darkGray;
            Color titleColor = new Color(100, 100, 100);
            Color selectedColor = Color.gray;
            String selectedText = "Not Selected";
            EtchedBorder etchedBorder = new EtchedBorder();
            if (layer.isSelected()) {
                backgroundColor = new Color(80, 80, 80);
                titleColor = Color.lightGray;
                selectedColor = new Color(123, 245, 86);
                selectedText = "Selected";
            }
            if (layer.isVisible()) {
                etchedBorder = new EtchedBorder(EtchedBorder.RAISED, Color.white, Color.white);
            }

            JPanel layerPanel = new JPanel();
            layerPanel.setLayout(null);
            // set the maximum size of the layer panel
            layerPanel.setMaximumSize(layerPanel.getPreferredSize());
            TitledBorder titleBorder = new TitledBorder(etchedBorder, "Layer " + (i+1));
            titleBorder.setTitleColor(titleColor);
            layerPanel.setBorder(titleBorder);
            layerPanel.setBackground(backgroundColor);
            layerPanel.addMouseListener(new LayerPanelMouseListener(layer));

            // add checkbox to set layer as selected and add action listener and default value to false
            JCheckBox layerSelectedCheckBox = new JCheckBox(selectedText, layer.isSelected());
            layerSelectedCheckBox.setBounds(80, 25, 100, 20);
            layerSelectedCheckBox.setBackground(backgroundColor);
            layerSelectedCheckBox.setForeground(selectedColor);
            layerSelectedCheckBox.setFocusable(false);
            layerSelectedCheckBox.addActionListener(new LayerSelectedAction(layer));
            layerPanel.add(layerSelectedCheckBox);

            JPanel layerPreviewPanel = new JPanel();
            layerPreviewPanel.setBounds(15, 25, 50, 50);
            layerPreviewPanel.setLayout(new GridLayout(50, 50));
            JLabel[] pixels = new JLabel[2500];
            Layer backgroundLayer = new Layer("Background");
            backgroundLayer.makeTransparentBackground();
            backgroundLayer.merge(layer);
            for (int j = 0; j < 2500; j++) {
                pixels[j] = new JLabel();
                pixels[j].setBackground(backgroundLayer.getPixel(j));
                pixels[j].setOpaque(true);
                layerPreviewPanel.add(pixels[j]);
            }

            layerPanel.add(layerPreviewPanel);

            layersContainer.add(layerPanel);
        }
        layersContainer.revalidate();
    }
    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
        this.layers = canvas.getLayers();
    }

    private class AddLayerAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (layers.size() < 6) {
                canvas.addLayer();
                // make sure previous layers are not visible
                for(int i = 0; i < layers.size()-1; i++){
                    layers.get(i).setVisible(false);
                }
                updateLayers();
            }

        }
    }

    private class LayerPanelMouseListener implements MouseListener {
        private Layer layer;
        public LayerPanelMouseListener(Layer layer) {
            this.layer = layer;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            layer.setVisible(!layer.isVisible());
            // make sure all other layers are not visible
            for(Layer l : layers){
                if(l != layer){
                    l.setVisible(false);
                }
            }
            // get the layer index for the current layer
            int layerIndex = layers.indexOf(layer);
            canvas.setCurrentLayer(layerIndex);
            updateLayers();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class LayerSelectedAction implements ActionListener{
        private Layer layer;
        public LayerSelectedAction(Layer layer){
            this.layer = layer;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            layer.setSelected(!layer.isSelected());
            canvas.repaint();
            canvas.updateCanvas();
            updateLayers();
        }
    }

    private class RemoveLayerAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.removeLayer();
            updateLayers();
        }
    }

    private class MergeAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.mergeLayers();
            updateLayers();
        }
    }


}
