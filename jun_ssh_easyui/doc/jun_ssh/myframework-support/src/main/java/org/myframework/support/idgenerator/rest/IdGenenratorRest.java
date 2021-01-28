package org.myframework.support.idgenerator.rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.myframework.support.idgenerator.dao.SeriesDao;
import org.myframework.support.idgenerator.entities.Series;
import org.myframework.support.idgenerator.util.IDGenerator;
import org.myframework.web.commons.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * <ol> IDGenerator 集成测试类
 * <li>{@link  }</li>
 * 
 * </ol>
 * @see
 * @author Wujun
 * @since 1.0
 * @2015年10月20日
 *
 */
@RestController
@RequestMapping("/idgenenrator")
public class IdGenenratorRest {

	@Resource(name="seriesDao")
	SeriesDao seriesDao ;
	 
	/**
	 * select * from TBL_SYS_SEQUENCE ;
	 * select * from TBL_SYS_CURRENT_MAX ;
	 *  /rest/idgenenrator/getNext?seriesID=processConfig 
	 * @param seriesID 对应 SEQUENCE_ID
	 * @return
	 */
	@RequestMapping(value = "/getNext")
	public String getNext(@RequestParam String  seriesID){
		return IDGenerator.getNext(seriesID); 
	}
	
	@RequestMapping(value = "/getNextSeries")
	public Series getNextSeries(@RequestParam String  seriesID){
		return seriesDao.findOne(seriesID); 
	}
	
	
	@RequestMapping(value = "/getNext1")
	public String abc(@RequestParam String  seriesID){
		return "abc"; 
	}
	
}
