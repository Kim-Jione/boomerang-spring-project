<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            <div class="card mb-4">
            <div class="card-header">
                 <i class="fas fa-table me-1"></i>
                 사용자 정보
             </div>
                 <div class="card-body">
                     <table id="datatablesSimple">
                         <thead>
                             <tr>
                                <th>목 록</th>       
                                <th>아이디</th>
                                <th>이메일</th>
                                <th>닉네임</th>
                                <th>성별</th>
                                <th>키</th>
                                <th>체형</th>
                                <th>목소리톤</th>
                                <th>연령대</th>
                                <th>경력</th>
                                <th>학력</th>
                                <th>수정</th>
                                <th>삭제</th>
                             </tr>
                         </thead>
                         <tfoot>
                             <tr>
                                <th>목 록</th>
                                <th>아이디</th>
                                <th>이메일</th>
                                <th>닉네임</th>
                                <th>성별</th>
                                <th>키</th>
                                <th>체형</th>
                                <th>목소리톤</th>
                                <th>연령대</th>
                                <th>경력</th>
                                <th>학력</th>
                                <th>수정</th>
                                <th>삭제</th>
                             </tr>
                         </tfoot>
                         <tbody>
                            <c:forEach var="userList" items="${userList}"  varStatus="loop">
                                 <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${userList.userLoginId}</td>
                                    <td>${userList.userEmail}</td>
                                    <td>${userList.userNickname}</td>
                                    <td>${userList.userGender}</td>
                                    <td>${userList.userHeight}</td>
                                    <td>${userList.userForm}</td>
                                    <td>${userList.userTone}</td>
                                    <td>${userList.userAge}</td>
                                    <td>${userList.userCareer}</td>
                                    <td>${userList.userEducation}</td>
                                    <td><button class="btn btn-warning"> <a href="/s/api/auth/manage/userUpdateForm/${userList.userId}" class="nav-link collapsed">수정</a> </button></td>
                                    <td><button class="btn btn-danger" onclick="script"> 삭제 </button></td>
                                 </tr>
                            </c:forEach>
                         </tbody>
                     </table>
                 </div>
            </div>
        </div>
    </main>
</div>
</div>
<script>
</script>
<%@ include file="../layout/footer.jsp" %>