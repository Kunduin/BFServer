package serviceImpl.executePart.commends;
import serviceImpl.IOpart.userSaveWork.FileList;
import serviceImpl.IOpart.userSaveWork.UserSaveFile;

import java.util.ArrayList;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class Operator {
    private char[] bfCode;
    private char[] param;
    private int codePointer;
    private int dataPointer;
    private int paramPointer;
    private ArrayList<Character> outPutChar;
    private ArrayList<Character> data;


    public Operator(String code, String param){
        code=code.replaceAll("\\r|\\n| ","");
        while (param.length()<code.replaceAll("[~,]","").length()){param+=" ";}

        this.bfCode=code.toCharArray();
        this.param=param.toCharArray();
        this.codePointer=0;
        this.dataPointer=0;
        this.paramPointer=0;
        this.outPutChar=new ArrayList<>();
        this.data=new ArrayList<>();
        this.data.add((char)0);
        this.data.add((char)0);
    }

    public void execute(){
        while (codePointer!=bfCode.length){
            interpreter(bfCode[codePointer]);
        }
    }

    public String getResult(){
        StringBuffer result=new StringBuffer();
        for(int i=0,length=outPutChar.size();i<length;i++){
            result.append(outPutChar.get(i));
        }
        return result.substring(0);
    }

    public void interpreter(char commend){
        switch (commend){
            case Commend.NEXT_CHAR:
                dataPointer++;
                data.add((char)0);
                break;
            case Commend.PREVIOUS_CHAR:
                dataPointer--;
                break;
            case Commend.ADD:
                char tmpAdder=data.get(dataPointer);
                data.add(dataPointer,(char)(tmpAdder+1));
                data.remove(dataPointer+1);
                break;
            case Commend.SUB:
                int tmpSuber=data.get(dataPointer);
                data.add(dataPointer,(char)(tmpSuber-1));
                data.remove(dataPointer+1);

                break;
            case Commend.OUTPUT:
                int tmpInt=data.get(dataPointer);
                outPutChar.add((char)tmpInt);
                break;
            case Commend.INPUT:
                data.add(dataPointer,param[paramPointer]);
                data.remove(dataPointer+1);
                paramPointer++;
                break;
            case Commend.BRANKET_LEFT:
                if(data.get(dataPointer)==0) {
                    int index=1;
                    while (index > 0) {
                        codePointer++;
                        if (bfCode[codePointer] == Commend.BRANKET_LEFT) {
                            index++;
                        } else if (bfCode[codePointer] == Commend.BRANKET_RIGHT) {
                            index--;
                        }
                    }
                }
                break;
            case Commend.BRANKET_RIGHT:
                if(data.get(dataPointer)!=0){
                    int index=1;
                    while (index>0){
                        codePointer--;
                        if(bfCode[codePointer]==Commend.BRANKET_RIGHT){
                            index++;
                        }else if(bfCode[codePointer]==Commend.BRANKET_LEFT){
                            index--;
                        }
                    }
                }
                break;
            default:
                break;
        }
        codePointer++;
    }

    public ArrayList getStack(){
        return data;
    }

    public static void main(String[] args) throws Exception{
//        Operator operator=new Operator(" ,>,,>++++++++[<------<------>>-]  <<[>[>+>+<<-]>>[<<+>>-]<<<-]  >>>++++++[<++++++++>-],<.>.","2 3" );
//        operator.execute();
//        System.out.println(operator.getResult());
//        System.out.println(operator.getStack());
//
//        ExecuteService executeService=new ExecuteServiceImpl();
//        String ook=new String("Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
//                "Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook? Ook! Ook. Ook? Ook.\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
//                "Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook!\n" +
//                "Ook? Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
//                "Ook? Ook. Ook? Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook!\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
//                "Ook! Ook. Ook! Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook? Ook. Ook.\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
//                "Ook! Ook? Ook! Ook! Ook. Ook? Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook!\n" +
//                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook? Ook. Ook? Ook! Ook. Ook? Ook! Ook!\n" +
//                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook!\n" +
//                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook.\n" +
//                "Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
//                "Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook? Ook! Ook. Ook? Ook. Ook. Ook.\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook? Ook. Ook. Ook.\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook! Ook! Ook. Ook? Ook. Ook. Ook.\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook? Ook! Ook. Ook? Ook. Ook. Ook. Ook.\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook.\n" +
//                "Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook!\n" +
//                "Ook! Ook! Ook! Ook! Ook! Ook. Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook!\n" +
//                "Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook? Ook. ");
//
//        System.out.println(executeService.execute(ook,"","Ook","~"));
//        FileList fileList=new FileList("bay");
//        fileList.newFile("abc","Ook","12:33");
//        fileList.updateFile("abc","Ook","123:42");
//        System.out.println(fileList.checkFile("abc"));
        UserSaveFile userSaveFile=new UserSaveFile("bay","abc","Ook");
        userSaveFile.saveFile("asfddsadasdfads","12:99");
        System.out.println(userSaveFile.readFile("12:99"));

    }

}
