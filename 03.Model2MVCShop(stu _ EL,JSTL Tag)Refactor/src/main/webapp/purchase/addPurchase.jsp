<%@ page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

다음과 같이 구매가 되었습니다.

<c:forEach var="i" begin="0" end="${ fn:length(list) -1 }" step="1">
<table border=1>
	<tr>
		<td>물품번호</td>
		<td>${ list[i].purchaseProd.prodNo }<%-- ${ purchaseVO.purchaseProd.prodNo } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td>${ list[i].buyer.userId }<%-- ${ purchaseVO.buyer.userId } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>${ (fn:trim(list[i].paymentOption) == '1')?"현금구매":"신용구매" }<%-- ${ (purchaseVO.paymentOption == '1')?"현금구매":"신용구매" } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${ list[i].receiverName }<%-- ${ purchaseVO.receiverName } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${ list[i].receiverPhone }<%-- ${ purchaseVO.receiverPhone } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${ list[i].divyAddr }<%-- ${ purchaseVO.divyAddr } --%></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${ list[i].divyRequest }<%-- ${ purchaseVO.divyRequest } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>${ list[i].divyDate }<%-- ${ purchaseVO.divyDate } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>수량</td>
		<td>${ list[i].amount }<%-- ${ purchaseVO.amount } --%></td>
		<td></td>
	</tr>
</table>
</c:forEach>
</form>

</body>
</html>