package com.italk.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.italk.bean.Cluster;
import com.italk.bean.Member;
import com.italk.bean.User;
import com.italk.dao.ClusterDao;

/**
 * 群组接口实现
 * @author luoshengsha
 *
 * 2016年6月30日-下午8:54:54
 */
@Service
public class ClusterServiceImpl implements ClusterService {

	@Resource
	private ClusterDao clusterDao;
	
	@Override
	@Transactional
	public void save(Cluster cluster) {
		clusterDao.save(cluster);
		//将群组加入进群里
		pushMember(cluster,cluster.getOwner());
	}

	@Override
	public void update(Cluster cluster) {
		clusterDao.save(cluster);
	}

	@Override
	public void delete(String uuid) {
		clusterDao.delete(uuid);
	}

	@Override
	public Cluster find(String uuid) {
		return clusterDao.find(uuid);
	}

	@Override
	public List<Cluster> getByUser(User user) {
		return clusterDao.getByUser(user.getId());
	}

	@Override
	public void pushMember(Cluster cluster, User user) {
		//创建组员
		Member member = new Member();
		member.setUser(user);
		member.setCluster(cluster);
		
		if(cluster.getOwner().getId() == user.getId()) {
			member.setIsmaster(true);
		}
		member.setNick(user.getNickname() == null ? user.getName() : user.getNickname());
		
		cluster.getMembers().add(member);
		
		clusterDao.save(cluster);
	}

	@Override
	public void delMember(Cluster cluster, User member) {
		cluster.getMembers().remove(member);
		clusterDao.save(cluster);
	}

}
