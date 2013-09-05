<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>

<anu:header id="1998" title="Search" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/relations.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="/jsp/header.jsp" />

<anu:content layout="doublewide">
	<h1>Search Metadata Stores</h1>
	<form action="" method="GET">
	Value to Search For:<input type="text" name="search-val" value="${param['search-val']}" />
	<br/>
	<input type="submit" value="Search" />
	</form>
	<br/>
	<jsp:include page="search_results.jsp" />
</anu:content>

<jsp:include page="/jsp/footer.jsp" />