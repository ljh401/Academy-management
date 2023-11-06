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
</head>
<body>
	<div class="wrapR ">
     <%@include file="rheader.jsp" %>

        <div class="rheaderwrap ">


            <div onclick="onMove('myMenu')" class="rbtn">나의 메뉴</div>

        </div>

        <div class="hbutton">상단고정버튼</div>

        <div class="rcontBox">
            <div class="rcont rtitle">

                <h2>바이크 번호</h2>

	            <div class="numbox">

	               <p class="fs18m02">경기 안산</p>
		           <div class="numbox2">
			           <span class="col20 fs15m02">하</span>
			           <span class="col50 fs22m02">5771</span>
		           </div>

                </div>


            </div>
        </div>
	 <div class="footerR" >
       <div onclick="goWork()" class="fbtn btn100 bgm02">출근하기</div>
   </div>
    </div>
    <script src="../../js/gorider/rider/rAfterWork.js"></script>
    <script src="../../js/gorider/rider/rheader.js"></script>
</body>
</html>