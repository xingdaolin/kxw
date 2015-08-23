package kxw.com.dao;

import java.util.List;

import kxw.com.enity.Address;

/**
 * @ClassName: AddressDao 
* @Description: 地址操作dao
* @author xingdl@yuchengtech.com 
* @date 2014年12月15日 上午10:21:44 
* @version V1.0
 */
public interface AddressDao {
	/**
	 * @Description: 获取所有的省份 
	 *@return
	 *@throws Exception
	 */
	public List<Address> getProvinces()throws Exception;
	/**
	 * @Description: 根据省份id获取该省的所有城市列表
	 *@param provinceId
	 *@return
	 *@throws Exception
	 */
	public List<Address> getCityByProvicdeId(int provinceId)throws Exception;
}
