package com.hcycom.jhipster.domain;

/**
 * CE设备表实体类
 * 
 * @author Xi
 *
 */
public class Device {
	private String ce_id;	 // 设备id
	private String ce_name;	 // 设备名称
	private String ce_ip;	 // 设备连接ip
	private String ce_port;	 // 设备端口
	private String ssh_username;	// 登录用户名
	private String ssh_password;	// 登录密码
	private String os;	// 登录系统
	
	private String os_kernel;	//操作系统内核型号
    private String cpu_total;	//CPU核心数
    private String disk_mount;	//硬盘挂载名及容量
    private String swap_total;	//虚拟内容容量
    private String server_type;	//服务器型号
    private String disk_total;	//硬盘总容量
    private String mem_total;	//物理内存容量
    private String os_type;	//操作系统类型
    private String ipv4;	//服务器ipv4地址
    private String host_name;	//服务器主机名
    private String cpu_type;	//CPU型号
    private int status=1;	//设备状态
	public String getCe_id() {
		return ce_id;
	}
	public void setCe_id(String ce_id) {
		this.ce_id = ce_id;
	}
	public String getCe_name() {
		return ce_name;
	}
	public void setCe_name(String ce_name) {
		this.ce_name = ce_name;
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
	
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOs_kernel() {
		return os_kernel;
	}
	public void setOs_kernel(String os_kernel) {
		this.os_kernel = os_kernel;
	}
	public String getCpu_total() {
		return cpu_total;
	}
	public void setCpu_total(String cpu_total) {
		this.cpu_total = cpu_total;
	}
	public String getDisk_mount() {
		return disk_mount;
	}
	public void setDisk_mount(String disk_mount) {
		this.disk_mount = disk_mount;
	}
	public String getSwap_total() {
		return swap_total;
	}
	public void setSwap_total(String swap_total) {
		this.swap_total = swap_total;
	}
	public String getServer_type() {
		return server_type;
	}
	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}
	public String getDisk_total() {
		return disk_total;
	}
	public void setDisk_total(String disk_total) {
		this.disk_total = disk_total;
	}
	public String getMem_total() {
		return mem_total;
	}
	public void setMem_total(String mem_total) {
		this.mem_total = mem_total;
	}
	public String getOs_type() {
		return os_type;
	}
	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}
	public String getIpv4() {
		return ipv4;
	}
	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getCpu_type() {
		return cpu_type;
	}
	public void setCpu_type(String cpu_type) {
		this.cpu_type = cpu_type;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Device [ce_id=" + ce_id + ", ce_name=" + ce_name + ", ce_ip=" + ce_ip + ", ce_port=" + ce_port
				+ ", ssh_username=" + ssh_username + ", ssh_password=" + ssh_password + ", os=" + os + ", os_kernel="
				+ os_kernel + ", cpu_total=" + cpu_total + ", disk_mount=" + disk_mount + ", swap_total=" + swap_total
				+ ", server_type=" + server_type + ", disk_total=" + disk_total + ", mem_total=" + mem_total
				+ ", os_type=" + os_type + ", ipv4=" + ipv4 + ", host_name=" + host_name + ", cpu_type=" + cpu_type
				+ ", status=" + status + "]";
	}
   
}
