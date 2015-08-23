package kxw.com.service.impl;

import java.util.List;

import kxw.com.dao.AddressDao;
import kxw.com.enity.Address;
import kxw.com.service.AddressService;

public class AddressServiceImpl implements AddressService {
	private AddressDao addressDao;
	@Override
	public List<Address> getProvinces() throws Exception {
		// TODO Auto-generated method stub
		List<Address> list = this.addressDao.getProvinces();
		return list;
	}

	@Override
	public List<Address> getCityByProvinceId(int provinceId) throws Exception {
		// TODO Auto-generated method stub
		//省份id都是3位的
		if(provinceId<100)
			throw new Exception("@AddressServiceImpl.getCityByProvinceId(int provinceId)参数错误，请检查参数");
		List<Address> list = this.addressDao.getCityByProvicdeId(provinceId);
		return list;
	}

	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}
	
}
