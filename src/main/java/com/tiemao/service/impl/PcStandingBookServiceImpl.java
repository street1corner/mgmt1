package com.tiemao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.dao.PcStandingbookMapper;
import com.tiemao.pojo.PcStandingbook;
import com.tiemao.service.PcStandingBookService;
@Service
public class PcStandingBookServiceImpl implements PcStandingBookService {

	@Autowired
	PcStandingbookMapper pcStandingbookMapper;
	
	@Override
	public PageBean<PcStandingbook> getPcStandingBookInfo(int pageNum, int pageSize, String searchKey, String searchValue) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);//开启分页
		PageBean<PcStandingbook> pg = new PageBean<>();
		Map<String, String> map = new HashMap<>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		pg.setData(pcStandingbookMapper.getPcStandingbookInfo(map));
		pg.setTotalRecord((int)startPage.getTotal());
		return pg;
	}

	@Override
	public ServerResponse deleteByPrimaryKey(Integer id) {
		int result = pcStandingbookMapper.deleteByPrimaryKey(id);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}

	@Override
	public ServerResponse insertSelective(PcStandingbook record) {
		// TODO Auto-generated method stub
		int result = pcStandingbookMapper.insertSelective(record);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}

	@Override
	public ServerResponse updatePcStandingBookInfo(PcStandingbook record) {
		// TODO Auto-generated method stub
		int result = pcStandingbookMapper.updateByPrimaryKeySelective(record);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}

}
