<%@page import="com.model2.mvc.common.util.CommonUtil"%>
<%@page import="com.model2.mvc.service.domain.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
function btnClick() {
	if(confirm('정말 삭제하시겠습니까?')){
		document.detailForm.submit();
	}
}

function count(type) {
	var resultNumber = document.getElementById('result');
	var number = resultNumber.innerText;
	
	if(type=='plus'){
		number = parseInt(number) +1;
		alert('plus : ' + number);
	}else if(type=='minus'){
		number = parseInt(number) -1;
		if(number==0){
			number = 1;
		}
		alert('minus : ' + number);
	}

	document.getElementById('amount').innerText = number;
	var price = document.getElementById('price').innerText;
	document.getElementById('totalPrice').innerText = parseInt(number) * parseInt(price);
	alert(parseInt(number) * parseInt(price));

	resultNumber.innerText = number;
}

</script>

<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="/css/admin.css" type="text/css">
</head>

<body bgcolor="#ffffff" text="#000000">
	<div style="width: 98%; margin-left: 10px;">
		<form name="detailForm" action="/deleteCart.do" method="post">
			<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif" width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">${ sessionScope.user.role } 장바구니</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37" /></td>
				</tr>
			</table>



			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
				<tr>
					<td colspan="11">
						<a href=""><br>전체 선택 개수/${ count }</a>
						<input type="button" value="선택 삭제" onclick="btnClick()">
					</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품이미지</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품정보</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">수량</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">가격</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
					<!-- list시작 -->
					<c:set var="size" value="${ fn:length(list) }"/>
					<c:if test="${ count > 0 }">
						<c:forEach var="i" begin="0" end="${ size-1 }" step="1">
							<tr class="ct_list_pop">
								<td align="center">
								<input type="checkbox" name="deleteCheckBox" value="${ list[i].prod_no }"></td>
								<td></td>
								<td align="left"><img src="/images/uploadFiles/${ list[i].image }"/></td>
								<td></td>
								<td align="left">${ list[i].prod_name }</td>
								<td></td>
								<td align="left">
									<input type="button" value="-" class="btn_minus" onclick='count("minus")'>
									<b id="result">${ list[i].amount }</b>
									<input type="button" value="+" class="btn_plus" onclick='count("plus")'>
								</td>
								<td></td>
								<td align="left">
								<b id="price">${ list[i].price }</b>원 * 
								<b id="amount">${ list[i].amount }</b>개 = 
								<b id="totalPrice">${ list[i].price * list[i].amount }</b>원
								</td>
							</tr>
							<tr>
								<td colspan="11" bgcolor="D6D7D6" height="1"></td>
							</tr>
						</c:forEach>
					</c:if>
			</table>



			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
				<tr>
					<td align="center"></td>
				</tr>
			</table>

		</form>

	</div>
</body>
</html>
