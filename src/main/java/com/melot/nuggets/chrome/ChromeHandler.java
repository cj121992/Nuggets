package com.melot.nuggets.chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ChromeHandler {

	private static ChromeHandler instance = new ChromeHandler();

	private static WebElement kw;
	private static WebElement su;
	private static WebElement upquery;
	private static WebElement searchBtn;
	private static WebElement cancel;

	private static WebElement leftFrame;
	private static WebElement rightFrame;

	private static WebDriver baiduWebDriver;
	private static WebDriver sougouWebDriver;

	public static ChromeHandler getInstance() {
		return instance;
	}

	private ChromeHandler() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}

	public static void openWeb(String url) {
		WebDriver webDriver = new ChromeDriver();
		try {
			webDriver.get(url);
		} catch (Throwable t) {
		}
		leftFrame = webDriver.findElement(By.id("leftIframe"));
		String lUrl = leftFrame.getAttribute("src");
		rightFrame = webDriver.findElement(By.id("rightIframe"));
		String rUrl = rightFrame.getAttribute("src");
		System.out.println(lUrl);
	}

	public static void find(String keyword) {
		leftFrame.sendKeys(keyword);
		rightFrame.sendKeys(keyword);
	}

	public static void main(String[] args) throws InterruptedException {

		WebDriver baiduWebDriver = openBaidu();

		WebDriver sougouWebDriver = openSogou();
		findSogou("玉不琢不成器");
		findSogou("cj");
		close(baiduWebDriver);
		close(sougouWebDriver);
	}

	public static void close(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		System.out.println("Hello World!");
	}

	public static void close() throws InterruptedException {
		Thread.sleep(3000);
		sougouWebDriver.close();
		baiduWebDriver.close();
		System.out.println("close success!");
	}

	public static WebDriver openSogou() {
		sougouWebDriver = new ChromeDriver();
		sougouWebDriver.get("https://www.sogou.com/web?query=hello");

		upquery = sougouWebDriver.findElement(By.id("upquery"));
		searchBtn = sougouWebDriver.findElement(By.id("searchBtn"));
		cancel = sougouWebDriver.findElement(By.id("top_qreset"));
		return sougouWebDriver;
	}

	public static WebDriver openBaidu() {
		baiduWebDriver = new ChromeDriver();
		baiduWebDriver.get("http://www.baidu.com");

		kw = baiduWebDriver.findElement(By.id("kw"));
		su = baiduWebDriver.findElement(By.id("su"));
		return baiduWebDriver;
	}

	// public static void findSogou(String keyWords) {
	// cancel.click();
	// upquery.sendKeys(keyWords);
	// searchBtn.click();
	// }

	public static void findSogou(String keyWords) {
		sougouWebDriver.get("https://www.sogou.com/web?query=" + keyWords);
	}

	// public static void findBaidu(String keyWords) {
	// kw.clear();
	// kw.sendKeys(keyWords);
	// su.click();
	// }

	public static void findBaidu(String keyWords) {
		baiduWebDriver.get("http://www.baidu.com/s?wd=" + keyWords);
	}

}
