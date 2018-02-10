package com.melot.nuggets.spider.similarity;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by lingfengsan on 2018/1/23.
 *
 * @author lingfengsan
 */
public class BaiDuSimilarity implements Similarity {
	// 设置APPID/AK/SK
	private static AipNlp client = new AipNlp("10818704", "I0SyYIReAGhc9YRDM6TVLqSl", "Du4w9Nif4s8sgCaLDRQ8Qx4d4sHfZMGh");
	private String question;
	private String answer;

	public BaiDuSimilarity(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	@Override
	public Double call() {
		long start = System.currentTimeMillis();
		// 传入可选参数调用接口
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("model", "CNN");
		// 短文本相似度
		JSONObject res = client.simnet(question, answer, options);
//		float time = (System.currentTimeMillis() - start) / 1000f;
		double score;
		if (res.has("score")) {
			score = res.getDouble("score");
		} else {
			score = -1d;

		}
//		System.out.println(answer + score);
//		System.out.println("短文本相似度处理时间" + time);
		return score;
	}

	public static void main(String[] args) {
		String question = "四大丑女";
		String a = "孟光";
		String b = "钟无艳";
		String c = "黄月英";
		System.out.println("关联度答案:--------------------------是选大值,非选小值");
		BaiDuSimilarity as = new BaiDuSimilarity(question, a);
		System.out.println(a + ":" + as.call());
		BaiDuSimilarity bs = new BaiDuSimilarity(question, b);
		System.out.println(b + ":" + bs.call());
		BaiDuSimilarity cs = new BaiDuSimilarity(question, c);
		System.out.println(c + ":" + cs.call());
	}

	public static void setClient(AipNlp client) {
		BaiDuSimilarity.client = client;
	}
}