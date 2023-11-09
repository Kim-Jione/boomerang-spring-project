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
    alert(filmo);

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
