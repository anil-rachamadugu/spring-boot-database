package com.demo.springdatabase.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springdatabase.beans.Role;
import com.demo.springdatabase.dao.RoleServiceDao;
import com.demo.springdatabase.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private RoleServiceDao roleServiceDao;

	@Override
	public List<Role> getRoles() {
		return roleServiceDao.getRoles();
	}

	@Override
	public boolean addRole(Role role) {
		System.out.println("In service impl class");
		return roleServiceDao.addRole(role);
	}

	@Override
	public boolean deleteRole(Role role) {
		return roleServiceDao.deleteRole(role);
	}

	@Override
	public boolean updateRoleName(Role role) {
		return roleServiceDao.updateRoleName(role);
	}


}
