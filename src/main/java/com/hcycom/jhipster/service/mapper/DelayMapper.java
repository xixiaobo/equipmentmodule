package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hcycom.jhipster.domain.Delay;


@Mapper
public interface DelayMapper {

	/**
	 * 增加链路
	 * 
	 * @param ce
	 * @return
	 */
//	@Insert("INSERT INTO ipx_vll_link_delay (vll_link_id,link_status,delay_average,"
//			+ "delay_maxnum,delay_minnum,packet_loss,test_time) "
//			+ "VALUES(#{delay.vll_link_id},#{delay.link_status},#{delay.delay_average},"
//			+ "#{delay.delay_maxnum},#{delay.delay_minnum},#{delay.packet_loss},#{delay.test_time})")
	@Insert("INSERT INTO ipx_vll_link_delay (vll_link_id,link_status,delay_average,"
			+ "delay_maxnum,delay_minnum,packet_loss,test_time) "
			+ "VALUES(#{delay.vll_link_id},#{delay.link_status},#{delay.delay_average},"
			+ "#{delay.delay_maxnum},#{delay.delay_minnum},#{delay.packet_loss},DATE_FORMAT(#{delay.test_time},'%Y-%m-%d %T'))")
	public int addDelay(@Param("delay") Delay delay);

	/**
	 * 根据原设备ID获取链路信息
	 * 
	 * @param Delay
	 * @return
	 */
	@Select("SELECT vll_link_id,link_status,delay_average,delay_maxnum,delay_minnum,"
			+ "packet_loss,DATE_FORMAT(test_time, '%Y-%m-%d %T') test_time "
			+ "FROM ipx_vll_link_delay	WHERE vll_link_id = #{delay.vll_link_id}	"
			+ "AND DATE_FORMAT(test_time, '%Y-%m-%d') BETWEEN DATE_FORMAT(#{beginTime},'%Y-%m-%d') "
			+ "AND DATE_FORMAT(#{endTime},'%Y-%m-%d') ORDER BY	test_time")
	public List<Delay> findDelayByIDAndTime(@Param("delay") Delay delay,@Param("beginTime") String beginTime,@Param("endTime") String endTime);

}
