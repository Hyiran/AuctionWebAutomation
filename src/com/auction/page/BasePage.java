package com.auction.page;

import org.openqa.selenium.WebDriver;

import com.framework.webdriver.baseapi.WebdriverBaseApi;

public class BasePage {
	protected WebDriver webDriver;
	protected WebdriverBaseApi driver;

	public BasePage(WebDriver driver) {
		webDriver = driver;
		this.driver = new WebdriverBaseApi(webDriver);
	}

	public boolean isTextPresent(String expectedText) {
		return driver.isContentAppeared(expectedText);
	}

	public boolean isTextNotPresent(String expectedText) {
		return driver.isContentNotAppeared(expectedText);
	}

}
