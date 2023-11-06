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
<%--  <%@include file="rheader.jsp" %> --%>
 <%@include file="rheader.jsp" %>
        <div class="rheaderwrap ">

				<div class="riderinformation">
            		<p><span class="colorY rname"></span> 라이더님 반갑습니다. </p>
            	</div>

            <div  onclick="onMove('myMenu')" class="rbtn">나의 메뉴</div>

        </div>

        <div class="hbutton">상단고정버튼</div>

        <div class="rcontBox">
            <div class="rcont rtitle">

                <h2>바이크 번호</h2>

	            <div class="numbox">

	               <p class="fs18m02 nlevel0"><!-- 바이크넘버 --></p>
		           <div class="numbox2">
			           <span class="col20 fs15m02 rbikenum nlevel1"><!-- 바이크넘버 --></span>
			           <span class="col50 fs22m02 rbikenum nlevel2"><!-- 바이크넘버 --></span>
		           </div>

                </div>


            </div>
        </div>
		<div class="footerR" >
	       <div onclick="goWork()"class="fbtn btn100 bgm02">출근하기</div>
	    </div>
    </div>

    <!-- 최신 JQUERY import ( ajax() 사용할 js파일부터 위에서 호출)  -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

	<script src="../../js/gorider/rider/rBeforeWork.js"></script>
    <!--<script src="../../js/gorider/rider/rheader.js"></script>  # rheader.jsp에 선언되어 있어 중복코드로 삭제됨-->

</body>
</html>