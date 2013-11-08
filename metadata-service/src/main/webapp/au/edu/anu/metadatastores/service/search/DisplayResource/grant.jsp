<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>

<anu:header id="1998" title="${it.grant.title}" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="/jsp/header.jsp" />

<anu:content layout="doublewide">
<h1>${it.grant.title}</h1>
<div>
	<span class="title text-uni">Contract Code</span> ${it.grant.contractCode}
</div>
<div>
	<span class="title text-uni">Status</span> ${it.grant.status}
</div>
<div>
	<span class="title text-uni">Funds Provider</span> ${it.grant.fundsProvider}
</div>
<div>
	<span class="title text-uni">Reference Number</span> ${it.grant.referenceNumber}
</div>
<div>
	<span class="title text-uni">Start Date</span> ${it.grant.startDate}
</div>
<div>
	<span class="title text-uni">End Date</span> ${it.grant.endDate}
</div>
<c:if test="${not empty it.grant.firstInvestigator}">
	<div>
		<span class="title text-uni">First Investigator</span>
		${it.grant.firstInvestigator.givenName} ${it.grant.firstInvestigator.surname}
	</div>
</c:if>
<c:if test="${not empty it.grant.associatedPeople}">
	<div>
		<span class="title text-uni">Investigators</span><br/>
		<ul class="removebullet">
		<c:forEach items="${it.grant.associatedPeople}" var="person">
			<li>${person.givenName} ${person.surname}</li>
		</c:forEach>
		</ul>
	</div>
</c:if>
<c:if test="${not empty it.grant.description}">
	<div>
		<span class="title text-uni">Description</span><br/> ${it.grant.description}
	</div>
</c:if>
<div>
	<span class="title text-uni">Subjects</span><br/>
	<ul class="removebullet">
	<c:forEach items="${it.grant.anzforSubjects}" var="subject">
		<li>${subject.code}<c:if test="${not empty subject.value}"> - ${subject.value}</c:if>, ${subject.percentage}%</li>
	</c:forEach>
	</ul>
</div>
</anu:content>

<jsp:include page="relations.jsp" />

<jsp:include page="/jsp/footer.jsp" />