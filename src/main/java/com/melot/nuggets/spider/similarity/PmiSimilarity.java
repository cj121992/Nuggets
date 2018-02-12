package com.melot.nuggets.spider.similarity;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.log4j.Logger;

/**
 * Created by lingfengsan on 2018/1/23.
 *
 * @author lingfengsan
 */
public class PmiSimilarity implements Similarity {
    private SearchFactory searchFactory = new SearchFactory();
    private String question;
    private String answer;
    public PmiSimilarity(String question,String answer){
        this.question=question;
        this.answer=answer;
    }
 
    private static final Logger log = Logger.getLogger(PmiSimilarity.class);
    
    @Override
    @SuppressWarnings("unchecked")
    public Double call() {
        FutureTask[] futureTasks = new FutureTask[2];
        Search[] searches = new Search[2];
        searches[0] = searchFactory.getSearch(Config.getSearchSelection()
                , (question + " " + answer), false);
        searches[1] = searchFactory.getSearch(Config.getSearchSelection()
                , answer, false);
        for (int i = 0; i < searches.length; i++) {
            futureTasks[i] = new FutureTask<Long>(searches[i]);
            new Thread(futureTasks[i]).start();
        }
        while (true) {
            if (futureTasks[0].isDone() && futureTasks[1].isDone()) {
                break;
            }
        }
        try {
            Long countQA=(Long)futureTasks[0].get();
            Long countA=(Long)futureTasks[1].get();
//            log.info(answer+(double)countQA/(double)countA);
            return (double)countQA/(double)countA;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0.0;

    }
    
    public static void main(String[] args) {
    	String question = "四大丑女";
		String a = "孟光";
		String b = "钟无艳";
		String c = "黄月英";
		similarity(question, a, b, c);
    	
		question = "下列不是俄国作家的是";
		a = "泰戈尔 ";
		b = "普希金";
		c = "高尔基";
		similarity(question, a, b, c);
		
		
//		question = "不属于新写实小说的代表作家有";
//		a = "刘恒 ";
//		b = "池莉";
//		c = "刘索拉";
//		similarity(question, a, b, c);
		
//		question = "下列不是吝啬鬼的是";
//		a = "严监生";
//		b = "葛朗台";
//		c = "希礼";
//		similarity(question, a, b, c);

		
//		question = "周易八卦不包括以下哪个选项";
//		a = "艮";
//		b = "巽";
//		c = "撰";
//		similarity(question, a, b, c);
//
//		//这题不对 //TODO
//		question = "达摩花了几年到达中国";
//		a = "3";
//		b = "4";
//		c = "5";
//		similarity(question, a, b, c);
//		
//		question = "最早的扇子出现在哪里";
//		a = "四川";
//		b = "浙江";
//		c = "安徽";
//		similarity(question, a, b, c);
//		
//		question = "开卷有益是谁提出的观点";
//		a = "唐太宗";
//		b = "赵匡胤";
//		c = "赵光义";
//		similarity(question, a, b, c);
//		
//		question = "裴頠没有发表过以下那部作品";
//		a = "辩才论";
//		b = "崇有论";
//		c = "论语";
//		similarity(question, a, b, c);

	}

	private static void similarity(String question, String a, String b, String c) {
		log.info("Pmi关联度答案:--------------------------是选大值,非选小值");
		log.info("題目 : " + question);
		PmiSimilarity asp = new PmiSimilarity(question, a);
		log.info(a + ":" + double2String(asp.call()));
		PmiSimilarity bsp = new PmiSimilarity(question, b);
		log.info(b + ":" + double2String(bsp.call()));
		PmiSimilarity csp = new PmiSimilarity(question, c);
		log.info(c + ":" + double2String(csp.call()));
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

	private static String double2String(double aspd) {
		BigDecimal bigDecimal = new BigDecimal(aspd);  
        String aspdr = bigDecimal.toString();
		return aspdr;
	}
}