package com.italk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.Cluster;
import com.italk.bean.FriendsGroup;
import com.italk.bean.User;
import com.italk.service.ClusterService;
import com.italk.service.FriendsGroupService;
import com.italk.service.UserService;
import com.italk.utils.WebUtil;
import com.italk.vo.ClusterVo;
import com.italk.vo.FriendsGroupVo;
import com.italk.vo.ReturnObject;
import com.italk.vo.UserVo;

/**
 * 主面板控制器
 * @author luoshengsha
 *
 * 2016年6月14日-下午6:57:38
 */
@RestController
@RequestMapping(value="/center")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Resource
	private ClusterService clusterService;
	@Resource
	private UserService userService;
	@Resource
	private FriendsGroupService groupService;
	
	/**
	 * 获取群组列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cluster/list")
	public ReturnObject getCluster(HttpServletRequest request) {
		ReturnObject obj = new ReturnObject();
		
		try {
			User user = WebUtil.getLoginUser(request);
			List<ClusterVo> clusters = new ArrayList<ClusterVo>();
			for(Cluster c : clusterService.getByUser(user)) {
				ClusterVo cv = new ClusterVo();
				cv.setId(c.getUuid());
				cv.setName(c.getName());
				
				for(User u : c.getMembers()) {
					UserVo uv = new UserVo();
					uv.setId(u.getId());
					uv.setName(u.getName());
					uv.setNick(u.getNickname());
					uv.setAvatar(u.getAvatar()==null? "" : u.getAvatar().getImgpath());
					uv.setStatus(u.getStatus());
					cv.getMembers().add(uv);
					
					if(cv.getAvatars().size() < 4) {
						if(u.getAvatar() != null) {
							cv.getAvatars().add(uv.getAvatar());
						}
					}
				}
				clusters.add(cv);
			}
			
			obj.setStatus(1);
			obj.setData(clusters);
			obj.setMessage("获取群组列表成功");
		} catch (Exception e) {
			obj.setStatus(0);
			obj.setMessage("获取群组列表失败");
			
			logger.error("获取群组列表失败", e);
		}
		return obj;
	}
	
	/**
	 * 获取好友列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/friends/list")
	public ReturnObject getFriends(HttpServletRequest request) {
		
		ReturnObject obj = new ReturnObject();
		
		try {
			User user = WebUtil.getLoginUser(request);
			
			List<FriendsGroupVo> friendsGroup = new ArrayList<FriendsGroupVo>();
			
			for(FriendsGroup group : groupService.getByCreater(user)) {
				FriendsGroupVo fgv = new FriendsGroupVo();
				fgv.setId(group.getUuid());
				fgv.setName(group.getName());
				
				for(User u : group.getMembers()) {
					UserVo uv = new UserVo();
					uv.setId(u.getId());
					uv.setName(u.getName());
					uv.setNick(u.getNickname());
					uv.setAvatar(u.getAvatar()==null? "" : u.getAvatar().getImgpath());
					uv.setStatus(u.getStatus());
					fgv.getMembers().add(uv);
				}
				
				friendsGroup.add(fgv);
			}
			
			obj.setStatus(1);
			obj.setData(friendsGroup);
			obj.setMessage("获取好友列表成功");
		} catch (Exception e) {
			obj.setStatus(0);
			obj.setMessage("获取好友列表失败");
			
			logger.error("获取好友列表失败", e);
		}
		return obj;
	}
}
