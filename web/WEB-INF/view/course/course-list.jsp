<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" href="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/css/demo.css" type="text/css">
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script  src="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/js/jquery-1.4.4.min.js"></script>
	<script  src="<%=request.getContextPath() %>/static/common/plugin/zTree_v3-master/js/jquery.ztree.core.js"></script>

    
<title>Insert title here</title>
</head>
<body>

		<SCRIPT type="text/javascript">
		
		var setting = {
			view: {
				dblClickExpand: true,
				showLine: true,
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};


		
 		var zNodes = ${json};
 		
		function onClick(e,treeId, treeNode) {
			
			//显示添加课程按钮
			document.getElementById('insertCourse').style.display="";
			//隐藏添加表单
			document.getElementById('courseInsert').style.display="none";
			//清除验证信息
			document.getElementById('nameInfo').innerHTML=''; 
			if(treeNode.pId == null){
				
				document.getElementById("courseDetail").style.display="none";
				
			}else{
				
				document.getElementById("courseDetail").style.display="";
				document.getElementById('pName').value=treeNode.getParentNode().name;
			}

			var operateTime = new Date(treeNode.operateTime);
			// js日期格式
			operateTime = operateTime.getFullYear()+"-"+(operateTime.getMonth()+1)+"-"+operateTime.getDate();
			
			
			
			//设置显示信息
			document.getElementById('id').value=treeNode.id;
			
			document.getElementById('name').value=treeNode.name;
			document.getElementById('operateUser').value=treeNode.operateUserId;
			document.getElementById('operateTime').value=operateTime;
			document.getElementById('memo').value=treeNode.memo;
			
			var all_options = document.getElementById("state").options;
			if(treeNode.state == 1){
				all_options[0].selected = true;
			}else{
				all_options[1].selected = true;
			}
				
			
		}
		
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
			//默认展开所有节点
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
			treeObj.expandAll(true); 
		});
		

		//节点删除
		function removeNodes()
	    {
	        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	        //选中节点
	      var nodes = treeObj.getSelectedNodes();
	      for (var i=0, l=nodes.length; i < l; i++) 
	      {
	          //删除选中的子节点
	        treeObj.removeChildNodes(nodes[i]);
	      }
	    }
		
		
	</SCRIPT>
	<style type="text/css">
		.ztree li button.switch {visibility:hidden; width:1px;}
		.ztree li button.switch.roots_docu {visibility:visible; width:16px;}
		.ztree li button.switch.center_docu {visibility:visible; width:16px;}
		.ztree li button.switch.bottom_docu {visibility:visible; width:16px;}
	</style>
 </HEAD>

<BODY>
<h1 style="margin-top:15px;font-size:30px;">课程管理</h1>
<h6></h6>
<hr/>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
		<center id="insertCourse" style="display: none;" ><input type="button" value="添加课程" onclick="insertCourse();"/></center>
	</div>
	
	
 	<div class="right" id="courseDetail" style="display: none;">
 		<form id = "courseUpdateForm" action = "CourseServlet?command=courseUpdate" method="post">
			<ul class="info">
				<li class="title"><h2>课程信息</h2>
					<ul class="list">
						
						<li class="highlight_red" style="display: none;">课程Id：<input type="text" name ="id" id = "id" /></li><br/>
						<li class="highlight_red">父 节 点:<input type="text" name ="pName" id = "pName" disabled = "disabled"/></li><br/>
						<li class="highlight_red">课程名称:<input type="text" name ="name" id ="name" onkeyup="validateCourse();" /><span id = "nameInfo" style="color:red;"></span></li><br/>
						
						<li class="highlight_red">状　　态：
							<select name = "state" id="state" onchange="validateCourse();">
								<option>有效</option>
								<option>无效</option>
							</select>
						</li><br/>
						
						<li class="highlight_red">操 作 者:<input type="text" name ="operateUser" id ="operateUser"  disabled = "disabled"/></li><br/>
						<li class="highlight_red">操作时间:<input type="text" name ="operateTime" id ="operateTime"  disabled = "disabled"/></li><br/>
						<li class="highlight_red">备　　注:<textarea rows="6" cols="50" style="vertical-align:top;" name="memo" id="memo"></textarea></li><br/>
						<li class="highlight_red"><input type="submit" id="updateSubmit" value ="修改" onclick="return confirm('确定修改该课程吗？');" disabled = "disabled"/></li><br/>
					</ul>
				</li>
			</ul>
		</form>
	</div> 
	
	 <div class="right" id="courseInsert" style="display: none;">
 		<form id = "courseInsertForm" action = "CourseServlet?command=courseInsert" method="post">
			<ul class="info">
				<li class="title"><h2>添加课程</h2>
					<ul class="list">
						
						<li class="highlight_red"  style="display: none;">父节点Id:<input type="text" name ="newPId" id="newPId"/></li><br/>
						
						<!-- <li class="highlight_red"  style="display: none;">父节点是否为叶子节点:<input type="text" name ="parentIsLeaf" id="parentIsLeaf"/></li><br/> -->
						
						
						<li class="highlight_red">父 节 点:<input type="text" name ="newPName" id="newPName"  disabled = "disabled"/></li><br/>
						<li class="highlight_red">课程名称:<input type="text" name ="newName" id="newName" onkeyup="validate();" /><span id = "newNameInfo" style="color:red;"></span></li><br/>
						
						<li class="highlight_red">状　　态：
							<select name = "state">
								<option>有效</option>
								<option>无效</option>
							</select>
						</li><br/>
						
						<li class="highlight_red">备　　注:<textarea rows="6" cols="50" style="vertical-align:top;" name="memo"></textarea></li><br/>
						<li class="highlight_red"><input type="submit" id="insertSubmit" value ="添加" onclick="return confirm('确定添加该课程吗？');" disabled = "disabled"/></li><br/>
					</ul>
				</li>
			</ul>
		</form>
	</div> 
	
</div>

<script type="text/javascript">
	
	/**
	*修改时课程名称做唯一验证
	*
	*/
	var validateCourse = function(){
		
		var courseName = document.getElementById('name').value;
	
		var id = document.getElementById('id').value;
		
	
		$.get("CourseServlet?command=validateCourse",{courseName:courseName,courseId:id},function(validateState){
			
			if(validateState == 'true'){
				document.getElementById('nameInfo').innerHTML='该课程名已存在！';
				document.getElementById('updateSubmit').disabled = "disabled";
			}else{
				document.getElementById('nameInfo').innerHTML='';
				document.getElementById('updateSubmit').disabled = "";
			}
			
		});
			
	}
	
	
	/**
	*添加时课程名称做唯一验证
	*
	*/
	var validate = function(){
		
		
		
		var courseName = document.getElementById('newName').value;
		
		
		$.get("CourseServlet?command=validateCourse",{courseName:courseName},function(validateState){
			
			if(validateState == 'true'){
				document.getElementById('newNameInfo').innerHTML='该课程名已存在！';
				document.getElementById('insertSubmit').disabled = "disabled";
			}else if(courseName == ""){
				document.getElementById('newNameInfo').innerHTML='该课程名不能为空！';
				document.getElementById('insertSubmit').disabled = "disabled";
			}else{
				document.getElementById('newNameInfo').innerHTML='';
				document.getElementById('insertSubmit').disabled = "";
			}
			
		});
			
	}
	
	
	/**
	*添加课程
	*
	*/
	
	function insertCourse(){
		//页面初始化
		document.getElementById('courseDetail').style.display= 'none';
		document.getElementById('courseInsert').style.display= '';
		
		//相应参数设置
		document.getElementById('newPId').value = document.getElementById('id').value;
		document.getElementById('newPName').value = document.getElementById('name').value;
		/* document.getElementById('parentIsLeaf').value = isLeaf; */
		
	}
	
</script>



</BODY>

</body>
</html>