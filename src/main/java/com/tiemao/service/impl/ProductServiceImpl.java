package com.tiemao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.dao.ProductMapper;
import com.tiemao.pojo.Product;
import com.tiemao.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Override
	public ServerResponse deleteByPrimaryKey(Integer productId) {
		// TODO Auto-generated method stub
		int result = productMapper.deleteByPrimaryKey(productId);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}

	@Override
	public ServerResponse insertSelective(Product record) {
		// TODO Auto-generated method stub
		int result = productMapper.insertSelective(record);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}

	@Override
	public ServerResponse updateByPrimaryKeySelective(Product record) {
		// TODO Auto-generated method stub
		int result = productMapper.updateByPrimaryKeySelective(record);
		if(result>0){
			return ServerResponse.createBySuccess();
		}
		return ServerResponse.createByError();
	}

	@Override
	public PageBean<Product> getProductInfo(int pageNum, int pageSize, String searchKey, String searchValue) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);//开启分页
		PageBean<Product> pg = new PageBean<>();
		Map<String, String> map = new HashMap<>();
		map.put("searchKey", searchKey);
		map.put("searchValue", searchValue);
		pg.setData(productMapper.getProductInfo(map));
		pg.setTotalRecord((int)startPage.getTotal());
		return pg;
	}

	@Override
	public Product selectByPrimaryKey(Integer productId) {
		// TODO Auto-generated method stub
		return productMapper.selectByPrimaryKey(productId);
	}

}
