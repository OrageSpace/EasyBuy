<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div class="l_history">
	<div class="his_t">
		<span class="fl">我的收藏</span> <span class="fr"><a href="#">清空</a></span>
	</div>
	<ul>
		<c:forEach var="favorite" items="${sessionScope.favoriteList}">
			<li>
				<div class="img">
					<a href="#"><img src="${ctx}/files/${favorite.fileName}"
						width="185" height="162"></a>
				</div>
				<div class="name">
					<a href="#">${favorite.name}</a>
				</div>
				<div class="price">
					<font>￥<span>${favorite.price}</span></font> &nbsp;
				</div>
			</li>
		</c:forEach>
	</ul>
</div>