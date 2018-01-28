package com.melot.nuggets.screenshot;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Screenshot {
	public static void captureScreen(String fileName, String folder) throws Exception {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		screenRectangle = new Rectangle(0, 100, 360, 280);

		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		// 截图保存的路径
		File screenFile = new File(fileName);
		// 如果路径不存在,则创建
		if (!screenFile.getParentFile().exists()) {
			screenFile.getParentFile().mkdirs();
		}
		// 判断文件是否存在，不存在就创建文件
		if (!screenFile.exists() && !screenFile.isDirectory()) {
			screenFile.mkdir();
		}

		File f = new File(screenFile, folder);
		ImageIO.write(image, "png", f);
		// 自动打开
		/*
		 * if (Desktop.isDesktopSupported() &&
		 * Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
		 * Desktop.getDesktop().open(f);
		 */
	}

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		try {
			int i = 0;
			long startTime = System.currentTimeMillis();
			while (i++ < 5) {
				captureScreen("F:\\image\\", i + ".png");
			}
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			System.out.println(duration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
