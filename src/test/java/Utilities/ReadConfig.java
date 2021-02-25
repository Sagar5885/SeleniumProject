package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties pro;

    public ReadConfig(){
        File src = new File("./Configurations/config.properties");
        try{
            FileInputStream fs = new FileInputStream(src);
            pro = new Properties();
            pro.load(fs);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String getBaseURL(){
        return pro.getProperty("baseURL");
    }

    public String getChromePath(){
        return pro.getProperty("chromePath");
    }

    public String getfirefoxPath(){
        return pro.getProperty("firefoxPath");
    }
}
