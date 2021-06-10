<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${board.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${board.contents }<br>
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<c:choose>
						<c:when test='${authUser.name == null}'>
							<a href="${pageContext.servletContext.contextPath }/board?a=list">글목록</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.servletContext.contextPath }/board?a=writeform&no=${param.no}">댓글달기</a>
							<c:if test="${authUser.no == board.userNo }">
								<a href="${pageContext.servletContext.contextPath }/board?a=modify&name=${authUser.name}&no=${param.no}">글수정</a>
							</c:if>
							<a href="${pageContext.servletContext.contextPath }/board?a=list">글목록</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>