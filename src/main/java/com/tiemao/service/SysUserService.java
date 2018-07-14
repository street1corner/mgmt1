package com.tiemao.service;

import com.tiemao.pojo.SysUser;

public interface SysUserService {
//	public User getUserInfoById(String id);
	/**
	 * 返回表格分页信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
//	public PageBean<SysUser> getSysUserInfo(int pageNum,int pageSize);
	/**
	 * 根据帐号或id查找用户,2者选一,空为null
	 * @param userId
	 * @param userAccount
	 * @return
	 */
	public SysUser selectByPrimaryKeys(String userId,String userAccount);
	/**
	 * 删除用户
	 */
	public boolean deleteUser(String userId);
	/**
	 * 添加用户
	 */
	public boolean addUser(SysUser user);
	/**
	 * 有选择的更新用户信息
	 * @param record
	 * @return
	 */
	boolean updateByPrimaryKeySelective(SysUser record);
	
}
