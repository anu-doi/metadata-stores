<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>

<anu:header id="1998" title="${it.dublinCore.titles[0]}" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="/jsp/header.jsp" />

<anu:content layout="doublewide">

<h1>${it.dublinCore.titles[0]}</h1>
<c:if test="${not empty it.dublinCore.titles}">
	<div>
	<h3>Titles</h3>
	<ul class="removebullet">
	<c:forEach items="${it.dublinCore.titles}" var="title">
		<li>${title}</li>
	</c:forEach>
	</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.creators}">
	<div>
		<h3>Creators</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.creators}" var="creator">
			<li>${creator}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.subjects}">
	<div>
		<h3>Subjects</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.subjects}" var="subject">
			<li>${subject}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.descriptions}">
	<div>
		<h3>Descriptions</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.descriptions}" var="description">
			<li>${description}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.publishers}">
	<div>
		<h3>Publishers</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.publishers}" var="publisher">
			<li>${publisher}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.contributors}">
	<div>
		<h3>Contributors</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.contributors}" var="contributor">
			<li>${contributor}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.dates}">
	<div>
		<h3>Dates</h3>
		<ul class="removebullet">
			<c:forEach items="${it.dublinCore.dates}" var="date">
				<li>${date}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.types}">
	<div>
		<h3>Types</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.types}" var="type">
			<li>${type}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.formats}">
	<div>
		<h3>Formats</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.formats}" var="format">
			<li>${format}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.identifiers}">
	<div>
		<h3>Identifiers</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.identifiers}" var="identifier">
			<li>${identifier}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.sources}">
	<div>
		<h3>Sources</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.sources}" var="source">
			<li>${source}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.languages}">
	<div>
		<h3>Languages</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.languages}" var="language">
			<li>${language}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.relations}">
	<div>
		<h3>Relations</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.relations}" var="relation">
			<li>${relation}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.coverage}">
	<div>
		<h3>Coverage</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.coverage}" var="coverage">
			<li>${coverage}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.dublinCore.rights}">
	<div>
		<h3>Rights</h3>
		<ul class="removebullet">
		<c:forEach items="${it.dublinCore.rights}" var="right">
			<li>${right}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>

</anu:content>

<jsp:include page="relations.jsp" />

<jsp:include page="/jsp/footer.jsp" />