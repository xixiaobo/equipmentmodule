package com.hcycom.jhipster.service.DTO;

public class DeviceDTO {
	private String ce_id;	 // 设备id
	private String ce_ip;	 // 设备连接ip
	private String ce_port;	 // 设备端口
	private String ssh_username;	// 登录用户名
	private String ssh_password;	// 登录密码
	private String os;	//系统类型
	private int status;
	
	public String getCe_ip() {
		return ce_ip;
	}
	public void setCe_ip(String ce_ip) {
		this.ce_ip = ce_ip;
	}
	public String getCe_id() {
		return ce_id;
	}
	public void setCe_id(String ce_id) {
		this.ce_id = ce_id;
	}
	public String getCe_port() {
		return ce_port;
	}
	public void setCe_port(String ce_port) {
		this.ce_port = ce_port;
	}
	public String getSsh_username() {
		return ssh_username;
	}
	public void setSsh_username(String ssh_username) {
		this.ssh_username = ssh_username;
	}
	public String getSsh_password() {
		return ssh_password;
	}
	public void setSsh_password(String ssh_password) {
		this.ssh_password = ssh_password;
	}
	
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DeviceDTO [ce_id=" + ce_id + ", ce_ip=" + ce_ip + ", ce_port=" + ce_port + ", ssh_username="
				+ ssh_username + ", ssh_password=" + ssh_password + ", os=" + os + ", status=" + status + "]";
	}
	
	
}
