package com.tiemao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.dao.LingyongRecordMapper;
import com.tiemao.dao.ProductMapper;
import com.tiemao.pojo.LingyongRecord;
import com.tiemao.pojo.Product;
import com.tiemao.service.LingyongRecordService;
@Service
public class LingyongRecordServiceImpl implements LingyongRecordService {
	@Autowired
	private LingyongRecordMapper lingyongRecordMapper;
	@Autowired
	private ProductMapper productMapper;
	@Override
	
	public ServerResponse deleteByPrimaryKey(Integer id, Integer productId, Integer amount) {
		int result = lingyongRecordMapper.deleteByPrimaryKey(id);
		if(result>0){
			Product product1 = productMapper.selectByPrimaryKey(productId);
			if(product1 != null){
				Product product = new Product();
				product.setProductId(productId);
				product.setRemainAmount(product1.getRemainAmount() + amount);
				if(productMapper.updateByPrimaryKeySelective(product)>0){
					return ServerResponse.createBySuccess();
				}else {
					return ServerResponse.createByErrorMsg("操作失败");
				}
			}else{
				return ServerResponse.createByErrorMsg("该产品已被删除,记录将被清空");
			}
		}else{
			return ServerResponse.createByError();
		}
	}

	@Override
	public boolean insertSelective(LingyongRecord record) {
		// TODO Auto-generated method stub
		return lingyongRecordMapper.insertSelective(record)>0;
	}

	@Override
	public PageBean<LingyongRecord> getLingyongRecordInfo(int pageNum, int pageSize, String searchKey,
			String searchValue) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);//开启分页
		PageBean<LingyongRecord> pg = new PageBean<>();
		Map<String, String> map = new HashMap<>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		pg.setData(lingyongRecordMapper.getLingyongRecordInfo(map));
		pg.setTotalRecord((int)startPage.getTotal());
		return pg;
	}

}
