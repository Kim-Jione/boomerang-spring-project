<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/css/performance.css">
    <link rel="stylesheet" href="/css/default.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;600&display=swap"
          rel="stylesheet">
    <link
            href="https://fonts.googleapis.com/css?family=Material+Icons%7CMaterial+Icons+Outlined%7CMaterial+Icons+Two+Tone%7CMaterial+Icons+Round%7CMaterial+Icons+Sharp"
            rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <title>BusanMate with performance</title>
</head>

<body>
<%@ include file="layout/header.jsp" %>
<div class="banner">
    <swiper-container class="banner_Swiper" pagination="true" pagination-clickable="true" space-between="30"
                      centered-slides="true" autoplay-delay="4000" autoplay-disable-on-interaction="false" loop="true">
        <swiper-slide class="banner_slide1"><img src="/image/banner1.png"></swiper-slide>
        <swiper-slide class="banner_slide2"><img src="/image/banner2.png"></swiper-slide>
        <swiper-slide class="banner_slide3"><img src="/image/banner3.png"></swiper-slide>
    </swiper-container>
</div>

<div class="ranking_post">
    <ul class="title_area">
        <li><i class="fa-solid fa-fire"></i></li>
        <li>
            <h2>인기 전시 공연글</h2>
        </li>
    </ul>
    <div class="top_list">
        <c:forEach var="best" items="${bestPfList}">
        <div class="top">
            <img class="medal" src="${best.imgPath}">
            <article class="poster">
                <div class="img"></div>
                <a href="/s/api/performance/detailForm/${best.pfId}">

                    <img src="/image/poster/${best.pfThumbnail}" class="img-hover">
                </a>
                <div class="info">
                    <div class="badge">
                        <div class="category forMusical">${best.pfGenre}</div>
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
        </div>
    </c:forEach>
    </div>
</div>

<div class="main_post">
    <ul class="main_title">
        <li><i class="fa-solid fa-compact-disc"></i></li>
        <li>
            <h2>전시 공연 정보</h2>
        </li>
    </ul>

    <div class="filter">
        <div class="dropdown_list">
            <select id="filterGenre" onchange="filterPosts()" class="dropdown">
                <option value="all">장르</option>
                <option value="단편영화">단편영화</option>
                <option value="장편영화">장편영화</option>
                <option value="연극">연극</option>
                <option value="OTT/TV 드라마">OTT/TV 드라마</option>
                <option value="웹 컨텐츠">웹 컨텐츠</option>
                <option value="광고">광고</option>
                <option value="전시">전시</option>
                <option value="기타">기타</option>
            </select>

            <select id="filterAge" onchange="filterPosts()" class="dropdown">
                <option value="all">연령대</option>
                <option value="전체관람가">전체관람가</option>
                <option value="12세 이상">12세 이상</option>
                <option value="15세 이상">15세 이상</option>
                <option value="19세 이상">19세 이상</option>
            </select>

            <select id="filterRun" onchange="filterPosts()" class="dropdown">
                <option value="all">상영 여부</option>
                <option value="상영중">상영중</option>
                <option value="상영마감">상영마감</option>
            </select>

            <select id="filterCharge" onchange="filterPosts()" class="dropdown">
                <option value="all">유/무료</option>
                <option value="유료">유료</option>
                <option value="무료">무료</option>
            </select>
        </div>
        <div class="search_bar">
            <input type="text" id="filterText" oninput="filterSearch()" placeholder="제목, 장르를 입력해보세요.">
            <button><i class="fa-solid fa-magnifying-glass"></i></button>
        </div>
    </div>

    <!-- 전시 공연 정보 -->
    <section class="poster_wrapper">
        <c:forEach var="pf" items="${pfList}">
            <article class="poster search">
                <div class="img"></div>
                <a href="/s/api/performance/detailForm/${pf.pfId}">
                    <img src="${pf.pfThumbnail}" class="img-hover">
                </a>
                <div class="info">
                    <div class="badge">
                        <div class="category forMusical">${pf.pfGenre}</div>
                        <div class="age for19">${pf.pfAgerating}</div>
                        <c:if test="${pf.isPrice == true}">
                            <div class="charge">유료</div>
                        </c:if>
                        <c:if test="${pf.isPrice == false}">
                            <div class="charge">무료</div>
                        </c:if>
                    </div>

                    <h3 class="title">소울메이트</h3>
                    <div class="like_plus">
                      <h4 class="location">영화의 전당</h4>
                      <div class="like_btn">
                        <span class="heart"></span>
                      </div>
                    </div>
                    <h5 class="date">2023.11.14 ~ 2023.12.25</h5>
                  </div>
                </article>
              </div>

              <div class="top">
                <img class="medal" src="/image/ranking3.png">
                <article class="poster">
                  <div class="img"></div>
                  <a href="#">
                    <img src="/image/poster/poster_4.jpg" class="img-hover">
                  </a>
                  <div class="info">
                    <div class="badge">
                      <div class="category forTheater"> <i class="fa-solid fa-masks-theater"> 연극</i> </div>
                      <div class="age forAll">ALL</div>
                      <div class="charge">유료</div>
                    </div>
                    <h3 class="title">소리</h3>
                    <h4 class="location">부산 문화 회관</h4>
                    <h5 class="date">2023.11.14 ~ 2023.12.25</h5>
                  </div>
                </article>
              </div>

            </div>
          </div>


          <div class="main_post">
            <ul class="main_title">
              <li><i class="fa-solid fa-compact-disc"></i></li>
              <li>
                <h2>전시 공연 정보</h2>
              </li>
            </ul>

            <div class="filter">
              <div class="dropdown_list">
                <select id="filterGenre" onchange="filterPosts()" class="dropdown">
                  <option value="all">장르</option>
                  <option value="영화">영화</option>
                  <option value="연극">연극</option>
                  <option value="뮤지컬">뮤지컬</option>
                  <option value="전시/행사">전시/행사</option>
                </select>

                <select id="filterAge" onchange="filterPosts()" class="dropdown">
                  <option value="all">연령대</option>
                  <option value="전체이용가">전체이용가</option>
                  <option value="12세 이상">12세 이상</option>
                  <option value="15세 이상">15세 이상</option>
                  <option value="19세 이상">19세 이상</option>
                </select>

                <select id="filterRun" onchange="filterPosts()" class="dropdown">
                  <option value="all">상영 여부</option>
                  <option value="상영중">상영중</option>
                  <option value="싱양 마감">상영 마감</option>
                </select>

                <select id="filterCharge" onchange="filterPosts()" class="dropdown">
                  <option value="all">유/무료</option>
                  <option value="유료">유료</option>
                  <option value="무료">무료</option>
                </select>
              </div>
              <div class="search_bar">
                <input type="text" id="filterText" oninput="filterPosts()" placeholder="제목, 장르를 입력해보세요.">
                <button><i class="fa-solid fa-magnifying-glass"></i></button>
              </div>
            </div>

            <!-- 전시 공연 정보 -->
            <section class="poster_wrapper">

              <article class="poster search">
                <div class="img"></div>
                <a href="#">
                  <img src="/image/poster/poster_1.jpg" class="img-hover">
                </a>
                <div class="info">
                  <div class="badge">
                    <div class="category forMusical"> <i class="fa-solid fa-music"> 뮤지컬</i> </div>
                    <div class="age for19">19</div>
                    <div class="charge">유료</div>
                  </div>
                  <h3 class="title">밑바닥에서</h3>
                  <h4 class="location">예술의 전당 CJ극장</h4>
                  <h5 class="date">2023.11.14 ~ 2023.12.25</h5>
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


<footer class="footer">
    <div class="left_cover"></div>
</footer>

<script src="https://kit.fontawesome.com/3f247b3389.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-element-bundle.min.js"></script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="/js/performance.js"></script>
<script src="/js/default.js"></script>

</body>

</html>