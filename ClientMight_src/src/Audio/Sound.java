package src.Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class Sound {
    private AudioInputStream audioInputStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceDataLine;
    public Sound(String Path){
        System.out.println(Path);
        PlayAudio(Path);
    }
    private void PlayAudio(String path){
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            audioFormat = audioInputStream.getFormat();
            DataLine.Info datalineinfo = new DataLine.Info(SourceDataLine.class,audioFormat,AudioSystem.NOT_SPECIFIED);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(datalineinfo);
            sourceDataLine.open(audioFormat);
        }
        catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void start() throws IOException {
        int count;
        byte bit[] = new byte[1024];
        sourceDataLine.start();
        while ((count = audioInputStream.read(bit,0,bit.length)) != -1){
            sourceDataLine.write(bit,0,count);
        }
    }
    public void stop() throws IOException {
        sourceDataLine.drain();
        sourceDataLine.close();
        audioInputStream.close();
    }
}