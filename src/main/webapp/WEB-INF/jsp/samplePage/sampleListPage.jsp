<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<div>samplePage</div>
<h1>${list}</h1>
<div>
<c:forEach items="${list}" var="list">
${list} <br/>
</c:forEach>
</div>

