<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Go Rider!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
    <link href="/ezenTeam1/css/gorider/rider.css" rel="stylesheet">
<!--     <style type="text/css">*{border:solid 1px red;}</style> -->

</head>
<body>
<!-- 전체렙 -->

<div class="wrapR">  <!-- 라이더 프로필 전체구역 -->
     <%@include file="rheader.jsp" %>

        <div class="rheaderwrapA backimg" >  <!-- 뒤로가기 버튼구역-->
            <div class="rtopbtn">
                <a href="/ezenTeam1/gorider/rider/rMyMenu.jsp">
               <i class="fa-solid fa-arrow-right-from-bracket fa-rotate-180" style="color: #ffffff;"></i></a>

            </div>

            <div class="rsubTitle">공지사항/이벤트</div>

        </div>


   <div class="contBox">
<!--         <div class="vbtnBox"> -->

<!--             <button onclick="onList()" type="button">목록보기</button> -->
<!--         </div> -->

        <ul class="bviewBox">
        <!-- 게시물의 내용 출력 -->
        </ul>

    </div>




   <!-- bottom -->


     <div class="footerR" >
       <div onclick="onMove('callList')" class="fbtn btn50 bgm02">콜리스트</div>
       <div onclick="onMove('goBack')" class="fbtn btn50 bgm02">돌아가기</div>
   </div>
</div>
<script src="/ezenTeam1/js/gorider/rider/rBoardView.js"></script>
</body>
</html>