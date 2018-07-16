package com.tiemao.service;

import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.Product;

public interface ProductService {
	ServerResponse deleteByPrimaryKey(Integer productId);
	
	ServerResponse insertSelective(Product record);
	
	ServerResponse updateByPrimaryKeySelective(Product record);
	
	Product selectByPrimaryKey(Integer productId);
	
	PageBean<Product> getProductInfo(int pageNum, int pageSize, String searchKey, String searchValue);
}
