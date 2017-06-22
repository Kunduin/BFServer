package serviceImpl.IOpart.userSaveWork;

import serviceImpl.IOpart.easyToIO.ReadAndWrite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class UserSaveFile {

    private String username;
    private String filename;
    private String language;
    private String filePath;
    private String splitSign;
    private FileList fileList;

    public UserSaveFile(String username,String filename,String language){
        this.username=username;
        this.filename=filename;
        this.language=language;
        this.splitSign="######";
        this.filePath=username+"."+filename+"."+language+".UserSaveFile";
        this.fileList=new FileList(username);
    }

    public boolean saveFile(String code,String time)throws IOException{
        fileList.updateFile(filename,language,time);
        String theLine=time+splitSign+code;
        ReadAndWrite.write(filePath,theLine);
        return true;
    }
    public String readFile(String time)throws IOException{
        ArrayList<String> eachLineDetail=ReadAndWrite.read(filePath);
        for(String e:eachLineDetail){
            String detail[]=e.split(splitSign);
            if(detail.length>1&&detail[0].equals(time)){
                return detail[1];
            }
        }
        return null;
    }
    public ArrayList getAllVersion() throws IOException{
        return ReadAndWrite.read(filePath);
    }

}
