<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">
	var contentPath="${ctx}";
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>易买网---结算页面</title>
</head>
<body>
<jsp:include page="../../common/pre/header.jsp" flush="true"/>
<!--Begin Header Begin-->

<%@ include file="../../common/pre/searchBar.jsp" %>
<!--End Header End--> 
<!--Begin Menu Begin-->
<%@ include file="../../common/pre/categoryBar.jsp" %>

<div id="settlmentDiv" class="i_bg">
	
</div>
<!--Begin Footer Begin -->
    <%@ include file="../../common/pre/footer.jsp" %>
    <%@ include file="../../common/pre/footer_desc.jsp" %>
    <script type="text/javascript" src="${ctx}/statics/js/cart/cart.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/settlement/settlement.js"></script>
    <!--End Footer End -->    
</body>
</html>