package com.italk.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.italk.bean.Cluster;
import com.italk.bean.UploadFile;

/**
 * 文件接口
 * @author luoshengsha
 *
 * 2016年6月27日-下午5:33:53
 */
public interface UploadFileService {

	public void save(UploadFile file);
	
	public void delete(String uuid);
	
	public Page<UploadFile> getFiles(Cluster cluster, PageRequest page);
}
