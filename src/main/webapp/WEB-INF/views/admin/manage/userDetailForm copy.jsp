<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <%@ include file="../layout/sidebar.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">사용자</h1>
                        <div class="card mb-4">
                            <div class="card-body">
                                사용자의 정보를 관리합니다.
                            </div>
                        </div>
                        <div class="mb-3 mt-3">
                            아이디 : ${userPS.userLoginId}
                        </div>
                        <div class="mb-3 mt-3">
                            이메일 :
                            <input id="userEmail" type="text" value="${userPS.userEmail}" />
                        </div>
                        <div class="mb-3 mt-3">
                            닉네임 :
                            <input id="userNickname" type="text" value="${userPS.userNickname}" />
                        </div>
                        <div class="mb-3 mt-3">
                            연령대 :
                            <input id="userAge" type="text" value="${userPS.userAge}" />
                        </div>
                        <div class="mb-3 mt-3">
                            포지션 :
                            <input id="userPosition" type="text" value="${userPS.userPosition}" />
                        </div>
                        <div class="mb-3 mt-3">
                            성별 :
                            <input id="userGender" type="text" value="${userPS.userGender}" />
                        </div>
                        <div class="mb-3 mt-3">
                            경력 :
                            <input id="userCareer" type="text" value="${userPS.userCareer}" />
                        </div>
                        <div class="mb-3 mt-3">
                            자기소개 : ${userPS.userIntro}
                        </div>
                        <div class="mb-3 mt-3">
                            포트폴리오 링크 :
                            <input id="userPortfolioLink" type="text" value="${userPS.userPortfolioLink}" />
                        </div>
                        <div class="mb-3 mt-3">
                            연락방법 :
                            <input id="userContactLink" type="text" value="${userPS.userContactLink}" />
                        </div>
                        <div class="mb-3 mt-3">
                            프로필 이미지 :
                            <input id="userProfileImg" type="text" value="${userPS.userProfileImg}" />
                        </div>
                        <div class="mb-3 mt-3">
                            권한 :
                            <input id="userRole" type="text" value="${userPS.userRole}" />
                        </div>
                        <div class="mb-3 mt-3">
                            상태 :
                            <input id="userStatus" type="text" value="${userPS.userStatus}" />
                        </div>
                        <div class="mb-3 mt-3">
                            회원가입일 :
                            <input id="createdAt" type="text" value="${userPS.createdAt}" />
                        </div>
                        <div class="mb-3 mt-3">
                            회원정보 수정일 :
                            <input id="updatedAt" type="text" value="${userPS.updatedAt}" />
                        </div>
                    </div>
                </main>
            </div>
            </div>
            <%@ include file="../layout/footer.jsp" %>