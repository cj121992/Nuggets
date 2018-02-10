package com.melot.nuggets.websocket;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.melot.nuggets.chrome.ChromeHandler;


@ClientEndpoint
public class WSClient {

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
			System.out.println(message);
			String question = json.getString("topicContent");
			ChromeHandler.findSogou(question);
			ChromeHandler.findBaidu(question);
			JSONArray options = json.getJSONArray("options");
			String a = options.getJSONObject(0).getString("optionContent");
			String b = options.getJSONObject(1).getString("optionContent");
			String c = options.getJSONObject(2).getString("optionContent");
			System.out.println("題目 : " + question + "\n" + "选项a  : "  + a + "\n" + "选项 b : "  + b + "\n" + "选项 c: "  + c + "\n");
		}
	}
	
//TODO test
//	public static void main(String[] args) {
//		String msgA = "{\"MsgTag\":10030203,\"options\":[{\"optionContent\":\"钟无艳\"}, {\"optionContent\":\"黄月英\"}, {\"optionContent\":\"孟光\"}],\"topicContent\":\"以下不属于中国四大丑女的是?\"}";
//		WSClient wsClient = new WSClient();
//		wsClient.onMessage(msgA);
//	}
	
	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}
	
	@OnClose
	public void onClose() throws InterruptedException {
		ChromeHandler.close();
	}
}
