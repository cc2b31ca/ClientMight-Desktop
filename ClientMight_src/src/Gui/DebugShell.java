package src.Gui;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DebugShell extends JFrame {
    static JTextArea ta = new JTextArea();
    public DebugShell(){
        setBounds(10,10,480,770);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(new Res().setimage());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Shell();
        setVisible(true);
    }
    private void Shell(){
        JScrollPane jsp = new JScrollPane(ta);
        ta.setEnabled(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(jsp);
        new S_Shell().start();
    }
}

class S_Shell extends Thread{
    @Override
    public void run() {
        super.run();

        ByteArrayOutputStream bytes = new ByteArrayOutputStream(1024);
        PrintStream buffrer_p = new PrintStream(bytes);
        PrintStream oldStream = System.out;
        System.setOut(buffrer_p);
        while (true){
            String res = bytes.toString();
            DebugShell.ta.setText(res);
            try {
                sleep(200);
                oldStream.println("hahah");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}