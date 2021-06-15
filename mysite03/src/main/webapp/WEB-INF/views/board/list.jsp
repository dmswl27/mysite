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
<link
	href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board}" method="post">
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
					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${list }" var="board"  begin = "0" end = "${map.countBoard }" varStatus="status">
						<tr>
							<td>${map.totalCount-status.index }</td>
							<c:if test="${board.depth == 0}">
								<td style="text-align: left; padding-left: ${board.depth *20}px"><a
									href="${pageContext.servletContext.contextPath }/board/view/${board.no}">${board.title }</a></td>
							</c:if>
							<c:if test="${board.depth >=1 }">
								<td style="text-align: left; padding-left: ${board.depth *20}px"><img
									src='${pageContext.servletContext.contextPath }/assets/images/reply.png' /><a
									href="${pageContext.servletContext.contextPath }/board/view/${board.no}">${board.title }</a></td>
							</c:if>
							<td style="text-align: center">${board.userName }</td>
							<td>${board.hit }</td>
							<td>${board.regdate}</td>
							<td><c:choose>
									<c:when test="${board.userName eq authUser.name }">
										<a
											href="${pageContext.servletContext.contextPath }/board/delete/${board.no}"><img
											src='${pageContext.servletContext.contextPath }/assets/images/recycle.png' /></a>
									</c:when>

								</c:choose></td>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${map.page <= 10 }">
								<li>◀</li>
							</c:when>
							<c:otherwise>
								<li><a href="/mysite03/board/${map.firstPageNo - 10}">◀</a></li>
							</c:otherwise>


						</c:choose>
						<c:forEach var="pageNo" begin="${map.firstPageNo }"	end="${map.lastPageNo }">
							<c:choose>
								<c:when test="${pageNo eq map.currentPageNo}">
									<li class="selected">${pageNo}</li>
								</c:when>
								<c:otherwise>
									<li><a href="/mysite03/board/${pageNo }">${pageNo }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>


							<c:when test="${map.firstPageNo + 10 >= map.totalPage}">
								<li>▶</li>
							</c:when>
							<c:otherwise>
								<li><a href="/mysite03/board/${map.firstPageNo + 10}">▶</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- pager 추가 -->
				<c:if test="${not empty authUser }">
					<div class="bottom">
					<a
						href="${pageContext.servletContext.contextPath }/board/write"
						id="new-book">글쓰기</a>
					</div>
				</c:if>
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>