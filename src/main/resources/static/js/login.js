const loginsec=document.querySelector('.login-section')
const loginlink=document.querySelector('.login-link')
const registerlink=document.querySelector('.register-link')
registerlink.addEventListener('click',()=>{
    loginsec.classList.add('active')
})
loginlink.addEventListener('click',()=>{
    loginsec.classList.remove('active')
})

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