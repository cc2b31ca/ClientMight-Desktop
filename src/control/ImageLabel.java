package src.control;

import javax.swing.*;
import java.awt.*;

public class ImageLabel extends JPanel {
    Image image_de;

    public ImageLabel(int x,int y,Image image){
        this.setBounds(x,y,100,100);
        image_de = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gacs2d = (Graphics2D)g;
        gacs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gacs2d.drawImage(image_de, 0,0,100,100,null);
    }
}
