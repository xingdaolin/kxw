package kxw.com.util;

//import java.awt.geom.Arc2D.Double;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StringUtils {

    // Constants used by escapeHTMLTags
    private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
    private static final char[] AMP_ENCODE = "&amp;".toCharArray();
    private static final char[] LT_ENCODE = "&lt;".toCharArray();
    private static final char[] GT_ENCODE = "&gt;".toCharArray();

    //取得随机数用
    private static Random randGen = new Random();
    private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    /** 说明: 将字符串变量由null转换为""串
     *  @param str 需要处理的字符串
     *  @return 处理后的字符串
     */
    public static String null2String(String str) {
        if (str == null) str = "";
        return str.trim();
    }
    /**
     * html字符串换行
     * @param word
     * @return
     */
    public static String breakWord(String word){
		String result = word;
		if (word != null && word.length()>15){
			result = word.substring(0, 15) + "<br>";
			String cutedWord = word.substring(15, word.length());
			result = result + breakWord(cutedWord);
		}
		return result;
	}
    
    public static String empty2null(String str) {
        if (str != null) {
            if (str.trim().equals("")) {
                str = null;
            }
        }
        return str;
    }

    public static double null2double(String s) {
        double d = 0.0;
        try {
            d = Double.parseDouble(s);
        }
        catch (Exception e) {
            d = 0.0;
        }
        return d;

    }

    public static int null2int(String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        }
        catch (Exception e) {
            i = 0;
        }
        return i;
    }
    
    public static int null3int(String s) {
    	if(s==null || s.trim().equals(""))
    		return 0;
    	char c;
        boolean flag=true;
        for(int i=0; i<s.length(); i++){
            c=s.charAt(i);
            if(c!='0' && c!='1' && c!='2' && c!='3' && c!='4' && c!='5' && c!='6' && c!='7' && c!='8' && c!='9'){
                flag=false;
                break;
            }
        }
        return flag ? Integer.parseInt(s) : -1;
    }

    public static long null2Long(String s) {
        long i = 0;
        try {
            i = Long.parseLong(s);
        }
        catch (Exception e) {
            i = 0;
        }
        return i;
    }

    //转换成整型
    public static String changeIntoInt(String s){
    	if(s==null||s.length()==0)
    		return "0";
    	else{
    		String i = getFormatStr(s,0);
        //System.out.println("i="+i.substring(0,i.length()-2));
			// return i.substring(0,i.length()-2);
    		/**modify for bigdecimal*/
    		return i;
    	}
    }

    //转换成字符型
    public static String changeIntoZifu(String s){
    	if(s == null || s.equals("") || s.equals("null"))
    		s = "";
    	return s;
    }

    //转换成浮点型
    public static String changeIntoFudian(String str,String s){
    	if(str==null||str.length()==0)
    		str = "0";

    	int num = Integer.parseInt(s);
    	return getFormatStr(str,num);

    }

    //字符转换函数
    //input: 对象
    //output:如果字符串为null,返回为空,否则返回该字符串
    public static String nullObjectString(Object s) {
        String str = "";
        try {
            str = s==null ? str : s.toString();
        }
        catch (Exception e) {
            str = "";
        }
        return str;
    }
    
    //字符转换函数
    //input: 对象
    //output:如果字符串为null,返回为空,否则返回该字符串
    public static String nullObject2String(Object s) {
        String str = "";
        try {
            str = s.toString();
        }
        catch (Exception e) {
            str = "";
        }
        return str;
    }

    public static String nullObject2String(Object s, String chr) {
        String str = chr;
        try {
            str = s.toString();
        }
        catch (Exception e) {
            str = chr;
        }
        return str;
    }

    /**
     * 如果传入对象为空或无法转为数字就返回0
     * @param s
     * @return
     */
    public static int nullObject2int(Object s) {
        String str = "";
        int i = 0;
        try {
            str = s.toString();
            i = Integer.parseInt(str);
        }
        catch (Exception e) {
            i = 0;
        }
        return i;
    }

    /**
     * 如果传入对象为空或无法转为数字就返回默认值
     * @param s
     * @return
     */
    public static int nullObject2int(Object s, int in) {
        String str = "";
        int i = in;
        try {
            str = s.toString();
            i = Integer.parseInt(str);
        }
        catch (Exception e) {
            i = in;
        }
        return i;
    }

    /**
     * 字符串的首字母大写
     * @param string
     * @return
     */
    public static String firstToUpperCase(String string) {
        String post = string.substring(1, string.length());
        String first = ("" + string.charAt(0)).toUpperCase();
        return first + post;
    }

    public static ArrayList TokenizerString(String str, String dim) {
        return TokenizerString(str, dim, false);
    }

    /******将输入的字符串str按照分割符dim分割成字符串数组并返回ArrayList字符串数组********
     If the returndim flag is true, then the dim characters are also returned as tokens.
         Each delimiter is returned as a string of length one. If the flag is false,
     the delimiter characters are skipped and only serve as separators between tokens.
     **************************************************************************/
    public static ArrayList TokenizerString(String str, String dim,
        boolean returndim) {
        str = null2String(str);
        dim = null2String(dim);
        ArrayList strlist = new ArrayList();
        StringTokenizer strtoken = new StringTokenizer(str, dim, returndim);
        while (strtoken.hasMoreTokens()) {
            strlist.add(strtoken.nextToken());
        }
        return strlist;
    }

    /******类似上面的方法,将输入的字符串str按照分割符dim分割成字符串数组,******************
     * 并返回定长字符串数组**************************************************************/

    public static String[] TokenizerString2(String str, String dim) {
        return TokenizerString2(str, dim, false);
    }

    public static String[] TokenizerString2(String str, String dim,
        boolean returndim) {
        ArrayList strlist = TokenizerString(str, dim, returndim);
        int strcount = strlist.size();
        String[] strarray = new String[strcount];
        for (int i = 0; i < strcount; i++) {
            strarray[i] = (String) strlist.get(i);
        }
        return strarray;
    }

    /**
     * 取得一个随机字符串,包含数字和字符
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static final String getRandomString(int length) {
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    /** 说明: ISO_8559_1字符转换为GBK字符
     *  @param strIn 需要转换的字符
     *  @return 转换后的GBK字符
     */
    public static String IsoToGBK(String strIn) {
        String strOut = "";
        if (strIn == null)return strOut;
        try {
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "GBK");
        }
        catch (UnsupportedEncodingException e) {
        }
        return strOut;
    }

    /** 说明: ISO_8559_1字符转换为GBK字符
     *  @param strIn 需要转换的字符
     *  @return 转换后的GBK字符
     */
    public static String IsoToGBKs(String strIn) {
        String strOut = "";
        if (strIn == null) return strOut;
        try {
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "GBK");
        }
        catch (UnsupportedEncodingException e) {
        }
        return strOut;
    }
    /** 说明: GBK字符转换为ISO_8559_1字符
     *  @param strIn 需要转换的GBK字符
     *  @return 转换后的ISO_8559_1字符
     */

    public static String GBKToIso(String strIn) {
        byte[] b;
        String strOut = "";
        if (strIn == null)return strOut;
        try {
            b = strIn.getBytes("GBK");
            strOut = new String(b, "ISO8859_1");
        }
        catch (UnsupportedEncodingException e) {
        }
        return strOut;
    }
    /** 说明: GBK字符转换为UTF-8字符
     *  @param strIn 需要转换的GBK字符
     *  @return 转换后的字符
     */
    public static String strToUTF(String str){
        if(str==null || "".equals(str)){
            return str;
        }
        String retVal = str;
        try{
            //retVal = new String(str.getBytes(""), "UTF-8");
        } catch(Exception e){
            e.printStackTrace();
        }
        return retVal;
    }


    /**
     * 替换字符串中全部的特殊子串
     * @param mainString 被字符串
     * @param oldString 原有子串
     * @param newString 新的子串
     * @return 替换后字符串
     */
    public static String replaceString(String mainString, String oldString,
        String newString) {
        if (mainString == null) {
            return null;
        }
        if (oldString == null || oldString.length() == 0) {
            return mainString;
        }
        if (newString == null) {
            newString = "";
        }
        int i = mainString.lastIndexOf(oldString);
        if (i < 0)return mainString;
        StringBuffer mainSb = new StringBuffer(mainString);
        while (i >= 0) {
            mainSb.replace(i, i + oldString.length(), newString);
            i = mainString.lastIndexOf(oldString, i - 1);
        }
        return mainSb.toString();
    }

    /**
     * 替换字符串中全部的特殊子串（忽略大小写）
     * @param line 被字符串
     * @param oldString 原有子串
     * @param newString 新的子串
     * @return 替换后字符串
     */
    public static final String replaceIgnoreCase(String line, String oldString,
        String newString) {
        if (line == null) {
            return null;
        }
        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ( (i = lcLine.indexOf(lcOldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ( (i = lcLine.indexOf(lcOldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * 替换字符串中全部的特殊子串，同时，记录替换得个数
     * @param line 被字符串
     * @param oldString 原有子串
     * @param newString 新的子串
     * @param count 替换个数
     * @return line 替换后字符串
     */
    public static final String replace(String line, String oldString,
        String newString, int[] count) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ( (i = line.indexOf(oldString, i)) >= 0) {
            int counter = 0;
            counter++;
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ( (i = line.indexOf(oldString, i)) > 0) {
                counter++;
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            count[0] = counter;
            return buf.toString();
        }
        return line;
    }

    /**
     * 替换字符串中全部的特殊子串（忽略大小写），同时，记录替换得个数
     * @param line 被字符串
     * @param oldString 原有子串
     * @param newString 新的子串
     * @param count 替换个数
     * @return 替换后字符串
     */
    public static final String replaceIgnoreCase(String line, String oldString,
        String newString, int[] count) {
        if (line == null) {
            return null;
        }
        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ( (i = lcLine.indexOf(lcOldString, i)) >= 0) {
            int counter = 0;
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ( (i = lcLine.indexOf(lcOldString, i)) > 0) {
                counter++;
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            count[0] = counter;
            return buf.toString();
        }
        return line;
    }

    /**
     * 对字符串进行分割
     * @param line 源字符串
     * @param newString  分割字符串
     * @return 字符串数组
     */
    public static String[] split(String line, String newString) {
        int begin = 0;
        int end = 0;
        ArrayList strList = new ArrayList();
        if (line == null) {
            return null;
        }
        if (newString == "") {
            int i;
            for (i = 0; i < line.length(); i++) {
                strList.add(line.substring(i, i + 1));
            }
            return (String[]) strList.toArray(new String[strList.size()]);
        }

        end = line.indexOf(newString);
        if (end == -1) {
            strList.add(line);
            return (String[]) strList.toArray(new String[strList.size()]);
        }
        else {
            while (end >= 0) {
                strList.add(line.substring(begin, end));
                begin = end + newString.length();
                end = line.indexOf(newString, begin);
            }
            strList.add(line.substring(begin, line.length()));
            return (String[]) strList.toArray(new String[strList.size()]);
        }
    }

    /**
     * 合并被切分的字符串
     * @param list 子串集合
     * @param delim 合并分割符
     * @return 合并字符串
     */
    public static String joinStr(List list, String delim) {
        if (list == null || list.size() < 1)
            return null;
        StringBuffer buf = new StringBuffer();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            buf.append( (String) i.next());
            if (i.hasNext())
                buf.append(delim);
        }
        return buf.toString();
    }

    /**
     * 切分字符串
     * @param str 源字符串
     * @param delim 切分标志
     * @return 子串集合
     */
    public static List splitStr(String str, String delim) {
        List splitList = null;
        StringTokenizer st = null;

        if (str == null)
            return splitList;

        if (delim != null)
            st = new StringTokenizer(str, delim);
        else
            st = new StringTokenizer(str);

        if (st != null && st.hasMoreTokens()) {
            splitList = new ArrayList();

            while (st.hasMoreTokens())
                splitList.add(st.nextToken());
        }
        return splitList;
    }

    /**
     * 二进制数转化成十进制数,目前只支持正数
     * @param str 一个由0，1组成的字符串
     * @return 转化后的十进制数值,若返回-1则表明转化失败，有可能是输入字符串
     * 不合法
     */
    public static int binsToDecs(String str) {
        int ret = 0;
        int v = 1;
        for (int i = 0; i < str.length(); i++) {
            if (i != 0)
                v = v * 2;
            if (str.charAt(i) == '0')
                continue;
            else if (str.charAt(i) == '1')
                ret = ret + v;
            else
                return -1;
        }
        return ret;
    }

    /**
     * 过滤HTML标签
     * @param in 源字符串
     * @return 替换后字符串
     */
    public static final String escapeHTMLTags(String in) {
        if (in == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = in.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer( (int) (len * 1.3));
        for (; i < len; i++) {
            ch = input[i];
            if (ch > '>') {
                continue;
            }
            else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT_ENCODE);
            }
            else if (ch == '>') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(GT_ENCODE);
            }
        }
        if (last == 0) {
            return in;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    /**
     * Escapes all necessary characters in the String so that it can be used
     * in an XML doc.
     *
     * @param string the string to escape.
     * @return the string with appropriate characters escaped.
     */
    public static final String escapeForXML(String string) {
        if (string == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = string.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer( (int) (len * 1.3));
        for (; i < len; i++) {
            ch = input[i];
            if (ch > '>') {
                continue;
            }
            else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT_ENCODE);
            }
            else if (ch == '&') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(AMP_ENCODE);
            }
            else if (ch == '"') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(QUOTE_ENCODE);
            }
        }
        if (last == 0) {
            return string;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    /**
     * Unescapes the String by converting XML escape sequences back into normal
     * characters.
     *
     * @param string the string to unescape.
     * @return the string with appropriate characters unescaped.
     */
    public static final String unescapeFromXML(String string) {
        string = replaceString(string, "&lt;", "<");
        string = replaceString(string, "&gt;", ">");
        string = replaceString(string, "&quot;", "\"");
        return replaceString(string, "&amp;", "&");
    }

    /**
     * 转还文本的回车为HTML回车
     * @param str 源串
     * @return 替换后字符串
     */
    public static String encodeCR(String str) {
        str = replaceString(str, "\r\n", "\n");
        str = replaceString(str, "\n", "<BR />");
        return str;
    }

    public static String encodeCRWithPram(String str) {
        str = replaceString(str, "\r\n", "\n");
        str = replaceString(str, "\n", "<BR/>　　");
        str = "　　" + str;
        return str;
    }

    /**
     * 转化HTML回车文本回车
     * @param str 源串
     * @return 替换后字符串
     */
    public static String decodeCR(String str) {
        str = replaceString(str, "<BR />", "\r\n");
        return str;
    }

    /**
     * 对字符串进行截取
     * @param StrCmd 要截取的字符串
     * @param subnum 要保留的字符数
     * @return 返回值为截取后的字符串
     */
    public static String subStr(String StrCmd, int subnum) {
        String tempSub = "";
        if (subnum <= 0) {
            subnum = 5;
        }
        for (int i = 0; i < StrCmd.length(); i++) {
            String tmpstr = StrCmd.substring(i, i + 1);
            int codenum = tmpstr.hashCode();
            if (codenum >= 128) {
                subnum = subnum - 2;
            }
            else {
                subnum = subnum - 1;
            }
            tempSub += tmpstr;
            if (subnum <= 0) {
                tempSub += "...";
                break;
            }
        }
        return tempSub;
    }

    /**
     * 得到两个数之间的一个随机数
     * @param iLower 最小值
     * @param iUpper 最大值
     * @return 随机数
     */
    public String getRandom(int iLower, int iUpper) {
        int iRandom = 0;
        Random random = new Random();
        float fRandom = random.nextFloat();
        iRandom = iLower + (int) ( (iUpper - iLower) * fRandom);
        String strRandom = String.valueOf(iRandom);
        return strRandom;
    }

    /**
     * 把类似 .4 或 －.44 形式显示的小数字符串改为0.4 或-0.44形式显示的字符串
     * @param strDecimal 原来的小数字符串
     * @return 转换后的字符串
     */


   public static String getCookie(HttpServletRequest req, String key) {
     Cookie[] cookies = req.getCookies();
     for (int i = 0; i < cookies.length; i++) {
       if (cookies[i].getName().equals(key)) {
         return cookies[i].getValue();
       }
     }
     return null;
   }

   public static void setCookie(HttpServletResponse res, String key,
                                String value, int age, String domain) {

     Cookie newCookie = new Cookie(key, value);
     newCookie.setMaxAge(age);
     newCookie.setDomain(domain);
     newCookie.setPath("/");

     res.addCookie(newCookie);

   }

   public static void setCookie(HttpServletResponse res, String key,
                                String value, int age) {

     Cookie newCookie = new Cookie(key, value);
     newCookie.setMaxAge(age);
     newCookie.setPath("/");
     res.addCookie(newCookie);

   }

   //根据参数
   //得到中文的空格数量
   //add by heguoqiang 2006－03－19
   public static String getChineseSpace(int num) {
       StringBuffer bf=new StringBuffer();
       for(int i=0;i<num;i++){
           bf.append("　");
       }
       return bf.toString();
  }
   public static void setCookie(HttpServletResponse res, String key,
                                String value) {
     setCookie(res, key, value, -1);
   }

    public String strDecimalFormat(String strDecimal) {
        String sFormatDecimal = strDecimal;
        if (strDecimal.length() > 0) {
            if (strDecimal.substring(0, 1).equals(".")) {
                sFormatDecimal = "0" + strDecimal;
            }
        }
        if (strDecimal.length() > 1) {
            if (strDecimal.substring(0, 2).equals("-.")) {
                sFormatDecimal = "-0" + strDecimal.substring(1);
            }
        }
        return sFormatDecimal;
    }









    /**
     * 格式化数字（字符串性质），保留小数点后num位。四舍五入
     * @param inStr String    小数（字符串性质）
     * @param num int         小数点后位数
     * @return String
     */
    public static String getFormatStr(String inStr, int num) {

        if(inStr==null || "".equals(inStr))
        	inStr = "0";
        double inVal = Double.parseDouble(inStr);

        return roundString(inVal,num);

        /*
        String retVal = "";
        for (int i = num; i > 0; i--) {
            temp *= 10;
        }
        inVal *= temp;
        long dl = Math.round(inVal);

                retVal = numFormat((double) (dl) / (double)temp);

        if(retVal.indexOf('.')==-1 && num>0)
        	retVal += ".0";
        int tempNum = retVal.substring(retVal.indexOf('.')+1).length();
        for(int i=0;i<num-tempNum;i++)
        	retVal += "0";
        return retVal;

        */

    }

    /**
     * 主函数
     * @param args 测试参数
     */
    public static void main(String[] args) {
        String i = getFormatStr(".623641",0);
        System.out.println("i="+i.substring(0,i.length()-2));

    }

    /**
     * 字符串前导符( Escape: '\' ) 自动添加函数.
     * <p>
     * 对于通过 HTML &lt;TEXTAREA&gt; 输入的数据,
     * 因为其中包含回车、换行等特殊字符,
     * 在将这些变量传给 HTML 中的 javascript 变量时,这些字符串将导致
     * javascript 程序出错, 因此需要对于包含这些特殊字符的字符串进行转换处理,
     * 因为 javascript, 特殊字符转义前导符与 java/C 语言一致,
     * 所以实际是在这些特殊字符中加入前导符 '\' .
     *
     * @param         s 要输出或处理的字符串.
     * @return        自动添加了前导符的字符串.
     */
    public static String escape(String s) {
        try {
            int i = 0;
            char c;
            StringBuffer bf = new StringBuffer("");
            while (i < s.length()) {
                c = s.charAt(i);
                if (c == '\\')
                    bf.append("\\\\");
                else if (c == '\r')
                    bf.append("\\r");
                else if (c == '\n')
                    bf.append("\\n");
                else if (c == '\t')
                    bf.append("\\t");
                else if (c == '"')
                    bf.append("\\\"");
                else
                    bf.append(c);
                i++;
            }
            return (bf.toString());
        }
        catch (Exception e) {
            return (null);
        }
    }



    /**
     * \u00B9\u00A6\u00C4\u00DC:×\u00D6·\u00FB\u00B2\u00B9×\u00E3".00"\u00BA\u00AF\u00CA\u00FD
     * @param s,\u00B4\u00AB\u00C8\u00EB\u00B5\u00C4\u00D0è\u00D2\u00AA\u00B2\u00B9×\u00E3×\u00D6·\u00FB\u00B4\u00AE
     * @param n\u00A3\u00AC\u00D0è\u00D2\u00AA\u00B2\u00B9×\u00E3\u00B5\u00C4\u00CE\u00BB\u00CA\u00FD
     * @return
     */
    public static String numFull(String s2, int n) {
        String s = s2;
        if (s == null) {
            s = "0";
        }
        int index2 = s.indexOf(".");
        String s1 = "";
        if (index2 > 0) {
            s1 = s.substring(index2 + 1);
        }
        else if (n > 0) {
            s = s + ".";
        }
        for (int i = n - s1.length(); i > 0; i--) {
            s = s + "0";
        }
        return s;
    }

    //\u00B6\u00D4\u00CA\u00FD×\u00D6\u00B8\u00F1\u00CA\u00BD\u00BB\u00AF\u00CA\u00E4\u00B3\u00F6
    public static String numFormat(double n) {
        String s = "";

//        System.out.println("old=="+n);

        s = java.text.DecimalFormat.getInstance().format(n);

//         System.out.println("new=="+s);

        s = replaceString(s, ",", "");

//         System.out.println("return string =="+s);

        return s;
    }

    public static String numFormat(long n) {
        String s = "";
        s = java.text.DecimalFormat.getInstance().format(n);
        s = replaceString(s, ",", "");
        return s;
    }

    public static String numFormat(int n) {
        String s = "";
        s = java.text.DecimalFormat.getInstance().format(n);
        s = replaceString(s, ",", "");
        return s;
    }

    public static String numFormat(float n) {
        String s = "";
        s = java.text.DecimalFormat.getInstance().format(n);
        s = replaceString(s, ",", "");
        return s;
    }

    public static String numFormat(String n) {
        String s = "";
        if (n == null || n.length() == 0)return "";
        else {
            try{
                s = java.text.DecimalFormat.getInstance().format(Double.parseDouble(n));
                s = replaceString(s, ",", "");
            }
            catch(Exception e){
            }
        }
        return s;
    }

    /**
     * 判断字符串是否为数据
     * @param str String
     * @return boolean
     */
    public static boolean isNumType(String str){

        boolean retValue = true;
        if(str == null || str.length() <= 0)
            return false;
        for(int i=0;i<str.length();i++){
            char t = str.charAt(i);
            if(t < '0' || t > '9'){
                if(t != '.' && t != '-'){
                    retValue = false;
                    break;
                }
            }
        }
        return retValue;

    }

    /** 说明: 将字符串变量由null转换为""串
     *  @param str 需要处理的字符串
     *  @return 处理后的字符串
     */
    public static String  nullToStr (String str){
        if (str==null) str="";
        return str.trim();
    }

    /**
   * 双精度浮点数取小数点后若干位.
   * <p>
   * 取浮点数后若干位, 其余位数按四舍五入舍去.
   *
   * @param        f 要处理的浮点数.
   * @param        n 小数点后要保留的小数位数.
   * @return       进行小数点位数处理后的浮点数转换后的字符串.
   */
  public static String roundString(double f, int n) {
    int r = 1;
    double f2;
    for (int i = 1; i <= n; i++) r = r * 10;
    f2 = ( (double) Math.round(f * r)) ;
    String s = java.text.DecimalFormat.getInstance().format(f2);
    s = replaceString(s, ",", "");

    if(n>0){
      if(s.length()<=n){
        for(int i=s.length();i<=n;i++){
          s="0"+s;
        }
      }
      s = s.substring(0, s.length() - n ) + "." +
          s.substring(s.length() - n );
    }
    return s;
  }

  /**
   * 公式格式化
   * @param formulaStr
   * @return
   */
  public static String formulaFormat(String valueStr){
	  if(valueStr == null)
		  valueStr = "NULL";
	  valueStr = valueStr.replaceAll("null","0");
	  valueStr = valueStr.replaceAll("NULL","0");
      
	  if(valueStr.equals(""))
  		  valueStr = "0";  
      if(valueStr.startsWith("-") || valueStr.startsWith("+"))
    	  valueStr = "(0" + valueStr + ")" ;      
    	  
	  return valueStr;
  }
  
  /**
   * 将数字转换为字符，例如：1＝》A，2＝》B，27＝》AA，28＝》AB
   * 目前：最大值到ZZ
   * @param numValue
   * @return
   */
  public static String num2Char(int numValue){
	  
	  int fatherNum = 0;
	  while(numValue > 26){
		  fatherNum++;
		  numValue -= 26;
	  }
	  char numChar = (char)('A' + numValue -1);
	  String retValue = String.valueOf(numChar);
	  if(fatherNum > 0)
		  retValue += String.valueOf((char)('A' + fatherNum -1));
	  
	  return retValue;
  }

  public static int getLength(String str){
	  	if(str==null || str.length()==0)
	  		return 0;
	    int length=0;
	    for(int i=0;i<str.length();i++){
	        if(str.charAt(i)>'~')
	            length+=2;
	        else
	            length++;
	    }
	    return length;
	}
  public static Object[] listToObjArr(ArrayList list){
	  if(list.size()==0){
		  return null;
	  }
	  Object[] obj=new Object[list.size()];
	  for(int i=0;i<list.size();i++){
			obj[i]=list.get(i);
		}
	  return obj;
  }

  /**
	  * 解码 前台ajax传输乱码问题
	  */
	 public static String  unescape (String src) {  
		 StringBuffer tmp = new StringBuffer();  
		 tmp.ensureCapacity(src.length());  
		 int  lastPos=0,pos=0;  
		 char ch;  
		 while (lastPos<src.length())  {   
			 pos = src.indexOf("%",lastPos);   
			 if (pos == lastPos){    
				 if (src.charAt(pos+1)=='u'){     
					 ch = (char)Integer.parseInt(src.substring(pos+2,pos+6),16);     
					 tmp.append(ch);     
					 lastPos = pos+6;     
				}else{    
					ch = (char)Integer.parseInt(src.substring(pos+1,pos+3),16);    
					tmp.append(ch);    
					lastPos = pos+3;     
				}    
			 }else {    
				 if (pos == -1){    
					 tmp.append(src.substring(lastPos));     
					 lastPos=src.length();     
				 }else{    
					tmp.append(src.substring(lastPos,pos));    
					lastPos=pos;     
				 }    
			 }  
		 }  
		 return tmp.toString(); 
	}
	 /**
	  * COPY AND MODI ARRAY deepToString
	  * //将JDK1.5改为1.4
	  * @param a
	  * @return
	  */
	public static String deepToString(Object[] a) {
	        if (a == null)
	            return "null";

	        int bufLen = 20 * a.length;
	        if (a.length != 0 && bufLen <= 0)
	            bufLen = Integer.MAX_VALUE;
	        StringBuffer buf = new StringBuffer(bufLen);
	        buf.append('[');
	        for (int i = 0; i < a.length; i++) {
	            if (i != 0)
	                buf.append(", ");

	            Object element = a[i];
	            if (element == null) {
	                buf.append("null");
	            } else {
	                Class eClass = element.getClass();

	                if (eClass.isArray()) {
	                    if (eClass == byte[].class)
	                        buf.append(toString((byte[]) element));
	                    else if (eClass == short[].class)
	                        buf.append(toString((short[]) element));
	                    else if (eClass == int[].class)
	                        buf.append(toString((int[]) element));
	                    else if (eClass == long[].class)
	                        buf.append(toString((long[]) element));
	                    else if (eClass == char[].class)
	                        buf.append(toString((char[]) element));
	                    else if (eClass == float[].class)
	                        buf.append(toString((float[]) element));
	                    else if (eClass == double[].class)
	                        buf.append(toString((double[]) element));
	                    else if (eClass == boolean[].class)
	                        buf.append(toString((boolean[]) element));
	                    else { // element is an array of object references
	                    	 buf.append("[...]");
	                    }
	                } else {  // element is non-null and not an array
	                    buf.append(element.toString());
	                }
	            }
	        }
	        buf.append(']');
	        return buf.toString();
	}
	 public static String toString(Object[] a) {
	        if (a == null)
	            return "null";
		int iMax = a.length - 1;
	        if (iMax == -1)
	            return "[]"; 
	        StringBuffer b = new StringBuffer();
		b.append('[');
	        for (int i = 0; ; i++) {
	            b.append(String.valueOf(a[i]));
	            if (i == iMax)
			return b.append(']').toString();
		    b.append(", ");
	        }
	 }
	 public static String toString(long[] a) {
	        if (a == null)
	            return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
	            return "[]";

		StringBuffer b = new StringBuffer();
	        b.append('[');
	        for (int i = 0; ; i++) {
	            b.append(a[i]);
		    if (i == iMax)
			return b.append(']').toString();
	            b.append(", ");
	        }
	    }
	 public static String toString(int[] a) {
	        if (a == null)
	            return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
	            return "[]";

		StringBuffer b = new StringBuffer();
	        b.append('[');
	        for (int i = 0; ; i++) {
	            b.append(a[i]);
		    if (i == iMax)
			return b.append(']').toString();
	            b.append(", ");
	        }
	    }
	 public static String toString(short[] a) {
	        if (a == null)
	            return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
	            return "[]";

		StringBuffer b = new StringBuffer();
	        b.append('[');
	        for (int i = 0; ; i++) {
	            b.append(a[i]);
		    if (i == iMax)
			return b.append(']').toString();
	            b.append(", ");
	        }
	    }
	 public static String toString(char[] a) {
	        if (a == null)
	            return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
	            return "[]";

		StringBuffer b = new StringBuffer();
	        b.append('[');
	        for (int i = 0; ; i++) {
	            b.append(a[i]);
		    if (i == iMax)
			return b.append(']').toString();
	            b.append(", ");
	        }
	    }
	 public static String toString(byte[] a) {
	        if (a == null)
	            return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
	            return "[]";

		StringBuffer b = new StringBuffer();
	        b.append('[');
	        for (int i = 0; ; i++) {
	            b.append(a[i]);
		    if (i == iMax)
			return b.append(']').toString();
	            b.append(", ");
	        }
	    }
	  public static String toString(boolean[] a) {
	        if (a == null)
	            return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
	            return "[]";

		StringBuffer b = new StringBuffer();
	        b.append('[');
	        for (int i = 0; ; i++) {
	            b.append(a[i]);
		    if (i == iMax)
			return b.append(']').toString();
	            b.append(", ");
	        }
	    }
	  public static String toString(float[] a) {
	        if (a == null)
	            return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
	            return "[]";

		StringBuffer b = new StringBuffer();
	        b.append('[');
	        for (int i = 0; ; i++) {
	            b.append(a[i]);
		    if (i == iMax)
			return b.append(']').toString();
	            b.append(", ");
	        }
	    }
	  public static String toString(double[] a) {
	        if (a == null)
	            return "null";
		int iMax = a.length - 1;
		if (iMax == -1)
	            return "[]";

		StringBuffer b = new StringBuffer();
	        b.append('[');
	        for (int i = 0; ; i++) {
	            b.append(a[i]);
		    if (i == iMax)
			return b.append(']').toString();
	            b.append(", ");
	        }
	    }

	  public static String strReplace(String oldStr,String oldflag,String newflag){
		 String newStr="";
		 int inFlag = -1;
		 if(oldStr ==null ||oldStr.length()==0){
			 return oldStr;
		 }
		 if(oldflag==null||oldflag.length()==0){
			 return oldStr;
		 }
		 if(newflag==null){
			 return oldStr;
		 }
		 if(oldStr.indexOf(oldflag)==-1){
			 return oldStr;
		 }
		 while(oldStr.length()>0){
			 inFlag = oldStr.indexOf(oldflag);
			 System.out.println("inflag = "+inFlag);
			 if(inFlag == -1){
				 break;
			 }
			 newStr = oldStr.substring(0, inFlag);
			 newStr += newflag;
//			 System.out.println("oldStr="+oldStr);
			 if(oldStr.substring(inFlag+oldflag.length()).indexOf(oldflag)==0){
			 }else{
				 newStr += oldStr.substring(inFlag+oldflag.length());
			 }
			 oldStr = newStr;
		 }
		 return newStr;
	  }
	  /**
	   * 处理乱码，必须保证用系统统一编码；
	   */
	  public static String handleSystemCode(String handleString, String code){
		  if(handleString!=null){
		  	String[] docnames = null; 
			byte[] docnameBytes = null;
			if (handleString != null && handleString.length()>0){
				docnames = handleString.split("```");
			}
			if (docnames !=null && docnames.length>0){
				//第一个字符为空
				docnameBytes = new byte[docnames.length-1];
			}
			for(int i=1;i<docnames.length;i++){
				docnameBytes[i-1] = Byte.parseByte(docnames[i]);
			}
			String temp = "";
			try {
				temp = new String(docnameBytes, code);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return temp;
		  }else{
			  return "";
		  }
	  }
	  /**
	   * 16进制字符转换
	   */
	  public static String toHexString(byte[] temp){
			String tem = "";
			for(int m=0; m<temp.length; m++){
				tem += Integer.toHexString(temp[m] & 0xff) ;
			}
			return tem;
	  }
		/**
		 * 
		 * 功能说明：判断字符串是否为空白串，即是否为null或者字符串中包含的字符均为空白字符，如空格、tab、换行、回车
		 * @param str 需要判断的字符串
		 * @return 返回判断结果
		 */
		public static boolean isBlank(String str){
			if(str != null){
				char[] chs = str.toCharArray();
				for(int i = 0; i < chs.length; i ++){
					if(Character.isWhitespace(chs[i])){
						continue;
					}
					return false;
				}
			}
			return true;
		}
		
		/**
		 * 
		 * 功能说明：判断字符串是否非空白，即字符串是否不为空串且包含非空白字符
		 * @param str 需要判断的字符串
		 * @return 返回判断结果
		 */
		public static boolean isNotBlank(String str){
			return !isBlank(str);
		}
		
		public static String joinWith(String split,Object[] objs){
			if (objs == null || objs.length == 0)
				return "";
			StringBuffer str = new StringBuffer();
			for (Object o : objs){
				str.append(o.toString()).append(split);
			}
			str.delete(str.length() - split.length(), str.length());
			return str.toString();
		}
		/**
		 * 
		 * 功能说明：判断字符串是否为字符串的一部分
		 * @param str 需要判断的字符串
		 * @return 返回判断结果
		 */
		public static boolean isPartOf(String strTotal,String strPart,String splitStr){
			boolean result = false;
			String temp = "";
			if(StringUtils.isBlank(strTotal)){
				return false;
			}
			if(StringUtils.isBlank(strPart)){
				return false;
			}
			if(StringUtils.isBlank(splitStr)){
				return false;
			}
			strTotal = strTotal.trim();
			strPart = strPart.trim();
			StringTokenizer st = new StringTokenizer(strTotal ,splitStr);
			while(st.hasMoreElements()){
				temp = (String)st.nextToken();
				if(strPart.equals(temp)){
					result = true;
					break;
				}
			}
	        return result;
        } 
}
