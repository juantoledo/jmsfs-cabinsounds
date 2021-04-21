package piniufly.ui.model;

import piniufly.ClipThread;
import piniufly.ui.MotionPanel;

import javax.swing.*;
import java.awt.*;

public class ButtonEntry extends Entry {

    private JButton button;

    boolean isDone = true;

    public ButtonEntry(String title, ImageIcon imageIcon, String clipPath, Container container, String where) {
        super(title, imageIcon, container);

        JPanel panelButton = new MotionPanel(container);
        panelButton.setLayout(new java.awt.GridLayout());

        button = new javax.swing.JButton();
        button.setLayout(new java.awt.GridLayout());
        button.setFont(new java.awt.Font("Tahoma", 0, 9));
        button.setIcon(imageIcon);
        button.setText(title);
        button.setToolTipText(title);
        button.setBorder(null);

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if (isDone) {
                    clipThread = new ClipThread(clipPath, where);
                    clipThread.start();

                }

                isDone = clipThread.isDone();

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
