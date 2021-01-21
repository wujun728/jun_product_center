package sy.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.PhysicalTypeDef;
import sy.service.InitAppServiceI;
import sy.util.base.FileUtils;

import com.alibaba.fastjson.JSON;

/**
 * 初始化业务 数据库
 * 
 * 
 */
@Service
public class InitAppServiceImpl implements InitAppServiceI {

	private static final Logger logger = Logger
			.getLogger(InitAppServiceImpl.class);

	private static final String FILEPATH = "initAppDataBase.xml";

	private static final String DATA_FILE_PATH = "initAppMySQLDbData.sql";
	@Autowired
	private BaseDaoI baseDao;

	@Override
	synchronized public void initDb() {
		try {
			Document document = new SAXReader().read(Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(FILEPATH));

//			initAppPhysicalTypeDef(document);// 初始化 体格类型定义表
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * PHYSICAL_TYPE_DEF
	 * 
	 * @param document
	 */
	@SuppressWarnings("unchecked")
	private void initAppPhysicalTypeDef(Document document) {
		List<Object> childNodes = document
				.selectNodes("//PHYSICAL_TYPE_DEFs/PHYSICAL_TYPE_DEF");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			PhysicalTypeDef physicalTypeDef = (PhysicalTypeDef) baseDao
					.getById(PhysicalTypeDef.class, Integer.valueOf(node.valueOf("@phyId")));
			if (physicalTypeDef == null) {
				physicalTypeDef = new PhysicalTypeDef();
			}

			physicalTypeDef.setPhyId(Integer.valueOf(node.valueOf("@phyId")));

			if (!StringUtils.isBlank(node.valueOf("@parentId"))) {
				physicalTypeDef.setPhysicalTypeDef((PhysicalTypeDef) baseDao
						.getById(PhysicalTypeDef.class,
								node.valueOf("@parentId")));
			} else {
				physicalTypeDef.setPhysicalTypeDef(null);
			}

			physicalTypeDef.setPhyName(node.valueOf("@name"));
			physicalTypeDef.setExt1(node.valueOf("@ext"));

			logger.info(JSON.toJSONStringWithDateFormat(physicalTypeDef,
					"yyyy-MM-dd HH:mm:ss"));

			baseDao.saveOrUpdate(physicalTypeDef);
		}
	}

	@Override
	synchronized public void execDbData() {

		try {
			List<String> list = new ArrayList<String>();

			InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(DATA_FILE_PATH);

			FileUtils.readToList(list, is);

			for (String sql : list) {
				logger.info("读取到的sql  " + sql.toString());
				if (sql != null && !sql.isEmpty() && !sql.startsWith("--")) {
					execSQL(sql);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execSQL(String sql) {

		logger.info("执行sql  " + sql.toString());

		baseDao.executeSaveSql(sql);
	}

}
