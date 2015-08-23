package kxw.com.dao;

import java.util.List;

import kxw.com.enity.Status;
import kxw.com.enity.User;

/**
 * @ClassName: UserDao 
* @Description: 用户dao层接口 
* @author xingdl@yuchengtech.com 
* @date 2014年12月12日 下午12:34:19 
* @version V1.0
 */
public interface UserDao {
	/**
	 * @Description: 保存用户方法 
	 *@param user
	 */
	public void addUser(User user) throws Exception;
	/**
	 * @Description: 修改用户方法
	 *@param user
	 *@throws Exception
	 */
	public void modifyUser(User user) throws Exception;
	/**
	 * @Description: 获取用户密码 
	 *@param user
	 *@return
	 */
	public String getPassword(User user);
	/**
	 * @Description: 获取用户身份状态
	 *@return
	 *@throws Exception
	 */
	public List<Status> getStatus() throws Exception;
	/**
	 * @Description: 根据用户的登录email获取用户 
	 *@param email
	 *@return
	 *@throws Exception
	 */
	public User getUser(String email) throws Exception;
}
