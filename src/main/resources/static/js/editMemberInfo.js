//유저의 기존 옵션을 선택하기
function existingOption() {
    //html에 출력되어 있는 user의 기존정보 문자열을 가져옴
    const gender = document.querySelector("#userGender");
    const form = document.querySelector("#userForm");
    const tone = document.querySelector("#userTone");
    const field = document.querySelector("#userField");
    const age = document.querySelector("#userAge");
    const career = document.querySelector("#userCareer");

    //html에 input/option 버튼을 가져옴
    const genderList = document.querySelectorAll(".user_gender");
    const formList = document.querySelectorAll(".user_form");
    const toneList = document.querySelectorAll(".user_tone");
    const fieldList = document.querySelectorAll(".user_field");
    const ageList = document.querySelectorAll(".user_age");
    const careerList = document.querySelectorAll(".user_career");


    //기존정보와 input/option값을 비교해 같으면 해당 input/option에 체크/셀렉트
    genderList.forEach((genderList) => {
        if(genderList.value == gender.value) {
            genderList.checked == true;
        }
    })
    formList.forEach((formList) => {
        if(formList.value == form.value) {
            formList.selected == true;
        }
    })
    toneList.forEach((toneList) => {
        if(toneList.value == tone.value) {
            toneList.selected == true;
        }
    })
    fieldList.forEach((fieldList) => {
        if(fieldList.value == field.value) {
            fieldList.selected == true;
        }
    })
    ageList.forEach((ageList) => {
        if(ageList.value == age.value) {
            ageList.selected == true;
        }
    })
    careerList.forEach((careerList) => {
        if(careerList.value == career.value) {
            careerList.selected == true;
        }
    })

}

function Forminit(){
    existingOption();

    document.querySelector(".save").addEventListener("click", saveForm)
    var filmo = document.querySelectorAll(".filmo_body");

    function saveForm(){
        var data = {
                userNickname:$("#user_nickname").val(),
                userHeight:$("#userheight").val(),
                userForm:$("#user_form").val(),
                userTone:$("#user_tone").val(),
                userAge:$("#user_age").val(),
                userCareer:$("#user_career").val(),
                userSkill:$("#user_skill").val(),
                userEducation:$("user_education").val(),
                userContactLink:$("user_contact").val(),
                userEmail:$("#user_email").val()
        }
        $.ajax("/s/api/user/userConfigUpdate", {
                    type: "put",
                    dataType: "json",
                    data: JSON.stringify(data),
                    headers: {
                        "Content-Type": "application/json",
                    },
                }).done((res) => {
                    if (res.code == 1) {
                        console.log("asdasd");
                        alert("정보가 변경되었습니다.");
                        // location.href = "/user/update" + userId;
                    } else {
                        alert(" 정보를 다시 확인해주세요.");
                        return false;
                    }
                });
       console.log(data);
    }

}
window.addEventListener("load", Forminit);

// 과거 마이페이지 코드
// var iconImages = document.querySelectorAll(".icon_imgs");
// iconImages.forEach(function (iconImage) {
//   iconImage.addEventListener("click", function () {
//     changeImage(iconImage.src);
//   });
// });

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


//필모그래피 컨트롤
let filmoNum = 2;

let add = document.querySelector(".filmo_add");
let filmoTable = document.querySelector(".filmo_body");
add.addEventListener("click", addFilmo);

//필모 추가하기
function addFilmo() {
  console.log("버튼을 클릭했습니다");
  filmoNum += 1;
  console.log(filmoNum);

  let filmoCol = document.createElement("tr");
  filmoCol.id = filmoNum;
  filmoCol.innerHTML =
    '<tr><td><input id="title" type="text" /></td><td><input id="prod_year" type="number" value="" /></td><td><input id="role" type="text" value=""/></td><td><input id="genre" type="text" value="" /></td><td><input id="director" type="text" value="" /></td><td><input id="history" type="text" value="" /></td><td><button class="filmo_del">삭제</button></td></tr>';

  filmoTable.appendChild(filmoCol);
  activeDelBtns(); //생성한 행의 삭제버튼도 활성화
}

//제거 버튼 활성 함수
function activeDelBtns() {
  let delBtns = document.querySelectorAll(".filmo_del");

  for (let i = 0; i < delBtns.length; i++) {
    delBtns[i].addEventListener("click", function () {
      let tr = delBtns[i].parentElement.parentElement;
      filmoTable.removeChild(tr);
    });
  }

  filmoNum -= 1;
  console.log(filmoNum);
}

//삭제버튼 초기 활성화
activeDelBtns();

// function addFilmo() {
//
//   let filmoNum = 3;
//   let parser = new DOMParser();
//   let filmoCol =
//     '<tr><td><input id="title" type="text" /></td><td><input id="prod_year" type="number" value="" /></td><td><input id="role" type="text" value=""/></td><td><input id="genre" type="text" value="" /></td><td><input id="director" type="text" value="" /></td><td><input id="history" type="text" value="" /></td><td><button class="filmo_del">삭제</button></td></tr>';
//     // "<tr><td><input id='title' type='text' value='' /></td><td><input id='prod_year' type='number' value='' /></td><td><input id='role' type='text;' value=''/></td><td><input id='genre' type='text' value='' /></td><td><input id='director' type='text' value='' /></td><td><input id='history' type='text' value='' /></td><td><button class='filmo_del'>삭제</button></td></tr>";
//   let filmoColDom = parser.parseFromString(filmoCol, "text/html").body
//     .firstChild;
//   filmoColDom.id = ++filmoNum;

//   filmoNum++;
//   filmoTable.appendChild(filmoColDom);
// }

//이벤트리스너
// document
//   .querySelector(".btn_withdrawal")
//   .addEventListener("click", openWithdrawalConfirmer);
// document
//   .querySelector(".btn_withdrawal_close")
//   .addEventListener("click", closeWithdrawalConfirmer);
