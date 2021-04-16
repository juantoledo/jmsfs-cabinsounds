package piniufly;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ClipThread extends Thread {

    AudioListener listener = new AudioListener();;

    private File file;

    JToggleButton button;

    public ClipThread(File file) {
        this.file = file;
    }

    public ClipThread(String filePath) {
        this.file = new File(filePath);
    }

    public ClipThread(String filePath, JToggleButton button) {
        this.file = new File(filePath);
        this.button = button;
    }

    @Override
    public synchronized void run() {


        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
                clip.start();
                listener.waitUntilDone();
            } finally {
                clip.close();
                if(button != null){
                    button.setSelected(false);
                    button.setForeground(Color.WHITE);
                }
                this.stop();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                audioInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
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

    public boolean isDone(){
        return listener.isDone();
    }
}
