<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">
	var contentPath="${ctx}";
	var currPageNo=parseInt("${requestScope.pageObj.currPageNo}");
	var totalCount=parseInt("${requestScope.pageObj.totalCount}");
	var totalPageCount=parseInt("${requestScope.pageObj.totalPageCount}");
	var url=contentPath+"${requestScope.pageObj.url}";
</script>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/statics/css/style.css" />
<style>
.order_tab {
	font-size: 14px;
}
</style>
<!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
<title>易买网---我的订单</title>
</head>
<body>
	<p></p>
	<div class="mem_tit">订单号：${param.serialNumber}</div>
	<table border="0" class="order_tab"
		style="width: 930px; text-align: center; margin-bottom: 30px;"
		cellspacing="0" cellpadding="0">
		<tr>
			<td width="40%">商品名称</td>
			<td width="25%">数量</td>
			<td width="15%">金额</td>
		</tr>
		<c:forEach var="order" items="${requestScope.pageObj.list}">
			<tr>
				<td><font color="#ff4e00">${order.serialNumber}</font></td>
				<td>${order.createTime}</td>
				<td>￥${order.cost}</td>
				<td><c:if test="${order.status == 1}">
									未确认
								</c:if> <c:if test="${order.status == 2}">
									已发货
								</c:if> <c:if test="${order.status == 3}">
									已收货
								</c:if></td>
				<td><c:if test="${order.status==3}">
									删除订单
								</c:if> <c:if test="${order.status!=3}">
						<a href="">申请退款</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pages" id="pages"></div>
	<script type="text/javascript"
		src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/member/member.js"></script>
</body>
<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
