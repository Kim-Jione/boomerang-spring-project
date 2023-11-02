const submitBtn = document.querySelector(".submit_btn");
const userName = document.querySelector("#user");
const comment = document.querySelector("#comment");
const commentsCont = document.querySelector(".comments_container");

submitBtn.addEventListener("click", submitFeedback);
submitBtn.addEventListener("click", resizeTextarea);

feedbackArr = [];
function submitFeedback(e) {
  // get user name
  const userForm = userName.value;
  // get feedback
  const commentForm = comment.value;
  const currentTime = new Date();
  // if inputs are not empty
  if (userForm && commentForm !== "") {
    // create new feedback
    newFeedback = {
      id: 1,
      userName: userForm,
      userComment: commentForm,
      createdDate: currentTime.toString(),
    };
    // add new feedback to array
    feedbackArr.push(newFeedback);
    resetForm();
    // add feedback to list
    addFeedback(newFeedback);
  }

  e.preventDefault();
}



function resetForm() {
  comment.value = "";

}

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
                    <p class="created_date">${item.createdDate}</p>
                </div>
            </div>
            <div class="comment_btns">
                <button>수정</button>
                <button>삭제</button>
                <button>신고하기</button>
            </div>
        </div>
        <textarea class="comment textarea" readonly>${item.userComment}</textarea>
        
    `;
  // insert feedback into the list
  commentsCont.insertAdjacentElement("beforeend", div);
}

// 텍스트에어리어 높이 조절 함수
function resizeTextarea() {
  const textareas = document.querySelectorAll(".textarea");

  for (let i = 0; i < textareas.length; i++) {
    textareas[i].style.height = "auto";
    textareas[i].style.height = textareas[i].scrollHeight + "px";
  }
}

window.addEventListener("load", resizeTextarea);