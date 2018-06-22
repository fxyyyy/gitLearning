package com.fxy.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fxy.assist.ErrEnum;
import com.fxy.assist.Msg;
import com.fxy.bean.Article;
import com.fxy.bean.Comment;
import com.fxy.bean.FileList;
import com.fxy.bean.Folder;
import com.fxy.bean.Inform;
import com.fxy.bean.LearnRate;
import com.fxy.bean.Path;
import com.fxy.bean.User;
import com.fxy.bean.VideoComment;
import com.fxy.bean.VideoFolder;
import com.fxy.bean.VideoList;
import com.fxy.service.ArticleService;
import com.fxy.service.CommentService;
import com.fxy.service.LearnRateService;
import com.fxy.service.PathService;
import com.fxy.service.UserService;
import com.fxy.service.VideoCommentService;
import com.fxy.service.VideoFolderService;
import com.fxy.service.VideoListService;
import com.fxy.util.Page;
import com.fxy.util.PageUtil;
import com.fxy.util.UpYunUtil;

import main.java.com.UpYun;
import main.java.com.upyun.UpException;

@Controller
public class VideoController {
	
	@Autowired
	private VideoCommentService videoCommentService;

	@Autowired
	private VideoFolderService videoFolderService;
	
	@Autowired
	private VideoListService videoListService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LearnRateService learnRateService;
	
	protected static final Logger log = LoggerFactory.getLogger(VideoController.class);

	//跳到显示该文件夹下视频的页面
	@RequestMapping(value = "hrefFolderVideo")
	public String hrefFolderVideo(HttpSession session,String strFolder) {
		session.setAttribute("folderForVideo", strFolder);
		return "modelShowFolderVideo";
	}
	
	//begin:视频播放页评论
	@ResponseBody
	@RequestMapping(value = "commentVideo", method = RequestMethod.POST)
	public Msg commentVideo(HttpServletRequest request,int idVideo, String inputComment) {
		// 设置评论的时间
		Date date = new Date();

		//获得评论用户的ID
		HttpSession session = request.getSession();
		int intUserId  = (int) session.getAttribute("intUserId");
		//根据id获得名字
		User user = userService.selectByIdForName(intUserId);
		
		// 封装传过来的值
		VideoComment comment = new VideoComment();
		comment.setContent(inputComment);
		comment.setPublishtime(date);
		comment.setUserid(intUserId);
		comment.setVideoid(idVideo);
		comment.setUsername(user.getName());
		// 调用插入数据库函数
		int intFlagInsertComment = videoCommentService.addComment(comment);
		// 插入成功，返回1
		log.info("主评论插入成功返回1-->flag:" + intFlagInsertComment);
  
		//根据用户ID获得主评论用户的用户名User selectByPrimaryKey(Integer id)
		User userForName = userService.selectByIdForName(intUserId);
		String strName = userForName.getName();
		return Msg.success().add("comment", comment).add("commentUserName", strName);
		
	}
	
	
	//根据文件夹id获得该文件夹下的全部视频
	@ResponseBody
	@RequestMapping(value = "/urlChooseVideoList")
	public Msg urlChooseVideoList(HttpSession session,int path) {
		//根据名字获得可能的文件夹名称
		List<VideoList> videoLists = videoListService.selectAllFileByPath(path);
		return Msg.success().add("videoLists", videoLists);
	}
	
	
	//根据用户输入的pathName课程名字显示可能的结果
	@ResponseBody
	@RequestMapping(value = "/urlChooseVideoFolder")
	public Msg urlChoosePathName(HttpSession session,String pathName) {
		//根据名字获得可能的文件夹名称
		//List<VideoFolder> listVideoFolders = videoFolderService.chooseVideoFolder(pathName);
		//VideoList name
		List<VideoList>  list = videoListService.selectByName(pathName);
		return Msg.success().add("list", list);
	}
	
	
	//后台视频管理系统：显示全部视频
	@RequestMapping(value = "/urlManageShowVideo", method = RequestMethod.GET)
	public String urlManageShowVideo(HttpSession session) {
		//获得全部视频及其缩略图
		List<VideoList> listVideoLists = videoListService.selectAll();
		
		session.setAttribute("listVideoLists",listVideoLists );
		
		//遍历显示视频
		for (VideoList videoList : listVideoLists) {
			log.info("videoList:"+videoList.getSmallimage());
		}
		return "manageShowAllVideo";
	}
	
	/**
	 * @Title: modelV
	 * @Description: 前台：跳转到已发表的全部视频页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@RequestMapping(value = "modelV")
	public String modelV(HttpServletRequest request) {
		return "modelShowAllVideo";
	}
	
	/**
	 * @Title: urlManageVideo
	 * @Description: 后台：跳转到视频系统管理页面，显示全部视频文件夹
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@RequestMapping(value = "urlManageVideo")
	public String urlManageVideo(HttpServletRequest request) {
		// 获得全部的视频文件夹
		List<VideoFolder> videoFolders = videoFolderService.selectAll();
		request.getSession().setAttribute("videoFolders", videoFolders);
		
		return "manageVideoMain";
	}
	
	/**
	 * @Title: urlManageVideoMkdirs
	 * @Description: 后台：跳转到创建文件夹页面
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@RequestMapping(value = "urlManageVideoMkdirs")
	public String urlManageVideoMkdirs(HttpServletRequest request) {
		return "manageVideoMkdirs";
	}
	
	/**
	 * @Title: urlManageVideo
	 * @Description: 后台：在云里创建文件夹
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@RequestMapping(value = "urlVideoMkdirs")
	public String urlVideoMkdirs(HttpServletRequest request, String videoFolder) {
		
		//自己记得在创建的文件夹路径加/在后面
		log.info("videoFolder:"+videoFolder);
		
		if (videoFolder.length() == 0) {
			request.getSession().setAttribute("mkdirsErr", "您没有输入文件夹的名称！");
			return "manageVideoMkdirs";
		} else {
			// 得到文件夹路径
			String path = videoFolder + "/";
			// 查询数据库是否已有该记录，如果没有，就插入，然后就可以根据path来显示刚才创建的文件夹
			int num = videoFolderService.queryByPath(path);
			log.info("num:" + num);
			// 如果数据库中已有该文件夹记录
			if (num != 0) {
				request.getSession().setAttribute("errFolder", "该文件夹已经存在，请重新输入文件夹名称！");
				return "manageVideoMkdirs";
				// 如果没有
			} else {
				// 创建本地文件夹
				File file = new File("E:/"+videoFolder);
				if (file.mkdirs()) {
					log.info("多级层文件夹创建成功！创建后的文件目录为：" + file.getPath() + ",上级文件为:" + file.getParent());
				}
				// 创建目录，自动创建父级目录
				UpYunUtil upYunUtil = new UpYunUtil();
				boolean result;
				try {
					result = upYunUtil.upYunPath().mkDir(path, true);

					log.info("result:" + result);
					if (result) {
						// begin:如果成功创建

						// 获得要创建文件夹的用户ID
						int intUserId = (int) request.getSession().getAttribute("intUserId");
						log.info("intUserId:" + intUserId);

						// 把文件夹路径插入数据库
						VideoFolder entityAddVideoFolder = new VideoFolder();
						entityAddVideoFolder.setFolder(path);
						entityAddVideoFolder.setUserid(intUserId);
						int intNumForAdd = videoFolderService.add(entityAddVideoFolder);
						log.info("intNumForAdd:" + intNumForAdd);

						// 显示当前创建的文件夹
						List<VideoFolder> entityShowVideoFolder = videoFolderService.showJustFolder(path);
						log.info("entityShowVideoFolder:" + entityShowVideoFolder.toString());

						// 把刚才创建的文件夹存到session
						request.getSession().setAttribute("entityShowVideoFolder", entityShowVideoFolder);
						// 跳转到显示刚才创建的文件夹页面
						return "manageVideoNewFolder";
						// end:如果成功创建
					} else {
						// 如果创建失败
						request.getSession().setAttribute("errFolder", "该文件夹已经存在，请重新输入文件夹名称！");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 如果视频文件夹创建失败
				return "manageVideoMkdirs";
			}
		}
	}	
	
	/**
	 * @Title: urlUploadVideoId 
	 * @Description: 后台：获得待上传到的文件夹ID，跳到视频上传页面
	 * @param request
	 * @param id
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	@RequestMapping(value = "urlUploadVideoId")
	public String urlUploadVideoId(HttpServletRequest request, int id) {
		log.info("待上传到的文件夹ID:" + id);
		request.getSession().setAttribute("UploadVideoId", id);
		log.info("request.getSession().getAttribute('UploadVideoId'):" + request.getSession().getAttribute("UploadVideoId"));
		return "manageVideoUpload";
	}
	
	/**
	 * @Title: urlUploadVideo 
	 * @Description: 后台：上传视频到云
	 * @param request
	 * @param id
	 * @return         
	 * @throws 
	 * @author: fxy
	 * @date: 2018年3月16日
	 */
	@RequestMapping(value = "urlUploadVideo")
	public String urlUploadVideo(HttpServletRequest request, @RequestParam("file") MultipartFile[] file,
			String info) {
		
		log.info("待上传到的视频描述:" + info);
		
		// 用于标识是否有文件准备上传
		String strFile = "";
		for (MultipartFile file1 : file) {
			// 获得上传的文件名
			strFile = file1.getOriginalFilename();
			log.info("文件名:" + strFile);
		}

		//重要：存能够上传的VideoList，用来在下一个页面显示缩略图和点击图片进入视频播放
		List<VideoList> forNextVideoLists = new ArrayList<>();
		
		// 如果没有选择文件就点击上传
		if (strFile.isEmpty()) {
			log.info("没有选择文件！");
			request.getSession().setAttribute("loadErr", "您没有选择文件上传！");
			return "manageVideoUpload";
		} else {
			log.info("有文件准备上传！");

			//获得要上传视频的用户ID
			int intUserId = (int) request.getSession().getAttribute("intUserId");
			log.info("intUserId:" + intUserId);
			
			// 获得要上传的文件路径的ID
			int intUploadVideoId = (int) request.getSession().getAttribute("UploadVideoId");
			log.info("intUploadVideoId:" + intUploadVideoId);
			
			// 判断videofileList中是否有该id对应的值，因为他们是外键
			// 根据intUploadVideoId获得folder路径
			VideoFolder videoFolderById = videoFolderService.selectVideoFolderById(intUploadVideoId);
			//String strFolderPath = videoFolderById.getFolder()+"/";
			String strFolderPath = videoFolderById.getFolder();
			log.info("strFolderPath:" + strFolderPath);

			
			// 现在要上传的文件名
			String nowFileName = "";
			// 现在要上传的文件后缀
			String Suffix = "";
			// 判断是否上传和插入数据库成功
			int num = 0;
			// 计算数据库中已经存在的文件名跟要上传的文件名相等的个数
			// 如果为0就表示可以上传
			int intCount = 0;
			boolean flag = false;

			// 根据文件夹id获得数据库中该文件夹下的VideoList表的所有文件名
			// VideoFolder的id = VideoList的path
			List<VideoList> videoListById = videoListService.selectAllNameById(intUploadVideoId);
			for (VideoList videoList : videoListById) {
				log.info("videoList.getName():"+videoList.getName());
			}
			log.info("videoListById.size():"+videoListById.size());
			
			//初始化云
			UpYun upyun = new UpYun("cloud-video", "fxy", "20561yuan");
			
			// 遍历上传的文件，得到文件名判断数据库中是否存在
			for (MultipartFile file1 : file) {
				// 获得上传的文件名
				nowFileName = file1.getOriginalFilename();
				log.info("nowFileName文件名:" + nowFileName);
				
				//如果文件名是视频格式就上传
				//MP4，AVI，3GP，RMVB，WAV，MPG，MKV，VOB，MOV，FLV，SWF
				String[] strArray = {
						"MP4","AVI","3GP","RMVB","WAV","MPG","MKV","VOB","MOV","FLV","SWF",
						"mp4","avi","3gp","rmvb","wav","mpg","mkv","vob","mov","flv","swf"
				};
				int intVideoTypeCount = 0;
				//获得要上传的文件后缀
				String strSuffix = nowFileName.substring(nowFileName.lastIndexOf(".")+1);
				log.info("后缀为:"+strSuffix);
				for (String strVideoType : strArray) {
					if (strSuffix.equals(strVideoType)) {
						intVideoTypeCount++;
					} 
				}
				log.info("intVideoTypeCount:"+intVideoTypeCount);
				//总共视频格式22种，每一个视频在一轮判断下来，只能符合一种格式，就会是1
				if (intVideoTypeCount!=1) {
					log.info("该文件不是视频格式！");
					request.getSession().setAttribute("uploadTypeErr", nowFileName + ":该文件不是视频格式，请重新命名上传！");
					return "manageVideoUpload";
				} else {

					//如果该ID对应文件夹下没有视频
					if (videoListById.size() == 0) {

						//把视频先上传到本地
						flag = uplocal(file1, "E:/"+strFolderPath+nowFileName);
						log.info("成功上传到本地:"+flag);
						log.info("strFolderPath:"+strFolderPath);
						log.info("nowFileName:"+nowFileName);
						log.info("strFolderPath.substring(0, strFolderPath.length()-1):"+strFolderPath.substring(0, strFolderPath.length()-1));
						
						// 采用数据流模式上传文件（节省内存）,自动创建父级目录
						String lastUploadPath = "E:\\"+ strFolderPath + nowFileName;
						log.info("lastUploadPath:"+lastUploadPath);
						File fileYun = new File(lastUploadPath);
						
						try {
							upyun.setContentMD5(UpYun.md5(fileYun));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							//flag = upyun.writeFile("/"+strFolderPath.substring(0, strFolderPath.length()-1), fileYun, true);
							flag = upyun.writeFile("/"+strFolderPath+nowFileName, fileYun, true);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UpException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (flag) {
							log.info("上传成功了。。。");
							//先获得该用户id已经发表的视频数
							int intCountVideos = userService.selectByIdForVideos(intUserId);
							
							//封装发表文章后的用户信息
							User user = new User();
							user.setId(intUserId);
							user.setVideos(intCountVideos);
							
							//根据用户ID把用户发表的视频数加1
							int intFlagUserArticlesAdd = userService.addVideos(user); 
							log.info("intFlagUserArticlesAdd不为0即表示用户发表的视频数更新成功:"+intFlagUserArticlesAdd);
//begin:截取视频缩略图，一个视频借小中大三张						
							//视频的真实路径
							String strVideoRealPath  = "E:/"+ strFolderPath+nowFileName;
							//strVideoRealPath:E:/777/3.mp4
							log.info("strVideoRealPath:"+strVideoRealPath);
							request.getSession().setAttribute("strVideoRealPath", strVideoRealPath);
							//根据文件名去截取三种格式的图片669*357 214*263 198*231
							String strImagesPrefix = nowFileName.substring(0,nowFileName.lastIndexOf("."));
							log.info("文件名前缀为:"+strImagesPrefix);
//截图的路径（输出路径），生成的缩略图根据文件名去命名 							
String strSmallPath ="E:\\GraduationProjectV1\\wordspace\\demo2\\src\\"
		+ "main\\resources\\static\\myImages\\small\\"+strImagesPrefix+".jpg";
							
							//截取的图片尺寸
							String strSmallImage  = "  198*231  ";
							
try {   
	//调用批处理文件 ：生成小图  
	Runtime.getRuntime().exec(
		"E:\\ffmpeg\\ffmpeg-3.4.2-win64-static\\bin\\ffmpeg.exe  -i  "
	    + strVideoRealPath
		+ "  -y -f mjpeg -ss 3 -t 0.001 -s   "+strSmallImage+"   "
		+ strSmallPath
    ); 
	log.info("执行了视频截图操作");
} catch (IOException e) {   
	e.printStackTrace();   
}
//end:截取视频缩略图，一个视频借小中大三张							
							// 把文件名插入数据库的视频信息表
							VideoList videoList = new VideoList();
							videoList.setPublishTime(new Date());
							videoList.setName(nowFileName);
							videoList.setPath(intUploadVideoId);
							videoList.setInfo(info);
							videoList.setUserId(intUserId);
							
							//每个视频有三个尺寸的图片
							
							videoList.setSmallimage("myImages/small/"+strImagesPrefix+".jpg");
							num = videoListService.add(videoList);
							
							//重要：存能够上传的VideoList，用来在下一个页面显示缩略图和点击图片进入视频播放
							forNextVideoLists.add(videoList);
							
							//用于显示刚才上传的视频
							request.getSession().setAttribute("videoList", videoList);
							log.info("把文件信息插入数据库,num不为0即插入成功:" + num);
						} else {
							log.info("上传失败了。。。");
							return "manageVideoUpload";
						}
					} else {
						// 遍历folder表中id对应fileList的fileFolder，然后获得fileName，判断跟要上传的文件名是否相等
						for (VideoList videoList : videoListById) {
							if (videoList.getName().equals(nowFileName)) {
								intCount++;
								log.info("intCount:" + intCount);
							}
						}
						// begin:上传
						if (intCount != 0) {
							log.info("该文件已经上传过了！");
							request.getSession().setAttribute("uploadTwoErr", nowFileName + ":该文件名已经存在，请重新命名上传！");
							return "manageVideoUpload";
						} else {
							//把视频先上传到本地
							flag = uplocal(file1, "E:/"+strFolderPath+nowFileName);
							log.info("成功上传到本地:"+flag);
							
							// 采用数据流模式上传文件（节省内存）,自动创建父级目录
							log.info("strFolderPath:"+strFolderPath);
							log.info("nowFileName:"+nowFileName);
							log.info("strFolderPath.substring(0, strFolderPath.length()-1):"+strFolderPath.substring(0, strFolderPath.length()-1));
							String lastUploadPath = "E:\\"+ strFolderPath+ nowFileName;
							log.info("lastUploadPath:"+lastUploadPath);
							File fileYun = new File(lastUploadPath);
							log.info("fileYun:"+fileYun);
							try {
								upyun.setContentMD5(UpYun.md5(fileYun));
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							try {
								flag = upyun.writeFile("/"+strFolderPath+nowFileName, fileYun, true);
							} catch (IOException e) {
								e.printStackTrace();
							} catch (UpException e) {
								e.printStackTrace();
							}
							
							//视频的真实路径
							//TODO:
							String strVideoRealPath  =  "E:/"+strFolderPath+nowFileName;
							//strVideoRealPath:E:/777/3.mp4
							log.info("strVideoRealPath:"+strVideoRealPath);
							request.getSession().setAttribute("strVideoRealPath", strVideoRealPath);
//begin:截取视频缩略图，一个视频借小中大三张							
							//根据文件名去截取三种格式的图片669*357 214*263 198*231
							String strImagesPrefix = nowFileName.substring(0,nowFileName.lastIndexOf("."));
							log.info("文件名前缀为:"+strImagesPrefix);
//截图的路径（输出路径），生成的缩略图根据文件名去命名 							
String strSmallPath ="E:\\GraduationProjectV1\\wordspace\\demoThree\\src\\"
		+ "main\\resources\\static\\myImages\\small\\"+strImagesPrefix+".jpg";

							
							//截取的图片尺寸
							String strSmallImage  = "  198*231  ";
							
							
try {   
	//调用批处理文件 ：生成小图  
	Runtime.getRuntime().exec(
		"E:\\ffmpeg\\ffmpeg-3.4.2-win64-static\\bin\\ffmpeg.exe  -i  "
	    + strVideoRealPath
		+ "  -y -f mjpeg -ss 3 -t 0.001 -s   "+strSmallImage+"   "
		+ strSmallPath
    ); 
	log.info("执行了视频截图操作");
} catch (IOException e) {   
	e.printStackTrace();   
}
//end:截取视频缩略图，一个视频借小中大三张							
log.info("flag:"+flag);
							if (flag) {
								log.info("上传成功了。。。");
								
								// 把文件名插入数据库的视频信息表
								VideoList videoList = new VideoList();
								videoList.setPublishTime(new Date());
								videoList.setName(nowFileName);
								videoList.setPath(intUploadVideoId);
								videoList.setInfo(info);
								videoList.setUserId(intUserId);
								
								//每个视频有三个尺寸的图片
								videoList.setSmallimage("myImages/small/"+strImagesPrefix+".jpg");
								num = videoListService.add(videoList);
								
								//重要：存能够上传的VideoList，用来在下一个页面显示缩略图和点击图片进入视频播放
								forNextVideoLists.add(videoList);
								
								log.info("把视频信息插入数据库,num不为0即插入成功:" + num);
							} else {
								log.info("上传失败了。。。");
								return "manageVideoUpload";
							}
						}
						// end:上传
					}
				}
				
			}

			if (flag) {
				// 获得该文件夹下的全部的视频
				List<VideoList> videoLists = videoListService.selectAllFileByPath(intUploadVideoId);
				request.getSession().setAttribute("videoLists", videoLists);
				
				//用于显示刚才上传的视频
				return "manageUploadVideoResult";
			} else {
				request.getSession().setAttribute("uploadTwoErr", nowFileName + ":该文件名已经存在，请重新命名上传！");
				log.info("文件上传失败了。。。");
				return "manageVideoUpload";
			}

		}
	}
	
	// 上传文件的代码
	public boolean uplocal(MultipartFile file, String filePath) {

		File desFile = new File(filePath);
		if (!desFile.getParentFile().exists()) {
			desFile.mkdirs();
		}
		try {
			file.transferTo(desFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	
	/**
	 * @Title: urlShowAllVideo
	 * @Description: 前台：显示全部视频的缩略图和视频
	 * @param request
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@ResponseBody
	@RequestMapping(value = "urlShowAllVideo", method = RequestMethod.POST)
	public Msg urlShowAllVideo(HttpServletRequest request,int pageNum) {
		//获得全部视频及其缩略图
		List<VideoList> listVideoLists = videoListService.selectAll();
		int totalCount = listVideoLists.size();
		Page page = PageUtil.createPage(6, totalCount, pageNum);
		int intIndex =  page.getBeginIndex();
		int intEveryPage = page.getEveryPage();
		log.info("intIndex:"+intIndex+"   intEveryPage:"+intEveryPage);
		List<VideoList> videoLists = videoListService.findByPage(intIndex,intEveryPage);
		List<String> nameList = new ArrayList<>();
		return Msg.success().add("pageList", page).add("listVideoForPlay", videoLists);

	}
	
	//根据选中的文件夹显示该文件夹下全部视频
	@ResponseBody
	@RequestMapping(value = "urlShowFolderVideo", method = RequestMethod.POST)
	public Msg urlShowFolderVideo(HttpSession session,int pageNum) {
		String folderForVideo = (String) session.getAttribute("folderForVideo");
		//根据文件夹名称获得id
		List<VideoFolder> videoFolders = videoFolderService.selectPath(folderForVideo);
		log.info("id:"+videoFolders.get(0).getId());
		//获得全部视频及其缩略图
		List<VideoList> listVideoLists = videoListService.selectAllFileByPath(videoFolders.get(0).getId());
		
		log.info("listVideoLists:"+listVideoLists);
		return Msg.success().add("listVideoForPlay", listVideoLists);
	}
	
	/**
	 * @Title: urlPlayVideo
	 * @Description: 前台：跳转到播放视频页面
	 * @param request
	 * @return String
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@RequestMapping(value = "urlPlayVideo", method = RequestMethod.GET)
	public String urlPlayVideo(HttpServletRequest request,int id) {
		log.info("id:"+id);
		//获得该id对应的视频实体类
		VideoList videoList = videoListService.selectById(id);
		//根据该实体类获得name视频名称
		String strVideoName = videoList.getName();
		
		//根据该实体类获得path = 视频文件夹的id
		int intPath = videoList.getPath();
		//根据该id获得VideoFolder的folder，即文件存放在云的路径
		VideoFolder videoFolder = videoFolderService.selectById(intPath);
		String strFolder = videoFolder.getFolder();
		//拼接云播放路径前缀+ 文件夹路径 + 视频名称
		String strUrlPlay = "http://cloud-video.test.upcdn.net/" + strFolder + strVideoName;
		log.info("strUrlPlay:"+strUrlPlay);
		//存到session
		request.getSession().setAttribute("strUrlPlay", strUrlPlay);
		request.getSession().setAttribute("strFolder", strFolder);
		request.getSession().setAttribute("videoList", videoList);
		return "modelPlayVideo";
	}
	
	
	/**
	 * @Title: urlPlayLast
	 * @Description: 前台：播放视频
	 * @param request
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月7日
	 */
	@ResponseBody
	@RequestMapping(value = "urlPlayLast", method = RequestMethod.POST)
	public Msg urlPlayLast(HttpServletRequest request) {
		String strUrlPlay =  (String) request.getSession().getAttribute("strUrlPlay");
		String strFolder =  (String) request.getSession().getAttribute("strFolder");
		VideoList videoList =  (VideoList) request.getSession().getAttribute("videoList");
		String flagVideoPre =  (String) request.getSession().getAttribute("flagVideoPre");
		String flagVideoNex =  (String) request.getSession().getAttribute("flagVideoNex");
		
		//获得全部视频的评论并显示
		List<VideoComment> videoComments = videoCommentService.showById(videoList.getId());
		
		return Msg.success().add("strUrlPlay", strUrlPlay)
				.add("strFolder", strFolder).add("flagVideoNex", flagVideoNex)
				.add("videoList", videoList).add("flagVideoPre", flagVideoPre)
				.add("videoComments", videoComments);
	}
	
	/**
	 * @Title: urlSaveVideoAndTime
	 * @Description: 前台：保存该用户播放视频的总时间和视频名字（学生角色）
	 * @param request
	 * @return Msg
	 * @throws 
	 * @author fxy 
	 * @date 2018年3月30日
	 */
	@ResponseBody
	@RequestMapping(value = "urlSaveVideoAndTime", method = RequestMethod.POST)
	public Msg urlSaveVideoAndTime(HttpServletRequest request,String strTime) {
		
		HttpSession session = request.getSession();
		int intUserId = (int) session.getAttribute("intUserId");
		
		//如果role_id为2是学生，需要保存
		
		//根据id去获得role_id
		User user = userService.selectByIdForName(intUserId);
		int intRoleId = user.getRoleId();
		log.info("intRoleId:"+intRoleId);
		
		//判断用户使教师还是学生
		if (intRoleId == 1) {
			//如果role_id为1是教师，不需要保存观看视频的信息
			return Msg.success();
		} else {
			double dblTime = Double.parseDouble(strTime);
			// 获得用户观看视频的时长
			long lngTime = new Double(dblTime).longValue();
			log.info("lngTime:"+lngTime);
			long lngHour = lngTime / 3600;
			long lngMinutes = ( lngTime - lngHour * 3600 ) / 60;
			long lngSecond = ( lngTime - lngHour * 3600 ) % 60;
			//存入数据库的格式
			
			String strPlayTime = lngHour +"小时"+ lngMinutes+"分钟"+ lngSecond+"秒";
			
			// 获得用户播放的视频名
			String strUrlPlay =  (String) request.getSession().getAttribute("strUrlPlay");
			String strVideoName = strUrlPlay.substring(strUrlPlay.lastIndexOf("/")+1,strUrlPlay.length());
			log.info("strVideoName:"+strVideoName);
			
			//把用户观看的信息存入数据库
			LearnRate learnRate = new LearnRate();
			learnRate.setUsername(user.getName());
			learnRate.setVideoname(strVideoName);
			learnRate.setPlaytime(strPlayTime);
			
			//存入数据库
			int intFlag = learnRateService.save(learnRate);
			log.info("intFlag:"+intFlag);
			return Msg.success();
		}
		
	}
	
	//跳到上一个视频，下一个视频
	@RequestMapping(value = "urlPlayVideoNex", method = RequestMethod.GET)
	public String urlPlayVideoNex(HttpServletRequest request,int id,int path,String time) throws ParseException {
		//标识如果没有下一节
		request.getSession().setAttribute("flagVideoNex","");
		
		
		//获得SimpleDateFormat类，我们转换为yyyy-MM-dd的时间格式  
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
            //使用SimpleDateFormat的parse()方法生成Date  
            Date date = sf.parse(time);          //将string转换成固定
		
		log.info("date:"+date);
		
		//根据path去获得全部id
		List<VideoList> intIDS = videoListService.selectByPath(path,date);
		int intCount = 0;
		for (VideoList videoList : intIDS) {
			if (videoList.getId() != id &&
					(videoList.getId() > id)) {
				intCount = videoList.getId();
				break;
			}
		}
		
		if (intCount > 0) {
			//获得该id对应的视频实体类
			VideoList videoList = videoListService.selectById(intCount);
			//根据该实体类获得name视频名称
			String strVideoName = videoList.getName();
			
			//根据该实体类获得path = 视频文件夹的id
			int intPath = videoList.getPath();
			//根据该id获得VideoFolder的folder，即文件存放在云的路径
			VideoFolder videoFolder = videoFolderService.selectById(intPath);
			String strFolder = videoFolder.getFolder();
			//拼接云播放路径前缀+ 文件夹路径 + 视频名称
			String strUrlPlay = "http://cloud-video.test.upcdn.net/" + strFolder + strVideoName;
			log.info("strUrlPlay:"+strUrlPlay);
			//存到session
			request.getSession().setAttribute("strUrlPlay", strUrlPlay);
			request.getSession().setAttribute("strFolder", strFolder);
			request.getSession().setAttribute("videoList", videoList);
		}else {
			//标识如果没有下一节
			request.getSession().setAttribute("flagVideoNex","");
		}
		return "modelPlayVideo";
		
	}
	
	@RequestMapping(value = "urlPlayVideoPre", method = RequestMethod.GET)
	public String urlPlayVideoPre(HttpServletRequest request,int id,int path,String time) throws ParseException {
		//标识如果没有上一节
		request.getSession().setAttribute("flagVideoPre","");
		//获得SimpleDateFormat类，我们转换为yyyy-MM-dd的时间格式  
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
            //使用SimpleDateFormat的parse()方法生成Date  
            Date date = sf.parse(time);          //将string转换成固定
		
		log.info("date:"+date);
		
		//根据path去获得全部id
		List<VideoList> intIDS = videoListService.selectByPathPre(path,date);
		int intCount = 0;
		for (VideoList videoList : intIDS) {
			if (videoList.getId() != id &&
					(videoList.getId() < id)) {
				intCount = videoList.getId();
				break;
			}
		}
		
		if (intCount > 0) {
			//获得该id对应的视频实体类
			VideoList videoList = videoListService.selectById(intCount);
			//根据该实体类获得name视频名称
			String strVideoName = videoList.getName();
			
			//根据该实体类获得path = 视频文件夹的id
			int intPath = videoList.getPath();
			//根据该id获得VideoFolder的folder，即文件存放在云的路径
			VideoFolder videoFolder = videoFolderService.selectById(intPath);
			String strFolder = videoFolder.getFolder();
			//拼接云播放路径前缀+ 文件夹路径 + 视频名称
			String strUrlPlay = "http://cloud-video.test.upcdn.net/" + strFolder + strVideoName;
			log.info("strUrlPlay:"+strUrlPlay);
			//存到session
			request.getSession().setAttribute("strUrlPlay", strUrlPlay);
			request.getSession().setAttribute("strFolder", strFolder);
			request.getSession().setAttribute("videoList", videoList);
		}else {
			//标识如果没有上一节
			request.getSession().setAttribute("flagVideoPre","");
		}
		return "modelPlayVideo";
		
	}
}
