package com.auction.action;

import org.openqa.selenium.WebDriver;

import com.auction.page.HomePage;
import com.auction.page.LoginPage;

public class LogonAction {
	private WebDriver driver;
	private LoginPage LoginPage;
	private HomePage HomePage;

	public LogonAction(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * ��¼�ɹ���������ҳ
	 * 
	 * @param userName
	 * @param passWord
	 * @param vercode
	 * @param expectedText
	 * @return
	 */
	public boolean login(String userName, String passWord, String vercode,
			String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		LoginPage = HomePage.goLoginPage();
		HomePage = LoginPage.loginOK(userName, passWord, vercode);
		flag = HomePage.isTextPresent(expectedText);
		return flag;
	}

	/**
	 * �˳���¼��������ҳ
	 * 
	 * @param expectedText
	 * @return
	 */
	public boolean logout(String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		LoginPage = HomePage.logout();
		flag = LoginPage.isTextPresent(expectedText);
		LoginPage.goHomePage();
		return flag;
	}

	/**
	 * �û������󣬵�¼ʧ�ܣ�������ҳ
	 * 
	 * @param userName
	 * @param passWord
	 * @param vercode
	 * @param expectedText
	 * @return
	 */
	public boolean loginByNameErr(String userName, String passWord,
			String vercode, String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		LoginPage = HomePage.goLoginPage();
		LoginPage.loginByNameErr(userName, passWord, vercode);
		flag = LoginPage.isTextPresent(expectedText);
		LoginPage.goHomePage();
		return flag;
	}

	/**
	 * ������󣬵�¼ʧ�ܣ�������ҳ
	 * 
	 * @param userName
	 * @param passWord
	 * @param vercode
	 * @param expectedText
	 * @return
	 */
	public boolean loginByPwdErr(String userName, String passWord,
			String vercode, String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		LoginPage = HomePage.goLoginPage();
		LoginPage.loginByPwdErr(userName, passWord, vercode);
		flag = LoginPage.isTextPresent(expectedText);
		LoginPage.goHomePage();
		return flag;
	}

	/**
	 * ��֤����󣬵�¼ʧ�ܣ�������ҳ
	 * 
	 * @param userName
	 * @param passWord
	 * @param vercode
	 * @param expectedText
	 * @return
	 */
	public boolean loginByVercodeErr(String userName, String passWord,
			String vercode, String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		LoginPage = HomePage.goLoginPage();
		LoginPage.loginByVercodeErr(userName, passWord, vercode);
		flag = LoginPage.isTextPresent(expectedText);
		LoginPage.goHomePage();
		return flag;
	}
}
