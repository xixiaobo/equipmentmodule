package com.hcycom.jhipster.service.DTO;

public class LinkDelayDTO {
	private String vll_link_id; // VLL链路id
	private String ce_ip;	 // 设备连接ip
	private String ce_port;	 // 设备端口
	private String ssh_username;	// 登录用户名
	private String ssh_password;	// 登录密码
	private String dst_port_ip; // 目的端端口ip
	private String os;
	private String link_status;
	public String getVll_link_id() {
		return vll_link_id;
	}
	public void setVll_link_id(String vll_link_id) {
		this.vll_link_id = vll_link_id;
	}
	public String getCe_ip() {
		return ce_ip;
	}
	public void setCe_ip(String ce_ip) {
		this.ce_ip = ce_ip;
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
	public String getDst_port_ip() {
		return dst_port_ip;
	}
	public void setDst_port_ip(String dst_port_ip) {
		this.dst_port_ip = dst_port_ip;
	}
	
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	
	public String getLink_status() {
		return link_status;
	}
	public void setLink_status(String link_status) {
		this.link_status = link_status;
	}
	@Override
	public String toString() {
		return "LinkDelayDTO [vll_link_id=" + vll_link_id + ", ce_ip=" + ce_ip + ", ce_port=" + ce_port
				+ ", ssh_username=" + ssh_username + ", ssh_password=" + ssh_password + ", dst_port_ip=" + dst_port_ip
				+ ", os=" + os + ", link_status=" + link_status + "]";
	}
	
	
}
