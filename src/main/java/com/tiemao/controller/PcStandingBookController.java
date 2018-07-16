package com.tiemao.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiemao.common.Const;
import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.PcStandingbook;
import com.tiemao.service.PcStandingBookService;

@Controller
public class PcStandingBookController {
	@Autowired
	private PcStandingBookService pcStandingBookService;
	
	@RequestMapping("/computerInfo")
	@ResponseBody
	public PageBean<PcStandingbook> server(Integer page, Integer limit, String searchKey, String searchValue){
		if(page == null) {
			page = 0;
			limit = 0;
		}
		if(searchKey !=null && !"".equals(searchKey)){
			page = 0;
		}
		return pcStandingBookService.getPcStandingBookInfo(page, limit, searchKey, searchValue);
	}
	
	@RequestMapping("/deleteComputerInfo")
	@ResponseBody
	public ServerResponse deleteComputerInfo(Integer id){
		return pcStandingBookService.deleteByPrimaryKey(id);
	}
	
	@RequestMapping("/insertComputerInfo")
	@ResponseBody
	public ServerResponse insertComputerInfo(String computerId,String brand,String type,
			String configure,String installedSystem,String installedDate,String guaranteeTime,String mirroredVersion,
			String macAddress,String username,String installer, String installedSoft, HttpServletRequest request) throws ParseException{
		PcStandingbook pcStandingBook = new PcStandingbook();
		pcStandingBook.setComputerId(computerId);
		pcStandingBook.setBrand(brand);
		pcStandingBook.setType(type);
		pcStandingBook.setConfigure(configure);
		pcStandingBook.setInstalledSystem(installedSystem);
		pcStandingBook.setInstalledDate(new SimpleDateFormat("yyyy-MM-dd").parse(installedDate));
		pcStandingBook.setGuaranteeTime(guaranteeTime);
		pcStandingBook.setMirroredVersion(mirroredVersion);
		pcStandingBook.setMacAddress(macAddress);
		pcStandingBook.setUsername(username);
		pcStandingBook.setInstaller(installer);
		pcStandingBook.setCreateTime(new Date());
		pcStandingBook.setLastmodifyPerson((String)request.getSession().getAttribute("userNickname"));
		pcStandingBook.setInstalledSoft(installedSoft);
		return pcStandingBookService.insertSelective(pcStandingBook);
	}
	
	@RequestMapping("/updateComputerInfo")
	@ResponseBody
	public ServerResponse updateComputerInfo(String id, String computerId,String brand,String type,
			String configure,String installedSystem,String installedDate,String guaranteeTime,String mirroredVersion,
			String macAddress,String username,String installer, String installedSoft, HttpServletRequest request) throws ParseException{
		PcStandingbook pcStandingBook = new PcStandingbook();
		pcStandingBook.setId(Integer.valueOf(id));
		pcStandingBook.setComputerId(computerId);
		pcStandingBook.setBrand(brand);
		pcStandingBook.setType(type);
		pcStandingBook.setConfigure(configure);
		pcStandingBook.setInstalledSystem(installedSystem);
		pcStandingBook.setInstalledDate(new SimpleDateFormat("yyyy-MM-dd").parse(installedDate));
		pcStandingBook.setGuaranteeTime(guaranteeTime);
		pcStandingBook.setMirroredVersion(mirroredVersion);
		pcStandingBook.setMacAddress(macAddress);
		pcStandingBook.setUsername(username);
		pcStandingBook.setInstaller(installer);
		pcStandingBook.setUpdateTime(new Date());
		pcStandingBook.setLastmodifyPerson((String)request.getSession().getAttribute(Const.USER_NICKNAME));
		pcStandingBook.setInstalledSoft(installedSoft);
		return pcStandingBookService.updatePcStandingBookInfo(pcStandingBook);
	}

}
