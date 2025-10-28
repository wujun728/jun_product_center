package io.github.wujun728.admin;

import io.github.wujun728.admin.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TestUrl {
    @Test
    public void regexSpringTest() {
        AntPathMatcher matcher = new AntPathMatcher();
        String path = "/test/a/b";
        String pattern = "/test/${id}";
        boolean isMatch=matcher.match(pattern, path);
        log.info("urlMatch:{}",UrlUtil.match("post:/admin/common/abc/get","post:/admin/common/{code}/get"));
        log.info("isMatch:{}",isMatch);
    }
    @Test
    public void testArgs(){
        String path = "/a/${b}/c/${x}";

        Pattern pattern = Pattern.compile("(\\$\\{\\w+\\})");
        Matcher matcher = pattern.matcher(path);
        int start = 0;
        while (matcher.find(start)){
            String str = matcher.group();
            start = matcher.end();
            log.info("args:{}",str);
        }
    }
    @Test
    public void test(){
        String url = "/a/${x}/${b}/a";
        String pattern = UrlUtil.getUrl(url);
        log.info("pattern:{}",pattern);
        log.info("match:{}",UrlUtil.match("/a/sss/ddd/a?s=123",pattern));
    }
}
