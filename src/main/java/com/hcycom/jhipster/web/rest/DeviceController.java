package com.hcycom.jhipster.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hcycom.jhipster.domain.Device;
import com.hcycom.jhipster.service.DTO.DeviceDTO;
import com.hcycom.jhipster.service.mapper.DeviceMapper;
import com.hcycom.jhipster.service.mapper.LinkMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@Api(tags = { "设备管理" }, description = "设备管理接口")
public class DeviceController {
	private final Logger log = LoggerFactory.getLogger(DeviceController.class);

	@Autowired
	private DeviceMapper deviceMapper;

	@RequestMapping(value = "/Device", method = RequestMethod.POST)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Device--POST')")
	@ApiOperation(value = "创建设备", notes = "传入设备表参数，创建设备", httpMethod = "POST")
	public ResponseEntity<Map<String, Object>> Device(@RequestBody Device device) {
		Map<String, Object> map = new HashMap<String, Object>();
		DeviceDTO de=new DeviceDTO();
		de.setCe_ip(device.getCe_ip());
		Device findDevice = deviceMapper.findDeviceByIp(de);
		if (findDevice != null) {
			map.put("msg", "链路已存在");
			map.put("error_code", 0);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		device.setCe_id(uuid);
		int i = deviceMapper.addDevice(device);
		if (i > 0) {
			map.put("data", device);
			map.put("msg", "设备添加成功！");
			map.put("error_code", 1);
		} else if (i == 0) {
			map.put("msg", "设备添加失败！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/Device_delete/{uuid}", method = RequestMethod.DELETE)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Device_delete/{uuid}--DELETE')")
	@ApiOperation(value = "删除设备", notes = "传入设备的id，删除此设备", httpMethod = "DELETE")
	public ResponseEntity<Map<String, Object>> Device_delete(@PathVariable String uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		DeviceDTO device = new DeviceDTO();
		device.setCe_id(uuid);
		int i = deviceMapper.deleteDevice(device);
		if (i > 0) {
			map.put("msg", "设备删除成功！");
			map.put("error_code", 1);
		} else if (i == 0) {
			map.put("msg", "设备删除失败！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@DeleteMapping("/Device_deleteByMore")
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Device_deleteByMore--DELETE')")
	@ApiOperation(value = "删除多个设备", httpMethod = "DELETE", notes = "删除多个设备根据uuid数组")
	@ApiParam(name = "ids", value = "参数类型为String[],为设备uuid的数组", required = true)
	public ResponseEntity<Map<String, Object>> Device_deleteByMore(@RequestBody String[] uuids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String uuid : uuids) {
			DeviceDTO device = new DeviceDTO();
			device.setCe_id(uuid);
			deviceMapper.deleteDevice(device);
		}
		map.put("msg", "设备批量删除成功！");
		map.put("error_code", 1);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/Device_putCEName", method = RequestMethod.PUT)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Device_putCEName--PUT')")
	@ApiOperation(value = "修改设备别名", notes = "传入设备id以及新的设备别名，根据id修改设备别名", httpMethod = "PUT")
	public ResponseEntity<Map<String, Object>> Device_putCEName(@RequestBody DeviceDTO device) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = deviceMapper.updateDeviceName(device);
		if (i > 0) {
			map.put("msg", "设备别名修改成功！");
			map.put("error_code", 1);
		} else if (i == 0) {
			map.put("msg", "设备别名修改失败！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Device_putSSHPass", method = RequestMethod.PUT)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Device_putSSHPass--PUT')")
	@ApiOperation(value = "修改设备SSH密码", notes = "传入设备id以及新的SSH密码，根据id修改设备SSH密码", httpMethod = "PUT")
	public ResponseEntity<Map<String, Object>> Device_putSSHPass(@RequestBody DeviceDTO device) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = deviceMapper.updateDeviceSSHPass(device);
		if (i > 0) {
			map.put("msg", "设备SSH密码修改成功！");
			map.put("error_code", 1);
		} else if (i == 0) {
			map.put("msg", "设备SSH密码修改失败！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/Device_get/{ce_id}", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Device_get/{ce_id}--GET')")
	@ApiOperation(value = "获取设备详情", notes = "根据ce_id获取设备详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> Device_get(@PathVariable String ce_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		DeviceDTO deviceDto = new DeviceDTO();
		deviceDto.setCe_id(ce_id);
	    Device device = deviceMapper.getDeviceById(deviceDto);
		if (device.getCe_name() != null && !device.getCe_name().equals("")) {
			map.put("data", device);
			map.put("msg", "设备详情获取成功！");
			map.put("error_code", 1);
		} else if (device.getCe_name() == null && device.getCe_name().equals("")) {
			map.put("msg", "设备详情获取失败或设备不存在！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/Device_getByIP", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Device_getByIP--GET')")
	@ApiOperation(value = "根据IP地址获取设备详情", notes = "根据ce_ip获取设备详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> Device_getByIP(@RequestBody String ce_ip) {
		Map<String, Object> map = new HashMap<String, Object>();
		DeviceDTO deviceDto = new DeviceDTO();
		deviceDto.setCe_ip(ce_ip);
		Device device = deviceMapper.findDeviceByIp(deviceDto);
		if (device.getCe_name() != null && !device.getCe_name().equals("")) {
			map.put("data", device);
			map.put("msg", "设备详情获取成功！");
			map.put("error_code", 1);
		} else if (device.getCe_name() == null && device.getCe_name().equals("")) {
			map.put("msg", "设备详情获取失败或设备不存在！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/Device_getAll", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Device_getAll--GET')")
	@ApiOperation(value = "获取所有设备", notes = "获取所有设备详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> Device_getAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Device> list = deviceMapper.getAllDevice();
		if (list.size() > 0) {
			map.put("data", list);
			map.put("msg", "所有设备详情获取成功！");
			map.put("error_code", 1);
		} else if (list.size() == 0) {
			map.put("msg", "所有设备详情获取失败或设备为空！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/getDeviceByLike", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/getDeviceByLike--GET')")
	@ApiOperation(value = "模糊查询设备", notes = "根据名称模糊查询设备", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> getDeviceByLike(@RequestParam("device_name") String device_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		device_name = device_name.replace(" ", "");
		List<Device> list = deviceMapper.getAllDeviceByLike(device_name);
		if (list.size() > 0) {
			map.put("data", list);
			map.put("msg", "设备获取成功！");
			map.put("error_code", 1);
		} else if (list.size() <= 0) {
			map.put("msg", "设备获取失败或设备不存在！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
