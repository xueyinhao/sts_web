<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h2>msg=${message}</h2>
		<h2>id=${id}</h2>
		
		
		<form action="/sts_web/test/doTest" method="post">
			<input type="text" name="name" value="nnnnnn">
			<input type="text" name="age" value="222222">
			<input type="text" name="name2" value="nnn2222">
			<input type="submit" value="提交">
		</form>
	</body>
</html>
