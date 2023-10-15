<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>메인 페이지 목차</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f2f2f2;
                    margin: 0;
                    padding: 0;
                }

                header {
                    background-color: #333;
                    color: #fff;
                    padding: 20px 0;
                    text-align: center;
                }

                h1 {
                    margin: 0;
                }


                nav {
                    background-color: #555;
                    color: #fff;
                    padding: 10px;
                }

                ul {
                    list-style: none;
                    padding: 0;
                }

                li {
                    margin: 5px 0;
                }

                li a {
                    text-decoration: none;
                    color: #fff;
                }

                li a:hover {
                    text-decoration: underline;
                }
                a:visited { color: green; text-decoration: none;}
                .container {
                    max-width: 500px;
                    margin: 20px auto;
                    padding: 10px 20px;
                    background-color: #fff;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                }
            </style>
        </head>

        <body>
            <header style="background-color: #333;color: #fff;padding: 20px 0;text-align: center;">
                <a href="/" style="text-decoration: none; outline: none "><h1>메인 페이지</h1></a>
            </header>
            <div class="container">
                <div style="display: flex; justify-content: space-between;">
                    <div>
                        <h3>화면 모음</h3>
                        <p><a href="/jobSearch/writeList">구인글 목록</a></p>
                        <p><a href="/user/joinListForm">사용자 목록</a></p>
                    </div>
                    <div>
                        <h3>기능 모음</h3>
                        <p><a href="/user/joinForm"> 회원가입</a></p>
                        <p> <a href="/user/loginForm">로그인</a></p>
                        <p> <a href="/user/logout">로그아웃</a></p>
                        <p><a href="/jobSearch/writeForm">구인정보 작성하기</a></p>
                    </div>
                </div>




            </div>
        </body>

        </html>