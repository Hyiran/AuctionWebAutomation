package com.auction.action;

import org.openqa.selenium.WebDriver;

import com.auction.page.HomePage;
import com.auction.page.ManageKind.ManageKindPage;

public class ManageKindAction {
	private WebDriver driver;
	private HomePage HomePage;
	private ManageKindPage ManageKindPage;

	public ManageKindAction(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * ���������ɹ�
	 * 
	 * @param data
	 * @param vercode
	 * @param expectedText
	 * @return
	 */
	public boolean addKindOK(String data, String vercode, String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		ManageKindPage = HomePage.goManageKindPage();
		ManageKindPage.addKindOK(data, vercode);
		flag = ManageKindPage.isTextPresent(expectedText);
		ManageKindPage.goHomePage();
		return flag;
	}

	/**
	 * ��������࣬������Ϊ��
	 * 
	 * @param data
	 * @param vercode
	 * @param expectedText
	 * @return
	 */
	public boolean addKindByKindNameEmpty(String data, String vercode,
			String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		ManageKindPage = HomePage.goManageKindPage();
		ManageKindPage.addKindByKindNameEmpty(data, vercode);
		flag = ManageKindPage.isTextPresent(expectedText);
		ManageKindPage.goHomePage();
		return flag;
	}

	/**
	 * ��������࣬��֤��Ϊ��
	 * 
	 * @param data
	 * @param vercode
	 * @param expectedText
	 * @return
	 */
	public boolean addKindByVercodeEmpty(String data, String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		ManageKindPage = HomePage.goManageKindPage();
		ManageKindPage.addKindByVercodeEmpty(data);
		flag = ManageKindPage.isTextPresent(expectedText);
		ManageKindPage.goHomePage();
		return flag;
	}

	/**
	 * ��������࣬��֤�����
	 * 
	 * @param data
	 * @param vercode
	 * @param expectedText
	 * @return
	 */
	public boolean addKindByVercodeErr(String data, String vercode,
			String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		ManageKindPage = HomePage.goManageKindPage();
		ManageKindPage.addKindByVercodeErr(data, vercode);
		flag = ManageKindPage.isTextPresent(expectedText);
		ManageKindPage.goHomePage();
		return flag;
	}

	/**
	 * ɾ�����������
	 * 
	 * @param data
	 * @param vercode
	 * @param expectedText
	 * @return
	 */
	public boolean delKind(String data, String vercode, String expectedText) {
		boolean flag = false;
		HomePage = new HomePage(driver);
		ManageKindPage = HomePage.goManageKindPage();
		ManageKindPage.addKindOK(data, vercode);
		ManageKindPage.delKind(data);
		flag = ManageKindPage.isTextNotPresent(expectedText);
		ManageKindPage.goHomePage();
		return flag;
	}
}
