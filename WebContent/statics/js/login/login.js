/**
 * 实现登录的函数
 */

function login(){
	var loginName=$("#loginName").val().trim();
	var pwd=$("#pwd").val().trim();
	
	if(loginName==""||login==null||pwd==""||pwd==null){
		return false;
	}
	
	//ajax实现登录验证
	$.ajax({
		url:contentPath+"/Login",
		method:"POST",
		data:{loginName:loginName,pwd:pwd,action:"login"},
		dataType:"JSON",
		success:function(data){
			if(data.status==1){
				location.href=contentPath+"/Home";
			}else if(data.status==-1){
				showMessage(data.message);
			}
		},
		error:function(){
			showMessage("验证用户信息时发生异常，请稍后重试或与系统管理员联系！");
		}
	});
	
	return false;
}