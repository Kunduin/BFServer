package serviceImpl.userPart;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;

import agreement.LoginAgreement;
import service.UserService;
import serviceImpl.userPart.userArrengement.AllTheUser;
import serviceImpl.userPart.userArrengement.CurrentUser;

public class UserServiceImpl implements UserService {


    @Override
    public String login(String username, String password) throws IOException {
        System.out.println("????");
        if (!AllTheUser.hasTheUser(username)) {
            return LoginAgreement.USERID_ABSENCE;
        } else if (!AllTheUser.checkThePassword(username, password)) {
            return LoginAgreement.PASSWORD_WRONG;
        } else if (CurrentUser.hasTheCurrentUser(username)) {
            return LoginAgreement.CURRENT_USER_EXIST;
        } else {
            CurrentUser.addACurrentUser(username);
            return LoginAgreement.LOGIN_SUCCESS;
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
