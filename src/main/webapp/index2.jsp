<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<script type="text/javascript" src="/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function btn(){
		$.ajax({
			url: '/user/fun',
			type: 'post',
			data: {},
			//success: function(data){
		    success (data) {
				console.log("操作成功", data);
			},
			//error: function(message){
			error (){
				console.log("操作失败", message);
			}
		});
	}
</script>
</head>
<body>
	<button type="button" onclick="btn()">函数格式</button>
</body>
</html>