/**
 * 表单验证 
 */
function adduser() {
	var flag = true;
	var code = document.getElementById('code').value;
	var password = document.getElementById('password').value;
	var password2 = document.getElementById('password2').value;
	var name = document.getElementById('name').value;
	var gender = document.getElementById('gender').value;
	var phone = document.getElementById('phone').value;
	var email = document.getElementById('email').value;s
	var role = document.getElementById('role').value;
	var classid = document.getElementById('classid').value;
	var operate_user_id = document.getElementById('operate_user_id').value;
	var state = document.getElementById('state').value;
	var memo = document.getElementById('memo').value;
	//账号
	if (!/^[\w]*$/.test(code)) {
		document.getElementById('codeInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请输入账号名！</font>';
		flag = false;
	} else {
		document.getElementById('codeInfo').innerHTML = '<images src="images/ok.png"/>';
	}
	//密码
	if (!/^[\w\.]{6,12}$/.test(password)) {
		document.getElementById('passwordInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请输入密码！</font>';
		flag = false;
	} else {
		document.getElementById('passwordInfo').innerHTML = '<images src="images/ok.png"/>';
	}
	//密码验证
	if ( password != password2) {
		document.getElementById('password2Info').innerHTML = '<images src="images/err.png"/><font color="red" size="3">两次密码输入不一致！</font>';
		flag = false;
	} else {
		document.getElementById('password2Info').innerHTML = '<images src="images/ok.png"/>';
	}

	//名字

	if (name == '') {
		document.getElementById('nameInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">名字不能为空！</font>';
		flag = false;
	} else {
		document.getElementById('nameInfo').innerHTML = '<images src="images/ok.png"/>';
	}
	//cellphone
	if (!/^(13|15|14|17|18)\d{9}$/.test(phone)) {
		document.getElementById('phoneInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">手机号码不存在！</font>';
		flag = false;
	} else {
		document.getElementById('phoneInfo').innerHTML = '<images src="images/ok.png"/>';
	}

	//邮箱

	if (!/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/
			.test(email)) {
		document.getElementById('emailInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">输入的邮箱格式不对！</font>';
		flag = false;
	} else {
		document.getElementById('emailInfo').innerHTML = '<images src="images/ok.png"/>';
	}

	//角色
	if (role == '') {
		document.getElementById('roleInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">年龄不能为空！</font>';
		flag = false;
	} else {
		document.getElementById('roleInfo').innerHTML = '<images src="images/ok.png"/>';
	}
	//班级
	if (classid == '') {
		document.getElementById('classidInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">年龄不能为空！</font>';
		flag = false;
	} else {
		document.getElementById('classidInfo').innerHTML = '<images src="images/ok.png"/>';
	}
	//编号
	if (operate_user_id == '') {
		document.getElementById('operateInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">年龄不能为空！</font>';
		flag = false;
	} else {
		document.getElementById('operateInfo').innerHTML = '<images src="images/ok.png"/>';
	}


	// 性别
		var sexflag = false;
		for (var i = 0; i < gender.length; i++) {
			if (gender[i].checked){
				sexflag = true;
				break;
			}
		}
		if (!sexflag) {
			document.getElementById('genderInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请选择性别！</font>'; 
			flag = false;
		} else {
			document.getElementById('genderInfo').innerHTML = '<images src="images/ok.png"/>'; 
		}
	
	return flag;
}
/*
 * 清出文本框的内容
 */


//清除账号
function clearusenameText() {
	var code = document.getElementById('code').value;
	if (code == '请您输入账号') {
		document.getElementById('code').value = '';
	}
}
/*
 * 验证账号
 */
function validateusename() {
	var code = document.getElementById('code').value;
	if (code == '') {
		document.getElementById('code').value = '请您输入账号';
	}
	if (!/^[\w]*$/.test(code)) {
		document.getElementById('codeInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请输入账号名！</font>';
		flag = false;

	} else {
		document.getElementById('codeInfo').innerHTML = '<images src="images/ok.png"/>';
	}
}

/*
 * 验证密码
 */
function validatePassword() {
	var password = document.getElementById('password').value;
	if (!/^[\w\.]{6,12}$/.test(password)) {
		document.getElementById('passwordInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">密码长度错误，必须在6~12位之间！</font>';
		flag = false;

	} else {
		document.getElementById('passwordInfo').innerHTML = '<images src="images/ok.png"/>';
	}
}

/*
 * 再次验证密码
 */
function validatePassword1() {
	var password2 = document.getElementById('password2').value;
	var password = document.getElementById('password').value;
	if (password != password2) {
		document.getElementById('password2Info').innerHTML = '<images src="images/err.png"/><font color="red" size="3">两次密码输入不一致！</font>';
		flag = false;
	} else {
		document.getElementById('password2Info').innerHTML = '<images src="images/ok.png"/>';
	}
}

/*
 * 清除QQtex
 
function clearQQText() {
	var qq = document.getElementById('qq').value;
	if (qq == '请您输QQ号') {
		document.getElementById('qq').value = '';
	}
}

 * 验证QQ号
 
function validateQQ() {
	var qq = document.getElementById('qq').value;
	if (qq == '') {
		document.getElementById('qq').value = '请您输QQ号';
	}
	if (!/^[1-9]\d{4,10}$/.test(qq)) {
		document.getElementById('qqInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请输入5~11位数字QQ号！</font>';
		flag = false;
	} else {
		document.getElementById('qqInfo').innerHTML = '<images src="images/ok.png"/>';
	}
}*/

//清除名字
function nameText() {
	var name = document.getElementById('name').value;
	if (name == '请您输入名字') {
		document.getElementById('name').value = '';
	}
}
/*
 * 验证名字
 */
function validatename() {
	var name = document.getElementById('name').value;
	if (name == '') {
		document.getElementById('name').value = '请您输入名字';
	}
	if (!/^[\w]*$/.test(name)) {
		document.getElementById('nameInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请输入名字！</font>';
		flag = false;
	} else {
		document.getElementById('nameInfo').innerHTML = '<images src="images/ok.png"/>';
	}
}
//清除角色
function cleareRoleText() {
	var role = document.getElementById('role').value;
	if (role == '请您输入角色') {
		document.getElementById('role').value = '';
	}
}
/*
 * 验证角色
 */
function validateRole() {
	var role = document.getElementById('role').value;
	if (role == '') {
		document.getElementById('role').value = '请您输入角色';
	}
	if (!/^[\w]*$/.test(role)) {
		document.getElementById('roleInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请您输入角色！</font>';
		flag = false;
	} else {
		document.getElementById('roleInfo').innerHTML = '<images src="images/ok.png"/>';
	}
}
//清除课程
function cleareClassText() {
	var classid = document.getElementById('classid').value;
	if (classid == '请您输入课程') {
		document.getElementById('classid').value = '';
	}
}
/*
 * 验证课程
 */
function validateClass() {
	var classid = document.getElementById('classid').value;
	if (classid == '') {
		document.getElementById('classid').value = '请您输入课程';
	}
	if (!/^[\w]*$/.test(classid)) {
		document.getElementById('classidInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请您输入课程！</font>';
		flag = false;
	} else {
		document.getElementById('classidInfo').innerHTML = '<images src="images/ok.png"/>';
	}
}
//清除编号
function cleareOperateText() {
	var operate_user_id = document.getElementById('operate_user_id').value;
	if (operate_user_id == '请您输入编号') {
		document.getElementById('operate_user_id').value = '';
	}
}
/*
 * 验证编号
 */
function validateOperate() {
	var operate_user_id = document.getElementById('operate_user_id').value;
	if (operate_user_id == '') {
		document.getElementById('operate_user_id').value = '请您输入编号';
	}
	if (!/^[0-9]{10}$/.test(operate_user_id)) {
		document.getElementById('operate_user_idInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请输入编号！</font>';
		flag = false;
	} else {
		document.getElementById('operate_user_idInfo').innerHTML = '<images src="images/ok.png"/>';
	}
}
//清除状态
function cleareStateText() {
	var state = document.getElementById('state').value;
	if (state == '请您输入状态') {
		document.getElementById('state').value = '';
	}
}
/*
 * 验证状态
 */
function validateState() {
	var state = document.getElementById('state').value;
	if (state == '') {
		document.getElementById('state').value = '请您输入状态';
	}
	if (!/^[\w]*$/.test(state)) {
		document.getElementById('stateInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">请您输入状态！</font>';
		flag = false;
	} else {
		document.getElementById('stateInfo').innerHTML = '<images src="images/ok.png"/>';
	}
}
/*
 * 清除emailtex
 */
function clearemailText() {
	var email = document.getElementById('email').value;
	if (email == '请您输邮箱账号') {
		document.getElementById('email').value = '';
	}
}
/*

 /*
 * 验证email号
 */
function validateEmail() {
	var email = document.getElementById('email').value;
	if (email == '') {
		document.getElementById('email').value = '请您输邮箱账号';
	}
	if (!/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/
			.test(email)) {
		document.getElementById('emailInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">输入的邮箱格式不对！</font>';
		flag = false;
	} else {
		document.getElementById('emailInfo').innerHTML = '<images src="images/ok.png"/>';
	}
}

/*
 * 清除phone
 */
function clearcellphoneText() {
	var phone = document.getElementById('phone').value;
	if (phone == '请您输入手机号') {
		document.getElementById('phone').value = '';
	}
}
/*

 /*
 * 验证手机号
 */
function validatecellphone() {
	var phone = document.getElementById('phone').value;
	if (phone == '') {
		document.getElementById('phone').value = '请您输入手机号';
	}
	if (!/^(13|15|14|17|18)\d{9}$/.test(phone)) {
		document.getElementById('phoneInfo').innerHTML = '<images src="static/images/err.png"/><font color="red" size="3">手机号码不存在！</font>';
		flag = false;
	} else {
		document.getElementById('phoneInfo').innerHTML = '<images src="static/images/ok.png"/>';
	}
}

/*
 * 清除个人详细
 */
function cleareMemoText() {
	var memo = document.getElementById('memo').value;
	if (memo == '请您输入个人描述') {
		document.getElementById('memo').value = '';
	}
}
/*
 * 验证详细
 */
function validateMemo() {
	var memo = document.getElementById('memo').value;
	if (memo == '') {
		document.getElementById('memo').value = '请您输入个人描述';
	}
	if (memo == '') {
		document.getElementById('memoInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">个人描述不能为空！</font>';
		flag = false;
	} else {
		document.getElementById('memoInfo').innerHTML = '<images src="images/ok.png"/>';
	}

}

/*
 * 验证个人呢详情
 
function validatetextarea() {
	var textarea = document.getElementById('textarea').value;
	if (textarea == '') {
		document.getElementById('textareaInfo').innerHTML = '<images src="images/err.png"/><font color="red" size="3">个人详情不能为空！</font>';
		flag = false;
	} else {
		document.getElementById('textareaInfo').innerHTML = '<images src="images/ok.png"/>';
	}

}*/

/*
 * 协议同意
 */
function agreeSelect() {
	var agree = document.getElementById('agree');
	if (!agree.checked) {
		document.getElementById('submit').disabled = true;
	} else {
		document.getElementById('submit').disabled = false;
	}
}

/*
*clearInfo
*/
function clearInfo(){
	var span = document.getElementsByName('span');
	for (var i = 0; i <span.length; i++) {
		 span[i].innerHTML ='';
		}
	}

