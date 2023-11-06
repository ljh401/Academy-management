
/*[ 라이더 화면 ] 라이더가 수락 했을대. */


let userType = "rider";
let no = loginRno;
let rmessage = "";
let 라이더마커 = null;
let markerPosition1 = "";
let sriderla = 37.320682;
let sriderlo = 126.832668;
let outmessage ="";


navigator.geolocation.getCurrentPosition(e => {
    sfromla = e.coords.latitude;
    sfromlo = e.coords.longitude;
});

let gpsClientSocket = null // 라이더 위치 정보 ;

// gps 이동 메시지 보냈을때..
function gpsMove( way ){
	if( way == 'right') sriderlo += 0.0010;
	if( way == 'left') sriderlo -= 0.0010;
	if( way == 'up') sriderla += 0.0010;
	if( way == 'down') sriderla -= 0.0010;
	gpsClientSocket.send(JSON.stringify({ sfromla: sriderla, sfromlo: sriderlo }));
}
// gps 이동 메시지 받았을때
function gpsRecevie(e){
	let data = JSON.parse(e.data);
    console.log(data);
    sriderla = data.sfromla;
    sriderlo = data.sfromlo;
    마커셋팅();
}

let map = null;

let mno = 0; // 라이더와 현재 연결된 회원번호;

// 수락 했을때.. 
function accept( sno ) {
	

	callClientSocket = new WebSocket(`ws://192.168.17.148:8080/ezenTeam1/callsocket/${userType}/${no}`); // 라이더 콜 정보 


    let contentBox = document.querySelector('.accept');
   
   callClientSocket.onopen = function(event) {
    
 
    let riderInfo = {
		type: "accept",
		sno : sno ,
		rno : loginRno, sriderla: sriderla, sriderlo: sriderlo}
		callClientSocket.send(JSON.stringify(riderInfo));
	};
    
    
    let html = `
        <button type="button" onclick="gpsMove('right')" class="rightBtn">앞으로</button>
        <button type="button" onclick="gpsMove('left')" class="leftBtn">뒤로</button>
        <button type="button" onclick="gpsMove('up')" class="topBtn">위로</button>
        <button type="button" onclick="gpsMove('down')" class="bottomBtn">아래로</button>
        <button type="button" onclick="getOn(${sno})" class = "onBtn">탑승</button>
        <button type="button" onclick="getOut(${sno})" class = "outBtn">하차</button>
    `;
    contentBox.innerHTML = html;
    
    // 수락했을때.. gps 소켓 연결
    gpsClientSocket = new WebSocket(`ws://192.168.17.148:8080/ezenTeam1/gpssocket/${sno}`); // 라이더 위치 정보 ;
    gpsClientSocket.onmessage = (e) => { gpsRecevie(e) };
}

// 탑승
function getOn( sno ){
	let outinfo = { type : "on" , mno : mno , rno : loginRno , sno : sno };
	callClientSocket.send( JSON.stringify(outinfo));
}
// 하차 
function getOut(sno){
	// 1. 하차 이벤트 처리 
		// 3. 유저에게 메시를 보내서 하차 하라고 보내기.
		let outinfo = { type : "out" , mno : mno , rno : loginRno , sno : sno };
		callClientSocket.send( JSON.stringify(outinfo));
		mno = 0;
}

let callClientSocket = new WebSocket(`ws://192.168.17.148:8080/ezenTeam1/callsocket/${userType}/${no}`);
console.log(callClientSocket)
callClientSocket.onmessage = (e) => {
	
    alert('통신');
    let jsonData = JSON.parse(e.data);
    console.log('userType : ' + userType)
    console.log(jsonData);
    
   // 1.  수락했을때 지도 띄우기
    if( jsonData.type == 'call'){
		getAccept( jsonData );
	}else if( jsonData.type == 'out'){
		// 4. 페이지 전환. http://192.168.17.148:8080/ezenTeam1/gorider/rider/rWorking.jsp
		location.href="/ezenTeam1/gorider/rider/rWorking.jsp";
	}
}
 
//   
function getAccept( jsonData ){
	let callcontent = document.querySelector('.callcontent');
    
    mno = jsonData.mno;
    
    let html = `
        <h3> 콜 도착 </h3>
        <div id="map" style="width:100%;height:350px;"></div>
        <div class="mno">고객 아이디 : ${jsonData.mno}</div>
        <div class="start">고객 위치 : ${jsonData.출발지.address_name}</div>
        <div class="end">목적지 : ${jsonData.목적지.주소} ${jsonData.목적지.장소명}</div>
        <div class="call">요청내용 : ${jsonData.요청내용}</div>
        <div class="choicebox">
            <button onclick="accept(${ jsonData.sno })" type="button" class="accept">수락</button>
            <button onclick="reject()" type="button" class="reject">거절</button>
        </div>
    `;

    callcontent.innerHTML = html;
    
    let mapContainer = document.getElementById('map');
	let mapOption = {
	    center: new kakao.maps.LatLng(jsonData.sfromla, jsonData.sfromlo),
	    level: 4
	};
	
	map = new kakao.maps.Map(mapContainer, mapOption);

    let startSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png';
    let startSize = new kakao.maps.Size(50, 45);
    let startOption = { offset: new kakao.maps.Point(15, 43) };

    let startImage = new kakao.maps.MarkerImage(startSrc, startSize, startOption);

    let startDragSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_drag.png';
    let startDragSize = new kakao.maps.Size(50, 64);
    let startDragOption = { offset: new kakao.maps.Point(15, 54) };

    let startDragImage = new kakao.maps.MarkerImage(startDragSrc, startDragSize, startDragOption);

    let markerPosition1 = new kakao.maps.LatLng(jsonData.sfromla, jsonData.sfromlo);

    let marker1 = new kakao.maps.Marker({
        position: markerPosition1,
        map: map,
        image: startImage
    });

    kakao.maps.event.addListener(marker1, 'dragstart', function() {
        marker1.setImage(startDragImage);
    });

    kakao.maps.event.addListener(marker1, 'dragend', function() {
        marker1.setImage(startImage);
    });

    let arriveSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png';
    let arriveSize = new kakao.maps.Size(50, 45);
    let arriveOption = {
        offset: new kakao.maps.Point(15, 43)
    };

    let arriveImage = new kakao.maps.MarkerImage(arriveSrc, arriveSize, arriveOption);

    let arriveDragSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_drag.png';
    let arriveDragSize = new kakao.maps.Size(50, 64);
    let arriveDragOption = {
        offset: new kakao.maps.Point(15, 54)
    };

    let arriveDragImage = new kakao.maps.MarkerImage(arriveDragSrc, arriveDragSize, arriveDragOption);

    let markerPosition2 = new kakao.maps.LatLng(jsonData.stola, jsonData.stolo);

    let marker2 = new kakao.maps.Marker({
        position: markerPosition2,
        map: map,
        image: arriveImage
    });

    kakao.maps.event.addListener(marker2, 'dragstart', function() {
        marker2.setImage(arriveDragImage);
    });

    kakao.maps.event.addListener(marker2, 'dragend', function() {
        marker2.setImage(arriveImage);
    });

    let riderSrc = '/ezenTeam1/img/gorider/icon.png';
    let riderSize = new kakao.maps.Size(50, 45);
    let riderOption = {
        offset: new kakao.maps.Point(15, 43)
    };

    let riderImage = new kakao.maps.MarkerImage(riderSrc, riderSize, riderOption);

    let 라이더위치 = new kakao.maps.LatLng(sriderla, sriderlo);

    라이더마커 = new kakao.maps.Marker({
        position: 라이더위치,
        map: map,
        image: riderImage
    });
    
    
    let bounds = new kakao.maps.LatLngBounds();
    bounds.extend(markerPosition1);
    bounds.extend(markerPosition2);

    map.setBounds(bounds);
}

// 이동 된 라이더 마커 다시 셋팅 
function 마커셋팅() { 

	라이더마커.setMap(null); // 기존 마커 없애고
 	
    let riderSrc = '/ezenTeam1/img/gorider/icon.png';
    let riderSize = new kakao.maps.Size(50, 45);
    let riderOption = {
        offset: new kakao.maps.Point(15, 43)
    };
    
    let riderImage = new kakao.maps.MarkerImage(riderSrc, riderSize, riderOption);

    let 라이더위치 = new kakao.maps.LatLng(sriderla, sriderlo);

    라이더마커 = new kakao.maps.Marker({ // 라이더 마커
        position: 라이더위치,
        image: riderImage
    });

    라이더마커.setMap(map);
    map.setCenter( 라이더위치 );
}


