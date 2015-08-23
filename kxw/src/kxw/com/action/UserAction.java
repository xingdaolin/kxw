package kxw.com.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.directwebremoting.util.Logger;







import kxw.com.data.DesktopIconsDefinition;
import kxw.com.data.DesktopIconsSetting;
import kxw.com.enity.DesktopIcon;
import kxw.com.enity.Status;
import kxw.com.enity.User;
import kxw.com.enity.UserAddress;
import kxw.com.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * @ClassName: RegisterAction 
* @Description: 注册action 
* @author xingdl@yuchengtech.com 
* @date 2014年12月12日 下午1:09:40 
* @version V1.0
 */
public class UserAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware {
	private String result;
	private User user = new User();
	private UserAddress address = new UserAddress();
	private UserService userService;
	static Logger log = Logger.getLogger(UserAction.class);
	private InputStream response;
	private Map statusResponse;//身份状态，转换成json返回给客户端 
	private HttpServletRequest request;
	private HttpServletResponse servletResponse;
	private Map<String,Object> session;
	public InputStream getResponse() {
		return response;
	}
	
	public Map getStatusResponse() {
		return statusResponse;
	}
	
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	public String getResult() {
		return result;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String regist() throws Exception {
		// TODO Auto-generated method stub
		log.info("用户注册");
		//判断校验码
		String local_validateCode = (String) session.get("validationCode");
		if(!local_validateCode.equalsIgnoreCase(user.getValidateCode())){
			response = new ByteArrayInputStream("{success:'false',msg:'验证码错误'}".getBytes("utf-8"));
			return "result2";
		}
		if(user.getAddress()==null)
			user.setAddress(getAddress());
		try{
			User u = this.userService.getUser(this.user.getEmail());
			//用户存在
			if(u!=null){
				response = new ByteArrayInputStream("{success:'false',msg:'此邮件已注册'}".getBytes("utf-8"));
				return "result2";
			}
			this.userService.addUser(user);
			response = new ByteArrayInputStream("{success:'true',msg:'注册成功，是否返回登录页面?'}".getBytes("utf-8"));
			result = "用户注册成功";
			//注册默认图标
			this.userService.addDefaultDesktopIconSettings(user);
		}catch(Exception ex){
			log.error("用户注册错误："+ex.getMessage());
			ex.printStackTrace();
			response = new ByteArrayInputStream("{success:'false',msg:'注册失败，系统错误'}".getBytes("utf-8"));
			return "result2";
		}finally{
			if(response!=null)
				response.close();
		}
		return "result2";
	}
	public String getList(){
		try {
			List<Status> list = this.userService.getStatus();
			List listMap = new ArrayList();
			if(list!=null){
				statusResponse = new HashMap();
				for(Status st:list){
					Map map = new HashMap();
					map.put("id", st.getId());
					map.put("name",st.getName());
					listMap.add(map);
				}
			}
		this.statusResponse.put("result", listMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("错误信息:"+e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//注销的方法
	public String loginOut(){
		HttpSession session = request.getSession();
		if(session!=null){
			session.removeAttribute("email");
			session.removeAttribute("user");
			log.info("注销用户");
		}
		return null;
	}
	public String login(){
		/**
		 * 如果用户是保持一段时间内登录，在直接返回主页面
		 */
		if(session.get("email")!=null||session.get("user")!=null){
			DesktopIcon icons = this.userService.getDesktopIcon((String)session.get("email"));
			String javaScript = DesktopIconsDefinition.getShowDesktopIconsJavaScriptFunction(icons, true);
			request.setAttribute("javascript", javaScript);
			return "main";
		}
		try {
			if(this.user==null){
				response = new ByteArrayInputStream("{success:'false',msg:'请填写完整的登录信息'}".getBytes("utf-8"));
				return ERROR;
			}
				
			//判断校验码
			String local_validateCode = (String) session.get("validationCode");
			if(!local_validateCode.equalsIgnoreCase(user.getValidateCode())){
				response = new ByteArrayInputStream("{success:'false',msg:'验证码错误'}".getBytes("utf-8"));
				return ERROR;
			}
			//验证用户密码
			if(!this.userService.verifyUser(this.user)){//验证没有通过
				response = new ByteArrayInputStream("{success:'false',msg:'用户不存在或登录密码错误'}".getBytes("utf-8"));
				return ERROR;
			}
			response = new ByteArrayInputStream("{success:'true',msg:'登录成功'}".getBytes("utf-8"));
			session.put("user", user);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("@UserAction.login(),错误信息是："+e.getMessage());
			e.printStackTrace();
			try {
				response = new ByteArrayInputStream("{success:'false',msg:'登录失败，系统错误'}".getBytes("utf-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return ERROR;
		}finally{
			if(response!=null)
				//释放资源
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
				
					e.printStackTrace();
				}
		}
		//添加cookie 
		addCookie();
		return SUCCESS;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.servletResponse = response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public User getUser() {
		if(user.getAddress()==null)
			user.setAddress(this.getAddress());
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.user.setAddress(this.getAddress());
	}
	public void addCookie(){
		int time = 0;
		switch(user.getLoginStatus()){
		case 1://保持一天内登录
			time = 24*3600;
			break;
		case 2://保持一周内登录
			time = 24*3600*7;
			break;
		case 3://保持一个月内登录
			time = 24*3600*30;
			break;
		case 4://保持一年内登录
			time = 24*3600*365;
		break;
		}
		if(time>0){
			HttpSession session = request.getSession();
			session.setAttribute("email", user.getEmail());
			//设置session有效时间 
			session.setMaxInactiveInterval(time);
			//要想session在新的窗口中有效，要把session的id放到cookie中
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			//设置cookie的有效时间
			cookie.setMaxAge(time);
			//把cookie推送到客户端
			servletResponse.addCookie(cookie);
		}
	}
}
