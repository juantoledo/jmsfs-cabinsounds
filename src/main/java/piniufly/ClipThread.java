package piniufly;

import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ClipThread extends Thread {

  AudioListener listener = new AudioListener();

  private File file;

  JToggleButton button;

  private Clip clip;

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

      clip = AudioSystem.getClip();
      clip.addLineListener(listener);
      clip.open(audioInputStream);

      try {
        clip.start();
        listener.waitUntilDone();
      } finally {
        clip.close();
        if (button != null) {
          button.setSelected(false);
          button.setForeground(Color.WHITE);
        }
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
