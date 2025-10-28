package io.github.wujun728.admin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 解析amis sdk.css ,存储图标,使用百度翻译,多行,对应复制到数据库里面
 */
@Slf4j
public class ReadIcons {
    static RestTemplate restTemplate = new RestTemplate();
    public static void main(String[] args) {
        String path = "D:\\work\\workspace\\workspace_hyz\\jqp\\jqp-admin\\src\\main\\resources\\static\\amis\\sdk.css";
        List<String> lines = FileUtil.readLines(path, "UTF-8");
        List<String> faLines = new ArrayList<>();

        Map<String,Icon> iconMap = new HashMap<>();

        List<Icon> icons = new ArrayList<>();
        for(String line:lines){
            if(faLines != null){
                if(line.equals("}")){

                    boolean nameEnd = false;
                    List<String> names = new ArrayList<>();
                    String content = null;
                    for(String nameLine:faLines){
                        if(!nameEnd){
                            if(nameLine.endsWith("{")){
                                names.add(getName(nameLine));
                                nameEnd = true;
                            }else{
                                names.add(getName(nameLine));
                            }
                        }else{
                            if(nameLine.contains("content")){
                                content = getContent(nameLine);
                                break;
                            }
                        }
                    }
                    if(content != null){
                        System.out.println("-----------------");
                        System.out.println(faLines);
                        System.out.println("names:"+names+"content:"+content);

                        Icon icon = iconMap.get(content);
                        if(icon == null){
                            icon = new Icon();
                            icon.content = content;
                            iconMap.put(content,icon);
                            icons.add(icon);
                        }
                        for(String name:names){
                            if(!icon.names.contains(name)){
                                icon.names.add(name);
                            }
                        }
                    }

                    faLines = null;
                }else{
                    faLines.add(line);
                }
                continue;
            }
            if(line.startsWith(".fa")){
                faLines = new ArrayList<>();
                faLines.add(line);
            }
        }
        System.out.println("insert into icon (icon) values ");
        for(Icon icon:icons){
//            for(String name:icon.names){
//                String url = StrUtil.format("https://v.api.aa1.cn/api/api-fanyi-yd/index.php?msg={}&type=2",name);
//                String json = restTemplate.getForObject(url, String.class);
//
//                String label = JSONUtil.parseObj(json).getStr("text");
//                icon.labels.add(label);
//            }
            System.out.println(StrUtil.format("('{}'),", icon.names.get(0)));
            //log.info("names:{},content:{},labels",icon.names,icon.content,icon.labels);
        }
        System.out.println(";");
    }

    private static class Icon{
        List<String> names = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        String content;
    }

    private static String getName(String name){
        if(name.contains("{")){
            name = name.substring(0,name.indexOf("{"));
        }
        if(name.contains(".fa.fa")){
            name = name.replace(".fa.fa-","");
        }else{
            name = name.replace(".fa-","");
        }
        if(name.contains(":")){
            name = name.split(":")[0];
        }
        return name.trim();
    }

    private static String getContent(String content){
        content = content.substring(content.indexOf(":")+1,content.indexOf(";"));
        return content.trim();
    }
}
