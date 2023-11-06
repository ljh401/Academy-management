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

			<div class="rsubTitle">공지사항/이벤트</div>

		</div>

		<!-- 공지사항 /이벤트 시작 -->
		<div class="btypeBox">
	        <div onclick="onList('N',0)" class="btypeN">공지사항</div>
	        <div onclick="onList('E',0)" class="btypeE" >이벤트</div>
	    </div>
	    <div class="bContBox scrollbox">
	        <div class="btitle">[공지]제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목 <span>2023-10-23</span></div>
	        <div class="btitle">[공지]제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목 <span>2023-10-23</span></div>
	        <div class="btitle">[공지]제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목 <span>2023-10-23</span></div>
	        <div class="btitle">[공지]제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목 <span>2023-10-23</span></div>
	        <div class="btitle">[공지]제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목 <span>2023-10-23</span></div>
	        <div class="btitle">[공지]제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목 <span>2023-10-23</span></div>
	        <div class="btitle">[공지]제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목 <span>2023-10-23</span></div>
	    </div>
        <div class="footerR" >
            <div onclick="onMove('callList')" class="fbtn btn50 bgm02">콜리스트</div>
            <div onclick="onMove('goBack')" class="fbtn btn50 bgm02">돌아가기</div>
        </div>
	</div> <!-- 라이더 프로필 전체구역 e -->
	<!-- js호출 -->
	<script src="../../js/gorider/rider/rBoardList.js"></script>

</body>
</html>


















