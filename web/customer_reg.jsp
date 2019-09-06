
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>客户注册</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        .boder {
            border: 1px solid #5B96D0;
        }

        .col1 {
            background-color: #A6D2FF;
            text-align: right;
            padding-right: 10px;
            border: 1px solid #5B96D0;
            line-height: 50px;
        }

        .col2 {
            padding-left: 10px;
            border: 1px solid #5B96D0;
            line-height: 50px;
        }

        .textfield {
            height: 20px;
            width: 200px;
            border: 1px solid #999999;
            text-align: left;
            font-size: medium;
            line-height: 50px;
        }
    </style>

  <script>
      function verify(myform) {
          var erroMsg="";
          if(myform.userid.value==""){
              erroMsg+="客户账号不能为空！\n";
          }
          if(myform.name.value==""){
              erroMsg+="客户姓名不能为空！\n";
          }
          if(myform.password.value!=myform.password2.value){
              erroMsg+="两次密码输入不一致！\n";
          }

          var pattern = /^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$/;

          if(!pattern.test(myform.birthday.value)){
              erroMsg+="日期格式不正确！\n";
          }

          if(erroMsg==""){
              return true;
          }else{
              alert(erroMsg);
              return false;

          }



      }

  </script>
</head>

<body>
<div><img src="images/reg.jpg" align="absmiddle" /></div>
<br>
<hr width="100%" />
<div class="text3" align="center">请填写下列信息</div>
<br>

<%--
 Eorror msg
 --%>

<ul>
   <c:forEach var="error" items="${errors}">
     <li class="error">${error} </li>
   </c:forEach>

</ul>


<form action="controller" method="post" onsubmit="return verify(this)">
<table width="60%" border="0" align="center" class="boder">
  <tr>
    <td width="35%" height="27" class="col1">客户账号：</td>
    <td class="col2"><input type="text" name="userid" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">客户姓名：</td>
    <td class="col2"><input type="text" name="name" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">客户密码：</td>
    <td class="col2"><input type="password" name="password" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">再次输入密码：</td>
    <td class="col2"><input type="password" name="password2" />*</td>
  </tr>
  <tr>
    <td height="27" class="col1">出生日期：</td>
    <td  class="col2"><input type="text" name="birthday" />*
      格式（YYYY-MM-DD）</td>
  </tr>
  <tr>
    <td height="27" class="col1">通讯地址：</td>
    <td class="col2"><input type="text" name="address"  /></td>
  </tr>
  <tr>
    <td height="27" class="col1">电话号码：</td>
    <td class="col2"><input type="text" name="phone"   /></td>
  </tr>
</table>
<br>
<div align="center">
  <input type="image" src="images/submit_button.jpg" />
</div>

  <input type="hidden" name="action" value="reg">
</form>

<%@include file="footer.jsp"%>
</html>
