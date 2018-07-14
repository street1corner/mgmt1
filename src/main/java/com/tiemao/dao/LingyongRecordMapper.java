package com.tiemao.dao;

import java.util.List;
import java.util.Map;

import com.tiemao.pojo.LingyongRecord;

public interface LingyongRecordMapper {
    /**
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    int insert(LingyongRecord record);

    /**
     * @param record
     * @return
     */
    int insertSelective(LingyongRecord record);

    LingyongRecord selectByPrimaryKey(Integer id);
    
    /**
     * @param map:searchKey,searchValue
     * @return
     */
    List<LingyongRecord> getLingyongRecordInfo(Map<String, String> map);

    int updateByPrimaryKeySelective(LingyongRecord record);

    int updateByPrimaryKey(LingyongRecord record);
}