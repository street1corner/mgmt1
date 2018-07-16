package com.tiemao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.dao.ServerInfoMapper;
import com.tiemao.pojo.ServerInfo;
import com.tiemao.service.ServerInfoService;
@Service
public class ServerInfoServiceImpl implements ServerInfoService {
	@Autowired
	private ServerInfoMapper serverInfoMapper;

	@Override
	public PageBean<ServerInfo> getServerInfo(int pageNum, int pageSize, String searchKey, String searchValue) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);//开启分页
		PageBean<ServerInfo> pg = new PageBean<>();
		Map<String, String> map = new HashMap<>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		pg.setData(serverInfoMapper.getServerInfo(map));
		pg.setTotalRecord((int)startPage.getTotal());
		return pg;
	}

	@Override
	public ServerResponse deleteByPrimaryKey(Integer serverId) {
		// TODO Auto-generated method stub
		int result = serverInfoMapper.deleteByPrimaryKey(serverId);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}

	@Override
	public ServerResponse insertSelective(ServerInfo record) {
		// TODO Auto-generated method stub
		int result = serverInfoMapper.insertSelective(record);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}

	@Override
	public ServerResponse updateServerInfo(ServerInfo record) {
		// TODO Auto-generated method stub
		int result = serverInfoMapper.updateByPrimaryKeySelective(record);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}
	

}
