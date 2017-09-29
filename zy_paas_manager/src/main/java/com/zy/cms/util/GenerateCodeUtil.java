package com.zy.cms.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.apache.log4j.Logger;

public class GenerateCodeUtil {

	private static final Logger log = Logger.getLogger(GenerateCodeUtil.class);

	private static int widthDefault = 90; // 定义图片的width
	private static int heightDefault = 20; // 定义图片的height
	private static int codeCount = 4; // 定义图片上显示验证码的个数
	private static int xx = 15;
	private static int fontHeight = 18;
	private static int codeY = 16;

	// private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
	// 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
	// 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2',
	// '3', '4', '5', '6', '7', '8', '9' };

	private static char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static BufferedImage getCode(StringBuffer randomCode, Integer width, Integer height) {

		try {

			width = (width != null ? width : widthDefault);
			height = (height != null ? height : heightDefault);
			// 定义图像buffer
			BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics gd = buffImg.getGraphics();
			// 创建一个随机数生成器类
			Random random = new Random();
			// 将图像填充为白色
			gd.setColor(Color.WHITE);
			gd.fillRect(0, 0, width, height);

			// 创建字体，字体的大小应该根据图片的高度来定。
			Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
			// 设置字体。
			gd.setFont(font);

			// 画边框。
			gd.setColor(Color.white);
			gd.drawRect(0, 0, width - 1, height - 1);

			// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
			gd.setColor(Color.BLACK);
			/*
			 * for (int i = 0; i < 40; i++) { int x = random.nextInt(width); int
			 * y = random.nextInt(height); int xl = random.nextInt(12); int yl =
			 * random.nextInt(12); gd.drawLine(x, y, x + xl, y + yl); }
			 */
			int red = 0, green = 0, blue = 0;

			// 随机产生codeCount数字的验证码。
			for (int i = 0; i < codeCount; i++) {
				// 得到随机产生的验证码数字。
				String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
				// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
				red = random.nextInt(255);
				green = random.nextInt(255);
				blue = random.nextInt(255);

				// 用随机产生的颜色将验证码绘制到图像中。
				gd.setColor(new Color(red, green, blue));
				gd.drawString(code, (i + 1) * xx, codeY);

				// 将产生的四个随机数组合在一起。
				randomCode.append(code);
			}

			return buffImg;
		} catch (Exception e) {
			log.error("【生成图片异常】error=" + e.getMessage());
		}
		return new BufferedImage(widthDefault, heightDefault, BufferedImage.TYPE_INT_RGB);

	}

	/**
	 * 随机生成字母或数字组合码
	 * 
	 * @param length
	 * @return
	 */
	public static String generateCharAndNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) { // 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 随机生成字母或数字组合码
	 * 
	 * @param length
	 * @return
	 */
	public static String generateNumCode(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

	/**
	 * 随机生成length长度的字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String generateChar(int length) {
		char[] ss = new char[length];
		int i = 0;
		while (i < length) {
			int f = (int) (Math.random() * 2);
			if (f == 0)
				ss[i] = (char) ('A' + Math.random() * 26);
			else
				ss[i] = (char) ('a' + Math.random() * 26);
			i++;
		}
		String rs = new String(ss);
		return rs;
	}

	public static void main(String[] args) {
		System.out.println(generateNumCode(6));
	}
}
