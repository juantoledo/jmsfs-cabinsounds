package piniufly;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;

public class App {


    private void execute() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {


        for (int i = 0; i < 1; i++) {
            new ClipThread(new File("/Users/jtoledoc/IdeaProjects/jmsfs-cabinsounds/sample.wav")).start();
        }


    }




    private static void playClip(File clipFile) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException, InterruptedException
    {

        class AudioListener implements LineListener {

            private boolean done = false;
            @Override public synchronized void update(LineEvent event) {
                Type eventType = event.getType();
                if (eventType == Type.STOP || eventType == Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }
            public synchronized void waitUntilDone() throws InterruptedException {
                while (!done) { wait(); }
            }
        }

        AudioListener listener = new AudioListener();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
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
        } finally {
            audioInputStream.close();
        }
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

        System.out.println("Hello World!");

        //playClip(new File("C:\\TAXI.wav"));
       // Thread.sleep(1000);
       // playClip(new File("C:\\TAXI.wav"));
        new App().execute();




    }
}
