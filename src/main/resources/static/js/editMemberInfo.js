function Passwdinit() {
    // 비밀번호 수정 //
    document.querySelector(".edit_passwd").addEventListener("click", openPasswdChanger);
    document.querySelector("#closeChanger").addEventListener("click", closePasswdChanger);
    document.querySelector("#saveChanger").addEventListener("click", savePasswdChanger);

    // 비밀번호 변경 열기
    function openPasswdChanger() {
        let PasswdChanger = document.querySelector(".passwordChanger");
        PasswdChanger.style.display = "flex";
    }

    // 비밀번호 변경 닫기
    function closePasswdChanger() {
        let PasswdChanger = document.querySelector(".passwordChanger");
        PasswdChanger.style.display = "none";
    }

    // 패스워드 구조 체크
    function isPasswd(passwd) {
        const condition = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
        return condition.test(passwd);
    }

    passwdConfirm.addEventListener("input", isSamePasswd);

    // 비밀번호 확인 검사
    function isSamePasswd() {
        var passwd = document.getElementById("userPassword");
        var passwdConfirm = document.getElementById("userPasswordConfirm");

        if (passwd.value !== passwdConfirm.value) {
            // 비밀번호와 비밀번호 확인 값이 다를 경우 경고를 표시
            document.getElementById("passwordError").style.display = "flex";
        } else {
            // 두 값이 같을 때 경고를 숨김
            document.getElementById("passwordError").style.display = "none";
        }
    }

    function savePasswdChanger() {
        var userPasswd = document.getElementById("userPassword");
        var userPasswdConf = document.getElementById("userPasswordConfirm");

        if (userPasswd.value !== userPasswdConf.value) {
            alert("비밀번호가 일치하지 않습니다.");
        }

        if (userPasswd.value.length < 1 || userPasswdConf.value.length < 1) {
            alert("비밀번호를 입력해주셔야 합니다.");
        }

        var data = {
            userPassword:$("#userPassword").val()
        };
        console.log(data);
        $.ajax("/s/api/user/passwdUpdate", {
            type: "put",
            dataType: "json",
            data: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json",
            },
        }).done((res) => {
            if (res.code == 1) {
                console.log("asdasd");
                alert("비밀번호가 변경되었습니다.");
                // location.href = "/user/update" + userId;
            } else {
                alert("비밀번호 입력 정보를 다시 확인해주세요.");
                return false;
            }
        });
    }
}

function Forminit(){
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


function Imginit(){
    //이벤트리스너
    document.querySelector(".edit_img").addEventListener("click", openImageEditor);
    document.querySelector("#close_editor").addEventListener("click", closeImageEditor);
    document.querySelector("#save_editor").addEventListener("click", saveImageEditor);

    function openImageEditor() {
      let imageEditor = document.querySelector(".img_editor");
      imageEditor.style.display = "flex";
    }
    //이미지 셀렉터 닫기
    function closeImageEditor() {
      let imageEditor = document.querySelector(".img_editor");
      imageEditor.style.display = "none";
    }

    //선택한 이미지로 프로필 이미지 변경
    function saveImageEditor() {
           let formData = new FormData();
           formData.append("file", $("#userProfileImg")[0].files[0]);
           console.log(formData);
                $.ajax("/s/api/user/Imageupdate", {
                    type: "PUT",
                    data: formData,
                    processData: false, // 쿼리스트링 방지
                    contentType: false,
                    enctype: "multipart/form-data",
                }).done((res) => {
                    if (res.code == 1) {
                        alert(" 이미지 수정 완료되었습니다.");
                    } else {
                        alert(" 이미지를 다시 확인해주세요.");
                        return false;
                    }
                });
            }
    }



window.addEventListener("load", Forminit);
window.addEventListener("load", Passwdinit);
window.addEventListener("load", Imginit);












// 유효성 검사










//선택한 이미지로 프로필 이미지 변경
function savePasswdChanger() {
  closePasswdChanger();
}

let passwd = document.querySelector("#userPassword");
let passwdConfirm = document.querySelector("#userPasswordConfirm");



//이미지 셀렉터 열기


/////////////////
//  회원 탈퇴  ///
////////////////

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


function imageUpload(){
            document.getElementById('save_editor').addEventListener('click', function () {
            const imageInput = document.getElementById('userProfileImg');

            if (imageInput.files.length > 0) {
                const selectedImage = imageInput.files[0];
                const reader = new FileReader();

                reader.onload = function (e) {
                    const imageBlob = e.target.result;
                    document.body.removeChild(a);
                };
                reader.readAsDataURL(selectedImage);
            }
        });
}

