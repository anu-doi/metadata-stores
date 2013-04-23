<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<body>
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
	</body>
</html>