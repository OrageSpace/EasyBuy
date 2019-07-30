<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<style>
		.nav>.leftNav{
			display:none;
		}
	</style>
    <!--[if IE 6]>
    <script src="${ctx}/statics/js/common//iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
<title>易买网--商品列表</title>
</head>
<body>  
<!--Begin Header Begin-->
	<%@ include file="../../common/pre/header.jsp"%>
	<%@ include file="../../common/pre/searchBar.jsp"%>
<!--End Header End--> 
<!--Begin Menu Begin-->
	<%@ include file="../../common/pre/categoryBar.jsp"%>
<!--End Menu End--> 
<div class="i_bg">
    <!--Begin 筛选条件 Begin-->

    <!--End 筛选条件 End-->
    
    <div class="content mar_20">
    	<div class="l_history">
        	<div class="his_t">
            	<span class="fl">浏览历史</span>
                <span class="fr"><a href="#">清空</a></span>
            </div>
        	<ul>
            	<li>
                    <div class="img"><a href="#"><img src="${ctx}/statics/images/his_1.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="${ctx}/statics/images/his_2.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>768.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="${ctx}/statics/images/his_3.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>680.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="${ctx}/statics/images/his_4.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
                <li>
                    <div class="img"><a href="#"><img src="${ctx}/statics/images/his_5.jpg" width="185" height="162" /></a></div>
                	<div class="name"><a href="#">Dior/迪奥香水2件套装</a></div>
                    <div class="price">
                    	<font>￥<span>368.00</span></font> &nbsp; 18R
                    </div>
                </li>
        	</ul>
        </div>
        <div class="l_list">
        	<div class="list_t">
            	<span class="fl list_or">
                	<a href="#" class="now">默认</a>
                    <a href="#">
                    	<span class="fl">销量</span>                        
                        <span class="i_up">销量从低到高显示</span>
                        <span class="i_down">销量从高到低显示</span>                                                     
                    </a>
                    <a href="#">
                    	<span class="fl">价格</span>                        
                        <span class="i_up">价格从低到高显示</span>
                        <span class="i_down">价格从高到低显示</span>     
                    </a>
                    <a href="#">新品</a>
                </span>
                <span class="fr">共发现${requestScope.pageObj.totalCount}件</span>
            </div>
            <div class="list_c">
            	
                <ul class="cate_list">
                	<c:forEach var="product" items="${requestScope.pageObj.list}">
                		<li>
	                    	<div class="img"><a href="#"><img src="${ctx}/files/${product.fileName}" width="210" height="185" /></a></div>
	                        <div class="price">
	                            <font>￥<span>${product.price}</span></font> &nbsp; 26R
	                        </div>
	                        <div class="name"><a href="#">${product.name}</a></div>
	                        <div class="carbg">
	                        	<a href="#" class="ss">收藏</a>
	                            <a href="#" class="j_car" onclick="addProductToCart('${product.id}','1');">加入购物车</a>
	                        </div>
	                    </li>
                	</c:forEach>
                </ul>
                
                <div class="pages" id="pages">
                	
                </div>
                
                
                
            </div>
        </div>
    </div>
    
    <!--Begin Footer Begin -->
   	<%@ include file="../../common/pre/footer.jsp"%>
   	<%@ include file="../../common/pre/footer_desc.jsp"%>
    <!--End Footer End -->    
</div>

</body>

    <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>        
	<script type="text/javascript" src="${ctx}/statics/js/common/lrscroll_1.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/common/n_nav.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/product/product.js"></script>
    <script type="text/javascript" src="${ctx}/statics/js/cart/cart.js"></script>
<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
