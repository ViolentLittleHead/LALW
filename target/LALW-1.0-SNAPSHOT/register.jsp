<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>LALW | Register</title>

    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">

    <script>
        window.onload =function(){
            document.getElementById("form").onsubmit = function () {
                return verifyUsername()&&verifyPassword()&&verifyEmail()&&checkPassword()&&checkVcode();
            }
            document.getElementById("username").onblur = verifyUsername;
            document.getElementById("password").onblur = verifyPassword;
            document.getElementById("checkPassword").onblur = checkPassword;
            document.getElementById("email").onblur = verifyEmail;
            document.getElementById("vcode").onblur = checkVcode;
        }

        //校验用户名格式以及是否重复
        function verifyUsername() {
            var username = document.getElementById("username").value;
            if(username == ""){
                return false;
            }
            var reg_username = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,10}$/;
            var flag = reg_username.test(username);
            if(flag){
                $.ajax({
                    url:'${pageContext.request.contextPath}/user/verifyUsername',
                    type:'post',
                    data:{"username":$("#username").val()},
                    success: function (fk) {
                        if(fk=="0"){
                            alert('用户名已存在');
                            document.getElementById("username").focus();
                            return false;
                        }
                        if(fk=="1"){
                            alert('用户名可使用');
                            return true;
                        }
                    }
                });
            }else {
                alert('请以以字母开头、可带数字、“_”、“.”的5-10个字串 ');
                document.getElementById("username").focus();
                return false;
            }

        }
        //校验密码格式
        function verifyPassword() {
            var password = document.getElementById("password").value;
            if(password == ""){
                return false;
            }
            var reg_password = /^(\w){6,20}$/;
            var flag = reg_password.test(password);
            if(flag){
                return true;
            }else{
                alert('密码只能输入6-20个字母、数字、下划线 ');
                document.getElementById("password").focus();
                return false;
            }
        }
        //校验两次密码一样
        function checkPassword() {
            var password = document.getElementById("password").value;
            var checkPassword = document.getElementById("checkPassword").value;
            if(checkPassword==""){
                return false;
            }
            if(password == checkPassword){
                return true;
            }else {
                alert('两次密码不一致');
                return false;
            }
        }
        //校验邮箱格式
        function verifyEmail() {
            var email = document.getElementById("email").value;
            if(email==""){
                return false;
            }
            var reg_email = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            var flag = reg_email.test(email);
            if(flag){
                return true;
            }else {
                alert('邮箱格式错误');
            }
        }
        function checkVcode() {
            var vcode = document.getElementById("vcode").value;
            var checkVcode = "<%=session.getAttribute("vcode")%>";
            if(vcode.toLowerCase() == checkVcode.toLowerCase()){
                return true;
            }else {
                alert('验证码错误');
                return false;
            }
        }
    </script>

</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="all-admin-index.html"><b>LALW</b>现代学习系统</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">注册系统</p>

        <form action="${pageContext.request.contextPath}/user/register"
              method="post">
            <div class="form-group has-feedback">
                <input type="text" id="username" name="username" class="form-control"
                       placeholder="用户名"> <span
                    class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" id="password" name="password" class="form-control"
                       placeholder="密码"> <span
                    class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" id="checkPassword" name="checkPassword" class="form-control"
                       placeholder="确认密码"> <span
                    class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" name="email" id="email" class="form-control"
                       placeholder="邮箱"> <span
                    class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" name="vcode" class="form-control"
                       placeholder="验证码"> <span
                    class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <!-- /.col -->
                <div class="col-xs-4">
                    <input type="button" id="btnGetVcode" name="btnGetVcode" onclick="vcodebutton()" value="获取验证码" class="btn btn-primary btn-block btn-flat"></input>
                </div>
                <!-- /.col -->
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" id="form" name="form" class="btn btn-primary btn-block btn-flat">注册</button>
                </div>
                <!-- /.col -->
            </div>
        </form>



    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<!-- Bootstrap 3.3.6 -->
<!-- iCheck -->
<script
        src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function() {
        $('input').iCheck({
            checkboxClass : 'icheckbox_square-blue',
            radioClass : 'iradio_square-blue',
            increaseArea : '20%' // optional
        });
    });

    function vcodebutton() {
        $.ajax({
            url: '${pageContext.request.contextPath}/email/send',
            type: 'post',
            data:{"email": $("#email").val()}
        });

        alert('已发送');
        send(document.getElementById("btnGetVcode"),30);
    }

    /**
     * 控制ele的按钮在指定时间内禁止重复点击
     * @param ele 标签对象
     * @param time 禁用时间（s）
     */
    function send (ele, time) {
        console.log(time);
        if (time == 0) {
            ele.removeAttribute("disabled");
            return ele.value = '重新获取';
        }

        ele.setAttribute("disabled", true);
        ele.value = time + 's';
        setTimeout(function(){send(ele,--time)}, 1000);
    }


</script>
</body>

</html>