<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>라이더 승인 요청 상세보기</title>
    <link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">

    <link href="/ezenTeam1/css/gorider/admin.css" rel="stylesheet">
<style>
/* *{border:solid 1px red;} */
</style>
</head>
<body>

     <div class="wrap">
     <%@include file="aheader.jsp" %>
     <div class="contentBoxA">
        <h5 class="ptitle">라이더 승인 요청 상세보기</h5>
        <div class="viewbox scrollbox">

            <div class="disFlexStart sec1">

                <div class="rPhotobox">
                    <img class="rimg1">
                </div>
                <div class="rInfo">
                    <div><span class="rname"></span> </div>
                    <div><span class="rphone"></span> </div>
                </div>

            </div>

            <div class="sec2">
                <div class="title"><span> 면허증 </span></div>
                <div> <img class="rimg2" ></div>
            </div>

            <div class="sec2">
                <div><span> 차량 등록증 </span></div>
                <div> <img class="rimg3"></div>
            </div>
            <div class="btnsec">
                <button id="approveButton" onclick="onapprove()"class="btnA btypeW50H50" type="button">승인</button>
                <button id="denyButton" onclick="ondeny()"class="btnA btypeW50H50" type="button">거부</button>
            </div>

        </div>
    </div>



      <%@include file="afooter.jsp" %>
     </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/ezenTeam1/js/gorider/admin/adminView.js"></script>

</body>
</html>