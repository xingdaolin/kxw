package kxw.com.enity;
/**
 * @ClassName: Address 
* @Description: 地址实体类  
* @author xingdl@yuchengtech.com 
* @date 2014年12月12日 上午10:18:46 
* @version V1.0
 */
public class UserAddress {
	private String id;
	//省
	private int province;
	//市
	private int city;
	//区
	private int local;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getLocal() {
		return local;
	}
	public void setLocal(int local) {
		this.local = local;
	}
	
}
