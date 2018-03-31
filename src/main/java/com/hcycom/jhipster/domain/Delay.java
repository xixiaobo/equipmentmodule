package com.hcycom.jhipster.domain;

import java.io.Serializable;
/**
 * VLL链路时延表
 * @author Xi
 *
 */
public class Delay implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vll_link_id;			//VLL链路id
	private int link_status;			//链路状态
	private double delay_average;		//当前平均时延
	private double delay_maxnum;		//当前最大时延
	private double delay_minnum;		//当前最小时延
	private String packet_loss;			//丢包率
	private String test_time;				//测试时间
	public String getVll_link_id() {
		return vll_link_id;
	}
	public void setVll_link_id(String vll_link_id) {
		this.vll_link_id = vll_link_id;
	}
	public int getLink_status() {
		return link_status;
	}
	public void setLink_status(int link_status) {
		this.link_status = link_status;
	}
	
	public double getDelay_average() {
		return delay_average;
	}
	public void setDelay_average(double delay_average) {
		this.delay_average = delay_average;
	}
	public double getDelay_maxnum() {
		return delay_maxnum;
	}
	public void setDelay_maxnum(double delay_maxnum) {
		this.delay_maxnum = delay_maxnum;
	}
	public double getDelay_minnum() {
		return delay_minnum;
	}
	public void setDelay_minnum(double delay_minnum) {
		this.delay_minnum = delay_minnum;
	}
	
	public String getPacket_loss() {
		return packet_loss;
	}
	public void setPacket_loss(String packet_loss) {
		this.packet_loss = packet_loss;
	}
	public String getTest_time() {
		return test_time;
	}
	public void setTest_time(String test_time) {
		this.test_time = test_time;
	}
	@Override
	public String toString() {
		return "Delay [vll_link_id=" + vll_link_id + ", link_status=" + link_status + ", delay_average=" + delay_average
				+ ", delay_maxnum=" + delay_maxnum + ", delay_minnum=" + delay_minnum + ", packet_loss=" + packet_loss
				+ ", test_time=" + test_time + "]";
	}
	
	
	

}
