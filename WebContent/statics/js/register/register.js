/**
 * 实现注册
 */
function register(){
	var loginName=$("#loginName").val().trim();
	var password=$("#password").val().trim();
	var confirmPwd=$("#confirmPwd").val().trim();
	var email=$("#email").val().trim();
	var mobile=$("#mobile").val().trim();
	var userName=$("#userName").val().trim();
	var identityCode=$("#identiryCode").val().trim();
	var sex=$(".sex");
	
	for(var i=0;i<sex.length;i++){
		if(sex.eq(i).attr("checked")=="checked"){
			sex=sex.eq(i).val();
		}
	}
	
	//检查表单信息是否填写完整
	var emailReg=/^([a-zA-Z0-9_\.\-])+(\@([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})$/;
	var mobileReg=/^\d{11}$/;
	var identityCodeRed=/^\w{15,18}$/;
	
	if(loginName==""||loginName==null){
		showMessage("用户名不能为空！");
		return false;
	}
	
	if(password==""||password==null){
		showMessage("密码不能为空！");
		return false;
	}
	
	if(email==""||email==null){
		showMessage("邮箱不能为空！");
		return false;
	}
	
	if(mobile==""||mobile==null){
		showMessage("手机号不能为空！");
		return false;
	}
	
	if(userName==""||userName==null){
		showMessage("真实姓名不能为空！");
		return false;
	}
	
	if(password!=confirmPwd){
		showMessage("两次输入的密码不一致！");
		return false;
	}
	
	if(!emailReg.test(email)){
		showMessage("邮箱格式不正确！");
		return false;
	}
	
	if(!mobileReg.test(mobile)){
		showMessage("手机号码格式不正确！");
		return false;
	}
	
	if(!identityCodeRed.test(identityCode)){
		showMessage("身份证号码格式不正确！");
		return false;
	}
	
	$.ajax({
		url:contentPath+"/Register",
		method:"POST",
		data:{loginName:loginName,password:password,confirmPwd:confirmPwd,email:email,mobile:mobile,userName:userName,sex:sex,identityCode:identityCode,action:"doRegister"},
		dataType:"JSON",
		success:function(data){
			if(data.status==1){
				showMessage("注册成功，您的用户名为“"+loginName+"”!");
				window.setTimeout(function(){
					location.href=contentPath+'/Login?action=toLogin';
				},3000);
			}else if(data.status==-1){
				showMessage(data.message);
			}
		},
		error:function(){
			showMessage("注册时发生异常，请稍后重试或与系统管理员联系！");
		}
	});
	
	return false;
}
