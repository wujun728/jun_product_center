package org.myframework.support.idgenerator.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.myframework.support.idgenerator.dao.CurrentMaxDao;
import org.myframework.support.idgenerator.dao.SeriesDao;
import org.myframework.support.idgenerator.entities.CurrentMax;
import org.myframework.support.idgenerator.entities.Series;
import org.myframework.support.idgenerator.entities.CurrentMax.CurrentMaxPK;
import org.myframework.support.idgenerator.util.IDGeneratorException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;;

/**
 * <p>
 * Title: 序列号Dao类
 * </p>
 * <p>
 * Description: 航信税务软件-稽查部分
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: 合力金桥软件公司
 * </p>
 * 
 * @author Wujun
 * @version 1.0
 */

@Service("idUtil")
public class IDUtil {
	public IDUtil() {

	}

	/**
	 * 获取指定条件的当前最大号对象,如果不存在返回一个新的
	 * 
	 * @param series
	 *            Series
	 * @param maxID
	 *            String
	 * @throws IDGeneratorException
	 * @return CurrentMax
	 */
	public CurrentMax getCurrentMaxForced(String sequenceId, String maxID)
			throws IDGeneratorException {
		if (sequenceId == null)
			throw new IDGeneratorException("没有定义流水号", "407000000");
		// 创建主键类
		CurrentMaxPK pk = new CurrentMaxPK(maxID, sequenceId);
		// 获取当前最大值对象
		CurrentMax currentMax = getCurrentMax(pk);
		if (currentMax == null) {
			// 创建一个新的
			currentMax = new CurrentMax();
			currentMax.setPk(pk);
			// 设置缺省值为零
			currentMax.setValue(new java.math.BigDecimal("0"));
		}
		return currentMax;
	}

	/**
	 * 得到当前最大值对象
	 * 
	 * @param pk
	 *            CurrentMaxPK
	 * @return CurrentMax
	 */
	public CurrentMax getCurrentMax(CurrentMaxPK pk) {
		CurrentMax cm  = currentMaxDao.findOne(pk);
		return cm;
	}

	/**
	 * 创建一个新的当前最大值对象
	 * @param pk
	 *            CurrentMaxPK
	 * @return CurrentMax
	 */
	public CurrentMax genCurrentMax(CurrentMaxPK pk) {
		// 创建一个新的
		CurrentMax currentMax = new CurrentMax();
		currentMax.setPk(pk);
		// 设置缺省值为零
		currentMax.setValue(new java.math.BigDecimal("0"));
		return currentMax;
	}

	/**
	 * 创建主键
	 * 
	 * @param series
	 *            Series
	 * @param maxID
	 *            String
	 * @return CurrentMaxPK
	 */
	public CurrentMaxPK genCurrentMaxPK(String sequenceId, String maxID) {
		if (sequenceId == null)
			throw new IDGeneratorException("没有定义流水号", "407000000");
		// 创建主键类
		CurrentMaxPK pk = new CurrentMaxPK(sequenceId, maxID);
		return pk;
	}

	/**
	 * 保存当前最大号
	 * 
	 * @param cm
	 *            CurrentMax
	 */
	public void saveCurrentMax(CurrentMax cm) {
		currentMaxDao.save( cm);
	}

	/**
	 * 更新当前最大号
	 * 
	 * @param cm
	 *            CurrentMax
	 */
	public void updateCurrentMax(CurrentMax cm) {
		currentMaxDao.save( cm);
	}

	/**
	 * 创建一个新的当前最大号
	 * 
	 * @param cm
	 *            CurrentMax
	 */
	public void createCurrentMax(CurrentMax cm) {
		currentMaxDao.save( cm);
	}

	/**
	 * 获取流水号定义对象
	 * 
	 * @param id
	 *            String
	 * @return Series
	 */
	public Series getSeries(String id) {
		return seriesDao.findOne(id);
	}

	public CurrentMax getCurrentMaxByJDBC(CurrentMaxPK pk) {
		Map<?, ?> record = getJDBCDao().queryForMap(
				" select c.CURRENT_VALUE from TBL_CURRENT_MAX c with (updlock, rowlock) where c.SEQUENCE_ID='"
						+ pk.getSequenceId() + "' and c.MAX_ID='"
						+ pk.getMaxId() + "'");// ,new Object[]{pk.getSeries(),
												// pk.getMaxId()});
		if (record == null) {
			return null;
		}
		CurrentMax cm = new CurrentMax();
		cm.setValue(new BigDecimal((record.get("CURRENT_VALUE").toString())));
		cm.setPk(pk);
		return cm;
	}

	public void updateCurrentMaxByJDBC(CurrentMax cm) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update TBL_CURRENT_MAX set CURRENT_VALUE = '")
				.append(cm.getValue().intValue()).append("' ")
				.append(" where SEQUENCE_ID= '")
				.append(cm.getPk().getSequenceId()).append("' ")
				.append(" and MAX_ID= '").append(cm.getPk().getMaxId())
				.append("' ");
		getJDBCDao().execute(sb.toString());

	}

	public void createCurrentMaxByJDBC(CurrentMax cm) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				" insert into TBL_CURRENT_MAX(CURRENT_VALUE, SEQUENCE_ID,MAX_ID )values(")
				.append(cm.getValue().intValue()).append(", ").append(" '")
				.append(cm.getPk().getSequenceId()).append("' ").append(", ")
				.append(" '").append(cm.getPk().getMaxId()).append("')");
		getJDBCDao().execute(sb.toString());

	}

	/**
	 * 
	 * @return
	 */
	public JdbcTemplate getJDBCDao() {
		return this.jdbcTemplate;
	}

	@Resource(name = "jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@Resource(name = "currentMaxDao")
	CurrentMaxDao currentMaxDao;

	@Resource(name = "seriesDao")
	SeriesDao seriesDao;
}
