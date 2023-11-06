<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Go Rider!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
<style>
/*   *{border:solid 1px red;} */
</style>
</head>
<body>
<!-- 전체렙 -->
<div  class="wrap ">

	<div class="loginWrap ">
        <div class="logo ">GORIDER</div>
        <div class="loginBox">
            <input class="mid" type="text" placeholder="아이디를 입력하세요">
            <input class="mpwd" type="text" placeholder="비밀번호를 입력하세요">
            <button onclick="onLogin()" class="btn btypeW100H50" type="button">로그인</button>
        </div>
        <div class="loption disFlexEnd">
<!--             <div hidden><input type="radio" class="lsave"> 로그인정보 저장</div> -->
            <div class="findBox disFlexEnd">
	            <div onclick="findInfo('id')" class="findInfo">아이디 찾기</div>
	            <div onclick="findInfo('pwd')">비밀번호 찾기</div>
	        </div>
        </div>

	</div>


</div>
   <!-- 최신 JQUERY import ( ajax() 사용할 js파일부터 위에서 호출)  -->
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script src="/ezenTeam1/js/gorider/member/mLogin.js" type="text/javascript"></script>
</body>
</html>