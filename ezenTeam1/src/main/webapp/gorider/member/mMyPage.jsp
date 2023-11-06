<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Go Rider!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
   <link href="/ezenTeam1/css/gorider/member.css" rel="stylesheet">
<!-- <style type="text/css">*{border:solid 1px red;}</style> -->
</head>
<body>
<!-- 전체렙 -->

 <div  class="wrap">
 <%@include file="mBHeader.jsp" %>
    <!-- 프로필영역 -->
   	<div class="myInfoBox"> <!-- 클릭시 수정페이지로. -->
		<div class="col30">
	   		<img class="mphoto" src="/ezenTeam1/gorider/member/file/default.png"/>

	   		<div onclick="onPhotomModal()" ><i class="fa-regular fa-pen-to-square"></i></div>
	   	</div>
	   	<div class="minfo">
			<div class="mi_mname"> </div>
		   	<div class="mi_memail"> </div>
	   	</div>
	</div>
    <!-- 메뉴영역 -->
   	<div class="myMenuBox">
   		<div class="disFlexStCent " onclick="onBoardList()">
   			<div><i class="fa-solid fa-bullhorn"> </i></div>
   			<div class="mtitle"> 공지/이벤트</div>
   		</div>

   		<div class="disFlexStCent" onclick="onUsedList()">
   			<div><i class="fa-solid fa-clock-rotate-left"> </i> </div>
   			<div> 이용기록</div>
   		</div>
   		<div class="disFlexStCent" onclick="onPayModal()" >
   			<div><i class="fa-regular fa-credit-card"> </i></div>
   			<div> 결제수단관리변경</div>
   		</div>


   	</div>
	<!-- 모달영역 -->
	<div class="modalwrap modalPhoto">
		<div class="modal">
			<form class="myform">
				<div class="previewBox">
                    <img class=preview alt="" src="/ezenTeam1/gorider/member/file/default.png">
				</div>
				<div class="minputBox">
					<input onchange="preview(this)" class="mphoto" name="mphoto" type="file" accept="image/*">
				</div>

				<div class="mbtnBox">
					<button onclick="onChangePhoto()" type="button">프로필변경</button>
					<button onclick="onModalClose('P')" type="button">취소</button>
				</div>
			</form>
		</div>

	</div>

	<!-- 모달영역 -->
	<div class="modalwrap2 ">
		<div class="modal2">
			<form class="myformpay">

				 <div class="usedPayBox">
					<div class="usedTitle"> 현재 사용중인 카드 정보</div>
					<div class="usedPay"><!-- 현재 결제카드 정보 --></div>
				</div>
				<div class="newPayBox">
					<div class="newTitle">변경할 결제카드번호를 입력해주세요</div>

					<div class="modalpayinfo">
						<input  maxlength="4"  name="mpayinfo1" class="mid cpay01" type="text"/>
			         	- <input  maxlength="4"  name="mpayinfo2" class="mid cpay02" type="text"/>
			         	- <input  maxlength="4"  name="mpayinfo3" class="mid cpay03" type="text"/>
			         	- <input  maxlength="4"  name="mpayinfo4" class="mid cpay04" type="text"/>
					</div>

				</div>
				<div class="mbtnBox">
					<button onclick="onChangePay()" type="button">저장</button>
					<button onclick="onModalClose('C')" type="button">취소</button>
				</div>
			</form>
		</div>

	</div>

   <!-- bottom -->
<%--    <%@include file="footer.jsp" %> --%>



</div>


<script src="/ezenTeam1/js/gorider/member/minfo.js"></script>
</body>
</html>