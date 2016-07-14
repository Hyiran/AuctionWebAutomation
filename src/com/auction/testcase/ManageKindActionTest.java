package com.auction.testcase;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.auction.action.LogonAction;
import com.auction.action.ManageKindAction;
import com.framework.util.DateTimeUtil;
import com.framework.webdriver.baseapi.WebdriverBaseApi;
import com.framework.webdriver.basecase.WebdriverBaseCase;

@Test(groups = { "ManageKindActionTest" }, description = "物品种类管理")
public class ManageKindActionTest extends WebdriverBaseCase {
	private LogonAction LogonAction;
	private ManageKindAction ManageKindAction;
	protected WebdriverBaseApi webdriver;
	private Logger logger = Logger.getLogger(ManageKindActionTest.class);

	@BeforeClass(alwaysRun = true)
	public void beforeClassTest() {
		try {
			beforeClass();
			LogonAction = new LogonAction(driver);
			ManageKindAction = new ManageKindAction(driver);
			LogonAction.login("mysql", "mysql", "666888", "Java EE的框架程序");
		} catch (Exception e) {
			logger.error("beforeClassTest error：", e);
		}

	}

	@Test(enabled = true, alwaysRun = true, description = "添加新种类成功")
	public void testAddKindOK() {
		boolean flag = false;
		try {
			beforeTest("testAddKindOK");
			String data = DateTimeUtil.getDateTime();
			flag = ManageKindAction.addKindOK(data, "666888", data);
		} catch (Exception e) {
			logger.error("testAddKindOK error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testAddKindOK", flag);
		Assert.assertTrue(flag, className + ".testAddKindOK failed!capture:"
				+ captureName);
	}

	@Test(dependsOnMethods = { "testAddKindOK" }, enabled = true, alwaysRun = true, description = "添加新种类，种类名为空")
	public void testAddKindByKindNameEmpty() {
		boolean flag = false;
		try {
			beforeTest("testAddKindByKindNameEmpty");
			String data = DateTimeUtil.getDateTime();
			flag = ManageKindAction.addKindByKindNameEmpty(data, "666888",
					"种类名必填");
		} catch (Exception e) {
			logger.error("testAddKindByKindNameEmpty error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testAddKindByKindNameEmpty", flag);
		Assert.assertTrue(flag, className
				+ ".testAddKindByKindNameEmpty failed!capture:" + captureName);
	}

	@Test(dependsOnMethods = { "testAddKindByKindNameEmpty" }, enabled = true, alwaysRun = true, description = "添加新种类，验证码错误")
	public void testAddKindByVercodeErr() {
		boolean flag = false;
		try {
			beforeTest("testAddKindByVercodeErr");
			String data = DateTimeUtil.getDateTime();
			flag = ManageKindAction.addKindByVercodeErr(data, "666889",
					"验证码不匹配,请重新输入");
		} catch (Exception e) {
			logger.error("testAddKindByVercodeErr error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testAddKindByVercodeErr", flag);
		Assert.assertTrue(flag, className
				+ ".testAddKindByVercodeErr failed!capture:" + captureName);
	}

	@Test(dependsOnMethods = { "testAddKindByVercodeErr" }, enabled = true, alwaysRun = true, description = "添加新种类，验证码为空")
	public void testAddKindByVercodeEmpty() {
		boolean flag = false;
		try {
			beforeTest("testAddKindByVercodeEmpty");
			String data = DateTimeUtil.getDateTime();
			flag = ManageKindAction.addKindByVercodeEmpty(data, "验证码必填");
		} catch (Exception e) {
			logger.error("testAddKindByVercodeEmpty error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testAddKindByVercodeEmpty", flag);
		Assert.assertTrue(flag, className
				+ ".testAddKindByVercodeEmpty failed!capture:" + captureName);
	}

	@Test(dependsOnMethods = { "testAddKindByVercodeEmpty" }, enabled = true, alwaysRun = true, description = "删除已添加种类")
	public void testDelKind() {
		boolean flag = false;
		try {
			beforeTest("testDelKind");
			String data = DateTimeUtil.getDateTime();
			flag = ManageKindAction.delKind(data, "666888", data);
		} catch (Exception e) {
			logger.error("testDelKind error：", e);
		}
		logger.debug("flag==" + flag);
		String captureName = afterTest("testDelKind", flag);
		Assert.assertTrue(flag, className + ".testDelKind failed!capture:"
				+ captureName);
	}

	@AfterClass(alwaysRun = true)
	public void afterClassTest() {
		try {
			LogonAction.logout("请输入用户名和密码登录系统");
			afterClass();
		} catch (Exception e) {
			logger.error("afterClassTest error：", e);
		}

	}
}
