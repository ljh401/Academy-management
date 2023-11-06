<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- css 호출 -->
	<link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
	<link href="/ezenTeam1/css/gorider/rider.css" rel="stylesheet">
<!-- <style>*{border:solid 1px red;}</style> -->

</head>
<body>

	<!-- 라이더 내정보 수정 -->
	<div class="wrapR">  <!-- 라이더 프로필 전체구역 -->
	     <%@include file="rheader.jsp" %>
	
		<div class="rheaderwrapA backimg" >  <!-- 뒤로가기 버튼구역-->
            <div class="rtopbtn">
                <a href="/ezenTeam1/gorider/rider/rMyMenu.jsp">
               <i class="fa-solid fa-arrow-right-from-bracket fa-rotate-180" style="color: #ffffff;"></i></a>

            </div>


			<div class="rsubTitle01">입금정보</div>

		</div>
        <!-- 입금정보 출력 -->
		<div class="btypeBox">
            <div onclick="getIncomeInfo('AD')" class="btypeN">입금내역</div>
            <div onclick="getIncomeInfo('ND')" class="btypeE" >입금예정</div>
        </div>
        <div class="histroyBox scrollbox sheight"> <!-- list 전체 -->
            <ul class="hContBox">
             <li><span class="hTitleA">운행번호</span><span class="hContent">123456</span><li>
             <li><span class="hTitleA">운행일자</span><span class="hContent">2023-10-10 11:11:00</span><li>
             <li><span class="hTitleA">입금일자</span><span class="hContent"> 2023-10-15 12:10:10</span><li>
             <li><span class="hTitleA">운행수입</span><span class="hContent"> 4300 원</span><li>
            </ul>
            <ul class="hContBox">
             <li><span class="hTitleA">운행번호</span><span class="hContent">123456</span><li>
             <li><span class="hTitleA">운행일자</span><span class="hContent">2023-10-10 11:11:00</span><li>
             <li><span class="hTitleA">입금일자</span><span class="hContent"> 2023-10-15 12:10:10</span><li>
             <li><span class="hTitleA">운행수입</span><span class="hContent"> 4300 원</span><li>
            </ul>
            <ul class="hContBox">
             <li><span class="hTitleA">운행번호</span><span class="hContent">123456</span><li>
             <li><span class="hTitleA">운행일자</span><span class="hContent">2023-10-10 11:11:00</span><li>
             <li><span class="hTitleA">입금일자</span><span class="hContent"> 2023-10-15 12:10:10</span><li>
             <li><span class="hTitleA">운행수입</span><span class="hContent"> 4300 원</span><li>
            </ul>
            <ul class="hContBox">
             <li><span class="hTitleA">운행번호</span><span class="hContent">123456</span><li>
             <li><span class="hTitleA">운행일자</span><span class="hContent">2023-10-10 11:11:00</span><li>
             <li><span class="hTitleA">입금일자</span><span class="hContent"> 2023-10-15 12:10:10</span><li>
             <li><span class="hTitleA">운행수입</span><span class="hContent"> 4300 원</span><li>
            </ul>
            <ul class="hContBox">
             <li><span class="hTitleA">운행번호</span><span class="hContent">123456</span><li>
             <li><span class="hTitleA">운행일자</span><span class="hContent">2023-10-10 11:11:00</span><li>
             <li><span class="hTitleA">입금일자</span><span class="hContent"> 2023-10-15 12:10:10</span><li>
             <li><span class="hTitleA">운행수입</span><span class="hContent"> 4300 원</span><li>
            </ul>

        </div>

     <div class="footerR" >
       <div onclick="onMove('callList')" class="fbtn btn50 bgm02">콜리스트</div>
       <div onclick="onMove('goBack')" class="fbtn btn50 bgm02">돌아가기</div>
   </div>
	</div> <!-- 라이더 프로필 전체구역 e -->




	<script src="../../js/gorider/rider/rIncomeInfo.js"></script>

</body>
</html>


















