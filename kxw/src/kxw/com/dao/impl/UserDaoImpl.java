package kxw.com.dao.impl;

import java.util.List;

import kxw.com.dao.UserDao;
import kxw.com.enity.Status;
import kxw.com.enity.User;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}

	@Override
	public void modifyUser(User user) throws Exception {
		// TODO Auto-generated method stub
		this.addUser(user);
	}

	@Override
	public List<Status> getStatus() throws Exception {
		// TODO Auto-generated method stub
		//设置缓存
		this.getHibernateTemplate().setCacheQueries(true);
		String hql = "from Status";
		return 	this.getHibernateTemplate().find(hql);
	}

	@Override
	public String getPassword(User user) {
		// TODO Auto-generated method stub
		String hql = "select password from User u where u.email=?";
		List<String> list = this.getHibernateTemplate().find(hql, user.getEmail());
		return list!=null&&list.size()>0?list.get(0):null;
	}

	@Override
	public User getUser(String email) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from User u where u.email=?";
		List<User> list = this.getHibernateTemplate().find(hql, email);
		return list!=null&&list.size()>0?list.get(0):null;
	}

}
