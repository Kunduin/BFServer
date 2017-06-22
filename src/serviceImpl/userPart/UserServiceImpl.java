package serviceImpl.userPart;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;

import service.UserService;
import serviceImpl.userPart.userArrengement.AllTheUser;
import serviceImpl.userPart.userArrengement.CurrentUser;

public class UserServiceImpl implements UserService {


    @Override
    public boolean login(String username, String password) throws IOException {
        if (!AllTheUser.hasTheUser(username)) {
            return false;
        } else if (!AllTheUser.checkThePassword(username, password)) {
            return false;
        } else if (!CurrentUser.hasTheCurrentUser(username)) {
            return false;
        } else {
            CurrentUser.addACurrentUser(username);
            return true;
        }

    }

    @Override
    public boolean logout(String username) throws RemoteException {
        CurrentUser.removeACurrentUser(username);
        return true;
    }

    @Override
    public boolean signUp(String username, String password) throws IOException {
        if (AllTheUser.hasTheUser(username)) {
            return false;
        } else {
            AllTheUser.addAUser(username, password);
            return true;
        }
    }

    @Override
    public boolean deleteId(String username) throws IOException {
        AllTheUser.deleteId(username);
        return true;
    }

}
