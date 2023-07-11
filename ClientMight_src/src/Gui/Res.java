package src.Gui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Res {
    public ImageIcon icones = new ImageIcon(getClass().getResource("Assets/kpgey.png"));
    public Image setimage(){
        return icones.getImage();
    }
}
