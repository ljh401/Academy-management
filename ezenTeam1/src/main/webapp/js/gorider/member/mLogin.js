console.log('js실행됩니다.');

//1. login
function onLogin(){
	console.log('onLogin')
	let mid = document.querySelector('.mid').value;
	let mpwd = document.querySelector('.mpwd').value;
	$.ajax({
		url:"/ezenTeam1/MemberFindController",
		method:"post",
		data:{mid:mid, mpwd:mpwd},
		success: r =>{

			//console.log("성공" + r);
			if(r){
				//alert(mid+'로그인 성공');
				location.href = '/ezenTeam1/gorider/member/mindex.jsp'
			} else {
				alert('회원정보가 일치하지 않습니다');
				//초기화 해주기
				mid='';
				mpwd='';
			}
		},
		error : e =>{console.log("실패"+ e)}
	})
}


//2 .아이디 비밀번호 화면출력
function findInfo(type){
	let loginBox = document.querySelector('.loginBox');
	let findBox = document.querySelector('.findBox');
	let html =``;
	let html2 =``
//		alert(type);
	if (type =='id'){
		document.querySelector('.logo').innerHTML = '아이디 찾기'


		html += `<input class="name" type="text" placeholder="이름을 입력하세요">
            <input class="email" type="text" placeholder="이메일주소를 입력하세요">
            <button onclick="onFind('findId')" class="btn btypeW100H50" type="button">아이디 찾기</button>`
		loginBox.innerHTML = html;

		html2 += `<div onclick="findInfo('login')">로그인하기</div>
	            <div onclick="findInfo('pwd')">비밀번호 찾기</div>`
			findBox.innerHTML = html2;


	} else if(type =='pwd'){
		document.querySelector('.logo').innerHTML = '비밀번호 찾기'

		html += `<input class="id" type="text" placeholder="아이디를 입력하세요">
            <input class="email" type="text" placeholder="이메일주소를 입력하세요">
            <button onclick="onFind('findPwd')" class="btn btypeW100H50" type="button">비밀번호 찾기</button>`
		loginBox.innerHTML = html;

		html2 += `<div onclick="findInfo('id')" class="findInfo">아이디 찾기</div>
            	<div onclick="findInfo('login')">로그인하기</div>`
		findBox.innerHTML = html2;

	} else if( type == 'login'){
		location.href = '/ezenTeam1/gorider/member/mlogin.jsp'

	}
}

//3.아이디/비밀번호 찾기
function onFind(type){
	if(type == 'findId'){
		let mname = document.querySelector('.name')
		let memail =  document.querySelector('.email')
		$.ajax({
			url:"/ezenTeam1/MemberFindController",
			method:"get",
			data:{type:type,mname:mname.value, memail:memail.value},
			success: r =>{

				//console.log("성공" + r);
				if(r){
					alert('아이디는 ' + r.mid + '입니다.');
					location.href = '/ezenTeam1/gorider/member/mlogin.jsp'
				} else {
					alert('회원정보가 일치하지 않습니다');
					//초기화 해주기
					mname.value='';
					memail.value='';
				}
			},
			error : e =>{console.log("실패"+ e)}
		});
	} else if(type == 'findPwd') {
		let mid = document.querySelector('.id')
		let memail = document.querySelector('.email')
		$.ajax({
			url:"/ezenTeam1/MemberFindController",
			method:"get",
			data:{type:type, mid:mid.value, memail:memail.value},
			success: r =>{

				console.log(r);
				if(r){
					alert('비밀번호는 ' + r.mpwd + '입니다.');
					location.href = '/ezenTeam1/gorider/member/mlogin.jsp'
				} else {
					alert('회원정보가 일치하지 않습니다');
					//초기화 해주기
					mid.value='';
					memail.value='';
				}
			},
			error : e =>{console.log("실패"+ e)}
		});
	}
}
