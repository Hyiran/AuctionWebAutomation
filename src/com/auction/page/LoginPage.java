package com.auction.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
	private Logger logger = Logger.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		super(driver);
		logger.debug("running test in ��LoginPage��");
	}

	public void inputUserName(String userName) {
		driver.sendKeys(By.name("username"), userName);
	}

	public void inputPassWord(String passWord) {
		driver.sendKeys(By.name("password"), passWord);
	}

	public void inputVercode(String vercode) {
		driver.sendKeys(By.name("vercode"), vercode);
	}

	public void clickLoginButton() {
		driver.click(By.xpath("//input[@type='submit'][@value='��¼']"));
	}

	public void login(String userName, String passWord, String vercode) {
		inputUserName(userName);
		inputPassWord(passWord);
		inputVercode(vercode);
		clickLoginButton();
	}

	/**
	 * ��¼�ɹ�
	 * 
	 * @param userName
	 * @param passWord
	 * @param vercode
	 * @return
	 */
	public HomePage loginOK(String userName, String passWord, String vercode) {
		login(userName, passWord, vercode);
		return new HomePage(webDriver);
	}

	/**
	 * �û������󣬵�¼ʧ��
	 * 
	 * @param userName
	 * @param passWord
	 * @param vercode
	 * @return
	 */
	public LoginPage loginByNameErr(String userName, String passWord,
			String vercode) {
		login(userName, passWord, vercode);
		return this;
	}

	/**
	 * ������󣬵�¼ʧ��
	 * 
	 * @param userName
	 * @param passWord
	 * @param vercode
	 * @return
	 */
	public LoginPage loginByPwdErr(String userName, String passWord,
			String vercode) {
		login(userName, passWord, vercode);
		return this;
	}

	/**
	 * ��֤����󣬵�¼ʧ��
	 * 
	 * @param userName
	 * @param passWord
	 * @param vercode
	 * @return
	 */
	public LoginPage loginByVercodeErr(String userName, String passWord,
			String vercode) {
		login(userName, passWord, vercode);
		return this;
	}

	public void goHomePage() {
		driver.click(By.partialLinkText("������ҳ"));
	}
}
