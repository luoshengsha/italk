package com.italk.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:source.properties")
@ConfigurationProperties(prefix="system"/*,locations = "classpath:source.properties"*/)
public class PropertiesUtil {
	/** 上传文件路径 **/
	public static String file_path;
	/** 上传头像的路径 **/
	public static String avatar_path;
	/** 图片服务器的url **/
	public static String pic_url;
	/** 文件服务器地址 **/
	public static String file_url;

	public static String getFile_path() {
		return file_path;
	}

	public static void setFile_path(String file_path) {
		PropertiesUtil.file_path = file_path;
	}

	public static String getAvatar_path() {
		return avatar_path;
	}

	public static void setAvatar_path(String avatar_path) {
		PropertiesUtil.avatar_path = avatar_path;
	}

	public static String getPic_url() {
		return pic_url;
	}

	public static void setPic_url(String pic_url) {
		PropertiesUtil.pic_url = pic_url;
	}

	public static String getFile_url() {
		return file_url;
	}

	public static void setFile_url(String file_url) {
		PropertiesUtil.file_url = file_url;
	}
}
