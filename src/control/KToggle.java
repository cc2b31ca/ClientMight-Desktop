package src.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KToggle extends JPanel implements ActionListener {
    Timer update = new Timer(10,this);
    boolean isToggle;
    boolean isOn;
    String DrawTitles;

    public KToggle(int x, int y, int wid, int hei, boolean Tog,String Title){
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Toggle();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                isOn = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                isOn = false;
            }
        });
        setBounds(x,y,wid,hei);
        isToggle = Tog;
        DrawTitles = Title;
        update.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xn = getWidth();
        int yn = getHeight();
        Graphics2D gacs2d = (Graphics2D)g;
        gacs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gacs2d.setColor(new Color(200, 131, 156));
        gacs2d.fillRoundRect(0,0,xn/2,yn,10,10);
        if(isOn){
            gacs2d.setColor(new Color(255, 255, 255, 45));
            gacs2d.fillRoundRect(0,0,xn/2,yn,10,10);
        }
        if(isToggle){
            gacs2d.setColor(new Color(0,0,0));
            gacs2d.drawLine(10,yn/4+6,xn/4,yn/3+15);
            gacs2d.drawLine(xn/4,yn/3+15,xn/2-5,10);
        }
        gacs2d.setColor(new Color(0,0,0));
        gacs2d.drawString(DrawTitles,xn/2,yn/2);
}

    private void Toggle(){
        isToggle = !isToggle;
    }

    public boolean isChick(){
        return isToggle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}