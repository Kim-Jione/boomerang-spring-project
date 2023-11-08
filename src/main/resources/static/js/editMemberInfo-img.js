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

    }
           let data = {
                userId : 1
//                $("#userId").val(),
           }
           console.log(data);

           let formData = new FormData();

           formData.append("file", $("#userProfileImg")[0].files[0]);
           formData.append("ImgUpdateDto", new FormData().append("data", JSON.stringify(data)));


           console.log(formData);
           $.ajax("/s/api/user/Imageupdate", {
               type: "PUT",
               data: formData,
               processData: false,
               contentType: false,
           }).done((res) => {
               if (res.code == 1) {
                   alert("이미지 수정 완료되었습니다.");
               } else {
                   alert("이미지를 다시 확인해주세요.");
               }
           });
    }
window.addEventListener("load", Imginit);