package com.tiemao.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.ServerInfo;
import com.tiemao.service.ServerInfoService;

@Controller
public class ServerInfoController {

	@Autowired
	private ServerInfoService serverInfoService;
	
	@RequestMapping(value = "/serverInfo", method = RequestMethod.GET)
	@ResponseBody
	public PageBean<ServerInfo> server(@RequestParam(value="page",defaultValue="1")Integer page, @RequestParam(value="limit",defaultValue="10")Integer limit, String searchKey, String searchValue){
		return serverInfoService.getServerInfo(page, limit, searchKey, searchValue);
	}
	
	@RequestMapping(value = "/deleteServerInfo", method = RequestMethod.DELETE)
	@ResponseBody
	public ServerResponse deleteServerInfo(Integer serverId){
		return serverInfoService.deleteByPrimaryKey(serverId);

	}
	
	@RequestMapping("/insertServerInfo")
	@ResponseBody
	public ServerResponse insertServerInfo(String serverName,String ipAddress,String subnetMask,
			String gateway,String serverAccount,String serverPassword,Integer port,String comments){
		ServerInfo serverInfo = new ServerInfo();
		serverInfo.setServerName(serverName);
		serverInfo.setIpAddress(ipAddress);
		serverInfo.setSubnetMask(subnetMask);
		serverInfo.setGateway(gateway);
		serverInfo.setServerAccount(serverAccount);
		serverInfo.setServerPassword(serverPassword);
		serverInfo.setPort(port);
		serverInfo.setComments(comments);
		serverInfo.setCreatTime(new Date());
		serverInfo.setUpdateTime(new Date());
		
		return serverInfoService.insertSelective(serverInfo);
	}
	
	@RequestMapping("/updateServerInfo")
	@ResponseBody
	public ServerResponse updateServerInfo(Integer serverId,String serverName,String ipAddress,String subnetMask,
			String gateway,String serverAccount,String serverPassword,Integer port,String comments){
		ServerInfo serverInfo = new ServerInfo();
		serverInfo.setServerId(serverId);
		serverInfo.setServerName(serverName);
		serverInfo.setIpAddress(ipAddress);
		serverInfo.setSubnetMask(subnetMask);
		serverInfo.setGateway(gateway);
		serverInfo.setServerAccount(serverAccount);
		serverInfo.setServerPassword(serverPassword);
		serverInfo.setPort(port);
		serverInfo.setComments(comments);
		serverInfo.setUpdateTime(new Date());
		
		return serverInfoService.updateServerInfo(serverInfo);
	}
}