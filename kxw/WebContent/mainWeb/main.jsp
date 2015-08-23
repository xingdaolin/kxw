<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/commons/scripts.jsp" %>
<script type="text/javascript" src="js/main_desktop.js"></script>
<%
	System.out.println(request.getAttribute("javascript"));
%>
<script type="text/javascript">
eval(unescape(<%=request.getAttribute("javascript")%>));
</script>
<title>主页面</title>
</head>
<body>

<div id="x-desktop">
 

</div>
<div id="ux-taskbar">
	<div id="ux-taskbar-start"></div>
	<div id="ux-taskbuttons-panel"></div>
	<div class="x-clear"></div>
</div>
</body>
</html>