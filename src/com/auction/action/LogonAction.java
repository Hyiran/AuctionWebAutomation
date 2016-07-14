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
	 * µÇÂ¼³É¹¦£¬½øÈëÖ÷Ò³
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
	 * ÍË³öµÇÂ¼£¬·µ»ØÖ÷Ò³
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
	 * ÓÃ»§Ãû´íÎó£¬µÇÂ¼Ê§°Ü£¬·µ»ØÖ÷Ò³
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
	 * ÃÜÂë´íÎó£¬µÇÂ¼Ê§°Ü£¬·µ»ØÖ÷Ò³
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
	 * ÑéÖ¤Âë´íÎó£¬µÇÂ¼Ê§°Ü£¬·µ»ØÖ÷Ò³
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
