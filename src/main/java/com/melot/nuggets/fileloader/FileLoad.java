package com.melot.nuggets.fileloader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.melot.nuggets.websocket.WSClient;


public class FileLoad {
	static int m = 1;
    private static final Logger log = Logger.getLogger(WSClient.class);

	public static void search(File a, String x, int displayCount) throws IOException {// 在文件a中的每行中查找x
		int flag = 0;
		boolean print = false;
		// Scanner scan = new Scanner(a,"gbk");
		// int k = 0;
		// while(true){
		// if(scan.hasNext()==false) break;
		// String s = scan.nextLine();
		// k++;
		// if(s.contains(x)){
		// String ss =m +".文件:"+ a.getPath() + " 第" + k + "行 \n 内容：" + s;
		// System.out.println(ss);
		// m++;
		// }
		// }
		Scanner scan1 = new Scanner(a, "utf-8");
		int k1 = 0;
		while (true) {
			if (scan1.hasNext() == false)
				break;
			String s1 = scan1.nextLine();
			k1++;
			if (s1.contains(x)) {
				String ss1 = s1 + "---------------第" + k1 + "行  内容：\n";
				System.err.println(ss1);
				m++;
				print = true;
			} else if (print) {
				if (flag++ < displayCount) {
					String ss1 = s1 + "---------------第" + k1 + "行  内容：\n";
					System.out.println(ss1);
				} else {
					break;
				}
			}
		}
		if (!print) {
			log.info("not found : " + x);
		}
		scan1.close();
	}
	// static void f(File a,String s)throws IOException{//在a下所有文件中查找含有s的行
	//
	// File[] ff = a.listFiles();
	// if(ff==null) return;
	// for(File it : ff){
	// if(it.isFile()){//若a是文件，直接查找
	// search(it,s);
	// }
	// if(it.isDirectory()){//若a是目录，则对其目录下的目录或文件继续查找
	// f(it,s);
	// }
	// }
	// }

	public static void main(String[] args) throws IOException {
		search(new File("F:\\Users\\admin\\workspace\\nuggets\\question.txt"), "清末被称为“钱王”的云南首富", 5);
	}

}