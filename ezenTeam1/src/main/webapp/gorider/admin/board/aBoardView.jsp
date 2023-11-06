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
	<div class="contBox">
		<div class="vbtnBox">
			<button type="button">삭제하기</button>
			<button onclick="onList()" type="button">목록보기</button>
		</div>

		<ul class="bviewBox">
		<!-- 게시물의 내용 출력 -->
		</ul>

	</div>
</div>
<script src="/ezenTeam1/js/gorider/admin/adminBoardView.js"></script>

</body>
</html>