package com.hcycom.jhipster.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.hcycom.jhipster.domain.Link;
import com.hcycom.jhipster.service.mapper.LinkMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@Api(tags = { "链路管理" }, description = "链路管理接口")
public class LinkController {

	@Autowired
	private LinkMapper linkMapper;

	@RequestMapping(value = "/Link", method = RequestMethod.POST)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Link--POST')")
	@ApiOperation(value = "创建链路", notes = "传入链路表参数，创建链路", httpMethod = "POST")
	public ResponseEntity<Map<String, Object>> Link(@RequestBody Link link) {
		Map<String, Object> map = new HashMap<String, Object>();
		Link findVll = linkMapper.getLinkById(link);
		if (findVll != null) {
			map.put("msg", "链路已存在");
			map.put("error_code", 0);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		link.setVll_link_id(uuid);
		int i = linkMapper.addLink(link);
		if (i > 0) {
			map.put("data", link);
			map.put("msg", "链路添加成功！");
			map.put("error_code", 1);
		} else if (i == 0) {
			map.put("msg", "链路添加失败！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/Link_delete/{uuid}", method = RequestMethod.DELETE)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Link_delete/{uuid}--DELETE')")
	@ApiOperation(value = "删除链路", notes = "传入链路的id，删除此链路", httpMethod = "DELETE")
	public ResponseEntity<Map<String, Object>> Link_delete(@PathVariable String uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Link link = new Link();
		link.setVll_link_id(uuid);
		int i = linkMapper.deleteLink(link);
		if (i > 0) {
			map.put("msg", "链路删除成功！");
			map.put("error_code", 1);
		} else if (i == 0) {
			map.put("msg", "链路删除失败！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@DeleteMapping("/Link_deleteByMore")
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Link_deleteByMore--DELETE')")
	@ApiOperation(value = "删除多个链路", httpMethod = "DELETE", notes = "删除多个链路根据uuid数组")
	@ApiParam(name = "ids", value = "参数类型为String[],为链路uuid的数组", required = true)
	public ResponseEntity<Map<String, Object>> Link_deleteByMore(@RequestBody String[] uuids) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String uuid : uuids) {
			Link link = new Link();
			link.setVll_link_id(uuid);
			linkMapper.deleteLink(link);
		}
		map.put("msg", "链路批量删除成功！");
		map.put("error_code", 1);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/Link_putLinkName", method = RequestMethod.PUT)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Link_putLinkName--PUT')")
	@ApiOperation(value = "修改链路名称", notes = "传入链路id以及新的链路名称，根据id修改链路名称", httpMethod = "PUT")
	public ResponseEntity<Map<String, Object>> Link_putLinkName(@RequestBody Map<String, String> link) {
		Map<String, Object> map = new HashMap<String, Object>();
		String uuid = link.getOrDefault("vll_link_id", "");
		String vll_link_name = link.getOrDefault("vll_link_name", "");
		Link links = new Link();
		links.setVll_link_id(uuid);
		links.setVll_link_name(vll_link_name);
		int i = linkMapper.updateLinkName(links);
		if (i > 0) {
			map.put("msg", "链路别名修改成功！");
			map.put("error_code", 1);
		} else if (i == 0) {
			map.put("msg", "链路别名修改失败！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/Link_get/{vll_link_id}", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Link_get/{vll_link_id}--GET')")
	@ApiOperation(value = "获取链路详情", notes = "根据vll_link_id获取链路详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> Device_get(@PathVariable String vll_link_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Link link = new Link();
		link.setVll_link_id(vll_link_id);
		link = linkMapper.getLinkById(link);
		if (link.getVll_link_name() != null && !link.getVll_link_name().equals("")) {
			map.put("data", link);
			map.put("msg", "链路详情获取成功！");
			map.put("error_code", 1);
		} else if (link.getVll_link_name() == null && link.getVll_link_name().equals("")) {
			map.put("msg", "链路详情获取失败或链路不存在！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/Link_getAll", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/Link_getAll--GET')")
	@ApiOperation(value = "获取所有链路", notes = "获取所有链路详情", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> Link_getAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Link> list = linkMapper.getAllLink();
		if (list.size() > 0) {
			map.put("data", list);
			map.put("msg", "所有链路详情获取成功！");
			map.put("error_code", 1);
		} else if (list.size() == 0) {
			map.put("msg", "所有链路详情获取失败或链路为空！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/getLinkByLike", method = RequestMethod.GET)
	@Timed
	@PreAuthorize("@InterfacePermissions.hasPermission(authentication, 'equipmentmodule/api/getLinkByLike--GET')")
	@ApiOperation(value = "模糊查询链路", notes = "根据名称模糊查询链路", httpMethod = "GET")
	public ResponseEntity<Map<String, Object>> getLinkByLike(@RequestParam("link_name") String link_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		link_name = link_name.replace(" ", "");
		List<Link> list = linkMapper.getAllLinkByLike(link_name);
		if (list.size() > 0) {
			map.put("data", list);
			map.put("msg", "链路获取成功！");
			map.put("error_code", 1);
		} else if (list.size() <= 0) {
			map.put("msg", "链路获取失败或链路不存在！");
			map.put("error_code", 0);
		} else {
			map.put("msg", "服务器错误！");
			map.put("error_code", 2);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
