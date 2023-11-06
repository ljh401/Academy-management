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
<!--     <style type="text/css">*{border:solid 1px red;}</style> -->

</head>
<body>
<!-- 전체렙 -->

<div  class="wrap">

   <%@include file="header.jsp" %>

   <div class="contBox">
        <div class="vbtnBox">

            <button onclick="onList()" type="button">목록보기</button>
        </div>

        <ul class="bviewBox">
        <!-- 게시물의 내용 출력 -->
        </ul>

    </div>




   <!-- bottom -->
<%--    <%@include file="footer.jsp" %> --%>



</div>
<script src="/ezenTeam1/js/gorider/member/mBoardView.js"></script>
</body>
</html>