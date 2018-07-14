package com.tiemao.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**个人电脑台帐基类
 * @author hlw
 * @date 2018年4月16日 10:08:59
 */
public class PcStandingbook {
	private Integer id;
	
    private String computerId;

    private String brand;

    private String type;

    private String configure;

    private String installedSystem;

    private Date installedDate;

    private String guaranteeTime;

    private String mirroredVersion;

    private String macAddress;

    //使用人
    private String username;

    //安装人
    private String installer;
    
    private Date createTime;

    private Date updateTime;
    
    private String lastmodifyPerson;
    
    private String installedSoft;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComputerId() {
        return computerId;
    }

    public void setComputerId(String computerId) {
        this.computerId = computerId == null ? null : computerId.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getConfigure() {
        return configure;
    }

    public void setConfigure(String configure) {
        this.configure = configure == null ? null : configure.trim();
    }

    public String getInstalledSystem() {
        return installedSystem;
    }

    public void setInstalledSystem(String installedSystem) {
        this.installedSystem = installedSystem == null ? null : installedSystem.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    public String getGuaranteeTime() {
        return guaranteeTime;
    }

    public void setGuaranteeTime(String guaranteeTime) {
        this.guaranteeTime = guaranteeTime == null ? null : guaranteeTime.trim();
    }

    public String getMirroredVersion() {
        return mirroredVersion;
    }

    public void setMirroredVersion(String mirroredVersion) {
        this.mirroredVersion = mirroredVersion == null ? null : mirroredVersion.trim();
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress == null ? null : macAddress.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer == null ? null : installer.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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
		this.lastmodifyPerson = lastmodifyPerson;
	}
	
	public String getInstalledSoft() {
		return installedSoft;
	}

	public void setInstalledSoft(String installedSoft) {
		this.installedSoft = installedSoft == null ? null : installedSoft.trim();
	}
    
}