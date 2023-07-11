package src.Gui;

import src.control.KButton;
import src.control.KToggle;
import src.Audio.Sound;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Settings extends JFrame {
    public Settings(){
        SettingGui();
    }
    private void SettingGui(){
        setTitle("设置");
        setResizable(false);
        setBounds(10,10,785,788);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Res().setimage());
        addPath();
        addTog();
        setVisible(true);
    }
    private void addPath(){
        KButton kb = new KButton(10,10,100,50,"KButton1");
        kb.set_strings("测试音频");
        kb.setStateListener(new KButton.StateListener() {
            @Override
            public void statechange(Object src) {
                new audio_test().start();
            }
        });
        add(kb);
    }

    private void addTog(){
        KToggle toggle = new KToggle(10,70,100,50,true,"wofule");
        add(toggle);
    }
}

class audio_test extends Thread{
    @Override
    public void run() {
        super.run();
        try {
            Sound sound = new Sound("sample-15s.wav");
            sound.start();
            sound.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
