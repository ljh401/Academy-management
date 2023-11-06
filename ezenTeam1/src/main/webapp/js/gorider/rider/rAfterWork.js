
console.log('rAfterWork.js');


if(loginState == true){ // 마냐게 로그인이 되있으면 
	document.querySelector('.rname').innerHTML = loginRname;
}else if( loginState == true ){
	document.querySelector('.rbikenum').innerHTML = loginRbikenum;
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






