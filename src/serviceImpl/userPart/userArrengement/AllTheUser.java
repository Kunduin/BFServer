package serviceImpl.userPart.userArrengement;

import service.UserService;
import serviceImpl.userPart.UserServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class AllTheUser {
    private static String userList="userList";

    public static boolean addAUser(String username,String password) throws IOException{
        FileWriter fileWriter=new FileWriter(userList,true);
        BufferedWriter writer=new BufferedWriter(fileWriter);
        writer.write(Encryption.doEncryption(username,password));
        writer.flush();
        writer.newLine();
        writer.close();
        return true;
    }
    public static boolean hasTheUser(String username)throws IOException{
        return readUserList().containsKey(username);
    }
    public static boolean checkThePassword(String username,String password) throws IOException{
        return readUserList().get(username).equals(password);
    }
    public static boolean deleteId(String username) throws IOException{
        HashMap<String,String> map=readUserList();
        map.remove(username);
        ArrayList<Map.Entry<String,String>> orderedMap=new ArrayList<>(map.entrySet());
        FileWriter fileWriter=new FileWriter(userList);
        BufferedWriter writer=new BufferedWriter(fileWriter);
        for(int i=0;i<orderedMap.size();i++){
            writer.write(Encryption.doEncryption(orderedMap.get(i).getKey(),orderedMap.get(i).getValue()));
            writer.flush();
            writer.newLine();
        }
        writer.close();
        return true;
    }

    private static HashMap readUserList() throws IOException{
        HashMap<String,String> map=new HashMap<>();
        FileReader fileReader=new FileReader(userList);
        BufferedReader reader=new BufferedReader(fileReader);
        String line=new String();
        while ((line=reader.readLine())!=null){
            map.put(Encryption.getId(line),Encryption.getPassword(line));
        }
        return map;
    }

    public static void main(String[] args) throws IOException{
//        addAUser("bay","123454");
//        addAUser("xxz","123454");
//        addAUser("xxt","123454");
//        System.out.println(hasTheUser("xxz"));
//        deleteId("xxz");
//        System.out.println(checkThePassword("bay","e3214"));
//        System.out.println(checkThePassword("bay","123454"));
        UserService userService=new UserServiceImpl();
        System.out.println(userService.login("bay","123454"));

    }
}
