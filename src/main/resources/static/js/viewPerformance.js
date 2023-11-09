/***************/
/* 본문 하단 버튼 */
/***************/

if (
    document.querySelector(".edit_btn") &&
    document.querySelector(".delete_btn")
) {
  const editBtn = document.querySelector(".edit_btn");
  const deleteBtn = document.querySelector(".delete_btn");
  editBtn.addEventListener("click", editPost);
  deleteBtn.addEventListener("click", openDeleteConfirm);
  function editPost() {
    window.location.href = "/s/api/performance/updateForm/" + pfId;
  }

  // 삭제확인창 열기
  function openDeleteConfirm() {
    let deleteConfirm = document.querySelector(".delete_confirm");
    deleteConfirm.style.display = "flex";
  }
  // 삭제버튼들 연결
  const confirmDelete = document.querySelector("#confirmDelete");
  confirmDelete.addEventListener("click", () => {
    deletePf();
  });
  const closeDelete = document.querySelector("#closeDelete");
  closeDelete.addEventListener("click", () => {
    let deleteConfirm = document.querySelector(".delete_confirm");
    deleteConfirm.style.display = "none";
  });
}
const reportBtn = document.querySelector(".report");
let pfId = $("#pfId").val();

reportBtn.addEventListener("click", reportPf);

/*******/
/* 댓글 */
/*******/
const submitBtn = document.querySelector(".submit_btn");
const userName = document.querySelector("#user");
const comment = document.querySelector("#comment");
const commentsCont = document.querySelector(".comments_container");

submitBtn.addEventListener("click", submitFeedback);
submitBtn.addEventListener("click", resizeTextarea);

// 댓글 저장을 위한 배열
feedbackArr = [];

//댓글을 배열에 저장하는 함수
function submitFeedback(e) {
  // get user name
  const userForm = userName.value;
  // get feedback
  const commentForm = comment.value;
  const currentTime = new Date();
  // if inputs are not empt삭
  if (userForm && commentForm !== "") {
    // create new feedback
    newFeedback = {
      id: 1,
      userName: userForm,
      userComment: commentForm,
      createdDate: currentTime.toString()
    };
    // add new feedback to array
    feedbackArr.push(newFeedback);
    resetForm();
    // add feedback to list
    addFeedback(newFeedback);
  }

  e.preventDefault();
}

// 댓글 창을 초기화 함수
function resetForm() {
  comment.value = "";
}

// 저장된 배열에서 댓글 정보를 불러와 html에 붙여넣는 함수
function addFeedback(item) {
  const letter = "";
  // create new div
  const div = document.createElement("div");
  // add class
  div.classList = "comment_card";
  // add id
  div.id = item.id;
  // add html
  div.innerHTML = `
        <div class="comment_top">
            <div class="comment_info">
                <div class="pic center_display">
                    <img src="${letter}" alt="image"/>
                </div>
                <div class="comment_info_txt">
                    <p class="nickname">
                        ${item.userName}
                    </p>
                    <p class="created_date"><fmt:formatDate value="${item.createdDate}" pattern="yy.MM.dd kk:mm" type="date" /></p>
                </div>
            </div>
            <div class="comment_btns">
                <button class='editBtn'>수정</button>
                <button class='removeBtn'>삭제</button>
                <button class='reportBtn'>신고하기</button>
            </div>
        </div>
        <textarea class="comment textarea" id="commentContent" readonly>${item.userComment}</textarea>

    `;
  // insert feedback into the list
  commentsCont.insertAdjacentElement("beforeend", div);
  activeCommentBtn();
}

// 댓글 버튼 활성화
function activeCommentBtn() {
  // 각 버튼 요소 선택
  const editButtons = document.querySelectorAll(".editBtn");
  const removeButtons = document.querySelectorAll(".removeBtn");
  const reportButtons = document.querySelectorAll(".reportBtn");

  // 각 버튼과 함수 연결
  editButtons.forEach((editButton) => {
    editButton.addEventListener("click", editCommentBtn);
  });
  removeButtons.forEach((removeButton) => {
    removeButton.addEventListener("click", removeComment);
  });
  reportButtons.forEach((reportButton) => {
    reportButton.addEventListener("click", reportComment);
  });
}

// 댓글 수정
function editCommentBtn() {
  const commentCard = this.closest(".comment_card"); // 수정 버튼의 부모 comment_card 요소 찾기
  const textarea = commentCard.querySelector(".comment.textarea"); // 해당 comment_card 내의 textarea 요소 찾기
  textarea.readOnly = false;
  textarea.style.border = "1px solid #4b91f7";
  textarea.focus();

  const editBtn = commentCard.querySelector(".editBtn");
  editBtn.innerText = "저장";
  editBtn.removeEventListener("click", editCommentBtn);
  editBtn.addEventListener("click", saveComment);
}

// 수정 댓글 저장
function saveComment() {
  const commentCard = this.closest(".comment_card"); // 저장 버튼의 부모 comment_card 요소 찾기
  const textarea = commentCard.querySelector(".comment.textarea"); // 해당 comment_card 내의 textarea 요소 찾기
  textarea.readOnly = true;
  textarea.style.border = "none";

  let data = {
    pfId: $("#pfId").val(),
    commentId: commentCard.querySelector(".commentId").value,
    commentContent: textarea.value,
    userId: $("#userId").val()
  };

  $.ajax("/s/api/comment/update", {
    type: "PUT",
    data: JSON.stringify(data),
    dataType: "json",
    headers: {
      "Content-Type": "application/json; charset=utf-8"
    }
  }).done((res) => {
    if (res.code == 1) {
      alert(res.msg);
    }
  });
  const editBtn = commentCard.querySelector(".editBtn");
  editBtn.innerText = "수정";
  editBtn.removeEventListener("click", saveComment);
  editBtn.addEventListener("click", editCommentBtn);
}

// 댓글 삭제
function removeComment() {
  const commentCard = this.closest(".comment_card"); // 삭제 버튼의 부모 comment_card 요소 찾기
  if (confirm("정말 삭제하시겠습니까?")) {
    const commentCard = this.closest(".comment_card"); // 저장 버튼의 부모 comment_card 요소 찾기

    let data = {
      commentId: commentCard.querySelector(".commentId").value,
      pfId: $("#pfId").val(),
      userId: $("#userId").val()
    };

    $.ajax("/s/api/comment/delete", {
      type: "DELETE",
      dataType: "json",
      data: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json"
      }
    }).done((res) => {
      if (res.code == 1) {
        alert(res.msg);
        location.reload();
      } else {
        alert(res.msg);
        return false;
      }
    });
  }
}

//  댓글 신고하기
function reportComment() {
  var nWidth = "500";
  var nHeight = "600";
  var xPos = document.body.clientWidth / 2 - nWidth / 2;
  xPos += window.screenLeft; //듀얼 모니터
  var yPos = screen.availHeight / 2 - nHeight / 2;

  window.open(
      "/s/api/reportForm",
      "신고하기",
      "width=" +
      nWidth +
      ",height=" +
      nHeight +
      ",left=" +
      xPos +
      ", top=" +
      yPos +
      ",toolbar=no"
  );
}

//  공연글 신고하기
function reportPf() {
  let pfId = $("#pfId").val();
  let userId = $("#pfUserId").val();

  var nWidth = "700";
  var nHeight = "900";
  var xPos = document.body.clientWidth / 2 - nWidth / 2;
  xPos += window.screenLeft; //듀얼 모니터
  var yPos = screen.availHeight / 2 - nHeight / 2;

  window.open(
      "/s/api/reportFormPf/" + pfId + "/" + userId,
      "신고하기",
      "width=" +
      nWidth +
      ",height=" +
      nHeight +
      ",left=" +
      xPos +
      ", top=" +
      yPos +
      ",toolbar=no"
  );
}

// textarea 높이 조절 함수
// 새로운 댓글이 추가됐을 때 댓글창 height값을 지정해주기 위해 필요
function resizeTextarea() {
  const textareas = document.querySelectorAll(".textarea");

  for (let i = 0; i < textareas.length; i++) {
    textareas[i].style.height = "auto";
    textareas[i].style.height = textareas[i].scrollHeight + "px";
  }
}

// 페이지가 처음 로드될 때 기존 댓글들의 height값을 지정
window.addEventListener("load", resizeTextarea);
activeCommentBtn();

// 추천 아이콘 클릭
$("#iconLove").click(() => {
  let isLovedState = $("#iconLove").hasClass("fa-solid"); // hasClass => fa-solid 갖고 있으면 true 없으면 false
  if (isLovedState) {
    deleteLove();
  } else {
    insertLove();
  }
});

// 공연글 추천하기
function insertLove() {
  let data = {
    pfId: $("#pfId").val(),
    userId: $("#userId").val()
  };

  $.ajax("/s/api/likey", {
    type: "POST",
    data: JSON.stringify(data),
    dataType: "json",
    headers: {
      "Content-Type": "application/json; charset=utf-8"
    }
  }).done((res) => {
    if (res.code == 1) {
      renderLoves();
      let count = $("#countLikey").text();
      $("#countLikey").text(Number(count) + 1);
      $("#likeyId").val(res.data.likeyId);
    } else {
      alert(res.msg);
      location.href = "/user/loginForm";
    }
  });
}

// 공연글 추천 취소하기
function deleteLove() {
  let likeyId = $("#likeyId").val();

  $.ajax("/s/api/unlikey/" + likeyId, {
    type: "DELETE",
    dataType: "json"
  }).done((res) => {
    if (res.code == 1) {
      renderCancelLoves();
      let count = $("#countLikey").text();
      $("#countLikey").text(Number(count) - 1);
    } else {
      alert("공연글 추천 취소에 실패했습니다");
    }
  });
}

// 하트 그리기
function renderLoves() {
  $("#iconLove").removeClass("fa-regular");
  $("#iconLove").addClass("fa-solid");
}

// 하트 지우기
function renderCancelLoves() {
  $("#iconLove").removeClass("fa-solid");
  $("#iconLove").addClass("fa-regular");
}

// 공연글 삭제
function deletePf() {
  let data = {
    pfId: $("#pfId").val(),
    userId: $("#userId").val()
  };

  $.ajax("/s/api/performance/delete", {
    type: "DELETE",
    data: JSON.stringify(data),
    dataType: "json",
    headers: {
      "Content-Type": "application/json; charset=utf-8"
    }
  }).done((res) => {
    if (res.code == 1) {
      alert(res.msg);
      location.href = "/performance/mainForm";
    } else {
      alert(res.msg);
      return false;
    }
  });
}

// 댓글 작성
$("#commentWriteBtn").click(() => {
  write();
});

function write() {
  let data = {
    commentContent: $("#commentContent").val(),
    pfId: $("#pfId").val(),
    userId: $("#userId").val()
  };
  if (data.commentContent.length < 1) {
    alert("댓글을 입력해주세요.");
    return;
  }

  $.ajax("/s/api/comment/write", {
    type: "POST",
    dataType: "json",
    data: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json"
    }
  }).done((res) => {
    if (res.code == 1) {
      alert(res.msg);
      location.href = "/s/api/performance/detailForm/" + data.pfId;
    } else {
      alert(res.msg);
      return false;
    }
  });
}
