package serviceImpl.IOpart;

import serviceImpl.IOpart.autoSaveWork.AutoSaveFile;
import serviceImpl.IOpart.userSaveWork.FileList;
import serviceImpl.IOpart.userSaveWork.UserSaveFile;

import java.io.IOException;

/**
 * Created by 15852 on 2017/6/22 0022.
 */
public class CurrentProject {
    private String username;
    private String filename;
    private String language;
    private FileList fileList;
    private UserSaveFile userSaveFile;
    private AutoSaveFile autoSaveFile;
    public CurrentProject(String username,String filename,String language) throws IOException {
        this.username=username;
        this.filename=language;
        this.language=language;
        this.fileList=new FileList(username);
        this.userSaveFile=new UserSaveFile(username,filename,language);
        this.autoSaveFile=new AutoSaveFile(username,filename,language);
    }
    public String getUsername(){
        return username;
    }
    public String getFilename(){
        return filename;
    }
    public String getLanguage(){
        return language;
    }
    public FileList getFileList(){
        return fileList;
    }
    public UserSaveFile getUserSaveFile(){
        return userSaveFile;
    }
    public AutoSaveFile getAutoSaveFile(){
        return autoSaveFile;
    }
}
