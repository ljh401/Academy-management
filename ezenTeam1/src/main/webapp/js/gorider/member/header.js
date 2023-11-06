console.log('js실행됩니다.');
function onMove(type){

	if(type =='S') {location.href='/ezenTeam1/gorider/member/mSignup.jsp'}
	if(type =='L') {location.href='/ezenTeam1/gorider/member/mlogin.jsp'}
	if(type =='M') {location.href='/ezenTeam1/gorider/member//mindex.jsp'}
	if(type =='MY') {location.href='/ezenTeam1/gorider/member/mMyPage.jsp'}

}


let loginState = false; //로그인상태 기본값 false로 세팅
let loginMid = ''; // 전역변수로 선언
let loginMname = '';
let loginMeamil = '';
let loginMphoto ='';
let loginMno = 0;
getMemberInfo();
// 1. 세션에 있는 로그인정보 유무에 따라  화면수정
function getMemberInfo(){
	console.log("헤더 JS")
	$.ajax({
		url:"/ezenTeam1/MemberInfoController",
		method:"get",
		async:false, //동기화설정(waiting)
		data:{'type':'login'},
		success: r =>{
			let btnBox = document.querySelector('.hbtnbox');
			let html =``;
			
			console.log(r);
			if(r == null){ //세션정보가 없으면 회원가입/ 로그인 버튼 활성화
				loginState = false;
				loginMid = '';
				html +=`<div onclick="onMove('S')"  class="btn r1">가입 </div>
				        <div onclick="onMove('L')"  class="btn r1"> 로그인 </div> `

			} else {//세션정보가 있으면 회원명 프로필사진 출력
				loginState = true;
				loginMid = r.mid;
				loginMname = r.mname;
				loginMeamil = r.memail
				loginMphoto = r.mphoto == null? 'default.png': r.mphoto;
				loginMno = r.mno;	console.log("loginMno :: "+loginMno);
				
				html += ` <div class="minfo">
							<span class="mname">${r.mname}님</span>
							<span class="sayhello">반갑습니다. <br> <span class="logout" onclick="logout()"> 로그아웃</span></span>
						    <img onclick="onMove('MY')" src="/ezenTeam1/gorider/member/file/${loginMphoto}"/>
						   </div>`
				console.log("loginState :: "+loginState);
				console.log("loginMid :: "+loginMid);
				console.log("loginMname :: "+r.mname);
			}
			btnBox.innerHTML = html;

		},
		error : e =>{console.log(e)}
	})
}

function logout(){
		$.ajax({
			url:"/ezenTeam1/MemberInfoController",
			method:"get",
			data:{type:"logout"},
			success: r =>{

				console.log(loginState);
				//alert('로그아웃 되었습니다.');
				location.href = '/ezenTeam1/gorider/member/mindex.jsp'

			},
			error : e =>{console.log("실패 :: "+ e)}
		})

}

function goBack(){
	window.history.back();
}