package piniufly.ui.model;

import javax.swing.*;

public class PlayableToggleButton extends JToggleButton {

    private ImageIcon playingImage;
    private ImageIcon stoppedImage;

    public PlayableToggleButton(ImageIcon stoppedImage, ImageIcon playingImage) {
        this.stoppedImage = stoppedImage;
        this.playingImage = playingImage;
    }

    public void setPlaying() {
        setIcon(playingImage);
    }

    public void setStopped() {
        setIcon(stoppedImage);
    }


}
