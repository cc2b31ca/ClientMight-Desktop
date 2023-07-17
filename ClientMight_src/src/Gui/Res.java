package src.Gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Res {
    public ImageIcon icones = new ImageIcon(getClass().getResource("Assets/kpgey.png"));
    public Image setimage(){
        return icones.getImage();
    }

    public Font getfont(){
        try{
            Font deblackfont = Font.createFont(Font.TRUETYPE_FONT,new File("Assets/DeEBlack.ttf")).deriveFont(Font.PLAIN,14);
            return deblackfont;
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
