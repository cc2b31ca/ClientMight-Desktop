package src.Logos.Scripts;

import java.io.*;

public class Config {
    File config_dir = new File("Config");
    File config_file = new File("Config/Software_Config.Ksp");
    public Config(){
        try{
            if(!config_dir.exists()) config_dir.mkdirs();
            if(!config_file.exists()) config_file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SaveConfig(String save_text){
        try{
            FileWriter writer = new FileWriter(config_file);
            writer.write(save_text);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String LoadConfig(int line){
        String tmp = null;
        try{
            LineNumberReader lnr = new LineNumberReader(new FileReader(config_file));
            String sline;
            while((sline = lnr.readLine()) != null){
                if(lnr.getLineNumber() == line){
                    tmp = sline;
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tmp;
    }
}
