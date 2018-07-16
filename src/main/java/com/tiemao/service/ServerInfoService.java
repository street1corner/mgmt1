package com.tiemao.service;

import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.ServerInfo;

/**
 * 服务器服务类接口
 * @author hlw
 * @date 2018年3月20日 15:45:56
 */
public interface ServerInfoService {
	/**
	 * @param pageNum
	 * @param pageSize
	 * @param searchKey
	 * @param searchValue
	 * @return
	 */
	public PageBean<ServerInfo> getServerInfo(int pageNum, int pageSize, String searchKey, String searchValue);
	/**
	 * 根据服务器id删除
	 * @param userId
	 * @return
	 */
	public ServerResponse deleteByPrimaryKey(Integer serverId);
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public ServerResponse insertSelective(ServerInfo record);
	/**
	 * 更新
	 * @param record
	 * @return
	 */
	public ServerResponse updateServerInfo(ServerInfo record);
}
