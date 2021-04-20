package piniufly.ui.model;

import javax.swing.*;

public class PlayableIcon {

    private ImageIcon playingIcon;

    private ImageIcon stoppedIcon;

    public PlayableIcon(ImageIcon stoppedIcon, ImageIcon playingIcon ) {
        this.playingIcon = playingIcon;
        this.stoppedIcon = stoppedIcon;
    }

    public ImageIcon getPlayingIcon() {
        return playingIcon;
    }

    public ImageIcon getStoppedIcon() {
        return stoppedIcon;
    }
}
