package com.tiemao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiemao.dao.SysUserDao;
import com.tiemao.pojo.SysUser;
import com.tiemao.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;

//	@Override
//	public User getUserInfoById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public PageBean<SysUser> getSysUserInfo(int pageNum, int pageSize) {
//		
//		return null;
//	}

	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SysUser selectByPrimaryKeys(String userId, String userAccount) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userAccount", userAccount);
		SysUser user = sysUserDao.selectByPrimaryKeys(map);
		return user;
	}

	@Override
	public boolean updateByPrimaryKeySelective(SysUser record) {
		return sysUserDao.updateByPrimaryKeySelective(record)>0;
	}

	@Override
	public boolean addUser(SysUser user) {
		// TODO Auto-generated method stub
		return false;
	}

}
