package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hcycom.jhipster.domain.Link;
import com.hcycom.jhipster.domain.tool.Page;
import com.hcycom.jhipster.service.DTO.LinkDelayDTO;

@Mapper
public interface LinkMapper {

	/**
	 * 增加链路
	 * 
	 * @param ce
	 * @return
	 */
	@Insert("INSERT INTO ipx_vll_link (vll_link_id,vll_link_name,src_device_id,dst_device,"
			+ "dst_port_ip,link_status) VALUES(#{link.vll_link_id},#{link.vll_link_name},"
			+ "#{link.src_device_id},#{link.dst_device},#{link.dst_port_ip},#{link.link_status})")
	public int addLink(@Param("link") Link link);

	/**
	 * 修改链路名称
	 * 
	 * @param ce
	 * @return
	 */
	@Update("update ipx_vll_link set vll_link_name=#{link.vll_link_name} where vll_link_id=#{link.vll_link_id}")
	public int updateLinkName(@Param("link") Link link);
	
	@Update("update ipx_vll_link set link_status=#{link.link_status},"
			+ "delay_average = #{link.delay_average},delay_maxnum = #{link.delay_maxnum},"
			+ "delay_minnum	= #{link.delay_minnum},packet_loss = #{link.packet_loss},"
			+ "last_test_time=DATE_FORMAT(#{link.last_test_time},'%Y-%m-%d %T') "
			+ "where vll_link_id = #{link.vll_link_id}")
	public int loadingLinkByLinkID(@Param("link") Link link);
	
	@Update("update ipx_vll_link set link_status=#{link.link_status},"
			+ "delay_average = #{link.delay_average},delay_maxnum = #{link.delay_maxnum},"
			+ "delay_minnum	= #{link.delay_minnum},packet_loss = #{link.packet_loss},"
			+ "last_test_time=DATE_FORMAT(#{link.last_test_time},'%Y-%m-%d %T') "
			+ "where src_device_id = #{link.src_device_id}")
	public int loadingLinkByDeviceID(@Param("link") Link link);

	/**
	 * 删除链路
	 * 
	 * @param ce
	 * @return
	 */
	@Delete("DELETE l,dl FROM ipx_vll_link l LEFT JOIN ipx_vll_link_delay dl ON "
			+ "l.vll_link_id = dl.vll_link_id WHERE l.vll_link_id=#{link.vll_link_id}")
	public int deleteLink(@Param("link") Link link);

	/**
	 * 获取链路详细信息
	 * 
	 * @param ce
	 * @return
	 */
//	@Select("select vll_link_id,vll_link_name,src_device_id,dst_device,"
//			+ "dst_port_ip,link_status,delay_average,delay_maxnum,delay_minnum,packet_loss,last_test_time"
//			+ " from ipx_vll_link where vll_link_id=#{link.vll_link_id}")
	@Select("select	vll_link_id,vll_link_name,src_device_id,dst_device,"
			+ "dst_port_ip,link_status,delay_average,delay_maxnum,delay_minnum,packet_loss,"
			+ "DATE_FORMAT(last_test_time,'%Y-%m-%d %T') last_test_time"
			+ " from ipx_vll_link where vll_link_id = #{link.vll_link_id}")
	public Link getLinkById(@Param("link") Link link);

	/**
	 * 获取所有链路信息
	 * 
	 * @return
	 */
	@Select("select	vll_link_id,vll_link_name,src_device_id,dst_device,"
			+ "dst_port_ip,link_status,delay_average,delay_maxnum,delay_minnum,packet_loss,"
			+ "DATE_FORMAT(last_test_time,'%Y-%m-%d %T') last_test_time"
			+ " from ipx_vll_link")
	public List<Link> getAllLink();

	/**
	 * 获取链路总数
	 * 
	 * @return
	 */
	@Select("select count(vll_link_id) from ipx_vll_link")
	public int getAllLinkCount();

	/**
	 * 分页获取链路
	 * 
	 * @return
	 */
	@Select("select	vll_link_id,vll_link_name,src_device_id,dst_device,"
			+ "dst_port_ip,link_status,delay_average,delay_maxnum,delay_minnum,packet_loss,"
			+ "DATE_FORMAT(last_test_time,'%Y-%m-%d %T') last_test_time"
			+ " from ipx_vll_link LIMIT #{page.startPos},#{page.pageSize}")
	public List<Link> getAllCEByPage(@Param("page") Page page);

	/**
	 * 根据原链路ID获取链路信息
	 * 
	 * @param Link
	 * @return
	 */
	@Select("select	vll_link_id,vll_link_name,src_device_id,dst_device,"
			+ "dst_port_ip,link_status,delay_average,delay_maxnum,delay_minnum,packet_loss,"
			+ "DATE_FORMAT(last_test_time,'%Y-%m-%d %T') last_test_time"
			+ " from ipx_vll_link where src_device_id=#{link.src_device_id}")
	public List<Link> findLinkBySreID(@Param("link") Link link);


	/**
	 * 筛选链路别名获取链路信息
	 * 
	 * @param Link
	 * @return
	 */
	@Select("select	vll_link_id,vll_link_name,src_device,src_port_ip,dst_device,dst_port_ip,"
			+ "link_status,delay_average,delay_maxnum,delay_minnum,packet_loss,"
			+ "DATE_FORMAT(last_test_time,'%Y-%m-%d %T') last_test_time from ipx_vll_link where vll_link_name like '%${link_name}%'")
	public List<Link> getAllLinkByLike(@Param("link_name") String link_name);

	/**
	 * 获取所有链路中的设备信息
	 * 
	 * @return
	 */
	@Select("select v.vll_link_id,v.dst_port_ip,v.link_status," + "d.ce_ip,d.ce_port,d.ssh_username,d.ssh_password,d.os"
			+ " from ipx_vll_link v left join ipx_device_ce d " + "on v.src_device_id=d.ce_id where d.ce_id=#{ce_id}")
	public List<LinkDelayDTO> getLinkDevice(@Param("ce_id") String ce_id);
}
