package sy.util.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

/**
 * 代码生成工具
 * 
 * 主要用来通过model类生成dao、daoImpl、service、serviceImpl、controller文件
 * 
 * http://git.oschina.net/sphsyv/sypro
 * 
 * @author 孙宇
 *
 */
public class GeneratorUtil {

	/**
	 * 测试方法
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		GeneratorUtil generatorUtil = new GeneratorUtil("D:/generator", "C:/Users/SunYu/git/sypro/sy-web-demo/src/main/java/sy/model/demo", "sy.dao.demo", "sy.service.demo", "sy.controller.demo");
		Boolean b = generatorUtil.generator();// 根据model目录所有model生成
		// Boolean b = generatorUtil.generator(new String[] { "DemoA" });// 根据指定目录指定model生成
		if (b) {
			System.out.println("生成完毕");
		} else {
			System.out.println("第二个参数有问题，不存在的目录？");
		}
	}

	private String generatorPath;// 将生成的文件写入哪个目录
	private String modelPath;// 模型文件路径
	private String pkType = "Long";// 主键类型
	private String daoPackageName;// dao接口的包名
	private String daoPath;// dao生成目录
	private String daoImplPackageName;// daoImpl的包名
	private String daoImplPath;// daoImpl生成目录
	private String servicePackageName;// service接口的包名
	private String servicePath;// service生成目录
	private String serviceImplPackageName;// serviceImpl的包名
	private String serviceImplPath;// serviceImpl生成目录
	private String controllerPackageName;// controller的包名
	private String controllerPath;// controller生成目录

	/**
	 * 构造
	 * 
	 * @param generatorPath
	 *            将生成的文件写入哪个目录
	 * @param modelPath
	 *            模型文件路径
	 * @param daoPackageName
	 *            要生成dao接口的包名
	 * @param servicePackageName
	 *            要生成serviceImpl的包名
	 * @param controllerPackageName
	 *            要生成controller的包名
	 */
	public GeneratorUtil(String generatorPath, String modelPath, String daoPackageName, String servicePackageName, String controllerPackageName) {
		this.generatorPath = generatorPath;
		this.modelPath = modelPath;
		this.daoPackageName = daoPackageName;
		this.servicePackageName = servicePackageName;
		this.controllerPackageName = controllerPackageName;

		daoPath = generatorPath + "/" + daoPackageName.replaceAll("\\.", "/");
		daoImplPackageName = daoPackageName + ".impl";
		daoImplPath = generatorPath + "/" + daoImplPackageName.replaceAll("\\.", "/");
		servicePath = generatorPath + "/" + servicePackageName.replaceAll("\\.", "/");
		serviceImplPackageName = servicePackageName + ".impl";
		serviceImplPath = generatorPath + "/" + serviceImplPackageName.replaceAll("\\.", "/");
		controllerPath = generatorPath + "/" + controllerPackageName.replaceAll("\\.", "/");
	}

	/**
	 * 通过model类生成dao、daoImpl、service、serviceImpl、controller文件
	 * 
	 * @throws IOException
	 */
	synchronized public Boolean generator() throws IOException {

		return generator(null);

	}

	/**
	 * 通过model类生成dao、daoImpl、service、serviceImpl、controller文件
	 * 
	 * @param modelNames
	 *            要通过哪些model生成
	 * @return
	 * @throws IOException
	 */
	synchronized public Boolean generator(String[] modelNames) throws IOException {

		ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
		Configuration cfg = Configuration.defaultConfiguration();
		GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

		System.out.println("准备在【" + generatorPath + "】生成文件");
		System.out.println("Model文件路径【" + modelPath + "】");
		System.out.println("主键类型【" + pkType + "】");
		System.out.println("预计生成DAO的包名【" + daoPackageName + "】");
		System.out.println("预计在【" + daoPath + "】生成dao接口文件");
		System.out.println("预计生成DAOImpl的包名【" + daoImplPackageName + "】");
		System.out.println("预计在【" + daoImplPath + "】生成daoImpl类文件");
		System.out.println("预计生成service的包名【" + servicePackageName + "】");
		System.out.println("预计在【" + servicePath + "】生成service接口文件");
		System.out.println("预计生成serviceImpl的包名【" + serviceImplPackageName + "】");
		System.out.println("预计在【" + serviceImplPath + "】生成serviceImpl类文件");
		System.out.println("预计生成controller的包名【" + controllerPackageName + "】");
		System.out.println("预计在【" + controllerPath + "】生成controller类文件");
		System.out.println("------------------------------------------------------------------------------------------");

		File file = new File(modelPath);
		File[] modelFiles = file.listFiles();// model文件数组

		if (modelFiles != null) {

			List<File> modelFileList = Arrays.asList(modelFiles);
			List<File> generatorFileList = new ArrayList<File>();

			if (modelNames != null && modelNames.length > 0) {// 如果要指定某些model来生成dao、service、controller文件
				for (File modelFile : modelFileList) {
					for (String mn : modelNames) {
						if (modelFile.getName().equalsIgnoreCase(mn + ".java")) {
							generatorFileList.add(modelFile);
						}
					}
				}
				modelFileList = generatorFileList;
			}

			generatorDao(gt, modelFileList);

			generatorDaoImpl(gt, modelFileList);

			generatorService(gt, modelFileList);

			generatorServiceImpl(gt, modelFileList);

			generatorColtroller(gt, modelFileList);
		}

		gt.close();

		if (modelFiles == null) {
			return false;
		}
		return true;

	}

	private void generatorColtroller(GroupTemplate gt, List<File> modelFileList) throws IOException {
		System.out.println("准备生成Controller类......");
		File path = new File(controllerPath);
		path.mkdirs();
		for (File modelFile : modelFileList) {
			if (modelFile.getName().lastIndexOf(".java") > -1) {
				Template t = gt.getTemplate("/controller.btl");
				Map<String, String> bindingMap = getBindingMap(modelFile);
				t.binding(bindingMap);
				// String str = t.render();
				// System.out.println(str);
				FileOutputStream fileOutputStream = new FileOutputStream(controllerPath + "/" + bindingMap.get("modelName") + "Controller.java");// 生成Controller类文件
				t.renderTo(fileOutputStream);
				System.out.println("生成【" + bindingMap.get("modelName") + "Controller.java】");
			}
		}
		System.out.println("所有Controller类生成完毕！");
		System.out.println("------------------------------------------------------------------------------------------");
	}

	private void generatorServiceImpl(GroupTemplate gt, List<File> modelFileList) throws IOException {
		System.out.println("准备生成Service实现类......");
		File path = new File(serviceImplPath);
		path.mkdirs();
		for (File modelFile : modelFileList) {
			if (modelFile.getName().lastIndexOf(".java") > -1) {
				Template t = gt.getTemplate("/serviceImpl.btl");
				Map<String, String> bindingMap = getBindingMap(modelFile);
				t.binding(bindingMap);
				// String str = t.render();
				// System.out.println(str);
				FileOutputStream fileOutputStream = new FileOutputStream(serviceImplPath + "/" + bindingMap.get("modelName") + "ServiceImpl.java");// 生成Service实现类文件
				t.renderTo(fileOutputStream);
				System.out.println("生成【" + bindingMap.get("modelName") + "ServiceImpl.java】");
			}
		}
		System.out.println("所有Service实现类生成完毕！");
		System.out.println("------------------------------------------------------------------------------------------");
	}

	private void generatorService(GroupTemplate gt, List<File> modelFileList) throws IOException {
		System.out.println("准备生成Service接口......");
		File path = new File(servicePath);
		path.mkdirs();
		for (File modelFile : modelFileList) {
			if (modelFile.getName().lastIndexOf(".java") > -1) {
				Template t = gt.getTemplate("/service.btl");
				Map<String, String> bindingMap = getBindingMap(modelFile);
				t.binding(bindingMap);
				// String str = t.render();
				// System.out.println(str);
				FileOutputStream fileOutputStream = new FileOutputStream(servicePath + "/" + bindingMap.get("modelName") + "Service.java");// 生成Service接口文件
				t.renderTo(fileOutputStream);
				System.out.println("生成【" + bindingMap.get("modelName") + "Service.java】");
			}
		}
		System.out.println("所有Service接口生成完毕！");
		System.out.println("------------------------------------------------------------------------------------------");
	}

	private void generatorDaoImpl(GroupTemplate gt, List<File> modelFileList) throws IOException {
		System.out.println("准备生成DAO实现类......");
		File path = new File(daoImplPath);
		path.mkdirs();
		for (File modelFile : modelFileList) {
			if (modelFile.getName().lastIndexOf(".java") > -1) {
				Template t = gt.getTemplate("/daoImpl.btl");
				Map<String, String> bindingMap = getBindingMap(modelFile);
				t.binding(bindingMap);
				// String str = t.render();
				// System.out.println(str);
				FileOutputStream fileOutputStream = new FileOutputStream(daoImplPath + "/" + bindingMap.get("modelName") + "DaoImpl.java");// 生成DAO实现类文件
				t.renderTo(fileOutputStream);
				System.out.println("生成【" + bindingMap.get("modelName") + "DaoImpl.java】");
			}
		}
		System.out.println("所有DAO实现类生成完毕！");
		System.out.println("------------------------------------------------------------------------------------------");
	}

	private void generatorDao(GroupTemplate gt, List<File> modelFileList) throws IOException {
		System.out.println("准备生成DAO接口......");
		File path = new File(daoPath);
		path.mkdirs();
		for (File modelFile : modelFileList) {
			if (modelFile.getName().lastIndexOf(".java") > -1) {
				Template t = gt.getTemplate("/dao.btl");
				Map<String, String> bindingMap = getBindingMap(modelFile);
				t.binding(bindingMap);
				// String str = t.render();
				// System.out.println(str);
				FileOutputStream fileOutputStream = new FileOutputStream(daoPath + "/" + bindingMap.get("modelName") + "Dao.java");// 生成DAO接口文件
				t.renderTo(fileOutputStream);
				System.out.println("生成【" + bindingMap.get("modelName") + "Dao.java】");
			}
		}
		System.out.println("所有DAO接口生成完毕！");
		System.out.println("------------------------------------------------------------------------------------------");
	}

	private Map<String, String> getBindingMap(File modelFile) throws IOException {
		FileInputStream fis = new FileInputStream(modelFile);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String firstString = br.readLine();
		String modelPackageName = firstString.replace("package ", "").replace(";", "").trim();// model的包名
		br.close();
		isr.close();
		fis.close();
		String modelName = modelFile.getName().substring(0, modelFile.getName().indexOf("."));// model的名字
		Map<String, String> bindingMap = new HashMap<String, String>();
		bindingMap.put("modelPackageName", modelPackageName);
		bindingMap.put("pkType", pkType);
		bindingMap.put("modelName", modelName);
		bindingMap.put("lowerModelName", StringUtil.lowerFirst(modelName));
		bindingMap.put("daoPackageName", daoPackageName);
		bindingMap.put("daoImplPackageName", daoImplPackageName);
		bindingMap.put("servicePackageName", servicePackageName);
		bindingMap.put("serviceImplPackageName", serviceImplPackageName);
		bindingMap.put("controllerPackageName", controllerPackageName);
		return bindingMap;
	}

}
