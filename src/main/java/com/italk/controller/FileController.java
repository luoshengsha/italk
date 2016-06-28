package com.italk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.Cluster;
import com.italk.bean.UploadFile;
import com.italk.service.ClusterService;
import com.italk.service.UploadFileService;
import com.italk.utils.DateUtil;
import com.italk.utils.PropertiesUtil;
import com.italk.vo.ReturnObject;
import com.italk.vo.UploadFileVo;

/**
 * 文件控制器
 * @author luoshengsha
 *
 * 2016年6月27日-下午8:32:01
 */
@RestController
@RequestMapping(value="/center/file")
public class FileController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Resource
	private UploadFileService fileService;
	@Resource
	private ClusterService clusterService;

	/**
	 * 获取群组文件
	 * @param clusterid
	 * @param pageNo
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value="/get_cluster_files")
	public ReturnObject getClusterFiles(@RequestParam("clusterid")String clusterid, @RequestParam(value="pageNo",defaultValue="1")int pageNo, 
			@RequestParam(value="pagesize",defaultValue="10")int pagesize) {
		ReturnObject obj = new ReturnObject();
		try {
			Cluster cluster = clusterService.find(clusterid);
			PageRequest page = new PageRequest(pageNo-1,pagesize,new Sort(Direction.DESC, "createtime"));
			Page<UploadFile> recordpage = fileService.getFiles(cluster, page);
			
			List<UploadFileVo> uploadFileVos = new ArrayList<UploadFileVo>();
			List<UploadFile> uploadFiles = recordpage.getContent();
			for(UploadFile file : uploadFiles) {
				UploadFileVo fv = new UploadFileVo();
				fv.setId(file.getUuid());
				fv.setName(file.getName());
				fv.setType(file.getType());
				fv.setPath(PropertiesUtil.file_url+file.getFilepath());
				fv.setCreatetime(DateUtil.date2Str(file.getCreatetime()));
				
				uploadFileVos.add(fv);
			}
			obj.setData(uploadFileVos);
			obj.setStatus(1);
			obj.setMessage("获取成功");
		} catch (Exception e) {
			obj.setStatus(1);
			obj.setMessage("获取失败");
			logger.error("获取好友聊天记录失败",e);
		}
		
		return obj;
	}
	
}
