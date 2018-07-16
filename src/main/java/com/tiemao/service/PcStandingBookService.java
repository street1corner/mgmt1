package com.tiemao.service;

import com.tiemao.common.PageBean;
import com.tiemao.common.ServerResponse;
import com.tiemao.pojo.PcStandingbook;

/**
 * pc台帐
 * 服务类接口
 * @author hlw
 * @date 2018年4月16日 14:04:10
 */
public interface PcStandingBookService {
	/**
	 * @param pageNum
	 * @param pageSize
	 * @param searchKey
	 * @param searchValue
	 * @return
	 */
	public PageBean<PcStandingbook> getPcStandingBookInfo(int pageNum, int pageSize, String searchKey, String searchValue);
	/**
	 * 根据id删除
	 * @param userId
	 * @return
	 */
	public ServerResponse deleteByPrimaryKey(Integer id);
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public ServerResponse insertSelective(PcStandingbook record);
	/**
	 * 更新
	 * @param record
	 * @return
	 */
	public ServerResponse updatePcStandingBookInfo(PcStandingbook record);
}
