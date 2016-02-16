<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<tiles:insertAttribute name="header" />
</head>
<body>

	<div id="wrap">

		<div id="top">
			<tiles:insertAttribute name="top" />
		</div>

		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>

		<div class="content_box">

			<div id="navi">
				<tiles:insertAttribute name="leftmenu" />
			</div>

			<!-- //navi -->
			<div class="body">
				<div id="depthmenu">
					<tiles:insertAttribute name="depthmenu" />
				</div>
				<tiles:insertAttribute name="body" />
			</div>

			<!-- //content -->
		</div>
		<!-- //content_box -->
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
</body>
</html>