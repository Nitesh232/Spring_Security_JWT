package com.nitesh.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springboot.entity.Role;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
	
	/*
	 * @Autowired private Role role;
	 */

	@GetMapping("/msg")
	public String getMsg() {
		return "Welcome to Spring Security with JWT Authentication";
	}
	
	
	@GetMapping("/demoadmin")
	public String getAdminMsg() {
		return "This method can be accessed by admins.";
	}

	
	@GetMapping("/demouser")
	public String getUserMsg() {
		return "This method can be accessed by Users.";
	}
}
