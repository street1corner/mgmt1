package com.tiemao.dao;

import java.util.List;
import java.util.Map;

import com.tiemao.pojo.Product;

public interface ProductMapper {
    /**
     * @param productId
     * @return
     */
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    /**
     * @param record
     * @return
     */
    int insertSelective(Product record);

    /**
     * @param productId
     * @return
     */
    Product selectByPrimaryKey(Integer productId);
    /**
     * 分页
     * @param map:searchKey,searchValue
     * @return
     */
    List<Product> getProductInfo(Map<String, String> map);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}