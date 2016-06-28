package com.italk.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.italk.bean.Avatar;
import com.italk.bean.UploadFile;
import com.italk.service.AvatarService;
import com.italk.service.ClusterService;
import com.italk.service.UploadFileService;
import com.italk.utils.ImageUtil;
import com.italk.utils.PropertiesUtil;
import com.italk.utils.StringUtil;
import com.italk.utils.WebUtil;
import com.italk.vo.ReturnObject;

/**
 * 上传文件控制器
 * @author luoshengsha
 *
 * 2016年6月25日-下午3:37:48
 */
@RestController
@RequestMapping("/center/upload")
public class UploadController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	/** 分隔符 **/
	private final String LOCAL_FILE_SEPARATOR = System.getProperty("file.separator");
	
	@Resource
	private AvatarService avatarService;
	@Resource
	private UploadFileService uploadFileService;
	@Resource
	private ClusterService clusterService;

	/**
	 * 上传文件
	 * @param name
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value="/file")
	public ReturnObject uploadFile(@RequestParam("file") MultipartFile uploadFile,String clusterid,HttpServletRequest request){
		ReturnObject obj = new ReturnObject();
		
        if (!uploadFile.isEmpty()) {
        	
        	Calendar cal = Calendar.getInstance(); 
        	
        	//生成文件名称
			String fileName = generateFileName(cal);
			
			//生成文件夹名称
			String directoryPath = generateDirPath(cal);
			
			//生成文件路径
			String filePath = generateFilePath(uploadFile, fileName);
			
			try {
				File destDir = new File(PropertiesUtil.file_path + directoryPath);
				
				if(!destDir.exists()) {//创建目录
					destDir.mkdirs();
				}
				
				byte[] bytes = uploadFile.getBytes();  
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(PropertiesUtil.file_path + directoryPath + filePath)));  
				stream.write(bytes);
				stream.close();
				
				//保存文件到数据库
				uploadFileToDb(uploadFile, clusterid, request, directoryPath + filePath);
				
				obj.setStatus(1);
				obj.setMessage("上传文件成功");
				obj.setData(PropertiesUtil.file_url + (directoryPath + filePath).replace(LOCAL_FILE_SEPARATOR, "/"));
				return obj;
			} catch (FileNotFoundException e) {
				logger.error("文件不存在", e);
				obj.setStatus(0);
				obj.setMessage("文件不存在");
				return obj;
			} catch (IOException e) {
				logger.error("文件上传失败", e);
				obj.setStatus(0);
				obj.setMessage("文件上传失败");
				return obj;
			}
        } else {
        	obj.setStatus(0);
			obj.setMessage("请选择文件");
			return obj;
        }
    }

	/**
	 * 保存文件到数据库
	 * @param uploadFile
	 * @param clusterid
	 * @param request
	 * @param filePath
	 */
	private void uploadFileToDb(MultipartFile uploadFile, String clusterid, HttpServletRequest request, String filePath) {
		try {
			UploadFile file = new UploadFile();
			file.setUuid(WebUtil.createUuid());
			file.setName(uploadFile.getOriginalFilename());
			file.setFilepath(filePath.replace(LOCAL_FILE_SEPARATOR, "/"));
			file.setUser(WebUtil.getLoginUser(request));
			file.setCluster(clusterService.find(clusterid));
			file.setCreatetime(new Date());
			file.setType(uploadFile.getContentType());
			
			uploadFileService.save(file);
		} catch (Exception e) {
			logger.error("文件保存到数据库失败", e);
		}
	}
	
	/**
	 * 上传头像
	 * @param name
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value="/avatar")
	public ReturnObject uploadAvatar(@RequestParam("file") MultipartFile uploadFile,HttpServletRequest request){
		ReturnObject obj = new ReturnObject();
		
        if (!uploadFile.isEmpty()) {
        	
        	//不能上传非图片文件
        	if(!ImageUtil.isPic(uploadFile.getContentType())) {
        		obj.setStatus(0);
				obj.setMessage("不能上传非图片文件");
				return obj;
        	}
        	
        	Calendar cal = Calendar.getInstance();
        	
        	//生成文件名称
			String fileName = generateFileName(cal);
			
			//生成文件夹名称
			String directoryPath = generateDirPath(cal);
			
			//生成文件路径
			String filePath = generateFilePath(uploadFile, fileName);
			
			try {
				File destDir = new File(PropertiesUtil.avatar_path + directoryPath);
				
				if(!destDir.exists()) {//创建目录
					destDir.mkdirs();
				}
				
				byte[] bytes = uploadFile.getBytes();  
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(PropertiesUtil.avatar_path + directoryPath + filePath)));  
				stream.write(bytes);
				stream.close();
				
				//保存到数据库
				saveAvatarToDb(uploadFile, request, directoryPath + filePath);
				
				obj.setStatus(1);
				obj.setMessage("上传头像成功");
				obj.setData(PropertiesUtil.pic_url + (directoryPath + filePath).replace(LOCAL_FILE_SEPARATOR, "/"));
				return obj;
			} catch (FileNotFoundException e) {
				logger.error("头像文件不存在", e);
				obj.setStatus(0);
				obj.setMessage("头像文件不存在");
				return obj;
			} catch (IOException e) {
				logger.error("头像上传失败", e);
				obj.setStatus(0);
				obj.setMessage("头像上传失败");
				return obj;
			}
        } else {
        	obj.setStatus(0);
			obj.setMessage("请选择头像文件");
			return obj;
        }
    }

	/**
	 * 生成文件路径
	 * @param uploadFile
	 * @param fileName
	 * @return
	 */
	private String generateFilePath(MultipartFile uploadFile, String fileName) {
		String filePath = LOCAL_FILE_SEPARATOR + fileName + "." + getExt(uploadFile.getOriginalFilename());
		return filePath;
	}

	/**
	 * 生成文件夹名称
	 * @param cal
	 * @return
	 */
	private String generateDirPath(Calendar cal) {
		String directoryPath = cal.get(Calendar.YEAR) + LOCAL_FILE_SEPARATOR + StringUtil.fillupDecimal(cal.get(Calendar.MONTH)+1, "0", 2) + LOCAL_FILE_SEPARATOR 
				          	   + StringUtil.fillupDecimal(cal.get(Calendar.DAY_OF_MONTH), "0", 2);
		return directoryPath;
	}

	/**
	 * 生成文件名称
	 * @param cal
	 * @return
	 */
	private String generateFileName(Calendar cal) {
		String fileName = cal.get(Calendar.YEAR) 
						  + StringUtil.fillupDecimal(cal.get(Calendar.MONTH)+1, "0", 2) 
				          + StringUtil.fillupDecimal(cal.get(Calendar.DAY_OF_MONTH), "0", 2)
				          + String.valueOf(new Date().getTime());
		return fileName;
	}

	/**
	 * 保存头像信息到数据库
	 * @param uploadFile
	 * @param request
	 * @param filePath
	 */
	private void saveAvatarToDb(MultipartFile uploadFile, HttpServletRequest request, String filePath) {
		try {
			Avatar avatar = new Avatar();
			avatar.setUuid(WebUtil.createUuid());
			avatar.setName(uploadFile.getOriginalFilename());
			avatar.setImgpath(filePath.replace(LOCAL_FILE_SEPARATOR, "/"));
			avatar.setUser(WebUtil.getLoginUser(request));
			avatarService.save(avatar);
		} catch (Exception e) {
			logger.error("头像保存到数据库失败", e);
		}
	}
	
	@RequestMapping(value="/download")
	public ResponseEntity<InputStreamResource> downloadFile( Long id)  
            throws IOException {
        String filePath = "D:\\2016\\06\\25\\201606251466846077582.txt"; 
        FileSystemResource file = new FileSystemResource(filePath);  
        HttpHeaders headers = new HttpHeaders();  
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
        //headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "周报06-24.txt"));  
        headers.add("Content-Disposition", "周报0624.txt");
        headers.add("Pragma", "no-cache");  
        headers.add("Expires", "0");  
  
        return ResponseEntity  
                .ok()  
                .headers(headers)  
                .contentLength(file.contentLength())  
                .contentType(MediaType.parseMediaType("application/octet-stream"))  
                .body(new InputStreamResource(file.getInputStream()));  
    }
	
	/**
	 * 获取后缀名
	 * @param fileName
	 * @return 
	 * @author luoshengsha
	 */
	private String getExt(String fileName) {
		//当文件名为空或传入的文件不符合时，返回空字符串
		if(fileName == null || fileName.split("\\.").length == 0) return "";
		return fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
	}
}
