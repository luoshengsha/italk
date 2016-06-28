package com.italk.dao;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.italk.bean.Cluster;

/**
 * 群组dao
 * @author luoshengsha
 *
 * 2016年6月21日-下午9:43:02
 */
public interface ClusterDao extends CrudRepository<Cluster, Integer> {

	/**
	 * 根据uuid删除
	 * @param uuid
	 */
	@Modifying
	@Query("delete from Cluster where uuid=?1")
	public void delete(String uuid);
	
	/**
	 * 通过uuid获取群组
	 * @param uuid
	 * @return
	 */
	@Query("select c from Cluster c where uuid=?1")
	public Cluster find(String uuid);
	
	/**
	 * 获取用户已加入的群组
	 * @param owner
	 * @return
	 */
	@OrderBy(value="createTime DESC")
	@Query(value="select c.* from t_cluster c left join t_cluster_user cu on cu.cluster_id=c.id where cu.user_id=?1",nativeQuery=true)
	public List<Cluster> getByUser(int userid);
}
