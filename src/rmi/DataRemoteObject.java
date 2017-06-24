package rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import service.ExecuteService;
import service.IOService;
import service.UserService;
import serviceImpl.executePart.ExecuteServiceImpl;
import serviceImpl.IOpart.IOServiceImpl;
import serviceImpl.userPart.UserServiceImpl;

public class DataRemoteObject extends UnicastRemoteObject implements IOService, UserService,ExecuteService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029039744279087114L;
	private IOService iOService;
	private UserService userService;
	private ExecuteService executeService;
	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
		executeService = new ExecuteServiceImpl();
	}

	@Override
	public boolean writeFile(String file, String userId, String fileName) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.writeFile(file, userId, fileName);
	}

	@Override
	public ArrayList getFileAllVersion(String userId) throws IOException{
		return iOService.getFileAllVersion(userId);
	}

	@Override
	public ArrayList readFileList(String userId) throws IOException {
		return iOService.readFileList(userId);
	}

	@Override
	public boolean newProject(String userId, String filename, String language) throws IOException {
		return iOService.newProject(userId,filename,language);
	}

	@Override
	public boolean oldProject(String userId, String filename, String language) throws IOException {
		return iOService.oldProject(userId,filename,language);
	}

	@Override
	public boolean deleteProject(String userId, String filename, String language) throws IOException {
		return iOService.deleteProject(userId,filename,language);
	}

	@Override
	public boolean userSave(String userId, String code,String time) throws IOException {
		return iOService.userSave(userId,code,time);
	}

	@Override
	public String versionControl(String userId, String time) throws IOException {
		return iOService.versionControl(userId,time);
	}

	@Override
	public boolean autoSave(String userId,String code, String time) throws IOException {
		return iOService.autoSave(userId,code,time);
	}

	@Override
	public String undo(String userId, int count) throws IOException {
		return iOService.undo(userId,count);
	}

	@Override
	public boolean cover(String userId, int count) throws IOException {
		return iOService.cover(userId,count);
	}

	@Override
	public String login(String username, String password) throws IOException {
		return userService.login(username, password);
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return userService.logout(username);
	}

	@Override
	public boolean signUp(String username, String password) throws IOException {
		return userService.signUp(username,password);
	}

	@Override
	public boolean deleteId(String username) throws IOException {
		return userService.deleteId(username);
	}


	@Override
	public String execute(String code, String param) throws RemoteException {
		return executeService.execute(code,param);
	}

	@Override
	public String execute(String code, String param, String language, String userId) throws RemoteException {
		return executeService.execute(code,param,language,userId);
	}
}
