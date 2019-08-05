<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">
	var contentPath="${ctx}";
</script>
<div class="top" id="searchBar">
    <div class="logo"><a href="Index.html"><img src="${ctx}/statics/images/logo.png" /></a></div>
    <div class="search">
    	<form action="${ctx}/Product?action=queryProductList" method="post">
        	<input type="text" name="keyWords" value="${requestScope.keyWords}" class="s_ipt" />
            <input type="submit" value="搜索" class="s_btn" />
        </form>                      
        <span class="fl"><a href="#">咖啡</a><a href="#">iphone 6S</a><a href="#">新鲜美食</a><a href="#">蛋糕</a><a href="#">日用品</a><a href="#">连衣裙</a></span>
    </div>
    <div class="i_car">
    	<div class="car_t">购物车 [ <span>${sessionScope.cart eq null ? 0:sessionScope.cart.items.size()}</span> ]</div>
        <div class="car_bg">
       		<!--Begin 购物车未登录 Begin-->
        	<c:if test="${empty sessionScope.loginUser}">
        		<div class="un_login">还未登录！<a href="Login.html" style="color:#ff4e00;">马上登录</a>
        	</c:if>
        	 查看购物车！
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
            	<c:forEach var="cartItem" items="${sessionScope.cart.items}">
            		<li>
	                	<div class="img"><a href="#"><img src="${ctx}/files/${cartItem.product.fileName}" width="58" height="58" /></a></div>
	                    <div class="name"><a href="#">${cartItem.product.name}</a></div>
	                    <div class="price"><font color="#ff4e00">￥${cartItem.product.price}</font> X${cartItem.quantity}</div>
	                </li>
            	</c:forEach>
            </ul>
            
            <c:if test="${sessionScope.cart.items.size()>0}">
            	<div class="price_sum">共计&nbsp; <font color="#ff4e00">￥</font><span>${sessionScope.cart.sumCost}</span></div>
            </c:if>
            
            <div class="price_a">
            	<c:if test="${!empty sessionScope.loginUser}">
            		<a href="${ctx}/Cart?action=toSettlement">去购物车结算</a>
            	</c:if>
            	<c:if test="${empty sessionScope.loginUser}">
            		<a href="${ctx}/Login?action=toLogin">去登录</a>
            	</c:if>
            </div>
            </div>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>