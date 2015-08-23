<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/commons/scripts.jsp" %>
<script type="text/javascript" src="<%=path%>/dwr/interface/AddressDwr.js"></script>
<script type="text/javascript" src="js/register.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<title>首页</title>
</head>
<body >
<script type="text/javascript">
	Ext.onReady(function(){
			Ext.QuickTips.init();
			<%
				String email = (String)request.getSession().getAttribute("email");
				if(email==null){
			%>
			login.show();
			<%}else{%>
				window.location.href="login.action";
			<%}%>
			var view = new Ext.Viewport({
				layout:'border',
				region:'center',
				items:[login]
			});
			
		});
</script>
</body>
</html>