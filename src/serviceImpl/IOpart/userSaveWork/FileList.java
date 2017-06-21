package serviceImpl.IOpart.userSaveWork;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class FileList {
    private String username;
    private String listFilePath;

    public FileList(String username){
        this.username=username;
        this.listFilePath=username+".fileList";
    }

    public boolean newFile(String filename,String language,String time) throws IOException {
        FileWriter fileWriter=new FileWriter(listFilePath,true);
        BufferedWriter writer=new BufferedWriter(fileWriter);
        writer.write(filename+"######"+language+"######"+time);
        writer.flush();
        writer.newLine();
        writer.close();
        return true;
    }

    public boolean deleteFile(String filename,String language) throws IOException{
        
        return true;
    }

    private ArrayList readList() throws IOException {
        ArrayList<String> eachFileName=new ArrayList<>();
        FileReader fileReader=new FileReader(listFilePath);
        BufferedReader reader=new BufferedReader(fileReader);
        String line;
        while ((line=reader.readLine())!=null){
            eachFileName.add(line);
        }
        return eachFileName;
    }
}
