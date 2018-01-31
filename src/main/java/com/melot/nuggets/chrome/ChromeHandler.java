package com.melot.nuggets.chrome;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeHandler {

	private static ChromeHandler instance = new ChromeHandler();

	private static WebElement kw;
	private static WebElement su;
	private static WebElement upquery;
	private static WebElement searchBtn;
	private static WebElement cancel;
	
	public static ChromeHandler getInstance() {
		return instance;
	}

	private ChromeHandler() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
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
	
	public static WebDriver openSogou() {
		WebDriver sougouWebDriver = new ChromeDriver();
		sougouWebDriver.get("https://www.sogou.com/web?query=hello");

		upquery = sougouWebDriver.findElement(By.id("upquery"));
		searchBtn = sougouWebDriver.findElement(By.id("searchBtn"));
		cancel = sougouWebDriver.findElement(By.id("top_qreset"));
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
		cancel.click();
		upquery.sendKeys(keyWords);
		searchBtn.click();
	}
	
	public static void findSogou(WebDriver driver, String keyWords) {
		driver.get("https://www.sogou.com/web?query=" + keyWords);
	}

	public static void findBaidu(String keyWords) {
		kw.clear();
		kw.sendKeys(keyWords);
		su.click();
	}

}
