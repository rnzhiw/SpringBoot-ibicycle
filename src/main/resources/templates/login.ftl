<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户登录</title>
    <link rel="stylesheet" href="/static/css/bootstrap.3.3.7.css">
    <link rel="stylesheet" href="/static/font-awesome/css/font-awesome.min.css">
</head>
<body>

<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h2>智慧电瓶车系统用户登录</h2>
                <form action="/login" role="form" class="form-horizontal" method="POST" name="loginForm" id="loginForm">
                    <div class="form-group">
                        <label for="mobile">手机号：</label>
                        <input type="text" class="form-control" name="mobile" id="mobile" placeholder="请输入手机号">
                    </div>
                    <div class="form-group">
                        <label for="password">密码：</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
                    </div>
                    <div class="row">
                        <div class="col-md-5">
                            <button class="btn btn-primary btn-block" type="reset" onclick="reset()">重置</button>
                        </div>
                        <div class="col-md-5">
                            <button class="btn btn-primary btn-block" type="submit" onclick="login()">登录</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>
</div>

</body>
<#--<script>-->
    <#--function login(){-->
        <#--$("#loginForm").validate({-->
            <#--submitHandler:function(form){-->
                <#--doLogin();-->
            <#--}-->
        <#--});-->
    <#--}-->
    <#--function doLogin(){-->
        <#--g_showLoading();-->

        <#--var inputPass = $("#password").val();-->

        <#--$.ajax({-->
            <#--url: "/login",-->
            <#--type: "POST",-->
            <#--data:{-->
                <#--mobile:$("#mobile").val(),-->
                <#--password: inputPass-->
            <#--},-->
            <#--success:function(data){-->
                <#--layer.closeAll();-->
                <#--// console.log(data);-->
                <#--if(data.code == 0){-->
                    <#--layer.msg("登录成功");-->
                    <#--window.location.href="/index";-->
                <#--}else{-->
                    <#--layer.msg(data.msg);-->
                <#--}-->
            <#--},-->
            <#--error:function(){-->
                <#--layer.closeAll();-->
            <#--}-->
        <#--});-->
    <#--}-->
<#--</script>-->
<!-- jquery  -->
<script type="text/javascript" src="/static/lib/core/jquery.3.2.1.min.js"></script>

<!-- jquery-validator -->
<script type="text/javascript" src="/static/lib/plugin/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="/static/lib/plugin/jquery-validation/localization/messages_zh.min.js"></script>
<!-- layer -->
<script type="text/javascript" src="/static/lib/plugin/layer/layer.js"></script>
</html>