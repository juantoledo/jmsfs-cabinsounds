package piniufly.ui.model;

import piniufly.ClipThread;
import piniufly.ui.MotionPanel;

import javax.swing.*;
import java.awt.*;

public class ToggleButtonEntry extends Entry {

    private PlayableToggleButton button;

    boolean isDone = true;

    public ToggleButtonEntry(String title, ImageIcon imageIcon, ImageIcon playing, String clipPath, Container container, String where) {
        super(title, imageIcon, container);

        JPanel panelButton = new MotionPanel(container);
        panelButton.setLayout(new java.awt.GridLayout());

        button = new PlayableToggleButton(imageIcon, playing);
        button.setLayout(new java.awt.GridLayout());
        button.setFont(new java.awt.Font("Tahoma", 0, 9));
        button.setIcon(imageIcon);
        button.setText(title);
        button.setToolTipText(title);
        button.setFocusable(false);

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if (!((AbstractButton) evt.getSource()).isSelected()) {
                    clipThread.stop();
                    button.setStopped();

                } else {
                    clipThread = new ClipThread(clipPath, button, where);
                    clipThread.start();

                }
            }
        });

        panelButton.add(button);
        container.add(panelButton);

    }

    @Override
    public JComponent getValue() {
        return button;
    }
}
