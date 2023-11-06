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
	<h5 class="aptitle"> 공지사항 /이벤트 </h5>
	<div class="listBox">
		<div class="listbtnBox">
		<a href="javascript:history.back();"><i class="fa-solid fa-arrow-right-from-bracket fa-rotate-180"></i></a>
			<button onclick="onWrite()" type="button">글작성하기</button>
		</div>
		<!-- 리스트 head -->
		<ul class="listhead">
			<li class="btype">종류</li>
			<li class="btarget">T</li>
			<li class="btitle">제목</li>
			<li class="bdate">작성일</li>
		</ul>
		<!-- 리스트 body -->
		<div class="listbody">
			<!-- 리스트목록 출력 -->


		</div>

	</div>
	<%@include file="../afooter.jsp" %>

</div>
<script src="/ezenTeam1/js/gorider/admin/adminBoardList.js"></script>

</body>
</html>