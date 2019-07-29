<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">
	var contentPath="${ctx}";
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${ctx}/statics/css/style.css" />
</head>
<body>
	<div class="soubg">
		<div class="sou">
	    	<!--Begin 所在收货地区 Begin-->
	    	<span class="s_city_b">
	        	<span class="fl">送货至：</span>
	            <span class="s_city">
	            	<span>四川</span>
	                <div class="s_city_bg">
	                	<div class="s_city_t"></div>
	                    <div class="s_city_c">
	                    	<h2>请选择所在的收货地区</h2>
	                        <table border="0" class="c_tab" style="width:235px; margin-top:10px;" cellspacing="0" cellpadding="0">
	                          <tr>
	                            <th>A</th>
	                            <td class="c_h"><span>安徽</span><span>澳门</span></td>
	                          </tr>
	                          <tr>
	                            <th>B</th>
	                            <td class="c_h"><span>北京</span></td>
	                          </tr>
	                          <tr>
	                            <th>C</th>
	                            <td class="c_h"><span>重庆</span></td>
	                          </tr>
	                          <tr>
	                            <th>F</th>
	                            <td class="c_h"><span>福建</span></td>
	                          </tr>
	                          <tr>
	                            <th>G</th>
	                            <td class="c_h"><span>广东</span><span>广西</span><span>贵州</span><span>甘肃</span></td>
	                          </tr>
	                          <tr>
	                            <th>H</th>
	                            <td class="c_h"><span>河北</span><span>河南</span><span>黑龙江</span><span>海南</span><span>湖北</span><span>湖南</span></td>
	                          </tr>
	                          <tr>
	                            <th>J</th>
	                            <td class="c_h"><span>江苏</span><span>吉林</span><span>江西</span></td>
	                          </tr>
	                          <tr>
	                            <th>L</th>
	                            <td class="c_h"><span>辽宁</span></td>
	                          </tr>
	                          <tr>
	                            <th>N</th>
	                            <td class="c_h"><span>内蒙古</span><span>宁夏</span></td>
	                          </tr>
	                          <tr>
	                            <th>Q</th>
	                            <td class="c_h"><span>青海</span></td>
	                          </tr>
	                          <tr>
	                            <th>S</th>
	                            <td class="c_h"><span>上海</span><span>山东</span><span>山西</span><span class="c_check">四川</span><span>陕西</span></td>
	                          </tr>
	                          <tr>
	                            <th>T</th>
	                            <td class="c_h"><span>台湾</span><span>天津</span></td>
	                          </tr>
	                          <tr>
	                            <th>X</th>
	                            <td class="c_h"><span>西藏</span><span>香港</span><span>新疆</span></td>
	                          </tr>
	                          <tr>
	                            <th>Y</th>
	                            <td class="c_h"><span>云南</span></td>
	                          </tr>
	                          <tr>
	                            <th>Z</th>
	                            <td class="c_h"><span>浙江</span></td>
	                          </tr>
	                        </table>
	                    </div>
	                </div>
	            </span>
	        </span>
	        <!--End 所在收货地区 End-->
	        <span class="fr">
	        	<c:if test="${empty sessionScope.loginUser}">
	        		<span class="fl">你好，请<a href="${ctx}/Login?action=toLogin">登录</a>&nbsp;|&nbsp;<a href="${ctx}/Register?action=toRegister" style="color:#ff4e00;">免费注册</a>&nbsp;</span>
	        	</c:if>
	        	<c:if test="${sessionScope.loginUser != null}">
	        		<span class="fl">欢迎您，${sessionScope.loginUser.userName}&nbsp;|&nbsp;<a href="Regist.html" style="color:#ff4e00;">我的订单</a>&nbsp;</span>
	        		
	        		<c:if test="${sessionScope.loginUser.type eq 1}">
	        			<span class="fl">&nbsp;|&nbsp;<a href="Regist.html" style="color:#ff4e00;">后台管理</a>&nbsp;</span>
	        		</c:if>
	        		
	        		<span class="fl">&nbsp;|&nbsp;<a href="${ctx}/Login?action=loginOut" style="color:#ff4e00;">注销</a>&nbsp;</span>
	        	</c:if>
	        </span>
	    </div>
	</div>
</body>
</html>