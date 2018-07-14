package com.tiemao.dao;

import java.util.List;
import java.util.Map;

import com.tiemao.pojo.ServerInfo;

/**
 * 服务器信息dao接口
 * @author hlw
 * @date 2018年3月20日 15:23:11
 */
public interface ServerInfoMapper {
    /**
     * 根据服务器Id删除
     * @param serverName
     * @return
     */
    int deleteByPrimaryKey(Integer serverId);

    /**
     * 新增记录
     * @param record
     * @return
     */
    int insertSelective(ServerInfo record);

    ServerInfo selectByPrimaryKey(String serverName);

    /**
     * 更新信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ServerInfo record);

    int updateByPrimaryKey(ServerInfo record);
    
    /**
     * 分页
     * @param map:searchKey,searchValue
     * @return
     */
    public List<ServerInfo> getServerInfo(Map<String, String> map);
}