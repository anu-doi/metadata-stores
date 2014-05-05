<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<anu:header id="1998" title="Search" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/relations.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="/jsp/header.jsp" />

<c:set var="rows" value="${it.rows}" />
<c:set var="searchURLPart" value="/rest/search" />

<anu:content layout="doublewide">
	<h1>Search Metadata Stores</h1>
	<form action="" method="GET">
	Value to Search For:<input type="text" name="search-val" value="${param['search-val']}" />
	<br/>
	<input type="submit" value="Search" />
	<input type="hidden" name="page" value="0" />
	<input type="hidden" name="rows" value="${rows}" />
	</form>
	<br/>
	<jsp:include page="search_results.jsp" />
	
	<c:if test="${not empty it.numItems}">
		<fmt:formatNumber var="numPages" value="${it.numItems / it.rows }" groupingUsed="false" />
		<c:if test="${fn:contains(numPages, '.')}">
			<c:set var="numPages" value="${fn:substringBefore(numPages, '.')}" />
		</c:if>
		<c:url var="startURL" value="${searchURLPart}">
			<c:param name="rows">${it.rows}</c:param>
			<c:param name="page">0</c:param>
			<c:param name="search-val">${param["search-val"]}</c:param>
		</c:url>
		<a href="${startURL}">&lt;&lt;</a>
		<c:forEach begin="0" end="${numPages}" var="pageVal">
			<c:choose>
				<c:when test="${pageVal == param.page}">
					${pageVal + 1}
				</c:when>
				<c:when test="${pageVal > param.page - 5 and pageVal < param.page + 5}">
					<c:url var="pageURL" value="${searchURLPart}">
						<c:param name="rows">${it.rows}</c:param>
						<c:param name="page">${pageVal}</c:param>
						<c:param name="search-val">${param["search-val"]}</c:param>
					</c:url>
					<a href="${pageURL}">${pageVal + 1}</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:url var="endURL" value="${searchURLPart}">
			<c:param name="rows">${it.rows}</c:param>
			<c:param name="page">${numPages}</c:param>
			<c:param name="search-val">${param["search-val"]}</c:param>
		</c:url>
		<a href="${endURL}">&gt;&gt;</a>
	</c:if>
</anu:content>

<jsp:include page="/jsp/footer.jsp" />