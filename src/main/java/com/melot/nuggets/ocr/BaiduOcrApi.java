package com.melot.nuggets.ocr;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class BaiduOcrApi {

	public static final String APP_ID = "10725275";

	public static final String APP_KEY = "pbLV4Qu8LVtEEsywbGuFZLvd";

	public static final String SECRET_KEY = "Le6jWrqQbWm5bmyVHAQKDxNXkRPqhg85";

	private static AipOcr client = new AipOcr(APP_ID, APP_KEY, SECRET_KEY);
	
	private static HashMap<String, String> options = new HashMap<String, String>();
	
	private static BaiduOcrApi instance = new BaiduOcrApi();
	
	private BaiduOcrApi() {
		if (instance == null) {
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);
			
			//是否开启旋转检测
		    options.put("detect_direction", "true");
			// client.setHttpProxy("proxy_host", port);
			// client.setSocketProxy("proxy_hsot", port);
		}
	}
	
	public static String callOrc(String path) {
		JSONObject res = client.basicGeneral(path, options);
		return res.toString(2);
	}
	
	
	
	public static void main(String[] args) {
		JSONObject res = client.basicGeneral("test3.png", options);
		System.out.println(res.toString(2));
		
		
		// 调用接口
//		String path_format = "test%s.png";
//		int i = 0;
//		String path = String.format(path_format, i);
//		long startTime = System.currentTimeMillis();
//		while (i++ < 5) {
//			JSONObject res = client.basicGeneral("test1", options);
//			System.out.println(res.toString(2));
//			path = String.format(path_format, i);
//		}
//		long endTime = System.currentTimeMillis();
//		long duration = endTime - startTime;
//		System.out.println(duration);
	}
}
