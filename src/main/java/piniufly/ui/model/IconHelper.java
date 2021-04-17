package piniufly.ui.model;

import javax.swing.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.lang.ClassLoader.getSystemResource;

public class IconHelper {


    private static String[] tagsMusic = {"music"};

    private static String[] tagsSafety = {"safety", "security"};

    private static String[] tagsTaxi = {"pushback", "taxi"};

    private static String[] tagsSeatBelt = {"seatbelt"};

    private static String[] tagsTakeOff = {"takeoff"};

    private static String[] tagsLanding = {"landing"};

    private static String[] tagsAtc = {"atc"};

    private static Map<ImageIcon, String[]> map = new HashMap<>();

    static {
        map.put(new ImageIcon(getSystemResource("icons/music.png")), tagsMusic);
        map.put(new ImageIcon(getSystemResource("icons/shield-account.png")), tagsSafety);
        map.put(new ImageIcon(getSystemResource("icons/road-variant.png")), tagsTaxi);
        map.put(new ImageIcon(getSystemResource("icons/seatbelt.png")), tagsSeatBelt);
        map.put(new ImageIcon(getSystemResource("icons/airplane-takeoff.png")), tagsTakeOff);
        map.put(new ImageIcon(getSystemResource("icons/airplane-landing.png")), tagsLanding);
        map.put(new ImageIcon(getSystemResource("icons/airport.png")), tagsAtc);

    }


    public static ImageIcon determineIcon(String criteria) {

        for (Map.Entry<ImageIcon, String[]> entry : map.entrySet()) {

            String[] options = entry.getValue();
            for (String option : options) {
                if (criteria.toLowerCase(Locale.ROOT).contains(option)) {
                    return entry.getKey();
                }
            }
        }
        return new ImageIcon(getSystemResource("icons/general.png"));
    }

}
