package com.auction.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.auction.page.ManageKind.ManageKindPage;

public class HomePage extends BasePage {
	private Logger logger = Logger.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
		logger.debug("running test in 【HomePage】");
	}

	/**
	 * 进入登录页面
	 * 
	 * @return
	 */
	public LoginPage goLoginPage() {
		driver.click(By.partialLinkText("登录"));
		return new LoginPage(webDriver);
	}

	/**
	 * 进入管理种类页面
	 * 
	 * @return
	 */
	public ManageKindPage goManageKindPage() {
		driver.click(By.partialLinkText("管理种类"));
		return new ManageKindPage(webDriver);
	}

	/**
	 * 退出登录进入登录页面
	 * 
	 * @return
	 */
	public LoginPage logout() {
		driver.click(By.partialLinkText("注销"));
		return new LoginPage(webDriver);
	}
}
