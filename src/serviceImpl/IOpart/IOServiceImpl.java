package serviceImpl.IOpart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import service.IOService;
import serviceImpl.IOpart.userSaveWork.FileList;

public class IOServiceImpl implements IOService {

    private HashMap<String, CurrentProject> map=new HashMap<>();


    @Override
    public boolean writeFile(String file, String userId, String fileName) {
        File f = new File(userId + "_" + fileName);
        try {
            FileWriter fw = new FileWriter(f, false);
            fw.write(file);
            fw.flush();
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList getFileAllVersion(String userId) throws IOException {
        return map.get(userId).getUserSaveFile().getAllVersion();
    }

    @Override
    public ArrayList readFileList(String userId) throws IOException {
        // TODO Auto-generated method stub
        FileList fileList=new FileList(userId);
        return fileList.getAllFile();
    }


    @Override
    public boolean newProject(String userId, String filename, String language) throws IOException {
        CurrentProject aNewProject = new CurrentProject(userId, filename, language);
        System.out.println("??");
        if (aNewProject.getFileList().checkFile(filename)) {
            return false;
        } else {
            map.put(userId, aNewProject);
            return true;
        }
    }

    @Override
    public boolean oldProject(String userId, String filename, String language) throws IOException {
        CurrentProject anOldProject = new CurrentProject(userId, filename, language);
        map.put(userId,anOldProject);
        return true;
    }

    @Override
    public boolean deleteProject(String userId, String filename, String language) throws IOException {
        CurrentProject anOldProject = map.get(userId);
        anOldProject.getFileList().deleteFile(filename);
        map.remove(userId);
        return true;
    }

    @Override
    public boolean userSave(String userId, String code, String time) throws IOException {
        System.out.println("--userSave");
        CurrentProject currentProject = map.get(userId);
        System.out.println("userSave");
        System.out.println(currentProject.getFilename());
        currentProject.getFileList().updateFile(currentProject, time);
        System.out.println("userSave--");
        currentProject.getUserSaveFile().saveFile(code, time);
        return true;
    }

    @Override
    public String versionControl(String userId, String time) throws IOException {
        CurrentProject currentProject = map.get(userId);
        return currentProject.getUserSaveFile().readFile(time);

    }

    @Override
    public boolean autoSave(String userId, String code, String time) throws IOException {
        CurrentProject currentProject = map.get(userId);
        currentProject.getAutoSaveFile().autoSaveFile(code, time);
        return false;
    }

    @Override
    public String undo(String userId, int count) throws IOException {
        CurrentProject currentProject = map.get(userId);
        return currentProject.getAutoSaveFile().getOldVersion(count);
    }

    @Override
    public boolean cover(String userId, int count) throws IOException {
        CurrentProject currentProject = map.get(userId);
        currentProject.getAutoSaveFile().coverVersion(count);
        return false;
    }

}
