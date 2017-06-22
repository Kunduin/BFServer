//需要客户端的Stub
package service;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote{
	public boolean login(String username, String password) throws IOException;

	public boolean logout(String username) throws RemoteException;

	public boolean signUp(String username, String password) throws IOException;

	public boolean deleteId(String username) throws IOException;

}
