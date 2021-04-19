package piniufly.ui.updatemanager;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkContrastIJTheme;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.ClassLoader.getSystemResource;
import static javax.swing.JOptionPane.showMessageDialog;
import static piniufly.util.Param.APP_NAME;
import static piniufly.util.Param.ASSET_TO_BE_UPDATED;

public class UpdateDialog {

    public static void executeUpdate(String remotePath, String localPath) {

        FlatAtomOneDarkContrastIJTheme.install();

        final JProgressBar pbFile = new JProgressBar();
        pbFile.setValue(0);
        pbFile.setMaximum(100);
        pbFile.setStringPainted(true);
        pbFile.setBorder(BorderFactory.createTitledBorder("Downloading update..."));

        JFrame theFrame = new JFrame(APP_NAME + " updater");
        theFrame.setAlwaysOnTop(true);
        theFrame.setIconImage(new ImageIcon(getSystemResource("icons/airplane.png")).getImage());
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = theFrame.getContentPane();
        contentPane.add(pbFile, BorderLayout.NORTH);

        final AtomicBoolean running = new AtomicBoolean(false);

        running.set(!running.get());

        new Thread(() -> {

            try {
                download(remotePath, localPath, pbFile);
            } catch (Exception ex) {
                showMessageDialog(null, ExceptionUtils.getStackTrace(ex), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

        }).start();

        theFrame.setSize(350, 100);
        theFrame.setLocationRelativeTo(null);
        theFrame.setVisible(true);
    }

    private static void download(String remotePath, String localPath, JProgressBar progressBar) throws Exception {

        BufferedInputStream in = null;
        FileOutputStream out = null;

        try {
            URL url = new URL(remotePath);
            URLConnection conn = url.openConnection();
            int size = conn.getContentLength();

            /*
            if (size < 0) {
                System.out.println("Could not get the file size");
            } else {
                System.out.println("File size: " + size);
            }*/

            in = new BufferedInputStream(url.openStream());
            out = new FileOutputStream(localPath);
            byte data[] = new byte[1024];
            int count;
            double sumCount = 0.0;

            while ((count = in.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);

                sumCount += count;
                if (size > 0) {
                    progressBar.setValue((int) (sumCount / size * 100.0));
                }
            }

        } catch (Exception ex) {
            showMessageDialog(null, ExceptionUtils.getStackTrace(ex), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            if (out != null)
                try {
                    out.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }

            Thread.sleep(3000);

            /* Launch APP */
            Runtime.getRuntime().exec(ASSET_TO_BE_UPDATED, null);

            System.exit(0);


        }
    }
}