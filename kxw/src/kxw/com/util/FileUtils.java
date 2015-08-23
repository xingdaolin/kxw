package kxw.com.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;


public class FileUtils{

	// private static Logger logger =
	// LogUtils.getLogger(FileUtils.class.getName());
	/** 文件缓冲区的长度 */
	private static int	buffersize	= 1024;

	/**
	 * 构造函数
	 */
	public FileUtils(){

	}

	/**
	 * 取得文件的真实路径
	 * 
	 * @param fileName
	 *        文件名
	 * @return 文件所在路径
	 */
	public synchronized static String getCanonicalFileName(String fileName){

		try{
			File file = new File(fileName);
			return file.getCanonicalPath();// + File.separatorChar +
			// file.getName();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 从文件路径中读取文件名称
	 * 
	 * @param oldFileUrl
	 *        文件名含路径
	 * @return 文件名
	 */
	public synchronized static String getFilenameFromPath(String oldFileUrl){

		int lastIndex = oldFileUrl.lastIndexOf("/");
		if(lastIndex < 1 || lastIndex >= oldFileUrl.length() - 1)
			lastIndex = oldFileUrl.lastIndexOf("/");
		if(lastIndex >= 0 && lastIndex <= oldFileUrl.length())
			return oldFileUrl.substring(lastIndex + 1).trim();
		else
			return oldFileUrl;
	}

	public static void writeFile(String filename,String str){

		PrintWriter pw = null;
		try{

			File file = new File(filename);
			pw = new PrintWriter(new FileOutputStream(file));
			pw.println(str);
		}catch(IOException e){
			System.out.println(e.getMessage());
		}finally{
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
	}


	/**
	 * 说明：将字符串写入一个文件中(static)
	 * 
	 * @param path
	 *        路径名称
	 * @param filename
	 *        读取的文件模版
	 * @param str
	 *        写入的字符串
	 */
	public static void writeFile(String path,String filename,String str){

		PrintWriter pw = null;
		try{
			File filePath = new File(path);
			if(!filePath.exists()){
				filePath.mkdirs();
			}
			File file = new File(path,filename);
			pw = new PrintWriter(new FileOutputStream(file));
			pw.println(str);

		}catch(IOException e){
			System.out.println(e.getMessage());
		}finally{
			if(pw != null){
				pw.flush();
				pw.close();
			}
		}
	}

	public static boolean writeFile(String filename,InputStream in){

		boolean result = false;
		FileOutputStream output = null;
		try{
			output = new FileOutputStream(filename);
			byte[] buffer = new byte[buffersize];
			int len;
			while((len = in.read(buffer,0,buffersize)) > 0){
				output.write(buffer,0,len);
			}
			result = true;
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}finally{
			try{
				if(in != null){
					in.close();
				}
				if(output != null){
					output.flush();
					output.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				result = false;
			}
		}
		return result;
	}

	/**
	 * 说明：读取文件内容(static)
	 * 
	 * @param filename
	 *        读取的文件名
	 * @return String 文件的字符串代码
	 */
	public static String readFile(String filename){

		String return_str = "";
		FileReader fr = null;
		LineNumberReader lr = null;
		try{
			fr = new FileReader(filename);
			lr = new LineNumberReader(fr,512);
			while(true){
				String str = lr.readLine();
				if(str == null)
					break;
				return_str += str + "\n";
			}
		}catch(FileNotFoundException e){
			System.out.println("FILENAME:" + filename);
			System.out.println("File not found");
			return_str = "error1!";
		}catch(IOException e){
			System.out.println("IO error");
			return_str = "error2!";
		}finally{
			if(lr != null){
				try{
					lr.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(fr != null){
				try{
					fr.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return return_str;
	}

	/**
	 * 说明：模板替换并返回(static)
	 * 
	 * @param filename
	 *        读取的文件模版
	 * @param tablename
	 *        替换列表
	 * @return String 模板替换出来的字符串
	 */
	// public static String replaceModel(String filename,Hashtable tablename){
	// String rstr=readFile(filename);
	// if(rstr.equals("error1!") || rstr.equals("error2!"))
	// return rstr;
	// for (Enumeration e=tablename.keys();e.hasMoreElements();){
	// String a=(String) e.nextElement();
	// String newstr=(String) tablename.get(a);
	// rstr=StringUtils.replaceString(rstr,a,newstr);
	// }
	// return rstr;
	// }
	public static String replaceModel(String filename,HashMap tablename){

		String rstr = readFile(filename);
		if(rstr.equals("error1!") || rstr.equals("error2!"))
			return rstr;
		Iterator iterator = tablename.keySet().iterator();
		while(iterator.hasNext()){
			String a = (String)iterator.next();
			String newstr = (String)tablename.get(a);
			rstr = StringUtils.replaceString(rstr,a,newstr);
		}
		return rstr;
	}

	/**
	 * 说明:单个文件的拷贝 文件属性在拷贝中丢失
	 * 
	 * @param from
	 *        原文件
	 * @param to
	 *        目标文件
	 * @return boolean true 成功 false 失败
	 */
	public static boolean copyFile(File from,File to){

		FileInputStream input;
		FileOutputStream output;
		try{
			input = new FileInputStream(from);
			output = new FileOutputStream(to);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		byte[] buffer = new byte[buffersize];
		int len;
		try{
			while((len = input.read(buffer,0,buffersize)) > 0){
				output.write(buffer,0,len);
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			try{
				if(input != null){
					input.close();
				}
				if(output != null){
					output.flush();
					output.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * 说明：将内容写到文件中,如果文件已经存在，则接到后面写
	 * 
	 * @param directory
	 *        路径
	 * @param filename
	 *        文件名
	 * @param content
	 *        内容
	 * @return boolean 写文件成功或失败
	 */
	public boolean contentAppendToFile(String directory,String filename,String content){

		FileOutputStream fos = null;
		boolean flag = true;
		try{
			File objFile = new File(directory,filename);
			if(!objFile.exists()){
				objFile.createNewFile();
				System.out.println("文件不存在!");
			}
			//
			byte[] b = content.getBytes();
			fos = new FileOutputStream(directory + "/" + filename,true);
			// System.out.println("开始写文件!");
			fos.write(b);
			// System.out.println("写文件结束!");
		}catch(Exception e){
			System.out.println(e.getMessage());
			flag = false;
		}finally{
			try{
				if(fos != null){
					fos.flush();
					fos.close();
				}
			}catch(Exception e){
			}
		}
		return flag;
	}

	/**
	 * 删除文件
	 * 
	 * @param filename
	 *        文件名(要包括完整的路径)
	 */
	public static void deleteDir(String filename){

		boolean isSucc = false;
		try{
			File file = new File(filename);
			if(file.exists()){
				deleteFile(file);
				isSucc = file.delete();
			}
		}catch(Exception e){
			System.out.println("Delete File Fails:" + e.getMessage());
		}finally{
			if(isSucc){
				System.out.println("删除成功!");
			}else{
				System.out.println("删除失败!");
			}
		}
		System.out.println("-------删除路径：" + filename);
	}

	public static void deleteFile(File file){

		if(file == null || !file.exists())
			return;
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(int i = 0;i < files.length;i++){
				deleteFile(files[i]);
			}
			file.delete();
		}else{
			file.delete();
		}
	}

	public ArrayList getlistFiles(String filename){

		ArrayList list = new ArrayList();
		try{
			File file = new File(filename);
			if(file.exists() && file.isDirectory()){
				File[] files = file.listFiles();
				for(int i = 0;i < files.length;i++){
					File subfile = files[i];
					list.add(subfile);
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		return list;
	}

	/** ********************************************************************************************** */

	/**
	 * auth:ligy 2007-09-04 上传文件大小的上限
	 * 
	 * @param propertiesPath
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static long getMaxFileSize(HttpServletRequest request) throws NumberFormatException,IOException{

		long maxFileSize = 1048576;// 3M
		maxFileSize = new Long(getValueFromProperties(request,"maxFileSize").toString()).longValue();
		return maxFileSize;
	}

	/**
	 * auth:ligy 2007-09-04 判断上传文件的大小是否受限
	 * 
	 * @param filesize
	 * @param propertiesPath
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static boolean docErrrorSize(HttpServletRequest request,int filesize) throws NumberFormatException,IOException{

		long upperSize = 0;
		upperSize = getMaxFileSize(request);
		if(filesize > upperSize){
			return false;
		}
		return true;
	}

	/**
	 * auth:ligy 2007-09-04 用关键字从属性文件中取关键值
	 * 
	 * @param String
	 *        propertiesFilePath-属性文件实际路径,String key-关键字
	 * @return String value-关键值
	 * @throws IOException
	 */
	public static String getValueFromProperties(HttpServletRequest request,String key) throws IOException{

		String value = "";
		String propertiesPath = request.getRealPath("/") + "/WEB-INF/classes/system.properties";
		FileInputStream file = null;
		try{
			file = new FileInputStream(propertiesPath);
			if(file != null){
				InputStream in = new BufferedInputStream(file);
				Properties props = new Properties();
				props.load(in);
				value = props.getProperty(key);
			}

		}catch(IOException e){
			e.printStackTrace();
			throw new IOException(e.toString());
		}finally{
			if(file != null){
				file.close();
			}
		}

		return value;
	}
	
	/**
	 * ligy 2007-09-04 创建某个目录
	 * 
	 * @param _path
	 * @return
	 * @throws IOException
	 */
	public static boolean createDir(String _path) throws IOException{

		boolean success = false;
		String filePath = _path.toString();
		try{
			File FilePath = new File(filePath);
			if(!FilePath.exists()){
				FilePath.mkdirs();
			}
			// else{
			// deleteDir(filePath);
			// }
			success = true;

		}catch(Exception e){
			e.printStackTrace();
			throw new IOException(e.toString());
		}
		System.out.println("-------创建路径：" + _path);
		return success;
	}

	/**
	 * auth:ligy saveToFile 把文件写入磁盘
	 * 
	 * @param InputStream
	 *        oIS, String sFileName
	 * @return int 0-成功写入
	 * @throws IOException
	 */
	public void saveToFile(InputStream oIS,String sFileName) throws IOException{

		FileOutputStream out = null;
		// 这里可以保存倒数据库或者磁盘
		try{
			out = new FileOutputStream(sFileName);
			byte b[] = new byte[10 * 1024 * 1024];
			int i = 0;
			while((i = oIS.read(b)) > 0){
				out.write(b,0,i);
			}
		}catch(IOException e){
			e.printStackTrace();
			throw new IOException(e.toString());
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * ligy 该类中已存在readFile( String filename )方法 但认为该方法实现欠佳
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String readWordFile(String input) throws IOException{

		char[] buffer = new char[4096];
		int len = 0;
		StringBuffer content = new StringBuffer(4096);
		String ENCODING = "UTF-8";
		InputStreamReader fr = null;
		BufferedReader br = null;
		try{
			fr = new InputStreamReader(new FileInputStream(input),ENCODING);
			br = new BufferedReader(fr);
			while((len = br.read(buffer)) > -1){
				content.append(buffer,0,len);
			}
		}catch(IOException e){
			e.printStackTrace();
			throw new IOException(e.toString());
		}finally{
			if(br != null)
				br.close();
			if(fr != null)
				fr.close();
		}

		return content.toString();
	}

	/**
	 * 将File文件读出String
	 * 
	 * @param file
	 * @return String
	 * @throws IOException
	 */
	public static String readWordFile(File file) throws IOException{

		char[] buffer = new char[4096];
		int len = 0;
		StringBuffer content = new StringBuffer(4096);
		String ENCODING = "UTF-8";
		InputStreamReader fr = null;
		BufferedReader br = null;
		try{
			fr = new InputStreamReader(new FileInputStream(file),ENCODING);
			br = new BufferedReader(fr);
			while((len = br.read(buffer)) > -1){
				content.append(buffer,0,len);
			}
		}catch(IOException e){
			e.printStackTrace();
			throw new IOException(e.toString());
		}finally{
			if(br != null)
				br.close();
			if(fr != null)
				fr.close();
		}

		return content.toString();
	}

	/**
	 * auth:ligy
	 * 
	 * @param output
	 * @param content
	 * @throws Exception
	 */
	public static void createFile(String output,String content) throws IOException{

		OutputStreamWriter fw = null;
		PrintWriter out = null;
		String ENCODING = "GBK";
		FileOutputStream fileOutputStream = null;
		try{
			fileOutputStream = new FileOutputStream(output);
			fw = new OutputStreamWriter(fileOutputStream,ENCODING);
			out = new PrintWriter(fw);
			out.print(content);
		}catch(IOException ex){
			ex.printStackTrace();
			throw new IOException(ex.toString());
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
			if(fw != null){
				fw.close();
			}
			if(fileOutputStream != null){
				fileOutputStream.flush();
				fileOutputStream.close();
			}

		}
	}

	/**
	 * auth:ligy 上面deleteDir(String filename)方法欠佳
	 * 
	 * @param path
	 * @throws IOException
	 */
	public static void delDir(String path) throws IOException{

		try{
			File file = new File(path);
			if(!file.isDirectory()){
				file.delete();
				return;
			}else if(file.isDirectory()){
				String[] filelist = file.list();
				for(int i = 0;i < filelist.length;i++){
					File delfile = new File(path + "/" + filelist[i]);
					if(!delfile.isDirectory()){
						delfile.delete();
					}else if(delfile.isDirectory()){
						delDir(filelist[i]);
					}
				}
				file.delete();
			}
		}catch(IOException e){
			e.printStackTrace();
			throw new IOException(e.toString());
		}
	}

	/**
	 * auth:ligy 用于压缩整个目录或者单个文件 用于压缩中文名文档
	 * 
	 * @param out
	 *        源文件的输出流
	 * @param f
	 *        目标压缩文件的输入流
	 * @param base
	 *        a
	 * @throws Exception
	 */
	/*public static void zipC(java.util.zip.CZipOutputStream out,File f,String downFileName,String base) throws IOException{

		if(f.isDirectory()){
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			for(int i = 0;i < fl.length;i++){
				zipC(out,fl[i],downFileName,base);
			}
		}else{
			base = base.length() == 0 ? "" : base + "/";
			out.putNextEntry(new ZipEntry(base + downFileName + ".doc"));
			FileInputStream in = new FileInputStream(f);
			int b;
			while((b = in.read()) != -1)
				out.write(b);
			in.close();
		}
	}*/

	/**
	 * auth:ligy 压缩一组文件
	 * 
	 * @param zipFileName
	 * @param fileList
	 * @param base
	 * @throws Exception
	 * @throws Exception
	 */
	/*public static void zipC(String zipFileName,ArrayList fileList,ArrayList lst_name,String base) throws Exception{

		// 压缩文件流
		java.util.zip.CZipOutputStream out = null;
		try{
			out = new java.util.zip.CZipOutputStream(new FileOutputStream(zipFileName),"GB2312");
			out.putNextEntry(new ZipEntry(base + "/"));
			List lst = new ArrayList();
			for(int i = 0;i < fileList.size();i++){
				String srcfilePath = (String)fileList.get(i);
				String fileName = (String)lst_name.get(i);
				// File srcfile = new File(filePath);

				String destFilePath = srcfilePath.substring(0,srcfilePath.lastIndexOf("/"));
				destFilePath += "/" + System.currentTimeMillis() + ".doc";
				lst.add(destFilePath);
				File destFile = new File(destFilePath);
				FileInputStream fis = new FileInputStream(srcfilePath);
				CipherInputStream cis = Des3Tool.decryptMode(Constant.THREE_DES_KEY,fis);
				ZipInputStream zis = new ZipInputStream(new BufferedInputStream(cis));
				FileOutputStream fos = new FileOutputStream(destFile);
				ZipEntry entry;
				BufferedOutputStream dest = null;
				int BUFFER = 2048;
				while((entry = zis.getNextEntry()) != null){
					System.out.println("Extracting: " + entry);
					int s;
					byte[] buf = new byte[BUFFER];
					dest = new BufferedOutputStream(fos,BUFFER);
					while((s = zis.read(buf,0,BUFFER)) != -1){
						dest.write(buf,0,s);
					}
					dest.flush();
					dest.close();
				}
				zipC(out,destFile,fileName,base);
			}
			out.closeEntry();
			out.close();
			for(int i = 0;i < lst.size();i++){
				FileUtils.delDir((String)lst.get(i));
			}
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.toString());
		}finally{
			if(out != null)
				out.close();
		}
	}*/

	/**
	 * 拷贝文件 ligy
	 * 
	 * @param source
	 * @param dest
	 * @throws IOException
	 *         add by: huangjh 2009-7-2
	 */
	public static void copyFile(String source,String dest) throws IOException{

		FileInputStream inFile = null;
		FileOutputStream outFile = null;
		try{
			File in = new File(source);
			File out = new File(dest);
			inFile = new FileInputStream(in);
			outFile = new FileOutputStream(out);
			byte[] buffer = new byte[1024];
			int i = 0;
			while((i = inFile.read(buffer)) != -1){
				outFile.write(buffer,0,i);
			}
		}catch(Exception e){ // delete by: huangjh 2009-7-2
			e.printStackTrace();
		}finally{
			try{
				if(inFile != null){
					inFile.close();
				}
				if(outFile != null){
					outFile.flush();
					outFile.close();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 复制整个目录下的文件 ligy
	 * 
	 * @param source
	 *        原文件路径
	 * @param dest
	 *        目标文件路径
	 * @throws IOException
	 *         add by: huangjh 2009-7-2
	 */
	public static void copyDict(String source,String dest) throws IOException{

		String source1;
		String dest1;
		File[] file = (new File(source)).listFiles();
		if(file == null){
			return;
		}
		for(int i = 0;i < file.length;i++){
			if(file[i].isFile()){
				source1 = source + "/" + file[i].getName();
				dest1 = dest + "/" + file[i].getName();
				copyFile(source1,dest1);
			}
		}

		for(int i = 0;i < file.length;i++){
			if(file[i].isDirectory()){
				source1 = source + "/" + file[i].getName();
				dest1 = dest + "/" + file[i].getName();
				File dest2 = new File(dest1);
				dest2.mkdir();
				copyDict(source1,dest1);
			}
		}
	}

	public static String getApplicationPath(){

		File file = new File("");
		file.getPath();
		return file.getAbsolutePath() + "//nhaudit";
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 *        测试参数
	 */
	public static void main(String[] args){

		// deleteDir("D:/project/nhaudit/WebRoot/document/workdoc/a.doc");
		// copyDict("D:/nh_jhxt/其他/工程备份/20071004", "D:/nh_jhxt/其他/工程备份/debug");
//		File f = new File("D:/nh_jhxt/其他/工程备份/20071004/doc_mag/");
//		System.out.println(getApplicationPath());
	}
}
