package com.demo.springdatabase.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {

	@Autowired
	private Environment env;

//	@ConfigurationProperties(prefix = "datasource.config")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}

	@Primary
	@Bean
	@Qualifier("mySqlJdbcTemplate")
	public DataSource mysqlDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getProperty("datasource.config.driverClassName"));
		dataSource.setUrl(env.getProperty("datasource.config.url"));
		dataSource.setUsername(env.getProperty("datasource.config.username"));
		dataSource.setPassword(env.getProperty("datasource.config.password"));
		dataSource.setInitialSize(Integer.valueOf(env.getProperty("datasource.config.initialSize")));
		dataSource.setMaxActive(Integer.valueOf(env.getProperty("datasource.config.maxActive")));
		dataSource.setMinIdle(Integer.valueOf(env.getProperty("datasource.config.minIdle")));
		dataSource.setMaxIdle(Integer.valueOf(env.getProperty("datasource.config.maxIdle")));
		dataSource.setValidationQuery(env.getProperty("datasource.config.validationQuery"));

		return dataSource;
	}

	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
