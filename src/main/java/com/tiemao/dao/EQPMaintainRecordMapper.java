package com.tiemao.dao;

import java.util.List;
import java.util.Map;

import com.tiemao.pojo.EQPMaintainRecord;

public interface EQPMaintainRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EQPMaintainRecord record);

    int insertSelective(EQPMaintainRecord record);

    EQPMaintainRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EQPMaintainRecord record);

    int updateByPrimaryKey(EQPMaintainRecord record);
    
    /**
     * @param map searchKey,searchValue
     * @return
     */
    List<EQPMaintainRecord> getEQPMaintainRecordInfo(Map<String, String> map);
}