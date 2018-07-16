package com.tiemao.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.Product;
import com.tiemao.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/productInfo")
	@ResponseBody
	public PageBean<Product> productInfo(@RequestParam(value="page",defaultValue="0")Integer page, @RequestParam(value="limit",defaultValue="10")Integer limit, String searchKey, String searchValue){
		return productService.getProductInfo(page, limit, searchKey, searchValue);
	}
	
	@RequestMapping("/deleteProduct")
	@ResponseBody
	public ServerResponse deleteProduct(Integer productId){
		return productService.deleteByPrimaryKey(productId);
	}
	
	@RequestMapping("/insertProduct")
	@ResponseBody
	public ServerResponse insertProduct(String productName,Integer remainAmount,Integer isDelete, HttpServletRequest request) throws ParseException{
		Product product = new Product();
		product.setProductName(productName);
		
		if(remainAmount != null && !"".equals(remainAmount)){
			product.setRemainAmount(remainAmount);
		}
		if(isDelete == null && "".equals(isDelete)){
			product.setIsDelete(0);
		}else {
			product.setIsDelete(isDelete);
		}
		return productService.insertSelective(product);
	}
	
	@RequestMapping("/updateProduct")
	@ResponseBody
	public ServerResponse updateProduct(Integer productId, String productName,Integer remainAmount,Integer isDelete, HttpServletRequest request){
		Product product = new Product();
		product.setProductId(productId);
		product.setProductName(productName);
		
		if (null != remainAmount && !"".equals(remainAmount)) {
			product.setRemainAmount(remainAmount);
		}

		if (isDelete != null && !"".equals(isDelete)) {
			product.setIsDelete(isDelete);
		}
		return productService.updateByPrimaryKeySelective(product);
	}

}
