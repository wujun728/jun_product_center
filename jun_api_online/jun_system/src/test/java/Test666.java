import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.db.sql.SqlExecutor;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


public class Test666 {


    public static void main(String[] args) {
        List<String> filenames = FileUtil.readLines(new File("D:\\Documents\\Desktop\\MP4NEW.txt"),"UTF-8");
        System.out.println(filenames.size());
        Set<String> sets = Sets.newHashSet();
        filenames.forEach(i->{sets.add(i);});
        System.out.println("不重复的mp4链接："+sets.size());

        String fileName = "new666";

        List<String> urls = FileUtil.readLines(new File("D:\\Documents\\Desktop\\"+fileName+".txt"),"UTF-8");
        Set<String> sets2 = Sets.newHashSet();
        Map<String,String> map2 = new HashMap<>();
        urls.forEach(i->{
            map2.put(FileNameUtil.getName(i),i);
            sets2.add(i);
        });
        System.out.println("不重复的new链接1："+sets2.size());
        System.out.println("不重复的new链接1："+map2.keySet().size());

        List<String> urlsNew = Lists.newArrayList();
        List<String> urlsNewMP4 = Lists.newArrayList();
        List<String> urlsExists = Lists.newArrayList();
//        for(String url : sets2){
//            if(!sets.stream().anyMatch(i->{
//                //return url.endsWith(i);
//                return FileNameUtil.getName(url).equalsIgnoreCase(i);
//               // return url.substring(url.lastIndexOf("/"), url.length()).equalsIgnoreCase(i);
//            })){
//                urlsNew.add(url);
//            }else{
//                urlsExists.add(url);
//            }
//        }

        List<String> difference = map2.keySet().stream()
                .filter(element -> !sets.contains(element))
                .collect(Collectors.toList());
        map2.entrySet().forEach((k)->{
            if(difference.contains(k.getKey())){
                urlsNew.add(k.getValue());
                if(k.getValue().contains(".mp4")){
                    urlsNewMP4.add(k.getValue());
                }
            }else{
                urlsExists.add(k.getValue());
            }
        });

        System.out.println("重复的mp4链接1exitst："+urlsExists.size());
        FileUtil.writeUtf8Lines(urlsExists,"D:\\Documents\\Desktop\\exists-"+ fileName +".txt");
        System.err.println("不重复的mp4链接1new："+urlsNew.size());
        FileUtil.writeUtf8Lines(urlsNew,"D:\\Documents\\Desktop\\new-"+ fileName +".txt");
        System.err.println("不重复的mp4链接1new2："+urlsNewMP4.size());
        FileUtil.writeUtf8Lines(urlsNewMP4,"D:\\Documents\\Desktop\\new-n-"+ fileName +".txt");

//        String result = HttpUtil.post("https://gitlab.billjc.com/oauth/token?grant_type=password", "{\n" +
//                "    \"username\": \"wujun82921\",\n" +
//                "    \"password\": \"Abcde123456\"\n" +
//                "}");
//        Console.log(result);
    }

}
