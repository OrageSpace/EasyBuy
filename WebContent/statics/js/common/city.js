/**
 * 获取城市JSON信息
 */
var cityObj=null;

getCityInfo();

function getCityInfo(){
	$.ajax({
		url:contentPath+"/City",
		method:"POST",
		data:{action:"getCityInfo"},
		dataType:"JSON",
		success:function(data){
			if(data.status==1){
				cityObj=eval("("+data.data+")");
				for (var i = 0; i < cityObj.length; i++) {
					var node=$("<option value="+cityObj[i].city.id+" onclick='dynamicNode(parseInt("+i+"))'>"+cityObj[i].city.name+"</option>");
					$("#city").append(node);
				}
			}
			
			dynamicNode(0);
		},
		error:function(){
			showMessage("获取省市信息时发生异常，请与系统管理员联系！");
		}
	});
}

function dynamicNode(index){
	var items=cityObj[index].items;
	
	$("#area").html("");
	for (var i = 0; i < items.length; i++) {
		var node=$("<option value="+items[i].city.id+" onclick='dynamicItem(parseInt("+index+"),parseInt("+i+"))'>"+items[i].city.name+"</option>");
		$("#area").append(node);
	}

	$("#county").html("");
	var ele=items[0].items;
	for (var i = 0; i < ele.length; i++) {
		var node=$("<option value="+ele[i].city.id+">"+ele[i].city.name+"</option>");
		$("#county").append(node);
	}
}

function dynamicItem(index1,index2){
	var ele=cityObj[index1].items[index2].items;
	$("#county").html("");
	for (var i = 0; i < ele.length; i++) {
		var node=$("<option value="+ele[i].city.id+">"+ele[i].city.name+"</option>");
		$("#county").append(node);
	}
}