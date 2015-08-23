package kxw.com.service;

import java.util.List;

import kxw.com.enity.DesktopIcon;
import kxw.com.enity.Status;
import kxw.com.enity.User;

/**
 * @ClassName: UserService 
* @Description: user业务逻辑组件 
* @author xingdl@yuchengtech.com 
* @date 2014年12月12日 下午12:39:43 
* @version V1.0
 */
public interface UserService {
	/**
	 * @Description: 保存用户 
	 *@param user
	 */
	public void addUser(User user) throws Exception;
	/**
	 * @Description: 修改用户
	 *@param user
	 */
	public void modifyUser(User user) throws Exception;
	/**
	 * @Description: 获取用户身份状态
	 *@return
	 *@throws Exception
	 */
	public List<Status> getStatus() throws Exception;
	/**
	 * @Description: 验证用户密码是否正确
	 *@param user
	 *@return
	 *@throws Exception
	 */
	public boolean verifyUser(User user) throws Exception;
	/**
	 * @Description: 根据Email获取用户
	 *@param email
	 *@return
	 *@throws Exception
	 */
	public User getUser(String email) throws Exception;
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 *@param user
	 */
	public void addDefaultDesktopIconSettings(User user);
	/**
	 * @Description: 根据用户email获取图标信息
	 *@param email
	 *@return
	 */
	public DesktopIcon getDesktopIcon(String email);
}
