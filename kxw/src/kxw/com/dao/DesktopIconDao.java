package kxw.com.dao;

import kxw.com.enity.DesktopIcon;
import kxw.com.enity.User;

public interface DesktopIconDao {
	//根据email获取用户的桌面图标信息 
	public DesktopIcon getDesktopIcon(String email);
	//添加图标
	public void addDesktopIconSettings(User user);
	//指定用户桌面图标的信息
	public void updateSettings(String email,String settings);
}
