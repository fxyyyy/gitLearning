package com.fxy.util;

import java.io.IOException;

import com.fxy.util.MailUtil;

public class Test {
	public static void main(String[] args) {
		
		//截图的路径（输出路径），生成的缩略图根据文件名去命名 							
		String strSmallPath ="E:\\GraduationProjectV1\\wordspace\\demoThree\\src\\"
				+ "main\\resources\\static\\myImages\\small\\"+"666"+".jpg";
		//截取的图片尺寸
		String strSmallImage  = "  198*231  ";
									
		try {   
			//调用批处理文件 ：生成小图  
			
			//注意生成图片的位置
			Runtime.getRuntime().exec(
				"E:\\ffmpeg\\ffmpeg-3.4.2-win64-static\\bin\\ffmpeg.exe  -i  "
			   // + strVideoRealPath
				+ "  -y -f mjpeg -ss 3 -t 0.001 -s   "+strSmallImage+"   "
				+ strSmallPath
		    ); 
			//log.info("执行了视频截图操作");
		} catch (IOException e) {   
			e.printStackTrace();   
		}
		//end:截取视频缩略图，一个视频借小中大三张	
		
		/*
		String str1 = "fg h&jkl";
		String str2 = str1.substring(1);//从第1号位置开始截取字符串，截到最后,把截取后的返回，赋值给str2
		System.out.println("str1 == " + str1);
		System.out.println("str2 == " + str2);*/
		
		/*int num = (int) Math.rint(Math.random() * 9000 + 1000);
		System.out.println("验证码：" + num);
		// 收件人邮箱
		String email = "359097854@qq.com";
		// 利用正则表达式验证邮箱是否符合邮箱的格式
		if (!email.matches("^\\w+@(\\w+\\.)com$")) {
			System.out.println("邮箱错误");
		}
		// 开启一个新的线程发送邮件
		else{
			new Thread(new MailUtil(email, num)).start();
		}*/
    }
}
