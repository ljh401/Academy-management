let no = loginMno;

/* [ 사용자 화면 ] 라이더가 수락 했을때*/

let message = document.querySelector('.message');

let html = `<h1>매칭 대기중입니다 잠시만 기다려주세요</h1>`;

message.innerHTML = html;

let callClientSocket = new WebSocket(`ws://192.168.17.148:8080/ezenTeam1/callsocket/user/${no}`);

let gpsClientSocket = null;

callClientSocket.onmessage = (e) => {
    let jsonData = JSON.parse(e.data);
    console.log(jsonData);
    
    // 1.  수락했을때 지도 띄우기
    if( jsonData.type == 'accept'){
		handleMatchSuccess( jsonData);
   		 // 콜 수락을 라이더로부터 받았을때. 유저는 gps소켓에 들어감.
   		if(gpsClientSocket== null ){
				gpsClientSocket = new WebSocket(`ws://192.168.17.148:8080/ezenTeam1/gpssocket/${ jsonData.sno }`);
		}
	}else if( jsonData.type == 'out'){
		getOut(); // 2. 하차.
	}else if( jsonData.type == 'on'){
		alert('라이더에 탑승했습니다.. 안전하게 하차후 결제 해주세요..');
	}
};


// 매칭 성공 시 화면 업데이트 함수
function handleMatchSuccess(jsonData) {
    let message = document.querySelector('.message');

    let html = `
        <h3> 매칭 성공 </h3>
        <div id="map" style="width:100%;height:350px;"></div>
        <div class="riderName">라이더 이름: ${jsonData.rname}</div>
        <div class="bikeNum">오토바이 번호: ${jsonData.rbikenum}</div>
    `;
    message.innerHTML = html;

    // 라이더 위치를 지도에 표시
    waitingMap(jsonData);
}

// 라이더 위치를 지도에 표시하는 함수 (카카오맵 API를 사용한 예제)
function waitingMap(jsonData) {
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

    let 라이더위치 = new kakao.maps.LatLng(jsonData.sriderla, jsonData.sriderlo);

    라이더마커 = new kakao.maps.Marker({
        position: 라이더위치,
        map: map,
        image: riderImage
    });
    
    
    let bounds = new kakao.maps.LatLngBounds();
    bounds.extend(markerPosition1);
    bounds.extend(markerPosition2);

    map.setBounds(bounds);
     map.setCenter( 라이더위치 );
}



// 하차 
function getOut(){
	// 1.하차 이벤트 처리
	
		
		// 2. 페이지 전환 [ 리뷰 페이지 ]
	// 테스트 
	location.href="/ezenTeam1/gorider/member/mindex.jsp";
}








