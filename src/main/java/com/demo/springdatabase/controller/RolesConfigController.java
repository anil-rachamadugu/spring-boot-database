package com.demo.springdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springdatabase.beans.Endpoints;
import com.demo.springdatabase.beans.Role;
import com.demo.springdatabase.service.RoleService;

@RestController
@RequestMapping(value = Endpoints.ROLES)
public class RolesConfigController {
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private RoleService configurationServiceTool;
	
	
	@GetMapping("/test")
	public void test() {
		System.out.println("TEST ENDPOINT");
	}

	@GetMapping(value = Endpoints.GETROLES)
	public List<Role> getRoles() {
		List<Role> rolesList = new ArrayList<Role>();
		rolesList = configurationServiceTool.getRoles();

		return rolesList;
	}
	
	@PostMapping(value = Endpoints.ADDROLE)
	public boolean addRole(@RequestBody Role role) {
		System.out.println("Request details - " + role.getName() + " id = " + role.getId());
		boolean isRoleAdded = configurationServiceTool.addRole(role);
		logger.info("isRoleAdded = " + isRoleAdded);
		return isRoleAdded ;
	}
	
	@PostMapping(value = Endpoints.DELETEROLE)
	public boolean deleteRole(@RequestBody Role role) {
		boolean isRoleDeleted = configurationServiceTool.deleteRole(role);
		logger.info("isRoleDeleted = " + isRoleDeleted);
		return isRoleDeleted;
	}
	
	@PostMapping(value = Endpoints.UPDATEROLE)
	public boolean updateRoleName(@RequestBody Role role) {
		boolean isRoleUpdated = configurationServiceTool.updateRoleName(role);
		logger.info("isRoleUpdated = " + isRoleUpdated);
		return isRoleUpdated;
	}
}
