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
        a, a:visited{
            text-decoration: none;
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

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <header>
        <h1>메인 페이지 목차</h1>
    </header>


    <div class="container">
        <h1><a href="/"> 메인 페이지 </a></h1>
        <h3><a href="/"> 기능 모음 </a></h3>
        <p><a href="/ex/exMainForm"> CRUD 예제 </a></p>
        <p><a href="/jobSearch/writeList">구인정보 목록</a></p>
        <h3><a href="/"> 화면 모음 </a></h3>
        <p><a href="/user/joinForm"> 회원가입 기능</a></p>
        <p> <a href="/user/loginForm">로그인 기능</a></p>
        <p><a href="/jobSearch/writeForm">구인정보 작성하기 기능</a></p>
    </div>
</body>
</html>
