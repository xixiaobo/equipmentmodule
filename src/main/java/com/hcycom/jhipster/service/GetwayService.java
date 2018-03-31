package com.hcycom.jhipster.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hcycom.jhipster.client.AuthorizedFeignClient;

@AuthorizedFeignClient(name = "jhipstergateway")
public interface GetwayService {
	
	@GetMapping("api/gateway/routes")
	List<Map<String, Object>> getGateway();
	
	@GetMapping("/v2/api-docs")
	String getgatewayDocumentation();

	@GetMapping("/{server}/v2/api-docs")
	String getDocumentation(@PathVariable("server") String server);
}
