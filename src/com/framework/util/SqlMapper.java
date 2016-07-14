package com.framework.util;

import java.io.Reader;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapper {
	private static SqlMapClient sqlMapClient;
	private static Logger logger = Logger.getLogger(SqlMapper.class);

	/**
	 * 创建SqlMapClient实例，SqlMapClient实例是Ibatis框架的核心。
	 */
	static {
		try {
			Reader reader = Resources
					.getResourceAsReader("config/SqlMapConfig.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (Exception e) {
			logger.error("创建SqlMapClient instance发生异常，异常信息：", e);
		}
	}

	/**
	 * 获得SqlMapClient实例。
	 * 
	 * @return SqlMapClient
	 */
	public static SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
}
