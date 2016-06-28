package com.italk.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.italk.bean.Cluster;
import com.italk.bean.UploadFile;
import com.italk.dao.UploadFileDao;

/**
 * 文件接口实现
 * @author luoshengsha
 *
 * 2016年6月27日-下午7:29:52
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {
	
	@Resource
	private UploadFileDao fileDao;

	@Override
	public void save(UploadFile file) {
		fileDao.save(file);
	}

	@Override
	public void delete(String uuid) {
		fileDao.delete(uuid);
	}

	@Override
	public Page<UploadFile> getFiles(Cluster cluster, PageRequest page) {
		return fileDao.getFiles(cluster, page);
	}

}
