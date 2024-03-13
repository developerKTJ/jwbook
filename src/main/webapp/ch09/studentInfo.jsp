<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<script type="text/javascript"><%@ include file="student.js" %></script> 
<style type="text/css"><%@ include file="student.css" %></style>
<title>학생 관리</title>
</head>
<body>
<div class="inner">
  <h1>학생 정보</h1>
  <div class="select">
    <form method="post" action="/jwbook/studentControl?action=select">
      <label for="id">아이디</label>
      <input type="text" name="id">
      <label for="username">이름</label>
      <input type="text" name="username">
      <label for="univ">대학</label>
      <input type="text" name="univ">
      <button type="submit">조회</button>
    </form>
  </div>
  <hr>
  <div class="content inner">
    <div class="table-wrap">
      <h2>학생 목록</h2>
      <table border="1">
        <tr><th>id</th><th>이름</th><th>대학</th></tr>
        <c:forEach items="${students}" var="s">
          <tr><td>${s.id}</td><td>${s.username}</td><td>${s.univ}</td><td class="none">${s.birth}</td><td class="none">${s.email}</td></tr>
        </c:forEach>
        </table>
    </div>
    <div class="menu-wrap">
      <div data-type="detail" class="detail-wrap menu active">
        <h2>상세 정보</h2>
        <div class="detail menu-content">
          <label>아이디</label>
          <input type="text" readonly name="id"><br>
          <label>이 &nbsp; 름</label>
          <input type="text" readonly name="username"><br>
          <label>대 &nbsp; 학</label>
          <input type="text" readonly name="univ"><br>
          <label>생 &nbsp; 일</label>
          <input type="text" readonly name="birth"><br>
          <label>이메일</label>
          <input type="text" readonly name="email">
        </div>
        <div class="btn-group">
          <button data-type="insert" class="insert">추가</button><button data-type="update" class="update">
            수정</button><button data-type="delete" class="delete">
            삭제</button><button class="main-btn">
            메인</button>
        </div>
      </div>
      <div data-type="insert" class="insert-wrap menu">
        <h2>학생 추가</h2>
        <form class="insert menu-content" method="post" action="/jwbook/studentControl?action=insert">
          <label>이 &nbsp; 름</label>
          <input type="text" name="username"><br>
          <label>대 &nbsp; 학</label>
          <input type="text" name="univ"><br>
          <label>생 &nbsp; 일</label>
          <input type="text" name="birth"><br>
          <label>이메일</label>
          <input type="text" name="email"><br>
          <div class="btn-group">
            <button class="menu-btn">취소</button><button type="submit">
              추가</button>
          </div>
          </form>
      </div>
      <div data-type="update" class="update-wrap menu">
        <h2>학생 정보 수정</h2>
        <form class="update menu-content" method="post" action="/jwbook/studentControl?action=update">
          <label>아이디</label>
          <input type="text" readonly name="id"><br>
          <label>이 &nbsp; 름</label>
          <input type="text" name="username"><br>
          <label>대 &nbsp; 학</label>
          <input type="text" name="univ"><br>
          <label>생 &nbsp; 일</label>
          <input type="text" name="birth"><br>
          <label>이메일</label>
          <input type="text" name="email"><br>
          <div class="btn-group">
            <button class="menu-btn">취소</button><button type="submit">
              수정</button>
          </div>
          </form>
      </div>
      <div data-type="delete" class="delete-wrap menu">
        <h2>학생 삭제</h2>
        <form class="delete menu-content" method="post" action="/jwbook/studentControl?action=delete">
          <label>아이디</label>
          <input type="text" name="id"><br>
          <label>이 &nbsp; 름</label>
          <input type="text" name="username"><br>
          <div class="btn-group">
            <button class="menu-btn">취소</button><button type="submit">
              삭제</button>
          </div>
          </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>