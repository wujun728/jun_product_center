package com.jun.plugin.utils.rss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Guid;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.WireFeedOutput;
import com.sun.syndication.io.XmlReader;  
  
//http://rss.webofknowledge.com/rss?e=4f1b64b6b221ea05&c=8c6909b93bf3eb38a2066a826b61a412  
public class RssParse {  
    public static void main(String[] args) {  
        try {  
            // parseXml(new  
            // URL("http://rss.webofknowledge.com/rss?e=4f1b64b6b221ea05&c=8c6909b93bf3eb38a2066a826b61a412"));  
            parseXml(new URL("http://rss.cnbeta.com/rss"));  
            String xml=createXml();  
            parseString(xml);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    private static void parseString(String xml) throws Exception {  
      
        SyndFeedInput input = new SyndFeedInput();  
        SyndFeed feed = null;  
        ByteArrayInputStream inputStream=new ByteArrayInputStream(xml.getBytes("UTF-8"));  
        feed = input.build(new XmlReader(inputStream));  
        List entries = feed.getEntries();// 得到所有的标题<title></title>  
        for (int i = 0; i < entries.size(); i++) {  
            SyndEntry entry = (SyndEntry) entries.get(i);  
            System.out.println(entry.getTitle());  
        }  
        System.out.println("feed size:" + feed.getEntries().size());  
    }  
  
    /** 
     * 根据链接地址得到数据 
     */  
    public static void parseXml(URL url) throws IllegalArgumentException,  
            FeedException {  
        try {  
            SyndFeedInput input = new SyndFeedInput();  
            SyndFeed feed = null;  
            URLConnection conn;  
            conn = url.openConnection();  
            String content_encoding = conn.getHeaderField("Content-Encoding");  
            if (content_encoding != null && content_encoding.contains("gzip")) {  
                System.out.println("conent encoding is gzip");  
                GZIPInputStream gzin = new GZIPInputStream(  
                        conn.getInputStream());  
                feed = input.build(new XmlReader(gzin));  
            } else {  
                feed = input.build(new XmlReader(conn.getInputStream()));  
            }  
  
            List entries = feed.getEntries();// 得到所有的标题<title></title>  
            
            for (int i = 0; i < entries.size(); i++) {  
                SyndEntry entry = (SyndEntry) entries.get(i);  
                System.out.println(entry.getTitle());  
                System.out.print("  :  "+entry.getLink());  
                System.out.print("  :  "+entry.getDescription());  
                System.out.print("  :  "+entry.getPublishedDate());  
//                System.out.print("  :  "+entry.getAuthor());  
//                System.out.print("  :  "+entry.getUri());  
//                System.out.print("  :  "+entry.getCategories());  
//                System.out.print("  :  "+entry.getContents());  
//                System.out.print("  :  "+entry.getContributors());  
//                System.out.print("  :  "+entry.getEnclosures());  
//                System.out.print("  :  "+entry.getForeignMarkup());  
//                System.out.print("  :  "+entry.getModules());  
//                System.out.print("  :  "+entry.getUpdatedDate());  
            }  
            System.out.println("feed size:" + feed.getEntries().size());  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
    /** 
     * 根据链接地址得到数据 
     */  
    public static List parseXmlForUrl(URL url) throws IllegalArgumentException,  
    FeedException {  
    	List list=new ArrayList();
    	try {  
    		SyndFeedInput input = new SyndFeedInput();  
    		SyndFeed feed = null;  
    		URLConnection conn;  
    		conn = url.openConnection();  
    		String content_encoding = conn.getHeaderField("Content-Encoding");  
    		if (content_encoding != null && content_encoding.contains("gzip")) {  
    			System.out.println("conent encoding is gzip");  
    			GZIPInputStream gzin = new GZIPInputStream(  
    					conn.getInputStream());  
    			feed = input.build(new XmlReader(gzin));  
    		} else {  
    			feed = input.build(new XmlReader(conn.getInputStream()));  
    		}  
    		
    		List entries = feed.getEntries();// 得到所有的标题<title></title>  
    		
    		
    		for (int i = 0; i < entries.size(); i++) {  
    			SyndEntry entry = (SyndEntry) entries.get(i);  
    			System.out.println(entry.getTitle());  
    			System.out.print("  :  "+entry.getLink());  
    			System.out.print("  :  "+entry.getDescription().getValue());  
    			System.out.print("  :  "+entry.getPublishedDate()); 
    			
    			Map map=new HashMap();
    			map.put("title", entry.getTitle());
    			map.put("description", entry.getDescription());
    			map.put("link", entry.getLink());
    			map.put("pubdate", entry.getPublishedDate());
    			list.add(map);
    			
//                System.out.print("  :  "+entry.getAuthor());  
//                System.out.print("  :  "+entry.getUri());  
//                System.out.print("  :  "+entry.getCategories());  
//                System.out.print("  :  "+entry.getContents());  
//                System.out.print("  :  "+entry.getContributors());  
//                System.out.print("  :  "+entry.getEnclosures());  
//                System.out.print("  :  "+entry.getForeignMarkup());  
//                System.out.print("  :  "+entry.getModules());  
//                System.out.print("  :  "+entry.getUpdatedDate());  
    		}  
    		System.out.println("feed size:" + feed.getEntries().size());  
    		
    	} catch (IOException e) {  
    		e.printStackTrace();  
    	}
		return list;  
    	
    }  
      
    public static String createXml() throws Exception {        
        /* 根据Channel源码提供的英文,Channel对象有两个构造器，一个默认的无参构造器用于clone对象，一个是有参的     
        * 我们自己指定的必须使用有参数的（因为我们需要许可证），指构造方法必须要创建一个type（版本），这个type不能随便写，必须要以rss_开头的版本号     
        * Licensed under the Apache License, Version 2.0 (the "License");     
        * 因为当前版本是2.0，所以就是rss_2.0，必须是rss_2.0否则会抛异常，该源码中写的已经很明白。     
        */       
       Channel channel = new Channel("rss_2.0");        
       channel.setTitle("channel标题");//网站标题        
        channel.setDescription("channel的描述");//网站描述        
        channel.setLink("www.shlll.net");//网站主页链接        
        channel.setEncoding("utf-8");//RSS文件编码        
        channel.setLanguage("zh-cn");//RSS使用的语言        
        channel.setTtl(5);//time to live的简写，在刷新前当前RSS在缓存中可以保存多长时间（分钟）        
        channel.setCopyright("版权声明");//版权声明        
        channel.setPubDate(new Date());//RSS发布时间        
        List<Item> items = new ArrayList<Item>();//这个list对应rss中的item列表                  
        Item item = new Item();//新建Item对象，对应rss中的<item></item>        
       item.setAuthor("hxliu");//对应<item>中的<author></author>        
       item.setTitle("新闻标题");//对应<item>中的<title></title>        
       item.setGuid(new Guid());//GUID=Globally Unique Identifier 为当前新闻指定一个全球唯一标示，这个不是必须的        
        item.setPubDate(new Date());//这个<item>对应的发布时间        
        item.setComments("注释");//代表<item>节点中的<comments></comments>        
        //新建一个Description，它是Item的描述部分        
        Description description = new Description();        
       description.setValue("新闻主题");//<description>中的内容        
        item.setDescription(description);//添加到item节点中        
        items.add(item);//代表一个段落<item></item>，        
        channel.setItems(items);        
        //用WireFeedOutput对象输出rss文本        
        WireFeedOutput out = new WireFeedOutput();        
        try {        
            String xml=out.outputString(channel);  
            System.out.println(xml);   
            return xml;  
        } catch (IllegalArgumentException e) {        
            e.printStackTrace();        
        } catch (FeedException e) {        
            e.printStackTrace();        
        }  
        return null;  
    }     
}  