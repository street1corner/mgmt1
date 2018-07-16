(function(){
	'use strict';
	
	angular.module('app').controller('loginCtrl', ['$scope','$http','$state',function($scope,$http,$state){
		console.log("login ctrl");
		//checkLogin();
		//获取验证码
		var codeKey = null;
		getCode();  
		//获取验证码
		function getCode(){
			if(codeKey==null){
				codeKey = guid();
			}
			$("#LAY-user-get-vercode").attr("src","image/captcha?codeKey="+codeKey+"&n="+Math.random());
		}
		
		$("#LAY-user-get-vercode").click(function(){
			getCode();
		});
		layui.use(['form'], function() {
			var form = layui.form;
			//提交
			form.on('submit(LAY-user-login-submit)', function(obj) {
				obj.field.verkey = codeKey;
				layer.load(1,{time: 3*1000});
				 $http({
			    	 method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
			    	 url:"../api/login", 　　   //请求路径
			    	 params:obj.field
			     }).success(function(res){
			    	 console.log(res);
			    	 if(res.code == 200){
			    		 //登录成功
			    		 layer.msg(res.msg,{icon: 1});
			    		 localStorage.setItem("user", JSON.stringify(res.data));
			    		 setTimeout(function() {
			    			 $state.go('home');
						 }, 2000);
			    	 }else{
			    		 layer.closeAll('loading');
			    		 layer.msg(res.msg,{icon: 2});
			    	 }
			    	 
			     }).error(function(res){
			    	 alert("服务器开小差了");
			     })	
				
//				$.post("api/login", obj.field, function(data) {
//					if (data.code == 200) {
//						layer.msg(data.msg,{icon: 1});
//						localStorage.setItem("token", data.token);
//						localStorage.setItem("user", JSON.stringify(data.user));
//						setTimeout(function() {
//							location.replace("index.html");
//						}, 2000);
//					} else {
//						layer.closeAll('loading');
//						layer.msg(data.msg,{icon: 2});
//					}
//				}, "json");
			});
		});
		
		$("body").keydown(function() {
			//console.log(event.which);
		    if (event.keyCode == "13") {//keyCode=13是回车键
		        $('#bt-login').click();
		    }
		});  

		//检查是否登录
		function checkLogin(){
			var tempUser = JSON.parse(localStorage.getItem("user"));
			if (tempUser != null) {
	    		 setTimeout(function() {
	    			 $state.go('home');
				 }, 500);
			}
		}

		//生成uuid
		function guid() {
		    function S4() {
		       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
		    }
		    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
		}
//		$http({
//	    	 method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
//	    	 url:"/Myhome/QueryAttendance", 　　   //请求路径
//	    	 params:{
//	    		 'id':$stateParams.id,
//   		 	 'queryTime':$stateParams.queryTime
//	    	 }  //传递参数，字符串map或对象，转化成？name=lisa形式跟在请求路径后面
//	     }).success(function(res){
//	    	 console.log(res);
//	    	 $scope.dates = res.dates;
//	    	 $scope.user = res.users;
//	    	 
//	     }).error(function(res){
//	    	 alert("cuowu");
//	     })
	}])
})();