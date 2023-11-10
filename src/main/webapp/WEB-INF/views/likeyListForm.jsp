<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/css/myLikey.css" />
        <link rel="stylesheet" href="/css/default.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />

        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap"
          rel="stylesheet" />
        <link
          href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
          rel="stylesheet" />

        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <title>BusanMate with Write</title>
      </head>

      <body>
        <%@ include file="layout/header.jsp" %>
          <div class="write_title">

            <h2>내 관심 글 목록</h2>
          </div>

          <div class="main_post">
            <ul class="main_title">
              <li><i class="fa-solid fa-rocket"></i></li>
              <li>
                <h2>구인 정보 관심 목록</h2>
              </li>
            </ul>

            <!-- 구인글 및 페이지네이션 -->
            <section class="search_job_container">
              <!--해당 부분부터 게시글 목록 (더미데이터)-->
              <c:forEach var="detailItem" items="${LikeyJSDetail}">
              <article class="search_job_slide">
                <div class="project">
                  <div class="badge_wrapper">
                    <div class="badge_movie">
                      <i class="fa-solid fa-clapperboard"></i>
                      <p class="badge_genre">${detailItem.jobGenre}</p>
                    </div>
                    <div class="badge_new">
                      <i class="fa-solid fa-gift"> 새로 올라온 글</i>
                    </div>
                  </div>

                  <div class="schedule">
                    <p>마감일 |</p>
                    <p>${detailItem.jobDeadline}</p>
                  </div>

                  <h1 class="project_title">
                        ${detailItem.jobTitle}
                  </h1>

                  <ul class="position_list">
                    <li class="position">배우</li>
                  </ul>

                  <div class="content">
                    <a href="viewJobSearch.html" class="more_btn">
                      <p>자세히보기</p>
                      <i class="fa-solid fa-arrow-up-right-from-square"></i>
                    </a>
                    <div class="like_btn">
                      <span class="heart"></span>
                    </div>
                  </div>

                  <div class="border"></div>

                  <div class="myinfo">
                    <div class="user_info">
                      <div>
                        <img class="icon" src=" ${detailItem.userProfileImg}" />
                      </div>
                      <div class="nickname">
                        <span class="accent">${detailItem.userCareer}</span>
                        <p>${detailItem.userNickname}</p>
                      </div>
                    </div>

                    <div class="viewAndInfo">
                      <div class="view">
                        <i class="fa-regular fa-eye"> ${detailItem.viewCount}</i>
                      </div>
                      <div class="comment">
                        <i class="fa-regular fa-comment"> ${detailItem.commentCount}</i>
                      </div>
                    </div>
                  </div>
                </div>
              </article>
              </c:forEach>
            </section>
            <div class="pagination">
              <!--<li class="page-item previous-page disable"><a class="page-link" href="#">Prev</a></li>
            <li class="page-item current-page active"><a class="page-link" href="#">1</a></li>
            <li class="page-item dots"><a class="page-link" href="#">...</a></li>
            <li class="page-item current-page"><a class="page-link" href="#">5</a></li>
            <li class="page-item current-page"><a class="page-link" href="#">6</a></li>
            <li class="page-item dots"><a class="page-link" href="#">...</a></li>
            <li class="page-item current-page"><a class="page-link" href="#">10</a></li>
            <li class="page-item next-page"><a class="page-link" href="#">Next</a></li>-->
            </div>
          </div>

          <div class="main_post2">
            <ul class="main_title">
              <li><i class="fa-solid fa-rocket"></i></li>
              <li>
                <h2>전시·공연 관심 목록</h2>
              </li>
            </ul>

            <!-- 전시 공연 정보 -->
            <section class="poster_wrapper">
              <article class="poster search">
                <div class="img"></div>
                <a href="#">
                  <img src="/image/poster/poster_1.jpg" class="img-hover" />
                </a>
                <div class="info">
                  <div class="badge">
                    <div class="category forMusical">
                      <i class="fa-solid fa-music"> 뮤지컬</i>
                    </div>
                    <div class="age for19">19세 이상</div>
                    <div class="charge">유료</div>
                  </div>
                  <h3 class="title">밑바닥에서</h3>
                  <h4 class="location">예술의 전당 CJ극장</h4>
                  <h5 class="date">2023.11.14 ~ 2023.12.25</h5>
                </div>
              </article>
            </section>


            <div class="pagination2">
              <!--<li class="page-item previous-page disable"><a class="page-link" href="#">Prev</a></li>
        <li class="page-item current-page active"><a class="page-link" href="#">1</a></li>
        <li class="page-item dots"><a class="page-link" href="#">...</a></li>
        <li class="page-item current-page"><a class="page-link" href="#">5</a></li>
        <li class="page-item current-page"><a class="page-link" href="#">6</a></li>
        <li class="page-item dots"><a class="page-link" href="#">...</a></li>
        <li class="page-item current-page"><a class="page-link" href="#">10</a></li>
        <li class="page-item next-page"><a class="page-link" href="#">Next</a></li>-->
            </div>

          </div>

          <footer class="footer">
            <div class="left_cover"></div>
          </footer>

          <script src="https://kit.fontawesome.com/3f247b3389.js" crossorigin="anonymous"></script>
          <script src="/js/myLikey.js"></script>
          <script src="/js/default.js"></script>
      </body>

      </html>