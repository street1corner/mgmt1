package com.tiemao.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EQPMaintainRecord {
    private Integer id;

    private String eqpName;

    private String eqpNumber;

    private Date breakdownTime;

    private String breakdownDesc;

    private Date solutionTime;

    private Integer status;

    private String maintainPerson;

    private Integer haveMirror;

    private String relativeData;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String lastmodifyPerson;

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getBreakdownTime() {
        return breakdownTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setBreakdownTime(Date breakdownTime) {
        this.breakdownTime = breakdownTime;
    }

    public String getBreakdownDesc() {
        return breakdownDesc;
    }

    public void setBreakdownDesc(String breakdownDesc) {
        this.breakdownDesc = breakdownDesc == null ? null : breakdownDesc.trim();
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getSolutionTime() {
        return solutionTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setSolutionTime(Date solutionTime) {
        this.solutionTime = solutionTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMaintainPerson() {
        return maintainPerson;
    }

    public void setMaintainPerson(String maintainPerson) {
        this.maintainPerson = maintainPerson == null ? null : maintainPerson.trim();
    }

    public Integer getHaveMirror() {
        return haveMirror;
    }

    public void setHaveMirror(Integer haveMirror) {
        this.haveMirror = haveMirror;
    }

    public String getRelativeData() {
        return relativeData;
    }

    public void setRelativeData(String relativeData) {
        this.relativeData = relativeData == null ? null : relativeData.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getLastmodifyPerson() {
        return lastmodifyPerson;
    }

    public void setLastmodifyPerson(String lastmodifyPerson) {
        this.lastmodifyPerson = lastmodifyPerson == null ? null : lastmodifyPerson.trim();
    }
}