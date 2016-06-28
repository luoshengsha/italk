package com.italk.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.italk.bean.FriendsGroup;
import com.italk.bean.User;
import com.italk.dao.FriendsGroupDao;

/**
 * 好友分组service实现类
 * @author luoshengsha
 *
 * 2016年6月14日-下午4:26:39
 */
@Service
@Transactional
public class FriendsGroupServiceImpl implements FriendsGroupService {

	@Resource
	private FriendsGroupDao friendGroupDao;
	
	@Override
	public void save(FriendsGroup group) {
		friendGroupDao.save(group);
	}

	@Override
	public void update(FriendsGroup group) {
		friendGroupDao.save(group);
	}

	@Override
	public void delete(String uuid) {
		FriendsGroup group = friendGroupDao.find(uuid);
		
		//默认分组不允许删除
		if(group.isDefault()) {
			return;
		}
		
		//默认分组
		FriendsGroup defaultGroup = friendGroupDao.getDefaultGroup(group.getCreater());
		
		//如果要删除的分组中有好友，将好友分配到默认分组中
		if(!group.getMembers().isEmpty()) {
			defaultGroup.getMembers().addAll(group.getMembers());
			friendGroupDao.save(defaultGroup);
		}
		
		//删除分组
		friendGroupDao.delete(group.getId());
	}

	@Override
	public FriendsGroup find(int id) {
		return friendGroupDao.findOne(id);
	}

	@Override
	public FriendsGroup find(String uuid) {
		return friendGroupDao.find(uuid);
	}

	@Override
	public void pushMember(FriendsGroup group, User user) {
		group.getMembers().add(user);
		friendGroupDao.save(group);
	}

	@Override
	public void moveMember(FriendsGroup fromGroup, FriendsGroup toGroup, User user) {
		//把好友添加至新分组中
		toGroup.getMembers().add(user);
		friendGroupDao.save(toGroup);
		
		//把好友从原分组中删除
		fromGroup.getMembers().remove(user);
		friendGroupDao.save(fromGroup);
		
	}

	@Override
	public void deleteMember(FriendsGroup group, User user) {
		group.getMembers().remove(user);
		friendGroupDao.save(group);
	}

	@Override
	public List<FriendsGroup> getByCreater(User creater) {
		// TODO Auto-generated method stub
		return friendGroupDao.getByCreater(creater);
	}
}
