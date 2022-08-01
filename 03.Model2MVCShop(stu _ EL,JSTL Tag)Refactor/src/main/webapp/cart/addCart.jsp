<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<title>Insert title here</title>
<script type="text/javascript" src="../javascript/calendar.js">
</script>
</head>

<body>

	<form name="addPurchase" method="post" action="/addPurchase.do">

		<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="15" height="37"><img src="/images/ct_ttl_img01.gif" width="15" height="37"></td>
				<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="93%" class="ct_ttl01">주문할 상품들</td>
							<td width="20%" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
				<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37" /></td>
			</tr>
		</table>

		<input type="hidden" name="prodNo" value="${ productVO.prodNo }" />

		<table width="600" border="0" cellspacing="0" cellpadding="0"
			align="center" style="margin-top: 13px;">
			<c:forEach var="i" begin="0" end="${ size-1 }" step="1">
				<tr class="ct_list_pop" id="divDataId">
					<td align="center"><input type="checkbox" onclick="ischecked()" id="deleteCheckBox" name="deleteCheckBox" value="${ list[i].prod_no }"></td>
					<td></td>
					<td align="left"><img height="250" width="250" src="/images/uploadFiles/${ list[i].image }" /></td>
					<td></td>
					<td align="left">${ list[i].prod_name }</td>
					<td></td>
					<td align="left"><input type="button" value="-" class="btn_minus" onclick='count("minus, ${ i }")'>
					<b id="result">${ list[i].amount }</b>
					<input type="hidden" id="amount" name="amount" value="${ list[i].amount }">
					<input type="hidden" id="addPurchaseCheckBox" name="addPurchaseCheckBox" value="${ list[i].prod_no }">
					<input type="hidden" id="handleAmount" name="handleAmount" value="${ list[i].prod_amount }">
					<input type="button" value="+" class="btn_plus" onclick='count("plus, ${ i }")'>
					<b id="limit"></b></td>
					<td></td>
					<td align="left"><b id="price">${ list[i].price }</b>원 * <b>${ list[i].amount }</b>개 = <b id="totalPrice">${ list[i].price * list[i].amount }</b>원</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>
			</c:forEach>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
			<tr>
				<td width="53%"></td>
				<td align="center">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="17" height="23"><img src="/images/ct_btnbg01.gif" width="17" height="23" /></td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;"><a href="javascript:fncAddPurchase();">결제완료</a></td>
							<td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23" /></td>
							<td width="30"></td>
							<td width="17" height="23"><img src="/images/ct_btnbg01.gif" width="17" height="23" /></td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;"><a href="javascript:history.go(-1)">취소</a></td>
							<td width="14" height="23"><img src="/images/ct_btnbg03.gif" width="14" height="23" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>