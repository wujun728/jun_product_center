package com.sanri.tools.modules.name.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 符号翻译
 */
@Component
@Slf4j
public class SymbolTranslate implements CharHandler {
    private static final Map<String,String> symbolMap = new HashMap<String, String>();
    static {
        symbolMap.put("+","plus");
        symbolMap.put("-","minus");
        symbolMap.put("*","multiply");
        symbolMap.put("/","division");
    }

    @Override
    public void handler(TranslateCharSequence translateCharSequence) {
        Set<String> needTranslateWords = translateCharSequence.getNeedTranslateWords();
        for (String needTranslateWord : needTranslateWords) {
            String word = symbolMap.get(needTranslateWord);
            if(word != null){
                translateCharSequence.addTranslate(needTranslateWord,word);
                translateCharSequence.setTranslate(true,needTranslateWord);
            }
        }
    }
}
