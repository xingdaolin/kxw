package kxw.com.service.impl;

import java.util.List;

import kxw.com.dao.UserDao;
import kxw.com.dao.impl.DesktopIconDaoImpl;
import kxw.com.enity.DesktopIcon;
import kxw.com.enity.Status;
import kxw.com.enity.User;
import kxw.com.service.UserService;
import kxw.com.util.Utils;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private DesktopIconDaoImpl desktopDao;
	@Override
	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		if(user==null)
			throw new Exception("@UserServiceImpl.addUser(User user) 参数user是null值");
		//加密密码 
		user.setPassword(Utils.md5(user.getPassword()));
		user.setId(Utils.getUUID());
		if(user.getAddress()!=null){
			user.getAddress().setId(Utils.getUUID());
			//关联用户 
			user.getAddress().setUser(user);
		}
		this.userDao.addUser(user);
	}

	@Override
	public void modifyUser(User user) throws Exception{
		// TODO Auto-generated method stub
		this.addUser(user);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public DesktopIconDaoImpl getDesktopDao() {
		return desktopDao;
	}

	public void setDesktopDao(DesktopIconDaoImpl desktopDao) {
		this.desktopDao = desktopDao;
	}

	@Override
	public List<Status> getStatus() throws Exception {
		// TODO Auto-generated method stub
		return this.userDao.getStatus();
	}

	@Override
	public boolean verifyUser(User user) throws Exception {
		// TODO Auto-generated method stub
		if(user==null)
			return false;
		if(user.getEmail()==null)
			return false;
		if(user.getPassword()==null)
			return false;
		String localPassword = this.getUserDao().getPassword(user);
		String remotePassword = Utils.md5(user.getPassword());
		//用户不存在
		if(localPassword==null)
			return false;
		if(localPassword.equals(remotePassword))
			return true;
		return false;
	}

	@Override
	public User getUser(String email) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		if(email==null)
			return user;
		user = this.userDao.getUser(email);
		return user;
	}

	@Override
	public void addDefaultDesktopIconSettings(User user) {
		// TODO Auto-generated method stub
		if(user!=null){
			this.desktopDao.addDesktopIconSettings(user);
		}
	}

	@Override
	public DesktopIcon getDesktopIcon(String email) {
		// TODO Auto-generated method stub
		DesktopIcon icon = null;
		if(email!=null)
			icon = this.desktopDao.getDesktopIcon(email);
		return icon;
	}
	
}
