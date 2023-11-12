//////////////////////
// 좋아요 애니메이션 //     //추후 아이디값 넣어서 변하게 설정 할것
/////////////////////
$(document).ready(function(){
  $('.heart').click(function(){
    $('.heart').toggleClass("heart-active")
  });
});

/////////////////////////////
// 드랍다운 필터링 - performance //
////////////////////////////
function filterPosts() {
  var filterGenre = document.getElementById('filterGenre').value;
  var filterAge = document.getElementById('filterAge').value;
  // var filterRun = document.getElementById('filterRun').value;
  var filterCharge = document.getElementById('filterCharge').value;
  var posts = document.getElementsByClassName('search_pf_slide');

  for (var i = 0; i < posts.length; i++) {
    var genre = posts[i].getElementsByClassName('category')[0].textContent;
    var age = posts[i].getElementsByClassName('age')[0].textContent;
    // var opening = posts[i].getElementsByClassName('opening')[0].textContent;
    var charge = posts[i].getElementsByClassName('charge')[0].textContent;

    if (
        (filterGenre === 'all' || filterGenre === genre) &&
        (filterAge === 'all' || filterAge === age) &&
        (filterCharge === 'all' || filterCharge === charge)
    ) {
      posts[i].classList.remove('hidden'); // 'hidden' 클래스 제거
    } else {
      posts[i].classList.add('hidden'); // 'hidden' 클래스 추가
    }
  }
  // 필터링 후 showPage 함수 호출
  showPage(1);
}

//////////////
// 검색기능 //
/////////////
function filterSearch() {
  var filterText = document.getElementById('filterText').value.toLowerCase();
  var posts = document.getElementsByClassName('search');

  for (var i = 0; i < posts.length; i++) {
      var title = posts[i]
          .getElementsByClassName('title')[0]
          .textContent.toLowerCase();
      var charge = posts[i]
          .getElementsByClassName('category')[0]
          .textContent.toLowerCase();

      if (title.includes(filterText) || charge.includes(filterText)) {
        posts[i].classList.remove('hidden');
      } else {
        posts[i].classList.add('hidden');
      }
  }
  showPage(1);
}

////////////////
// pagination //
///////////////
function getPageList(totalPages, page, maxLength){
  function range(start, end){
    return Array.from(Array(end - start + 1), (_, i) => i + start);
  }

  var sideWidth = maxLength < 9 ? 1 : 2;
  var leftWidth = (maxLength - sideWidth * 2 - 3) >> 1;
  var rightWidth = (maxLength - sideWidth * 2 - 3) >> 1;

  if(totalPages <= maxLength){
    return range(1, totalPages);
  }

  if(page <= maxLength - sideWidth - 1 - rightWidth){
    return range(1, maxLength - sideWidth - 1).concat(0, range(totalPages - sideWidth + 1, totalPages));
  }

  if(page >= totalPages - sideWidth - 1 - rightWidth){
    return range(1, sideWidth).concat(0, range(totalPages- sideWidth - 1 - rightWidth - leftWidth, totalPages));
  }

  return range(1, sideWidth).concat(0, range(page - leftWidth, page + rightWidth), 0, range(totalPages - sideWidth + 1, totalPages));
}

$(function(){
  var numberOfItems = $(".search").length;
  var limitPerPage = 12; //How many poster_wrapper items visible per a page
  var totalPages = Math.ceil(numberOfItems / limitPerPage);
  var paginationSize = 7; //How many page elements visible in the pagination
  var currentPage;

  function showPage(whichPage){
    if(whichPage < 1 || whichPage > totalPages) return false;

    currentPage = whichPage;

    $(".search")
        .hide()
        .slice((currentPage - 1) * limitPerPage, currentPage * limitPerPage)
        .show();

    $(".pagination li").slice(1, -1).remove();

    getPageList(totalPages, currentPage, paginationSize).forEach(item => {
      $("<li>")
          .addClass("page-item")
          .addClass(item ? "current-page" : "dots")
          .toggleClass("active", item === currentPage)
          .append(
              $("<a>")
                  .addClass("page-link")
                  .attr({href: "javascript:void(0)"})
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
                .attr({href: "javascript:void(0)"})
                .text("Prev")
        ),
    $("<li>")
        .addClass("page-item")
        .addClass("next-page")
        .append(
            $("<a>")
            .addClass("page-link")
            .attr({href: "javascript:void(0)"})
            .text("Next")
        )
  );

  $(".poster_wrapper").show();
  showPage(1);

  $(document).on("click",
      ".pagination li.current-page:not(.active)",
      function(){
    return showPage(+$(this).text());
  });

  $(".next-page").on("click", function(){
    return showPage(currentPage + 1);
  });

  $(".previous-page").on("click", function(){
    return showPage(currentPage - 1);
  });
});