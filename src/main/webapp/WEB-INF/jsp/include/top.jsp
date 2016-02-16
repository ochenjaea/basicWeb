<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<script type="text/javascript">

$(document).ready(function()
		{
			
			var session = '${sessionScope.userid }';
		
			if(session == null || session == '')
			{
			//logout();
			}

			
			$("#logout").click(function(event) {
				window.location = "<spring:url value='/j_spring_security_logout'/>";
			});
		});
		
		var logout = function(){
			window.location = "<spring:url value='/j_spring_security_logout'/>";
		};

</script>
<div style="background-color: moccasin;">
<span>여기는 TOP</span>

</div>
