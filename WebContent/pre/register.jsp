<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="css/style.css" />
    <!--[if IE 6]>
    <script src="js/iepng.js" type="text/javascript"></script>
        <script type="text/javascript">
           EvPNG.fix('div, ul, img, li, input, a'); 
        </script>
    <![endif]-->    
<title>易买网--注册页面</title>
</head>
<body>  
<!--Begin Header Begin-->
<%@include file="../common/pre/header.jsp"%>
<!--End Header End--> 
<!--Begin Login Begin-->
<div class="log_bg">	
    <div class="top">
        <div class="logo"><a href="Index.html"><img src="${ctx}/statics/images/logo.png" /></a></div>
    </div>
	<div class="regist">
    	<div class="log_img"><img src="${ctx}/statics/images/l_img.png" width="611" height="425" /></div>
		<div class="reg_c">
        	<form>
            <table border="0" style="width:420px; font-size:14px; margin-top:20px;" cellspacing="0" cellpadding="0">
              <tr height="50" valign="top">
              	<td width="95">&nbsp;</td>
                <td>
                	<span class="fl" style="font-size:24px;">注册</span>
                    <span class="fr">已有商城账号，<a href="${ctx}/Login?action=toLogin" style="color:#ff4e00;">我要登录</a></span>
                </td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;用户名 &nbsp;</td>
                <td><input type="text" value="" name="loginName" id="loginName" class="l_user" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;密码 &nbsp;</td>
                <td><input type="password" value="" name="password" id="password" class="l_pwd" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;确认密码 &nbsp;</td>
                <td><input type="password" value="" id="confirmPwd" name="confirmPwd" class="l_pwd" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;邮箱 &nbsp;</td>
                <td><input type="text" value="" id="email" name="email" class="l_email" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;手机 &nbsp;</td>
                <td><input type="text" value="" id="mobile" name="moblie" class="l_tel" /></td>
              </tr>
              <tr height="50">
                <td align="right"><font color="#ff4e00">*</font>&nbsp;真实姓名 &nbsp;</td>
                <td><input type="text" value="" id="userName" name="userName" class="l_mem" /></td>
              </tr>
              <tr height="50">
                <td align="right">&nbsp;性别 &nbsp;</td>
                <td>
                	<input type="radio" value="1"  checked="checked" name="sex" class="sex" />男&nbsp;&nbsp;&nbsp;
                	<input type="radio" value="2"  name="sex" class="sex" />女
                </td>
              </tr>
              <tr height="50">
                <td align="right">身份证号码 &nbsp;</td>
                <td><input type="text" value="" id="identiryCode" name="identityCode" class="l_num" /></td>
              </tr>
              <tr height="50">
                <td align="right"> <font color="#ff4e00">*</font>&nbsp;验证码 &nbsp;</td>
                <td>
                    <input type="text" value="" class="l_ipt" />
                    <a href="#" style="font-size:12px; font-family:'宋体';">换一张</a>
                </td>
              </tr>
              <tr>
              	<td>&nbsp;</td>
                <td style="font-size:12px; padding-top:20px;">
                	<span style="font-family:'宋体';" class="fl">
                    	<label class="r_rad"><input type="checkbox" checked="checked"/></label><label class="r_txt">我已阅读并接受《用户协议》</label>
                    </span>
                </td>
              </tr>
              <tr height="60">
              	<td>&nbsp;</td>
                <td><input type="button" value="立即注册" class="log_btn" onclick="register();"/></td>
              </tr>
            </table>
            </form>
        </div>
    </div>
</div>
<!--End Login End--> 
<!--Begin Footer Begin-->
<%@ include file="../common/pre/footer_desc.jsp" %>
<!--End Footer End -->    
<script src="${ctx}/statics/js/register/register.js"></script>
</body>


<!--[if IE 6]>
<script src="//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js"></script>
<![endif]-->
</html>
