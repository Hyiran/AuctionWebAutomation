package com.framework.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class DbUnitUtil {
	private static Connection conn = null;
	private static String dbDriver = null;
	private static String dbConnectionURL = null;
	private static String dbUsername = null;
	private static String dbPassword = null;
	protected IDatabaseConnection connection;
	private static PropUtil PropUtil = new PropUtil("config/jdbc.properties");
	private Logger logger = Logger.getLogger(DbUnitUtil.class);

	static {
		dbDriver = PropUtil.get("Driver");
		dbConnectionURL = PropUtil.get("ConnectionURL");
		dbUsername = PropUtil.get("Username");
		dbPassword = PropUtil.get("Password");
	}

	/**
	 * 
	 * @param tableName
	 *            需操作的数据库表名
	 */
	public void setUp() {
		connection = getDbUnitConnection();
	}

	public void tearDown() {
		closeJDBCConnection();
	}

	/**
	 * 功能：获取数据库连接
	 */
	public Connection getJDBCConnection() {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbConnectionURL, dbUsername,
					dbPassword);
		} catch (Exception e) {
			logger.error("执行DbTestCase.getJDBCConnection()方法发生异常，异常信息：", e);
		}
		if (conn == null) {
			logger.error("Error: DbTestCase.getJDBCConnection() 获得数据库链接失败.\r\n链接类型:"
					+ dbDriver
					+ "\r\n链接URL:"
					+ dbConnectionURL
					+ "\r\n链接用户:"
					+ dbUsername + "\r\n链接密码:" + dbPassword);
		}
		return conn;
	}

	/**
	 * 
	 * @param tableName
	 *            需操作的数据库表名
	 */
	public void closeJDBCConnection() {
		try {
			connection.close();
			conn.close();
		} catch (Exception e) {
			logger.error("执行DbTestCase.closeJDBCConnection()方法发生异常，异常信息：", e);
		}
	}

	/**
	 * This method returns a DbUnit database connection based on the schema name
	 */
	public IDatabaseConnection getDbUnitConnection() {
		IDatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection(getJDBCConnection());
		} catch (Exception e) {
			logger.error("执行DbTestCase.getDbUnitConnection()方法发生异常，异常信息：", e);
		}
		return connection;
	}

	public IDataSet getFlatXmlDataSet(String tableName) {
		IDataSet dataset = null;
		InputStream is = getClass().getClassLoader().getResourceAsStream(
				"dataset/" + tableName + ".xml");
		// return new FlatXmlDataSet(is);
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		builder.setColumnSensing(false);
		try {
			dataset = builder.build(is);
		} catch (Exception e) {
			logger.error("执行DbTestCase.getFlatXmlDataSet()方法发生异常，异常信息：", e);
		}
		return dataset;
	}

	/**
	 * This method inserts the contents of a FlatXmlDataSet file into the
	 * connection
	 */
	public void insertFileIntoDb(String tableName) {
		try {
			DatabaseOperation.CLEAN_INSERT.execute(connection,
					getFlatXmlDataSet(tableName));
		} catch (Exception e) {
			logger.error("执行DbTestCase.insertFileIntoDb()方法发生异常，异常信息：", e);
		}
	}

	/** Empty a table */
	public void emptyTable(String tableName) {
		try {
			DatabaseOperation.DELETE_ALL.execute(connection,
					getFlatXmlDataSet(tableName));
		} catch (Exception e) {
			logger.error("执行DbTestCase.emptyTable()方法发生异常，异常信息：", e);
		}
	}

	public static void main(String[] args) {
		DbUnitUtil db = new DbUnitUtil();
		db.setUp();
		db.insertFileIntoDb("tb_UserInfo");
		db.tearDown();
	}
}
