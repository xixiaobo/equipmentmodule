package com.hcycom.jhipster.domain;

public class Performance {
	private String ce_id;			//设备id
	private double cpu_user=0;		//用户空间程序的cpu使用率
	private double cpu_nice=0;		//用户空间且通过nice调度过的程序的cpu使用率
	private double cpu_system=0;		//系统空间的cpu使用率，主要是内核程序
	private double cpu_steal=0;			//被虚拟机偷走的cpu
	private double cpu_idle=0;			//空闲cpu
	private double rxbyt=0;			//每秒接收的字节（byte）总数
	private double txbyt=0;			//每秒传输的字节（byte）总数
	private String kbmemfree="0";		//可用的空闲内存数量，单位为 KB
	private String kbmemused="0";		//已使用的内存数量
	private double memused=0;			//已使用内存的百分数
	private String test_time;				//测试时间
	public String getCe_id() {
		return ce_id;
	}
	public void setCe_id(String ce_id) {
		this.ce_id = ce_id;
	}
	public double getCpu_user() {
		return cpu_user;
	}
	public void setCpu_user(double cpu_user) {
		this.cpu_user = cpu_user;
	}
	public double getCpu_nice() {
		return cpu_nice;
	}
	public void setCpu_nice(double cpu_nice) {
		this.cpu_nice = cpu_nice;
	}
	public double getCpu_system() {
		return cpu_system;
	}
	public void setCpu_system(double cpu_system) {
		this.cpu_system = cpu_system;
	}
	public double getCpu_steal() {
		return cpu_steal;
	}
	public void setCpu_steal(double cpu_steal) {
		this.cpu_steal = cpu_steal;
	}
	public double getCpu_idle() {
		return cpu_idle;
	}
	public void setCpu_idle(double cpu_idle) {
		this.cpu_idle = cpu_idle;
	}
	public double getRxbyt() {
		return rxbyt;
	}
	public void setRxbyt(double rxbyt) {
		this.rxbyt = rxbyt;
	}
	public double getTxbyt() {
		return txbyt;
	}
	public void setTxbyt(double txbyt) {
		this.txbyt = txbyt;
	}
	public String getKbmemfree() {
		return kbmemfree;
	}
	public void setKbmemfree(String kbmemfree) {
		this.kbmemfree = kbmemfree;
	}
	public String getKbmemused() {
		return kbmemused;
	}
	public void setKbmemused(String kbmemused) {
		this.kbmemused = kbmemused;
	}
	public double getMemused() {
		return memused;
	}
	public void setMemused(double memused) {
		this.memused = memused;
	}
	public String getTest_time() {
		return test_time;
	}
	public void setTest_time(String test_time) {
		this.test_time = test_time;
	}
	@Override
	public String toString() {
		return "Performance [ce_id=" + ce_id + ", cpu_user=" + cpu_user + ", cpu_nice=" + cpu_nice + ", cpu_system="
				+ cpu_system + ", cpu_steal=" + cpu_steal + ", cpu_idle=" + cpu_idle + ", rxbyt=" + rxbyt + ", txbyt="
				+ txbyt + ", kbmemfree=" + kbmemfree + ", kbmemused=" + kbmemused + ", memused=" + memused
				+ ", test_time=" + test_time + "]";
	}
	
	
	
}
