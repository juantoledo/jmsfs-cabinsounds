package piniufly;

import org.apache.commons.lang3.exception.ExceptionUtils;
import piniufly.ui.model.PlayableToggleButton;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioSystem.write;
import static piniufly.service.AudioHelper.*;

public class ClipThread extends Thread {

    AudioListener listener = new AudioListener();

    private File file;

    PlayableToggleButton button;

    private Clip clip;

    String where;

    public ClipThread(File file, String where) {
        this.file = file;
        this.where = where;
    }

    public ClipThread(String filePath, String where) {
        this.file = new File(filePath);
        this.where = where;
    }

    public ClipThread(String filePath, PlayableToggleButton button, String where) {
        this.file = new File(filePath);
        this.button = button;
        this.where = where;
    }

    @Override
    public synchronized void run() {

        AudioInputStream audioInputStream = null;

        try {
            audioInputStream = getAudioInputStream(file);

            clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);

            try {
                setGain(clip);
                clip.start();
                button.setPlaying();
                addActiveClip(where, clip);
                listener.waitUntilDone();
            } finally {
                clip.close();
                if (button != null) {
                    button.setSelected(false);
                    button.setStopped();
                }
                removeActiveClip(where, clip);
                this.stop();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null, ExceptionUtils.getStackTrace(ex), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            button.setSelected(false);
        } finally {
            try {
                audioInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null, ExceptionUtils.getStackTrace(ex), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class AudioListener implements LineListener {

        private boolean done = false;

        @Override
        public synchronized void update(LineEvent event) {
            LineEvent.Type eventType = event.getType();
            if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                done = true;
                notifyAll();
            }
        }

        public synchronized void waitUntilDone() throws InterruptedException {
            while (!done) {
                wait();
            }
        }

        public boolean isDone() {
            return done;
        }
    }

    public boolean isDone() {
        return listener.isDone();
    }

    public Clip getClip() {
        return clip;
    }
}
