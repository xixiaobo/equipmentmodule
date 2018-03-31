package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hcycom.jhipster.domain.Device;
import com.hcycom.jhipster.domain.tool.Page;
import com.hcycom.jhipster.service.DTO.DeviceDTO;

@Mapper
public interface DeviceMapper {

	/**
	 * 增加设备
	 * 
	 * @param ce
	 * @return
	 */
	@Insert("INSERT INTO ipx_device_ce (ce_id,ce_name,ce_ip,ce_port,ssh_username,ssh_password,os,os_kernel,"
			+ "cpu_total,disk_mount,swap_total,server_type,disk_total,mem_total,os_type,ipv4,host_name,cpu_type,status) "
			+ "VALUES(#{device.ce_id},#{device.ce_name},#{device.ce_ip},#{device.ce_port},"
			+ "#{device.ssh_username},#{device.ssh_password},#{device.os},#{device.os_kernel},#{device.cpu_total},#{device.disk_mount},"
			+ "#{device.swap_total},#{device.server_type},#{device.disk_total},#{device.mem_total},#{device.os_type},#{device.ipv4},#{device.host_name},#{device.cpu_type},#{device.status})")
	public int addDevice(@Param("device") Device device);

	/**
	 * 修改设备别名
	 * 
	 * @param ce
	 * @return
	 */
	@Update("update ipx_device_ce set ce_name=#{device.ce_name} where ce_id=#{device.ce_id}")
	public int updateDeviceName(@Param("device") DeviceDTO device);
	
	/**
	 * 修改设备状态
	 * 
	 * @param ce
	 * @return
	 */
	@Update("update ipx_device_ce set status=#{device.status} where ce_id=#{device.ce_id}")
	public int updateDeviceStatus(@Param("device") DeviceDTO device);

	/**
	 * 修改设备SSH密码
	 * 
	 * @param ce
	 * @return
	 */
	@Update("update ipx_device_ce set ssh_password = #{device.ssh_password} where ce_id=#{device.ce_id}")
	public int updateDeviceSSHPass(@Param("device") DeviceDTO device);
	
	/**
	 * 删除设备
	 * 
	 * @param ce
	 * @return
	 */
	@Delete("DELETE  d,l,dl FROM ipx_device_ce d LEFT JOIN ipx_vll_link l ON  l.src_device_id=d.ce_id "
			+ "LEFT JOIN ipx_vll_link_delay dl ON l.vll_link_id = dl.vll_link_id "
			+ "WHERE d.ce_id=#{device.ce_id}")
	public int deleteDevice(@Param("device") DeviceDTO device);

	/**
	 * 获取设备详细信息
	 * 
	 * @param ce
	 * @return
	 */
	@Select("select ce_id,ce_name,ce_ip,ce_port,ssh_username,os,os_kernel,"
			+ "cpu_total,disk_mount,swap_total,server_type,disk_total,mem_total,os_type,ipv4,host_name,cpu_type,status"
			+ " from ipx_device_ce where ce_id=#{device.ce_id}")
	public Device getDeviceById(@Param("device") DeviceDTO device);

	/**
	 * 获取所有设备信息
	 * 
	 * @return
	 */
	@Select("select ce_id,ce_name,ce_ip,ce_port,ssh_username,os,os_kernel,"
			+ "cpu_total,disk_mount,swap_total,server_type,disk_total,mem_total,os_type,ipv4,host_name,cpu_type,status"
			+ " from ipx_device_ce")
	public List<Device> getAllDevice();

	/**
	 * 获取设备总数
	 * 
	 * @return
	 */
	@Select("select count(ce_id) from ipx_device_ce")
	public int getAllDeviceCount();

	/**
	 * 分页获取设备
	 * 
	 * @return
	 */
	@Select("select ce_id,ce_name,ce_ip,ce_port,ssh_username,os,os_kernel,"
			+ "cpu_total,disk_mount,swap_total,server_type,disk_total,mem_total,os_type,ipv4,host_name,cpu_type,status"
			+ " from ipx_device_ce LIMIT #{page.startPos},#{page.pageSize}")
	public List<Device> getAllCEByPage(@Param("page") Page page);

	/**
	 * 根据Ip获取设备信息
	 * 
	 * @param device
	 * @return
	 */
	@Select("select ce_id,ce_name,ce_ip,ce_port,ssh_username,os,os_kernel,"
			+ "cpu_total,disk_mount,swap_total,server_type,disk_total,mem_total,os_type,ipv4,host_name,cpu_type,status"
			+ " from ipx_device_ce where ce_ip=#{device.ce_ip}")
	public Device findDeviceByIp(@Param("device") DeviceDTO device);

	/**
	 * 筛选设备别名获取设备信息
	 * 
	 * @param device
	 * @return
	 */
	@Select("select ce_id,ce_name,ce_ip,ce_port,ssh_username,os,os_kernel,"
			+ "cpu_total,disk_mount,swap_total,server_type,disk_total,mem_total,os_type,ipv4,host_name,cpu_type,status"
			+ " from ipx_device_ce where ce_name like '%${device_name}%'")
	public List<Device> getAllDeviceByLike(@Param("device_name") String device_name);
}
