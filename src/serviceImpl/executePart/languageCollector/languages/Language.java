package serviceImpl.executePart.languageCollector.languages;

import serviceImpl.executePart.languageCollector.languageInterface.LanguageTranslator;

/**
 * Created by 15852 on 2017/6/21 0021.
 */
public class Language {
    public static final String BRAINFUCK="Brainfuck";
    public static final String OOK="Ook";

    public static String translate(String code,String language){
        LanguageTranslator translator;

        switch (language){
            case Language.BRAINFUCK:
                translator=new BFTranslator();
                break;
            case Language.OOK:
                translator=new OokTranslator();
                break;
            default:
                translator=new BFTranslator();
        }

        return translator.translate(code);

    }
}
