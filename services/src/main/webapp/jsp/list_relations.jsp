<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>

<anu:header id="1998" title="Item Relations - ${it.item.title}" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/relations.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="header.jsp" />

<anu:content layout="doublewide">
	<c:if test="${not empty(it.item)}">
		<h1>${it.item.title}</h1>
		<table>
			<c:forEach var="attr" items="${it.item.itemAttributes}">
				<tr>
					<td>${attr.attrType}</td>
					<td>${attr.attrValue}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	Relationships
	<c:if test="${not empty(it.relations)}">
		<ol class="tree">
			<li class="hasChildren">
				<label for="${it.item.iid}">${it.relations[0].itemTitle}</label><input type="checkbox" value="${it.item.iid}" checked />
				<ol>
					<c:forEach var="relation" items="${it.relations}" varStatus="status">
						<li class="hasChildren search"><label for="${relation.relatedIid}" title="${relation.relationValue}">${relation.relatedItemTitle}</label><input type="checkbox" value="${relation.relatedIid}" /></li>
					</c:forEach>
				</ol>
			</li>
		</ol>
	</c:if>
	<c:if test="${empty(it.relations)}">
		<ul class="tree">
			<li>
				No Relations Found
			</li>
		</ul>
	</c:if>
</anu:content>

<jsp:include page="footer.jsp" />