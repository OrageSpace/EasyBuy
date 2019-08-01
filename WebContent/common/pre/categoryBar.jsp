<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="menu_bg">
	<div class="menu">
    	<!--Begin 商品分类详情 Begin-->    
    	<div class="nav">
        	<div class="nav_t">全部商品分类</div>
            <div class="leftNav">
                <ul> 
	                <c:forEach var="pc1" items="${requestScope.pcvList }">
	                	<li>
	                    	<div class="fj">
	                        	<span class="n_img"><span></span><img src="${ctx}/statics/images/nav1.png" /></span>
	                            <span class="fl">${pc1.productCategory.name}</span>
	                        </div>
	                        <div class="zj">
	                            <div class="zj_l">
	                                <c:forEach var="pc2" items="${pc1.productCategoryVoList}">
	                                	<div class="zj_l_c">
		                                    <h2>${pc2.productCategory.name}</h2>
		                                    <c:forEach var="pc3" items="${pc2.productCategoryVoList}">
		                                    	<a href="${ctx}/Product?action=queryProductList&keyWords=${pc3.productCategory.name}">${pc3.productCategory.name}</a>|
		                                    </c:forEach>
	                               		</div>
	                                </c:forEach>
	                            </div>
	                            <div class="zj_r">
	                                <a href="#"><img src="${ctx}/statics/images/n_img1.jpg" width="236" height="200" /></a>
	                                <a href="#"><img src="${ctx}/statics/images/n_img2.jpg" width="236" height="200" /></a>
	                            </div>
	                        </div>
	                    </li>
	                </c:forEach>     
                </ul>            
            </div>
        </div>  
        <!--End 商品分类详情 End-->                                                     
    	<ul class="menu_r">                                                                                                                                               
        	<li><a href="Index.html">首页</a></li>
            <c:forEach var="category" items="${requestScope.pcvList}">
            	<li><a href="${ctx}/Product?action=queryProductList&keyWords=${category.productCategory.name}&parentId=${category.productCategory.parentId}">${category.productCategory.name}</a></li>
            </c:forEach>
        </ul>
        <div class="m_ad">中秋送好礼！</div>
    </div>
</div>
</body>
</html>