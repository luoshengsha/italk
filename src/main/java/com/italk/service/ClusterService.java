package com.italk.service;

import java.util.List;

import com.italk.bean.Cluster;
import com.italk.bean.User;

/**
 * 群组接口
 * @author luoshengsha
 *
 * 2016年6月21日-下午9:58:18
 */
public interface ClusterService {

	/**
	 * 保存群组
	 * @param cluster
	 */
	public void save(Cluster cluster);
	
	/**
	 * 更新群组
	 * @param cluster
	 */
	public void update(Cluster cluster);
	
	/**
	 * 删除群组
	 * @param uuid
	 */
	public void delete(String uuid);
	
	/**
	 * 根据uuid获取群组
	 * @param uuid
	 * @return
	 */
	public Cluster find(String uuid);
	
	/**
	 * 获取用户已加入的群
	 * @param owner
	 * @return
	 */
	public List<Cluster> getByUser(User user);
	
	/**
	 * 添加组员
	 * @param cluster
	 * @param member
	 */
	public void pushMember(Cluster cluster, User member);
	
	/**
	 * 删除组员
	 * @param cluster
	 * @param member
	 */
	public void delMember(Cluster cluster, User member);
}
