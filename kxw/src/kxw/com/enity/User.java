package kxw.com.enity;

import java.sql.Date;

/**
 * @ClassName: User 
* @Description: 用户
* @author xingdl@yuchengtech.com 
* @date 2014年12月12日 上午9:56:37 
* @version V1.0
 */
public class User {
	private String id;
	private String name;
	private String password;
	private String email;
	private Date birthday;
	private int sex;
	/*性别中文名称*/
	private String sex_cn;
	private UserAddress address;
	private Status status;
	private int statusId;
	private Date registerDate;
	private int age;
	//验证码
	private String validateCode;
	//昵称
	private String nick;
	/**
	 * 登录状态:
	 *      ['0', '不自动登录'],
                ['1', '在一天内自动登录'],
                ['2','在一周内自动登录'],
                ['3', '在一个月内自动登录'],
                ['4', '在一年内自动登录']
	 */
	private int loginStatus;
	
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getSex_cn() {
		return sex_cn;
	}
	public void setSex_cn(String sex_cn) {
		this.sex_cn = sex_cn;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	
}
