package serviceImpl.IOpart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;

import service.IOService;

public class IOServiceImpl implements IOService {

    private HashMap<String, CurrentProject> map;


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
    public String readFile(String userId, String fileName) {
        // TODO Auto-generated method stub
        return "OK";
    }

    @Override
    public String readFileList(String userId) {
        // TODO Auto-generated method stub
        return "OK";
    }


    @Override
    public boolean newProject(String userId, String filename, String language) throws IOException {
        CurrentProject aNewProject = new CurrentProject(userId, filename, language);
        if (aNewProject.getFileList().checkFile(filename)) {
            return false;
        } else {
            map.put(userId, aNewProject);
            return true;
        }
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
        CurrentProject currentProject = map.get(userId);
        currentProject.getFileList().updateFile(currentProject, time);
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
