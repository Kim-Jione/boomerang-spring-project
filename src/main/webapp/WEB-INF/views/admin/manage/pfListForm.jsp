<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="./layout/header.jsp" %>
        <%@ include file="./layout/sidebar.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">공연 홍보글</h1>
                        <div class="card mb-4">
                            <div class="card-body">
                                공연 홍보글의 정보를 관리합니다.
                            </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                공연 홍보글 정보
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>제목</th>
                                            <th>장르</th>
                                            <th>조회수</th>
                                            <th>상태</th>
                                            <th>작성일</th>
                                            <th>수정</th>
                                            <th>삭제</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>번호</th>
                                            <th>제목</th>
                                            <th>장르</th>
                                            <th>조회수</th>
                                            <th>상태</th>
                                            <th>작성일</th>
                                            <th>수정</th>
                                            <th>삭제</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach var="pf" items="${pfList}" varStatus="loop">
                                            <tr>
                                                <td>${loop.index + 1}</td>
                                                <td>${pf.pfTitle}</td>
                                                <td>${pf.pfGenre}</td>
                                                <td>${pf.pfView}</td>
                                                <td>${pf.pfStatus}</td>
                                                <td>${pf.createdAt}</td>
                                                <td>
                                                    <a href="/admin/updateForm/${pf.userId}"
                                                        class="btn btn-warning">수정</a>
                                                </td>
                                                <td>
                                                    <form action="/admin/delete/${pf.userId}" method="delete"
                                                        onsubmit="return confirmDelete()">
                                                        <button type="submit" class="btn btn-danger">
                                                            삭제
                                                        </button>
                                                    </form>
                                                </td>
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
                function confirmDelete() {
                    return confirm("정말 삭제하시겠습니까?");
                }
            </script>
            <%@ include file="./layout/footer.jsp" %>