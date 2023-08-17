package src.Logos.Scripts;

import java.io.*;
public class Config {
    File config_dir = new File("Config");
    public File config_file = new File("Config/Software_Config.Ksp");
    public Config(){
        try{
            if(!config_dir.exists()) config_dir.mkdirs();
            if(!config_file.exists()) config_file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SaveConfig(String save_text,boolean mode){
        try{
            FileWriter writer = new FileWriter(config_file,mode);
            writer.write(save_text + "\r\n");
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

    public String ConfigSplit(String str,int Long){
        String[] Data = str.split(":");
        return Data[Long].trim();
    }
}
