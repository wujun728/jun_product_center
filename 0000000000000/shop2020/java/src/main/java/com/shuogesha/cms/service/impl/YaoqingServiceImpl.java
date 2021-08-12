package com.shuogesha.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shuogesha.cms.dao.YaoqingDao;
import com.shuogesha.cms.entity.Yaoqing;
import com.shuogesha.cms.service.CodeService;
import com.shuogesha.cms.service.YaoqingService;
import com.shuogesha.platform.service.AccountService;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;


@Service
public class YaoqingServiceImpl implements YaoqingService{
	
	@Autowired
	private YaoqingDao dao; 
 	@Autowired
	public AccountService accountService; 
	@Autowired
	public CodeService codeService;
	@Override
	public Yaoqing findById(Long id) {
		Yaoqing bean= dao.findById(id);
		if(bean==null) {
			 bean= new Yaoqing();
			 bean.setCode(getRandomCode());
			 bean.setId(id);
			 bean.setName("");//邀请码 
 			 save(bean);//保存用户信息
		}
		return bean;
	}
	
	@Override
	public Pagination getPage( String name,Integer uid,int pageNo, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		if(StringUtils.isNotBlank(name)){
			map.put("name", new StringBuilder("%").append(name).append("%").toString());
		}
		if(uid!=null&&uid>0){
			map.put("sid0", uid);
		}
		long totalCount = dao.count(map);
		Pagination<Yaoqing> page = new Pagination<Yaoqing>(pageNo, pageSize, totalCount);
 		map.put("pageSize", pageSize);
		map.put("offset", Integer.valueOf(pageSize)*((Integer.valueOf(pageNo)-1)));
 		List<Yaoqing> datas = dao.queryList(map);
		page.setDatas(datas);
		return page;  
	}

	@Override
	public void save(Yaoqing bean) {
		 bean.setDateline(RequestUtils.getNow());
		 dao.saveEntity(bean);
	}

	@Override
	public void update(Yaoqing bean) { 
		 dao.updateById(bean);
	}

	@Override
	public void removeById(Long id) {
		dao.removeById(id);
	}

	@Override
	public void removeByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			removeById(ids[i]);
		}
	}

	@Override
	public Yaoqing init(Long id, String refer) {
		 Yaoqing bean =dao.findById(id);
		 if(bean==null){
			 bean= new Yaoqing();
			 bean.setCode(getRandomCode());
			 bean.setId(id);
			 bean.setName(refer);//邀请码
 			 if(StringUtils.isNotBlank(refer)){
				 Yaoqing entity= dao.findByCode(refer);
				 if(entity!=null){//邀请会员的id存在 
					 bean.setSid0(entity.getId());
				 }
			 }
 			 save(bean);//保存用户信息
		 }else if(StringUtils.isBlank(bean.getName())){
			 if(StringUtils.isNotBlank(refer)){
				 bean.setName(refer);//邀请码
				 Yaoqing entity= dao.findByCode(refer);
				 if(entity!=null){//邀请会员的id存在 
					 bean.setSid0(entity.getId());
				 }
				 update(bean);
			 }
		 }
		return bean;
	}
	
	public String getRandomCode() {
		//生成8位用户编号 
 		String base = "abcdefghijklmnopqrstuvwxyz";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();  
	    while (true) {
	    	sb = new StringBuffer();
	    	for (int i = 0; i < 3; i++) {     
	            int number = random.nextInt(base.length());    
	            sb.append(String.valueOf(base.charAt(number)).toUpperCase());     
	        } 
	    	int newNum = (int)((Math.random()*9+1)*100);
	    	 sb.append(String.valueOf(newNum));
	    	if(dao.countCode(sb.toString())<=0){
	    		break;
	    	}
		}
	    return sb.toString();     
	}

	@Override
	public Yaoqing findByCode(String code) { 
		return dao.findByCode(code);
	}
	/**
	 * 获取徒弟的数量
	 * @param uid
	 * @return
	 */
	private long countChild(Integer uid) {
		Map<String, Object> map = new HashMap<String, Object>();  
		if(uid!=null&&uid>0){
			map.put("sid0", uid);
		}
		long totalCount = dao.countChild(map);
		return totalCount;
	}
	
//	/**
//	 * 暂时废弃不用
//	 */
//	@Override
//	public void upOne(Yaoqing bean, Double yaoqing) {
//		if(bean!=null&&StringUtils.isNotBlank(bean.getName())&&(bean.getSid0()==null||bean.getSid0()<=0)){
//			Code code=codeService.findByName(bean.getName());//平台邀请码
//			if(code!=null&&code.getYaoqing()!=null&&code.getYaoqing()>0){
//				yaoqing=code.getYaoqing();//设置平台邀请码得金额
//			}
//		} 
// 		Random random = new Random();
// 		if(yaoqing!=null&&yaoqing>0){
// 			Integer all=(int) (yaoqing*100);  
//  			Double price=Double.valueOf((random.nextInt(all)+1)/100);
// 			if(price>=0){
// 				if(price>=5.0){
// 					price=5.0;
// 				}
// 				//奖励本人金额
// 				bean.setJiangli(price);
// 				bean.setStatus("1");//设置已经奖励过啦
// 				bean.setMoney(Arith.add(bean.getMoney(),Double.valueOf(price)));
// 				update(bean);
// 				//奖励纪录
// 				accountService.upAccount(bean.getId(),price,true);//不可提现
// 				//纪录流水
// 				moneyService.add(Money.ZHUCE,Money.OK,Money.ADD,price,"",RequestUtils.getNow(),"",bean.getId());
// 				if(bean.getSid0()!=null&&bean.getSid0()>0){
// 					//奖励师傅的金额
// 					Yaoqing entity=findById(bean.getSid0());
// 					if(entity!=null){
// 						entity.setMoney(Arith.add(entity.getMoney(),Double.valueOf(price)));
// 						update(entity);
// 						//奖励纪录
// 						accountService.upAccount(entity.getId(),price,true);//不可提现
// 						//纪录流水
// 						moneyService.add(Money.ZHUCE,Money.OK,Money.ADD,price,"",RequestUtils.getNow(),"",entity.getId()); 
// 					}  
// 				 }
// 				
// 			}
// 		} 
// 		 
//	} 
//
//	@Override
//	public void upTwo(Integer payeer_id, Double yaoqing_fee, Double amount) {
//		Yaoqing entity=findById(payeer_id);
//		//奖励师傅的金额 
//		if(entity!=null&&entity.getSid0()!=null&&entity.getSid0()>0){
//			entity=findById(entity.getSid0());  
//			if(entity!=null&&StringUtils.isNotBlank(entity.getName())&&(entity.getSid0()==null||entity.getSid0()<=0)){
//				Code code=codeService.findByName(entity.getName());//平台邀请码
//				if(code!=null&&code.getYaoqing()!=null&&code.getYaoqing_fee()>0){
//					yaoqing_fee=code.getYaoqing_fee();//设置平台邀请码得金额
//				}
//			}  
//			Double end=Arith.mul(amount, yaoqing_fee);
//			BigDecimal db = new BigDecimal(end); 
//			String all = db.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
//			Double price=Double.valueOf(all);		
//			if(price>0.1){ //大于0.1
//				entity.setMoney(Arith.add(entity.getMoney(),Double.valueOf(price)));
//				update(entity);
//				//奖励纪录
//				accountService.upAccount(entity.getId(),price,false);
//				//纪录流水
//				moneyService.add(Money.FENCHENG,Money.OK,Money.ADD,price,"徒弟消费or充值贡献",RequestUtils.getNow(),"",entity.getId());
//			}
//		}
//	}
//
//	@Override
//	public void upThree(Integer payeer_id, Double shouyi_fee, Double amount) {
//		Yaoqing entity=findById(payeer_id);
//		//奖励师傅的金额 
//		if(entity!=null&&entity.getSid0()!=null&&entity.getSid0()>0){
//			entity=findById(entity.getSid0());  
//			if(entity!=null&&StringUtils.isNotBlank(entity.getName())&&(entity.getSid0()==null||entity.getSid0()<=0)){
//				Code code=codeService.findByName(entity.getName());//平台邀请码
//				if(code!=null&&code.getShouyi_fee()!=null&&code.getShouyi_fee()>0){
//					shouyi_fee=code.getShouyi_fee();//设置平台邀请码得金额
//				}
//			}  
//			Double end=Arith.mul(amount, shouyi_fee);
//			BigDecimal db = new BigDecimal(end); 
//			String all = db.setScale(2, BigDecimal.ROUND_HALF_UP).toString(); 
//			Double price=Double.valueOf(all);		
//			if(price>0.1){ //大于0.1
//				entity.setMoney(Arith.add(entity.getMoney(),Double.valueOf(price)));
//				update(entity);
//				//奖励纪录
//				accountService.upAccount(entity.getId(),price,false);
//				//纪录流水
//				moneyService.add(Money.FENCHENG,Money.OK,Money.ADD,price,"徒弟订单收益提成",RequestUtils.getNow(),"",entity.getId());
//			} 
//		}
//		
//	} 

}
