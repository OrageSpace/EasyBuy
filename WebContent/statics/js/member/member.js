/**
 * 
 */
function toMyOrder(currPageNo){
	jq.ajax({
		url:contentPath+"/Center",
		method:"POST",
		data:{action:"toMyOrder",currPageNo:currPageNo,sort:"createTime"},
		success:function(data){
			$("#m_right").html(data);
			refreshLeftBar(1);
		},
		error:function(){
			showMessage("检索数据时发生未知异常，请稍后重试或与系统管理员联系！");
		}
	});
}

function pageControl(currPageNo,totalCount,totalPageCount,url){
	var pages=$("#pages");
	var nodeStr="";
	
	if(totalCount>12){
	
		if(currPageNo-1>0){
			nodeStr+='<a href="#" onclick="toMyOrder('+(currPageNo-1)+')" id="previous" class="p_pre">上一页</a>';
		}
		
		if(totalPageCount>5){
			nodeStr+='<a href="#" class="cur">'+currPageNo+'</a>';
			
			for(var i=1;i<3-currPageNo;i++){
				var index=currPageNo+i;
				nodeStr+='<a href="#" onclick="toMyOrder('+index+')" class="p_pre">'+index+'</a>';
			}
			nodeStr+="...";
			nodeStr+='<a href="#" onclick="toMyOrder('+totalPageCount+')">'+totalPageCount+'</a>';
		}else{
			nodeStr+='<a href="#" onclick="toMyOrder('+currPageNo+')" class="cur">'+currPageNo+'</a>';
			
			for(var i=1;i<totalPageCount-currPageNo;i++){
				var index=currPageNo+i;
				nodeStr+='<a href="#" onclick="toMyOrder('+index+')" class="p_pre">'+index+'</a>';
			}
		}
		
		if(totalPageCount-currPageNo>1){
			nodeStr+='<a href="#" onclick="toMyOrder('+currPageNo+1+')" id="next" class="p_pre">下一页</a>"';
		}
	}
	
	pages.html(nodeStr);
}

pageControl(currPageNo,totalCount,totalPageCount,url);

function refreshLeftBar(code){
	$.ajax({
		url:contentPath+"/Center",
		method:"POST",
		data:{action:"refreshLeftBar",menu:code},
		success:function(data){
			$("#m_left").html(data);
		},
		error:function(){
			showMessage("检索数据时发生未知异常，请稍后重试或与系统管理员联系！");
		}
	});
}

function toOrderDetial(orderId,obj){
	$.ajax({
		url:contentPath+"/Center",
		method:"POST",
		data:{action:"toOrderDetial",
			  orderId:orderId,
			  serialNumber:obj.text()
		},
		success:function(data){
			$("#m_right").html(data);
		},
		error:function(){
			showMessage("检索数据时发生未知异常，请稍后重试或与系统管理员联系！");
		}
	});
}

function toProductCategory(currPageNo){
	$.ajax({
		url:contentPath+"/Category",
		method:"POST",
		data:{action:"toProductCategory",
			  currPageNo:currPageNo,
			  sort:"type"
		},
		success:function(data){
			$("#m_right").html(data);
		},
		error:function(){
			showMessage("检索数据时发生未知异常，请稍后重试或与系统管理员联系！");
		}
	});
}