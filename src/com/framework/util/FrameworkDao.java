package com.framework.util;

import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FrameworkDao {
	private static SqlMapClient sqlMapClient;
	private static Logger logger = Logger.getLogger(FrameworkDao.class);

	/**
	 * 获取满足指定条件的对象。
	 * 
	 * @param statment
	 *            要执行的statment
	 * @param object
	 *            执行statment的条件参数
	 * 
	 * @return 满足条件的单个对象
	 */
	public static Object getObjectByParam(String statment, Object object) {
		getSqlMapClient();
		try {
			return sqlMapClient.queryForObject(statment, object);
		} catch (Exception e) {
			logger.error("执行FrameworkDao.getObjectByParam()方法发生异常，异常信息：", e);
		}
		return null;
	}

	/**
	 * 随机选择对象集合中的某个对象。
	 * 
	 * @param statment
	 *            要执行的statment
	 * @param object
	 *            执行statment的条件参数
	 * 
	 * @return 随机选取的满足条件的对象集合中的单个对象
	 */
	public static Object getRandomObjectByParam(String statment, Object object) {
		List list = getObjectListByParam(statment, object);
		int listSize = list.size();
		int randomNum = RandomUtil.getRandomNumber(0, listSize);
		return listSize == 0 ? null : list.get(randomNum);
	}

	/**
	 * 获取满足条件的所有对象集合。
	 * 
	 * @param statment
	 *            要执行的statment
	 * @param object
	 *            执行statment的条件参数
	 * 
	 * @return 满足条件的所有对象集合
	 */
	public static List getObjectListByParam(String statment, Object object) {
		getSqlMapClient();
		try {
			return sqlMapClient.queryForList(statment, object);
		} catch (Exception e) {
			logger.error("执行FrameworkDao.getObjectListByParam()方法发生异常，异常信息：", e);
		}
		return null;
	}

	/**
	 * 获取所有的对象集合。
	 * 
	 * @param statment
	 *            要执行的statment
	 * 
	 * @return 所有对象集合
	 */
	public static List getAllObjects(String statment) {
		getSqlMapClient();
		try {
			return sqlMapClient.queryForList(statment);
		} catch (Exception e) {
			logger.error("执行FrameworkDao.getAllObjects()方法发生异常，异常信息：", e);
		}
		return null;
	}

	/**
	 * 获取SqlMapClient对象。
	 * 
	 * @param 无
	 * 
	 * @return SqlMapClient对象
	 */
	public static SqlMapClient getSqlMapClient() {
		sqlMapClient = SqlMapper.getSqlMapClient();
		return sqlMapClient;
	}
}
