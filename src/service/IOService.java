//需要客户端的Stub
package service;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IOService extends Remote{
	public boolean writeFile(String file, String userId, String fileName)throws RemoteException;
	
	public String readFile(String userId, String fileName)throws RemoteException;
	
	public String readFileList(String userId)throws RemoteException;

	public boolean newProject(String userId,String filename,String language) throws IOException;

	public boolean deleteProject(String userId,String filename,String language) throws IOException;

	public boolean userSave(String userId,String code,String time) throws IOException;

	public String versionControl(String userId,String time) throws IOException;

	public boolean autoSave(String userId,String code,String time) throws IOException;

	public String undo(String userId,int count) throws IOException;

	public boolean cover(String userId,int count) throws IOException;

}
