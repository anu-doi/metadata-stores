<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>

<anu:header id="1998" title="${it.publication.title}" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="/jsp/header.jsp" />

<anu:content layout="doublewide">
<h1>${it.publication.title}</h1>
<div>
	<span class="title text-uni">Type</span>${it.publication.type}
</div>
<div>
	<span class="title text-uni">Publication Name</span>${it.publication.publicationName}
</div>
<div>
	<span class="title text-uni">Aries ID</span>${it.publication.ariesId}
</div>
<div>
	<span class="title text-uni">Year</span>${it.publication.year}
</div>
<c:if test="${not empty it.publication.firstAuthor}">
	<div>
		<span class="title text-uni">First Author</span>${it.publication.firstAuthor}
	</div>
</c:if>
<div>
	<span class="title text-uni">Category</span>${it.publication.category}
</div>
<c:choose>
	<c:when test="${not empty it.publication.ISBN}">
		<div>
			<span class="title text-uni">ISBN</span>${it.publication.ISBN}
		</div>
	</c:when>
	<c:when test="${not empty it.publication.ISSN}">
		<div>
			<span class="title text-uni">ISSN</span>${it.publication.ISSN}
		</div>
	</c:when>
</c:choose>
<div>
	<span class="title text-uni">Authors</span>
	<ul class="removebullet">
	<c:forEach items="${it.publication.authors}" var="author">
		<li>${author.givenName} ${author.surname}</li>
	</c:forEach>
	</ul>
</div>
<div>
	<span class="title text-uni">Subjects</span>
	<ul class="removebullet">
	<c:forEach items="${it.publication.anzforSubjects}" var="subject">
		<li>${subject.code}<c:if test="${not empty subject.value}"> - ${subject.value}</c:if>, ${subject.percentage}%</li>
	</c:forEach>
	</ul>
</div>

</anu:content>

<jsp:include page="relations.jsp" />

<jsp:include page="/jsp/footer.jsp" />