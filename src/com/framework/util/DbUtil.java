package com.framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DbUtil {

	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static String dbDriver = null;
	private static String dbConnectionURL = null;
	private static String dbUsername = null;
	private static String dbPassword = null;
	private static PropUtil PropUtil = new PropUtil("config/db.properties");
	private static Logger logger = Logger.getLogger(DbUtil.class);

	static {
		dbDriver = PropUtil.get("Driver");
		dbConnectionURL = PropUtil.get("ConnectionURL");
		dbUsername = PropUtil.get("Username");
		dbPassword = PropUtil.get("Password");
	}

	/**
	 * 功能：获取数据库连接
	 */
	public static Connection getConnection() {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbConnectionURL, dbUsername,
					dbPassword);
		} catch (Exception e) {
			logger.error("执行DbUtil.getConnection()方法发生异常，异常信息：", e);
		}
		if (conn == null) {
			logger.error("Error: DbUtil.getConnection() 获得数据库链接失败.\r\n链接类型:"
					+ dbDriver + "\r\n链接URL:" + dbConnectionURL + "\r\n链接用户:"
					+ dbUsername + "\r\n链接密码:" + dbPassword);
		}
		return conn;
	}

	/**
	 * 功能：执行查询语句
	 */
	public static ResultSet executeQuery(String sql) {
		getConnection();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			logger.error("执行DbUtil.executeQuery()方法发生异常，异常信息：", e);
		}
		return rs;
	}

	/**
	 * 功能：执行查询语句，获取记录数
	 */
	public static int getRecordCount(String sql) {
		int counter = 0;
		rs = executeQuery(sql);
		try {
			while (rs.next()) {
				counter++;
			}
		} catch (SQLException e) {
			logger.error("执行DbUtil.getRecordCount()方法发生异常，异常信息：", e);
		}
		close();
		return counter;
	}

	/**
	 * 功能:执行更新操作
	 */
	public static int executeUpdate(String sql) {
		int result = 0;
		getConnection();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			logger.error("执行DbUtil.executeUpdate()方法发生异常，异常信息：", e);
		}
		close();
		return result;
	}

	/**
	 * 功能:关闭数据库的连接
	 */
	public static void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("执行DbUtil.close()方法发生异常，异常信息：", e);
		}
	}

	public static void main(String[] args) {
		String sql = "select * from icpservices i where i.ServId='019143011001'";
		int num = DbUtil.getRecordCount(sql);
		System.out.println("num = " + num);

		rs = DbUtil.executeQuery(sql);
		try {
			rs.next();
			System.out.println("\r\ncol1 = " + rs.getString(1) + "\r\ncol2 = "
					+ rs.getString(2) + "\r\ncol3 = " + rs.getString(3)
					+ "\r\ncol4 = " + rs.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtil.close();
	}
}
