/**
 * 实现购物车的js代码
 */
function addProductToCart(id,quantity){
	$.ajax({
		url:contentPath+"/Cart",
		method:"POST",
		data:{action:"addCartItem",id:id,quantity:quantity},
		dataType:"text",
		success:function(data){
			var result=eval("("+data+")");
			showMessage(result.message);
			if(result.status==1){
				refreshCart();
			}
		}
	});
	
	return false;
}

/**
 * 刷新购物车模块内容的JS代码
 * @returns
 */
function refreshCart(){
	$.ajax({
		url:contentPath+"/Cart",
		method:"POST",
		data:{action:"refreshCart"},
		dataType:"text",
		success:function(data){
			$("#searchBar").html(data);
		},
		error:function(){
			showMessage("刷新购物车状态时发生异常，请与系统管理员联系！");
		}
	});
}