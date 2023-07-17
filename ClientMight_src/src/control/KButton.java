package src.control;

import src.Gui.Res;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class KButton extends JPanel implements ActionListener {
    Timer update = new Timer(60,this);
    String drawstrings;
    boolean inont;
    public KButton(int x,int y,int wid,int hei,String ID){
        setBounds(x,y,wid,hei);
        update.start();
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    onclick();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(ID + ":Down");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inont = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                inont = false;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int khei = getHeight();
        int kwid = getWidth();
        Graphics2D gacs2d = (Graphics2D)g;
        gacs2d.setFont(new Res().getfont());
        gacs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gacs2d.setColor(new Color(200, 131, 156));
        gacs2d.fillRoundRect(0,0,kwid-1,khei-1,10,10);
        gacs2d.setColor(new Color(245, 218, 227));
        gacs2d.fillRect(25 / 2,25 / 2,kwid - 25,khei - 25);
        gacs2d.setColor(new Color(42, 42, 42));
        gacs2d.drawString(drawstrings,25 / 2 ,khei / 2);
        if(inont){
            gacs2d.setColor(new Color(255, 255, 255, 45));
            gacs2d.fillRoundRect(0,0,kwid-1,khei-1,10,10);
        }
        //System.out.println(kwid +":"+ khei +":"+ kx+":"+ky );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void set_strings(String Title){
        drawstrings = Title;
    }
    public interface StateListener{
        void statechange(Object src) throws IOException;
    }
    private StateListener stateListener;
    public void setStateListener(StateListener listener){
        this.stateListener = listener;
    }

    private void onclick() throws IOException {
        if(stateListener!=null){
            stateListener.statechange(this);
        }
    }

    public String getstring(){
        return drawstrings;
    }
}
