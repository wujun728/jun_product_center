package io.github.wujun728.admin;

import org.junit.jupiter.api.Test;
import org.ssssssss.script.MagicScriptContext;

import java.util.HashMap;
import java.util.Map;

public class ScriptTest {
    @Test
    void testScript(){
        MagicScriptContext context = new MagicScriptContext();

        Map<String,Object> map = new HashMap<>();
        Float f = new Float(10010.2);
        Integer m = new Integer(100);
        map.put("age",f);
        map.put("m",m);
        Object eval = context.eval("age>=10 && m < 100", map);
        System.out.println(eval);
    }
}
