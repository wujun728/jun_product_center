package com.lu.dynamicplus.modular.business.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.lu.core.utils.ToolUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 文章列表
 * </p>
 *
 * @author lu
 * @since 2019-02-07
 */
@Data
@Accessors(chain = true)
@TableName("lu_article")
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    /**
     * 父id
     */
    @TableField("header_pid")
    private Long headerPid;
    /**
     * 作者id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 授权等级
     */
    private String authLevel;
    /**
     * 积分
     */
    private String points;
    /**
     * 浏览量
     */
    private Integer views;
    /**
     * 点赞数
     */
    @TableField("thumbs_up")
    private Integer thumbsUp;
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;

    public static void phone() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/num.txt")));
        List<String> ls = new ArrayList<>();
        String line = "";
        Random rd = new Random(2);
        Random random = new Random();
        while((line = br.readLine()) != null){
            String temp = "";
            int flag = rd.nextInt(2);
            if(flag == 1){
                int len = random.nextInt(4) + 8;
                temp = "QQ号码："+ ToolUtil.getRandomNum(len) +" ";
            }
            temp += "手机号码：" + line;
            ls.add(temp);
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/num2.txt")));
        for (String s : ls) {
            bw.write(s + "\t\n");
        }
        bw.close();
        br.close();
    }

    public static void zhiwen() throws Exception {
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String tokenString = System.currentTimeMillis() + "" + new Random().nextInt(99999);
            try {
                MessageDigest mDigest = MessageDigest.getInstance("md5");
                byte[] md5 = mDigest.digest(tokenString.getBytes());
                BASE64Encoder encoder = new BASE64Encoder();
                String encode = encoder.encode(md5);
                System.out.println(encode);
                ls.add(encode);
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/zhiwen.txt")));
        for (String s : ls) {
            bw.write(s + "\t\n");
        }
        bw.close();
    }

    public static void region() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/OCR数据采集/全国省市区县.txt")));
        List<String> ls = new ArrayList<>();
        String line = "";
        Random rd = new Random(2);
        Random random = new Random();
        while((line = br.readLine()) != null){
            String temp = line;
            ls.add(temp.replace("自治区", "自治区省"));
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/OCR数据采集/全国省市区县2.txt")));
        for (String s : ls) {
            bw.write(s + "\t\n");
        }
        bw.close();
        br.close();
    }

    public static void clean() throws Exception {

        String sheng = "安徽省";
        File fss = new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/安徽/派出所-公安局/派出所");
        String targetRoot = "/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/安徽/派出所";
        File[] fs = fss.listFiles();
        for (File fff : fs) {
            File[] ff = fff.listFiles();
            if(ff != null){
                for (File f : ff) {
                    List<String> msgs = new ArrayList<>();
                    String fName = f.getName();
                    fName = fName.replace(".txt", "");
                    String[] fNames = fName.split(" ");
                    String fileName = sheng + " " +fName;
                    BufferedReader br = new BufferedReader(new FileReader(f));
                    String line = "";
                    while((line = br.readLine()) != null){
                        msgs.add(fileName + " " + line);
                    }

                    String shi = fNames[0];
                    String qu = fNames[1];

                    String tempRoot =  targetRoot + "/" + shi + "/" + qu;

                    File file = new File(tempRoot);
                    if(!file.exists()){
                        file.mkdirs();
                    }

                    String write = tempRoot + "/" + fName + ".txt";

                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(write)));

                    for (String s : msgs) {
                        bw.write(s + "\t\n");
                    }
                    bw.close();
                    br.close();

                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
//        clean();
        BufferedReader br = new BufferedReader(new FileReader(new File("/Users/zhanglu/Desktop/lu/workspace/xinfu_workspace/ocr/OCR技术相关/数据格式/数据采集(安徽)/详细地址/固定居住地/总揽(14699).txt")));
        String line = "";
        int count = 0;
        while((line = br.readLine()) != null){
            count ++;
        }
        System.out.println(count);
    }

}
