<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="../assets/layui/css/layui.css">
	<link rel="stylesheet" href="../static/css/login.css">
	<script src="../assets/layui/layui.js"></script>
	<script src="../assets/jquery-3.2.1.min.js"></script>
	<style>
		.login-main{
/* 			float:left; */
/* 			background: url(../static/img/login_banner.jpg) no-repeat; */
/* 			background-size: auto; */
		}
		.left{
/* 			float:left; */
/* 			width:50%;  */
		}
		.right{
/*  			float:left;  */
/*  			width:50%;  */
		}
		.layadmin-user-login-header p{
			color:black;
		}
	</style>
</head>
<body>
<div class="layadmin-user-login-main">
	<div class="layui-tab">
	  <ul class="layui-tab-title">
	    <li class="layui-this">登录</li>
	    <li>注册</li>
	  </ul>
	  <div class="layui-tab-content">
	    <div class="layui-tab-item layui-show">
			<div class="left" id="login">
			    <form class="layui-form layadmin-user-login-body" action="postLoginInfo" method="post" style="padding:20px;">
					<div class="layui-form-item">
							<label class="layadmin-user-login-icon layui-icon layui-icon-user" for="LAY-user-login-username"></label>
			      			<input class="layui-input" id="LAY-user-login-username" type="text" name="userinfo" required placeholder="用户信息" lay-verType="tips" lay-verify="required" autocomplete="off">
					</div>
					<div class="layui-form-item">
							<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
			      			<input class="layui-input" id="LAY-user-login-password" type="password" name="pwd" required placeholder="密码" lay-verType="tips" lay-verify="required" autocomplete="off">
					</div>
					
					<div class="layui-form-item">
						<div class="layui-row">
							<div class="layui-col-xs7">
								<label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
								<input type="text" name="vcode" id="LAY-user-login-vercode" lay-verType="tips" lay-verify="required" placeholder="验证码" class="layui-input">
							</div>
							<div class="layui-col-xs5">
								<div style="margin-left: 10px;">
									<img src="<%=request.getContextPath()%>/getimg" class="layadmin-user-login-codeimg" alt="验证码" />
								</div>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<button id="bt-login" class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 录</button>
					</div>
			    </form>
			</div>	    
	    </div>
	    <div class="layui-tab-item">
			<div class="right" id="register">
			    <form class="layui-form layadmin-user-login-body" action="postLoginInfo" method="post" style="padding: 20px;">
					<div class="layui-form-item">
							<label class="layadmin-user-login-icon layui-icon layui-icon-user" for="LAY-user-register-username"></label>
			      			<input class="layui-input" id="LAY-user-register-username" type="text" name="username" placeholder="昵称" lay-verType="tips" lay-verify="required" autocomplete="off">
					</div>
					<div class="layui-form-item">
							<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-register-password"></label>
			      			<input class="layui-input" id="LAY-user-register-password" type="password" name="pwd" required placeholder="密码" lay-verType="tips" lay-verify="required" autocomplete="off">
					</div>
					<div class="layui-form-item">
							<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-register-password1"></label>
			      			<input class="layui-input" id="LAY-user-register-password1" type="password" name="pwd" required placeholder="确认密码" lay-verType="tips" lay-verify="required" autocomplete="off">
					</div>
					<div class="layui-form-item">
							<label class="layadmin-user-login-icon layui-icon layui-icon-cellphone" for="LAY-user-register-phonenumber"></label>
			      			<input class="layui-input" id="LAY-user-register-phonenumber" type="text" name="phonenumber" required placeholder="手机号" lay-verType="tips" lay-verify="required|phone" autocomplete="off">
					</div>
					
					<div class="layui-form-item">
						<div class="layui-row">
							<div class="layui-col-xs7">
								<label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-register-vercode"></label>
								<input type="text" name="vcode" id="LAY-user-register-vercode" lay-verType="tips" lay-verify="required" placeholder="验证码" class="layui-input">
							</div>
							<div class="layui-col-xs5">
								<div style="margin-left: 10px;">
									<button class="layui-btn layui-btn-normal" style="width: 100%;">获取验证码</button>
								</div>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<button id="bt-register" class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">注册</button>
					</div>
			    </form>   
			</div>
		</div>
	  </div>
	</div>	
	
</div>
<script>
//一般直接写在一个js文件中

layui.use(['layer','jquery','form','element'], function(){
	var layer= layui.layer
  ,form = layui.form
  ,$ = layui.$;
	 var element = layui.element;
	var phoneinfo=$('#btn1');

//$('#register').hide();
	phoneinfo.click(function(){
		var number=$('#phonenumber').val();
		alert(number);
		var msgAjax = $.getJSON('http://localhost:8080/blog2/msgajax', {
		    phone: number,
		}).done(function (data) {
			
		    	alert(data.reason);
		   
		});
	}
	);
	
});

</script>

</body>
</html>