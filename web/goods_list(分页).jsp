<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>商品列表</title>
</head>
<link rel="stylesheet" type="text/css" href="css/public.css">
<style type="text/css">
	table {
		border-collapse: collapse;
	}

	/*商品列表第1列*/
	.col1 {
		padding-top: 5px;
		border-top: 1px dashed #666666;
		text-indent: 40px
	}

	/*商品列表第2列*/
	.col2 {
		padding-top: 5px;
		border-top: 1px dashed #666666;
		text-align: right;
	}

	/*商品列表第3列*/
	.col3 {
		padding-top: 5px;
		border-top: 1px dashed #666666;
		text-align: center;
	}
</style>
<body>
<table width="100%" border="0" align="center">
  <tr>
    <td width="616"><img src="images/list.jpg" align="absmiddle" /></td>
    <td width="734" align="right"><img src="images/mycar1.jpg" align="absmiddle" /> <a href="Cart.html">&nbsp;购物车</a> | <a href="zhanghu.html">我的帐户</a> | <a href="Catalog.html">商品列表</a></td>
  </tr>
</table>
<hr width="100%" />
<div class="text3" align="center">请从商品列表中选择您喜爱的商品</div>
<br>
<table width="100%" border="0" align="center">
  <tr bgcolor="#b4c8ed">
    <th>商品名称</th>
    <th width="5%" >商品价格</th>
    <th width="15%" >添加到购物车</th>
  </tr>
  <tr bgcolor='#ffffff'>
    <td class="col1"><a href="Product.html?productId=#">酷耶（Cooyes） i5四核/GTX1050ti独显4G/台式机电脑主机整机全套组装家用办公游戏 GTX750独显主机+23英寸显示器</a></td>
    <td class="col2">￥3399.00</td>
    <td class="col3"><a href="AddCart.html?productId=#">添加到购物车</a></td>
  </tr> 
   <tr bgcolor='#edf8ff'>
    <td class="col1"><a href="Product.html?productId=#">酷耶（Cooyes） i5四核/GTX1050ti独显4G/台式机电脑主机整机全套组装家用办公游戏 GTX750独显主机+23英寸显示器</a></td>
    <td class="col2">￥3399.00</td>
    <td class="col3"><a href="AddCart.html?productId=#">添加到购物车</a></td>
  </tr>
</table>
<hr/>
<div align="center">
<ul class="pagination">
  <li><a href="#">«</a></li>
  <li><a href="#">1</a></li>
  <li><a class="active" href="#">2</a></li>
  <li><a href="#">3</a></li>
  <li><a href="#">4</a></li>
  <li><a href="#">5</a></li>
  <li><a href="#">6</a></li>
  <li><a href="#">7</a></li>
  <li><a href="#">»</a></li>
</ul>
</div>

<%@include file="footer.jsp"%>

</body>
</html>
