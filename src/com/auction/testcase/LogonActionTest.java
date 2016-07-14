package com.auction.testcase;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.auction.action.LogonAction;
import com.auction.bean.UserInfo;
import com.framework.util.DbUnitUtil;
import com.framework.util.FrameworkDao;
import com.framework.webdriver.baseapi.WebdriverBaseApi;
import com.framework.webdriver.basecase.WebdriverBaseCase;

@Test(groups = { "LogonActionTest" }, description = "登录退出")
public class LogonActionTest extends WebdriverBaseCase {
	private DbUnitUtil DbUnitUtil;
	private UserInfo UserInfo;
	private LogonAction LogonAction;
	protected WebdriverBaseApi webdriver;
	private Logger logger = Logger.getLogger(LogonActionTest.class);

	@BeforeClass(alwaysRun = true)
	public void beforeClassTest() {
		try {
			beforeClass();
			DbUnitUtil = new DbUnitUtil();
			DbUnitUtil.setUp();
			DbUnitUtil.insertFileIntoDb("tb_UserInfo");
			LogonAction = new LogonAction(driver);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("beforeClassTest error：", e);
		}

	}

	@Test(enabled = true, alwaysRun = true, description = "登录")
	public void testLogin() {
		boolean flag = false;
		try {
			beforeTest("testLogin");
			UserInfo = (UserInfo) FrameworkDao.getRandomObjectByParam(
					"UserInfo.getUserByParam", "testLogin");
			flag = LogonAction.login(UserInfo.getUsername(),
					UserInfo.getPassword(), UserInfo.getVercode(),
					"Java EE的框架程序");
		} catch (Exception e) {
			logger.error("testLogin error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testLogin", flag);
		Assert.assertTrue(flag, className + ".testLogin failed!capture:"
				+ captureName);
	}

	@Test(dependsOnMethods = { "testLogin" }, enabled = true, alwaysRun = true, description = "退出")
	public void testLogout() {
		boolean flag = false;
		try {
			beforeTest("testLogout");
			flag = LogonAction.logout("请输入用户名和密码登录系统");
		} catch (Exception e) {
			logger.error("testLogout error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testLogout", flag);
		Assert.assertTrue(flag, className + ".testLogout failed!capture:"
				+ captureName);
	}

	@Test(dependsOnMethods = { "testLogout" }, enabled = true, alwaysRun = true, description = "用户名错误，登录失败")
	public void testLoginByNameErr() {
		boolean flag = false;
		try {
			beforeTest("testLoginByNameErr");
			flag = LogonAction.loginByNameErr("mysql22", "mysql", "666888",
					"用户名/密码不匹配");
		} catch (Exception e) {
			logger.error("testLoginByNameErr error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testLoginByNameErr", flag);
		Assert.assertTrue(flag, className
				+ ".testLoginByNameErr failed!capture:" + captureName);
	}

	@Test(dependsOnMethods = { "testLoginByNameErr" }, enabled = true, alwaysRun = true, description = "密码错误，登录失败")
	public void testLoginByPwdErr() {
		boolean flag = false;
		try {
			beforeTest("testLoginByPwdErr");
			flag = LogonAction.loginByPwdErr("mysql", "mysql22", "666888",
					"用户名/密码不匹配");
		} catch (Exception e) {
			logger.error("testLoginByPwdErr error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testLoginByPwdErr", flag);
		Assert.assertTrue(flag, className
				+ ".testLoginByPwdErr failed!capture:" + captureName);
	}

	@Test(dependsOnMethods = { "testLoginByPwdErr" }, enabled = true, alwaysRun = true, description = "验证码错误，登录失败")
	public void testLoginByVercodeErr() {
		boolean flag = false;
		try {
			beforeTest("testLoginByVercodeErr");
			flag = LogonAction.loginByVercodeErr("mysql", "mysql", "666889",
					"验证码不匹配");
		} catch (Exception e) {
			logger.error("testLoginByVercodeErr error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testLoginByVercodeErr", flag);
		Assert.assertTrue(flag, className
				+ ".testLoginByVercodeErr failed!capture:" + captureName);
	}

	@AfterClass(alwaysRun = true)
	public void afterClassTest() {
		try {
			afterClass();
		} catch (Exception e) {
			logger.error("afterClassTest error：", e);
		}

	}
}
