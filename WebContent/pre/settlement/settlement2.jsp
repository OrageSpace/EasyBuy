<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">
	var contentPath="${ctx}";
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/statics/css/style.css" />
<!--[if IE 6]>
    <script src="${ctx}/statics/js/common/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->
<title>尤洪</title>
</head>
<body>
	<!--End Menu End-->
	<div class="i_bg">
		<div class="content mar_20">
			<img src="${ctx}/statics/images/img2.jpg" />
		</div>

		<!--Begin 第二步：确认订单信息 Begin -->
		<div class="content mar_20">
			<div class="two_bg">
				<div class="two_t">
					<span class="fr"><a href="#">修改</a></span>商品列表
				</div>
				<table border="0" class="car_tab" style="width: 1110px;"
					cellspacing="0" cellpadding="0">
					<tr>
						<td class="car_th" width="550">商品名称</td>
						<td class="car_th" width="140">属性</td>
						<td class="car_th" width="150">购买数量</td>
						<td class="car_th" width="130">小计</td>
						<td class="car_th" width="140">返还积分</td>
					</tr>
					<c:forEach var="item" items="${sessionScope.cart.items}">
						<tr>
							<td>
								<div class="c_s_img">
									<img src="${ctx}/files/${item.product.fileName}" width="73"
										height="73" />
								</div> ${item.product.name}
							</td>
							<td align="center">颜色：灰色</td>
							<td align="center">1</td>
							<td align="center" style="color: #ff4e00;">￥${item.product.price}</td>
							<td align="center">26R</td>
						</tr>
					</c:forEach>
				</table>
				<div width="100%">
					<p></p>
					<div class="two_t">收货人信息</div>
					<div class="address" style="width: 1100px;">
						<div class="a_close">
							<a href="#"><img src="${ctx}/statics/images/a_close.png"></a>
						</div>
						<table border="0" class="add_t" align="center"
							style="width: 98%; margin: 10px auto;" cellspacing="0"
							cellpadding="0">
							<tbody>
								<tr>
									<td colspan="2" style="font-size: 14px; color: #ff4e00;">${addressList.get(0).remark}</td>
								</tr>
								<tr>
									<td align="right" width="120">收货人姓名：</td>
									<td>${sessionScope.loginUser.userName}</td>
								</tr>
								<tr>
									<td align="right">收货地址：</td>
									<td><select name="addressId" id="addressId">
											<c:forEach var="address" items="${requestScope.addressList}">
												<option value="${address.id}">${address.address}</option>
											</c:forEach>
											<option value="-1" onclick="jq('#newAddress').show()">添加地址</option>
									</select></td>
								</tr>
								<tr>
									<td align="right">详细地址：</td>
									<td>${requestScope.addressList.get(0).address}</td>
								</tr>
								<tr>
									<td align="right">手机：</td>
									<td>${sessionScope.loginUser.mobile}</td>
								</tr>
								<tr>
									<td align="right">电话：</td>
									<td>028-12345678</td>
								</tr>
								<tr>
									<td align="right">电子邮箱：</td>
									<td>${sessionScope.loginUser.email}</td>
								</tr>
								<tr>
									<td align="right">标志建筑：</td>
									<td>世外桃源</td>
								</tr>
							</tbody>
						</table>

						<p align="right">
							<a href="#" style="color: #ff4e00;">设为默认</a>&nbsp; &nbsp; &nbsp;
							&nbsp; <a href="#" style="color: #ff4e00;">编辑</a>&nbsp; &nbsp;
							&nbsp; &nbsp;
						</p>

					</div>

					<div class="mem_tit" class="two_t">
						<a href="#"><img src="${ctx}/statics/images/add_ad.gif"></a>
					</div>
					<table border="0" class="add_tab" class="peo_tab"
						style="width: 1100px; display: none;" cellspacing="0"
						cellpadding="0" id="newAddress">
						<tr>
							<td width="135" align="right">配送地区</td>
							<td colspan="3" style="font-family: '宋体';"><select
								name="country">
									<option value="1" selected="selected">中国</option>
							</select> <select name="city" id="city">
							</select> <select name="area" id="area">
							</select> <select name="county" id="county">
							</select> （必填）</td>
						</tr>
						<tr>
							<td align="right">收货人姓名</td>
							<td style="font-family: '宋体';"><input type="text"
								value="${sessionScope.loginUser.userName}" class="add_ipt">（必填）</td>
							<td align="right">电子邮箱</td>
							<td style="font-family: '宋体';"><input type="text"
								value="${sessionScope.loginUser.email}" class="add_ipt">（必填）</td>
						</tr>
						<tr>
							<td align="right">详细地址</td>
							<td style="font-family: '宋体';"><input type="text"
								value="世外桃源" class="add_ipt">（必填）</td>
							<td align="right">邮政编码</td>
							<td style="font-family: '宋体';"><input type="text"
								value="610000" class="add_ipt"></td>
						</tr>
						<tr>
							<td align="right">手机</td>
							<td style="font-family: '宋体';"><input type="text"
								value="${sessionScope.loginUser.mobile}" class="add_ipt">（必填）</td>
							<td align="right">电话</td>
							<td style="font-family: '宋体';"><input type="text"
								value="028-12345678" class="add_ipt"></td>
						</tr>
						<tr>
							<td align="right">标志建筑</td>
							<td style="font-family: '宋体';"><input type="text"
								value="世外桃源大酒店" class="add_ipt"></td>
							<td align="right">最佳送货时间</td>
							<td style="font-family: '宋体';"><input type="text" value=""
								class="add_ipt"></td>
						</tr>
						<tr>
							<td colspan="4">
								<p align="right" style="padding: 0px 30px;">
									<a href="#" class="add_b">取消</a>
								</p>
							</td>
						</tr>
					</table>
				</div>
				<div class="two_t">配送方式</div>
				<table border="0" class="car_tab" style="width: 1110px;"
					cellspacing="0" cellpadding="0">
					<tr>
						<td class="car_th" width="80"></td>
						<td class="car_th" width="200">名称</td>
						<td class="car_th" width="370">订购描述</td>
						<td class="car_th" width="150">费用</td>
						<td class="car_th" width="135">免费额度</td>
						<td class="car_th" width="175">保价费用</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox" name="ch"
							checked="checked" /></td>
						<td align="center" style="font-size: 14px;"><b>申通快递</b></td>
						<td>江、浙、沪地区首重为15元/KG，其他地区18元/KG，续重均为5-6元/KG， 云南地区为8元</td>
						<td align="center">￥15.00</td>
						<td align="center">￥0.00</td>
						<td align="center">不支持保价</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox" name="ch" /></td>
						<td align="center" style="font-size: 14px;"><b>城际快递</b></td>
						<td>运费固定</td>
						<td align="center">￥15.00</td>
						<td align="center">￥0.00</td>
						<td align="center">不支持保价</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox" name="ch" /></td>
						<td align="center" style="font-size: 14px;"><b>邮局平邮</b></td>
						<td>运费固定</td>
						<td align="center">￥15.00</td>
						<td align="center">￥0.00</td>
						<td align="center">不支持保价</td>
					</tr>
					<tr>
						<td colspan="6"><span class="fr"><label class="r_rad"><input
									type="checkbox" name="baojia" /></label><label class="r_txt">配送是否需要保价</label></span>
						</td>
					</tr>
				</table>

				<div class="two_t">支付方式</div>
				<ul class="pay">
					<li class="checked" onclick="changePaymentMethod(jq(this))">余额支付
						<div class="ch_img"></div>
					</li onclick="changePaymentMethod(jq(this))">
					<li onclick="changePaymentMethod(jq(this))">银行汇款
						<div class="ch_img"></div>
					</li onclick="changePaymentMethod(jq(this))">
					<li onclick="changePaymentMethod(jq(this))">货到付款
						<div class="ch_img"></div>
					</li>
					<li onclick="changePaymentMethod(jq(this))">支付宝
						<div class="ch_img"></div>
					</li>
				</ul>

				<div class="two_t">商品包装</div>
				<table border="0" class="car_tab" style="width: 1110px;"
					cellspacing="0" cellpadding="0">
					<tr>
						<td class="car_th" width="80"></td>
						<td class="car_th" width="490">名称</td>
						<td class="car_th" width="180">费用</td>
						<td class="car_th" width="180">免费额度</td>
						<td class="car_th" width="180">图片</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox" name="pack"
							checked="checked" /></td>
						<td><b style="font-size: 14px;">不要包装</b></td>
						<td align="center">￥15.00</td>
						<td align="center">￥0.00</td>
						<td align="center"></td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox" name="pack" /></td>
						<td><b style="font-size: 14px;">精品包装</b></td>
						<td align="center">￥15.00</td>
						<td align="center">￥0.00</td>
						<td align="center"><a href="#" style="color: #ff4e00;">查看</a></td>
					</tr>
				</table>

				<div class="two_t">祝福贺卡</div>
				<table border="0" class="car_tab" style="width: 1110px;"
					cellspacing="0" cellpadding="0">
					<tr>
						<td class="car_th" width="80"></td>
						<td class="car_th" width="490">名称</td>
						<td class="car_th" width="180">费用</td>
						<td class="car_th" width="180">免费额度</td>
						<td class="car_th" width="180">图片</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox" name="wish"
							checked="checked" /></td>
						<td><b style="font-size: 14px;">不要贺卡</b></td>
						<td align="center">￥15.00</td>
						<td align="center">￥0.00</td>
						<td align="center"></td>
					</tr>
					<tr>
						<td align="center" style="border-bottom: 0; padding-bottom: 0;"><input
							type="checkbox" name="wish" /></td>
						<td style="border-bottom: 0; padding-bottom: 0;"><b
							style="font-size: 14px;">祝福贺卡</b></td>
						<td align="center" style="border-bottom: 0; padding-bottom: 0;">￥15.00</td>
						<td align="center" style="border-bottom: 0; padding-bottom: 0;">￥0.00</td>
						<td align="center" style="border-bottom: 0; padding-bottom: 0;"><a
							href="#" style="color: #ff4e00;">查看</a></td>
					</tr>
					<tr valign="top">
						<td align="center"></td>
						<td colspan="4"><span class="fl"><b
								style="font-size: 14px;">祝福语：</b></span> <span class="fl"><textarea
									class="add_txt" style="width: 860px; height: 50px;"></textarea></span>
						</td>
					</tr>
				</table>

				<div class="two_t">其他信息</div>
				<table border="0" class="car_tab" style="width: 1110px;"
					cellspacing="0" cellpadding="0">
					<tr>
						<td width="145" align="right" style="padding-right: 0;"><b
							style="font-size: 14px;">使用红包：</b></td>
						<td><span class="fl"
							style="margin-left: 50px; margin-right: 10px;">选择已有红包</span> <select
							class="jj" name="city">
								<option value="0" selected="selected">请选择</option>
								<option value="1">50元</option>
								<option value="2">30元</option>
								<option value="3">20元</option>
								<option value="4">10元</option>
						</select> <span class="fl" style="margin-left: 50px; margin-right: 10px;">或者输入红包序列号</span>
							<span class="fl"><input type="text" value="" class="c_ipt"
								style="width: 220px;" /> <span class="fr"
								style="margin-left: 10px;"><input type="submit"
									value="验证红包" class="btn_tj" /></span></td>
					</tr>
					<tr valign="top">
						<td align="right" style="padding-right: 0;"><b
							style="font-size: 14px;">订单附言：</b></td>
						<td style="padding-left: 0;"><textarea class="add_txt"
								style="width: 860px; height: 50px;"></textarea></td>
					</tr>
					<tr>
						<td align="right" style="padding-right: 0;"><b
							style="font-size: 14px;">缺货处理：</b></td>
						<td><label class="r_rad"><input type="checkbox"
								name="none" checked="checked" /></label><label class="r_txt"
							style="margin-right: 50px;">等待所有商品备齐后再发</label> <label
							class="r_rad"><input type="checkbox" name="none" /></label><label
							class="r_txt" style="margin-right: 50px;">取下订单</label> <label
							class="r_rad"><input type="checkbox" name="none" /></label><label
							class="r_txt" style="margin-right: 50px;">与店主协商</label></td>
					</tr>
				</table>

				<table border="0" style="width: 900px; margin-top: 20px;"
					cellspacing="0" cellpadding="0">
					<tr height="70">
						<td align="right"><b style="font-size: 14px;">应付款金额：<span
								style="font-size: 22px; color: #ff4e00;">￥${sessionScope.cart.sumCost}</span></b></td>
					</tr>
					<tr height="70">
						<td align="right"><a href="#" onclick="settlement3();"><img
								src="${ctx}/statics/images/btn_sure.gif" /></a></td>
					</tr>
				</table>



			</div>
		</div>
		<!--End 第二步：确认订单信息 End-->
		<!--Begin Footer Begin -->
	</div>

</body>
<script type="text/javascript"
	src="${ctx}/statics/js/common/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/menu.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/num.js">
    	var jq = jQuery.noConflict();
</script>
<script type="text/javascript" src="${ctx}/statics/js/common/n_nav.js"></script>

<script type="text/javascript" src="${ctx}/statics/js/common/select.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/shade.js"></script>
<script type="text/javascript" src="${ctx}/statics/js/common/city.js"></script>
<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
