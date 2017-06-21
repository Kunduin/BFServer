package serviceImpl.userPart.userArrengement;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class Encryption {
    public static String getId(String line){
        String[] tmp=line.split("_");
        return tmp[0];
    }
    public static String getPassword(String line){
        String[] tmp=line.split("_");
        return tmp[1];
    }
    public static String doEncryption(String username,String password){
        return username+"_"+password;
    }
}
