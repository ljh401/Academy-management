<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
    <link href="/ezenTeam1/css/gorider/admin.css" rel="stylesheet">

</head>
<body>

     <div class="wrap">
     <%@include file="aheader.jsp" %>
     <h5 class="ptitle">서비스이용현황 정보</h5>

        <div class="listbox">

         <!-- title -->
            <ul class="listTitle rlistR">
                <li class="col15">S번호</li>
                <li class="col25">서비스일자</li><!-- 서비스일자/ 고객으로부터  입금된 날짜 -->
                <li class="col20">R지급액</li>
                <li class="col25">정산일</li><!-- 라이더에게 입금된 날짜 -->
                <li class="col15">소계</li><!-- 입금액 - 지급액 -->
           </ul>

        <!-- 1 content start -->
            <ul class="listContent rlistR">
                <li class="col15">53</li>
                <li class="col25">2023-09-14</li>
                <li class="col20">1800</li>
                <li class="col25">2023-09-14</li>
                <li class="col15">300</li>
           </ul>

             <ul class="listContent rlistR">
                <li class="col15">53</li>
                <li class="col25">2023-09-14</li>
                <li class="col20">1800</li>
                <li class="col25">2023-09-14</li>
                <li class="col15">300</li>
           </ul>

             <ul class="listContent rlistR">
                <li class="col15">53</li>
                <li class="col25">2023-09-14</li>
                <li class="col20">1800</li>
                <li class="col25">2023-09-14</li>
                <li class="col15">300</li>
           </ul>

           <ul class="listContent rlistR">
                <li class="col15">53</li>
                <li class="col25">2023-09-14</li>
                <li class="col20">1800</li>
                <li class="col25">2023-09-14</li>
                <li class="col15">300</li>
           </ul>
            <ul class="listContent rlistR">
                <li class="col15">53</li>
                <li class="col25">2023-09-14</li>
                <li class="col20">1800</li>
                <li class="col25">2023-09-14</li>
                <li class="col15">300</li>
           </ul>


        </div>


         <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
     	<script src="/ezenTeam1/js/gorider/admin/aClosingList.js"></script>
      <%@include file="afooter.jsp" %>
     </div>
</body>
</html>