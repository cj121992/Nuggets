package com.melot.nuggets.websocket;

import java.io.IOException;
import java.math.BigDecimal;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.melot.nuggets.chrome.ChromeHandler;
import com.melot.nuggets.spider.similarity.BaiDuSimilarity;
import com.melot.nuggets.spider.similarity.PmiSimilarity;


@ClientEndpoint
public class WSClient {
	
    private static final Logger log = Logger.getLogger(WSClient.class);
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connected to endpoint: " + session.getBasicRemote());
		try {
			//发送token
			session.getBasicRemote().sendText("{\"MsgTag\":10030101,\"userId\":-1,\"token\":\"92D4C39FCD8A4D9C86C1A98D16B2BA3A\",\"platform\":1}");
		} catch (IOException ex) {
		}
	}

	@OnMessage
	public void onMessage(String message) {
//		System.out.println(message);
		//把拦截到的问题答案msg发送到本地服务进行处理
		JSONObject json = JSON.parseObject(message);
		String msgTag = json.getString("MsgTag");
		if (msgTag.equals("10030203")) {
			log.info(message);
			String question = json.getString("topicContent");
			JSONArray options = json.getJSONArray("options");
			String a = options.getJSONObject(0).getString("optionContent");
			String b = options.getJSONObject(1).getString("optionContent");
			String c = options.getJSONObject(2).getString("optionContent");
			log.info("題目 : " + question + "\n" + "选项a  : "  + a + "\n" + "选项 b : "  + b + "\n" + "选项 c: "  + c + "\n");
			similarity(question, a, b, c);
			ChromeHandler.findBaidu(a);
			ChromeHandler.findBaidu2(b);
			ChromeHandler.findBaidu3(c);
			ChromeHandler.findSogou(question);
		}
	}
	
//TODO test
//	public static void main(String[] args) {
//		String msgA = "{\"MsgTag\":10030203,\"options\":[{\"optionContent\":\"钟无艳\"}, {\"optionContent\":\"黄月英\"}, {\"optionContent\":\"孟光\"}],\"topicContent\":\"以下不属于中国四大丑女的是?\"}";
//		WSClient wsClient = new WSClient();
//		wsClient.onMessage(msgA);
//	}
	
	private static void similarity(String question, String a, String b, String c) {
		log.info("Pmi关联度答案:--------------------------是选大值,非选小值");
		log.info("題目 : " + question);
		PmiSimilarity asp = new PmiSimilarity(question, a);
		double ad = asp.call();
		log.info(a + ":" + double2String(ad));
		PmiSimilarity bsp = new PmiSimilarity(question, b);
		double bd = bsp.call();
		log.info(b + ":" + double2String(bd));
		PmiSimilarity csp = new PmiSimilarity(question, c);
		double cd = csp.call();
		log.info(c + ":" + double2String(cd));
		log.info("Pmi推荐答案: " + getMax(ad, bd, cd));
		
		log.info("-----------------------------------------");
		log.info("NIP关联度答案:--------------------------是选大值,非选小");
		log.info("題目 : " + question);
		BaiDuSimilarity asn = new BaiDuSimilarity(question, a);
		log.info(a + ":" + double2String(asn.call()));
		BaiDuSimilarity bsn = new BaiDuSimilarity(question, b);
		log.info(b + ":" + double2String(bsn.call()));
		BaiDuSimilarity csn = new BaiDuSimilarity(question, c);
		log.info(c + ":" + double2String(csn.call()));
	}
	
	private static int getMax(double a, double b, double c) {
		if (a > b) {
			if (a > c) {
				return 1;
			} else {
				return 3;
			}
		} else {
			if (b > c) {
				return 2;
			} else {
				return 3;
			}
		}
	}
	
	private static String double2String(double aspd) {
		BigDecimal bigDecimal = new BigDecimal(aspd);  
        String aspdr = bigDecimal.toString();
		return aspdr;
	}
	
	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}
	
	@OnClose
	public void onClose() throws InterruptedException {
		ChromeHandler.close();
	}
}
