package com.du.lin.service;

import java.util.List;

import com.du.lin.bean.Memo;

public interface MemoService {
	String addMemo(String title , String text , String time);
	List<Memo> getUserMemoList();
	String deleteMemo(int id);
}
