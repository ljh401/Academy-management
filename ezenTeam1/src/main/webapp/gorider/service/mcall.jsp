<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


	<style type="text/css">
	
		.btns{
			    display: flex;
	    margin: 27px 2px;
		}
		.btns button{
			    width: 100%;
    background-color: #30e3ca;
    color: white;
    height: 50px;
    /* border-radius: 9px; */
    border: 1px solid;
    font-size: 20px;
    font-weight: bold;
		}
		.ccontent{
			    width: 100%;
    height: 30px;
    border-radius: 10px;
    padding: 10px;
    border: none;
    box-shadow: 0px 0px 4px #30e3ca;
    margin: 20px 0px;
		}
		.placeinfo{
			    margin: 20px auto;
    text-align: center;
		}
		
	
	
	</style>

</head>
<body>
<div class="wrap">
     <%@include file="../member/header.jsp" %>
     <!-- content구간 -->
     <div class="contentBox">
        
        <div id="map" style="width:100%;height:350px;"></div>
        
        <div id="clickLatlng"></div>
        
      <div class = "addrInput">
		<div class = "placeinfo">
		</div>
		<div class="예상금액"></div>
		<div class="btns">
			<button type = "button" class = "call">콜</button>
	  		<button type = "button" class = "back">뒤로가기</button>
		</div>
 		
 	</div>

     </div>
     
     
    <%-- <%@include file="../member/footer.jsp" %>
     --%>

</div>
<!--  -->
<!-- 카카오지도 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=59b47c7057625f350189b1cb8369a874&libraries=services"></script>

 <!-- js -->
   <script src="/ezenTeam1/js/gorider/service/mcall.js" type="text/javascript"></script>
   
</body>
</html>