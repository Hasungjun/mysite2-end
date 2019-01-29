<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board?a=search" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list }" var="vo" varStatus="status">
					<tr>
						<td>${size=size-1} </td>
						<td style="padding-left:${20*vo.depth }px">
						
						<c:if test="${vo.depth ne 0 }">
   						<img src="/mysite2/assets/images/reply.png"/> 
						</c:if>

						 <a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no }">${vo.title }</a></td>
						<td>${vo.user_name }</td>
						<td>${vo.hit }</td>
						<td>${vo.write_date }</td>
						
						<c:choose>
					<c:when test="${authuser.no eq vo.user_no }">
						<td><a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no }" class="del">삭제</a></td>
					</c:when>
					<c:otherwise>
						<td>삭제불가</td>
					</c:otherwise>
				</c:choose>
						
					</tr>
				</c:forEach>
				</table>
			
			
			
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="${pageContext.servletContext.contextPath }/board?a=search&kwd=${search }&pg=${pg-1 }">◀</a></li>
						
						<c:forEach begin="${startPage }" end="${endPage }" var="i">
   							<li <c:if test="${pg eq i }"> class = 'selected' </c:if>><a href="${pageContext.servletContext.contextPath }/board?a=search&kwd=${search }&pg=${i }">${i }</a></li> 	
						</c:forEach>
						
						<li><a href="${pageContext.servletContext.contextPath }/board?a=search&kwd=${search }&pg=${pg+1 }">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
			
			
			
			
			
				<c:choose>
					<c:when test="${empty authuser }">
						
				</c:when>
				<c:otherwise>
				<div class="bottom">
						<a href="${pageContext.servletContext.contextPath }/board?a=writeaction" id="new-book">글쓰기</a>
				</div>	
					</c:otherwise>
				</c:choose>	
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>