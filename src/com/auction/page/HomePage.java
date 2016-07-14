package com.auction.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.auction.page.ManageKind.ManageKindPage;

public class HomePage extends BasePage {
	private Logger logger = Logger.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
		logger.debug("running test in ��HomePage��");
	}

	/**
	 * �����¼ҳ��
	 * 
	 * @return
	 */
	public LoginPage goLoginPage() {
		driver.click(By.partialLinkText("��¼"));
		return new LoginPage(webDriver);
	}

	/**
	 * �����������ҳ��
	 * 
	 * @return
	 */
	public ManageKindPage goManageKindPage() {
		driver.click(By.partialLinkText("��������"));
		return new ManageKindPage(webDriver);
	}

	/**
	 * �˳���¼�����¼ҳ��
	 * 
	 * @return
	 */
	public LoginPage logout() {
		driver.click(By.partialLinkText("ע��"));
		return new LoginPage(webDriver);
	}
}
