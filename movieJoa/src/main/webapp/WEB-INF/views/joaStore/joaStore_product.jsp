<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품페이지</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/joaStore.css">
<script>
function count(type)  {
  // 결과를 표시할 element
  const countElement = document.getElementById('count');
  
  // 현재 화면에 표시된 값
  let number = countElement.innerText;
  
  // 더하기/빼기
  if(type == 'plus') {
	  if(number < 10){
    number = parseInt(number) + 1;
	  }
  }else if(type == 'minus')  {
	  if(number > 1){
    number = parseInt(number) - 1;
	  }
  }
  
  // 결과 출력
  countElement.innerText = number;
}
</script>
<style>
.product_right_price{
float:right;
}

.product_info{
	max-width:1100px;
 	margin:auto;
}
</style>
</head>
<body>
<c:import url="../header.jsp"></c:import>
<c:import url="joaStore_category.jsp"></c:import>
	
	<div class="product_main">
		<div class="product_container">
		<h1>MJOA콤보</h1>
		<hr color="black" size="2px">
		</div>
		<div class="product_space">
			<div class="product_img">
				<img src="/movieJoa/img/joaStore_img/combo1.jpg" alt="콤보" width="430" height="450">
			</div>
			<div class="product_select">
				<b>9,000원</b>
				<hr color="#dcdcdc"/>
				<table>
					<tr>
						<td>상품구성</td><td>팝콘(L)1+탄산음료(M)2</td>
					</tr>
					<tr>
						<td>유효기간</td><td>구매일로부터 6개월 이내</td>
					</tr>
					<tr>
						<td>원산지</td><td>옥수수:미국산</td>
					</tr>
				</table>
				<hr color="#dcdcdc"/>
				<span>MJOA콤보<br></span>
				<div class="product_count">
					<input type='button' onclick='count("minus")' value='-'/>
					<div id='count'>0</div>
					<input type='button' onclick='count("plus")' value='+'/>
				</div>
				<hr>
				<span>총 구매금액</span><span class="product_right_price">5,000원</span>
				<div>
					<a href="joaStoreCart.do"><input type="button" value="장바구니"></a>
					<a href="joaStorePay.do"><input type="button" value="구매하기"></a>
				</div>			
			</div>
		</div>
		<hr color="black" size="2px" width="1100px">		
		<div class="product_info">	
		</div>
	</div>
<c:import url="../footer.jsp"></c:import>
</body>
</html>