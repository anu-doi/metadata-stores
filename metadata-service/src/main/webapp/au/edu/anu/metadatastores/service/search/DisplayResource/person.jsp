<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>

<c:set value="${it.person.givenName} ${it.person.surname}" var="title" />
<c:if test="${not empty it.person.displayName}">
	<c:set value="${it.person.displayName}" var="title" />
</c:if>

<anu:header id="1998" title="${title}" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="/jsp/header.jsp" />

<anu:content layout="doublewide">
<h1>${title}</h1>
<c:if test="${not empty it.person.uid}">
	<div>
		<span class="title text-uni">UID</span> ${it.person.uid}
	</div>
</c:if>
<div>
	<span class="title text-uni">Given Name</span> ${it.person.givenName}
</div>
<div>
	<span class="title text-uni">Surname</span> ${it.person.surname}
</div>
<c:if test="${not empty it.person.displayName}">
	<div>
		<span class="title text-uni">Display Name</span> ${it.person.displayName}
	</div>
</c:if>
<c:if test="${not empty it.person.ariesId}">
	<div>
		<span class="title text-uni">Aries ID</span> ${it.person.ariesId}
	</div>
</c:if>
<c:if test="${not empty it.person.email}">
	<div>
		<span class="title text-uni">Email</span> ${it.person.email}
	</div>
</c:if>
<c:if test="${not empty it.person.phoneNumbers}">
	<div>
		<span class="title text-uni">Phone Number(s)</span>
		<c:forEach items="${it.person.phoneNumbers}" var="phone" varStatus="count">
			<c:choose>
				<c:when test="${!count.last}">
					${phone},
				</c:when>
				<c:otherwise>
					${phone}
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</c:if>
<c:if test="${not empty it.person.faxNumbers}">
	<div>
		<span class="title text-uni">Fax Number(s)</span>
		<c:forEach items="${it.person.faxNumbers}" var="fax" varStatus="count">
			<c:choose>
				<c:when test="${!count.last}">
					${fax},
				</c:when>
				<c:otherwise>
					${fax}
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</c:if>
<c:if test="${not empty it.person.jobTitle}">
	<div>
		<span class="title text-uni">Job Title</span> ${it.person.jobTitle}
	</div>
</c:if>
<c:if test="${not empty it.person.preferredName}">
	<div>
		<span class="title text-uni">Preferred Name</span> ${it.person.preferredName}
	</div>
</c:if>
<c:if test="${not empty it.person.staffType}">
	<div>
		<span class="title text-uni">Staff Type</span> ${it.person.staffType}
	</div>
</c:if>
<c:if test="${not empty it.person.nlaId}">
	<div>
		<span class="title text-uni">NLA ID</span> ${it.person.nlaId}
	</div>
</c:if>
<c:if test="${not empty it.person.country}">
	<div>
		<span class="title text-uni">Country</span> ${it.person.country}
	</div>
</c:if>
<c:if test="${not empty it.person.institution}">
	<div>
		<span class="title text-uni">Institution</span> ${it.person.institution}
	</div>
</c:if>
<c:if test="${not empty it.person.description}">
	<div>
		<span class="title text-uni">Description</span><br/> ${it.person.description}
	</div>
</c:if>
<c:if test="${not empty it.person.anzforSubjects}">
	<div>
		<span class="title text-uni">Subjects</span><br/>
		<ul class="removebullet">
		<c:forEach items="${it.person.anzforSubjects}" var="subject">
			<li>${subject.code}<c:if test="${subject.value}"> - ${subject.value}</c:if>, ${subject.percentage}%</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
</anu:content>

<jsp:include page="relations.jsp" />

<jsp:include page="/jsp/footer.jsp" />