package com.demo.springdatabase.service;

import java.util.List;

import com.demo.springdatabase.beans.Role;

public interface RoleService {
	
	List<Role> getRoles();

	boolean addRole(Role role);

	boolean deleteRole(Role role);

	boolean updateRoleName(Role role);

}
