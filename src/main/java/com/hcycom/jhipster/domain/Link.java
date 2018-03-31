package com.hcycom.jhipster.domain;

/**
 * VLL链路表实体类
 * 
 * @author Xi
 *
 */
public class Link {
	private String vll_link_id; // VLL链路id
	private String vll_link_name; // VLL链路名称
	private String src_device_id; // 源端设备id
	private String dst_device; // 目的端设备名称
	private String dst_port_ip; // 目的端端口ip
	private int link_status=1; // 链路状态【1为正常，0为不正常,3为暂停】
	private int delay_average; // 当前平均时延
	private int delay_maxnum; // 当前最大时延
	private int delay_minnum; // 当前最小时延
	private double packet_loss; // 丢包率
	private String last_test_time; // 最后测试时间
	public String getVll_link_id() {
		return vll_link_id;
	}
	public void setVll_link_id(String vll_link_id) {
		this.vll_link_id = vll_link_id;
	}
	public String getVll_link_name() {
		return vll_link_name;
	}
	public void setVll_link_name(String vll_link_name) {
		this.vll_link_name = vll_link_name;
	}
	public String getSrc_device_id() {
		return src_device_id;
	}
	public void setSrc_device_id(String src_device_id) {
		this.src_device_id = src_device_id;
	}
	public String getDst_device() {
		return dst_device;
	}
	public void setDst_device(String dst_device) {
		this.dst_device = dst_device;
	}
	public String getDst_port_ip() {
		return dst_port_ip;
	}
	public void setDst_port_ip(String dst_port_ip) {
		this.dst_port_ip = dst_port_ip;
	}
	public int getLink_status() {
		return link_status;
	}
	public void setLink_status(int link_status) {
		this.link_status = link_status;
	}
	public int getDelay_average() {
		return delay_average;
	}
	public void setDelay_average(int delay_average) {
		this.delay_average = delay_average;
	}
	public int getDelay_maxnum() {
		return delay_maxnum;
	}
	public void setDelay_maxnum(int delay_maxnum) {
		this.delay_maxnum = delay_maxnum;
	}
	public int getDelay_minnum() {
		return delay_minnum;
	}
	public void setDelay_minnum(int delay_minnum) {
		this.delay_minnum = delay_minnum;
	}
	
	public double getPacket_loss() {
		return packet_loss;
	}
	public void setPacket_loss(double packet_loss) {
		this.packet_loss = packet_loss;
	}
	public String getLast_test_time() {
		return last_test_time;
	}
	public void setLast_test_time(String last_test_time) {
		this.last_test_time = last_test_time;
	}
	@Override
	public String toString() {
		return "Link [vll_link_id=" + vll_link_id + ", vll_link_name=" + vll_link_name + ", src_device_id="
				+ src_device_id + ", dst_device=" + dst_device + ", dst_port_ip=" + dst_port_ip + ", link_status="
				+ link_status + ", delay_average=" + delay_average + ", delay_maxnum=" + delay_maxnum
				+ ", delay_minnum=" + delay_minnum + ", packet_loss=" + packet_loss + ", last_test_time="
				+ last_test_time + "]";
	}

}
