<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>

<anu:header id="1998" title="Item Relations - ${it.item.title}" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/relations.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="jsp/header.jsp" />

<anu:content layout="doublewide">
	<h1>Welcome to ANU Metadata Stores</h1>
</anu:content>

<jsp:include page="jsp/footer.jsp" />