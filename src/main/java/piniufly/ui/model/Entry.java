package piniufly.ui.model;

import piniufly.ClipThread;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public abstract class Entry {

    protected String title;

    protected ImageIcon icon;

    protected Container container;

    protected ClipThread clipThread;

    public Entry(String title, ImageIcon imageIcon, Container containingPanel){
        this.title = title;
        this.icon = imageIcon;
        this.container = containingPanel;
    }

    public abstract JComponent getValue();

    public ClipThread getClipThread() {
        return clipThread;
    }
}
