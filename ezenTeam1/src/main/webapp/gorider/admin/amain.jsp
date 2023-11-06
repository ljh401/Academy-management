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

</head>
<body>
	<div class="wrap">
     <%@include file="aheader.jsp" %>
     <div class="menuBox">
    	<ul class="amenu">

            <li onclick="goMenu('R')">라이더 승인요청</li>
            <li onclick="goMenu('S')">서비스 이용 현황</li>
            <li onclick="goMenu('V')">리뷰/평점관리</li>
            <li onclick="goMenu('D')">라이더 입금내역</li>
            <li onclick="goMenu('C')">서비스 정산</li>
            <li onclick="goMenu('B')">공지/이벤트 관리</li>


        </ul>

     </div>


	 <%@include file="afooter.jsp" %>
    </div>
</body>
</html>