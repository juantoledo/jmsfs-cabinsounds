package piniufly.ui.model;

import javax.swing.*;
import java.awt.*;

public abstract class Entry {

    protected String title;

    protected ImageIcon icon;

    protected Container container;

    public Entry(String title, ImageIcon imageIcon, Container containingPanel){
        this.title = title;
        this.icon = imageIcon;
        this.container = containingPanel;
    }

    public abstract JComponent getValue();

}
