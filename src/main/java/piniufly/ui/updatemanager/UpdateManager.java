package piniufly.ui.updatemanager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.ClassLoader.getSystemResource;
import static piniufly.util.Param.*;

public class UpdateManager {

    public static void checkForUpdates() throws Exception {


        String latest = UpdateManager.getLatestVersion();
        if (latest.compareTo(APP_VERSION) != 0) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to update to the latest version (" + latest + ") ?", "Update available",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon(getSystemResource("icons/airplane"))) == 0) {

                /* Launch APP */
                Runtime.getRuntime().exec(UPDATER_EXE, null);

                System.exit(0);

            }

        }
    }

    public static String getLatestVersion() throws Exception {
        URL url = new URL(REPO_REF);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();


        con.addRequestProperty("Accept", "application/vnd.github.v3+json");
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/vnd.github.v3+json");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        Gson gson = new Gson();

        String tagName = gson.fromJson(content.toString(), JsonObject.class).get("tag_name").getAsString();

        return tagName;
    }


}
