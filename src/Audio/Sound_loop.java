package src.Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound_loop {
    private AudioInputStream ais;
    private Clip clip;
    private String data_path;

    public Sound_loop(String path) {
        data_path = path;
    }

    public void loop() {
        try {
            ais = AudioSystem.getAudioInputStream(new File(data_path));
            AudioFormat af = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, af);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            ais.close();
            clip.stop();
            clip.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
