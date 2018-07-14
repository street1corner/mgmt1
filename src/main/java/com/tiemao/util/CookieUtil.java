package com.tiemao.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 * @date 2018年7月6日 15:06:15
 */
public class CookieUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	
	private final static String COOKIE_DOMAIN = "tiemao.com";
	private final static String COOKIE_NAME = "mgmt_login_token";

	public static String readLoginToken(HttpServletRequest request){
		Cookie[] cks = request.getCookies();
		if(cks != null){
			for(Cookie ck : cks){
				logger.info("read cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
				if(StringUtils.equals(ck.getName(), COOKIE_NAME)){
					logger.info("return cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
					return ck.getValue();
				}
			}
		}
		return null;
	}
	
	public static void writeLoginToken(HttpServletResponse response, String token){
		Cookie ck = new Cookie(COOKIE_NAME, token);
		ck.setDomain(COOKIE_DOMAIN);
		ck.setPath("/");//代表设置在根目录
		//单位秒
		//如果不设置maxage,cookie就不写入硬盘,而写在内寸
		ck.setMaxAge(60 * 60 * 24 * 365);//-1代表永久
		logger.info("write cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
		response.addCookie(ck);
	}
	
	public static void delLoginToken(HttpServletResponse response, HttpServletRequest request){
		Cookie[] cks = request.getCookies();
		if(cks != null){
			for(Cookie ck : cks){
				if(StringUtils.equals(ck.getName(), COOKIE_NAME)){
					ck.setDomain(COOKIE_DOMAIN);
					ck.setPath("/");
					ck.setMaxAge(0);//0代表删除此cookie
					logger.info("del cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
					response.addCookie(ck);
					return;//命中这个if则不再循环退出提方法
				}
			}
		}
	}
}
