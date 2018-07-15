package com.tiemao.service;

import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.EQPStandingbook;

public interface EQPStandingBookService {
	PageBean<EQPStandingbook> getEQPStandingbookInfo(int pageNum, int pageSize, String searchKey,
			String searchValue);
	ServerResponse deleteByPrimaryKey(Integer id);
	
	ServerResponse insertSelective(EQPStandingbook record);
	
	ServerResponse updateByPrimaryKeySelective(EQPStandingbook record);
}
