package com.tiemao.dao;

import java.util.List;
import java.util.Map;

import com.tiemao.pojo.PcStandingbook;

/**
 * 个人电脑台帐dao接口
 * @author hlw
 *@date 2018年4月16日 10:12:12
 */
public interface PcStandingbookMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PcStandingbook record);

    int updateByPrimaryKeySelective(PcStandingbook record);

    /**
     * 分页
     * @param map:searchKey,searchValue
     * @return
     */
    public List<PcStandingbook> getPcStandingbookInfo(Map<String, String> map);
    
    PcStandingbook selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(PcStandingbook record);
}