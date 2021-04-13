package piniufly;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ClipThread extends Thread {

    private File file;

    public ClipThread(File file) {
        this.file = file;
    }

    @Override
    public synchronized void run() {

        AudioListener listener = new AudioListener();
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
    }

}
