package piniufly.service;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static javax.sound.sampled.FloatControl.Type.MASTER_GAIN;

public class AudioHelper {

    private static Map<String, ArrayList<Clip>> activeClips = new HashMap<String, ArrayList<Clip>>();

    public static void addActiveClip(String where, Clip clip) {
        if (isNull(activeClips.get(where))) {
            activeClips.put(where, new ArrayList<Clip>());
        }
        activeClips.get(where).add(clip);
    }

    public static void removeActiveClip(String where, Clip clip) {
        if (!isNull(activeClips.get(where))) {
            activeClips.get(where).remove(clip);
        }
    }

    public static List<Clip> getActiveClips(String where) {
        if (!isNull(activeClips.get(where))) {
            return activeClips.get(where);
        }
        return new ArrayList<Clip>();
    }

    public static final int MIN_GAIN = -80;

    public static final int MAX_GAIN = 6;

    public static int CURRENT_GAIN_VALUE;

    public static float limit(FloatControl control, float level) {
        return Math.min(control.getMaximum(), Math.max(control.getMinimum(), level));
    }

    public static void setGain(Clip clip) {
        ((FloatControl) clip.getControl(MASTER_GAIN)).setValue(limit(((FloatControl) clip.getControl(MASTER_GAIN)), CURRENT_GAIN_VALUE));
    }

    public static void stopAll(String where) {
        getActiveClips(where).forEach((clip) -> {
            clip.stop();
        });
    }

}
