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

</head>
<body> <!-- 라이더 차량번호 , 계좌번호 수정페이지 -->

	<div class="wrapR"> <!-- 라이더 차량번호수정 전체 -->
			

     <%@include file="rheader.jsp" %>
		<div class="rheaderwrapA backimg" >  <!-- 뒤로가기 버튼구역-->
            <div class="rtopbtn">
                <a href="/ezenTeam1/gorider/rider/rMyMenu.jsp">
               <i class="fa-solid fa-arrow-right-from-bracket fa-rotate-180" style="color: #ffffff;"></i></a>

            </div>

            <div class="rsubTitle">차량 번호 변경 </div>

        </div>

	<form class="rNumChange scrollbox">

		<div class="rChangeBox"> <!-- 차량번호 수정구역 -->
            <div class="rci_subtitle">차량번호 변경 <span>변경할 차량번호를 입력하세요</span> </div>
			<input type="text" class="rBikeChange" name="rBikeChange">

		</div> <!-- 차량번호 수정구역 e -->

		<div class="rChangeBox"> <button onclick="rBikeNumChange()" type="button"> 수정하기 </button>  </div>

        
	</form>
     <div class="footerR" >
       <div onclick="onMove('callList')" class="fbtn btn50 bgm02">콜리스트</div>
       <div onclick="onMove('goBack')" class="fbtn btn50 bgm02">돌아가기</div>
   </div>
	</div> <!-- 라이더 차량번호수정 전체 e -->


	<!-- 최신 JQUERY import ( ajax() 사용할 js파일부터 위에서 호출)  -->
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>

	<script src="../../js/gorider/rider/rBikeNumChange.js"></script>

</body>
</html>