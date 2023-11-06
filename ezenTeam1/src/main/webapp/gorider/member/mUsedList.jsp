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
<!-- <style>*{border:solid 1px red;}</style> -->

</head>
<body>
 <div  class="wrap">
<%--  <%@include file="mBHeader.jsp" %> --%>
   <%@include file="header.jsp" %>

<!--     <div class="hcontBox"> -->
    <div class="contBox">
	    <div class="bpageinfo">
	        <a href="javascript:history.back()">
	            <i class="fa-solid fa-arrow-right-from-bracket fa-rotate-180"></i><span class="gohome">이전페이지로</span>
	        </a>
	    </div>

        <div class="histroyBox scrollbox mUsedList"> <!-- list 전체 -->

			<!-- 이용기록 리스트 출려 구역 -->
	        
        </div>
    </div>
 </div>
<!-- 카카오 -->
 <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1f944d875d569375b6c6bc259f5f497b&libraries=services"></script>
 <script src="/ezenTeam1/js/gorider/member/mUsedList.js"></script>
</body>
</html>