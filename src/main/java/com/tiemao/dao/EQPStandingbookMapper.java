package com.tiemao.dao;

import java.util.List;
import java.util.Map;

import com.tiemao.pojo.EQPStandingbook;

public interface EQPStandingbookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EQPStandingbook record);

    int insertSelective(EQPStandingbook record);

    EQPStandingbook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EQPStandingbook record);

    int updateByPrimaryKey(EQPStandingbook record);
    
    /**
     * @param map:searchKey,searchValue
     * @return
     */
    List<EQPStandingbook> getEQPStandingbookInfo(Map<String, String> map);
    
}