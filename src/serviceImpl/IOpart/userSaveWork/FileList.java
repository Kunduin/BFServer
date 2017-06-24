package serviceImpl.IOpart.userSaveWork;

import agreement.IOAgreement;
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

    public FileList(String username) throws IOException {
        this.username=username;
        this.listFilePath=username+".fileList";
        this.splitSign= IOAgreement.SPLIT_SIGN;
//        ReadAndWrite.write(listFilePath,"");
//        FileWriter writer=new FileWriter(listFilePath);
//        writer.write("");
//        writer.flush();
//        writer.close();
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
        System.out.println("newFile");
        String theLine=currentProject.getFilename()+splitSign+currentProject.getLanguage()+splitSign+time;
        System.out.println("newFIle--");
        ReadAndWrite.write(listFilePath,theLine);
        return true;
    }

    public boolean updateFile(String filename,String language,String time) throws IOException{
        deleteFile(filename);
        newFile(filename,language,time);
        return true;
    }
    public boolean updateFile(CurrentProject currentProject,String time) throws IOException{
        System.out.println("--delete");
        deleteFile(currentProject.getFilename());
        System.out.println("delete--");
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

    public ArrayList getAllFile() throws IOException {
        return ReadAndWrite.read(listFilePath);
    }

}
