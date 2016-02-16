<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<tiles:insertAttribute name="header" />
	<script type="text/javascript">
		$(document).ready(function() {
			
			window.location = "<spring:url value='/j_spring_security_logout'/>";
		});
		
		
	</script>
</head>

</html>
