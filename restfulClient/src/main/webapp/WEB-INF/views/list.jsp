<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center">
<h2>목록보기</h2>
<h5>
start : ${start}  /  end : ${end}  /  <br />
리스트사이즈 : ${listSize}  /  리스트시작페이지 : ${listStartpage}  /  리스트끝페이지 : ${listEndpage}  /  <br />
현재페이지 : ${currentPage}  /  전체레코드 : ${totalRecord}  /  전체페이지 : ${totalPage} / <br />
</h5>

<table border=1>
<tr>
	<th>번호</th><th>이름</th><th>나이</th><th>메모</th>
</tr>
<c:forEach var="m" items="${li}" >
<tr>
	<td>${m.idx}</td>
	<td>${m.name}</td>
	<td>${m.age}</td>
	<td>${m.memo}</td>
</tr>
</c:forEach>
</table>

<form action="/list">
<input type="hidden" name="start" value="0" />
<input type="hidden" name="end" value="10" />
<select name="searchCondition">
	<option value="name">이름</option>
</select>
<input type="text" name="searchKeyword" />
<input type="submit" value="검색하기" />
</form>

<!-- 첫페이지 -->
<c:if test="${start != 1}">
<a href="/list?start=1&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">첫페이지</a>
</c:if>

<!-- 이전페이지 -->
<c:if test="${listStartpage > listSize}">
<a href="/list?start=${prePage}&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">...</a>
</c:if>

<!-- 숫자페이지 -->
<c:forEach var="i" begin="${listStartpage}" end="${listEndpage}" step="1">
	<c:set var="start" value="${(i-1) * pageSize + 1}" />
	<c:set var="end" value="${i * pageSize}" />
	<c:if test="${end > totalRecord}">
    	<c:set var="end" value="${totalItems}" />
    </c:if>
	<c:if test="${i <= totalPage}">
		<a href="/list?start=${start}&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">${i}</a>
	</c:if>
</c:forEach>

<!-- 다음 페이지 링크 -->
<c:if test="${listEndpage < totalPage}">
<a href="/list?start=${nextPage}&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">...</a>
</c:if>

<!-- 마지막페이지 -->
<c:if test="${totalPage > listEndpage}">
<a href="/list?start=${lastPage}&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">끝페이지</a>
</c:if>

</div>

</body>
</html>