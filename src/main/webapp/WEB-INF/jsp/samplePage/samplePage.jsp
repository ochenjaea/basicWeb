<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring4 MVC -HelloWorld</title>
</head>
<script>

	var testFnc = function(){
		
		//alert("ajaxTest");
		
		var param = "test=hi";
		
		$.ajax({
			type:"POST",
			url : "/WebSample/ajax/ajaxTest.do",
			data: param,
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			dataType : "json",
			success : function(data) {
				console.log(data);
				$("#jsonResult").append(data.hi);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};
	
	
	var ajaxTest = function(){
		var data ={};
		data["test"] = "test";
		
		var serviceUrl = "/common/selectAdvaceFieldList.do";
		
		doAction(data, serviceUrl, function(result) {
			console.log(data);
		});
	};


</script>
<body>
<h1>Hello : ${name}</h1>
<button id="testBtn" onclick="testFnc()">ajax test</button>
<button id="testBtn" onclick="ajaxTest()">ajax test</button>
<div id="jsonResult"></div>
</body>
</html>