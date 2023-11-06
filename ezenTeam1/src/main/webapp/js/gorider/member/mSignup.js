/**
 *
 */
let checkList = [false, false, false]//아이디/비밀번호/이메일체크결과
console.log('연결');
//회원가입 정보 저장
function onSave(){
	console.log( "onSave()");


	if(checkList[0] && checkList[1]&& checkList[2]){
		//폼객체가져오기
		let signupForm  = document.querySelectorAll('.signupForm')[0]
		//폼데이터 객체만들기
		let signupData = new FormData(signupForm);
		console.log(signupData);

		$.ajax({
			url:"/ezenTeam1/MemberInfoController",
			method:"post",
			data:signupData,
			contentType:false,
			processData:false,
			success: r =>{

				console.log("성공" + r);
				if(r) {
					alert('정상가입 되었습니다.');
					location.href = '/ezenTeam1/gorider/member/mlogin.jsp'
				}else {
					alert('회원가입실패. 관리자에게 문의바랍니다.')
				}

			},
				error : e =>{console.log("실패"+ e)}
			})
	} else {
		alert('입력되지 않았거나 잘못 입력된 내용이 있습니다.')
	}

}

function dupleIdCheck(){
	let mid = document.querySelector('.mid').value;
	let idComment = document.querySelector('.idComment')

	//영문+ 숫자조합 8자이상20자 미만으로 정규표현식
	let midC = /^[a-zA-Z0-9]{8,20}$/
	//정규표현식에 적합하면
	if(midC.test(mid)){
		//사용가능 메시지 전달
		idComment.innerHTML ='사용가능한 아이디입니다.'
		//아이디 중복체크
		$.ajax({
			url:"/ezenTeam1/MemberInfoController",
			method:"get",
			data:{type:'isExist', key:mid,search:'mid'},
			success: r => {
				console.log(r)
				if(r == true){
					idComment.innerHTML ='이미 사용중인 아이디입니다.'
					checkList[0] = false;
				} else{
					idComment.innerHTML ='사용가능한 아이디입니다.'
					checkList[0] = true;
				}
			},
			error : e => {console.log(e)}
		})
	} else { // 정규표현식에 적합하지 않으면
		//부적함 메시지를 전달
		idComment.innerHTML ='아이디는 영문+숫자조합 8~20자미만으로 작성해주세요'
		checkList[0] = false;
	}

	console.log(mid);
}

//비밀번호 유효성 검사
function pwdCheck(){
	let mpwd = document.querySelector('.mpwd').value;
	let mpwd2 = document.querySelector('.mpwd2').value;
	let pwdComment = document.querySelector('.pwdComment') ;
	let pwd2Comment = document.querySelector('.pwd2Comment') ;
	console.log(mpwd);
	//영문+ 숫자조합 8자이상20자 미만으로 정규표현식
	let pwdC = /^[a-zA-Z0-9!@#$%^&*]{8,20}$/
	if(pwdC.test(mpwd)){
		pwdComment.innerHTML = '사용가능한 비밀번호입니다.';
		if(pwdC.test(mpwd2)){
			if(mpwd == mpwd2){
				pwd2Comment.innerHTML = '사용가능한 비밀번호입니다.'
				checkList[1] = true;
			} else {
				pwd2Comment.innerHTML = '비밀번호가 일치하지 않습니다.'
				checkList[1] = false;
			}
		} else {
			pwd2Comment.innerHTML = '비밀번호는 영문+숫자조합 8~20자미만으로 작성해주세요'
			checkList[1] = false;
		}

	} else{
		pwdComment.innerHTML = '비밀번호는 영문+숫자조합 8~20자미만으로 작성해주세요'
		checkList[1] = false;
	}
	console.log(checkList)
}
let authBtn = document.querySelector('.authBtn');
//authBtn.disabled = true; //인증버튼의 기본값을 사용불가 상태로 세팅disabled = true;
if(authBtn.disabled ==true){ //사용불가 상태일때 스타일 적용
	authBtn.style.backgroundColor='#666'
	authBtn.style.cursor = 'none';
	authBtn.style.borderRadius = '5px';
	authBtn.style.fontSize = '0.9rem';
}
function emailcheck(){

	let memail = document.querySelector('.memail').value;
	let emailComment = document.querySelector('.emailComment');


	let memailC = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]+$/ //이메일 정규표현식
		//- 이메일 주소의 로컬 파트, @ 기호, 도메인 파트(1차 및 최상위 도메인)의 형식

	if(memailC.test(memail)){ //정규표현식에 맞으면
		//emailComment.innerHTML ='사용가능한 이메일입니다.'
		//이메일 중복체크
		$.ajax({
			url:"/ezenTeam1/MemberInfoController",
			method:"get",
			data:{type:'isExist', key:memail,search:'memail'},
			success: r => {
				console.log(r)
				if(r == true){
					emailComment.innerHTML ='이미 사용중인 이메일입니다.'
					authBtn.disabled = true;
					checkList[2] = false;
				} else{
					emailComment.innerHTML ='사용가능한 이메일입니다.'

					checkList[2] = false; //인증번호까지 일치해야 하므로 false
					authBtn.disabled = false;
					// /사용가능 상태일때 스타일 변경
					authBtn.style.backgroundColor='var(--main02)'
					authBtn.style.cursor = 'pointer';
				}
			},
			error : e => {console.log(e)}
		})
	} else{ //정규표현식에 맞지 않으면
		emailComment.innerHTML ='이메일형식에 맞게 입력해주세요'
		authBtn.disabled = true;
		checkList[2] = false;
	}
}

/*4~  6번 함수에서 공통으로 사용할 변수선언*/
let authcode='';	//인증코드
let timer=0;		//인증시간
let timerInter;		//setInterval  함수를 가지고있는 변수 [ setInterval종료시 필요]

//인증번호받기버튼이 눌렸을때
function authReq(){
	let authbox = document.querySelector('.authbox');

	let html =` <li class="title"> 이메일 인증번호  <span class="timebox">02:00</span></li>
		         <li class="input75">
		         	<input  name="memailcheck" class="memailcheck" type="text"/>
		         	<button onclick="auth()" class="" type="button">인증하기</button>
		         </li>`
	//3.authbox에 html 대입
		authbox.innerHTML = html;

	authcode = '123123'
	timer = 120;
	settimer();

	/*
	$.ajax({
		url:"/ezenTeam1/AuthSendEmailController",
		method:"get",
		data:{memail : document.querySelector('.memail').value},
		success: r =>{
			console.log("통신성공 : " + r);
			// 1. authbox div 호출
			let authbox = document.querySelector('.authbox');

			// 2. authbox 안 html구성
			let html = `<span class="timebox">02:00</span>
				  		<input class="memailcheck" type="text">
				  		<button onclick="auth()" type="button" >인증</button>`

			// 3.authbox에 html대입
			authbox.innerHTML = html;

			// 4. 타이머 실행
			authcode = '1234' // 테스트용 인증코드 1234
			timer = 120;		//테스트용 제한시간 10초
			settimer();  	// 타이머함수 호출
		} ,
		error:e=>{
			console.log('통신실패 '+ e)
		}

	});
	*/
}

// 타이머
function settimer(){
	timerInter = setInterval(()=>{

		// 시간형식만들기
		let m = parseInt(timer / 60)
		let s = parseInt(timer % 60);  //초

		//두자리수로 맞춤
		m = m < 10 ? "0" + m: m; //10보다 작으면 0을 덧붙임
		s = s < 10 ? "0" + s: s;
		//timerbox에 00:00형식으로  html작성
		document.querySelector('.timebox').innerHTML = `${m}:${s}`
			timer --;
		//타이머가 0보다 작으면 종료
		if(timer <0){
			clearInterval(timerInter);

			//인증실패
			document.querySelector('.emailComment').innerHTML ='시간초과 인증실패';
			checkList[2]=false;

			document.querySelector('.authbox').innerHTML = ``;

		}
	}, 1000)
	console.log(checkList)

}
//6. 인증요청후 인증코드릉 입력하고 일치여부 확인 함수
function auth(){
	console.log('인증코드 체크');

	//1. 입력받은 인증코드
	let ecode = document.querySelector('.memailcheck').value;

	//2. 비교
	if(authcode == ecode){
		clearInterval(timerInter);
		document.querySelector('.emailComment').innerHTML = `인증성공`;
		checkList[2] = true;
		document.querySelector('.authbox').innerHTML=``; //authbox초기화
	} else {
		document.querySelector('.emailComment').innerHTML = `인증코드 불일치`;
		checkList[2] = false;
	}
	console.log( checkList )
}

