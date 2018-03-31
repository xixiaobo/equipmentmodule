package com.hcycom.jhipster.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hcycom.jhipster.domain.Delay;
import com.hcycom.jhipster.domain.Link;
import com.hcycom.jhipster.service.mapper.DelayMapper;
import com.hcycom.jhipster.service.mapper.DeviceMapper;
import com.hcycom.jhipster.service.mapper.LinkMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = { "时延管理" }, description = "时延管理接口")
public class DelayController {
	private final Logger log = LoggerFactory.getLogger(DelayController.class);

	@Autowired
	private DelayMapper delayMapper;

	@Autowired
	private LinkMapper linkMapper;

	@Autowired
	private DeviceMapper deviceMapper;

	public final static long SECOND = 1 * 1000;

	@Scheduled(fixedRate = SECOND * 60 * 10)
	public void addDelay() {

//		List<Device> devices = deviceMapper.getAllDevice();
//		devices.stream().forEach(device -> {
//
//			if (device.getStatus() != 3) {
//				JSONObject json = new JSONObject();
//				json.put("ce_ip", "47.94.147.210");
//				json.put("ssh_username", "root");
//				json.put("ssh_password", "Xxb19950213");
//				json.put("os_type", "linux");
//				json.put("ce_port", 22);
//				String re = HttpUtil.sendHttpPostJson("http://47.94.147.210:10240/api/rest/StatusHost",
//						json.toJSONString());
//				JSONObject jsonObject = JSON.parseObject(re);
//				int error_code = jsonObject.getInteger("error_code");
//				DeviceDTO deviceDTO = new DeviceDTO();
//				deviceDTO.setCe_id(device.getCe_id());
//				Date newTime = new Date();
//				String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newTime);
//				if (error_code == 1) {
//					boolean flag = jsonObject.getJSONObject("data").getBoolean("flag");
//					if (flag) {
//						if (device.getStatus() == 0) {
//							device.setStatus(1);
//							deviceMapper.updateDeviceStatus(deviceDTO);
//						}
//						List<LinkDelayDTO> dtos = linkMapper.getLinkDevice(device.getCe_id());
//						dtos.forEach(linkDelayDTO -> {
//							if (!linkDelayDTO.getLink_status().equals("3")) {
//								json.put("ping", linkDelayDTO.getDst_port_ip());
//								String re2 = HttpUtil.sendHttpPostJson("http://47.94.147.210:10240/api/rest/StatusHost",
//										json.toJSONString());
//								JSONObject jsonObject2 = JSON.parseObject(re2);
//								if (error_code == 1) {
//
//								}
//							}
//
//						});
//					} else {
//						device.setStatus(0);
//						deviceMapper.updateDeviceStatus(deviceDTO);
//						List<LinkDelayDTO> dtos = linkMapper.getLinkDevice(device.getCe_id());
//						dtos.forEach(dto -> {
//
//						});
//					}
//				} else {
//					if (device.getStatus() != 3) {
//						device.setStatus(0);
//						deviceMapper.updateDeviceStatus(deviceDTO);
//						List<LinkDelayDTO> dtos = linkMapper.getLinkDevice(device.getCe_id());
//						dtos.forEach(dto -> {
//
//						});
//					}
//				}
//			}
//
//		});

		log.info("---------------------------\n\n定时测试");

	}

	@RequestMapping(value = "/Delay_get", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Delay_get--GET')")
	@ApiOperation(value = "获取时延详情", notes = "根据ce_id获取时延详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> Delay_get(@RequestBody Map<String, String> reqMap) {
		log.info("requestMap:\n" + reqMap);
		Map<String, Object> map = new HashMap<String, Object>();
		Delay delay = new Delay();
		delay.setVll_link_id(reqMap.getOrDefault("vll_link_id", ""));
		Link link = new Link();
		link.setVll_link_id(reqMap.getOrDefault("vll_link_id", ""));
		link = linkMapper.getLinkById(link);
		if (link.getLast_test_time() == null) {
			map.put("msg", "时延详情获取失败或时延不存在！");
			map.put("error_code", 0);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		String beginTime = reqMap.getOrDefault("beginTime", link.getLast_test_time());
		String endTime = reqMap.getOrDefault("endTime", link.getLast_test_time());
		List<Delay> list = delayMapper.findDelayByIDAndTime(delay, beginTime, endTime);
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
