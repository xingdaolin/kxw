package kxw.com.dao.impl;

import java.util.List;

import kxw.com.dao.AddressDao;
import kxw.com.enity.Address;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AddressDaoImpl extends HibernateDaoSupport implements AddressDao {

	@Override
	public List<Address> getProvinces() throws Exception {
		// TODO Auto-generated method stub
		//配置缓存
		this.getHibernateTemplate().setCacheQueries(true);
		String hql = "from Address a where length(a.id)=3";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Address> getCityByProvicdeId(int provinceId) throws Exception {
		// TODO Auto-generated method stub
		
		String hql = "from Address a where substr(a.id,1,3)=? and length(a.id)>3";
		return this.getHibernateTemplate().find(hql, provinceId);
	}

}
