package sprites.io.UI.canvaspanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class LayersPanel extends JPanel {
  private ArrayList<Layer> layers;
  private Canvas canvas;

  private JPanel layersContainer;
  private JScrollPane layersScrollPane;

  public LayersPanel(Canvas canvas) {
    this.canvas = canvas;
    this.layers = canvas.getLayers();

    setLayout(new BorderLayout());
    // set the size of the panel
    setBounds(50, 10, 100, 500);
    setBorder(new TitledBorder(new EtchedBorder(), "Layers"));

    layersContainer = new JPanel(new GridLayout(0, 1));
    layersScrollPane = new JScrollPane(layersContainer);
    add(layersScrollPane, BorderLayout.CENTER);

    JPanel buttonsContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    add(buttonsContainer, BorderLayout.SOUTH);

    JButton addLayerButton = new JButton("Add Layer");
    addLayerButton.addActionListener(new AddLayerAction());
    buttonsContainer.add(addLayerButton);

    updateLayers();
  }

  private void updateLayers() {
    layersContainer.removeAll();
    for (int i = 0; i < layers.size(); i++) {
      Layer layer = layers.get(i);

      JPanel layerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      layerPanel.setBorder(new EtchedBorder());

      JCheckBox layerVisibilityCheckBox = new JCheckBox("Set visible", layer.isVisible());
      layerVisibilityCheckBox.addActionListener(new LayerVisibilityAction(layer));
      layerPanel.add(layerVisibilityCheckBox);

      layerPanel.add(layer.getNameLabel());

      layersContainer.add(layerPanel);
    }
    revalidate();
  }

  private class AddLayerAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      //Layer layer = canvas.addLayer();
      updateLayers();
    }
  }

  private class LayerVisibilityAction implements ActionListener {
    private Layer layer;

    public LayerVisibilityAction(Layer layer) {
      this.layer = layer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      layer.setVisible(((JCheckBox) e.getSource()).isSelected());
      canvas.repaint();
    }
  }
}

