<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>

    <link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
        <link href="/ezenTeam1/css/gorider/rider.css" rel="stylesheet">
<!-- <style>*{border:solid 1px red;}</style> -->
</head>
<body> <!-- 라이더 메뉴 페이지 -->
	<div class="wrapR ">
     <%@include file="rheader.jsp" %>
        <div class="rheaderwrapA ">

            <div class="rmTitle">나의 메뉴</div>

        </div>

<!--         <div class="hbutton">상단고정버튼</div> -->

        <div class="rcontBox scrollbox">
	        <div class="rmyBtnBox">
	            <div class="rmyBtn"> <a href="/ezenTeam1/gorider/rider/rProfil.jsp"> 내정보수정 </a> </div>
	            <div class="rmyBtn"> <a href="/ezenTeam1/gorider/rider/rBikeNumChange.jsp"> 차량번호 변경 </a></div>
	            <div class="rmyBtn"> <a href="/ezenTeam1/gorider/rider/rBankChange.jsp"> 계좌번호 변경 </a></div>
	            <div class="rmyBtn"> <a href="/ezenTeam1/gorider/rider/rDriveRecord.jsp">주행기록 </div>
	            <div class="rmyBtn"> <a href="/ezenTeam1/gorider/rider/rIncomeInfo.jsp">입금내역 </div>
	            <div class="rmyBtn"> <a href="/ezenTeam1/gorider/rider/rBoardList.jsp">공지사항/이벤트정보 </div>

            </div>

        </div>
	 <div class="footerR" >
       <div onclick="onMove('callList')" class="fbtn btn50 bgm02">콜리스트</div>
       <div onclick="onMove('goBack')" class="fbtn btn50 bgm02">돌아가기</div>
   </div>
    </div>

    <script src="../../js/gorider/rider/rAfterWork.js"></script>

</body>
</html>