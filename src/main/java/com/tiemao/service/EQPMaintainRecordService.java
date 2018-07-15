package com.tiemao.service;

import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.EQPMaintainRecord;

public interface EQPMaintainRecordService {
	
	ServerResponse deleteByPrimaryKey(Integer id);
	
	ServerResponse insertSelective(EQPMaintainRecord record);
	
	ServerResponse updateByPrimaryKeySelective(EQPMaintainRecord record); 
	
	PageBean<EQPMaintainRecord> getEQPMaintainRecordInfo(int pageNum, int pageSize, String searchKey,
			String searchValue);
}
