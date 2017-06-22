//需要客户端的Stub
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IOService extends Remote{
	public boolean writeFile(String file, String userId, String fileName)throws RemoteException;
	
	public String readFile(String userId, String fileName)throws RemoteException;
	
	public String readFileList(String userId)throws RemoteException;

	public boolean newProject(String userId,String filename,String language) throws RemoteException;

	public boolean deleteProject(String userId,String filename,String language) throws RemoteException;

	public boolean userSave(String userId,String time) throws RemoteException;

	public String versionControl(String userId,String time) throws RemoteException;

	public boolean autoSave(String userId,String time) throws RemoteException;

	public String undo(String userId,String count) throws RemoteException;

	public boolean cover(String userId,String count) throws RemoteException;

}
