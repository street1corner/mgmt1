package com.tiemao.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.LingyongRecord;
import com.tiemao.pojo.Product;
import com.tiemao.service.LingyongRecordService;
import com.tiemao.service.ProductService;

@Controller
public class LingyongRecordController {
	@Autowired
	private ProductService productService;
	@Autowired
	private LingyongRecordService  lingyongRecordService;
	
	@RequestMapping("/lingyongRecordInfo")
	@ResponseBody
	public PageBean<LingyongRecord> lingyongRecordInfo(@RequestParam(value = "page")Integer page, @RequestParam(value = "limit")Integer limit, String searchKey, String searchValue){
		return lingyongRecordService.getLingyongRecordInfo(page, limit, searchKey, searchValue);
	}
	
	@RequestMapping(value = "/deleteLingyong", method = RequestMethod.DELETE)
	@ResponseBody
	public ServerResponse deleteLingyong(Integer id, Integer productId, Integer amount){
		return lingyongRecordService.deleteByPrimaryKey(id,productId,amount);
	}
	
	@RequestMapping("/insertLingyong")
	@ResponseBody
	public ServerResponse insertLingyong(Integer productId, String user, Integer amount, String remark,
			String lingyongTime, String department, Integer oldtonew, String usePurpose, HttpServletRequest request) throws ParseException{
		Product product1 = productService.selectByPrimaryKey(productId);
		if(product1.getRemainAmount()<amount){
			return ServerResponse.createByErrorMsg("库存不足" + amount);
		}
		LingyongRecord lingyongRecord = new LingyongRecord();
		lingyongRecord.setProductId(productId);
		lingyongRecord.setUser(user);
		lingyongRecord.setAmount(amount);
		lingyongRecord.setRemark(remark);
		lingyongRecord.setLingyongTime(new SimpleDateFormat("yyyy-MM-dd").parse(lingyongTime));
		lingyongRecord.setDepartment(department);
		lingyongRecord.setOldtonew(oldtonew);
		lingyongRecord.setUsePurpose(usePurpose);
		if(lingyongRecordService.insertSelective(lingyongRecord)){
			Product product = new Product();
			product.setProductId(productId);
			product.setRemainAmount(product1.getRemainAmount() - amount);
			return productService.updateByPrimaryKeySelective(product);
		}else {
			return ServerResponse.createByError();
		}
	}
}