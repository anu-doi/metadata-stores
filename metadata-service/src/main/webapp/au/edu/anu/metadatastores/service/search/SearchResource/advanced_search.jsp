<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<anu:header id="1998" title="Search" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />" />
	<script type="text/javascript" src="<c:url value='/js/attributes.js' />"></script>
</anu:header>

<jsp:include page="/jsp/header.jsp" />

<c:set var="rows" value="${it.rows}" />
<c:set var="searchURLPart" value="/rest/search/advanced" />

<anu:content layout="doublewide">
	<h1>Search Metadata Stores</h1>
	<form action="" method="GET">
	<div class="term">
		<label for="search-val">Value to search for:</label>
	</div>
	<p>Additional Filters</p>
	<div class="term">
		<label for="system">Source:</label>
		<select name="system" class="system">
			<option value="">All</option>
			<c:forEach var="sysType" items="${it.systemTypes}">
				<option value="${sysType.extSystem}"
					<c:if test="${sysType.extSystem == param['system']}">selected</c:if>
				>${sysType.title}</option>
			</c:forEach>
		</select>
	</div>
	<div>
		<input type="button" id="addSearchField" value="Add Search Field" />
	</div>
	<div id="terms">
		<c:choose>
			<c:when test="${not empty param['search-val[]']}">
				<c:forEach items="${paramValues['search-val[]']}" var="searchVal" varStatus="status">
					<c:set var="fieldVal" value="${paramValues['field[]'][status.count - 1]}" />
					<div class="term">
						<label for="field[]">Field:</label>
						<select name="field[]" class="field">
							<c:forEach var="attrType" items="${it.attrTypes}">
								<option value="${attrType.attrType}"
									<c:if test="${attrType.attrType == fieldVal}">selected</c:if>
								>${attrType.title}</option>
							</c:forEach>
						</select>
						<input type="text" name="search-val[]" value="${searchVal}" />
					</div>
				</c:forEach>
				
			</c:when>
			<c:otherwise>
				<div class="term">
					<label for="field[]">Field:</label>
					<select name="field[]" class="field">
						<c:forEach var="attrType" items="${it.attrTypes}">
							<option value="${attrType.attrType}"
								<c:if test="${attrType.attrType == param['field']}">selected</c:if>
							>${attrType.title}</option>
						</c:forEach>
					</select>
					<input type="text" name="search-val[]" value="${param['search-val']}" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<input type="hidden" name="page" value="0" />
	<input type="hidden" name="rows" value="${rows}" />
	<input type="submit" value="Search" />
	</form>
	<br/>
	<jsp:include page="search_results.jsp" />
	
	<c:if test="${not empty it.numItems}">
		<fmt:formatNumber var="numPages" value="${it.numItems / it.rows }" groupingUsed="false" />
		<c:set var="numPages" value="${fn:substringBefore(numPages, '.')}" />
		<c:url var="startURL" value="${searchURLPart}">
			<c:param name="rows">${it.rows}</c:param>
			<c:param name="page">0</c:param>
			<c:param name="system">${param.system}</c:param>
			<c:forEach items="${paramValues['search-val[]']}" var="searchVal">
				<c:param name="search-val[]">${searchVal}</c:param>
			</c:forEach>
			<c:forEach items="${paramValues['field[]']}" var="fieldVal">
				<c:param name="field[]">${fieldVal}</c:param>
			</c:forEach>
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
						<c:param name="system">${param.system}</c:param>
						<c:forEach items="${paramValues['search-val[]']}" var="searchVal">
							<c:param name="search-val[]">${searchVal}</c:param>
						</c:forEach>
						<c:forEach items="${paramValues['field[]']}" var="fieldVal">
							<c:param name="field[]">${fieldVal}</c:param>
						</c:forEach>
					</c:url>
					<a href="${pageURL}">${pageVal + 1}</a>
				</c:when>
			</c:choose>
		</c:forEach>
		
		<c:url var="endURL" value="${searchURLPart}">
			<c:param name="rows">${it.rows}</c:param>
			<c:param name="page">${numPages}</c:param>
			<c:param name="system">${param.system}</c:param>
			<c:forEach items="${paramValues['search-val[]']}" var="searchVal">
				<c:param name="search-val[]">${searchVal}</c:param>
			</c:forEach>
			<c:forEach items="${paramValues['field[]']}" var="fieldVal">
				<c:param name="field[]">${fieldVal}</c:param>
			</c:forEach>
		</c:url>
		<a href="${endURL}">&gt;&gt;</a>
	</c:if>
</anu:content>

<jsp:include page="/jsp/footer.jsp" />