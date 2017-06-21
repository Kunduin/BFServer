package serviceImpl.userPart.userArrengement;

import java.util.ArrayList;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class CurrentUser {
    private static ArrayList<String> currentUsers=new ArrayList<>();
    public static boolean hasTheCurrentUser(String username){
        return currentUsers.contains(username);
    }
    public static boolean addACurrentUser(String username){
        currentUsers.add(username);
        return true;
    }
    public static boolean removeACurrentUser(String username){
        currentUsers.remove(username);
        return true;
    }

}
