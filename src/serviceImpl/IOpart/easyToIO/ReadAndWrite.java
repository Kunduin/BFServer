package serviceImpl.IOpart.easyToIO;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by 15852 on 2017/6/22 0022.
 */
public class ReadAndWrite {
    public static ArrayList read(String filePath) throws IOException {
        ArrayList<String> eachLineDetail= new ArrayList<>();
        eachLineDetail.clear();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        while ((line = reader.readLine()) != null) {
            eachLineDetail.add(line);
        }
        reader.close();
        return eachLineDetail;
    }
    public static boolean write(String filePath,String ALine) throws IOException{
        FileWriter fileWriter=new FileWriter(filePath,true);
        BufferedWriter writer=new BufferedWriter(fileWriter);
        writer.write(ALine);
        writer.flush();
        writer.newLine();
        writer.close();
        return true;
    }
    public static boolean write(String filePath,ArrayList<String> eachLineDetail)throws IOException{
        FileWriter fileWriter=new FileWriter(filePath);
        BufferedWriter writer=new BufferedWriter(fileWriter);
        for(String e:eachLineDetail){
            writer.write(e);
            writer.flush();
            writer.newLine();
        }
        writer.close();
        return true;
    }
    public static boolean write(String filePath,ArrayList<String> eachLineDetail,Boolean writeWhere)throws IOException{
        if(writeWhere){
            FileWriter fileWriter=new FileWriter(filePath,true);
            BufferedWriter writer=new BufferedWriter(fileWriter);
            for(String e:eachLineDetail){
                writer.write(e);
                writer.flush();
                writer.newLine();
            }
            writer.close();
        }else {
            write(filePath,eachLineDetail,writeWhere);
        }
        return true;
    }
}
