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
  <script type="text/javascript" src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
</head>
<body>
	<div class="soubg">
		<div class="sou">
	    	<!--Begin 所在收货地区 Begin-->
	    	<span class="s_city_b">
	        	<span class="fl">送货至：</span>
	            <span class="s_city">
	            	<span id="cityName">四川</span>
	                <div class="s_city_bg">
	                	<div class="s_city_t"></div>
	                    <div class="s_city_c">
	                    	<h2>请选择所在的收货地区</h2>
	                        <table border="0" class="c_tab" style="width:235px; margin-top:10px;" cellspacing="0" cellpadding="0">
	                          <tr>
	                            <th>A</th>
	                            <td class="c_h"><span id="city_340000">安徽</span><span id="city_820000">澳门</span></td>
	                          </tr>
	                          <tr>
	                            <th>B</th>
	                            <td class="c_h"><span id="city_110000">北京</span></td>
	                          </tr>
	                          <tr>
	                            <th>C</th>
	                            <td class="c_h"><span id="city_500000">重庆</span></td>
	                          </tr>
	                          <tr>
	                            <th>F</th>
	                            <td class="c_h"><span id="city_350000">福建</span></td>
	                          </tr>
	                          <tr>
	                            <th>G</th>
	                            <td class="c_h"><span id="city_440000">广东</span><span id="city_450000">广西</span><span id="city_520000">贵州</span><span id="city_620000">甘肃</span></td>
	                          </tr>
	                          <tr>
	                            <th>H</th>
	                            <td class="c_h"><span id="city_130000">河北</span><span id="city_410000">河南</span><span id="city_230000">黑龙江</span><span id="city_460000">海南</span><span id="city_420000">湖北</span><span id="city_430000">湖南</span></td>
	                          </tr>
	                          <tr>
	                            <th>J</th>
	                            <td class="c_h"><span id="city_320000">江苏</span><span id="city_220000">吉林</span><span id="city_360000">江西</span></td>
	                          </tr>
	                          <tr>
	                            <th>L</th>
	                            <td class="c_h"><span id="city_210000">辽宁</span></td>
	                          </tr>
	                          <tr>
	                            <th>N</th>
	                            <td class="c_h"><span id="city_150000">内蒙古</span><span id="city_640000">宁夏</span></td>
	                          </tr>
	                          <tr>
	                            <th>Q</th>
	                            <td class="c_h"><span id="city_630000">青海</span></td>
	                          </tr>
	                          <tr>
	                            <th>S</th>
	                            <td class="c_h"><span id="city_310000">上海</span><span id="city_370000">山东</span><span id="city_">山西</span><span class="c_check" id="city_510000">四川</span><span id="city_610000">陕西</span></td>
	                          </tr>
	                          <tr>
	                            <th>T</th>
	                            <td class="c_h"><span id="city_710000">台湾</span><span id="city_120000">天津</span></td>
	                          </tr>
	                          <tr>
	                            <th>X</th>
	                            <td class="c_h"><span id="city_540000">西藏</span><span id="city_810000">香港</span><span id="city_650000">新疆</span></td>
	                          </tr>
	                          <tr>
	                            <th>Y</th>
	                            <td class="c_h"><span id="city_530000">云南</span></td>
	                          </tr>
	                          <tr>
	                            <th>Z</th>
	                            <td class="c_h"><span id="city_330000">浙江</span></td>
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
	        		<span class="fl">欢迎您，${sessionScope.loginUser.userName}&nbsp;|&nbsp;<a href="${ctx}/Center?action=toPersonalCenter" style="color:#ff4e00;">个人中心</a>&nbsp;</span>
	        		
	        		<c:if test="${sessionScope.loginUser.type eq 1}">
	        			<span class="fl">&nbsp;|&nbsp;<a href="Regist.html" style="color:#ff4e00;">后台管理</a>&nbsp;</span>
	        		</c:if>
	        		
	        		<span class="fl">&nbsp;|&nbsp;<a href="${ctx}/Login?action=loginOut" style="color:#ff4e00;">注销</a>&nbsp;</span>
	        	</c:if>
	        </span>
	    </div>
	</div>
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<script>
	$("#city_"+returnCitySN['cid']).addClass("c_check").parent().parent().siblings().children("td").children("span").removeClass("c_check");
	$("#cityName").text(returnCitySN['cname']);
</script>
</body>
</html>