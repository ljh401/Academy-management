<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>

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

			<div class="rsubTitle">내 프로필 변경</div>

		</div>

		<form class="rpChange">

			<div class="rphoto"> <!-- 라이더 프로필 사진 구역 -->

				<img  src="/ezenTeam1/gorider/rider/img/default.webp"/>


			</div> <!-- 라이더 프로필 사진 구역 e -->
		    <div class="rphotobtnBox">
                <input class="photoinput" name="rphoto" type="file" placeholder="수정할 프로필 사진 선택">
                <button onclick="changeImg()" type="button"> 사진변경 </button>
            </div>
		</form>

		<div class="rpcontent"> <!-- 라이더 이름 -->

			<div class="rctitle">이름 :  <span class="rpname"> </span> </div>
			<div class="rzone"> 안산 상록구 , KOR </div>

		</div>  <!-- 라이더 이름 e -->

     <div class="footerR" >
       <div onclick="onMove('callList')" class="fbtn btn50 bgm02">콜리스트</div>
       <div onclick="onMove('goBack')" class="fbtn btn50 bgm02">돌아가기</div>
    </div>
	</div> <!-- 라이더 프로필 전체구역 e -->

	<!-- 최신 JQUERY import ( ajax() 사용할 js파일부터 위에서 호출)  -->
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>

	<!-- js호출 -->
	<script src="../../js/gorider/rider/rProfil.js"></script>

</body>
</html>


















