/* 비밀번호 검사 */
//유효성 검사. 하나 이상의 영어대소문자와 숫자, 특수문자 포함, 8글자 이상.
function isPasswd() {
  const condition =
    /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

  return condition.test(passwd);
}

//비밀번호 확인 검사
function isSamePasswd() {
  
  if(passwd.value !== passwdConfirm.value) {
    // 비밀번호와 비밀번호 확인 값이 다를 경우 경고를 표시
    document.getElementById("passwordError").style.display = "block";
  } else {
    // 두 값이 같을 때 경고를 숨김
    document.getElementById("passwordError").style.display = "none";
}
}

let passwd = document.querySelector('#user_password')
let passwdConfirm = document.querySelector('#user_password_confirm')

passwdConfirm.addEventListener('input', isSamePasswd);



/* 프로필 이미지 변환기능 */
//이미지 셀렉터 열기
function openImageSelector() {
  let imageSelector = document.getElementById("imageSelector");
  imageSelector.style.display = "block";
}
//이미지 셀렉터 닫기
function closeImageSelector() {
  let imageSelector = document.getElementById("imageSelector");
  imageSelector.style.display = "none";
}
//선택한 이미지로 프로필 이미지 변경
function changeImage(newImageSrc) {
  document.getElementById("icon_img").src = newImageSrc;
  closeImageSelector();
}

document.querySelector(".icon").addEventListener("click", openImageSelector);
document.querySelector("#close").addEventListener("click", closeImageSelector);

var iconImages = document.querySelectorAll(".icon_imgs");
iconImages.forEach(function (iconImage) {
    iconImage.addEventListener("click", function() {
      changeImage(iconImage.src);
    });
  });