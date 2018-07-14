package com.tiemao.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 领用记录
 * @author hlw
 * @date 2018年5月19日 14:34:54
 */
public class LingyongRecord {
    private Integer id;

    private Integer productId;

    private String user;

    private Date lingyongTime;

    private String department;

    private String usePurpose;

    private Integer oldtonew;

    private String remark;
    
    private Integer amount;
    
    /**
     * 新增
     */
    private String productName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getLingyongTime() {
        return lingyongTime;
    }

    public void setLingyongTime(Date lingyongTime) {
        this.lingyongTime = lingyongTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getUsePurpose() {
        return usePurpose;
    }

    public void setUsePurpose(String usePurpose) {
        this.usePurpose = usePurpose == null ? null : usePurpose.trim();
    }

    public Integer getOldtonew() {
        return oldtonew;
    }

    public void setOldtonew(Integer oldtonew) {
        this.oldtonew = oldtonew;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}