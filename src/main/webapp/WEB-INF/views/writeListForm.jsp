<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <!DOCTYPE html>
      <html lang="ko">

      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="/css/myPost.css" />
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

            <h2>내 글 작성목록</h2>
          </div>

          <div class="main_post">
            <ul class="main_title">
              <li><i class="fa-solid fa-rocket"></i></li>
              <li>
                <h2>구인 정보 작성 목록</h2>
              </li>
            </ul>

            <!-- 구인글 및 페이지네이션 -->
            <section class="search_job_container">
              <c:forEach var="job" items="${myJSList}">
                <input id="jobId" type="hidden" value="${job.jobId}" />
                <input id="userId" type="hidden" value="${principal.userId}" />

                <div class="search_job_slide">
                  <c:if test="${job.isDead}">
                    <img src="/image/deadline.png" class="deadline">
                  </c:if>
                  <div class="project">
                    <div class="badge_wrapper">
                      <c:choose>
                        <c:when test="${job.jobGenre == '장편영화'}">
                          <!-- 영화 장르일 때 -->
                          <div class="badge_movie">
                            <i class="fa-solid fa-gift">
                              <p class="badge_genre">장편영화</p>
                            </i>
                          </div>
                          <c:if test="${job.isToday}">
                            <!-- 새로 올라온 글일 때 -->
                            <div class="badge_new">
                              <i class="fa-solid fa-gift"> 새로 올라온 글</i>
                            </div>
                          </c:if>
                        </c:when>
                        <c:when test="${job.jobGenre == '단편영화'}">
                          <!-- 영화 장르일 때 -->
                          <div class="badge_movie">
                            <i class="fa-solid fa-gift">
                              <p class="badge_genre">단편영화</p>
                            </i>
                          </div>
                          <c:if test="${job.isToday}">
                            <!-- 새로 올라온 글일 때 -->
                            <div class="badge_new">
                              <i class="fa-solid fa-gift"> 새로 올라온 글</i>
                            </div>
                          </c:if>
                        </c:when>
                        <c:otherwise>
                          <div class="badge_movie">
                            <i class="fa-solid fa-clapperboard">
                              <p class="badge_genre"> ${job.jobGenre}</p>
                            </i>
                          </div>
                          <c:if test="${job.isToday}">
                            <!-- 새로 올라온 글일 때 -->
                            <div class="badge_new">
                              <i class="fa-solid fa-gift"> 새로 올라온 글</i>
                            </div>
                          </c:if>
                          <c:if test="${job.isFame}">
                            <!-- 인기 글일 때 -->
                            <div class="badge_new">
                              <i class="fa-solid fa-gift"> 인기글</i>
                            </div>
                          </c:if>
                        </c:otherwise>
                      </c:choose>
                    </div>

                    <div class="schedule">
                      <p>마감일 | </p>
                      <p>${job.jobDeadline}</p>
                    </div>

                    <h1 class="project_title">
                      ${job.jobContentTitle}
                    </h1>

                    <ul class="position_list">
                      <c:forEach var="position" items="${job.jobPositionTitle}">
                        <li class="position">
                          ${position.jobPositionTitle}
                        </li>
                      </c:forEach>
                    </ul>

                    <div class="content">
                      <div class="more_btn">
                        <p><a href="/s/api/jobSearch/detailForm/${job.jobId}">자세히보기</a> </p>
                        <i class="fa-solid fa-arrow-up-right-from-square"></i>
                      </div>
                      <div class="like_btn">
                        추천수: ${job.likeyCount}
                      </div>
                    </div>

                    <div class="border"></div>

                    <div class="myinfo">
                      <div class="user_info">
                        <div>
                          <a href="/s/api/user/detailForm/${job.userId}">
                            <img class="icon" src="/img/${job.userProfileImg}">
                          </a>
                        </div>
                        <div class="nickname">
                          <span class="accent">${job.userCareer}</span>
                          <p>${job.userNickname}</p>
                        </div>
                      </div>Z

                      <div class="viewAndInfo">
                        <div class="view">
                          <i class="fa-regular fa-eye">${job.viewCount}</i>
                        </div>
                        <div class="comment">
                          <i class="fa-regular fa-comment">${job.commentCount}</i>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
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
                <h2>전시·공연 작성 목록</h2>
              </li>
            </ul>

            <!-- 전시 공연 정보 -->
            <section class="poster_wrapper">
              
              <c:forEach var="best" items="${myPfList}">
                <img class="medal" src="${best.imgPath}">
                <article class="poster search">
                  <div class="img"></div>
                  <a href="/s/api/performance/detailForm/${best.pfId}">
                    <img src="/img/${best.pfThumbnail}" class="img-hover">
                  </a>
                  <div class="info">
                    <div class="badge">
                      <c:if test="${best.pfGenre == '단편영화' || best.pfGenre == '장편영화'}">
                        <div class="category forMusical">${best.pfGenre}</div>
                      </c:if>
                      <c:if test="${best.pfGenre == '연극'}">
                        <div class="category forMusical">${best.pfGenre}</div>
                      </c:if>
                      <c:if test="${best.pfGenre == '드라마'}">
                        <div class="category forMusical">${best.pfGenre}</div>
                      </c:if>
                      <c:if test="${best.pfGenre == '웹 컨텐츠'}">
                        <div class="category forMusical">${best.pfGenre}</div>
                      </c:if>
                      <c:if test="${best.pfGenre == '광고'}">
                        <div class="category forMusical">${best.pfGenre}</div>
                      </c:if>
                      <c:if test="${best.pfGenre == '전시'}">
                        <div class="category forMusical">${best.pfGenre}</div>
                      </c:if>
                      <c:if test="${best.pfGenre == '기타'}">
                        <div class="category forMusical">${best.pfGenre}</div>
                      </c:if>
                      <div class="age for19">${best.pfAgerating}</div>
                      <c:if test="${best.isPrice == true}">
                        <div class="charge">유료</div>
                      </c:if>

                      <c:if test="${best.isPrice == false}">
                        <div class="charge">무료</div>
                      </c:if>
                    </div>
                    <h3 class="title">${best.pfTitle}</h3>
                    <h4 class="location">${best.pfLocation}</h4>
                    <h5 class="date">${best.pfStartDate} ~ ${best.pfDeadline}</h5>
                  </div>
                </article>
              </c:forEach>
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
          <script src="/js/myPost.js"></script>
          <script src="/js/default.js"></script>
          <%@ include file="layout/footer.jsp" %>
      </body>

      </html>