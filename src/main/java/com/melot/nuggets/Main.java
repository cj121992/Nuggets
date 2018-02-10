package com.melot.nuggets;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.melot.nuggets.chrome.ChromeHandler;
import com.melot.nuggets.ocr.BaiduOcrApi;
import com.melot.nuggets.screenshot.Screenshot;

public class Main {
	private static int i = 0;
	
	private static Logger logger = Logger.getLogger(Main.class);
	public static void main(String[] args) throws Exception {
		WebDriver baidu = ChromeHandler.openBaidu();
		WebDriver sogou = ChromeHandler.openSogou();

		long start = System.currentTimeMillis();
		while (i < 10) {
			String path = "F:\\image\\";
			String fileName = i++ + ".png";
			Screenshot.captureScreen(path, fileName);
			String keywords = BaiduOcrApi.getInstance().callOrc(path + fileName);
			try {
				ChromeHandler.findBaidu(keywords);
			} catch (Exception e) {
				logger.error("keywords error : " + keywords, e);
				continue;
			}
			
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		ChromeHandler.close(sogou);
		ChromeHandler.close(baidu);
		
	}
}
