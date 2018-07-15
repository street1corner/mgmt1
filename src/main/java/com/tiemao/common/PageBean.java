package com.tiemao.common;

import java.util.List;

public class PageBean<T> {
	
	//已知数据
    private int pageNum;    //当前页,从请求那边传过来。
    private int pageSize;    //每页显示的数据条数。
    private int totalRecord;    //总的记录条数。查询数据库得到的数据
	      
	//需要计算得来
    private int totalPage;    //总页数，通过totalRecord和pageSize计算可以得来
    /**
     * 开始索引，也就是我们在数据库中要从第几行数据开始拿，有了startIndex和pageSize，
     * 就知道了limit语句的两个数据，就能获得每页需要显示的数据了
     */
    private int startIndex;        
    private String msg;    //状态信息 
    private int code;    //数据状态码  成功的状态码，默认：0
    //将每页要显示的数据放在list集合中
	private List<T> data;
	
	public PageBean() {
		// TODO Auto-generated constructor stub
	}
	public PageBean(int pageNum,int pageSize,int totalRecord) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		//总页数
		if(totalRecord % pageSize == 0){
			this.totalPage = totalRecord / pageSize; 
		}else{
			this.totalPage = totalRecord / pageSize + 1;
		}
		//开始索引
		this.startIndex = (pageNum-1)*pageSize ;
	}
	
	public PageBean(int totalRecord, List<T> data){
		this.totalRecord = totalRecord;
		this.data = data;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
}
