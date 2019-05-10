<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function btn(){
		$.ajax({
			url: '/user/test',
			type: 'post',
			data: JSON.stringify({"name":"zhangsan","age":"12"}),
			contentType: 'application/json;charset=utf-8',
			success: function(data){
				console.log(data);
			},
			error: function(message){
				console.log(message);
			}
		});
	}
	function btn2(){
        $.ajax({
            url: '/user/test',
            type: 'post',
            data: JSON.stringify({"name":"","age":"12"}),
            contentType: 'application/json;charset=utf-8',
            success: function(data){
                console.log(data);
            },
            error: function(message){
                console.log(message);
            }
        });
    }
	function btn3(){
        $.ajax({
            url: '/user/test',
            type: 'post',
            //data: JSON.stringify({"name":"","age":"12"}),
            contentType: 'application/json;charset=utf-8',
            success: function(data){
                console.log(data);
            },
            error: function(message){
                console.log(message);
            }
        });
    }
	function btn4(){
        $.ajax({
            url: '/user/test',
            type: 'post',
            data: {"name":"张三","age":"12"},
            contentType: 'application/json;charset=utf-8',
            success: function(data){
                console.log(data);
            },
            error: function(message){
                console.log(message);
            }
        });
    }
	
	function btn_1(){
        window.location.href="/user/test?name=zhangsan,lisi&age=12"
    }
	function btn_2(){
		$.ajax({
            url: '/user/test',
            type: 'post',
            data: {"name":"张三","age":""},
            success: function(data){
                console.log(data);
            },
            error: function(message){
                console.log(message);
            }
        });
				
    }
	function btn_3(){
        $.ajax({
            url: '/user/test',
            type: 'post',
            data: JSON.stringify({"name":"张三","age":"12"}),
            success: function(data){
                console.log(data);
            },
            error: function(message){
                console.log(message);
            }
        });
    }
</script>
</head>
<body>
	<button type="button" onclick="btn()">json 正确格式</button>
	<button type="button" onclick="btn2()">json 正确格式，有空值</button>
	<button type="button" onclick="btn3()">json 正确格式，空值</button>
	<button type="button" onclick="btn4()">json 错误格式</button>
	
	<button type="button" onclick="btn_1()">key-value 正确格式</button>
	<button type="button" onclick="btn_2()">key-value 正确格式，有空值</button>
	<button type="button" onclick="btn_3()">key-value 错误格式</button>
</body>
</html>