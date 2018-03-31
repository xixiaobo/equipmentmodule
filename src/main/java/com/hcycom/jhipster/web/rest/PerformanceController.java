package com.hcycom.jhipster.web.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import com.hcycom.jhipster.domain.Device;
import com.hcycom.jhipster.domain.Performance;
import com.hcycom.jhipster.service.DTO.DeviceDTO;
import com.hcycom.jhipster.service.mapper.DeviceMapper;
import com.hcycom.jhipster.service.mapper.PerformanceMapper;
import com.hcycom.jhipster.web.rest.tools.HttpUtil;
import com.hcycom.jhipster.web.rest.tools.TimeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = { "性能参数管理" }, description = "性能参数管理接口")
public class PerformanceController {
	private final Logger log = LoggerFactory.getLogger(PerformanceController.class);

	@Autowired
	private DeviceMapper deviceMapper;

	@Autowired
	private PerformanceMapper performanceMapper;

	@Value("${pythonWeb}")
	String pythonWeb;

	public final static long SECOND = 1 * 1000;

	@Scheduled(fixedRate = SECOND * 60 * 5)
	public void addPerformance() {

		log.info("-----性能参数定时更新开始------");
		List<Device> devices = deviceMapper.getAllDevice();
		devices.stream().forEach(device -> {
			try {

				JSONObject json = new JSONObject();
				json.put("ce_ip", device.getCe_ip());
				json.put("ssh_username", device.getSsh_username());
				json.put("ssh_password", device.getSsh_password());
				json.put("os", device.getOs());
				json.put("ce_port", Integer.parseInt(device.getCe_port()));
				String re = HttpUtil.sendHttpPostJson(pythonWeb + "api/rest/StatusHost", json.toJSONString());
				JSONObject jsonObject = JSON.parseObject(re);
				int error_code = jsonObject.getInteger("error_code");
				if (error_code == 0) {
					DeviceDTO deviceDTO = new DeviceDTO();
					deviceDTO.setCe_id(device.getCe_id());
					deviceDTO.setStatus(0);
					device.setStatus(0);
					deviceMapper.updateDeviceStatus(deviceDTO);
				}
				Performance performance = new Performance();
				performance.setCe_id(device.getCe_id());
				if (device.getStatus() != 0) {
					String re2 = HttpUtil.sendHttpPostJson(pythonWeb + "api/rest/SysstatHost", json.toJSONString());
					JSONObject jsonObject2 = JSON.parseObject(re2);
					error_code = jsonObject2.getInteger("error_code");
					if (error_code == 1) {
						jsonObject.getJSONObject("data").put("test_time", jsonObject.getJSONObject("data").getString("endtime"));
						jsonObject.getJSONObject("data").put("ce_id", device.getCe_id());
						performance=jsonObject.getJSONObject("data").toJavaObject(Performance.class);
						performanceMapper.addPerformance(performance);
					} else {
						Date newTime = new Date();
						String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newTime);
						performance.setTest_time(newDate);
						performanceMapper.addPerformance(performance);
					}

				} else {
					Date newTime = new Date();
					String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newTime);
					performance.setTest_time(newDate);
					performanceMapper.addPerformance(performance);
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.error("自动添加出错！\n\t错误设备id："+device.getCe_id()+"\n\t"+e.getMessage());
			}

		});
		
		log.info("-----性能参数定时更新结束------");

	}

	@RequestMapping(value = "/Performance_get", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Performance_get--GET')")
	@ApiOperation(value = "获取性能参数详情", notes = "根据ce_id获取性能参数详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> Performance_get(@RequestBody Map<String, String> reqMap) {
		log.info("requestMap:\n" + reqMap);
		Map<String, Object> map = new HashMap<String, Object>();
		String ce_id = reqMap.getOrDefault("ce_id", "");
		Date newTime = new Date();
		String newDate = new SimpleDateFormat("yyyy-MM-dd").format(newTime);
		String oldDate=TimeUtil.getTimeByMinute(-30);
		String beginTime = reqMap.getOrDefault("beginTime", newDate);
		String endTime = reqMap.getOrDefault("endTime", oldDate);
		List<Performance> list=performanceMapper.findPerformanceByIDAndTime(ce_id, beginTime, endTime);
		if (list.size() > 0) {
			map.put("data", list);
			map.put("msg", "时延详情获取成功！");
			map.put("error_code", 1);
		} else if (list.size() == 0) {
			map.put("msg", "时延详情获取失败或时延不存在！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
