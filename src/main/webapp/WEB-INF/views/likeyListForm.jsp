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

        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
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

            <div class="search_job_container">
                                        <c:forEach var="job" items="${jobList}">
                                            <input id="jobId" type="hidden" value="${job.userId}" />
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
                                                                    <i class="fa-solid fa-gift"> 장편영화</i>
                                                                </div>
                                                                <c:if test="${job.isToday}">
                                                                    <!-- 새로 올라온 글일 때 -->
                                                                    <div class="badge_new">
                                                                        <i class="fa-solid fa-gift"> 새로 올라온 글</i>
                                                                    </div>
                                                                </c:if>
                                                               <c:if test="${job.isToday == null}">
                                                                 <!-- 새로 올라온 글일 때 -->
                                                                  <div class="badge_new">
                                                                  <i class="fa-solid fa-gift"> 새로 올라온 글</i>
                                                               </div>
                                                               </c:if>
                                                            </c:when>
                                                            <c:when test="${job.jobGenre == '단편영화'}">
                                                                <!-- 영화 장르일 때 -->
                                                                <div class="badge_movie">
                                                                    <i class="fa-solid fa-gift"> 단편영화</i>
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
                                                                    <i class="fa-solid fa-clapperboard"> ${job.jobGenre}</i>
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
                                                        </div>

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
                                        <!-- 여기까지만 복사 -->
                                    </div>
            <section class="search_job_container">
              <!--해당 부분부터 게시글 목록 (더미데이터)-->
              <c:forEach var="JobLikeyList" items="${LikeyJSDetail}">
                <input id="jobId" type="hidden" value="${JobLikeyList.jobId}" />
                <input id="userId" type="hidden" value="${principal.userId}" />
                <article class="search_job_slide">
                  <div class="project">
                    <div class="badge_wrapper">
                      <c:choose>
                        <c:when test="${JobLikeyList.jobGenre == '장편영화'}">
                          <!-- 영화 장르일 때 -->
                          <div class="badge_movie">
                            <i class="fa-solid fa-gift">
                              장편영화
                            </i>
                          </div>
                          <c:if test="${JobLikeyList.isToday}">
                            <!-- 새로 올라온 글일 때 -->
                            <div class="badge_new">
                              <i class="fa-solid fa-gift"> 새로 올라온 글</i>
                            </div>
                          </c:if>
                        </c:when>
                        <c:when test="${JobLikeyList.jobGenre == '단편영화'}">
                          <!-- 영화 장르일 때 -->
                          <div class="badge_movie">
                            <i class="fa-solid fa-gift">
                              단편영화
                            </i>
                          </div>
                          <c:if test="${JobLikeyList.isToday}">
                            <!-- 새로 올라온 글일 때 -->
                            <div class="badge_new">
                              <i class="fa-solid fa-gift"> 새로 올라온 글</i>
                            </div>
                          </c:if>
                        </c:when>
                        <c:otherwise>
                          <div class="badge_movie">
                            <i class="fa-solid fa-clapperboard" class="badge_genre">
                              ${JobLikeyList.jobGenre}</i>
                          </div>
                          <c:if test="${JobLikeyList.isToday}">
                            <!-- 새로 올라온 글일 때 -->
                            <div class="badge_new">
                              <i class="fa-solid fa-gift"> 새로 올라온 글</i>
                            </div>
                          </c:if>
                        </c:otherwise>
                      </c:choose>
                    </div>

                    <div class="schedule">
                      <p>마감일 |</p>
                      <p>${JobLikeyList.jobDeadline}</p>
                    </div>

                    <h1 class="project_title">
                      ${JobLikeyList.jobTitle}
                    </h1>

                    <ul class="position_list">
                      <c:forEach var="position" items="${JobLikeyList.jobPositionTitle}">
                        <li class="position">
                          ${position.jobPositionTitle}
                        </li>
                      </c:forEach>
                    </ul>

                    <div class="content">
                      <a href="/s/api/jobSearch/detailForm/${JobLikeyList.jobId}" class="more_btn">
                        <p>자세히보기</p>
                        <i class="fa-solid fa-arrow-up-right-from-square"></i>
                      </a>
                    </div>

                    <div class="border"></div>

                    <div class="myinfo">
                      <div class="user_info">
                        <div>
                          <img class="icon" src="/img/${JobLikeyList.userProfileImg}" />
                        </div>
                        <div class="nickname">
                          <span class="accent">${JobLikeyList.userCareer}</span>
                          <p>${JobLikeyList.userNickname}</p>
                        </div>
                      </div>

                      <div class="viewAndInfo">
                        <div class="view">
                          <i class="fa-regular fa-eye"> ${JobLikeyList.viewCount}</i>
                        </div>
                        <div class="comment">
                          <i class="fa-regular fa-comment"> ${JobLikeyList.commentCount}</i>
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
                                        <c:forEach var="pf" items="${LikeyPFDetail}">
                                            <article class="poster search">
                                                <div class="img"></div>
                                                <a href="/s/api/performance/detailForm/${pf.pfId}">
                                                    <c:if test="${pf.isDead}">
                                                        <img src="/image/deadlinePf.png" class="deadline">
                                                    </c:if>
                                                    <img src="/img/${pf.pfThumbnail}" class="img-hover">
                                                </a>
                                                <div class="info">
                                                    <div class="badge">
                                                        <c:if test="${pf.pfGenre == '단편영화' || pf.pfGenre == '장편영화'}">
                                                            <div class="category forMusical">${pf.pfGenre}</div>
                                                        </c:if>
                                                        <c:if test="${pf.pfGenre == '연극'}">
                                                            <div class="category forMusical">${pf.pfGenre}</div>
                                                        </c:if>
                                                        <c:if test="${pf.pfGenre == '드라마'}">
                                                            <div class="category forMusical">${pf.pfGenre}</div>
                                                        </c:if>
                                                        <c:if test="${pf.pfGenre == '웹 컨텐츠'}">
                                                            <div class="category forMusical">${pf.pfGenre}</div>
                                                        </c:if>
                                                        <c:if test="${pf.pfGenre == '광고'}">
                                                            <div class="category forMusical">${pf.pfGenre}</div>
                                                        </c:if>
                                                        <c:if test="${pf.pfGenre == '전시'}">
                                                            <div class="category forMusical">${pf.pfGenre}</div>
                                                        </c:if>
                                                        <c:if test="${pf.pfGenre == '기타'}">
                                                            <div class="category forMusical">${pf.pfGenre}</div>
                                                        </c:if>
                                                        <div class="age for19">${pf.pfAgerating}</div>
                                                        <c:if test="${pf.isPrice == true}">
                                                            <div class="charge">유료</div>
                                                        </c:if>
                                                        <c:if test="${pf.isPrice == false}">
                                                            <div class="charge">무료</div>
                                                        </c:if>
                                                    </div>
                                                    <h3 class="title">${pf.pfTitle}</h3>
                                                    <h4 class="location">${pf.pfLocation}</h4>
                                                    <h5 class="date">${pf.pfStartDate} ~ ${pf.pfDeadline}</h5>
                                                    <c:if test="${pf.isDead == true}">
                                                        <div class="opening" hidden>상영중</div>
                                                    </c:if>
                                                    <c:if test="${pf.isDead == false}">
                                                        <div class="opening" hidden>상영마감</div>
                                                    </c:if>
                                                </div>
                                            </article>
                                        </c:forEach>
                                    </section>
              <c:forEach var="PFlikeyList" items="${LikeyPFDetail}">
                <input id="jobId" type="hidden" value="${PFlikeyList.PFId}" />
                <input id="userId" type="hidden" value="${principal.userId}" />
                <article class="poster search">
                  <div class="img"></div>

                  <a href="/s/api/performance/detailForm/${PFlikeyList.PFId}">
                    <c:if test="${PFlikeyList.isDead == false}">
                      <img src="/image/deadlinePf.png" class="deadline">
                    </c:if>
                    <img src="/img/${PFlikeyList.thumbnail}" class="img-hover" />
                  </a>
                  <div class="info">
                    <div class="badge">
                      <c:if test="${PFlikeyList.genre == '단편영화' || PFlikeyList.genre == '장편영화'}">
                        <div class="category forMusical">${PFlikeyList.genre}</div>
                      </c:if>
                      <c:if test="${PFlikeyList.genre == '연극'}">
                        <div class="category forMusical">${PFlikeyList.genre}</div>
                      </c:if>
                      <c:if test="${PFlikeyList.genre == '드라마'}">
                        <div class="category forMusical">${PFlikeyList.genre}</div>
                      </c:if>
                      <c:if test="${PFlikeyList.genre == '웹 컨텐츠'}">
                        <div class="category forMusical">${PFlikeyList.genre}</div>
                      </c:if>
                      <c:if test="${PFlikeyList.genre == '광고'}">
                        <div class="category forMusical">${PFlikeyList.genre}</div>
                      </c:if>
                      <c:if test="${PFlikeyList.genre == '전시'}">
                        <div class="category forMusical">${PFlikeyList.genre}</div>
                      </c:if>
                      <c:if test="${PFlikeyList.genre == '기타'}">
                        <div class="category forMusical">${PFlikeyList.genre}</div>
                      </c:if>
                      <div class="age for19">${PFlikeyList.ageRating}</div>
                      <c:if test="${PFlikeyList.isPrice == true}">
                        <div class="charge">유료</div>
                      </c:if>
                      <c:if test="${PFlikeyList.isPrice == false}">
                        <div class="charge">무료</div>
                      </c:if>
                    </div>
                    <h3 class="title">${PFlikeyList.pfTitle}</h3>
                    <h4 class="location">${PFlikeyList.location}</h4>
                    <h5 class="date">${PFlikeyList.startdate} ~ ${PFlikeyList.deadline}</h5>
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
          <script src="/js/myLikey.js"></script>
          <script src="/js/default.js"></script>
          <%@ include file="layout/footer.jsp" %>
      </body>

      </html>