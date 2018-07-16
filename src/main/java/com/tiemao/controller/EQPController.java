package com.tiemao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiemao.common.Const;
import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.EQPMaintainRecord;
import com.tiemao.pojo.EQPStandingbook;
import com.tiemao.service.EQPMaintainRecordService;
import com.tiemao.service.EQPStandingBookService;

/**
 * 设备台帐与设备维护
 * @author hlw
 *	
 */
@Controller
@RequestMapping("/eqp")
public class EQPController {
	@Autowired
	private EQPStandingBookService eqpStandingBookService;
	@Autowired
	private EQPMaintainRecordService eqpMaintainRecordService;

	//设备台帐接口
	@RequestMapping(value = "/eqpStandingbookInfo", method = RequestMethod.GET)
	@ResponseBody
	public PageBean<EQPStandingbook> eqpStandingbookInfo(Integer page, Integer limit, String searchKey, String searchValue){
		
		if(page == null) {
			page = 0;
			limit = 0;
		}
		if(searchKey !=null && !"".equals(searchKey)){
			page = 0;
		}
		return eqpStandingBookService.getEQPStandingbookInfo(page, limit, searchKey, searchValue);
	}
	
	@RequestMapping(value = "/deleteEQPStandingbook", method = RequestMethod.DELETE)
	@ResponseBody
	public ServerResponse deleteEQPStandingbook(Integer id){
		return eqpStandingBookService.deleteByPrimaryKey(id);
	}
	
	
	@RequestMapping(value = "/cuEQPStandingbook", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse cuEQPStandingbook(EQPStandingbook eqp){
		if(eqp.getId() == null){
			return eqpStandingBookService.insertSelective(eqp);
		} else {
			return eqpStandingBookService.updateByPrimaryKeySelective(eqp);
		}
	}
	
	//设备维护接口
	@RequestMapping(value = "/eqpMaintainRecordInfo", method = RequestMethod.GET)
	@ResponseBody
	public PageBean<EQPMaintainRecord> eqpMaintainRecordInfo(Integer page, Integer limit, String searchKey, String searchValue){
		if(page == null) {
			page = 0;
			limit = 0;
		}
		if(searchKey !=null && !"".equals(searchKey)){
			page = 0;
		}
		return eqpMaintainRecordService.getEQPMaintainRecordInfo(page, limit, searchKey, searchValue);
	}
	
	@RequestMapping(value = "/deleteEQPMaintainRecord", method = RequestMethod.DELETE)
	@ResponseBody
	public ServerResponse deleteEQPMaintainRecord(Integer id){
		return eqpMaintainRecordService.deleteByPrimaryKey(id);
	}
	
	@RequestMapping(value = "/cuEQPMaintainRecord", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse cuEQPMaintainRecord(HttpSession session, EQPMaintainRecord eqp){
		eqp.setLastmodifyPerson((String)session.getAttribute(Const.USER_NICKNAME));
		if(eqp.getId() == null){
			return eqpMaintainRecordService.insertSelective(eqp);
		} else {
			return eqpMaintainRecordService.updateByPrimaryKeySelective(eqp);
		}
	}
}
