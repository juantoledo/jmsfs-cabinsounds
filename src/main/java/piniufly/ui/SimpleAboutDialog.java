package piniufly.ui;

import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import static java.lang.ClassLoader.getSystemResource;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static piniufly.util.Param.*;

public class SimpleAboutDialog extends JDialog {
    public SimpleAboutDialog(JFrame parent) {
        super(parent, "About " + APP_NAME, true);

        ImageIcon img = new ImageIcon(getSystemResource("icons/airplane.png"));
        this.setIconImage(img.getImage());

        JLabel linkLabel = new JLabel("<html><a href='" + APP_SITE + "'>" + APP_SITE + "</a></html>");
        linkLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                try {
                    open(new URI(APP_SITE));
                } catch (Exception ex) {
                    showMessageDialog(null, ExceptionUtils.getStackTrace(ex), ex.getClass().getName(), ERROR_MESSAGE);
                }
            }
        });
        Box b = Box.createVerticalBox();
        b.add(Box.createGlue());
        b.add(new JLabel(APP_NAME));
        b.add(new JLabel(APP_DESCRIPTION));
        b.add(linkLabel);

        b.add(Box.createGlue());
        getContentPane().add(b, "Center");

        JPanel p2 = new JPanel();
        JButton ok = new JButton("Ok");
        p2.add(ok);
        getContentPane().add(p2, "South");

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
            }
        });

        setSize(480, 150);
        setAlwaysOnTop(true);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
    }

    private static void open(URI uri) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(uri);
        } else { /* TODO: error handling */ }
    }

    public static void main(String[] args) {
        JDialog f = new SimpleAboutDialog(new JFrame());
        f.show();
    }
}
