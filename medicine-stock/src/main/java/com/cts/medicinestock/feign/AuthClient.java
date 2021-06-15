package com.cts.medicinestock.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "authorizatiion-microservice", url="http://localhost:8400")
public interface AuthClient {
	
	@PostMapping()
	public boolean authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
   
}
