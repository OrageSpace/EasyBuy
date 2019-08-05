<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">
	var contentPath="${ctx}";
	var currPageNo=parseInt("${requestScope.pageObj.currPageNo}");
	var totalCount=parseInt("${requestScope.pageObj.totalCount}");
	var totalPageCount=parseInt("${requestScope.pageObj.totalPageCount}");
	var url="${requestScope.pageObj.url}";
	var action="${requestScope.action}";
	var sort="type";
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
	<div class="mem_tit">商品分类列表</div>
	<table border="0" class="order_tab"
		style="width: 930px; text-align: center; margin-bottom: 30px;"
		cellspacing="0" cellpadding="0">
		<tr>
			<td width="10%">编号</td>
			<td width="25%">名称</td>
			<td width="15%">级别</td>
			<td width="20%">父级编号</td>
			<td>操作</td>
		</tr>
		<c:forEach var="item" items="${requestScope.pageObj.list}">
			<tr>
				<td><font color="#ff4e00">${item.id}</font></td>
				<td>${item.name}</td>
				<td>
					<c:if test="${item.type ==1}">
						一级分类
					</c:if>
					
					<c:if test="${item.type ==2}">
						二级分类
					</c:if>
					
					<c:if test="${item.type ==3}">
						三级分类
					</c:if>
				</td>
				<td>${item.parentId}</td>
				<td>
					<a href="#">修改</a>
					<a href="#">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pages" id="pages"></div>
	<script type="text/javascript"
		src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/member/member.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/category/category.js"></script>
</body>
<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
