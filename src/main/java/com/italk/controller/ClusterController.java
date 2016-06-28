package com.italk.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.Cluster;
import com.italk.bean.User;
import com.italk.service.ClusterService;
import com.italk.service.UserService;
import com.italk.utils.WebUtil;
import com.italk.vo.ReturnObject;

/**
 * 群组控制器
 * @author luoshengsha
 *
 * 2016年6月21日-下午10:12:12
 */
@RestController
@RequestMapping(value="/center/cluster")
public class ClusterController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(ClusterController.class);
	
	@Resource
	private ClusterService clusterService;
	@Resource
	private UserService userService;

	/**
	 * 创建群组
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/create")
	public ReturnObject create(String name,HttpServletRequest request) {
		ReturnObject obj = new ReturnObject();
		
		try {
			Cluster cluster = new Cluster();
			cluster.setUuid(WebUtil.createUuid());
			cluster.setName(name);
			cluster.setOwner(WebUtil.getLoginUser(request));
			cluster.setCreateTime(new Date());
			
			clusterService.save(cluster);
			
			obj.setStatus(1);
			obj.setMessage("创建成功");
		} catch (Exception e) {
			logger.error("创建群组失败", e);
			obj.setStatus(0);
			obj.setMessage("创建失败");
		}
		
		return obj;
	}
	
	/**
	 * 编辑群组
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ReturnObject edit(String id) {
		ReturnObject obj = new ReturnObject();
		
		try {
			Cluster cluster = clusterService.find(id);
			
			obj.setData(cluster);
			obj.setStatus(1);
			obj.setMessage("获取成功");
		} catch (Exception e) {
			logger.error("获取群组失败", e);
			obj.setStatus(0);
			obj.setMessage("获取失败");
		}
		
		return obj;
	}
	
	/**
	 * 更新群组
	 * @param id
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update")
	public ReturnObject update(String id, String name,HttpServletRequest request) {
		ReturnObject obj = new ReturnObject();
		
		try {
			Cluster cluster = clusterService.find(id);
			cluster.setName(name);
			
			clusterService.update(cluster);
			
			obj.setStatus(1);
			obj.setMessage("更新成功");
		} catch (Exception e) {
			logger.error("更新群组失败", e);
			obj.setStatus(0);
			obj.setMessage("更新失败");
		}
		
		return obj;
	}
	
	/**
	 * 删除群组
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete")
	public ReturnObject delete(String id, HttpServletRequest request) {
		ReturnObject obj = new ReturnObject();
		
		try {
			User user = WebUtil.getLoginUser(request);
			
			Cluster cluster = clusterService.find(id);
			
			//只有群主才能解散群组
			if(cluster.getOwner().getId() == user.getId()) {
				clusterService.delete(id);
				
				obj.setStatus(1);
				obj.setMessage("删除成功");
			} else {
				obj.setStatus(0);
				obj.setMessage("您不能解散此群");
			}
		} catch (Exception e) {
			logger.error("删除群组失败", e);
			obj.setStatus(0);
			obj.setMessage("删除失败");
		}
		
		return obj;
	}
	
	/**
	 * 向群组中添加成员
	 * @param userid
	 * @param groupid
	 * @return
	 */
	@RequestMapping(value="/pushmember")
	public ReturnObject pushMember(@RequestParam(value="userid",required=true) String userid, @RequestParam(value="groupid",required=true) String groupid) {
		ReturnObject obj = new ReturnObject();
		
		try {
			User user = userService.find(userid);
			Cluster cluster = clusterService.find(groupid);
			
			clusterService.pushMember(cluster, user);
			
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
	 * 删除组员
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
			Cluster cluster = clusterService.find(groupid);
			
			clusterService.delMember(cluster, user);
			
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
