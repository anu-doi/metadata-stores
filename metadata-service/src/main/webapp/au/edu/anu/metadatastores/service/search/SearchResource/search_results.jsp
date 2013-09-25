<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty it.items}">
	${it.numItems} results found<br/>
	<table>
		<tr>
			<th>Source</th>
			<th>Title</th>
		</tr>
		<c:forEach var="item" items="${it.items}">
			<tr>
				<td>${item.extSystem}</td>
				<td><a href="<c:url value='/rest/display/${item.id}' />">${item.title}</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>