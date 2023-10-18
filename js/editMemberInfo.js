/* 비밀번호 검사 컨트롤*/
//유효성 검사. 하나 이상의 영어대소문자와 숫자, 특수문자 포함, 8글자 이상.
function isPasswd() {
  const condition =
    /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

  return condition.test(passwd);
}

//비밀번호 확인 검사
function isSamePasswd() {
  if (passwd.value !== passwdConfirm.value) {
    // 비밀번호와 비밀번호 확인 값이 다를 경우 경고를 표시
    document.getElementById("passwordError").style.display = "block";
  } else {
    // 두 값이 같을 때 경고를 숨김
    document.getElementById("passwordError").style.display = "none";
  }
}

let passwd = document.querySelector("#user_password");
let passwdConfirm = document.querySelector("#user_password_confirm");

passwdConfirm.addEventListener("input", isSamePasswd);

/* 프로필 이미지 변환 컨트롤 */
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
  //이미지 변경 후, 변경된 이미지 주소를 서버로 보내는 부분 필요. (조장 문의)
  closeImageSelector();
}

//이벤트리스너
document.querySelector(".icon").addEventListener("click", openImageSelector);
document.querySelector("#close").addEventListener("click", closeImageSelector);

var iconImages = document.querySelectorAll(".icon_imgs");
iconImages.forEach(function (iconImage) {
  iconImage.addEventListener("click", function () {
    changeImage(iconImage.src);
  });
});

/* 회원 탈퇴 컨트롤 */
//회원탈퇴 창 열기
function openWithdrawalConfirmer() {
  let confirmer = document.querySelector(".confirm_withdrwal");
  confirmer.style.display = "block";
}

//회원탈퇴 창 닫기
function closeWithdrawalConfirmer() {
  let confirmer = document.querySelector(".confirm_withdrwal");
  confirmer.style.display = "none";
}

//이벤트리스너
document
  .querySelector(".btn_withdrawal")
  .addEventListener("click", openWithdrawalConfirmer);
document
  .querySelector(".btn_withdrawal_close")
  .addEventListener("click", closeWithdrawalConfirmer);
