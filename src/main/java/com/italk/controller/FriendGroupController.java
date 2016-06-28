package com.italk.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.FriendsGroup;
import com.italk.bean.User;
import com.italk.service.FriendsGroupService;
import com.italk.service.UserService;
import com.italk.utils.WebUtil;
import com.italk.vo.ReturnObject;

/**
 * 好友分组控制器
 * @author luoshengsha
 *
 * 2016年6月21日-下午2:58:46
 */
@RestController
@RequestMapping(value="/center/group")
public class FriendGroupController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(FriendGroupController.class);
	
	@Autowired
	private FriendsGroupService groupService;
	@Resource
	private UserService userService;
	
	/**
	 * 创建好友分组
	 * @param userid
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/create")
	public ReturnObject create(String name,HttpServletRequest request) {
		ReturnObject obj = new ReturnObject();
		
		try {
			User creater = WebUtil.getLoginUser(request);
			FriendsGroup group = new FriendsGroup();
			group.setUuid(WebUtil.createUuid());
			group.setName(name);
			group.setCreater(creater);
			group.setCreateTime(new Date());
			
			groupService.save(group);
			
			obj.setStatus(1);
			obj.setMessage("创建成功");
		} catch (Exception e) {
			logger.error("创建好友分组失败", e);
			obj.setStatus(0);
			obj.setMessage("创建失败");
		}
		
		return obj;
	}
	
	/**
	 * 编辑好友分组
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ReturnObject edit(String id) {
		ReturnObject obj = new ReturnObject();
		
		try {
			FriendsGroup group = groupService.find(id);
			obj.setData(group);
			obj.setStatus(1);
			obj.setMessage("获取分组信息成功");
		} catch (Exception e) {
			logger.error("获取分组信息失败", e);
			obj.setStatus(0);
			obj.setMessage("获取分组信息失败");
		}
		
		return obj;
	}
	
	/**
	 * 更新分组
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/update")
	public ReturnObject update(String id, String name) {
		ReturnObject obj = new ReturnObject();
		
		try {
			FriendsGroup group = groupService.find(id);
			group.setName(name);
			
			groupService.update(group);
			obj.setStatus(1);
			obj.setMessage("更新分组信息成功");
		} catch (Exception e) {
			logger.error("更新分组信息失败", e);
			obj.setStatus(0);
			obj.setMessage("更新分组信息失败");
		}
		
		return obj;
	}
	
	/**
	 * 删除分组
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete")
	public ReturnObject delete(String id) {
		ReturnObject obj = new ReturnObject();
		
		try {
			groupService.delete(id);
			
			obj.setStatus(1);
			obj.setMessage("删除分组成功");
		} catch (Exception e) {
			logger.error("删除失败", e);
			obj.setStatus(0);
			obj.setMessage("删除失败");
		}
		
		return obj;
	}
	
	/**
	 * 分组中添加好友
	 * @param userid
	 * @param groupid
	 * @return
	 */
	@RequestMapping(value="/pushmember")
	public ReturnObject pushMember(@RequestParam(value="userid",required=true) String userid, @RequestParam(value="groupid",required=true) String groupid) {
		ReturnObject obj = new ReturnObject();
		
		try {
			User user = userService.find(userid);
			FriendsGroup group = groupService.find(groupid);
			
			groupService.pushMember(group, user);
			
			obj.setStatus(1);
			obj.setMessage("添加成功");
		} catch (Exception e) {
			logger.error("添加失败", e);
			obj.setStatus(0);
			obj.setMessage("添加失败");
		}
		
		return obj;
	}
	
	/**
	 * 移动好友至新的分组
	 * @param userid
	 * @param fromGroupid
	 * @param toGroupid
	 * @return
	 */
	@RequestMapping(value="/movemember")
	public ReturnObject moveMember(@RequestParam(value="userid",required=true) String userid, 
			@RequestParam(value="from",required=true) String fromGroupid, 
			@RequestParam(value="to",required=true) String toGroupid) {
		ReturnObject obj = new ReturnObject();
		
		try {
			User user = userService.find(userid);
			FriendsGroup fromGroup = groupService.find(fromGroupid);
			FriendsGroup toGroup = groupService.find(toGroupid);
			
			groupService.moveMember(fromGroup, toGroup, user);
			
			obj.setStatus(1);
			obj.setMessage("移动成功");
		} catch (Exception e) {
			logger.error("移动失败", e);
			obj.setStatus(0);
			obj.setMessage("移动失败");
		}
		
		return obj;
	}
	
	/**
	 * 删除好友
	 * @param userid
	 * @param fromGroupid
	 * @return
	 */
	@RequestMapping(value="/delmember")
	public ReturnObject deleteMember(@RequestParam(value="userid",required=true) String userid, 
			@RequestParam(value="groupid",required=true) String groupid) {
		ReturnObject obj = new ReturnObject();
		
		try {
			User user = userService.find(userid);
			FriendsGroup group = groupService.find(groupid);
			
			groupService.deleteMember(group, user);
			
			obj.setStatus(1);
			obj.setMessage("删除成功");
		} catch (Exception e) {
			logger.error("删除失败", e);
			obj.setStatus(0);
			obj.setMessage("删除失败");
		}
		
		return obj;
	}
}
