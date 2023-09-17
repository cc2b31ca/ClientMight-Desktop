package src.Gui;

import src.Audio.Sound_loop;
import src.Logos.Scripts.LoadPlugins;
import src.control.KButton;
import src.control.KToggle;
import src.Audio.Sound;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Settings extends JFrame {

    audio_bgm Bgm = new audio_bgm();
    audio_test Test = new audio_test();

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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });
        setVisible(true);
    }
    private void addPath(){
        KButton kb = new KButton(10,10,100,50,"KButton1");
        kb.set_strings("测试音频");
        kb.setStateListener(new KButton.StateListener() {
            @Override
            public void statechange(Object src) {
                Test.start();
            }
        });
        KButton kb1 = new KButton(120,10,100,50,"KButton2");
        kb1.set_strings("播放背景音乐");
        kb1.setStateListener(new KButton.StateListener() {
            @Override
            public void statechange(Object src){
                Bgm.start();
            }
        });
        KButton debug_kb1 = new KButton(230,10,100,50,"Debug_Button1");
        debug_kb1.set_strings("测试参数");
        debug_kb1.setStateListener(new KButton.StateListener() {
            @Override
            public void statechange(Object src) throws IOException {
                Gui.config.SaveConfig(kb.getstring(),false);
            }
        });
        KButton kb2 = new KButton(10,70,100,50,"KButton3");
        kb2.set_strings("恢复默认");
        kb2.setStateListener(new KButton.StateListener() {
            @Override
            public void statechange(Object src) throws IOException {
                Gui.config.config_file.delete();
            }
        });
        KButton kb3 = new KButton(120,70,100,50,"KButton4");
        kb3.set_strings("启用插件");
        kb3.setStateListener(new KButton.StateListener() {
            @Override
            public void statechange(Object src) throws IOException {
                new LoadPlugins().Load();
            }
        });
        add(kb);
        add(kb1);
        add(kb2);
        add(kb3);
        if(Gui.debug_er){
            add(debug_kb1);
        }
    }

    private void addTog(){
        KToggle toggle = new KToggle(10,70,100,50,true,"wofule");
        //add(toggle);
    }
}

class audio_test extends Thread{
    @Override
    public void run() {
        super.run();
        try {
            Sound sound = new Sound("Assets/test.wav");
            sound.start();
            sound.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class audio_bgm extends Thread{
    @Override
    public void run() {
        super.run();
        Sound_loop sl = new Sound_loop("Assets/bgm.wav");
        sl.loop();
    }
}
