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
    		url: "/common/retmsg",
    		type: "get",
    		success: function(data){
    			console.log("console");
    			console.log(data);
    		},
    		error: function(a,xhr,b){
    			console.log(a);
    			console.log(xhr);
    			console.log(b);
    			if(xhr.status == 200){
    			    alert(xhr.responseText);    				
    			}
    		}
    	})
    }
    
    function ol2(){
        $.ajax({
            url: "/common/get",
            data: {"name": "测试"},
            type: "get",
            success: function(data){
                console.log("console");
                console.log(data);
            },
            error: function(xhr){
                console.log(xhr);
            }
        })
    }
</script>
</head>
<body>
    <button type="button" onclick="ol()">点击</button>
    
    <button type="button" onclick="ol2()">点击</button>
</body>
</html>