<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
    function ol(){
    	$.ajax({
    		url: '/user/pw',
    		data: {"data": $("#f").val()},
    		type: 'post',
    		success: function(data){
    			console.log(data);
    			data = data.trim();
    			var f = data.substring(0, 4);
   				console.log(f);
   				console.log(f == "fail");
    		},
    		error: function(xhr){
    			console.log(xhr);
    		}
    	});
    }
</script>
</head>
<body>
    <input id="f" type="text" value="0">
    <button type="button" onclick="ol()">测试</button>
</body>
</html>