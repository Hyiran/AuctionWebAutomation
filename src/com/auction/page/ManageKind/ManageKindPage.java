package com.auction.page.ManageKind;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.auction.page.BasePage;

public class ManageKindPage extends BasePage {
	private Logger logger = Logger.getLogger(ManageKindPage.class);

	public ManageKindPage(WebDriver driver) {
		super(driver);
		logger.debug("running test in 【ManageKindPage】");
	}

	public void inputKindName(String kindName) {
		driver.sendKeys(By.name("name"), kindName);
	}

	public void inputKindDesc(String kindDesc) {
		driver.sendKeys(By.name("desc"), kindDesc);
	}

	public void inputVercode(String vercode) {
		driver.sendKeys(By.name("vercode"), vercode);
	}

	public void clickAddButton() {
		driver.click(By.xpath("//input[@type='submit'][@value='添加']"));
	}

	public void addKind(String kindName, String kindDesc, String vercode) {
		this.inputKindName(kindName);
		this.inputKindDesc(kindDesc);
		this.inputVercode(vercode);
		this.clickAddButton();
	}

	/**
	 * 添加新种类成功
	 * 
	 * @param data
	 * @param vercode
	 * @return
	 */
	public ManageKindPage addKindOK(String data, String vercode) {
		this.addKind(data, data, vercode);
		return this;
	}

	/**
	 * 添加新种类，种类名为空
	 * 
	 * @param data
	 * @param vercode
	 * @return
	 */
	public ManageKindPage addKindByKindNameEmpty(String data, String vercode) {
		this.addKind("", data, vercode);
		return this;
	}

	/**
	 * 添加新种类，验证码为空
	 * 
	 * @param data
	 * @param vercode
	 * @return
	 */
	public ManageKindPage addKindByVercodeEmpty(String data) {
		this.addKind(data, data, "");
		return this;
	}

	/**
	 * 添加新种类，验证码错误
	 * 
	 * @param data
	 * @param vercode
	 * @return
	 */
	public ManageKindPage addKindByVercodeErr(String data, String vercode) {
		this.addKind(data, data, vercode);
		return this;
	}

	/**
	 * 删除已添加种类
	 * 
	 * @param kindName
	 * @return
	 */
	public ManageKindPage delKind(String kindName) {
		driver.click(By.xpath("//td[contains(text(),'" + kindName
				+ "')]/following::a[contains(text(),'删除')][1]"));
		driver.chooseOKOnAlert();
		return this;
	}

	public void goHomePage() {
		driver.click(By.partialLinkText("返回首页"));
	}
}
