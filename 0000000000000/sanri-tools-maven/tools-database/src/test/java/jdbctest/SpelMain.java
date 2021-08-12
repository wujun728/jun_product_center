package jdbctest;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * spel 还是强大点
 */
public class SpelMain {

//    @Test
//    public void testRandomData() throws OgnlException {
//        OgnlContext context = new OgnlContext();
//        Object value = Ognl.getValue("@com.sanri.tools.modules.core.utils.RandomUtil@idcard()", context);
//        System.out.println(value);
//    }

//    @Test
//    public void testRegexRandomData() throws OgnlException {
//        OgnlContext context = new OgnlContext();
//        Object value = Ognl.getValue("@com.sanri.tools.modules.core.service.data.RegexRandomDataService@regexRandom('\\d{10}\\s\\w{3}')", context);
//        System.out.println(value);
//    }

    @Test
    public void spelRandom(){
        String spelStr = "T(com.sanri.tools.modules.core.service.data.RegexRandomDataService).regexRandom('\\d{10}\\s\\w{3}')";
        ExpressionParser expParser = new SpelExpressionParser();
        Expression exp = expParser.parseExpression(spelStr);
        String result = exp.getValue(String.class);
        System.out.println(result);
    }
}
