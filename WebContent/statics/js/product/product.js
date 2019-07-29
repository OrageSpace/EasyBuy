/**
 * 
 */
//分页处理函数
function pageControl(currPageNo,totalCount,totalPageCount,url){
	var pages=$("#pages");
	var nodeStr="";
	
	if(totalCount>12){
	
		if(currPageNo-1>0){
			nodeStr+='<a href="'+url+"&currPageNo="+(currPageNo-1)+'" id="previous" class="p_pre">上一页</a>';
		}
		
		if(totalPageCount>5){
			nodeStr+='<a href="'+url+"&currPageNo="+(currPageNo)+'" class="cur">'+currPageNo+'</a>';
			
			for(var i=1;i<3-currPageNo;i++){
				var index=currPageNo+i;
				nodeStr+='<a href="'+url+"&currPageNo="+index+'" class="p_pre">'+index+'</a>';
			}
			nodeStr+="...";
			nodeStr+='<a href="'+url+"&currPageNo="+totalPageCount+'">'+totalPageCount+'</a>';
		}else{
			nodeStr+='<a href="'+url+"&currPageNo="+currPageNo+'" class="cur">'+currPageNo+'</a>';
			
			for(var i=1;i<totalPageCount-currPageNo;i++){
				var index=currPageNo+i;
				nodeStr+='<a href="'+url+"&currPageNo="+index+'" class="p_pre">'+index+'</a>';
			}
		}
		
		if(totalPageCount-currPageNo>1){
			nodeStr+='<a href="'+url+"&currPageNo="+(currPageNo+1)+'" id="next" class="p_pre">下一页</a>"';
		}
	}
	
	pages.html(nodeStr);
}

pageControl(currPageNo,totalCount,totalPageCount,url);