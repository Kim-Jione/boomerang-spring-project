////////////////////////////
// 핫게시물 반응형 슬라이드 //
///////////////////////////
const swiperEl = document.querySelector(".hot_swiper");
Object.assign(swiperEl, {
  slidesPerView: 1,
  spaceBetween: 10,

  breakpoints: {
    640: {
      slidesPerView: 2,
      spaceBetween: 30,
    },
    900: {
      slidesPerView: 3,
      spaceBetween: 30,
    },
    1340: {
      slidesPerView: 4,
      spaceBetween: 30,
    },
  },
});
swiperEl.initialize();

//////////////////////
// 좋아요 애니메이션 //     //추후 아이디값 넣어서 변하게 설정 할것
/////////////////////
// $(document).ready(function () {
//   $(".heart").click(function () {
//     $(".heart").toggleClass("heart-active");
//   });
// });


//////////////////////
// 좋아요 애니메이션 //     //변경 버전
/////////////////////
$("#iconLove").click(() => {
  let isLovedState = $("#iconLove").hasClass("fa-solid"); // hasClass => fa-solid 갖고 있으면 true 없으면 false
  if (isLovedState) {
      deleteLove();
  } else {
      insertLove();
  }
});

// DB에 insert 요청하기
function insertLove() {
  let postId = $("#postId").val();

  $.ajax("/s/api/post/love/" + postId, {
      type: "POST",
      dataType: "json",
  }).done((res) => {
      if (res.code == 1) {
          renderLoves();
          let count = $("#countLove").text();
          $("#countLove").text(Number(count) + 1);
          $("#loveId").val(res.data.loveId);
      } else {
          alert(res.msg);
          location.href = "/user/loginForm";
      }
  });
}

// DB에 delete 요청하기
function deleteLove() {
  let postId = $("#postId").val();
  let loveId = $("#loveId").val();

  $.ajax("/s/api/post/love/" + postId + "/" + loveId, {
      type: "DELETE",
      dataType: "json",
  }).done((res) => {
      if (res.code == 1) {
          renderCancelLoves();
          let count = $("#countLove").text();
          $("#countLove").text(Number(count) - 1);
      } else {
          alert("좋아요 취소에 실패했습니다");
      }
  });
}

// 빨간색 하트 그리기
function renderLoves() {
  $("#iconLove").removeClass("fa-regular");
  $("#iconLove").addClass("fa-solid");
}

// 검정색 하트 그리기
function renderCancelLoves() {
  $("#iconLove").removeClass("fa-solid");
  $("#iconLove").addClass("fa-regular");
}

/////////////////////////////
// 드랍다운 필터링 - 장르 //
////////////////////////////
function filterPosts() {
  var filterGenre = document.getElementById("filterGenre").value;
  var posts = document.getElementsByClassName("search_job_slide");

  for (var i = 0; i < posts.length; i++) {
    var genre = posts[i].getElementsByClassName("badge_genre")[0].textContent;

    if (filterGenre === "all" || filterGenre === genre) {
      posts[i].style.display = "block"; // 선택한 장르에 맞는 게시물만 표시
    } else {
      posts[i].style.display = "none";
    }
  }
}

//////////////
// 검색기능 //
/////////////
 function filterSearch() {
  var filterText = document.getElementById("filterText").value.toLowerCase();
  var search = document.getElementsByClassName("search_job_slide");

  for (var i = 0; i < search.length; i++) {
    var title = search[i]
      .getElementsByClassName("project_title")[0]
      .textContent.toLowerCase();
    var nickname = search[i]
      .getElementsByClassName("nickname")[0]
      .textContent.toLowerCase();

    if (title.includes(filterText) || nickname.includes(filterText)) {
      search[i].style.display = "block";
    } else {
      search[i].style.display = "none";
    }
  }
} 

////////////////
// pagination //
///////////////
function getPageList(totalPages, page, maxLength) {
  function range(start, end) {
    return Array.from(Array(end - start + 1), (_, i) => i + start);
  }

  var sideWidth = maxLength < 9 ? 1 : 2;
  var leftWidth = (maxLength - sideWidth * 2 - 3) >> 1;
  var rightWidth = (maxLength - sideWidth * 2 - 3) >> 1;

  if (totalPages <= maxLength) {
    return range(1, totalPages);
  }

  if (page <= maxLength - sideWidth - 1 - rightWidth) {
    return range(1, maxLength - sideWidth - 1).concat(
      0,
      range(totalPages - sideWidth + 1, totalPages)
    );
  }

  if (page >= totalPages - sideWidth - 1 - rightWidth) {
    return range(1, sideWidth).concat(
      0,
      range(totalPages - sideWidth - 1 - rightWidth - leftWidth, totalPages)
    );
  }

  return range(1, sideWidth).concat(
    0,
    range(page - leftWidth, page + rightWidth),
    0,
    range(totalPages - sideWidth + 1, totalPages)
  );
}

$(function () {
  var numberOfItems = $(".search_job_slide").length;
  var limitPerPage = 16; //How many search_job_slide items visible per a page
  var totalPages = Math.ceil(numberOfItems / limitPerPage);
  var paginationSize = 7; //How many page elements visible in the pagination
  var currentPage;

  function showPage(whichPage) {
    if (whichPage < 1 || whichPage > totalPages) return false;

    currentPage = whichPage;

    $(".search_job_slide")
      .hide()
      .slice((currentPage - 1) * limitPerPage, currentPage * limitPerPage)
      .show();

    $(".pagination li").slice(1, -1).remove();

    getPageList(totalPages, currentPage, paginationSize).forEach((item) => {
      $("<li>")
        .addClass("page-item")
        .addClass(item ? "current-page" : "dots")
        .toggleClass("active", item === currentPage)
        .append(
          $("<a>")
            .addClass("page-link")
            .attr({ href: "javascript:void(0)" })
            .text(item || "...")
        )
        .insertBefore(".next-page");
    });

    $(".previous-page").toggleClass("disable", currentPage === 1);
    $(".next-page").toggleClass("disable", currentPage === totalPages);
    return true;
  }

  $(".pagination").append(
    $("<li>")
      .addClass("page-item")
      .addClass("previous-page")
      .append(
        $("<a>")
          .addClass("page-link")
          .attr({ href: "javascript:void(0)" })
          .text("Prev")
      ),
    $("<li>")
      .addClass("page-item")
      .addClass("next-page")
      .append(
        $("<a>")
          .addClass("page-link")
          .attr({ href: "javascript:void(0)" })
          .text("Next")
      )
  );

  $(".search_job_container").show();
  showPage(1);

  $(document).on(
    "click",
    ".pagination li.current-page:not(.active)",
    function () {
      return showPage(+$(this).text());
    }
  );

  $(".next-page").on("click", function () {
    return showPage(currentPage + 1);
  });

  $(".previous-page").on("click", function () {
    return showPage(currentPage - 1);
  });
});
////////////////
// deadline //
///////////////

