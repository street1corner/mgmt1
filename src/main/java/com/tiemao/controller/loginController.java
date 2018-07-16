package com.tiemao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiemao.common.Const;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.SysUser;
import com.tiemao.service.SysUserService;
import com.tiemao.util.CookieUtil;
import com.tiemao.util.MD5Util;
import com.wf.captcha.utils.CaptchaUtil;

@Controller
@RequestMapping("/api")
public class loginController {
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/login")
	@ResponseBody
	public ServerResponse login(String account, String password, String vercode, String verkey, HttpServletRequest request, HttpServletResponse response){
		if(!CaptchaUtil.isVerified(verkey, vercode, request)){
			return ServerResponse.createByErrorMsg("验证码错误");
		}
		SysUser loginUser = sysUserService.selectByPrimaryKeys(null, account);
		if(loginUser == null){
			return ServerResponse.createByErrorMsg("用户不存在");
		}
		if(!loginUser.getUserPassword().equals(MD5Util.MD5EncodeUtf8(password))){
			return ServerResponse.createByErrorMsg("密码错误");
		}
		HttpSession session = request.getSession();
		session.setAttribute(Const.USER_NICKNAME, loginUser.getUserNickname());
		loginUser.setUserPassword(null);
		CookieUtil.writeLoginToken(response, session.getId());
		return ServerResponse.createBySuccess("登陆成功", loginUser);
	}
	
	@RequestMapping("loginOut")
	@ResponseBody
	public ServerResponse loginOut(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return ServerResponse.createBySuccessMsg("退出成功");
		
	}
}
