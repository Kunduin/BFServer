package serviceImpl.IOpart.userSaveWork;

import serviceImpl.IOpart.CurrentProject;
import serviceImpl.IOpart.easyToIO.ReadAndWrite;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class FileList {
    private String username;
    private String listFilePath;
    private String splitSign;

    public FileList(String username){
        this.username=username;
        this.listFilePath=username+".fileList";
        this.splitSign="######";
    }

    public ArrayList getFileList() throws IOException{
        return ReadAndWrite.read(listFilePath);
    }

    public boolean newFile(String filename,String language,String time) throws IOException {
        String theLine=filename+splitSign+language+splitSign+time;
        ReadAndWrite.write(listFilePath,theLine);
        return true;
    }
    public boolean newFile(CurrentProject currentProject,String time) throws IOException{
        String theLine=currentProject.getFilename()+splitSign+currentProject.getLanguage()+splitSign+time;
        ReadAndWrite.write(listFilePath,theLine);
        return true;
    }

    public boolean updateFile(String filename,String language,String time) throws IOException{
        deleteFile(filename);
        newFile(filename,language,time);
        return true;
    }
    public boolean updateFile(CurrentProject currentProject,String time) throws IOException{
        deleteFile(currentProject.getFilename());
        newFile(currentProject,time);
        return true;
    }

    public boolean checkFile(String filename) throws IOException{
        ArrayList<String> eachFileName= ReadAndWrite.read(listFilePath);
        ArrayList<String> singleName=new ArrayList<>();
        for(String e:eachFileName){
            String[] detail=e.split(splitSign);
            singleName.add(detail[0]);
        }
        return singleName.contains(filename);
    }

    public boolean deleteFile(String filename) throws IOException{
        ArrayList<String> eachFileName=ReadAndWrite.read(listFilePath);
        for(String e:eachFileName){
            String[] detail=e.split(splitSign);
            if(detail[0].equals(filename)){
                eachFileName.remove(e);
                break;
            }
        }
        ReadAndWrite.write(listFilePath,eachFileName);

        return true;
    }

}
