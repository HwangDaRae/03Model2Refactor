<%@page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
//��ü���� üũ�ڽ� Ŭ���� ��� üũ�ڽ� ���õ�
function selectAll(){
	var arrayCheckBox = document.getElementsByName("deleteCheckBox");
	if(document.getElementById("allDeleteCheckBox").checked == true){
	    for(var i=0;i<arrayCheckBox.length;i++) {
	    	document.getElementsByName("deleteCheckBox")[i].checked = true;
	    	document.getElementById('checkCount').innerText = arrayCheckBox.length;
	    }
	}else{
	    for(var i=0;i<arrayCheckBox.length;i++) {
	    	document.getElementsByName("deleteCheckBox")[i].checked = false;
	    	document.getElementById('checkCount').innerText = 0;
	    }
	}
}

//��������
function count(type, i) {
	alert(type);
	//ȭ�鿡 ���̴� ����
	var number = document.getElementById('result').innerText;
	//��ǰ������
	var handleAmount = document.getElementById('handleAmount').value;
	
	if(type=='plus'){
		if(number < handleAmount){
			number = parseInt(number) +1;
		}else if(number == handleAmount){
			document.getElementById('limit').innerText = '���̻� �����Ͻ� �� �����ϴ�';
		}
	}else if(type=='minus'){
		if(number==0){
			number = 1;
		}else if(number!=0){
			number = parseInt(number) -1;
		}
	}

	var price = document.getElementById('price').innerText;
	document.getElementById('totalPrice').innerText = parseInt(number) * parseInt(price);
	document.getElementById('result').innerText = number;
	document.getElementById('amount').value = number;
}

//üũ�� ��ǰ����
function ischecked(){
	var arrayCheckBox = document.getElementsByName("deleteCheckBox");
	var checkCount = 0;
	for(var i=0;i<arrayCheckBox.length;i++) {
		if(arrayCheckBox[i].checked){
			checkCount++;
		}
    }

	if(parseInt(checkCount)==parseInt(arrayCheckBox.length)){
		document.getElementById("allDeleteCheckBox").checked = true;
	}else{
		document.getElementById("allDeleteCheckBox").checked = false;
	}
	
	document.getElementById('checkCount').innerText = checkCount;
}

function deleteBtnClick(){
	if(confirm('���� �����Ͻðڽ��ϱ�?')){
		document.detailForm.action="/deleteCart.do";
		document.detailForm.submit();
	}
}

function cartTransaction(){
	alert('a');		
	document.detailForm.submit();
}

</script>

<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="/css/admin.css" type="text/css">
</head>

<body bgcolor="#ffffff" text="#000000">
	<div style="width: 98%; margin-left: 10px;">
		<form name="detailForm" action="/addPurchase.do" method="post">
			<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif" width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">${ sessionScope.user.role } ��ٱ���</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37" /></td>
				</tr>
			</table>



			<table id="dataTable" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
				<tr>
					<td colspan="11">
						<a href="">
							<input type="checkbox" id="allDeleteCheckBox" name="allDeleteCheckBox" value="selectAll" onclick='selectAll()'>��ü����&nbsp;&nbsp;
							<b id="checkCount">0</b>&nbsp;/&nbsp;${ count }</a>&nbsp;&nbsp;
						<input type="button" value="���� ����" onclick='deleteBtnClick()'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="����" onclick="cartTransaction()">
					</td>

				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">��ǰ�̹���</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">��ǰ����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">����</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
					<!-- list���� -->
					<c:set var="size" value="${ fn:length(list) }"/>
					<c:if test="${ count > 0 }">
						<div>
						<c:forEach var="i" begin="0" end="${ size-1 }" step="1">
							<tr class="ct_list_pop" id="divDataId">
								<td align="center">
								<input type="checkbox" onclick="ischecked()" id="deleteCheckBox" name="deleteCheckBox" value="${ list[i].prod_no }"></td>
								<td></td>
								<td align="left"><img height="250" width="250" src="/images/uploadFiles/${ list[i].image }"/></td>
								<td></td>
								<td align="left">${ list[i].prod_name }</td>
								<td></td>
								<td align="left">
									<input type="button" value="-" class="btn_minus" onclick='count("minus, ${ i }")'>
									<b id="result">${ list[i].amount }</b>
									<input type="hidden" id="amount" name="amount" value="${ list[i].amount }">
									<input type="hidden" id="addPurchaseCheckBox" name="addPurchaseCheckBox" value="${ list[i].prod_no }">
									<input type="hidden" id="handleAmount" name="handleAmount" value="${ list[i].prod_amount }">
									<input type="button" value="+" class="btn_plus" onclick='count("plus, ${ i }")'>
									<b id="limit"></b>
								</td>
								<td></td>
								<td align="left">
								<b id="price">${ list[i].price }</b>�� * 
								<b>${ list[i].amount }</b>�� = 
								<b id="totalPrice">${ list[i].price * list[i].amount }</b>��
								</td>
							</tr>
							<tr>
								<td colspan="11" bgcolor="D6D7D6" height="1"></td>
							</tr>
						</c:forEach>
						</div>
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
