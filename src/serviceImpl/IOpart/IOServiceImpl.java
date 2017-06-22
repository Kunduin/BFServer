package serviceImpl.IOpart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;

import service.IOService;

public class IOServiceImpl implements IOService{

	private HashMap<String,CurrentProject> map;

	
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
	public boolean newProject(String userId, String filename, String language) throws RemoteException {
		try {
			CurrentProject aNewProject=new CurrentProject(userId,filename,language);
			if(aNewProject.getFileList().checkFile(filename)) {
				return false;
			}else {
				map.put(userId,aNewProject);
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteProject(String userId, String filename, String language) throws RemoteException {
		return false;
	}

	@Override
	public boolean userSave(String userId, String time) throws RemoteException {
		return false;
	}

	@Override
	public String versionControl(String userId, String time) throws RemoteException {
		return null;
	}

	@Override
	public boolean autoSave(String userId, String time) throws RemoteException {
		return false;
	}

	@Override
	public String undo(String userId, String count) throws RemoteException {
		return null;
	}

	@Override
	public boolean cover(String userId, String count) throws RemoteException {
		return false;
	}

}
