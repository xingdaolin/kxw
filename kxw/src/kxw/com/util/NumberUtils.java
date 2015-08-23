package kxw.com.util;



/**
 * <p>Title: 基础类</p>
 * <p>Description: 数字转换</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company:  </p>
 * @author wangwg
 * @version 1.0
 */
public class NumberUtils {

	public static Double getDoubleFromString(String value){
		Double systemKey = null;
		try {
			systemKey = new Double(value);
		} catch (Exception e) {
			systemKey = new Double(-1);
		}
		return systemKey;
	}
	
	public static String format(float number, int bitCount){
		String result = "";
		String strbit = "";
		for(int i=0; i<bitCount; i++){
			strbit+="0";
		}
		java.text.DecimalFormat df =new   java.text.DecimalFormat("#."+strbit); 
		result = df.format(number);
		
		return result;
	}
}
