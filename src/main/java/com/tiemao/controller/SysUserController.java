package com.tiemao.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.SysUser;
import com.tiemao.service.SysUserService;
import com.tiemao.util.MD5Util;

/**
 * 用户
 * @author hlw
 * @date 2018年4月26日 20:29:37
 */
@Controller
public class SysUserController {
	
	private static Logger logger = LoggerFactory.getLogger(SysUserController.class);
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/updatePass")
	@ResponseBody
	public ServerResponse updatePass(String userAccount, String nowpass, String pass, String repass){
		if(!pass.equals(repass)){
			logger.info("{'msg':'新密码不一致,请确认后再修改','code':500}");
			return ServerResponse.createByErrorMsg("新密码不一致,请确认后再修改");
		}
		SysUser loginUser = sysUserService.selectByPrimaryKeys(null, userAccount);
		if(!loginUser.getUserPassword().equals(MD5Util.MD5EncodeUtf8(nowpass))){
			return ServerResponse.createByErrorMsg("当前密码不正确,请确认后再修改");
		}
		SysUser tmpUser = new SysUser();
		tmpUser.setUserPassword(MD5Util.MD5EncodeUtf8(pass));
		tmpUser.setUpdateTime(new Date());
		tmpUser.setUserAccount(loginUser.getUserAccount());
		if(!sysUserService.updateByPrimaryKeySelective(tmpUser)){
			return ServerResponse.createByErrorMsg("后台服务异常");
		}
		return ServerResponse.createBySuccessMsg("修改成功");
	}
	
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public ServerResponse updateUserInfo(String userAccount, String userNickname
			, String sex, String mobilePhone){
		SysUser tmpUser = new SysUser();
		tmpUser.setUserNickname(userNickname);
		tmpUser.setSex(sex);
		tmpUser.setMobilePhone(mobilePhone);
		tmpUser.setUpdateTime(new Date());
		tmpUser.setUserAccount(userAccount);
		if(!sysUserService.updateByPrimaryKeySelective(tmpUser)){
			return ServerResponse.createByErrorMsg("后台服务异常");
		}
		return ServerResponse.createBySuccessMsg("修改成功");
	}
	
}
