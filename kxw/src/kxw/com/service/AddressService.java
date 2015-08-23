package kxw.com.service;

import java.util.List;

import kxw.com.enity.Address;

/**
 * @ClassName: AddressService 
* @Description: 获取地址的业务组件
* @author xingdl@yuchengtech.com 
* @date 2014年12月15日 上午10:30:31 
* @version V1.0
 */
public interface AddressService {
	/**
	 * 	@Description: 获取所有的省份
	 *@return
	 */
	public List<Address> getProvinces()throws Exception;
	/**
	 * @Description: 根据省份id获取该省的所有城市 
	 *@param provinceId
	 *@return
	 *@throws Exception
	 */
	public List<Address> getCityByProvinceId(int provinceId)throws Exception;
}
