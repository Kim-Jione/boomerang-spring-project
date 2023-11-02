const submitBtn = document.querySelector(".submit_btn");
const userName = document.querySelector("#user");
const comment = document.querySelector("#comment");
// const likeIcon = document.querySelector(".heart__icon");
// const count = document.querySelector(".count");
const commentsCont = document.querySelector(".comments_container");

// likeIcon.addEventListener("click", likeVideo);
submitBtn.addEventListener("click", submitFeedback);
submitBtn.addEventListener("click", resizeTextarea);

feedbackArr = [];
// let positiveFeedback = false;
let likesCount = 0;

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
      //   typeOfFeedback: positiveFeedback,
    };
    // add new feedback to array
    feedbackArr.push(newFeedback);
    // if liked add to count
    // if (positiveFeedback === true) {
    //   addLikes();
    // }
    // clear inputs
    resetForm();
    // add feedback to list
    addFeedback(newFeedback);
  }

  e.preventDefault();
}

// function likeVideo() {
//   likeIcon.classList.toggle("liked");

//   if (likeIcon.classList.contains("liked")) {
//     likeIcon.innerHTML = `<i class="fas fa-heart"></i>`;
//     // set feedback to liked
//     positiveFeedback = true;
//   } else {
//     likeIcon.innerHTML = `<i class="far fa-heart"></i>`;
//     // set feedback to not liked
//     positiveFeedback = false;
//   }
// }

// function addLikes() {
//   likesCount++;
//   count.innerHTML = likesCount;
// }

function resetForm() {
  //   userName.value = "";
  comment.value = "";
  //   likeIcon.innerHTML = `<i class="far fa-heart"></i>`;
  //   positiveFeedback = false;
}

function addFeedback(item) {
  // select first letter of the user name
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
