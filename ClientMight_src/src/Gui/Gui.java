package src.Gui;

import javax.swing.*;
import src.Logos.*;
import src.Logos.Scripts.Config;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Gui extends JFrame {
    FileDialog openfiledialog = new FileDialog(this,Logovis.OpenFileDialogTitle,FileDialog.LOAD);
    JTextArea JTA = new JTextArea();

    public static Config config = new Config();
    public static boolean debug_er;
    //初始化用户界面
    public Gui(String debuger){
        System.out.println(debuger);
        //启动参数
        if(debuger.equals("debug")) Guiinit("欢迎 - " + Logovis.Logo + " - Debug",1);
        else Guiinit("欢迎 - " + Logovis.Logo,0);
        System.out.println(new Res().icones.toString());
        new Gui_loop();
    }
    private void Guiinit(String name,int mode){
        setTitle(name);
        setBounds(10,10,800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(new Res().setimage());
        setIconImage(icon.getImage());
        if(mode == 1) Menu(1);
        else Menu(0);
        InputText();
        debug_er = getdebuger(mode);
        setVisible(true);
    }
    //菜单
    private void Menu(int mode){
        JMenuBar jmb = new JMenuBar();
        //文件
        JMenu File = new JMenu(MenuStrings.File);
        JMenuItem jmiopenfile = new JMenuItem(MenuStrings.OpenFile);
        //匿名内部类
        jmiopenfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openfiledialog.setVisible(true);
                if(openfiledialog.getDirectory() == null){
                    JOptionPane.showMessageDialog(null,Logovis.FileErrorTitle,Logovis.Error,JOptionPane.ERROR_MESSAGE);
                }
                else {
                    try {
                        OpenFile();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.out.println("Open Dialog For File");
            }
        });
        JMenuItem jmiopendir = new JMenuItem(MenuStrings.OpenDir+"                    ");
        jmiopendir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按下Ms");
            }
        });
        JMenuItem jmisavefile = new JMenuItem(MenuStrings.SaveFile);
        jmisavefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        jmisavefile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SaveFile();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JMenuItem jmisaveproject = new JMenuItem(MenuStrings.Saves);
        jmisaveproject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        File.add(jmiopenfile);
        File.add(jmiopendir);
        File.addSeparator();
        File.add(jmisavefile);
        File.add(jmisaveproject);
        //帮助
        JMenu Help = new JMenu(MenuStrings.Help);
        JMenuItem jmiabout = new JMenuItem(MenuStrings.About+"                    ");
        jmiabout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new About();
            }
        });
        JMenuItem jmihelpdir = new JMenuItem(MenuStrings.Helpdirs);
        jmihelpdir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //添加控件
        Help.add(jmihelpdir);
        Help.addSeparator();
        Help.add(jmiabout);

        //设置
        JMenu setting = new JMenu(MenuStrings.settingstring);
        JMenuItem jmisetting = new JMenuItem(MenuStrings.SettingPage+"                    ");
        jmisetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Settings();
            }
        });
        setting.add(jmisetting);

        //测试/调试
        JMenu Debug = new JMenu(MenuStrings.Debug);
        JMenuItem debug_shell = new JMenuItem(MenuStrings.debugstring+"                    ");
        debug_shell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DebugShell();
            }
        });
        Debug.add(debug_shell);

        //终端
        JMenu OpenShell = new JMenu(MenuStrings.shell);
        JMenuItem open_shell = new JMenuItem(MenuStrings.openshell);
        open_shell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Shell();
            }
        });
        OpenShell.add(open_shell);


        //添加主控件
        jmb.add(File);
        jmb.add(setting);
        jmb.add(Help);
        jmb.add(OpenShell);
        if (mode == 1) jmb.add(Debug);
        add(jmb,BorderLayout.NORTH);
    }
    //输入框
    private void InputText(){
        JTA.setFont(new Res().getfont());
        JTA.setLineWrap(true);
        JTA.setWrapStyleWord(true);
        JScrollPane jsp = new JScrollPane(JTA);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jsp,BorderLayout.CENTER);
    }

    private void OpenFile() throws IOException {
        FileDialog fdopen = new FileDialog(this, "打开", FileDialog.LOAD);
        fdopen.setVisible(true);
        BufferedReader in = new BufferedReader(new FileReader(fdopen.getDirectory() + fdopen.getFile()));
        String str = null;
        while((str = in.readLine()) != null) {
            System.out.println(str);

            JTA.append(str + "\n");
        }
        in.close();
    }
    private void SaveFile() throws Exception{
        FileDialog fd = new FileDialog(this, "另存为", FileDialog.SAVE);
        fd.setVisible(true);
        if(fd.getDirectory() == null){
            JOptionPane.showMessageDialog(null,Logovis.SaveErrorTitle,Logovis.Error,JOptionPane.ERROR_MESSAGE);
            return;
        }
        FileOutputStream out = new FileOutputStream(fd.getDirectory() + fd.getFile() + ".txt" );
        String str = JTA.getText();
        out.write(str.getBytes());
        out.close();
    }

    private boolean getdebuger(int mode){
        boolean debug = false;
        if(mode == 1) debug = true;
        return debug;
    }
}

class Gui_loop extends Thread{
    S_Shell s_shell = new S_Shell();
    @Override
    public void run() {
        super.run();
        while (true){
            if(new DebugShell().isVisible()) s_shell.start();
            else s_shell.interrupt();
        }
    }
}