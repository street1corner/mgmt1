(function(){
	 'use strict';
	 //设备信息
	 angular.module("app").controller('eqpCtrl', ['$scope','$http','$state',function($scope,$http,$state){
			$scope.eqp = '';
			layui.use('layer',  function () {
				layer.msg('eqp');
			});
			layui.use('form',  function () {
				layui.form.render();
			});
			layui.use('table', function(){
				var table = layui.table;
				
				//第一个实例
				table.render({
					elem: '#eqptable'
					,id: 'eqptable'
					,url: 'eqp/eqpStandingbookInfo' //数据接口
					,page: true //开启分页
					,cols: [[ //表头
							{type:'numbers',fixed: 'left'}
//							,{type:'checkbox'}
							,{field: 'eqpName', title: '设备名称', width:190, sort: true}
							,{field: 'eqpNumber', title: '设备编号', width:100}
							,{field: 'eqpModel', title: '型号', width:130}
							,{field: 'eqpManufactor', title: '厂家', width:110}
							,{field: 'useDate', title: '使用日期', width:110}
							,{field: 'installPlace', title: '安装地点', width:90}
							,{align:'center', title: '设备状态', width:90, templet: '#articleTopicTpl'}
							,{field: 'dept', title: '部门', width:110}
							,{field: 'deptSub', title: '所属部门', width:100}
							,{field: 'remark', title: '备注', width:80}
							,{field: 'archivesNumber', title: '档案编号', width:80}
							,{align:'center', toolbar: '#barTpl', width: 120, title: '操作',fixed: 'right'}
							]]
					,response: {
						countName: 'totalRecord' //数据总数的字段名称，默认：count
					} 
				});
				
				//工具条点击事件
				layui.table.on('tool(eqp)', function(obj){
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
						eqpState:[{code:'1',state:'完好'},{code:'2',state:'停用'},{code:'3',state:'未验收'},{code:'4',state:'试生产'}]
						};
				console.log(data.installedSystem);
				$scope.eqpState = data.eqpState;
				$scope.brand = data.brand;
			}
			
			//搜索
			$scope.doSearch = function (){
				console.log($scope.eqp);
				var key = $("#searchKey").val();
				var value = $scope.eqp.searchValue;
				console.log(value);
				if (value=='') {
					key = '';
				}
				layui.table.reload('eqptable', {where: {searchKey: key,searchValue: value}});
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
				
				layui.use('laydate', function(){
					  var laydate = layui.laydate;
					  
					  //执行一个laydate实例
					  laydate.render({
					    elem: '#useDate' //指定元素
					  });
				});
				
				$scope.id = data.id;
				$("#editForm")[0].reset();
				$("#editForm").attr("method","POST");
				if(data!=null){
					$("#editForm input[name=eqpName]").val(data.eqpName);
//					$("#editForm select[name=brand]").val(data.brand);
					$("#editForm input[name=eqpNumber]").val(data.eqpNumber);
					$("#editForm input[name=eqpModel]").val(data.eqpModel);
					$("#editForm input[name=eqpManufactor]").val(data.eqpManufactor);
					$("#editForm input[name=useDate]").val(data.useDate);
//					$("#editForm select[name=installedSystem]").val(data.installedSystem);
					$("#editForm input[name=installPlace]").val(data.installPlace);
					$("#editForm input[name=eqpState]").val(data.eqpState);
					$("#editForm input[name=dept]").val(data.dept);
					$("#editForm input[name=deptSub]").val(data.deptSub);
					$("#editForm input[name=archivesNumber]").val(data.archivesNumber);
					$("#editForm textarea[name=remark]").val(data.remark);
					$("#editForm").attr("method","POST");
				}
				$("#btnCancel").click(function(){
					layer.closeAll('page');
				});
				
				
				//表单提交事件
				layui.form.on('submit(btnSubmit)', function(data) {
					
					data.field._method = $("#editForm").attr("method");
					data.field.id = $scope.id;
					layer.load(1,{time: 3*1000});
					$http({
						method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
						url:"eqp/cuEQPStandingbook", 　　   //请求路径
						params:data.field
					}).success(function(res){
						console.log(res);
						layer.closeAll('loading');
						if(res.code==200){
							layer.msg(res.msg,{icon: 1});
							layer.closeAll('page');
							layui.table.reload('eqptable', {});
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
					laytpl(selectOption.innerHTML).render($scope.eqpState, function(html){
						$("#eqpState").html(html);
						$("#eqpState").val(data.eqpState);
					});
				});
				
			}
			
			//显示添加表单弹窗
			function showEditModel(data){
				layer.open({
					type: 1,
					title: "添加设备信息",
					area: '900px',
					offset: '120px',
					content: $("#addModel").html()
				});
				layui.use('laydate', function(){
					  var laydate = layui.laydate;
					  
					  //执行一个laydate实例
					  laydate.render({
					    elem: '#useDate' //指定元素
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
						url:"eqp/cuEQPStandingbook", 　　   //请求路径
						params:data.field
					}).success(function(res){
						console.log(res);
						layer.closeAll('loading');
						if(res.code==200){
							layer.msg(res.msg,{icon: 1});
							layer.closeAll('page');
							layui.table.reload('eqptable', {});
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
					laytpl(selectOption.innerHTML).render($scope.eqpState, function(html){
						$("#eqpState").html(html);
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
						url:"eqp/deleteEQPStandingbook", 　　   //请求路径
						params:{
							'id':obj.data.id,
						}  //传递参数，字符串map或对象，转化成？name=lisa形式跟在请求路径后面
					}).success(function(res){
						console.log(res);
						layer.closeAll('loading');
						if(res.code==200){
							layer.msg(res.msg,{icon: 1});
							obj.del();
							layui.table.reload('eqptable', {});
						}else{
							layer.msg(res.msg,{icon: 2});
						}
					}).error(function(res){
						alert("cuowu");
					})
				});
			}
	 }])
	 //设备维护记录
	 .controller('eqpMaintainCtrl', ['$scope','$http','$state',function($scope,$http,$state){
			$scope.eqpMaintain = '';
			layui.use('layer',  function () {
				layer.msg('eqpMaintain');
			});
			layui.use('form',  function () {
				layui.form.render();
			});
			layui.use('table', function(){
				var table = layui.table;
				
				//第一个实例
				table.render({
					elem: '#eqpMaintaintable'
					,id: 'eqpMaintaintable'
					,url: 'eqp/eqpMaintainRecordInfo' //数据接口
					,page: true //开启分页
					,cols: [[ //表头
							{type:'numbers',fixed: 'left'}
//							,{type:'checkbox'}
							,{field: 'eqpName', title: '设备名称', width:190, sort: true}
							,{field: 'eqpNumber', title: '设备编号', width:100}
							,{field: 'breakdownTime', title: '故障时间', width:120}
							,{field: 'breakdownDesc', title: '故障描述', width:120}
							,{field: 'solutionTime', title: '解决时间', width:120}
							,{align:'center', title: '状态', width:100, templet: '#switchTpl'}
							,{field: 'maintainPerson', title: '维护人员', width:110}
							,{align:'center', title: '有无镜像备份', width:120, templet: '#haveMirrorswitchTpl'}
							,{field: 'remark', title: '备注', width:80}
							,{field: 'relativeData', title: '交付资料', width:80}
							,{field: 'lastmodifyPerson', title: '更新人', width:80}
							,{align:'center', toolbar: '#barTpl', width: 120, title: '操作',fixed: 'right'}
							]]
					,response: {
						countName: 'totalRecord' //数据总数的字段名称，默认：count
					} 
				});
				
				//工具条点击事件
				layui.table.on('tool(eqpMaintain)', function(obj){
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
				
				//监听状态开关操作
				layui.form.on('switch(statusDemo)', function(obj){
					$http({
						method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
						url:"eqp/cuEQPMaintainRecord", 　　   //请求路径
						params:{
							"id" : this.value,
							"status" : obj.elem.checked ? 1:2,
						}
					}).success(function(res){
						console.log(res);
						layer.closeAll('loading');
						if(res.code==200){
							layer.msg(res.msg,{icon: 1});
							layer.closeAll('page');
							layui.table.reload('eqpMaintaintable', {});
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
				$scope.getSelectData();
				showEditModel(null);
			});
			
			//获取下拉框数据
			$scope.getSelectData = function(){
				var data = { //数据
						status:[{code:'1',state:'解决'},{code:'2',state:'未解决'}]
						,haveMirror:[{code:'1',haveMirror:'有'},{code:'2',haveMirror:'无'}]
						};
				console.log(data.installedSystem);
				$scope.status = data.status;
				$scope.haveMirror = data.haveMirror;
				
				$http({
					method:"get",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
					url:"eqp/eqpStandingbookInfo", 　　   //请求路径
					//传递参数，字符串map或对象，转化成？name=lisa形式跟在请求路径后面
				}).success(function(res){
					$scope.eqpMaintainRecordList = res.data;
					console.log($scope.eqpMaintainRecordList);
				}).error(function(res){
					alert("cuowu");
				})
			}
			$scope.getSelectData(); //进入后先加载数据
			//搜索
			$scope.doSearch = function (){
				console.log($scope.eqpMaintain);
				var key = $("#searchKey").val();
				var value = $scope.eqpMaintain.searchValue;
				console.log(value);
				if (value=='') {
					key = '';
				}
				layui.table.reload('eqpMaintaintable', {where: {searchKey: key,searchValue: value}});
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
				
				layui.use('laydate', function(){
					  var laydate = layui.laydate;
					  
					//执行一个laydate实例
					  laydate.render({
					    elem: '#breakdownTime' //指定元素
					    ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
					    ,type: 'datetime'
					  });
					  laydate.render({
						elem: '#solutionTime' //指定元素
						,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
						,type: 'datetime'
					  });
				});
				
				$scope.id = data.id;
				$("#editForm")[0].reset();
				$("#editForm").attr("method","POST");
				if(data!=null){
//					$("#editForm select[name=eqpName]").val(data.eqpName);
//					$("#editForm select[name=brand]").val(data.brand);
					$("#editForm input[name=eqpNumber]").val(data.eqpNumber);
					$("#editForm input[name=breakdownTime]").val(data.breakdownTime);
					$("#editForm input[name=breakdownDesc]").val(data.breakdownDesc);
					$("#editForm input[name=solutionTime]").val(data.solutionTime);
//					$("#editForm select[name=installedSystem]").val(data.installedSystem);
					$("#editForm input[name=maintainPerson]").val(data.maintainPerson);
					$("#editForm textarea[name=relativeData]").val(data.relativeData);
					$("#editForm textarea[name=remark]").val(data.remark);
					$("#editForm").attr("method","POST");
				}
				$("#btnCancel").click(function(){
					layer.closeAll('page');
				});
				
				
				//表单提交事件
				layui.form.on('submit(btnSubmit)', function(data) {
					
					data.field._method = $("#editForm").attr("method");
					data.field.id = $scope.id;
					layer.load(1,{time: 3*1000});
					$http({
						method:"post",        　　// 可以是get,post,put, delete,head,jsonp;常使用的是get,post
						url:"eqp/cuEQPMaintainRecord", 　　   //请求路径
						params:data.field
					}).success(function(res){
						console.log(res);
						layer.closeAll('loading');
						if(res.code==200){
							layer.msg(res.msg,{icon: 1});
							layer.closeAll('page');
							layui.table.reload('eqpMaintaintable', {});
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
					laytpl(selectOptionStatus.innerHTML).render($scope.status, function(html){
						$("#status").html(html);
						$("#status").val(data.status);
					});
					laytpl(selectOption.innerHTML).render($scope.haveMirror, function(html){
						$("#haveMirror").html(html);
						$("#haveMirror").val(data.haveMirror);
					});
					
					laytpl(selectOptionEQPName.innerHTML).render($scope.eqpMaintainRecordList, function(html){
						$("#eqpName").html(html);
						$("#eqpName").val(data.eqpName);
					});
					
					
					layui.form.on('select(eqpNamefilter)', function(data){
						  console.log(data.value); //得到被选中的值
						  for(var i in $scope.eqpMaintainRecordList){
							  if($scope.eqpMaintainRecordList[i].eqpName == data.value){
								  $scope.eqpNumber = $scope.eqpMaintainRecordList[i].eqpNumber;
								  $("#editForm input[name=eqpNumber]").val($scope.eqpNumber);
								  break;
							  }
						  }
					}); 
				});
				
			}
			
			//显示添加表单弹窗
			function showEditModel(data){
				layer.open({
					type: 1,
					title: "添加维护设备记录",
					area: '900px',
					offset: '120px',
					content: $("#addModel").html()
				});
				layui.use('laydate', function(){
					  var laydate = layui.laydate;
					  
					  //执行一个laydate实例
					  laydate.render({
					    elem: '#breakdownTime' //指定元素
					    ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
					    ,type: 'datetime'
					  });
					  laydate.render({
						elem: '#solutionTime' //指定元素
						,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
						,type: 'datetime'
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
						url:"eqp/cuEQPMaintainRecord", 　　   //请求路径
						params:data.field
					}).success(function(res){
						console.log(res);
						layer.closeAll('loading');
						if(res.code==200){
							layer.msg(res.msg,{icon: 1});
							layer.closeAll('page');
							layui.table.reload('eqpMaintaintable', {});
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
					laytpl(selectOptionStatus.innerHTML).render($scope.status, function(html){
						$("#status").html(html);
					});
					laytpl(selectOption.innerHTML).render($scope.haveMirror, function(html){
						$("#haveMirror").html(html);
					});
					
					laytpl(selectOptionEQPName.innerHTML).render($scope.eqpMaintainRecordList, function(html){
						$("#eqpName").html(html);
					});
					
					
					layui.form.on('select(eqpNamefilter)', function(data){
						  console.log(data.value); //得到被选中的值
						  for(var i in $scope.eqpMaintainRecordList){
							  if($scope.eqpMaintainRecordList[i].eqpName == data.value){
								  $scope.eqpNumber = $scope.eqpMaintainRecordList[i].eqpNumber;
								  $("#editForm input[name=eqpNumber]").val($scope.eqpNumber);
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
						url:"eqp/deleteEQPMaintainRecord", 　　   //请求路径
						params:{
							'id':obj.data.id,
						}  //传递参数，字符串map或对象，转化成？name=lisa形式跟在请求路径后面
					}).success(function(res){
						console.log(res);
						layer.closeAll('loading');
						if(res.code==200){
							layer.msg(res.msg,{icon: 1});
							obj.del();
							layui.table.reload('eqpMaintaintable', {});
						}else{
							layer.msg(res.msg,{icon: 2});
						}
					}).error(function(res){
						alert("cuowu");
					})
				});
			}
	 }])
}())