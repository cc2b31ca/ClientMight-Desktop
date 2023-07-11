package src.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Shell extends JFrame {

    static JTextArea ta = new JTextArea();
    public Shell(){
        Gui();
    }
    private void Gui(){
        setTitle("Shell");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Res().setimage());
        setBounds(10,10,500,460);
        setLocationRelativeTo(null);
        setResizable(false);
        Control();
        setVisible(true);
    }

    private void Control(){
        ta.setEnabled(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        JTextArea ta2 = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ta);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JButton button = new JButton("执行");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Shell_Start(ta2.getText());
            }
        });
        add(scrollPane,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);
        add(ta2,BorderLayout.NORTH);
    }
    //这个方法比第一个好，执行时不会卡  stmt要执行的命令
    public static void Shell_Start(String stam){
        ta.setText("");
        BufferedReader br = null;
        try {
            File file = new File("\\daemonTmp");
            File tmpFile = new File("\\daemonTmp\\temp.tmp");//新建一个用来存储结果的缓存文件
            if (!file.exists()){
                file.mkdirs();
            }
            if(!tmpFile.exists()) {
                tmpFile.createNewFile();
            }
            ProcessBuilder pb = new ProcessBuilder().command("cmd.exe", "/c", stam).inheritIO();
            pb.redirectErrorStream(true);//这里是把控制台中的红字变成了黑字，用通常的方法其实获取不到，控制台的结果是pb.start()方法内部输出的。
            pb.redirectOutput(tmpFile);//把执行结果输出。
            pb.start().waitFor();//等待语句执行完成，否则可能会读不到结果。
            InputStream in = new FileInputStream(tmpFile);
            br= new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = br.readLine()) != null) {
                System.out.println(line);
                ta.append(line);
            }
            br.close();
            br = null;
            tmpFile.delete();//卸磨杀驴。
            System.out.println("执行完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
