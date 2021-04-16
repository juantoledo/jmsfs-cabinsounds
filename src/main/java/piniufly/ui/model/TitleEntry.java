package piniufly.ui.model;

import piniufly.ui.MotionPanel;

import javax.swing.*;
import java.awt.*;

public class TitleEntry extends Entry {

    private JLabel label;

    public TitleEntry(String title, ImageIcon imageIcon, Container container) {
        super(title, imageIcon, container);

        JPanel panelLabel = new MotionPanel((JFrame) container);
        JLabel label = new javax.swing.JLabel();

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelLabel.setLayout(new java.awt.GridLayout());

        label.setText(title);

        panelLabel.add(label);
        container.add(panelLabel);

    }

    @Override
    public JComponent getValue() {
        return label;
    }
}
