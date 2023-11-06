<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/ezenTeam1/css/gorider/common.css" rel="stylesheet">
   <link href="/ezenTeam1/css/gorider/rider.css" rel="stylesheet">
</head>
<body>
<div class="wrap">
    <div class="header">

        <h2> 라이더 지원서 작성 </h2>

    </div>
    <div class="rSignupBox scrollbox rSUsH">
    <form class="riderSignup">
        <ul class="riderSignupUL">
            <li class="title" > 이름 </li>
            <li class="input">
            	<input class="rname" name="rname" type="text">
            	 <span class="namecheckbox"> </span>
            </li>

            <li class="title" >아이디<span class="idcheckbox"> </span></li>
            <li class="dflex">
            	<!-- onkeyup="idcheck()" -->
                <input onkeyup="idcheck()" class="rid" name="rid" type="text">


            <li class="title" >비밀번호<span class="pwdcheckbox">  </span> </li>
            <li class=""><input onkeyup="pwdcheck()" maxlength="30" class="rpwd" name="rpwd" type="text"></li>

            <li class="title">비밀번호 확인</li>
            <li class=""><input onkeyup="pwdcheck()"  maxlength="30" class="rpwdconfirm" name="rpwdconfirm" type="text"></li>

			<li class="title">전화번호 </li>
			<li class=""> <input type="text" class="rphone" name="rphone" placeholder="- 제외하고 입력해주세요."/> </li>


            <li class="title">프로필사진</li>
            <li class=""><input onchange="" class="rphoto" name="rphoto" type="file">
            	<img class="preimg" src="" />
            </li>

            <li class="title">면허증</li>
            <li class=""><input onchange="" class="rlicense infile" name="rlicense" type="file"></li>

             <li class="title" >차량등록증</li>
            <li class=""><input onchange="" class="rregistration " name="rregistration" type="file"></li>

            <li class="title" > 계좌번호 </li>
            <li class="dflex">
                <select class="inselect rbank" name="rbank">
                    <option value=""> 은행선택 </option>
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
                <input class="raccount intext75" name="raccount" type="number">
            </li>

            <li class="title"> 차량번호 </li>
			<li class=""> <input type="text" class="rbikenum" name="rbikenum" placeholder="차량번호를 입력해주세요."/> </li>

        </ul>
        <div class="tSUbtnbox dflex">
            <button  class="btn50 colorW" onclick="" type="reset"> 다시쓰기</button>
            <button  class="btn50" onclick="signup()" type="button"> 지원서제출</button>
        </div>
       </form>
    </div><!-- 라이더 회원가입 전체구역 e -->

</div><!-- 전체구역 e -->


   <!-- 최신 JQUERY import ( ajax() 사용할 js파일부터 위에서 호출)  -->
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>

   <!-- js -->
   <script src="/ezenTeam1/js/gorider/rider/riderSignup.js" type="text/javascript"></script>
</body>
</html>