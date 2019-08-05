<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">
	var contentPath = "${ctx}";
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/statics/css/style.css" />
<!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
    	<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>
		<script type="text/javascript" src="${ctx}/statics/js/member/member.js></script>
		<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
<title>易买网---个人中心</title>
</head>
<body>
	<jsp:include page="../../common/pre/header.jsp" flush="true" />
	<!--Begin Header Begin-->

	<%@ include file="../../common/pre/searchBar.jsp"%>
	<!--End Header End-->
	<!--End Header End-->
	<div class="i_bg bg_color">
		<!--Begin 用户中心 Begin -->
		<div class="m_content">

			<div class="m_left">
				<%@include file="../../common/member_leftBar.jsp"%>
			</div>

			<div class="m_right" id="m_right">
				<div class="m_des">
					<table border="0" style="width: 870px; line-height: 22px;"
						cellspacing="0" cellpadding="0">
						<tr valign="top">
							<td width="115"><img src="${ctx}/statics/images/user.jpg"
								width="90" height="90" /></td>
							<td>
								<div class="m_user">TRACY</div>
								<p>
									等级：注册用户 <br /> <font color="#ff4e00">您还差 270 积分达到 分红100</font><br />
									上一次登录时间: 2015-09-28 18:19:47<br /> 您还没有通过邮件认证 <a href="#"
										style="color: #ff4e00;">点此发送认证邮件</a>
								</p>
								<div class="m_notice">用户中心公告！</div>
							</td>
						</tr>
					</table>
				</div>

				<div class="mem_t">资产信息</div>
				<table border="0" class="mon_tab"
					style="width: 870px; margin-bottom: 20px;" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="33%">用户等级：<span style="color: #555555;">普通会员</span></td>
						<td width="33%">消费金额：<span>￥200元</span></td>
						<td width="33%">返还积分：<span>99R</span></td>
					</tr>
					<tr>
						<td>账户余额：<span>￥200元</span></td>
						</td>
						<td>红包个数：<span style="color: #555555;">3个</span></td>
						</td>
						<td>红包价值：<span>￥50元</span></td>
						</td>
					</tr>
					<tr>
						<td colspan="3">订单提醒： <font style="font-family: '宋体';">待付款(<span>0</span>)
								&nbsp; &nbsp; &nbsp; &nbsp; 待收货(<span>2</span>) &nbsp; &nbsp;
								&nbsp; &nbsp; 待评论(<span>1</span>)
						</font>
						</td>
					</tr>
				</table>
				<div class="mem_t">账号信息</div>
				<table border="0" class="acc_tab" style="width: 870px;"
					cellspacing="0" cellpadding="0">
					<tr>
						<td class="td_l">用户ID：</td>
						<td>${sessionScope.loginUser.loginName}</td>
					</tr>
					<tr>
						<td class="td_l b_none">身份证号：</td>
						<td>${sessionScope.loginUser.identityCode.substring(0,5)}***********${sessionScope.loginUser.identityCode.substring(17)}</td>
					</tr>
					<tr>
						<td class="td_l b_none">电 话：</td>
						<td>${sessionScope.loginUser.mobile}</td>
					</tr>
					<tr>
						<td class="td_l">邮 箱：</td>
						<td>${sessionScope.loginUser.email}</td>
					</tr>
					<tr>
						<td class="td_l b_none">注册时间：</td>
						<td>2015/10/10</td>
					</tr>
					<tr>
						<td class="td_l">完成订单：</td>
						<td>0</td>
					</tr>
					<tr>
						<td class="td_l b_none">邀请人：</td>
						<td>邀请人</td>
					</tr>
					<tr>
						<td class="td_l">登录次数：</td>
						<td>3</td>
					</tr>
				</table>


			</div>
		</div>
		<!--End 用户中心 End-->
		<%@ include file="../../common/pre/footer.jsp"%>
		<%@ include file="../../common/pre/footer_desc.jsp"%>
</body>
<script>
	/**
 * 
 */
function toMyOrder(currPageNo){
	$.ajax({
		url:contentPath+"/Center",
		method:"POST",
		data:{action:"toMyOrder",currPageNo:currPageNo},
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
		data:{action:"getLeftBar",menu:code},
		success:function(data){
			$("#m_left").html(data);
		},
		error:function(){
			showMessage("检索数据时发生未知异常，请稍后重试或与系统管理员联系！");
		}
	});
}
</script>
<!--[if IE 6]<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>![endif]-->
</html>
