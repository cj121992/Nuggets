package com.melot.nuggets;

import com.melot.nuggets.ocr.BaiduOcrApi;
import com.melot.nuggets.screenshot.Screenshot;

public class Main {
	private static int i = 0;
	
	public static void main(String[] args) throws Exception {
		String path = "F:\\image\\";
		String fileName = i++ + ".png";
		Screenshot.captureScreen(path, fileName);
		BaiduOcrApi.
	}
}
