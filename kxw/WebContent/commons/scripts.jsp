<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="<%=path%>/extjs/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/extjs/desktop/css/desktop.css"/>
<script type="text/javascript" src="<%=path %>/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=path %>/extjs/ext-all-debug.js"></script>
<script type="text/javascript" src="<%=path %>/extjs/ext-lang-zh_CN.js"></script>
<script type="text/javascript"  src="<%=path %>/extjs/desktop/js/App.js"></script>
<script type="text/javascript"  src="<%=path %>/extjs/desktop/js/Desktop.js"></script>
<script type="text/javascript"  src="<%=path %>/extjs/desktop/js/Module.js"></script>
<script type="text/javascript"  src="<%=path %>/extjs/desktop/js/StartMenu.js"></script>
<script type="text/javascript"  src="<%=path %>/extjs/desktop/js/TaskBar.js"></script>
<script type="text/javascript" src="<%=path %>/js/common.js"></script>
<script type="text/javascript" src="<%=path %>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=path %>/dwr/util.js"></script>
<script type="text/javascript" >
var WEB_ROOT='<%=path%>';
function $importJs(_path){
	var str = "<script type='text/javascript' src='"+_path+"'></sc"+"ript>";
	document.write(str);
}
</script>