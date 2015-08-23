//注册页面验证码刷新的方法
function refashRegValidateCode(){
	var img = document.getElementById('regImgl');
	img.src='validationCode?temp=' + new Date().getMilliseconds();
	return false;
}

//登录页面验证码刷新方法
function refashLoginValidateCode(){
	var img = Ext.getDom('img2');
	img.src='validationCode?temp=' + new Date().getMilliseconds();
	return false;
}
//显示错误信息
function showErrorDialog(msg)
{
	var flag=false;
	Ext.MessageBox.show({
	    title: '错误',
	    msg:msg,
	    minWidth:200,
	    buttons: Ext.MessageBox.OK,        
	    icon: Ext.MessageBox.ERROR,
	        fn:function(btn){
	    	if(btn=='ok')
	    	flag=true;
	    }
	});    
}
//显示提示信息
function showInfoDialog(msg)
{
	var flag=false;
	Ext.MessageBox.show({
	    title: '提示',
	    msg:msg,
	    minWidth:200,
	    buttons: Ext.MessageBox.OK,        
	    icon: Ext.MessageBox.INFO,
	    fn:function(btn){
	    	if(btn=='ok')
	    	flag=true;
	    }
	});   
	return flag;
}