package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hcycom.jhipster.domain.Performance;

@Mapper
public interface PerformanceMapper {

	/**
	 * 增加CPU、内存等数据
	 * 
	 * @param ce
	 * @return
	 */
	@Insert("INSERT INTO ipx_device_performance (ce_id,cpu_user,cpu_nice,cpu_system,cpu_steal,"
			+ "cpu_idle,rxbyt,txbyt,kbmemfree,kbmemused,memused,test_time) "
			+ "VALUES(#{performance.ce_id},#{performance.cpu_user},#{performance.cpu_nice},"
			+ "#{performance.cpu_system},#{performance.cpu_steal},#{performance.cpu_idle},"
			+ "#{performance.rxbyt},#{performance.txbyt},#{performance.kbmemfree},"
			+ "#{performance.kbmemused},#{performance.memused},DATE_FORMAT(#{performance.test_time},'%Y-%m-%d %T'))")
	public int addPerformance(@Param("performance") Performance performance);

	/**
	 * 根据原设备ID获取链路信息
	 * 
	 * @param Delay
	 * @return
	 */
	@Select("SELECT ce_id,cpu_user,cpu_nice,cpu_system,cpu_steal,cpu_idle,rxbyt,txbyt,kbmemfree,"
			+ "kbmemused,memused,DATE_FORMAT(test_time, '%Y-%m-%d %T') test_time "
			+ "FROM ipx_device_performance	WHERE ce_id = #{ce_id} "
			+ "AND DATE_FORMAT(test_time, '%Y-%m-%d') BETWEEN DATE_FORMAT(#{beginTime},'%Y-%m-%d') "
			+ "AND DATE_FORMAT(#{endTime},'%Y-%m-%d') ORDER BY	test_time")
	public List<Performance> findPerformanceByIDAndTime(@Param("ce_id") String ce_id, @Param("beginTime") String beginTime,
			@Param("endTime") String endTime);

}
