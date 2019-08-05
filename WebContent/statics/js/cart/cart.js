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

function settlement1(){
	$.ajax({
		url:contentPath+"/Cart",
		method:"POST",
		data:{action:"settlement1"},
		success:function(data){
			$("#settlmentDiv").html(data);
		}
	});
}

function settlement2(){
	$.ajax({
		url:contentPath+"/Cart",
		method:"POST",
		data:{action:"settlement2"},
		success:function(data){
			$("#settlmentDiv").html(data);
		}
	});
}


settlement1();


function subQuantity(obj,id,stock){		
	var c = obj.parent().find(".car_ipt").val();
	
	c=parseInt(c)+1;
	
	if(c>stock){
		showMessage("商品库存不足！");
		return;
	}
		
	modifyQuantity(c,id,obj);
}

function LessQuantity(obj,id){    
	var c = obj.parent().find(".car_ipt").val();
	
	console.log(1);
	if(c==1){    
		c=1; 
		return;
	}else{    
		c=parseInt(c)-1; 
	}
	modifyQuantity(c,id,obj);
}  

function modifyQuantity(c,id,obj){
	$.ajax({
		url:contentPath+"/Cart",
		method:"POST",
		data:{action:"modifyQuantity",id:id,quantity:c},
		success:function(data){
		  data=eval("("+data+")");	
		  
		  if(data.status==-1){
			  showMessage(data.message);
			  return;
		  }
		  refreshCart();
		 setTimeout(settlement1(),1000);
		},
		error:function(){
			showMessage("操作失败，数量更新时发生异常，请稍后重试或与系统管理员联系！");
		}
	});
}

/**
 * 将商品加入收藏的函数
 * @param id
 * @returns
 */
function addFavorite(id){
	$.ajax({
		url:contentPath+"/Favorite",
		method:"POST",
		data:{
			action:"addFavorite",
			id:id
		},
		dataType:"JSON",
		success:function(data){
			if(data.status==1){
				refreshFavorite();
			}else if(data.status==-1){
				showMessage(data.message);
			}
		},
		error:function(){
			showMessage("添加收藏时发生异常，请与系统管理员联系！");
		}
	});
}

/**
 * 用于刷新收藏列表的函数
 * @returns
 */
function refreshFavorite(){
	$.ajax({
		url:contentPath+"/Favorite",
		method:"POST",
		data:{action:"refreshFavorite"},
		dataType:"text",
		success:function(data){
			console.log(data);
			$("#favoriteList").html(data);
		},
		error:function(){
			showMessage("刷新收藏列表时发生异常，请与系统管理员联系！");
		}
	});
}

var payMethod="支付宝";

function changePaymentMethod(obj){
	$(obj).addClass("checked").siblings().removeClass("checked");
	payMethod=obj.text();
}

/**
 * 生成订单
 * @returns
 */
function settlement3(){
	var addressId=$("#addressId").val();
	var newAddress=$("#city option:selected").text()+$("#area option:selected").text()+$("#county option:selected").text();
	
	$.ajax({
		url:contentPath+"/Cart",
		method:"POST",
		data:{
			action:"toSettlement3",
			addressId:addressId,
			newAddress:newAddress,
			payMethod:payMethod
		},
		success:function(data){
			$("#settlmentDiv").html(data);
		}
	});
}