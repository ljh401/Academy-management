<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- css 호출 -->
	<link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
	<link href="/ezenTeam1/css/gorider/rider.css" rel="stylesheet">

</head>
<body>
	
	<div class="wrapR"> <!-- 라이더 차량번호수정 전체 -->
			

	     <%@include file="rheader.jsp" %>
			<div class="rheaderwrapA backimg" >  <!-- 뒤로가기 버튼구역-->
	            <div class="rtopbtn">
	                <a href="/ezenTeam1/gorider/rider/rMyMenu.jsp">
	               <i class="fa-solid fa-arrow-right-from-bracket fa-rotate-180" style="color: #ffffff;"></i></a>
	
	            </div>
	
	            <div class="rsubTitle">계좌 번호 변경 </div>
	
	        </div>
		
		<form class="rNumChange scrollbox">
			
	        <!-- 계좌번호 입력구역 -->
	        <div class="rChangeBox ">
	            <div class="borderTop"></div>
		        <div class="rci_subtitle ">계좌번호 변경  <span>변경할 계좌번호를 입력하세요</span> </div>
				<div class="rBankChange">
					<select class="inselect rbankinfo" name="rbankinfo">
			                    <option value="" selected> 은행선택 </option>
			                    <option value="KM"> 국민 </option>
			                    <option value="IB"> 기업 </option>
			                    <option value="NH"> 농협 </option>
			                    <option value="SH"> 신한 </option>
			                    <option value="WR"> 우리 </option>
			                    <option value="KE"> 외환 </option>
			                    <option value="KA"> 카카오 </option>
			                    <option value="TS"> 토스 </option>
			                    <option value="HN"> 하나 </option>
			        </select>
			           <input class="raccountinfo" name="raccountinfo" type="number" >
				</div>
			</div>
			<div class="rChangeBox"> <button onclick="rBankNumChange()" type="button"> 수정하기 </button>  </div>
		</form>
	     <div class="footerR" >
	       <div onclick="onMove('callList')" class="fbtn btn50 bgm02">콜리스트</div>
	       <div onclick="onMove('goBack')" class="fbtn btn50 bgm02">돌아가기</div>
	   </div>
	</div> <!-- 라이더 차량번호수정 전체 e -->
	<!-- 최신 JQUERY import ( ajax() 사용할 js파일부터 위에서 호출)  -->
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="../../js/gorider/rider/rBankChange.js"></script>
	
	
</body>
</html>