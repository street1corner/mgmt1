package com.tiemao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiemao.common.PageBean;
import com.tiemao.common.ResponseCode;
import com.tiemao.common.ServerResponse;
import com.tiemao.dao.EQPMaintainRecordMapper;
import com.tiemao.pojo.EQPMaintainRecord;
import com.tiemao.service.EQPMaintainRecordService;
@Service
public class EQPMaintainRecordServiceImpl implements EQPMaintainRecordService {

	@Autowired
	private EQPMaintainRecordMapper eqpMaintainRecordMapper;
	@Override
	public ServerResponse deleteByPrimaryKey(Integer id) {
		if(id == null){
			return ServerResponse.createByErrorMsg(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
		}
		int count = eqpMaintainRecordMapper.deleteByPrimaryKey(id);
		if(count > 0){
			return ServerResponse.createBySuccess();
		}else {
			return ServerResponse.createByError();
		}
	}

	@Override
	public ServerResponse insertSelective(EQPMaintainRecord record) {
		int count = eqpMaintainRecordMapper.insertSelective(record);
		if(count > 0){
			return ServerResponse.createBySuccess();
		}else {
			return ServerResponse.createByError();
		}
	}

	@Override
	public ServerResponse updateByPrimaryKeySelective(EQPMaintainRecord record) {
		int count = eqpMaintainRecordMapper.updateByPrimaryKeySelective(record);
		if(count > 0){
			return ServerResponse.createBySuccess();
		}else {
			return ServerResponse.createByError();
		}
	}

	@Override
	public PageBean<EQPMaintainRecord> getEQPMaintainRecordInfo(int pageNum, int pageSize, String searchKey,
			String searchValue) {
		Page<EQPMaintainRecord> page = PageHelper.startPage(pageNum, pageSize);//开启分页
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		List<EQPMaintainRecord> list = eqpMaintainRecordMapper.getEQPMaintainRecordInfo(map);
		PageBean<EQPMaintainRecord> pageBean = new PageBean<>((int)page.getTotal(), list);
		return pageBean;
	}

}
