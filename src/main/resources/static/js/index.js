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
    const slideAmount = -currentIndex * 500;
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