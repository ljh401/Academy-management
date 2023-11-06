console.log('js실행됩니다.');

// 라이더 회원가입 js

/*

	회원가입 기능 순서
	1. 회원가입 기능
	2. 기능 실행 잘되면 -> 유효성 검사로 진행한다. 

 */

// 회원가입  체크여부 
let checkList = [ false , false ]  // 아이디 , 비밀번호 체크여부


// 1. 유효성 검사없는 회원가입 Form데이터전송시 
function signup(){
		
		if( document.querySelector('.rname').value == ''){
			alert('이름을 입력해주세요.');
			return; 
		}else if( document.querySelector('.rid').value == ''){
			alert('아이디를 입력해주세요.');
			return;
		}else if( document.querySelector('.rpwd').value == '' ){
			alert('비밀번호를 입력해주세요.')
			return;
		}else if( document.querySelector('.rpwdconfirm').value == '' ){
			alert('비밀번호확인 해주세요.')
		}else if( document.querySelector('.rphone').value == '' ){
			alert('전화번호 입력해주세요.')
			return;
		}else if( !document.querySelector('.rphoto').value ){ // 첨부파일은 스트링 형식이 아닌 true / false 있다 없다 형태로 호출이됨 
			alert('프로필사진 등록해주세요.');
			return;
		}else if( document.querySelector('.rlicense').value == ''  ){
			alert('면허증을 등록해주세요.');
			return;
		}else if( document.querySelector('.rbank').value == ''  ){
			alert('은행을 선택해주세요');
			return;
		}else if( document.querySelector('.raccount').value == ''  ){
			alert('계좌번호를 입력해주세요.');
			return;
		}else if( document.querySelector('.rbikenum').value == '' ){
			alert('차량번호를 입력해주세요');
			return;
		}
		
		
	// 1. 아이디 / 비밀번호 유효성검사 통과 체크
		console.log(checkList);
	if(checkList[0] && checkList[1] ){
		
		
		
	// 1. 입력받은 데이터를 한번에 가져오기 
		// 1. form객체 호출 
	let riderSignup = document.querySelectorAll('.riderSignup')[0]; 
		console.log('form객체 호출했습니다'+riderSignup);
		// 2. form 데이터 객체화 
	let signupData = new FormData(riderSignup); 
		console.log(signupData);
		
	// 2. ajax 첨부파일대용량 전송시 
		           $.ajax({
               url : "/ezenTeam1/RiderInfoController" , 
               method: "post" ,
               data : signupData ,
               contentType : false ,
               processData : false ,
               success : r => { 
				   console.log(r);
				   
				   if(r){ // 마냐게 컨트롤러에서 들어온값이 성공이면
					   alert('회원가입 성공');
					   location.href = '/ezenTeam1/gorider/rider/rlogin.jsp';
				   }else{
					   alert('회원가입 실패[관리자에게 문의]')
				   }
				   		
				   } ,
               error : e => { 
				   console.log(e) 
               } ,
            })
		}else{
			console.log('회원가입 진행 불가능');
		}
	
	
}// f  e

// 2. 아이디 유효성 검사
function idcheck(){
	console.log('idcheck 함수실행됩니다');

	// 1. 값 호출 
	let rid = document.querySelector('.rid').value;  console.log('rid 입력값가져옴'+rid);
	let idcheckbox = document.querySelector('.idcheckbox'); console.log('idcheckbox 입력값가져옴'+idcheckbox);
	
	// 2. 유호성 검사
	
	// 1. 아이디입력할때 영문[소문자]+슷지 조합의 5~20글자 사이 이면 
		// 1. 정규표현식 작성
	let ridj = /^[a-z0-9]{5,20}$/
		// 2. 정규표현식 검사.
	console.log(ridj.test(rid) )
	if(ridj.test(rid) ){ // 만약에 입력한 값이 패턴과 일치하면 
		
		
		// -- 입력한 아이디가 패턴과 일치하면 중복검사 
			   $.ajax({
     			 url : "/ezenTeam1/RiderFindController",      
      			 data : { type : "ridcheck" , data : rid },      
      			 method : "get",   
      			 success : r => {
			 	 console.log(r);
				 // true - 사용가능한
				 if(r == true){ idcheckbox.innerHTML = '사용불가능한 아이디입니다'; 
				 	checkList[0] = false; }
				 else{ idcheckbox.innerHTML = '사용가능한 아이디 입니다.'; 
					 checkList[0] = true;}
				   } ,       
      			 error : e => {} ,         
   });
	}else{ // 입력한값이 일치하지 않으면 
		idcheckbox.innerHTML = '영문(소문자)+숫자 조합의 5~20글자 가능합니다.'; 
			checkList[0] = false;
	}
	
}// f e


// 2. 비밀번호 유효성검사 
function pwdcheck(){
	console.log('패스워드유효성검사함수실행됩니다.');
	
	let pwdcheckbox = document.querySelector('.pwdcheckbox');
		console.log('.pwdcheckbox'+pwdcheckbox);
		
	// 1. 입력 값 호출 
	let rpwd = document.querySelector('.rpwd').value; 
		console.log('rpwd'+rpwd);
	let rpwdconfirm = document.querySelector('.rpwdconfirm').value; 
		console.log('rpwdconfirm'+rpwdconfirm);
	
	// 2. 유효성검사 
		// 1. 정규표현식 만들기 [ 영대소문자(1개필수) + 숫자(1개필수) 조합7~30글자 사이 ]
		// 정규표현식에서 ( ) 소괄호안에있는건 필수입력이다.
	let rpwdj = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{7,30}$/
	
	if(rpwdj.test(rpwd)){ // 1. 비밀번호 정규표현식 검사 
	
		// 2. 비밀번호 확인 정규표현식 검사 
		if(rpwdj.test(rpwdconfirm) ){
		
			// 3. 비밀번호 와 비밀번호 확인 일치여부 
				// 처음에 입력한 비밀번호 rpwd
				// 비밀번호 확인 - rpwdconfirm
			if( rpwd == rpwdconfirm ){ // 마냐게 입력한 비밀번호와 비밀번호확인과 일치하면
				pwdcheckbox.innerHTML = '사용가능한 비밀번호'; checkList[1] = true;
			}else{
				pwdcheckbox.innerHTML = '비밀번호가 일치하지 않습니다.'; checkList[1] = false;
			}
				
		}else{
			pwdcheckbox.innerHTML = ' 영대소문자1개이상 숫자1개이상 조합 10~30글자 사이로 입력해주세요1.'; checkList[1] = false;
		}
	}else{
		pwdcheckbox.innerHTML = ' 영대소문자1개이상 숫자1개이상 조합 10~30글자 사이로 입력해주세요.2'; checkList[1] = false;
	}
	console.log(checkList[1]);
}//  f  e


































