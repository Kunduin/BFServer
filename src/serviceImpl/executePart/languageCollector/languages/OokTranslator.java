package serviceImpl.executePart.languageCollector.languages;

import serviceImpl.executePart.commends.Commend;
import serviceImpl.executePart.languageCollector.languageInterface.LanguageTranslator;

import java.util.ConcurrentModificationException;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class OokTranslator implements LanguageTranslator{
    private StringBuffer bfCode=new StringBuffer();

    @Override
    public String translate(String code) {
        code=code.replaceAll(" |\\r|\\n","");
        for(int i=0;i<code.length();i+=8){
            switch (code.substring(i,i+8)){
                case "Ook.Ook?":
                    bfCode.append(Commend.NEXT_CHAR);
                    break;
                case "Ook?Ook.":
                    bfCode.append(Commend.PREVIOUS_CHAR);
                    break;
                case "Ook.Ook.":
                    bfCode.append(Commend.ADD);
                    break;
                case "Ook!Ook!":
                    bfCode.append(Commend.SUB);
                    break;
                case "Ook.Ook!":
                    bfCode.append(Commend.INPUT);
                    break;
                case "Ook!Ook.":
                    bfCode.append(Commend.OUTPUT);
                    break;
                case "Ook!Ook?":
                    bfCode.append(Commend.BRANKET_LEFT);
                    break;
                case "Ook?Ook!":
                    bfCode.append(Commend.BRANKET_RIGHT);
                    break;
                default:
                    break;
            }
        }
        return bfCode.substring(0);
    }
}
