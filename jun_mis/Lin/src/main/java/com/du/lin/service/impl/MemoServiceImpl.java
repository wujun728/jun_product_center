package com.du.lin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.du.lin.bean.Memo;
import com.du.lin.constant.Constant;
import com.du.lin.dao.MemoMapper;
import com.du.lin.service.MemoService;
import com.du.lin.utils.Userinfo;
@Service
public class MemoServiceImpl implements MemoService{
	@Autowired
	private MemoMapper mapper;
	
	@Override
	public String addMemo(String title, String text, String time) {
		Memo memo = new Memo();
		memo.setTitle(title);
		memo.setText(text);
		memo.setTime(time);
		memo.setUserid(Userinfo.getUserid());
		int result = mapper.insert(memo);
		if (result == 1) {
			return Constant.OPERATION_SUCCESS_CODE;
		}
		return Constant.ERROR_ADD_MEMO;
	}

	@Override
	public List<Memo> getUserMemoList() {
		return mapper.selectList(new EntityWrapper<Memo>().eq("userid", Userinfo.getUserid()));
	}

	@Override
	public String deleteMemo(int id) {
		int result = mapper.deleteById(id);
		if (result == 1) {
			return Constant.OPERATION_SUCCESS_CODE;
		}
		return Constant.ERROR_DELETE_MEMO;
	}

}
