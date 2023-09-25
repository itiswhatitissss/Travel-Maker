const prevButton = document.querySelector("#prev");
const nextButton = document.querySelector("#next");
const carousel = document.querySelector("#carousel");
const images = document.querySelectorAll("#carousel-item");

let currentIndex = 0;

prevButton.addEventListener("click", () => {
    currentIndex = Math.max(currentIndex - 1, 0);
    updateCarousel();
});

nextButton.addEventListener("click", () => {
    currentIndex = Math.min(currentIndex + 1, images.length - 1);
    updateCarousel();
});

function updateCarousel() {
    const slideAmount = -currentIndex * 600;
    carousel.style.transform = `translateX(${slideAmount}px)`;
}

document.addEventListener('DOMContentLoaded', function() {
    const startInput = document.getElementById('start');
    const endInput = document.getElementById('end');

    startInput.addEventListener('change', function() {
        const selectedDate = startInput.value;
        const localDate = new Date(selectedDate);
        const formattedDate = localDate.toISOString().substring(0, 10); // YYYY-MM-DD
        endInput.min = formattedDate; // Set the minimum value for endInput
    });
});

//plan 삭제 by AJAX
var planToDelete = null;

function showDeleteConfirmationModal(plno) {
    planToDelete = plno;
    $('#deleteConfirmationModal').modal('show');
}

function deletePlan() {
    if (planToDelete !== null) {
        var url = "deletePlan?plno=" + planToDelete;

        $.ajax({
            type: "POST",
            url: url,
            success: function (response) {
                if (response === "success") {
                    $('#deleteConfirmationModal').modal('hide');
                    location.reload();
                    $('#successModal').modal('show');
                } else {
                    alert("삭제 실패");
                }
            },
            error: function () {
                alert("오류가 발생했습니다.");
            }
        });

        planToDelete = null;
    }
    //plan 삭제 by AJAX
}

function setThumbnail(event) {
    var reader = new FileReader();
    reader.onload = function (event) {
        var img = document.createElement("img");
        img.setAttribute("src", event.target.result);
        document.querySelector("div#image_container").appendChild(img);
    };
    reader.readAsDataURL(event.target.files[0]);
}

var uno = document.getElementById("autoCompleteUno").value;

$(document).ready(function() {
    $("#autoCompleteInput").autocomplete({
        source: function(request, response) {

            console.log("request", request)
            console.log("response", response)
            console.log("uno",uno)

            $.ajax({
                url: "/travelmaker/main/autocomplete",
                type: "POST",
                dataType: "json",
                data: {
                    keyword: request.term,uno:uno
                },
                success: function(data) {
                    response(data.resultList);
                },
                error: function() {
                    alert("검색 중 오류가 발생했습니다.");
                }
            });
        },
        minLength: 1
        , // 자동완성 시작을 위한 최소 입력 길이
        select: function(event, ui) {
            var selectedPartyName = ui.item.value; //ui.item.value => partyname이 들어있음
            console.log("selectedPartyname",selectedPartyName)
            var encodedPartyName = encodeURIComponent(selectedPartyName);//인코딩
            location.href = "/travelmaker/main/searchParty?partyName=" + encodedPartyName +"&uno="+uno;
        }
    });
});


$(document).ready(function() {
    // 수정 모달을 띄우기 위한 이벤트 처리
    $(".modify-plan").on("click", function() {
        var plno = $(this).data("plno");
        $.ajax({
            url: "/travelmaker/main/getPlan", // 플랜 정보를 가져올 URL
            type: "GET",
            data: {
                plno: plno
            },
            success: function(plan) {
                $("#modPlno").val(plan.plno);
                $("#modTitle").val(plan.title);
                $("#modStart").val(plan.start);
                $("#modEnd").val(plan.end);
                $("#modifyPlanModal").modal("show");
            },
            error: function() {
                alert("플랜 정보를 가져오는 중 오류가 발생했습니다.");
            }
        });
    });

    // 수정 모달의 'Save' 버튼 클릭 이벤트 처리
    $("#modifySaveButton").on("click", function() {
        var form = $("#modifyPlanForm");
        $.ajax({
            url: "/travelmaker/main/modifyPlan", // 플랜 정보를 수정할 URL
            type: "POST",
            data: form.serialize(),
            success: function(response) {
                if (response === "success") {
                    $("#modifyPlanModal").modal("hide");
                    location.reload();
                    $('#successModal').modal('show');
                } else {
                    alert("플랜 수정 실패");
                }
            },
            error: function() {
                alert("플랜 정보 수정 중 오류가 발생했습니다.");
            }
        });
    });
});

//툴팁이랑 모달 호환하기
$(document).ready(function () {
    $('[data-bs-toggle="tooltip"]').tooltip();
});

document.querySelectorAll('[data-bs-toggle="tooltip"]').forEach(function (button) {
    button.addEventListener('click', function () {
        const targetModalId = button.getAttribute('data-bs-target');
        $(targetModalId).modal('show');
    });
});


let partyMemberList = document.querySelector(".partyMemberList");
let partyFriendList = document.querySelector(".partyFriendList");

$(document).ready(function () {
    // 수정 모달을 띄우기 위한 이벤트 처리
    $(".modify-pno").on("click", function () {

        let str = '';
        let str1= '';

        var pno = $(this).data("pno");
        console.log("pno======>", pno);
        var uno = document.getElementById("autoCompleteUno").value;
        console.log("uno======>", uno);

        $.ajax({
            url: "getPartyModify", // 플랜 정보를 가져올 URL
            type: "GET",
            data: {
                pno: pno,
                uno: uno
            },
            success: function (partylist) {
                if (partylist.length > 0) {
                    // partylist 배열에서 원하는 값들을 추출하여 사용
                    for (var i = 0; i < partylist.length; i++) {
                        var Party = partylist[i];
                        if (Party.title !== undefined) {
                            $("#modiTitle").val(Party.title);
                            console.log("title=========>", Party.title);
                        }
                        if (Party.pno !== undefined) {
                            $("#modiPno").val(Party.pno);
                        }
                        if (Party.fno !== undefined) {
                            $("#modifno").val(Party.fno);
                            str1 += '<input type="checkbox" name="selectedFriends" value="' + Party.fno + '" required>';
                        }
                        if (Party.name !== undefined) {
                            $("#modiname").val(Party.name);
                            console.log("Party.........NAME =========>", Party.name)
                            str1 += '<h7>'+ Party.name +'</h7><br>';
                        }
                        if (Party.member !== undefined){
                            $("#modiuser").val(Party.member);
                            console.log("Party.............MEMBER ==============>", Party.member)
                            str += '<li style="color: #141744;">' + Party.member + '</li>';
                        }
                        // if (Party.member !== undefined) {
                        //     str += '<input type="text" class="form-control" value="' + Party.member + '" required>';
                        // }

                    }

                    partyMemberList.innerHTML = str;
                    partyFriendList.innerHTML = str1;

                    $("#modifyPartyModal").modal("show");
                } else {
                    alert("파티 정보가 없습니다.");
                }
            },
            error: function () {
                alert("파티 정보를 가져오는 중 오류가 발생했습니다.");
            }
        });
    });

    // 수정 모달의 'Save' 버튼 클릭 이벤트 처리
    $("#modifyPartySaveButton").on("click", function () {
        // var form = $("#modifyPartyForm");
        var pno = $("#modiPno").val();
        // var uno = $("#modiUno").val();
        var title = $("#modiTitle").val();
        var fnoList = [];
        console.log("title===============>",title)
        $("input[name='selectedFriends']:checked").each(function () {
            fnoList.push($(this).val());
        });
        var fnoListString = fnoList.join(",");
        console.log("fnolist===============>",fnoList)
        $.ajax({
            url: "partyModify", // 플랜 정보를 수정할 URL
            type: "POST",
            data: {
                pno: pno,
                // uno: uno,
                title: title,
                fnoList: fnoListString
            },
            success: function (response) {
                console.log("response==============>",response)
                if (response === "success") {
                    $("#modifyPartyModal").modal("hide");
                    location.reload();
                    $('#successModal').modal('show');
                } else {
                    alert("플랜 수정 실패");
                }
            },
            error: function () {
                alert("플랜 정보 수정 중 오류가 발생했습니다.");
            }
        });
    });
});

//친구 리스트

$(document).ready(function () {

    // 수정 모달을 띄우기 위한 이벤트 처리
    $("a[data-bs-target='#friendModal']").on("click", function () {
        // 현재 페이지 URL을 가져옵니다.
        var currentUrl = window.location.href;
// URL에서 파라미터를 추출합니다.
        var urlParams = new URLSearchParams(currentUrl.split('?')[1]);
// "uno" 파라미터의 값을 가져옵니다.
        var uno = urlParams.get("uno");
        console.log("uno 값:", uno);

        let FriendList ="";
        FriendList = document.querySelector(".viewFriendList");
        console.log("FriendList=======>", FriendList)

        // // searchText 값을 가져옵니다.
        // var searchText = $("#friendSearchInput").val();
        //
        // // searchList 모달에 searchText를 전달합니다.
        // $("#friendSearchModal").data("searchText", searchText);

        let str2= '';

        $.ajax({
            url: "getFriend", // 플랜 정보를 가져올 URL
            type: "GET",
            data: {
                uno: uno
            },
            success: function (friendList) {
                if (friendList.length > 0) {

                    for (var i = 0; i < friendList.length; i++) {
                        var friend = friendList[i];

                            str2 += '<input type="checkbox" name="selectedFriends" value="' + friend.fno + '" required>'+'<h7>'+ friend.name +'</h7><br>';
                    }

                    FriendList.innerHTML = str2;
                    console.log("FriendList str2 =======>", FriendList)

                    $("#friendModal").modal("show");
                } else {
                    alert("친구 정보가 없습니다.");
                }
            },
            error: function () {
                alert("친구 정보를 가져오는 중 오류가 발생했습니다.");
            }
        });
    });

    // 수정 모달의 '삭제' 버튼 클릭 이벤트 처리
    $("#friendDeleteButton").on("click", function () {
        var fnoList = [];
        $("input[name='selectedFriends']:checked").each(function () {
            fnoList.push($(this).val());
        });
        var fnoListString = fnoList.join(",");
        console.log("fnolist===============>",fnoList)
        $.ajax({
            url: "friendDelete",
            type: "POST",
            data: {
                uno:uno,
                fnoList: fnoListString
            },
            success: function (response) {
                console.log("response==============>",response)
                if (response === "success") {
                    location.reload();
                    $('#successModal').modal('show');
                } else {
                    alert("친구 삭제 실패");
                }
            },
            error: function () {
                alert("친구 삭제 중 오류가 발생했습니다.");
            }
        });
    });
});

// 친구 검색
$(document).ready(function () {

    // 서치 모달을 띄우기 위한 이벤트 처리
    $(".friend-search").on("click", function () {

        var searchText = $("#SearchInput").val(); // 검색어 가져오기

        // searchText 값을 가져옵니다.
        // var searchText = $(this).data("searchText");
        console.log("searchText : ",searchText)

        $("#friendModal").modal("hide");

        let FriendList ="";
        FriendList = document.querySelector(".viewFriendList");
        console.log("FriendList=======>", FriendList)

        let str3= '';
        //서치텍스트 가져오기

        $.ajax({
            url: "friendSearch", // 플랜 정보를 가져올 URL
            type: "GET",
            data: {
                searchText : searchText
            },
            success: function (search) {
                if (search.length > 0) {
                    console.log("searchList : ",search);

                    for (var i = 0; i < search.length; i++) {
                        var friend = search[i];
                        console.log("friend : ",friend)

                        str3 += '<input type="checkbox" name="selectedFriends" value="' + friend.uno + '" required>'+'<h7>'+ friend.name +'</h7><br>';
                    }

                    FriendList.innerHTML = str3;
                    console.log("FriendList str3 =======>", FriendList)

                    $("#friendSearchModal").modal("show");
                } else {
                    alert("검색 결과가 없습니다.");
                }
            },
            error: function () {
                alert("검색 결고를 가져오는 중 오류가 발생했습니다.");
            }
        });
    });

    // 수정 모달의 '삭제' 버튼 클릭 이벤트 처리
    $("#friendDeleteButton").on("click", function () {
        var fnoList = [];
        $("input[name='selectedFriends']:checked").each(function () {
            fnoList.push($(this).val());
        });
        var fnoListString = fnoList.join(",");
        console.log("fnolist===============>",fnoList)
        $.ajax({
            url: "friendDelete",
            type: "POST",
            data: {
                uno:uno,
                fnoList: fnoListString
            },
            success: function (response) {
                console.log("response==============>",response)
                if (response === "success") {
                    location.reload();
                    $('#successModal').modal('show');
                } else {
                    alert("친구 삭제 실패");
                }
            },
            error: function () {
                alert("친구 삭제 중 오류가 발생했습니다.");
            }
        });
    });
});