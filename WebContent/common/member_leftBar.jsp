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
<html>
<head>
</head>
<body>
	<div class="left_n">管理中心</div>
	<div class="left_m">
		<div class="left_m_t t_bg1">订单中心</div>
		<ul>

			<li <c:if test="${param.menu==1}"> class="now" </c:if>><a
				href="#" onclick="toMyOrder(1);">我的订单</a></li>

			<li><a href="Member_Address.html">收货地址</a></li>
			<li><a href="#">缺货登记</a></li>
			<li><a href="#">跟踪订单</a></li>
		</ul>
	</div>
	<div class="left_m">
		<div class="left_m_t t_bg2">会员中心</div>
		<ul>
			<li><a href="Member_User.html">用户信息</a></li>
			<li><a href="Member_Collect.html">我的收藏</a></li>
			<li><a href="Member_Msg.html">我的留言</a></li>
			<li><a href="Member_Links.html">推广链接</a></li>
			<li><a href="#">我的评论</a></li>
		</ul>
	</div>
	<div class="left_m">
		<div class="left_m_t t_bg3">账户中心</div>
		<ul>
			<li><a href="Member_Safe.html">账户安全</a></li>
			<li><a href="Member_Packet.html">我的红包</a></li>
			<li><a href="Member_Money.html">资金管理</a></li>
		</ul>
	</div>
	<div class="left_m">
		<div class="left_m_t t_bg4">后台管理</div>
		<ul>
			<li><a href="#" onclick="toProductCategory(1)">分类管理</a></li>
			<li><a href="Member_Results.html">商品管理</a></li>
			<li><a href="Member_Commission.html">商品上架</a></li>
			<li><a href="Member_Cash.html">申请提现</a></li>
		</ul>
	</div>
	<script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/member/member.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
</body>
</html>