package io.github.wujun728.admin.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class UrlUtil {
    private static final Pattern pattern = Pattern.compile("(\\$\\{\\w+\\})");
    private static final AntPathMatcher matcher = new AntPathMatcher();
    public static String getUrl(String url){
        if(url.contains("?")){
            url = url.substring(0,url.indexOf("?"));
        }
        if(url.contains(":")){
            url = url.substring(url.indexOf(":")+1);
        }
        Matcher matcher = pattern.matcher(url);
        int start = 0;
        Map<String, Object> params = new HashMap<>();
        while (matcher.find(start)){
            String str = matcher.group();
            start = matcher.end();
            str = str.substring(2,str.length()-1);
            params.put(str,"*");
        }
        return TemplateUtil.getValue(url,params);
    }

    public static boolean match(String url,String pattern){
        if(url.contains("?")){
            url = url.substring(0,url.indexOf("?"));
        }
        return matcher.match(pattern, url);
    }

    public static List<String> getPathArgNames(String url){
        List<String> names = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\{([^\\}]+)\\}");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()){
            String arg = matcher.group(1);
            if(!names.contains(arg)){
                names.add(arg);
            }
        }
        return names;
    }
}
