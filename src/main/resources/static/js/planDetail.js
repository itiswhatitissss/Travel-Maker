var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.265642537105556, 127.00007891675567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

document.addEventListener('DOMContentLoaded', function () {
    var plno = document.getElementById("plno").value;
    fetchAndDisplayMarkers(plno);
});


//-----------------마커 찍고 DB저장 시작-------------------------//
kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
    // 좌표 저장
    var position = mouseEvent.latLng;

    // 모달 열기
    $('#writeTitle').modal('show');

    $('#saveButton').off('click').on('click', function () {
        // 입력된 title 가져오기
        var title = $('#markerTitle').val();

        // DB에 데이터 저장
        addMarkerAndSaveToServer(position, title);

        // 모달 닫기
        $('#writeTitle').modal('hide');

        location.reload();
    });
});
//-----------------마커 찍고 DB저장  끝-------------------------//


//-----------------DB 저장 post 시작-------------------------//
function addMarkerAndSaveToServer(position, title) {
    // 좌표 및 title 전송
    var lat = position.getLat();
    var lng = position.getLng();
    var plno = document.getElementById("plno").value;

    $.ajax({
        type: "POST",
        dataType: "json",
        url: "saveMarker",
        data: {lat: lat, lng: lng, plno: plno, title: title},
        success: function () {
        },
        error: function () {
        }
    });
}

//-----------------DB 저장 post 끝-------------------------//


//-----------------찍혀있는 마커 보여주기 시작-------------------------//
function addMarker(position, title,lat,lng) {
    var marker = new kakao.maps.Marker({
        position: position
    });

    var iwContent = '<div style="padding:5px;">' + title + ' <br><a href="https://map.kakao.com/link/map/' + title + ',' + lat + ',' + lng + '" style="color:blue" target="_blank">카카오맵</a> <a href="https://map.kakao.com/link/to/' + title + ',' + lat + ',' + lng + '" style="color:blue" target="_blank">길찾기</a></div>';

    var infowindow = new kakao.maps.InfoWindow({
        content: iwContent
    });

    // 마커를 지도에 표시
    marker.setMap(map);

    // 마커 클릭 시 인포윈도우 열기
    infowindow.open(map, marker);
}

function fetchAndDisplayMarkers(plno) {
    $.ajax({
        type: "GET",
        url: "getMarkers", // 서버의 엔드포인트를 지정해야 합니다.
        data: {plno: plno},
        dataType: "json",
        success: function (data) {
            data.forEach(function (markerData) {
                var position = new kakao.maps.LatLng(markerData.lat, markerData.lng);
                var title = markerData.title;

                addMarker(position, title, markerData.lat, markerData.lng); // title 추가
            });
        },
        error: function () {
        }
    });
}

//-----------------찍혀있는 마커 보여주기 끝-------------------------//


document.addEventListener('DOMContentLoaded', function () {
    var resetButton = document.querySelector('#deleteMarker');
    resetButton.addEventListener('click', function () {
        var plno = document.getElementById("plno").value;
        deleteMarker(plno);
    });
});

function deleteMarker(plno) {
    $.ajax({
        type: "POST",
        url: "deleteMarkers",
        data: {plno: plno},
        dataType: "json",
        success: function () {
        },
        error: function () {
            location.reload();
        }
    });
}

// "참석" 체크박스를 선택하면 "불참" 체크박스 선택 해제
document.querySelector('input[name="attendance"][value="1"]').addEventListener('change', function () {
    if (this.checked) {
        document.querySelector('input[name="attendance"][value="0"]').checked = false;
    }
});

// "불참" 체크박스를 선택하면 "참석" 체크박스 선택 해제
document.querySelector('input[name="attendance"][value="0"]').addEventListener('change', function () {
    if (this.checked) {
        document.querySelector('input[name="attendance"][value="1"]').checked = false;
    }
});

//지도 + 갤러리
document.querySelector('#photo').addEventListener('click', function () {
    // 지도 섹션 숨김
    document.getElementById('map-section').style.display = 'none';

    // 갤러리 섹션 표시
    document.getElementById('gallery-section').style.display = 'block';
});

document.getElementById('galleryPlus').addEventListener('click', function () {
    $('#addPhotoModal').modal('show');
});
