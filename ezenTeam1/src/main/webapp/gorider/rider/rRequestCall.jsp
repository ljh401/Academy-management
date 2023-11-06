<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gorider</title>
<link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
<link href="/ezenTeam1/css/gorider/rider.css" rel="stylesheet">
<!-- <style>*{border:solid 1px red;}</style> -->
</head>
<body>
	<div class="wrapR ">
     <%@include file="rheader.jsp" %>
        <div class="rheaderwrapA ">
            <div onclick="onMove('myMenu')" class="bcp mymenu">나의 메뉴</div>
        </div>

        <div class="hbutton" hidden>
			<div onclick="onMove('')" class="fbtn col50 bcp"><i class="fa-solid fa-ban"></i> 콜 멈추기</div>
			<div onclick="onMove('callList')"class="fbtn col50 bcp "><i class="fa-solid fa-list"></i> 콜 리스트</div>
		</div>

        <div class="rcontBox ">
            <div class="rRequestbox ">

               <div class="rfrom"> 안산 본오동  <br/>상록수역 </div>
               <div class="rarrow"><i class="fa-solid fa-sort-down"></i> </div>
               <div class="rto"> 안산 이동 <br> <span class="toname">이젠아카데미 <br>컴퓨터학원 </span></div>



            </div>
        </div>
		<div class="footerR cbtnBox" >
	       <div onclick="" class="fbtn col40 bcp ">거 부</div>
		   <div onclick=""class="fbtn col60 bcp "> 콜 수락</div>
	    </div>
    </div>
<script src="https://kit.fontawesome.com/bfee791ec9.js" crossorigin="anonymous"></script>
<script src="../../js/gorider/rider/rAfterWork.js"></script>

</body>
</html>