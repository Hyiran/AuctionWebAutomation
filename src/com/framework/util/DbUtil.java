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
	 * ���ܣ���ȡ���ݿ�����
	 */
	public static Connection getConnection() {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbConnectionURL, dbUsername,
					dbPassword);
		} catch (Exception e) {
			logger.error("ִ��DbUtil.getConnection()���������쳣���쳣��Ϣ��", e);
		}
		if (conn == null) {
			logger.error("Error: DbUtil.getConnection() ������ݿ�����ʧ��.\r\n��������:"
					+ dbDriver + "\r\n����URL:" + dbConnectionURL + "\r\n�����û�:"
					+ dbUsername + "\r\n��������:" + dbPassword);
		}
		return conn;
	}

	/**
	 * ���ܣ�ִ�в�ѯ���
	 */
	public static ResultSet executeQuery(String sql) {
		getConnection();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			logger.error("ִ��DbUtil.executeQuery()���������쳣���쳣��Ϣ��", e);
		}
		return rs;
	}

	/**
	 * ���ܣ�ִ�в�ѯ��䣬��ȡ��¼��
	 */
	public static int getRecordCount(String sql) {
		int counter = 0;
		rs = executeQuery(sql);
		try {
			while (rs.next()) {
				counter++;
			}
		} catch (SQLException e) {
			logger.error("ִ��DbUtil.getRecordCount()���������쳣���쳣��Ϣ��", e);
		}
		close();
		return counter;
	}

	/**
	 * ����:ִ�и��²���
	 */
	public static int executeUpdate(String sql) {
		int result = 0;
		getConnection();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			logger.error("ִ��DbUtil.executeUpdate()���������쳣���쳣��Ϣ��", e);
		}
		close();
		return result;
	}

	/**
	 * ����:�ر����ݿ������
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
			logger.error("ִ��DbUtil.close()���������쳣���쳣��Ϣ��", e);
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
