<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>

   <link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
   <link href="/ezenTeam1/css/gorider/member.css" rel="stylesheet">

</head>
<body> <!-- 사용자 회원가입 -->


	   <!-- 전체렙 -->
	<div  class="wrap">
	   <div class="signupWarp"> <!-- 회원가입 전체구역 -->
	      <div class="header">
	         <h2> 회원가입 </h2>
	         <p> 환영합니다. </p>
	      </div>

	      <form class="signupForm">


	         <ul class="signupbox">
		          <!-- 회원이름  -->
		         <li class="title"> 회원이름 </li>
		         <li class="input"><input name="mname" class="" type="text" /></li >

		         <!-- 회원아이디  -->

		         <li class="title"> 아이디 <span class="idComment"> </span></li>
		         <li class="input">
		              <input  onkeyup="dupleIdCheck()" maxlength="30"  name="mid" class="mid intext80" type="text"/>
		             </li>


		         <!-- 회원비밀번호 -->
		         <li class="title"> 비밀번호 <span class="pwdComment"> </span></li>
		         <li class="input"><input  onkeyup="pwdCheck()" maxlength="30"  name="mpwd" class="mpwd" type="text"/></li>
		         <li class="title"> 비밀번호 확인 <span class="pwd2Comment"> </span></li>
		         <li class="input"><input onkeyup="pwdCheck()" maxlength="30"  name="mpwd2" class="mpwd2" type="text"/></li>

		         <!-- 회원이메일 -->
		         <li class="title"> 이메일 <span class="emailComment"> </span></li>
		         <li class="input input65 disFlexCent">
		         <!-- 유효성검사 통과시 인증번호받기 버튼 사용가능 -->
		              <input onkeyup="emailcheck()" name="memail" class="memail" type="email"/>
		              <button onclick="authReq()" class="authBefore authBtn" type="button" disabled>인증번호받기</button>
		         </li>
		          <!-- 회원이메일 -->
		         <sapn class="authbox">

                 </sapn>
		         <!-- 결제카드번호 -->
		         <li class="title "> 결제카드번호 <span class="payComment"> </span></li>
		         <li class="payinfo disFlexCent">
		         	 <input  maxlength="4"  name="mpayinfo1" class="mid" type="text"/>
		         	- <input  maxlength="4"  name="mpayinfo2" class="mid" type="text"/>
		         	- <input  maxlength="4"  name="mpayinfo3" class="mid" type="text"/>
		         	- <input  maxlength="4"  name="mpayinfo4" class="mid" type="text"/>
		         </li>


	         </ul>
	         <div class="btnbox"><button onclick="onSave()" class="btn" type="button">회 원 등 록</button> </div>


	      </form>
	   </div> <!-- 회원가입 전체구역 e -->
	</div>


   <!-- 최신 JQUERY import ( ajax() 사용할 js파일부터 위에서 호출)  -->
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>

   <!-- js -->
   <script src="/ezenTeam1/js/gorider/member/mSignup.js" type="text/javascript"></script>


</body>
</html>