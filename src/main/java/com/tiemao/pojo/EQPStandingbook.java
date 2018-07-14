package com.tiemao.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author hlw
 * @date 2018年6月2日 09:07:15
 */
public class EQPStandingbook {
    private Integer id;

    private String eqpName;

    private String eqpNumber;

    private String eqpModel;

    private String eqpManufactor;

    private Date useDate;

    private String installPlace;

    private Integer eqpState;

    private String dept;

    private String deptSub;

    private String remark;

    private String archivesNumber;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEqpName() {
        return eqpName;
    }

    public void setEqpName(String eqpName) {
        this.eqpName = eqpName == null ? null : eqpName.trim();
    }

    public String getEqpNumber() {
        return eqpNumber;
    }

    public void setEqpNumber(String eqpNumber) {
        this.eqpNumber = eqpNumber == null ? null : eqpNumber.trim();
    }

    public String getEqpModel() {
        return eqpModel;
    }

    public void setEqpModel(String eqpModel) {
        this.eqpModel = eqpModel == null ? null : eqpModel.trim();
    }

    public String getEqpManufactor() {
        return eqpManufactor;
    }

    public void setEqpManufactor(String eqpManufactor) {
        this.eqpManufactor = eqpManufactor == null ? null : eqpManufactor.trim();
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getUseDate() {
        return useDate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public String getInstallPlace() {
        return installPlace;
    }

    public void setInstallPlace(String installPlace) {
        this.installPlace = installPlace == null ? null : installPlace.trim();
    }

    public Integer getEqpState() {
        return eqpState;
    }

    public void setEqpState(Integer eqpState) {
        this.eqpState = eqpState;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getDeptSub() {
        return deptSub;
    }

    public void setDeptSub(String deptSub) {
        this.deptSub = deptSub == null ? null : deptSub.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getArchivesNumber() {
        return archivesNumber;
    }

    public void setArchivesNumber(String archivesNumber) {
        this.archivesNumber = archivesNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}