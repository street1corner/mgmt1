(function(){
	'use strict';
	
	angular.module('app').controller('homeCtrl', ['$scope','$http','$state',function($scope,$http,$state){
		console.log("login success1111");
		layui.use('element', function(){
			var element = layui.element;
		});
		layui.use('layer',  function () {
			layer.msg('Welcome');
		});
		
		$scope.user = '';
		$scope.user = JSON.parse(localStorage.getItem("user"));
		//你的代码
		//退出登录
		$scope.loginOut = function(){
			layer.load(1);
			localStorage.removeItem("user");
			$http({
				method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
				url:"api/loginOut", 　　   //请求路径
			}).success(function(res){
				console.log(res);
				layer.closeAll('loading');
				if(res.code==200){
					layer.msg(res.msg,{icon: 1});
					layer.closeAll('page');
					$state.go("login");
				}else{
					layer.msg(res.msg,{icon: 2});
				}
			}).error(function(res){
				layer.closeAll('loading');
				alert("服务器开小差了");
			})
		}

	}])
	
	.controller('setCtrl', ['$scope','$http','$state',function($scope,$http,$state){
		console.log("set success");
		$scope.nowpass = '';
		$scope.pass = '';
		$scope.repass = '';
		$scope.user = '';
		$scope.user = JSON.parse(localStorage.getItem("user"));
		layui.use('element', function(){
			var element = layui.element;
		});
		
		layui.use('layer',  function () {
			layer.msg('Welcome');
		});
		
		layui.use('form', function(){
			  var form = layui.form;
			  
			  //监听提交
			  form.on('submit(changePass)', function(data){
			    console.log(JSON.stringify(data.field));
			    $scope.changePass();
			  });
			  //监听提交
			  form.on('submit(changeInfo)', function(data){
				  console.log(JSON.stringify(data.field));
				  layer.confirm('确定要修改吗？', {icon: 3, title:'提示'},function(index){
					  $scope.changeInfo(data);
					  layer.close(index);
				  })
			  });
		});
		

		//你的代码
		//修改密码
		$scope.changePass = function(){
			if($scope.pass != $scope.repass){
				layer.msg("两次密码不一致");
				return false;
			}
			layer.load(1);
			$http({
				method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
				url:"updatePass", 
				params:{
					'userAccount' : $scope.user.userAccount,
					'nowpass' : $scope.nowpass,
					'pass' : $scope.pass,
					'repass' : $scope.repass
				}
			}).success(function(res){
				console.log(res);
				layer.closeAll('loading');
				if(res.code==200){
					layer.msg(res.msg,{icon: 1});
					layer.closeAll('page');
				}else{
					layer.msg(res.msg,{icon: 2});
				}
			}).error(function(res){
				layer.closeAll('loading');
				alert("服务器开小差了");
			})
		}
		//修改信息
		$scope.changeInfo = function(data){
			layer.load(1);
			console.log(data.field.sex);
			$http({
				method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
				url:"updateUserInfo", 
				params:{
					'userAccount' : $scope.user.userAccount,
					'userNickname' : $scope.user.userNickname,
					'sex' : data.field.sex,
					'mobilePhone' : $scope.user.mobilePhone
				}
			}).success(function(res){
				console.log(res);
				layer.closeAll('loading');
				if(res.code==200){
					layer.msg(res.msg,{icon: 1});
					layer.closeAll('page');
				}else{
					layer.msg(res.msg,{icon: 2});
				}
			}).error(function(res){
				layer.closeAll('loading');
				alert("服务器开小差了");
			})
		}
	}])
	
	.controller('serverCtrl', ['$scope','$http','$state',function($scope,$http,$state){
		console.log("login success2222");
		$scope.server = '';
		layer.msg('server'); 
		layui.use('form',  function () {
			layui.form.render();
		});
		layui.use('table', function(){
			  var table = layui.table;
			  
			  //第一个实例
			  table.render({
			    elem: '#servertable'
			    ,id: 'servertable'
			   // ,height: 475
			    ,url: 'serverInfo' //数据接口
			    ,page: true //开启分页
			    ,cols: [[ //表头
			       {type:'numbers',fixed: 'left'}
//			      ,{type:'checkbox'}
			      ,{field: 'serverId', title: 'ID', width:80, sort: true}
			      ,{field: 'serverName', title: '服务器', width:120}
			      ,{field: 'ipAddress', title: 'IP地址', width:120}
			      ,{field: 'subnetMask', title: '掩码', width:120}
			      ,{field: 'gateway', title: '网关', width:120}
			      ,{field: 'serverAccount', title: '帐号', width:120}
			      ,{field: 'serverPassword', title: '密码', width:120}
			      ,{field: 'port', title: '端口', width:80}
			      ,{field: 'comments', title: '备注', width:120}
			      ,{field: 'creatTime', title: '创建时间', width:120}
			      ,{field: 'updateTime', title: '更新时间', width:120}
			      ,{align:'center', toolbar: '#barTpl', width: 180, title: '操作',fixed: 'right'}
			    ]],
			    response: {
			    	  countName: 'totalRecord' //数据总数的字段名称，默认：count
		    	} 
			  });
			  
				//工具条点击事件
				layui.table.on('tool(test)', function(obj){
					console.log("3333333333"+obj);
					var data = obj.data;
					var layEvent = obj.event;
			 
					if(layEvent === 'edit'){ //修改
						modifyEditModel(data);
					} else if(layEvent === 'del'){ //删除
						$scope.doDelete(obj);
					} else if(layEvent === 'reset'){ //重置密码
						doReSet(obj.data.userId);
					}
				});
		});
		
		//搜索
		$scope.doSearch = function (){
			console.log($scope.server);
			var key = $("#searchKey").val();
			var value = $scope.server.searchValue;
			if (value=='') {
				key = '';
			}
			layui.table.reload('servertable', {where: {searchKey: key,searchValue: value}});
		}
		
		//修改表单弹窗
		function modifyEditModel(data){
			layer.open({
				type: 1,
				title: "修改信息",
				area: '900px',
				offset: '120px',
				content: $("#addModel").html()
			});
			$scope.serverId = data.serverId;
			$("#editForm")[0].reset();
			$("#editForm").attr("method","POST");
			if(data!=null){
				$("#editForm input[name=serverName]").val(data.serverName);
				$("#editForm input[name=ipAddress]").val(data.ipAddress);
				$("#editForm input[name=subnetMask]").val(data.subnetMask);
				$("#editForm input[name=gateway]").val(data.gateway);
				$("#editForm input[name=serverAccount]").val(data.serverAccount);
				$("#editForm input[name=serverPassword]").val(data.serverPassword);
				$("#editForm input[name=port]").val(data.port);
				$("#editForm input[name=comments]").val(data.comments);
				$("#editForm").attr("method","PUT");
				$("#editForm").attr("method","PUT");
			}
			$("#btnCancel").click(function(){
				layer.closeAll('page');
			});
			
			//表单提交事件
			layui.form.on('submit(btnSubmit)', function(data) {
				data.field._method = $("#editForm").attr("method");
				data.field.serverId = $scope.serverId;
				layer.load(1,{time: 3*1000});
				$http({
					method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"updateServerInfo", 　　   //请求路径
					params:data.field
				}).success(function(res){
					console.log(res);
					layer.closeAll('loading');
					if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						layer.closeAll('page');
						layui.table.reload('servertable', {});
					}else{
						layer.msg(res.msg,{icon: 2});
					}
				}).error(function(res){
					alert("cuowu");
				})
				return false;
			});
			
		}
		//显示表单弹窗
		function showEditModel(data){
			layer.open({
				type: 1,
				title: data==null?"添加信息":"修改用户",
				area: '900px',
				offset: '120px',
				content: $("#addModel").html()
			});
			$("#editForm")[0].reset();
			$("#editForm").attr("method","POST");
			
			$("#btnCancel").click(function(){
				layer.closeAll('page');
			});
			
			//表单提交事件
			layui.form.on('submit(btnSubmit)', function(data) {
				data.field._method = $("#editForm").attr("method");
				layer.load(1,{time: 3*1000});
				$http({
			    	 method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
			    	 url:"insertServerInfo", 　　   //请求路径
			    	 params:data.field
			     }).success(function(res){
			    	 console.log(res);
					 layer.closeAll('loading');
					 if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						layer.closeAll('page');
						layui.table.reload('servertable', {});
					 }else{
						layer.msg(res.msg,{icon: 2});
				 	 }
			     }).error(function(res){
			    	 alert("cuowu");
			     })
				return false;
			});
			
		}
		
		//添加按钮点击事件
		$("#addBtn").click(function(){
			showEditModel(null);
		});
		
		//删除
		$scope.doDelete = function (obj){
			layer.confirm('真的要删除吗？', {icon: 3, title:'提示'},function(index){
				layer.close(index);
				layer.load(0,{time: 3*1000});//加载层,在回调层关掉它
				$http({
			    	 method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
			    	 url:"deleteServerInfo", 　　   //请求路径
			    	 params:{
			    		 'serverId':obj.data.serverId,
			    	 }  //传递参数，字符串map或对象，转化成？name=lisa形式跟在请求路径后面
			     }).success(function(res){
			    	 console.log(res);
					 layer.closeAll('loading');
					 if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						obj.del();
						layui.table.reload('servertable', {});
					 }else{
						layer.msg(res.msg,{icon: 2});
				 	 }
			     }).error(function(res){
			    	 alert("cuowu");
			     })
			});
		}
		
	}])
	
	//计算机系统运维
	.controller('computerCtrl', ['$scope','$http','$state',function($scope,$http,$state){
		console.log("comp");
		$scope.computer = '';
		$scope.installedSystem = [];
		$scope.brand = [];
		layui.use('layer',  function () {
			layer.msg('computer');
		});
		layui.use('form',  function () {
			layui.form.render();
		});
		layui.use('table', function(){
			var table = layui.table;
			
			//第一个实例
			table.render({
				elem: '#computertable'
				,id: 'computertable'
				//,height: 475
				,url: 'computerInfo' //数据接口
				,page: true //开启分页
				,cols: [[ //表头
						{type:'numbers',fixed: 'left'}
//						,{type:'checkbox'}
						,{field: 'computerId', title: 'PC编号', width:100, sort: true}
						,{field: 'brand', title: '品牌', width:80}
						,{field: 'type', title: '型号', width:80}
						,{field: 'configure', title: '配置', width:120}
						,{field: 'installedSystem', title: '安装系统', width:80}
						,{field: 'installedDate', title: '安装日期', width:110}
						,{field: 'guaranteeTime', title: '保修时间', width:120}
						,{field: 'mirroredVersion', title: '镜像版本', width:110}
						,{field: 'macAddress', title: 'mac地址', width:120}
						,{field: 'username', title: '使用者', width:80}
						,{field: 'installer', title: 'IT安装人', width:80}
						,{field: 'createTime', title: '创建时间', width:110}
						,{field: 'updateTime', title: '更新时间', width:110}
						,{field: 'lastmodifyPerson', title: '更新人', width:80}
						,{field: 'installedSoft', title: '安装软件', width:110}
						,{align:'center', toolbar: '#barTpl', width: 120, title: '操作',fixed: 'right'}
						]]
				,response: {
					countName: 'totalRecord' //数据总数的字段名称，默认：count
				} 
			});
			
			//工具条点击事件
			layui.table.on('tool(computer)', function(obj){
				console.log(obj);
				var data = obj.data;
				var layEvent = obj.event;
				
				if(layEvent === 'edit'){ //修改
					$scope.getSelectData();
					modifyEditModel(data);
				} else if(layEvent === 'del'){ //删除
					$scope.doDelete(obj);
				} 
			});
		});
		
		//添加按钮点击事件
		$("#addBtn").click(function(){
			$scope.getSelectData();
			showEditModel(null);
		});
		
		//获取下拉框数据
		$scope.getSelectData = function(){
			var data = { //数据
					installedSystem: ['win7(64)','win7(32)','win10','xp'],
					brand:['lenovo','dell','ausu','组装'],
					};
			console.log(data.installedSystem);
			$scope.installedSystem = data.installedSystem;
			$scope.brand = data.brand;
		}
		
		//搜索
		$scope.doSearch = function (){
			console.log($scope.computer);
			var key = $("#searchKey").val();
			var value = $scope.computer.searchValue;
			if (value=='') {
				key = '';
			}
			layui.table.reload('computertable', {where: {searchKey: key,searchValue: value}});
		}
		
		//修改表单弹窗
		function modifyEditModel(data){
			layer.open({
				type: 1,
				title: "修改信息",
				area: '900px',
				offset: '120px',
				content: $("#addModel").html(),
			});
			
			if(data.guaranteeTime == ''){
				$("#newold").hide();
			}
			
			
			layui.use('laydate', function(){
				  var laydate = layui.laydate;
				  
				  //执行一个laydate实例
				  laydate.render({
				    elem: '#installedDate' //指定元素
				  });
				  
				  laydate.render({ 
					  elem: '#guaranteeTime'
					  ,range: '_' //或 range: '~' 来自定义分割字符
					});
			});
			
			$scope.id = data.id;
			console.log($scope.computerId);
			$("#editForm")[0].reset();
			$("#editForm").attr("method","POST");
			if(data!=null){
				$("#editForm input[name=computerId]").val(data.computerId);
//				$("#editForm select[name=brand]").val(data.brand);
				$("#editForm input[name=type]").val(data.type);
				$("#editForm input[name=configure]").val(data.configure);
				$("#editForm input[name=macAddress]").val(data.macAddress);
				$("#editForm input[name=installer]").val(data.installer);
//				$("#editForm select[name=installedSystem]").val(data.installedSystem);
				$("#editForm input[name=installedDate]").val(data.installedDate);
				$("#editForm input[name=guaranteeTime]").val(data.guaranteeTime);
				$("#editForm input[name=mirroredVersion]").val(data.mirroredVersion);
				$("#editForm input[name=username]").val(data.username);
				$("#editForm textarea[name=installedSoft]").val(data.installedSoft);
				$("#editForm").attr("method","PUT");
			}
			$("#btnCancel").click(function(){
				layer.closeAll('page');
			});
			
			layui.form.on('radio(test)', function(data){
				if(data.value == 'new'){
					$("#newold").show();
				}else{
					$("#newold").hide();
				}
				  console.log(data.elem); //得到radio原始DOM对象
				  console.log(data.value); //被点击的radio的value值
			}); 
			
			//表单提交事件
			layui.form.on('submit(btnSubmit)', function(data) {
				
				data.field._method = $("#editForm").attr("method");
				data.field.id = $scope.id;
				layer.load(1,{time: 3*1000});
				$http({
					method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"updateComputerInfo", 　　   //请求路径
					params:data.field
				}).success(function(res){
					console.log(res);
					layer.closeAll('loading');
					if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						layer.closeAll('page');
						layui.table.reload('computertable', {});
					}else{
						layer.msg(res.msg,{icon: 2});
					}
				}).error(function(res){
					alert("cuowu");
				})
				return false;
			});
			
			layui.use('laytpl', function(){
				var laytpl = layui.laytpl;
				laytpl(selectOption.innerHTML).render($scope.installedSystem, function(html){
					$("#installedSystem").html(html);
					$("#installedSystem").val(data.installedSystem);
				});
				laytpl(selectOption.innerHTML).render($scope.brand, function(html){
					$("#brand").html(html);
					$("#brand").val(data.brand);
				});
			});
			
		}
		
		//显示添加表单弹窗
		function showEditModel(data){
			layer.open({
				type: 1,
				title: "添加信息",
				area: '900px',
				offset: '120px',
				content: $("#addModel").html()
			});
			
			$("#newold").hide();
			layui.use('laydate', function(){
				  var laydate = layui.laydate;
				  
				  //执行一个laydate实例
				  laydate.render({
				    elem: '#installedDate' //指定元素
				  });
				  
				  laydate.render({ 
					  elem: '#guaranteeTime'
					  ,range: '_' //或 range: '~' 来自定义分割字符
					});
			});
			$("#editForm")[0].reset();
			$("#editForm").attr("method","POST");
			
			$("#btnCancel").click(function(){
				layer.closeAll('page');
			});
			
			layui.form.on('radio(test)', function(data){
				if(data.value == 'new'){
					$("#newold").show();
				}else{
					$("#newold").hide();
				}
				  console.log(data.elem); //得到radio原始DOM对象
				  console.log(data.value); //被点击的radio的value值
			}); 
			//表单提交事件
			layui.form.on('submit(btnSubmit)', function(data) {
				data.field._method = $("#editForm").attr("method");
				layer.load(1,{time: 3*1000});
				$http({
					method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"insertComputerInfo", 　　   //请求路径
					params:data.field
				}).success(function(res){
					console.log(res);
					layer.closeAll('loading');
					if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						layer.closeAll('page');
						layui.table.reload('computertable', {});
					}else{
						layer.msg(res.msg,{icon: 2});
					}
				}).error(function(res){
					alert("cuowu");
				})
				return false;
			});
			

			layui.use('laytpl', function(){
				var laytpl = layui.laytpl;
				laytpl(selectOption.innerHTML).render($scope.installedSystem, function(html){
					$("#installedSystem").html(html);
				});
				laytpl(selectOption.innerHTML).render($scope.brand, function(html){
					$("#brand").html(html);
				});
			});
			
		}
		

		
		//删除
		$scope.doDelete = function (obj){
			layer.confirm('真的要删除吗？', {icon: 3, title:'提示'},function(index){
				layer.close(index);
				layer.load(0,{time: 3*1000});//加载层,在回调层关掉它
				$http({
					method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"deleteComputerInfo", 　　   //请求路径
					params:{
						'id':obj.data.id,
					}  //传递参数，字符串map或对象，转化成？name=lisa形式跟在请求路径后面
				}).success(function(res){
					console.log(res);
					layer.closeAll('loading');
					if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						obj.del();
						layui.table.reload('servertable', {});
					}else{
						layer.msg(res.msg,{icon: 2});
					}
				}).error(function(res){
					alert("cuowu");
				})
			});
		}
		
	}])

	//库存管理
	.controller('repertoryCtrl', ['$scope','$http','$state',function($scope,$http,$state){
		console.log("repertory");
		$scope.repertory = '';
		layui.use('layer',function(){
			layer.msg('repertory'); 
		});
		layui.use('form',  function () {
			layui.form.render();
		});
		layui.use('table', function(){
			var table = layui.table;
			
			//第一个实例
			table.render({
				elem: '#repertorytable'
				,id: 'repertorytable'
				,url: 'productInfo' //数据接口
				,page: true //开启分页
				,cols: [[ //表头
					{type:'numbers',fixed: 'left'}
	//				,{type:'checkbox'} //多选框
					,{title: '产品名称', width:200, templet: '#articleTopicTpl'}
					,{title:'是否可用', width:110, templet: '#switchTpl'}
					,{field: 'remainAmount', width:110, title: '剩余数量',sort: true}
					,{align:'center', toolbar: '#barTpl', width: 120, title: '操作'}
					]]
				,response: {
					countName: 'totalRecord' //数据总数的字段名称，默认：count
				} 
			});
			
			//工具条点击事件
			layui.table.on('tool(repertory)', function(obj){
				console.log(obj);
				var data = obj.data;
				var layEvent = obj.event;
				
				if(layEvent === 'edit'){ //修改
					modifyEditModel(data);
				} else if(layEvent === 'del'){ //删除
					$scope.doDelete(obj);
				} 
			});
			//监听开关操作
			layui.form.on('switch(sexDemo)', function(obj){
				$http({
					method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"updateProduct", 　　   //请求路径
					params:{
						"productId" : this.value,
						"isDelete" : obj.elem.checked ? 0:1,
					}
				}).success(function(res){
					console.log(res);
					layer.closeAll('loading');
					if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						layer.closeAll('page');
						layui.table.reload('repertorytable', {});
					}else{
						layer.msg(res.msg,{icon: 2});
					}
				}).error(function(res){
					alert("cuowu");
				})
			  //layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
			});
		});
		
		//添加按钮点击事件
		$("#addBtn").click(function(){
			showEditModel(null);
		});
		
		//搜索
		$scope.doSearch = function (){
			console.log($scope.repertory);
			var key = $("#searchKey").val();
			var value = $scope.repertory.searchValue;
			if (value=='') {
				key = '';
			}
			layui.table.reload('repertorytable', {where: {searchKey: key,searchValue: value}});
		}
		
		//修改表单弹窗
		function modifyEditModel(data){
			layer.open({
				type: 1,
				title: "修改产品",
				area: '443px',
				offset: '120px',
				content: $("#addModel").html(),
			});
			
			$scope.productId = data.productId;
			$("#editForm")[0].reset();
			$("#editForm").attr("method","POST");
			if(data!=null){
				$("#editForm input[name=productName]").val(data.productName);
				$("#editForm input[name=remainAmount]").val(data.remainAmount);
				$("#editForm").attr("method","PUT");
			}
			$("#btnCancel").click(function(){
				layer.closeAll('page');
			});
			
			//表单提交事件
			layui.form.on('submit(btnSubmit)', function(data) {
				data.field._method = $("#editForm").attr("method");
				data.field.productId = $scope.productId;
				layer.load(1,{time: 3*1000});
				$http({
					method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"updateProduct", 　　   //请求路径
					params:data.field
				}).success(function(res){
					console.log(res);
					layer.closeAll('loading');
					if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						layer.closeAll('page');
						layui.table.reload('repertorytable', {});
					}else{
						layer.msg(res.msg,{icon: 2});
					}
				}).error(function(res){
					alert("cuowu");
				})
				return false;
			});
		}
		
		//显示添加表单弹窗
		function showEditModel(data){
			layer.open({
				type: 1,
				title: "添加产品",
				area: '443px',
				offset: '120px',
				content: $("#addModel").html()
			});
			$("#editForm")[0].reset();
			$("#editForm").attr("method","POST");
			$("#btnCancel").click(function(){
				layer.closeAll('page');
			});
			
			//表单提交事件
			layui.form.on('submit(btnSubmit)', function(data) {
				data.field._method = $("#editForm").attr("method");
				layer.load(1,{time: 3*1000});
				$http({
					method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"insertProduct", 　　   //请求路径
					params:data.field
				}).success(function(res){
					console.log(res);
					layer.closeAll('loading');
					if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						layer.closeAll('page');
						layui.table.reload('repertorytable', {});
					}else{
						layer.msg(res.msg,{icon: 2});
					}
				}).error(function(res){
					alert("cuowu");
				})
				return false;
			});
		}
		//删除
		$scope.doDelete = function (obj){
			layer.confirm('请谨慎操作,删除会导致记录失效若不想使用建议更改*是否可用*',{icon:0, btn: ['不可阻挠','不删了'], title:'提示'},function(index){
				layer.close(index);
				layer.confirm('真的要删除吗？', {icon: 3, title:'提示'},function(index){
					layer.close(index);
					layer.load(0,{time: 3*1000});//加载层,在回调层关掉它
					$http({
						method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
						url:"deleteProduct", 　　   //请求路径
						params:{
							'productId':obj.data.productId,
						}  //传递参数，字符串map或对象，转化成？name=lisa形式跟在请求路径后面
					}).success(function(res){
						console.log(res);
						layer.closeAll('loading');
						if(res.code==200){
							layer.msg(res.msg,{icon: 1});
							obj.del();
							layui.table.reload('repertorytable', {});
						}else{
							layer.msg(res.msg,{icon: 2});
						}
					}).error(function(res){
						alert("cuowu");
					})
				});
			})
			

		}
		
	}])	
	
	//用户管理
	.controller('userCtrl',['$scope','$http','$state',function($scope,$http,$state){
		console.log("2222");
		$scope.user="huangliwei";
		layer.msg('hello'); 
		layui.use('element', function(){
			  var element = layui.element;
		});
		
		layui.use('table', function(){
			  var table = layui.table;
			  
			  //第一个实例
			  table.render({
			    elem: '#usertable'
			    ,height: 315
			    ,url: 'home/table.json' //数据接口
			    ,page: true //开启分页
			    ,cols: [[ //表头
			      {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
			      ,{field: 'username', title: '用户名', width:80}
			      ,{field: 'sex', title: '性别', width:80, sort: true}
			      ,{field: 'city', title: '城市', width:80} 
			      ,{field: 'sign', title: '签名', width: 177}
			      ,{field: 'experience', title: '积分', width: 80, sort: true}
			      ,{field: 'score', title: '评分', width: 80, sort: true}
			      ,{field: 'classify', title: '职业', width: 80}
			      ,{field: 'wealth', title: '财富', width: 135, sort: true}
			    ]]
			  });
			  
			});
	}])
	
	//维修间领用记录
	.controller('lyRecordCtrl', ['$scope','$http','$state',function($scope,$http,$state){
		console.log("lyRecord");
		$scope.lyRecord = '';
		$scope.productList = []; //产品列表
		$scope.amount = 0;
		
		layer.msg('lyRecord'); 
		layui.use('form',  function () {
			layui.form.render();
		});
		layui.use('table', function(){
			var table = layui.table;
			
			//第一个实例
			table.render({
				elem: '#lyRecordtable'
				,id: 'lyRecordtable'
				//,height: 475
				,url: 'lingyongRecordInfo' //数据接口
				,page: true //开启分页
				,cols: [[ //表头
						{type:'numbers',fixed: 'left'}
//						,{type:'checkbox'}
						,{field: 'productName', title: '产品名称'}
						,{field: 'user', title: '领用人'}
						,{field: 'amount', title: '领用数量'}
						,{field: 'lingyongTime', title: '领用时间', sort: true}
						,{field: 'department', title: '所在部门'}
						,{field: 'usePurpose', title: '用途'}
						,{title: '是否以旧换新', width:200, templet: '#articleTopicTpl'}
						,{field: 'remark', title: '备注'}
						,{align:'center', toolbar: '#barTpl', width: 120, title: '操作',fixed: 'right'}
						]]
				,response: {
					countName: 'totalRecord' //数据总数的字段名称，默认：count
				} 
			});
			
			//工具条点击事件
			layui.table.on('tool(lyRecord)', function(obj){
				console.log(obj);
				var data = obj.data;
				var layEvent = obj.event;
				
				if(layEvent === 'del'){ //删除
					$scope.doDelete(obj);
				} 
			});
		});
		
		//添加按钮点击事件
		$("#addBtn").click(function(){
			showEditModel(null);
		});
		
		//获取下拉框数据
		$scope.getSelectData = function(){
			$http({
				method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
				url:"productInfo", 　　   //请求路径
				params:{
					'searchKey': 'is_delete',
					'searchValue':'0',
				}  //传递参数，字符串map或对象，转化成？name=lisa形式跟在请求路径后面
			}).success(function(res){
				$scope.productList = res.data;
				console.log($scope.productList);
			}).error(function(res){
				alert("cuowu");
			})
		}
		$scope.getSelectData(); //进入后先加载数据
		//搜索
		$scope.doSearch = function (){
			console.log($scope.lyRecord);
			var key = $("#searchKey").val();
			var value = $scope.lyRecord.searchValue;
			if (value=='') {
				key = '';
			}
			layui.table.reload('lyRecordtable', {where: {searchKey: key,searchValue: value}});
		}
	
		//显示添加表单弹窗
		function showEditModel(data){
			var amount = 20;
			layer.open({
				type: 1,
				title: "新增记录",
				area: '900px',
				offset: '120px',
				content: $("#addModel").html()
			});
			
			layui.use('laydate', function(){
				  var laydate = layui.laydate;
				  
				  //执行一个laydate实例
				  laydate.render({
				    elem: '#lingyongTime' //指定元素
				  });
				  
			});
			$("#editForm")[0].reset();
			$("#editForm").attr("method","POST");
			
			$("#btnCancel").click(function(){
				layer.closeAll('page');
			});
			
			//表单提交事件
			layui.form.on('submit(btnSubmit)', function(data) {
				data.field._method = $("#editForm").attr("method");
				layer.load(1,{time: 3*1000});
				$http({
					method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"insertLingyong", 　　   //请求路径
					params:data.field
				}).success(function(res){
					console.log(res);
					layer.closeAll('loading');
					if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						layer.closeAll('page');
						layui.table.reload('lyRecordtable', {});
					}else{
						layer.msg(res.msg,{icon: 2});
					}
					$scope.getSelectData(); //提交成功后刷新缓存数据
				}).error(function(res){
					alert("cuowu");
				})
				return false;
			});
			

			layui.use('laytpl', function(){
				var laytpl = layui.laytpl;
				var ss = 20;
				console.log("dfsf"+$scope.productList);
				laytpl(selectOption.innerHTML).render($scope.productList, function(html){
					$("#productId").html(html);
				});
				layui.form.on('select(productIdfilter)', function(data){
					  console.log(data.value); //得到被选中的值
					  for(var i in $scope.productList){
						  if($scope.productList[i].productId == data.value){
							  $scope.amount = $scope.productList[i].remainAmount;
							  laytpl('剩余数量:{{d}}').render($scope.amount,function(html){
									$("#remainamount").html(html);
							  });
							  break;
						  }
					  }
				}); 
			});
			
			
		}
		

		
		//删除
		$scope.doDelete = function (obj){
			layer.confirm('真的要删除吗？', {icon: 3, title:'提示'},function(index){
				layer.close(index);
				layer.load(0,{time: 3*1000});//加载层,在回调层关掉它
				$http({
					method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"deleteLingyong", 　　   //请求路径
					params:{
						'id':obj.data.id,
						'productId':obj.data.productId,
						'amount':obj.data.amount,
					}  //传递参数，字符串map或对象，转化成？name=lisa形式跟在请求路径后面
				}).success(function(res){
					console.log(res);
					layer.closeAll('loading');
					if(res.code==200){
						layer.msg(res.msg,{icon: 1});
						obj.del();
						layui.table.reload('lyRecordtable', {});
					}else{
						layer.msg(res.msg,{icon: 2});
						layui.table.reload('lyRecordtable', {});
					}
				}).error(function(res){
					alert("cuowu");
				})
			});
		}
		
	}])
	
})();