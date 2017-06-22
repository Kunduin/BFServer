package serviceImpl.IOpart.autoSaveWork;

import serviceImpl.IOpart.easyToIO.ReadAndWrite;
import serviceImpl.IOpart.userSaveWork.FileList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class AutoSaveFile {
    private String username;
    private String filename;
    private String language;
    private String filePath;
    private String splitSign;

    public AutoSaveFile(String username, String filename,String language){
        this.username=username;
        this.filename=filename;
        this.language=language;
        this.splitSign="######";
        this.filePath=username+"."+filename+"."+language+".AutoSaveFile";
    }

    public boolean autoSaveFile(String code,String time)throws IOException{
        String theLine=time+splitSign+code;
        ReadAndWrite.write(filePath,theLine);
        return true;
    }
    public String getOldVersion(int count) throws IOException{
        ArrayList<String> eachLineDetail=ReadAndWrite.read(filePath);
        if(count>eachLineDetail.size()-1){
            return "#"+String.valueOf(count-eachLineDetail.size()+1);
        }else {
            return eachLineDetail.get(eachLineDetail.size()-count-1);
        }
    }

    public boolean coverVersion(int count) throws IOException{
        if(count>0) {
            ArrayList<String> eachLineDetail = ReadAndWrite.read(filePath);
            for (int i = 0; i < count; i++) {
                eachLineDetail.remove(eachLineDetail.size() - 1);
            }
            ReadAndWrite.write(filePath, eachLineDetail);
            return true;
        }
        return false;
    }

}
