package piniufly.ui.model;

import javax.swing.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.lang.ClassLoader.getSystemResource;
import static piniufly.util.Param.PLAY_SUFFIX;

public class IconHelper {

    private static String[] tagsMusic = {"music"};

    private static String[] tagsSafety = {"safety", "security"};

    private static String[] tagsTaxi = {"pushback", "taxi"};

    private static String[] tagsSeatBelt = {"seatbelt"};

    private static String[] tagsTakeOff = {"takeoff"};

    private static String[] tagsLanding = {"landing"};

    private static String[] tagsAtc = {"atc"};

    private static Map<PlayableIcon, String[]> map = new HashMap<>();

    static {
        map.put(new PlayableIcon(new ImageIcon(getSystemResource("icons/music")), new ImageIcon(getSystemResource("icons/music" + PLAY_SUFFIX))), tagsMusic);
        map.put(new PlayableIcon(new ImageIcon(getSystemResource("icons/shield-account")), new ImageIcon(getSystemResource("icons/shield-account" + PLAY_SUFFIX))), tagsSafety);
        map.put(new PlayableIcon(new ImageIcon(getSystemResource("icons/road-variant")), new ImageIcon(getSystemResource("icons/road-variant" + PLAY_SUFFIX))), tagsTaxi);
        map.put(new PlayableIcon(new ImageIcon(getSystemResource("icons/seatbelt")), new ImageIcon(getSystemResource("icons/seatbelt" + PLAY_SUFFIX))), tagsSeatBelt);
        map.put(new PlayableIcon(new ImageIcon(getSystemResource("icons/airplane-takeoff")), new ImageIcon(getSystemResource("icons/airplane-takeoff" + PLAY_SUFFIX))), tagsTakeOff);
        map.put(new PlayableIcon(new ImageIcon(getSystemResource("icons/airplane-landing")), new ImageIcon(getSystemResource("icons/airplane-landing" + PLAY_SUFFIX))), tagsLanding);
        map.put(new PlayableIcon(new ImageIcon(getSystemResource("icons/airport")), new ImageIcon(getSystemResource("icons/airport" + PLAY_SUFFIX))), tagsAtc);

    }


    public static PlayableIcon determineIcon(String criteria) {

        for (Map.Entry<PlayableIcon, String[]> entry : map.entrySet()) {

            String[] options = entry.getValue();
            for (String option : options) {
                if (criteria.toLowerCase(Locale.ROOT).contains(option)) {
                    return entry.getKey();
                }
            }
        }

        return new PlayableIcon(new ImageIcon(getSystemResource("icons/general")), new ImageIcon(getSystemResource("icons/general" + PLAY_SUFFIX)));
    }

}
