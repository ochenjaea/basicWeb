<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<tiles:insertAttribute name="header" />
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#USERID").focus();
			$("#submitButton").click(function(event) {
				formSubmit();
			});
		});
		
		// Enter Key Push
		function checkEnter(keyCode) {
			if(keyCode == 13) {
				formSubmit();
			}
		}
		// 로그인
		function formSubmit() {
			if($("#USERID").val() == "") {
				$("#USERID").focus();
				alert("사용자 ID를 입력해주세요");
				return;
			}

			if($("#PASSWORD").val() == "") {
				$("#PASSWORD").focus();
				alert("비밀번호를 입력해주세요");
				return;
			}
			
			$("#loginFrm").submit();
		}
	</script>
</head>
<body>
	<form name="loginFrm" action="<spring:url value='/j_spring_security_check'/>" method="post" id="loginFrm">
		<div>LOGIN</div>
		<ul>
			<li>
				<input id="USERID" name="j_username" type="text" onkeypress="checkEnter(event.keyCode);" onclick="javascript:$(this).attr('value','');"  value=""/>
			</li>
			<li>
				<input id="PASSWORD" name="j_password" type="password" onkeypress="checkEnter(event.keyCode);" onclick="javascript:$(this).attr('value','');" value=""/>
			</li>
		</ul>
		<input type="button" id="submitButton" value="login">
	</form>
</body>
</html>