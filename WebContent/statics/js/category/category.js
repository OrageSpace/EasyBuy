/**
 * 
 */
function toPage(currPageNo,action,url,sort){
	jq.ajax({
		url:contentPath+url,
		method:"POST",
		data:{action:action,currPageNo:currPageNo,sort:sort},
		success:function(data){
			$("#m_right").html(data);
			refreshLeftBar(1);
		},
		error:function(){
			showMessage("检索数据时发生未知异常，请稍后重试或与系统管理员联系！");
		}
	});
}

function commonPageControl(currPageNo,totalCount,totalPageCount,url,action){
	var pages=$("#pages");
	var nodeStr="";
	
	currPageNo=parseInt(currPageNo);
	totalCount=parseInt(totalCount);
	
	if(totalCount>12){
		if(currPageNo-1>0){
			nodeStr+='<a href="#" onclick="toPage('+(currPageNo-1)+',action,url,sort)" id="previous" class="p_pre">上一页</a>';
		}
		
		if(totalPageCount>5){
			nodeStr+='<a href="#" class="cur">'+currPageNo+'</a>';
			
			for(var i=1;i<3-currPageNo;i++){
				var index=currPageNo+i;
				nodeStr+='<a href="#" onclick="toPage('+index+',action,url,sort)" class="p_pre">'+index+'</a>';
			}
			nodeStr+="...";
			nodeStr+='<a href="#" onclick="toPage('+totalPageCount+',action,url,sort)">'+totalPageCount+'</a>';
		}else{
			nodeStr+='<a href="#" onclick="toPage('+currPageNo+',action,url,sort)" class="cur">'+currPageNo+'</a>';
			
			for(var i=1;i<totalPageCount-currPageNo;i++){
				var index=currPageNo+i;
				nodeStr+='<a href="#" onclick="toPage('+index+',action,url,sort)" class="p_pre">'+index+'</a>';
			}
		}
		
		if(totalPageCount-currPageNo>1){
			nodeStr+='<a href="#" onclick="toPage('+(currPageNo+1)+',action,url,sort)" id="next" class="p_pre">下一页</a>"';
		}
	}
	
	pages.html(nodeStr);
}

commonPageControl(currPageNo,totalCount,totalPageCount,url,action,sort);