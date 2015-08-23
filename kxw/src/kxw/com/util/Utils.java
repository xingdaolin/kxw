package kxw.com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class Utils {
	
	/** 把输入的字符串转成ascii码，除100求余
	   *  @param input 任意字符串
	   *  @param n 数字上限
	   *  @return int 0～n之间的数字
	   */
	  public static final int getSeqByAscii(String input,int n){
		  if(input==null)
			  return 0;
		  	int k=0;
		  	char c;
			for(int i=0;i<input.length();i++){
				c=input.charAt(i);
				k+=c;
			}
			k=k%n;
			return k;
	  }
	  
	  public static void main(String[] args) {
		  System.out.println(Utils.getSeqByAscii("4545568745454654654654", 40));
	  }
	  /**
	   * @Description: 密码加密算法
	   *@param password
	   *@return
	   */
	  public static String md5(String password){
		  if(password==null||"".equals(password))
			  return password;
		  try {
			  //md5算法
			MessageDigest message = MessageDigest.getInstance("MD5");
			password = new String(Base64.getEncoder().encode(message.digest(password.getBytes("utf-8"))),"utf-8");
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return password;
	  }
	  public static String getUUID(){
		  return UUID.randomUUID().toString();
	  }
}
