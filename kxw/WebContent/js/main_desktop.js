//导入控制窗口js文件
$importJs(WEB_ROOT+"/js/control_win.js");
$importJs(WEB_ROOT+"/js/desktop_icons.js");
$importJs(WEB_ROOT+"/js/login.js");
var blogin=true;
var myDesktop;
//创建桌面组件 
var MyDesktop =new Ext.app.App({
	//初始化
	init:function(){
		Ext.QuickTips.init();
	},
	//创建功能模块
	getModules:function(){
		//返回桌面上要添加的模块
		return [new ControlPanelWindow(), new DesktopIconsWindow() ];
	},
	//配置开始菜单
	getStartConfig:function(){
		return {
			title:'开心菜单',
			iconCls:'user',
			toolItems:[{
				text:'注销',
				iconCls:'logout',
				handler:function(){
					//判断用户是否登录
					if(blogin==false)
						return;
						//关闭打开的所有窗口
					if(myDesktop!=undefined){
						myDesktop.closeAll();
					}
					Ext.Ajax.request({url:'loginOut.action'});
					blogin=false;
//					login.show();
//					//刷新验证码
//					refashLoginValidateCode();
					window.location.href="index.jsp";
				},
				scope:this
			},'-',{
				text:'设置',
                iconCls:'settings',
                scope:this
			}]
		}
	}
});