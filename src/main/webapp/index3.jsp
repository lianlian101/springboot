<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<script type="text/javascript" src="/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function fun(){
	    console.log(a);
	    a = 'aaa';
	    console.log(a);
	    var a = 'bbb';
	    console.log(a);
	}
	fun();
	console.log('=========================');
	function fun2(){
		//console.log(b);
		b = 'bbb';
		console.log(b);
	}
	fun2();
	console.log('========================');
	fun3 = function fun4(){
		console.log('fun4-1');
	}
	fun3 = function fun4(){
		console.log('fun4-2');
	}
	fun3();
</script>
</head>
<body>
    <h3>变量和函数提升</h3>
</body>
</html>