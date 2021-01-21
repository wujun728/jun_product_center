package org.myframework.codeutil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.myframework.util.FileUtil;

/**
 * 
 * <ol>根据模版内容生成具体的源码文件
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2016年6月2日
 *
 */
public class CodeTool {
	public static String RESOURCE_PATH_PREFIX = "classpath:" ;

	private String tmplDir = "classpath:templates";
	private String encoding = "UTF-8";
	private String tmplFile;// 
	private String absolutePath;// 
	private Map context = new HashMap();
	boolean isDebug = false;

	public boolean getIsDebug() {
		return isDebug;
	}

	public void put(String key, Object value) {
		context.put(key, value);
	}

	public void put(Map paramMap) {
		context.putAll(paramMap);
	}

	public Object get(String key) {
		return context.get(key);
	}

	public void setIsDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public String getTmplFile() {
		return tmplFile;
	}

	public void setTmplFile(String tmplFile) {
		this.tmplFile = tmplFile;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public Map getContext() {
		return context;
	}

	public void setContext(Map context) {
		this.context = context;
	}

	public void createFileByTmpl() throws Exception {
		if(tmplDir.startsWith(RESOURCE_PATH_PREFIX)){
			tmplFile = tmplDir.substring(RESOURCE_PATH_PREFIX.length())+"/"+tmplFile;
		}
		Template template = Velocity.getTemplate(tmplFile, encoding);
		VelocityContext tmplContext = new VelocityContext(context);
		FileUtil.createFile(absolutePath);
		PrintWriter writer = new PrintWriter(
				new FileOutputStream(absolutePath), true);
		template.merge(tmplContext, writer);
		writer.flush();
		writer.close();
		if (isDebug) {
			debug();
		}
	}

	public String getText(String text) throws Exception {
		VelocityContext tmplContext = new VelocityContext(context);
		StringWriter w = new StringWriter();
		Velocity.evaluate(tmplContext, w, super.getClass().getName(), text);
		return w.toString();
	}

	/**
	 * 模版文件所在路径
	 *
	 * @return
	 */
	public String getTemplatePath() {
		if(tmplDir.startsWith(RESOURCE_PATH_PREFIX)){
			URL url = this.getClass().getResource("/"+tmplDir.substring(RESOURCE_PATH_PREFIX.length()));
			File file = new File(url.getPath());
			return file.getAbsolutePath();
		}
		return tmplDir;
	}
	
	public void setTmplDir(String tmplDir) {
		this.tmplDir = tmplDir;
	}

	public void initVelocity(){
		Properties p = new Properties();
		if(!tmplDir.startsWith(RESOURCE_PATH_PREFIX)){ //从目录加载vm文件
			p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, tmplDir);
		}else {//从classpath加载vm文件
			p.setProperty("file.resource.loader.class",
		                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		}
		Velocity.init(p);
	}
	
	/**
	 * 设置模版引擎，主要指向获取模版路径
	 */
	public static VelocityEngine getVelocityEngine() {
        Properties p = new Properties();
        p.setProperty("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        VelocityEngine engine = new VelocityEngine();
	    return engine;
	}

	/**
	 * 将模板转化成为文件
	 * 
	 * @param content 内容对象
	 * @param template 模板文件
	 * @param path 文件生成的目录
	 */
	public static void vmToFile(VelocityContext context, String templatePath,
	        String path) throws IOException {
	    File parent = new File(path).getParentFile();
	    File file = new File(path);
	    if (parent.exists() == false) {
	        parent.mkdirs();
	    }
	    if (file.exists() == false) {
	        file.createNewFile();
	    }
	    VelocityEngine velocity = getVelocityEngine();
	    Template template = velocity.getTemplate(templatePath, "UTF-8");
	    FileOutputStream fos = new FileOutputStream(path);
	    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos,
	    		"UTF-8")); // 设置写入的文件编码,解决中文问题
	    template.merge(context, writer);
	    writer.close();
	}

	public void debug() throws Exception {
//		VelocityEngine.FILE_RESOURCE_LOADER_PATH
//		Velocity.addProperty("file.resource.loader.path", getClassPath()
//				+ tmplDir);
//		Properties p = new Properties();
//		p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, getTemplatePath() );
//		Velocity.init(p);
		Template template = Velocity.getTemplate(tmplFile, encoding);//  
		VelocityContext tmplContext = new VelocityContext(context); //  
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				System.out));
		if (template != null)
			template.merge(tmplContext, writer); // 
		writer.flush();
		writer.close();
	}

	public CodeTool() {
		super();
	}
	
	public CodeTool(String tmplDir) {
		this.tmplDir = tmplDir ;
		initVelocity();
	}

	public CodeTool(String tmplFile, String absolutePath) {
		super();
		this.tmplFile = tmplFile;
		this.absolutePath = absolutePath;
	}

	public static void main(String[] args) throws Exception {
//		CodeTool tool = new CodeTool();
//		tool.setTmplFile("tmpl/DAOImpl.tmpl");
//		tool.setAbsolutePath("D:/ABC/abc1.txt");
//		tool.createFileByTmpl();
		System.out.println("111");
	}
}
