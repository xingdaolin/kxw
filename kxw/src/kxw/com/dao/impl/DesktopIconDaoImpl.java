package kxw.com.dao.impl;

import java.util.List;

import kxw.com.dao.DesktopIconDao;
import kxw.com.data.DesktopIconsDefinition;
import kxw.com.enity.DesktopIcon;
import kxw.com.enity.User;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DesktopIconDaoImpl extends HibernateDaoSupport implements
		DesktopIconDao {

	@Override
	public DesktopIcon getDesktopIcon(String email) {
		// TODO Auto-generated method stub
		//设置缓存
		this.getHibernateTemplate().setCacheQueries(true);
		String hql = "from DesktopIcon where email=?";
		List<DesktopIcon> list = this.getHibernateTemplate().find(hql, email);
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public void addDesktopIconSettings(User user) {
		// TODO Auto-generated method stub
		DesktopIcon desk = new DesktopIcon();
		desk.setEmail(user.getEmail());
		desk.setSettings(DesktopIconsDefinition.defaultDesktopIconSettings);
		this.getHibernateTemplate().save(desk);
	}

	@Override
	public void updateSettings(String email, String settings) {
		// TODO Auto-generated method stub
		String hql = "update DesktopIcon set settings=? where email=?";
		this.getHibernateTemplate().bulkUpdate(hql, settings,email);
	}

}
