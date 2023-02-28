package sprites.io.UI.layerspanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import sprites.io.UI.canvaspanel.Canvas;
import sprites.io.UI.canvaspanel.Layer;

public class LayersPanel extends JPanel {
    
    private ArrayList<Layer> layers;
    private Canvas canvas;
    
    private JPanel layersContainer;


    public LayersPanel(Canvas canvas){
        
        this.canvas = canvas;
        this.layers = canvas.getLayers();
        setLayout(new BorderLayout());
        setBounds(0, 0, 280, 512);
        setBorder(new TitledBorder(new EtchedBorder(), "Layers"));
        // set title to be centered
        ((TitledBorder)getBorder()).setTitleJustification(TitledBorder.CENTER);

        layersContainer = new JPanel(new GridLayout(0,1));
        //layersScrollPane = new JScrollPane(layersContainer);
        add(layersContainer, BorderLayout.CENTER);

        JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton addLayerButton = new JButton("Add");
        addLayerButton.addActionListener(new AddLayerAction());
        buttonsContainer.add(addLayerButton);

        JButton removeLayerButton = new JButton("Remove");
        removeLayerButton.addActionListener(new RemoveLayerAction());
        buttonsContainer.add(removeLayerButton);

        JButton mergeButton = new JButton("Merge");
        mergeButton.addActionListener(new MergeAction());
        buttonsContainer.add(mergeButton);

        buttonsContainer.setMaximumSize(buttonsContainer.getPreferredSize());
        add(buttonsContainer, BorderLayout.SOUTH);

        updateLayers();
    }


    private void updateLayers(){
        layersContainer.removeAll();
        for(int i = 0; i < layers.size(); i++){
            Layer layer = layers.get(i);

            JPanel layerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            // set the maximum size of the layer panel
            layerPanel.setMaximumSize(layerPanel.getPreferredSize());
            layerPanel.setBorder(new EtchedBorder());
            layerPanel.setBorder(new TitledBorder(new EtchedBorder(), "Layer " + (i+1)));

            JCheckBox layerVisibilityCheckBox = new JCheckBox("Set current", layer.isVisible());
            layerVisibilityCheckBox.addActionListener(new LayerVisibilityAction(layer));
            layerPanel.add(layerVisibilityCheckBox);

            // add checkbox to set layer as selected and add action listener and default value to false
            JCheckBox layerSelectedCheckBox = new JCheckBox("Set selected", layer.isSelected());
            layerSelectedCheckBox.addActionListener(new LayerSelectedAction(layer));
            layerPanel.add(layerSelectedCheckBox);

            layerPanel.add(layer.getNameLabel());

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
        canvas.addLayer();
        // make sure previous layers are not visible
        for(int i = 0; i < layers.size()-1; i++){
            layers.get(i).setVisible(false);
        }
        updateLayers();
        }
    }

    private class LayerVisibilityAction implements ActionListener{
        private Layer layer;
        public LayerVisibilityAction(Layer layer){
            this.layer = layer;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
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
