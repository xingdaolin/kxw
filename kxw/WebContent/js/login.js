var loginStateData = [
                ['0', '不自动登录'],
                ['1', '在一天内自动登录'],
                ['2','在一周内自动登录'],
                ['3', '在一个月内自动登录'],
                ['4', '在一年内自动登录']
                ];
var loginStateStore = new Ext.data.SimpleStore(
		{
			fields:['id', 'value'],
			data:loginStateData			
		}		
);
var loginStateCombobox = new Ext.form.ComboBox(
		{
			
			fieldLabel:'登录状态',
			store:loginStateStore,
			editable:false,
			allowBlank:false,
			blankText:'必须输入登录状态',
			mode:'local',
			name:'loginStatus',
			id:'loginStatusId',
			value:'0',
			triggerAction:'all',
			valueField:'id',
			displayField:'value',
			hiddenName : "loginStateId",
			readOnly:false,
			anchor : '90%'
		}		
);

var loginForm = new Ext.form.FormPanel({
	frame:true,
	labelAlign:'right',
	labelWidth:100,
	url:'login.action',
	align:'center',
	defaultType:'textfield',
	items:[
	{
		fieldLabel:'用户名',
		emptyText:'您的邮件地址',
		id:'loginname',
		allowBlank:false,
		blankText:'用户名必须填写',
		vtype:'email',
		vtypeText:'请输入正确的邮件格式',
		name:'email',
		anchor:'90%'
	},{
		fieldLabel:'开心网密码',
		inputType:'password',
		allowBlank:false,
		blankText:'必须输入密码',
		name:'password',
		id:'loginipassword',
		anchor:'90%'
	},{
		fieldLabel:'请输入下面验证码',
		name:'validateCode',
		id:'validateCodeid',
		allowBlank:false,
		blankText:'必须输入验证码',
		anchor:'90%'
	},{
		xtype:'fieldset',
		anchor:'90%',
		border:false,
		layout:'column',
		items:[
		{
			xtype:'label',
			style:'margin:5px 5px 5px 5px',
			html:'<a href="#" onclick="refashLoginValidateCode();"><img src="validationCode" id="img2"/></a>'
		},{
			xtype:'label',
			style:'margin:5px 10px 5px 10px;color:red',
			html:'<span>点击图片刷新验证码<span>'
		}
		]
	},loginStateCombobox
	]
});

var login = new Ext.Window({
	title:'用户登录',
	fit:true,
	modal:true,
	width:400,
	autoHeight:true,
	closeAction:'hide',
	closable:false,
	resizable:false,
	items:[loginForm],
	buttonAlign:'center',
	buttons:[
	{
		text:'<span style="font-size:12px;">登&nbsp;&nbsp;&nbsp;录</span>',
		height:15,
		handler:loginSubmit
	},{
		text:'<span style="font-size:12px">注&nbsp;&nbsp;&nbsp;册</span>',
		height:25,
		handler:function(){
			//显示注册页面
			register.show();
		}
	}
	]
});
//登录的方法
function loginSubmit(){
	//客户端验证
	if(!loginForm.getForm().isValid()){
		showErrorDialog("请检查输入参数的正确性");
		return;
	}
	var user = {
		"user.email":Ext.getCmp("loginname").getValue(),
		"user.password":Ext.getCmp("loginipassword").getValue(),
		"user.validateCode":Ext.getCmp("validateCodeid").getValue(),
		"user.loginStatus":loginStateCombobox.getValue()
	}
	//提交
	Ext.Ajax.request({
		url:'login.action',
		params:user,
		success:function(response,opts){
			var data = Ext.util.JSON.decode(response.responseText);
			if(data.success=='false'){//登录失败
				showErrorDialog(data.msg);
			}else{
					login.hide();
					//跳转到主页面
					window.location.href="login.action";
			}
		
		},
		failure:function(response,opts){//如果返回的不是200，则执行此函数
			var data = response.responseText;
				showErrorDialog(data);
		}
	});
}