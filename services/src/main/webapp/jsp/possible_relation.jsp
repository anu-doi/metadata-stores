<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>

<anu:header id="1998" title="Item Relations - ${it.item.title}" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/relations.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="header.jsp" />

<anu:content layout="doublewide">
	<h1>Potential Relations</h1>
	<form method="post" action="">
		<table border="1">
			<c:forEach var="relation" items="${it.relations}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><input type="radio" name="confirm[${status.count - 1}]" value="yes" />Yes <input type="radio" name="confirm[${status.count - 1}]" value="no" />No</td>
					<td><input type="hidden" name="iid" value="${relation.iid}" />${relation.itemTitle}</td>
					<td><input type="hidden" name="relationValue" value="${relation.relationValue}" />${relation.relationValue}</td>
					<td><input type="hidden" name="relatedIid" value="${relation.relatedIid}" />${relation.relatedItemTitle}</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Update" />
	</form>
</anu:content>

<jsp:include page="footer.jsp" />