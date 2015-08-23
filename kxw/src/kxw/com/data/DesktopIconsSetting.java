package kxw.com.data;
/**
 * @ClassName: DesktopIconsSetting 
* @Description:图标配置类
* @author xingdl@yuchengtech.com 
* @date 2014年12月25日 上午12:18:39 
* @version V1.0
 */
public class DesktopIconsSetting {
	private String id;
	private String title;
	private String url;
	private String privateId;
	
	public String getPrivateId() {
		return privateId;
	}
	public void setPrivateId(String privateId) {
		this.privateId = privateId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
