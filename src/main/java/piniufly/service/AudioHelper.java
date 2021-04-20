package piniufly.service;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.List;

public class AudioHelper {

    private static List<Clip> activeClips = new ArrayList<Clip>();

    public static void addActiveClip(Clip clip){
        activeClips.add(clip);
    }

    public static void removeActiveClip(Clip clip){
        activeClips.remove(clip);
    }

    public static List<Clip> getActiveClips() {
        return activeClips;
    }
}
