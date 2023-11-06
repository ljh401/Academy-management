<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="../css/sns.css"  rel="stylesheet">
</head>
<body>
	
	<div class="container" >
		<div class="headerBox"> <!-- header  -->
			<div> <a href="snsList.jsp"> SNS </a></div>
			<button class="btn"  onclick="onWrite()" type="button">등록</button>
		</div>
		<div class="searchBox">
			<span  class="sResult" >총 피드수 : <span class="rcount">14</span></span>
			<input onkeyup="onSearch()" class="keyword" type="text" placeholder="Search">
		</div>
	
		<div class="cWrap">
				<!-- 게시물 출력구간 -->
		</div>
		


	</div>


<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
<!-- 	<script src = "/ezenTeam1/js/sns.js"type="text/javascript"></script> -->
	
	<script src = "/ezenTeam1/js/snslist.js"type="text/javascript"></script>
	<!-- <script src = "/ezenTeam1/js/sns.js"type="text/javascript"></script> -->

</body>
</html>