package com.tiemao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.dao.EQPStandingbookMapper;
import com.tiemao.pojo.EQPStandingbook;
import com.tiemao.service.EQPStandingBookService;
@Service
public class EQPStandingBookServiceImpl implements EQPStandingBookService {
	@Autowired
	private EQPStandingbookMapper eqpStandingbookMapper;
	@Override
	public PageBean<EQPStandingbook> getEQPStandingbookInfo(int pageNum, int pageSize, String searchKey,
			String searchValue){
		Page<EQPStandingbook> page = PageHelper.startPage(pageNum, pageSize);//开启分页
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		List<EQPStandingbook> list = eqpStandingbookMapper.getEQPStandingbookInfo(map);
		PageBean<EQPStandingbook> pageBean = new PageBean<>((int)page.getTotal(), list);
		return pageBean;
	}
	@Override
	public ServerResponse deleteByPrimaryKey(Integer id) {
		int result = eqpStandingbookMapper.deleteByPrimaryKey(id);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}
	@Override
	public ServerResponse insertSelective(EQPStandingbook record) {
		int result = eqpStandingbookMapper.insertSelective(record);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}
	@Override
	public ServerResponse updateByPrimaryKeySelective(EQPStandingbook record) {
		int result = eqpStandingbookMapper.updateByPrimaryKeySelective(record);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}
}
