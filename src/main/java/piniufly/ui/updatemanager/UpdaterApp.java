package piniufly.ui.updatemanager;

import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;
import static piniufly.util.Param.ASSET_TO_BE_UPDATED;
import static piniufly.util.Param.ASSET_URL;

public class UpdaterApp {
    public static void main(String[] args) {

        try {

            String assetURL = ASSET_URL + UpdateManager.getLatestVersion() + "/" + ASSET_TO_BE_UPDATED;

            if (assetURL == null) {
                showMessageDialog(null, ASSET_TO_BE_UPDATED + "not found", "Error getting update file", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

            UpdateDialog.executeUpdate(assetURL, ASSET_TO_BE_UPDATED);

        } catch (Exception ex) {
            showMessageDialog(null, ExceptionUtils.getStackTrace(ex), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }


    }
}
