package com.infosys.irs.utility;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;

@Endpoint
public class InfyGoEndpoint {

	
	public String getId() {
		return "infygoendpoint";
	}

	
	public String invoke() {
		return "This is InfyGo custom endpoint message";
	}

	
	public boolean isEnabled() {
		return true;
	}

	public boolean isSensitive() {
		return false;
	}

}
