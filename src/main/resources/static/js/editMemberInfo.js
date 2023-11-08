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
window.addEventListener("load", Forminit);
