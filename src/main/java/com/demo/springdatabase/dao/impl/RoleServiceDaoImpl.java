package com.demo.springdatabase.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.demo.springdatabase.beans.Role;
import com.demo.springdatabase.dao.RoleServiceDao;

@Repository
public class RoleServiceDaoImpl implements RoleServiceDao {

private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private NamedParameterJdbcTemplate nJdbcTemplate;

	@Override
	public List<Role> getRoles() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id AS id, name as name ");
		sql.append("FROM roles ");
		sql.append("ORDER BY created_at DESC ");

		List<Role> list = nJdbcTemplate.query(sql.toString(), new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet rs, int arg1) throws SQLException {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Role role = new Role(id, name);

				return role;
			}
		});
		return list;
	}
	
	@Override
	public boolean addRole(Role role) {
		boolean isRoleAdded = false;
		StringBuilder roleSql = new StringBuilder();
		roleSql.append("SELECT id FROM roles ");
		roleSql.append("WHERE name= :name ;");
		
		MapSqlParameterSource paramsMap = new MapSqlParameterSource();
		paramsMap.addValue("name", role.getName());
		if(role.getName() != null) {
			int id = nJdbcTemplate.query(roleSql.toString(), paramsMap, new ResultSetExtractor<Integer>() {
				@Override
				public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
					int id = 0;
					if (rs.next()) {
						id = rs.getInt("id");
					}
						return id;
				}
			});
			try {
				if(id == 0) {
					System.out.println("IN IF condition..");
					roleSql = new StringBuilder();
					roleSql.append("INSERT INTO roles(name) ");
					roleSql.append("VALUES(:name) ;");
					
					GeneratedKeyHolder txnHolder = new GeneratedKeyHolder();
					int rowsAdded = nJdbcTemplate.update(roleSql.toString(), paramsMap, txnHolder);
					if(rowsAdded > 0) {
						int rowId = (txnHolder.getKey()).intValue();
						logger.info("Added new role in role id = " + rowId);
						isRoleAdded = true;
					}
				}
				else {
					logger.info("This role is Already present at id = " + id);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return isRoleAdded;
	}

	@Override
	public boolean deleteRole(Role role) {
		boolean isRoleDeleted = false;
		if(role.getId() != null  || role.getName() != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM roles ");
			sql.append("WHERE name =:name OR id = :id ; ");
			MapSqlParameterSource map = new MapSqlParameterSource();
			map.addValue("name", role.getName());
			map.addValue("id", role.getId());
			
			int rowsDeleted = nJdbcTemplate.update(sql.toString(), map);
			logger.info("Rows deleted - " + rowsDeleted);
			if(rowsDeleted > 0)
				isRoleDeleted = true;
		}
		return isRoleDeleted;
	}

	@Override
	public boolean updateRoleName(Role role) {
		boolean isRoleUpdated = false;
		if(role.getName() != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE roles SET ");
			sql.append("name = :name WHERE id = :id ;");
			MapSqlParameterSource map = new MapSqlParameterSource();
			map.addValue("name", role.getName());
			map.addValue("id", role.getId());
			
			int rowsUpdated = nJdbcTemplate.update(sql.toString(), map);
			logger.info("Rows updated - " + rowsUpdated);
			if(rowsUpdated > 0)
				isRoleUpdated = true;
		}
	
		return isRoleUpdated;
	}
}
