
/* [ 사용자 화면 ]  사용자가 콜 보냈을때. */

function call() {
	gpsClientSocket.onmessage = (e) => {
		alert('수락')
	}

}


let sfromla = 0;
let sfromlo = 0;
let 현재주소 = "";
let stola = 0;
let stolo = 0;
let destination = "";
let 요청내용 = "";
let spayYN = "N";
let spayment = 0;



let JsonInfo = sessionStorage.getItem('info');
if (JsonInfo) {
	let info = JSON.parse(JsonInfo);

	let destinationAddress = info.address;

	// 현재 위치 가져오기
	navigator.geolocation.getCurrentPosition(function(position) {
		let currentLocation = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);


		// 도착지 주소를 좌표로 변환
		let geocoder = new kakao.maps.services.Geocoder();
		geocoder.addressSearch(destinationAddress, function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				let destinationLocation = new kakao.maps.LatLng(result[0].y, result[0].x);




				// 지도 생성
				var mapContainer = document.getElementById('map');
				var mapOption = {
					center: currentLocation, // 현재 위치를 중심으로 지도 생성
					level: 3
				};
				var map = new kakao.maps.Map(mapContainer, mapOption);

				var startSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png', // 출발 마커이미지의 주소입니다    
					startSize = new kakao.maps.Size(50, 45), // 출발 마커이미지의 크기입니다 
					startOption = {
						offset: new kakao.maps.Point(15, 43) // 출발 마커이미지에서 마커의 좌표에 일치시킬 좌표를 설정합니다 (기본값은 이미지의 가운데 아래입니다)
					};

				var startImage = new kakao.maps.MarkerImage(startSrc, startSize, startOption);

				var startDragSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_drag.png', // 출발 마커의 드래그 이미지 주소입니다    
					startDragSize = new kakao.maps.Size(50, 64), // 출발 마커의 드래그 이미지 크기입니다 
					startDragOption = {
						offset: new kakao.maps.Point(15, 54) // 출발 마커의 드래그 이미지에서 마커의 좌표에 일치시킬 좌표를 설정합니다 (기본값은 이미지의 가운데 아래입니다)
					};

				// 출발 마커의 드래그 이미지를 생성합니다
				var startDragImage = new kakao.maps.MarkerImage(startDragSrc, startDragSize, startDragOption);



				// 현재 위치 마커 생성
				let currentLocationMarker = new kakao.maps.Marker({
					position: currentLocation,

					map: map,
					image: startImage
				});

				// 출발 마커에 dragstart 이벤트를 등록합니다
				kakao.maps.event.addListener(currentLocationMarker, 'dragstart', function() {
					// 출발 마커의 드래그가 시작될 때 마커 이미지를 변경합니다
					currentLocationMarker.setImage(startDragImage);
				});

				// 출발 마커에 dragend 이벤트를 등록합니다
				kakao.maps.event.addListener(currentLocationMarker, 'dragend', function() {
					// 출발 마커의 드래그가 종료될 때 마커 이미지를 원래 이미지로 변경합니다
					currentLocationMarker.setImage(startImage);
				});

				var arriveSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png', // 도착 마커이미지 주소입니다    
					arriveSize = new kakao.maps.Size(50, 45), // 도착 마커이미지의 크기입니다 
					arriveOption = {
						offset: new kakao.maps.Point(15, 43) // 도착 마커이미지에서 마커의 좌표에 일치시킬 좌표를 설정합니다 (기본값은 이미지의 가운데 아래입니다)
					};

				// 도착 마커 이미지를 생성합니다
				var arriveImage = new kakao.maps.MarkerImage(arriveSrc, arriveSize, arriveOption);

				var arriveDragSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_drag.png', // 도착 마커의 드래그 이미지 주소입니다    
					arriveDragSize = new kakao.maps.Size(50, 64), // 도착 마커의 드래그 이미지 크기입니다 
					arriveDragOption = {
						offset: new kakao.maps.Point(15, 54) // 도착 마커의 드래그 이미지에서 마커의 좌표에 일치시킬 좌표를 설정합니다 (기본값은 이미지의 가운데 아래입니다)
					};


				var arriveDragImage = new kakao.maps.MarkerImage(arriveDragSrc, arriveDragSize, arriveDragOption);


				// 도착지 마커 생성
				let destinationMarker = new kakao.maps.Marker({
					position: destinationLocation,

					map: map,
					image: arriveImage
				});

				// 도착 마커에 dragstart 이벤트를 등록합니다
				kakao.maps.event.addListener(destinationMarker, 'dragstart', function() {
					// 도착 마커의 드래그가 시작될 때 마커 이미지를 변경합니다
					destinationMarker.setImage(arriveDragImage);
				});

				// 도착 마커에 dragend 이벤트를 등록합니다
				kakao.maps.event.addListener(destinationMarker, 'dragend', function() {
					// 도착 마커의 드래그가 종료될 때 마커 이미지를 원래 이미지로 변경합니다
					destinationMarker.setImage(arriveImage);
				});
				
				// 두 마커 간의 직선을 그리기 위한 Polyline 객체 생성
				var linePath = [currentLocation, destinationLocation]; // 마커의 좌표를 선의 경로로 지정
				var polyline = new kakao.maps.Polyline({
					path: linePath, // 선의 경로로 마커의 좌표를 지정
					strokeWeight: 3, // 선의 두께 설정
					strokeColor: '#FF0000', // 선의 색깔 설정
					strokeOpacity: 0.7, // 선의 투명도 설정
					strokeStyle: 'solid' // 선의 스타일 설정
				});

				// 지도에 선을 표시합니다
				let poly = polyline.getLength();
				console.log(poly) 
				polyline.setMap(map);
				
				// m 터 당 3원
				spayment = `${  ( parseInt( poly ) * 3) }`;
				
				// 기본요금 [  ] 
				if( poly <= 1000 ){
					spayment = '2500';
				}
				document.querySelector('.예상금액').innerHTML=`${ spayment.toLocaleString() } 원 [ ${ ( poly/1000 ).toFixed(1) }m ]`
				
				// 지도 영역 설정
				let bounds = new kakao.maps.LatLngBounds();
				bounds.extend(currentLocation);
				bounds.extend(destinationLocation);
				map.setBounds(bounds);
				
				



				// 장소 정보 출력
				let placeinfo = document.querySelector('.placeinfo');
				let html = `
                    <div>장소명 : ${info.name} </div>
                    <div>주소 : ${info.address} </div>
                    <div><input class = "ccontent" type = "text" placeholder="요청사항을 적어주세요!"></div>
                `;
				placeinfo.innerHTML = html;

				destination = { 장소명: info.name, 주소: info.address };
				console.log(destination);



			}
			stola = result[0].y;
			stolo = result[0].x;
		});

		sfromla = position.coords.latitude;
		sfromlo = position.coords.longitude;

		geocoder.coord2Address(sfromlo, sfromla, function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				현재주소 = result[0].address;
			}
			console.log(현재주소)
		});


	});





}


let userType = "user";
let no = loginMno;
let callClientSocket = new WebSocket(`ws://192.168.17.148:8080/ezenTeam1/callsocket/${userType}/${no}`);


// * [10/08] 사용자가 라이더에게 콜 요청 
document.querySelector('.call').addEventListener('click', (e) => {



	requestContent = document.querySelector('.ccontent').value;
	let callInfo = {
		type: "call",
		sfromla: sfromla, sfromlo: sfromlo, mno: loginMno, stola: stola, stolo: stolo, 요청내용: requestContent
		, 목적지: destination, 출발지: 현재주소 , spayYN : "H" , spayment : spayment
		
	}
	
	callClientSocket.send(JSON.stringify(callInfo));
	
	 window.location.href = "/ezenTeam1/gorider/service/mwaiting.jsp"; // 그리고 페이지전환하면.. 소켓 초기화 
	
});




