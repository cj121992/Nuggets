package com.melot.nuggets.chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeHandler {

	private static ChromeHandler instance = new ChromeHandler();

	private static WebElement kw;
	private static WebElement su;
	private static WebElement query;
	private static WebElement stb;

	public static ChromeHandler getInstance() {
		return instance;
	}

	private ChromeHandler() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}

	public static void main(String[] args) throws InterruptedException {

		WebDriver baiduWebDriver = openBaidu();

		WebDriver sougouWebDriver = openSogou();

		close(baiduWebDriver, sougouWebDriver);
	}

	public static void close(WebDriver baiduWebDriver, WebDriver sougouWebDriver) throws InterruptedException {
		Thread.sleep(3000);
		baiduWebDriver.close();
		sougouWebDriver.close();
		System.out.println("Hello World!");
	}
	
	public static WebDriver openSogou() {
		WebDriver sougouWebDriver = new ChromeDriver();
		sougouWebDriver.get("http://www.sogou.com");

		query = sougouWebDriver.findElement(By.id("query"));
		stb = sougouWebDriver.findElement(By.id("stb"));
		return sougouWebDriver;
	}

	public static WebDriver openBaidu() {
		WebDriver baiduWebDriver = new ChromeDriver();
		baiduWebDriver.get("http://www.baidu.com");

		kw = baiduWebDriver.findElement(By.id("kw"));
		su = baiduWebDriver.findElement(By.id("su"));
		return baiduWebDriver;
	}
	
	public static void findSogou(String keyWords) {
		query.clear();
		query.sendKeys(keyWords);
		stb.click();
	}

	public static void findBaidu(String keyWords) {
		kw.clear();
		kw.sendKeys(keyWords);
		su.click();
	}

}
