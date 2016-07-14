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
	 *            ����������ݿ����
	 */
	public void setUp() {
		connection = getDbUnitConnection();
	}

	public void tearDown() {
		closeJDBCConnection();
	}

	/**
	 * ���ܣ���ȡ���ݿ�����
	 */
	public Connection getJDBCConnection() {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbConnectionURL, dbUsername,
					dbPassword);
		} catch (Exception e) {
			logger.error("ִ��DbTestCase.getJDBCConnection()���������쳣���쳣��Ϣ��", e);
		}
		if (conn == null) {
			logger.error("Error: DbTestCase.getJDBCConnection() ������ݿ�����ʧ��.\r\n��������:"
					+ dbDriver
					+ "\r\n����URL:"
					+ dbConnectionURL
					+ "\r\n�����û�:"
					+ dbUsername + "\r\n��������:" + dbPassword);
		}
		return conn;
	}

	/**
	 * 
	 * @param tableName
	 *            ����������ݿ����
	 */
	public void closeJDBCConnection() {
		try {
			connection.close();
			conn.close();
		} catch (Exception e) {
			logger.error("ִ��DbTestCase.closeJDBCConnection()���������쳣���쳣��Ϣ��", e);
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
			logger.error("ִ��DbTestCase.getDbUnitConnection()���������쳣���쳣��Ϣ��", e);
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
			logger.error("ִ��DbTestCase.getFlatXmlDataSet()���������쳣���쳣��Ϣ��", e);
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
			logger.error("ִ��DbTestCase.insertFileIntoDb()���������쳣���쳣��Ϣ��", e);
		}
	}

	/** Empty a table */
	public void emptyTable(String tableName) {
		try {
			DatabaseOperation.DELETE_ALL.execute(connection,
					getFlatXmlDataSet(tableName));
		} catch (Exception e) {
			logger.error("ִ��DbTestCase.emptyTable()���������쳣���쳣��Ϣ��", e);
		}
	}

	public static void main(String[] args) {
		DbUnitUtil db = new DbUnitUtil();
		db.setUp();
		db.insertFileIntoDb("tb_UserInfo");
		db.tearDown();
	}
}
