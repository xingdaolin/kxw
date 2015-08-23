package kxw.com.action.dwrAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kxw.com.enity.Address;
import kxw.com.service.AddressService;
import kxw.com.util.SpringContextUtil;

import org.apache.struts2.json.JSONReader;
import org.apache.struts2.json.JSONWriter;
import org.directwebremoting.util.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;

public class AddressDwrAction {
	static Logger log = Logger.getLogger(AddressDwrAction.class);
	//返回json格式
//	public String getProvinces(HttpServletRequest request){
//		JSONWriter json = new JSONWriter();
//		List<Address> list;
//		try {
//			list = this.getService().getProvinces();
//			if(list!=null){
//				String str =json.write(list);//转换成json
//				return str;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error("错误信息:"+e.getMessage());
//			e.printStackTrace();
//		}
//		return null;
//	}
	public List<Address> getProvinces(HttpServletRequest request){
		List<Address> list;
		try {
			list = this.getService().getProvinces();
			if(list!=null){
				return list;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("错误信息:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	public List<Address> getCityByProvinceId(HttpServletRequest request,int id){
		try{
			List<Address> list = this.getService().getCityByProvinceId(id);
			if(list!=null){
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			log.error("错误信息："+ex.getMessage());
		}
		return null;
	}
	private AddressService getService(){
		return (AddressService) SpringContextUtil.getBean("addressService");
	}
}
