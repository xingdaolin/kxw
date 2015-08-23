//***注册信息组件*****
/*登录用的基本信息fieldset*/
var regEmail = new Ext.form.TextField({
	fieldLabel:'邮件地址',
	vtype:'email',
	allowBlank:false,
	blankText:'请输入邮件地址',
	vtypeText:'邮件格式不正确,格式应为123@163.com',
	name:'email',
	id:'emailid'
});
var regPassword = new Ext.form.TextField({
	inputType:'password',
	id:'regpassword',
	name:'password',
	minLength:6,
	minLengthText:'最少要6位',
	maxLength:12,
	maxLengthText:'不能超过12位',
	vtype:'alphanum',
	vtypeText:'只能输入字母或数字',
	fieldLabel:'设置密码',
	allowBlank:false,
	blankText:'请输入密码',
	listeners:{
		'blur':function(){
			//如果两个密码一样，把校验提示去掉
			if((!Ext.isEmpty(regRePassword.getValue()))&&regPassword.getValue()==regRePassword.getValue()){
				regRePassword.clearInvalid() ;
			}else if((!Ext.isEmpty(regRePassword.getValue()))&&regPassword.getValue()!=regRePassword.getValue()){
				regRePassword.markInvalid("前后密码不一致");
			}
		}
	}
});
var regRePassword = new Ext.form.TextField({
	inputType:'password',
	id:'passwordid2',
	allowBlank:false,
	blankText:'请再次输入密码',
	fieldLabel:'请再次输入密码'
//	validator:validatePassword
});

var loginSet = new Ext.form.FieldSet({
	title:'登录用的信息',
	autoHeight:257,
	border:true,
	layout:'form',
	id:'loginSetId',
	items:[regEmail,{html:'6~12位的字母或数字',style:'color:green;margin-left:50px;'},regPassword,regRePassword]
});
//***验证码信息fielsset
//显示验证码的图片信息
var validateCodeImgSet = new Ext.form.FieldSet({
		border:false,
		layout:'column',
		style:'margin-top:10px',
		items:[
		{
			xtype:'label',
			html:'<a href="#"  onclick="refashRegValidateCode();"><img src="validationCode" id="regImgl"/></a>'
		},{
			xtype:'label',
			style:'margin-left:10px;color:red;margin-top:10px',
			text:'点击图片可刷新验证码'
		}
		]
});
//验证码组件
var registerValidateCodeSet = new Ext.form.FieldSet({
	title:'校验',
	autoHeight:true,
	border:true,
	layout:'form',
	defaultType:'textfield',
	items:[
	{
		name:'validateCode',
		id:'validationCodeid',
		fieldLabel:'请输入验证码',
		allowBlank:false,
		blankText:'请输入验证码'
	},validateCodeImgSet
	]
});
//左边panel
var leftpanel = new Ext.form.FieldSet({
	layout:'form',
	border:false,
	autoScroll:false,
	columnWidth:.5,
	items:[loginSet,registerValidateCodeSet]
});
/* 个人基本信息fieldSet*/

var nameField = new Ext.form.TextField({
	fieldLabel:'您的姓名',
	id:'regname',
	allowBlank:false,
	blankText:'请输入您的姓名',
	regex:new RegExp('^[\u4E00-\u9FFF]+$'),
	regexText:'只能输入汉字',
	name:'name',
	anchor:'85%'
});
var sexField = new Ext.form.ComboBox({
	fieldLabel:'您的性别',
	id:'sexid',
	allowBlank:false,
	blankText:'请输入您的性别',
	mode:'local',
	editable:false,
	store: new Ext.data.ArrayStore({
			id:0,
			fields:['value','text'],
			data:[
				[1,'女'],[0,'男']
			]
		}),
		valueField :'value',
		displayField:'text',
		readOnly:false,
		anchor:'85%',
		triggerAction:'all',
		minListWidth:220,
		listeners:{
			'blur':function(){
				var value = sexField.getValue();
				if(!Ext.isEmpty(value)&&(value!=1&&value!=0)){
					sexField.markInvalid("请在下拉框中选择正确的值");
				}
			}
		}
});
var birthdayField = new Ext.form.DateField({
	id:'birthdayid',
	name:'birthday',
	allowBlank:false,
	editable:false,
	minValue:new Date().add(Date.YEAR,-150),
	maxValue:new Date(),
	blankText:'请输入您的生日',
	fieldLabel:'您的生日',
	format:'Y-m-d',
	anchor:'85%'
});
var statusStore = new Ext.data.Store(
{
	proxy : new Ext.data.HttpProxy(
	{
		url : 'status.action'
	}),
	reader : new Ext.data.JsonReader({
		root:'result'
	},[{name:'id',type:'int'}
	,{name:'name',type:'string'}
	])
});
statusStore.load();
var statusField = new Ext.form.ComboBox({
	name:'statusId',
		fieldLabel:'目前身份',
		mode:'remote',
		triggerAction:'all',
		editable:false,
		allowBlank:false,
		blankText:'请输入您的身份',
		valueField:'id',
		displayField:'name',
		store:statusStore,
		minListWidth:220,
		anchor:'85%',
		readOnly:false
});
//居住省份
var provinceHiddeField = new Ext.form.Hidden({
	id:'provincehiddeid',
	name:'province'
});
//居住的城市
var cityHiddeField = new Ext.form.Hidden({
	id:'cityhideid',
	name:'city'
});
var selectMenu;
var cityField = new Ext.form.TriggerField({
	fieldLabel:'居住城市',
	anchor:'85%',
	id:'addressid',
	editable:false,
	allowBlank:false,
	blankText:'请输入您的居住城市',
	name:'address',
	onTriggerClick:triggerFn,
	listeners:{
		'hide':function(){
			//失去焦点时，销毁和隐藏菜单
			if(this.menu!=undefined){
				this.menu.removeAll();
			}
		},
		'focus':triggerFn
	}
});
var personInfoSet = new Ext.form.FieldSet({
	title:'个人信息',
	columnWidth:.5,
	height:273,
	style:'margin-left:10px',
	layout:'form',
	items:[nameField,sexField,birthdayField,statusField,cityField,provinceHiddeField,cityHiddeField]
});
var formPanel = new Ext.form.FormPanel({
	layout:'column',
	labelAlign:'right',
	id:'regFormid',
	labelWidth:100,
	url:'register.action',
	frame:true,
items:[leftpanel,personInfoSet]
});
	var register = new Ext.Window({
	title:'用户注册',
	fit:'true',
	draggable:false,//禁止拖动面板
	modal:true,//遮罩
	width:730,
	autoHeight:true,
	closeAction:'hide',
	resizable:false,
	buttonAlign:'center',
	items:[formPanel],
	buttons:[
	{
		text:'<span style="font-size:12px">注&nbsp;&nbsp;&nbsp;册</span>',
		height:25,
		handler:registerSubmit
	},{
		text:'<span style="font-size:12px">取&nbsp;&nbsp;&nbsp;消</span>',
		height:25,
		handler:function(){
			register.hide();
			formPanel.getForm().reset();
		}
	}
	]
});
//展现城市的panel
var cityPanel;
//单击省份的方法
function provinceOnclick(proid){
	//先从菜单中移除城市显示的panel
	Ext.getCmp("provincePanel").remove(cityPanel,true);
	provinceHiddeField.setValue(proid);
	//从后台获取城市的信息格式是List<Address>
	var html_province="";
	DWREngine.setAsync(false);
	AddressDwr.getCityByProvinceId(proid,function(data){
		//遍历省份
		Ext.each(data,function(v,i){
				html_province+="<a href='#' onclick='cityOnclick("+v.id+",\""+v.name+"\");'>"+v.name+"</a>&nbsp;&nbsp;";
				if(i%13==0&&i!=0){
					html_province="<div style='margin-top:4px'>"+html_province+"</div>";
					html_province+='<br/>';
					}
		});
		html_province+="</div>";
	});
	DWREngine.setAsync(true);
		if(html_province==""){
		Ext.Msg.show({
			title:'错误',
			msg:'查询地址出错',
			icon:Ext.Msg.ERROR,
			buttons:Ext.Msg.OK
		});
		return;
	}
	cityPanel = new Ext.Panel({
		id:'citypanelid',
		border:false,
		autoHeight:true,
		style:'margin-left:10px;margin-top:10px;margin-right:10px;margin-bottom:10px;font-size:13px;pading-top:15px;',
		html:html_province
	});
	Ext.getCmp("provincePanel").add(cityPanel);
}
//城市单击的方法
function cityOnclick(cityId,cityName){
	cityHiddeField.setValue(cityId);
	cityField.setValue(cityName);
	cityField.menu.hide(true);
}
//居住地triggerField组件单击后的方法
function triggerFn(){
		var html_province="<div style='margin-top:5px;'>";
	//从后台获取省份信息格式是List<Address>
	DWREngine.setAsync(false);
	AddressDwr.getProvinces(function(data){
		//遍历省份
		Ext.each(data,function(v,i){
				html_province+="<a href='#' onclick='provinceOnclick("+v.id+");'>"+v.name+"</a>&nbsp;&nbsp;";
				if(i%13==0&&i!=0){
					html_province="<div style='margin-top:4px'>"+html_province+"</div>";
					html_province+='<br/>';
					}
		});
		html_province+="</div>";
	});
	DWREngine.setAsync(true);
	if(html_province==""){
		Ext.Msg.show({
			title:'错误',
			msg:'查询地址出错',
			icon:Ext.Msg.ERROR,
			buttons:Ext.Msg.OK
		});
		return;
	}
	//先判断menu是否已销毁
	if(selectMenu==undefined){
		//生成菜单,显示省份信息 
	selectMenu = new Ext.menu.Menu({
		autoHeight:true
});
	}
	if(this.menu==undefined)
		this.menu=selectMenu;
	var _scope = this;
	//先移除所有组件
	this.menu.removeAll();
	this.menu.add(new Ext.Panel({
		id:'provincePanel',
		border:false,
		width:580,
		autoHeight:true,
		buttonAlign:'right',
		buttons:[
		{
			text:'关闭',
			handler:function(){
				// 销毁所有子菜单
				_scope.menu.removeAll();
				_scope.menu.hide(true);
			}
		}
		],
		items:[
		new Ext.Panel({
			autoHeight:true,
			border:false,
			style:'margin-left:10px;margin-top:10px;margin-right:10px;margin-bottom:10px;font-size:13px;pading-top:15px;',
			html:html_province
		}),{
			border:false,
			html:'<hr style="color:red"/>'
		}
		]
	}));
		this.menu.show(this.el);
}
//注册提交用的方法
function registerSubmit(){
	if(!formPanel.getForm().isValid()){//客户端验证没通过
		showErrorDialog("请检查输入参数是否合法!");
		return;
	}
	if(regPassword.getValue()!=regRePassword.getValue()){
		showErrorDialog("前后密码不一致!");
		return;
	}
	var user = {
		"user.name":nameField.getValue(),
		"user.sex":sexField.getValue(),
		"user.sex_cn":sexField.getValue()==1?'女':'男',
		"user.birthday":birthdayField.getValue(),
		"address.province":provinceHiddeField.getValue(),
		"address.city":cityHiddeField.getValue(),
		"user.statusId":statusField.getValue(),
		"user.password":regPassword.getValue(),
		"user.email":regEmail.getValue(),
		"user.validateCode":Ext.getCmp("validationCodeid").getValue()
	}
	Ext.Ajax.request({
		url:'register.action',
		method:'post',
		params:user,
		success:function(response,opts){
			var data = Ext.util.JSON.decode(response.responseText);
			if(data.success=='true'){//后台执行成功！
		Ext.MessageBox.show({
	    title: '提示',
	    msg:data.msg,
	    minWidth:200,
	    buttons: Ext.MessageBox.YESNO,        
	    icon: Ext.MessageBox.WARNING,
	    fn:function(btn){
	    	if(btn=='yes'){//关闭注册窗口
	    	register.hide();
	    	}
	    }
	});   
			}else{
				showErrorDialog(data.msg);
			}
		},
		failure:function(response,opts){//如果后台返回的不是200，则错误
			var msg = response.responseText;
			showErrorDialog(msg);
		}
	});
}