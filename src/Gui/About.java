package src.Gui;

import src.Logos.AboutStrings;
import src.Logos.Scripts.OpenBrowserUrl;
import src.control.ImageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class About extends JFrame {
    public About(){
        AboutInit();
    }

    private void AboutInit(){
        setTitle(AboutStrings.AboutWindow);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(0,0,400,250);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        Gui();
        ImageIcon icon = new ImageIcon(new Res().setimage());
        setIconImage(icon.getImage());
        setVisible(true);
    }

    private void Gui(){
        JLabel jLabel1 = new JLabel("开发人员 ：" + AboutStrings.developer1);
        JLabel jLabel2 = new JLabel("产品策划 ：" + AboutStrings.developer2);
        JLabel jLabel3 = new JLabel("打开KPGEY Studio 官方网站");
        {
            OpenBrowserUrl browserUrl = new OpenBrowserUrl();
            String url = "https://kpgeystudio.site/";
            jLabel3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    browserUrl.browserUrl(url);
                }
            });
            jLabel3.setBounds(10,50,200,20);
            jLabel3.setFont(new Res().getfont());
            jLabel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        jLabel1.setBounds(10,10,150,20);
        jLabel2.setBounds(10,30,150,20);
        ImageLabel imageLabel = new ImageLabel(getWidth() - 120,10,new Res().setimage());
        System.out.println(getWidth() + ":" + getX());
        add(jLabel1);
        add(jLabel2);
        add(jLabel3);
        add(imageLabel);
    }
}
