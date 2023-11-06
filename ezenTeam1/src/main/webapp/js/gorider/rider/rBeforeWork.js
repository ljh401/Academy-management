

console.log('rBeforeWork.js');


if(loginState == true){ // 로그인이 되있으면
	document.querySelector('.rname').innerHTML = loginRname;
//	document.querySelector('.rbikenum').innerHTML = loginRbikenum;

	/* nlevel0 ~nlevel2 문자열 잘라서 넣기 */
	// 바이크 번호는 지역(nlevel0) /구분문자(nlevel1)/번호4자리(nlevel2)로 구성
	// 맨마지막 인덱스(-1)에서 왼쪽으로 -2, -3, -4 .. 인덱스를 가지므로
	let rbikenum = loginRbikenum.replace(/ /g, ''); //문자열의 공백 제거
	console.log("after replace :: "+rbikenum);
	document.querySelector('.nlevel0').innerHTML = rbikenum.slice(0,-5);
	document.querySelector('.nlevel1').innerHTML = rbikenum.substr(-5,1);
	//맨마지막 인덱스에서 왼쪽으로 4개 : 번호판에서 번호에 해당
	document.querySelector('.nlevel2').innerHTML = rbikenum.substr(-4);

}else{
	alert('로그인 후 사용해주세요.');
	location.href="/ezenTeam1/gorider/rider/rlogin.jsp";
}

let callstate = 'Y';
function goWork(){

	/* riderState 테이블 업데이트 후 */
	location.href = '/ezenTeam1/gorider/rider/rWorking.jsp'


}

function outWork(){

	/* riderState 테이블 업데이트 후 */
	window.close();

}
function onMove(to){

	if(to == 'myMenu'){
		location.href = '/ezenTeam1/gorider/rider/rMyMenu.jsp';
	} else if(to == 'goBack'){
		window.history.back();
	}else if(to == 'callList'){
		location.href = '/ezenTeam1/gorider/rider/rCallListg.jsp'
	}



}
let currentIndex = 0;
var colors = ['#EBC12F',  '#90772B', '#4D421B' ]
function changeColor() {

    console.log(colors[currentIndex]);
    document.querySelector('.dot1').style.color = colors[currentIndex];
	document.querySelector('.dot2').style.color = colors[Math.abs(currentIndex-1)];
	document.querySelector('.dot3').style.color = colors[Math.abs(currentIndex-2)];

	currentIndex = (currentIndex + 1) % colors.length;
}
// setInterval(changeColor, 800);






