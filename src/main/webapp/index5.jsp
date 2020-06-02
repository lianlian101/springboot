<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="static/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="static/js/base64.js"></script>
<script type="text/javascript">
	var data = "*([&!])123adb明文";
	var encodeData = $.base64.encode(data);
	console.log("密文：", encodeData);
	var decodeData = $.base64.decode(encodeData);
	console.log("明文：", decodeData);
</script>
<body>
    <h1>base64测试</h1>
</body>
</html>