<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>

    <link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
        <link href="/ezenTeam1/css/gorider/rider.css" rel="stylesheet">
<!-- <style>*{border:solid 1px red;}</style> -->

	<style type="text/css">
		
		.choicebox{
			display: flex;
		}
		.choicebox button {
			width: 20%;
		    background-color: #30e3ca;
		    color: white;
		    height: 10px;
		    border: 1px solid;
		    font-size: 10px;
		    font-weight: bold;
		}
	
	
	</style>

</head>
<body>
	<div class="wrapR ">
 <%@include file="rheader.jsp" %>
        <div class="rheaderwrapA ">
            <div  onclick="onMove('myMenu')" class="bcp mymenu">나의 메뉴</div>
        </div>

        <div class="hbutton">
			<div onclick="onMove('MyMenu')" class="fbtn col50 bcp"><i class="fa-solid fa-ban"></i> 콜 멈추기</div>
			<div onclick="onMove('MyMenu')"class="fbtn col50 bcp "><i class="fa-solid fa-list"></i> 콜 리스트</div>
		</div>

        <div class="rcontBox ">
            <div class="rstatebox ">
				<div class = "callcontent">
              		<div class="rstate"> 콜 대기중 </div>
        			 <div on="interval" class="dotBox">

               		<i class="fa-solid fa-circle dot1"></i>
               		<i class="fa-solid fa-circle dot2"></i>
               		<i class="fa-solid fa-circle dot3"></i>
               </div>
               </div>
              



            </div>

        </div>
		<div class="footerR" >
	       <div  onclick="outWork()"class="fbtn btn100 bgm02">퇴근하기</div>
	    </div>
    </div>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=59b47c7057625f350189b1cb8369a874&libraries=services"></script>
<script src="https://kit.fontawesome.com/bfee791ec9.js" crossorigin="anonymous"></script>

<script src = "../../js/gorider/rider/rAfterWork.js"></script>
<script src = "../../js/gorider/service/riderAccept.js"></script>

</body>
</html>