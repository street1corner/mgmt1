package com.tiemao.dao;

import java.util.List;
import java.util.Map;

import com.tiemao.pojo.SysUser;

public interface SysUserDao {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    /**
     * @param map为 userId userAccount之一,没有传null
     * @return
     */
    SysUser selectByPrimaryKeys(Map<String, String> map);
    
    /**
     * @param userId
     * @return
     */
    SysUser selectByPrimaryKey(String userId);
    
	/**
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
    List<SysUser> selectByPage(Map<String, Integer> map);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}