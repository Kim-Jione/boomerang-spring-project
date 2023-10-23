<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <style>
        body{
        text-align: center;
        }
        .likeContent{
         display: inline-block;
         width: 80%;
         }
        .likeContent div{
            display: inline-block;
        }
        .pfContent{
            display: inline-block;
            width: 80%;
        }
        .pfContent div{
            display: inline-block;
        }
        .text_title{
            float: left;
        }
    </style>
</head>
        <body>
            <a href="/">
                <h1> 메인 페이지 </h1>
            </a>

            <h1>관심</h1>
            <div class="likeContent">
                <p class="text_title">구인정보</p>
                <c:forEach var="job" items="${jobList}">
                <div class="jobImg"><a href="#"> <img src="http://via.placeholder.com/280x300"> </a><p>${job.jobContentTitle}</p></div>
                <div class="jobTitle"></div>
                </c:forEach>
            </div>

            <div class="pfContent">
                <p class="text_title">전시공연</p>
                <c:forEach var="Performance" items="${perfomancesList}">
                <div><a href="#"> <img src="http://via.placeholder.com/280x300"> </a><p>${Performance.pfTitle}</p></div>
                <div></div>
                </c:forEach>
            </div>
        </body>
</html>
