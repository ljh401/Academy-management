<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
<link href="/ezenTeam1/css/gorider/admin.css" rel="stylesheet">
<!-- <style type="text/css">*{border:solid 1px red;}</style> -->

</head>
<body>
<div class="wrap">
 <%@include file="../aheader.jsp" %>
	<div class="listbtnBox">
        <a href="javascript:history.back();"><i class="fa-solid fa-arrow-right-from-bracket fa-rotate-180"></i></a>
            <h5 class="aptitle80"> 공지사항 /이벤트 </h5>
        </div>
	<div class="writeBox">
	<form class="bWriteForm">
		<ul>
			<li class="btypeBox">
				<input type="radio" name="btype" class="btype" value='N' onclick="getBtype()"><span>공지사항</span>
				<input type="radio" name="btype" class="btype" value='E' onclick="getBtype()"> 이벤트 </li>
			<!-- 이벤트 선택시 달력-->
            <li class="bePeriodBox">
            <!--
                <span class="startdate">Event시작일</span><i onclick="onCalendar('S')" class="fa-regular fa-calendar-check"></i>
                <span class="gubun"> - </span>
                <span class="enddate">Event 종료일</span><i onclick="onCalendar('E')"class="fa-solid fa-calendar-check"></i>
            -->
            </li>
			<li class="btargetBox">
				<input type="radio" name="btarget" class="btarget" value='U' onclick="getBtarget()" ><span>사용자</span>
				<input type="radio" name="btarget" class="btarget" value='R' onclick="getBtarget()"> 라이더 </li>


			<li><input type="text" class="btitle"  name ="btitle" placeholder="제목을 입력하세요"></li>
			<li><textarea class="bcontent"  name ="bcontent" placeholder="내용을 입력하세요"></textarea></li>
			<li><div class="bfiles">첨부할 파일을 드래그해주세요.
			<!-- 파일한개 -->
			<div>
				<span>파일명</span>
				<span>파일사이즈</span>
				<span><button type="button">X</button></span>

			</div>
			</div></li>
			<li class="bbtnBox">
				<button type="reset">다시쓰기</button>
				<button type="button" onclick="onSave()">저장하기</button>
			</li>
		</ul>
	</form>
	</div>

	<!-- 모달 달력 -->
	<div class="modalBox">
		<div class="modalcal"> <!-- 모달 달력 -->
			<div class="caltop">
				<!-- 이전 달 버튼 -->
				<button onclick="onNext(0)" type="button"> <i class="fa-solid fa-chevron-left"></i> </button>
				<!-- 현재 월 표시구역 -->
				<h6 class="caldate">2023년 07월</h6>
				<!-- 다음달 표시구역 -->
				<button onclick="onNext(1)" type="button"> <i class="fa-solid fa-chevron-right"></i> </button>
			</div>
			<div class="calendar"> <!-- 날짜 표시 구역 -->
				<!--  상단의 요일 표기  -->

				<!-- 일day 표기 -->


			</div>
		</div>
	</div>
</div>
<script src="/ezenTeam1/js/gorider/admin/adminBoard.js"></script>

</body>
</html>