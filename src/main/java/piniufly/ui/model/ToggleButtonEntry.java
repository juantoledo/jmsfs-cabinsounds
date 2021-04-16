package piniufly.ui.model;

import piniufly.ClipThread;
import piniufly.ui.MotionPanel;

import javax.swing.*;
import java.awt.*;

public class ToggleButtonEntry extends Entry {

    private JToggleButton button;

    ClipThread clip;

    boolean isDone = true;

    public ToggleButtonEntry(String title, ImageIcon imageIcon, String clipPath, Container container) {
        super(title, imageIcon, container);

        JPanel panelButton = new MotionPanel((JFrame) container);
        panelButton.setLayout(new java.awt.GridLayout());

        button = new javax.swing.JToggleButton();
        button.setLayout(new java.awt.GridLayout());
        button.setForeground(Color.WHITE);
        button.setFont(new java.awt.Font("Tahoma", 0, 9));
        button.setIcon(imageIcon);
        button.setText(title + " ");
        button.setToolTipText(title);
        button.setFocusable(false);
        //button.setBorder(null);

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                if(!((AbstractButton) evt.getSource()).isSelected()){
                    clip.stop();
                }else {
                    //button.setText("->" + button.getText());
                    clip = new ClipThread(clipPath, button);
                    clip.start();
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
