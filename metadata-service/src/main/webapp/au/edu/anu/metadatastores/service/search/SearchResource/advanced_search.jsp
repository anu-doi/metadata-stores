<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<anu:header id="1998" title="Search" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />" />
	<script type="text/javascript" src="<c:url value='/js/attributes.js' />"></script>
</anu:header>

<jsp:include page="/jsp/header.jsp" />

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
	<input type="submit" value="Search" />
	</form>
	<br/>
	<jsp:include page="search_results.jsp" />
</anu:content>

<jsp:include page="/jsp/footer.jsp" />