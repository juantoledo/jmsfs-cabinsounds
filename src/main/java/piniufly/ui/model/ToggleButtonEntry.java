package piniufly.ui.model;

import piniufly.ClipThread;
import piniufly.ui.MotionPanel;

import javax.swing.*;
import java.awt.*;

public class ToggleButtonEntry extends Entry {

    private JToggleButton button;

    boolean isDone = true;

    public ToggleButtonEntry(String title, ImageIcon imageIcon, String clipPath, Container container) {
        super(title, imageIcon, container);

        JPanel panelButton = new MotionPanel(container);
        panelButton.setLayout(new java.awt.GridLayout());

        button = new javax.swing.JToggleButton();
        button.setLayout(new java.awt.GridLayout());
        button.setForeground(Color.WHITE);
        button.setFont(new java.awt.Font("Tahoma", 0, 9));
        button.setIcon(imageIcon);
        button.setText(title + " ");
        button.setToolTipText(title);
        button.setFocusable(false);

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if(!((AbstractButton) evt.getSource()).isSelected()){
                    clipThread.stop();
                }else {
                    clipThread = new ClipThread(clipPath, button);
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
