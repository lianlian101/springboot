<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/static/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/static/js/jquery.qrcode.js"></script>
    <script type="text/javascript" src="/static/js/qrcode.js"></script>
    <script type="text/javascript">

        // 转utf-8
        function toUtf8(str) {
            var out, i, len, c;
            out = "";
            len = str.length;
            for (i = 0; i < len; i++) {
                c = str.charCodeAt(i);
                if ((c >= 0x0001) && (c <= 0x007F)) {
                    out += str.charAt(i);
                } else if (c > 0x07FF) {
                    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                    out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
                } else {
                    out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                    out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
                }
            }
            return out;
        }

        // 点击生成二维码
        function ol () {
            var str = "https://www.gatebim.com";
            str = toUtf8(str);
            $('#qrcode').qrcode({
                text: str, //设置二维码内容
                render: "canvas", //设置渲染方式: canvas/table, 默认canvas
                width: 256, //设置宽度,默认生成的二维码大小是 256×256
                height: 256, //设置高度
                typeNumber: -1, //计算模式
                background: "#ffffff", //背景颜色
                foreground: "#000000" //前景颜色
            });
        }

    </script>
</head>
<body>
    <button onclick="ol()">生成二维码</button>
    <div id="qrcode"></div>
</body>
</html>