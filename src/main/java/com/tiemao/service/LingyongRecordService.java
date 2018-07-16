package com.tiemao.service;

import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.LingyongRecord;

public interface LingyongRecordService {
	ServerResponse deleteByPrimaryKey(Integer id, Integer productId, Integer amount);
	
	boolean insertSelective(LingyongRecord record);
	
	PageBean<LingyongRecord> getLingyongRecordInfo(int pageNum, int pageSize, String searchKey, String searchValue);
}
