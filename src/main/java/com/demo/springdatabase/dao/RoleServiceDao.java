package com.demo.springdatabase.dao;

import java.util.List;

import com.demo.springdatabase.beans.Role;

public interface RoleServiceDao {

	List<Role> getRoles();

	boolean addRole(Role role);

	boolean updateRoleName(Role role);

	boolean deleteRole(Role role);

}
