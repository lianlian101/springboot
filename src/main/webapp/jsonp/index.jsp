<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	$.ajax({
	    url: '/jsonp/index',
	    type: "get",
	    async: false,
	    data: {},
	    dataType: 'jsonp', //这里为jsonp类型
	    jsonp: 'callback', // get请求携带的参数名称，
	    jsonpCallback: 'ol', //自定义回调函数名称，可以不写Jquery有默认的函数名称
	    success: function(json){
	        console.log("success", json);
	    },
	    error : function() {
	        alert("异常！");
	    }
	});
	
	function ol(data){
		console.log(data);
	}
</script>
</head>
<body>

</body>
</html>