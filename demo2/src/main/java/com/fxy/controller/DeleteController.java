package com.fxy.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fxy.bean.FileList;
import com.fxy.bean.Folder;
import com.fxy.service.FileListService;
import com.fxy.service.FolderService;

@Controller
public class DeleteController {
	@Autowired
	private FileListService fileListService;
	
	@Autowired
	private FolderService folderService;
	protected static final Logger logger = LoggerFactory.getLogger(DeleteController.class);
	
	
	/**
	 * 批量文件删除
	 * @param request
	 * @param rename
	 * @param path
	 * @return
	 */
	/**
	 * 批量删除 情况一:checkbox只选中一个 情况二:checkboxt选中多个
	 */
	@RequestMapping(value = "deleteByCheckbox")
	public String deleteByCheckbox(HttpServletRequest request, String ids) {
		logger.info("deleteByCheckbox加载完毕" + ids);

		if (ids.contains("-")) {
			// 多选:删除多个文件,全路径
			String[] str_ids = ids.split("-");
			for (String path : str_ids) {
				// ①删除数据库中文件的路径，就是filelist的filename字段
				// 获得文件名及其后缀
				File oldFile = new File(path);
				String name = oldFile.getName();
				logger.info("filelist表中的文件夹路径为filename:" + name);

				// 多-1是因为全路径时候多了一个/
				String oldFolder = path.substring(0, path.length() - name.length() - 1);
				logger.info("folder表中的文件夹路径为oldFolder:" + oldFolder);

				// 根据folder文件夹名称获得folder表的id
				int folderId = folderService.selectByFolder(oldFolder);
				logger.info("folderId:" + folderId);

				// 该folderId = filefolder, filename = name -->获得filelist的id
				int fileListId = fileListService.selectByNameAndFolder(name, folderId);
				logger.info("fileListId:" + fileListId);

				// 根据主键去删除单个文件
				int num = fileListService.deleteByPrimaryKey(fileListId);
				logger.info("num:" + num);

				// ②删除硬盘上的单个文件
				boolean flag = deleteFile(path);
				logger.info("flag:" + flag);

				// 获得全部的文件夹，在页面中显示
				List<Folder> lFolders = folderService.selectAll();
				request.getSession().setAttribute("lFolders", lFolders);
			}

		} else {
			// 单选:删除单个
			// ①删除数据库中文件的路径，就是filelist的filename字段
			// 获得文件名及其后缀
			File oldFile = new File(ids);
			String name = oldFile.getName();
			logger.info("filelist表中的文件夹路径为filename:" + name);

			// 多-1是因为全路径时候多了一个/
			String oldFolder = ids.substring(0, ids.length() - name.length() - 1);
			logger.info("folder表中的文件夹路径为oldFolder:" + oldFolder);

			// 根据folder文件夹名称获得folder表的id
			int folderId = folderService.selectByFolder(oldFolder);
			logger.info("folderId:" + folderId);

			// 该folderId = filefolder, filename = name -->获得filelist的id
			int fileListId = fileListService.selectByNameAndFolder(name, folderId);
			logger.info("fileListId:" + fileListId);

			// 根据主键去删除单个文件
			int num = fileListService.deleteByPrimaryKey(fileListId);
			logger.info("num:" + num);

			// ②删除硬盘上的单个文件
			boolean flag = deleteFile(ids);
			logger.info("flag:" + flag);

			// 获得全部的文件夹，在页面中显示
			List<Folder> lFolders = folderService.selectAll();
			request.getSession().setAttribute("lFolders", lFolders);
		}

		return "manageShowAllFolder";
	}
	
	
	
	
	/**
	 * 单个文件删除
	 * @param request
	 * @param rename
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "deleteOneFile")
	public String deleteOneFile(HttpServletRequest request, String path) {
		logger.info("deleteOneFile加载完毕" + path);
		
		//①删除数据库中文件的路径，就是filelist的filename字段
		//获得文件名及其后缀
		File oldFile = new File(path);
		String name = oldFile.getName();
		logger.info("filelist表中的文件夹路径为filename:" + name);
		
		//多-1是因为全路径时候多了一个/
		String oldFolder = path.substring(0, path.length() - name.length() - 1);
		logger.info("folder表中的文件夹路径为oldFolder:" + oldFolder);

		// 根据folder文件夹名称获得folder表的id
		int folderId = folderService.selectByFolder(oldFolder);
		logger.info("folderId:" + folderId);

		// 该folderId = filefolder, filename = name -->获得filelist的id
		int fileListId = fileListService.selectByNameAndFolder(name, folderId);
		logger.info("fileListId:" + fileListId);

		// 根据主键去删除单个文件
		int num = fileListService.deleteByPrimaryKey(fileListId);
		logger.info("num:" + num);
		
		//②删除硬盘上的单个文件
		boolean flag = deleteFile(path);
		logger.info("flag:"+flag);
		
		// 获得全部的文件夹，在页面中显示
		List<Folder> lFolders = folderService.selectAll();
		request.getSession().setAttribute("lFolders", lFolders);
		
		return "manageShowAllFolder";
	}
	
	
	
	
	 /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
            	logger.info("删除单个文件" + fileName + "成功！");
                return true;
            } else {
            	logger.info("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
        	logger.info("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
}
