<%@ page contentType="text/html; charset=euc-kr" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<c:forEach var="i" begin="0" end="${ fn:length(list) -1 }" step="1">
<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td>${ list[i].purchaseProd.prodNo }<%-- ${ purchaseVO.purchaseProd.prodNo } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td>${ list[i].buyer.userId }<%-- ${ purchaseVO.buyer.userId } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>${ (fn:trim(list[i].paymentOption) == '1')?"���ݱ���":"�ſ뱸��" }<%-- ${ (purchaseVO.paymentOption == '1')?"���ݱ���":"�ſ뱸��" } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td>${ list[i].receiverName }<%-- ${ purchaseVO.receiverName } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td>${ list[i].receiverPhone }<%-- ${ purchaseVO.receiverPhone } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td>${ list[i].divyAddr }<%-- ${ purchaseVO.divyAddr } --%></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td>${ list[i].divyRequest }<%-- ${ purchaseVO.divyRequest } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td>${ list[i].divyDate }<%-- ${ purchaseVO.divyDate } --%></td>
		<td></td>
	</tr>
	<tr>
		<td>����</td>
		<td>${ list[i].amount }<%-- ${ purchaseVO.amount } --%></td>
		<td></td>
	</tr>
</table>
</c:forEach>
</form>

</body>
</html>