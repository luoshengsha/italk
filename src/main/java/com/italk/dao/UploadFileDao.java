package com.italk.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.italk.bean.Cluster;
import com.italk.bean.UploadFile;

/**
 * 上传文件dao
 * @author luoshengsha
 *
 * 2016年6月27日-下午5:02:08
 */
public interface UploadFileDao extends CrudRepository<UploadFile, Integer> {

	/**
	 * 分页查询文件
	 * @return
	 */
	@Query("select f from UploadFile f where f.cluster=?1")
	public Page<UploadFile> getFiles(Cluster cluster, Pageable page);
	
	/**
	 * 根据uuid删除
	 * @param uuid
	 */
	@Modifying
	@Query("delete from UploadFile where uuid=?1")
	public void delete(String uuid);
}
