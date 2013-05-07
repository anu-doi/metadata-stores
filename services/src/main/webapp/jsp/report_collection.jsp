<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<anu:header id="1998" title="Item Report" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/relations.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="header.jsp" />

<anu:content layout="doublewide">
	<h1>Get collections report</h1>
	<jsp:useBean id="now" class="java.util.Date" />
	<form name="reportForm" class="anuform" method="POST" action="<c:url value='/rest/report' />">
		<label for="timePeriod">Created/Updated Time Period</label>
		<select id="timePeriod" name="timePeriod">
			<option value="1 day">1 day</option>
			<option value="1 week">1 week</option>
			<option value="2 weeks">2 weeks</option>
			<option value="1 month" selected="selected">1 month</option>
		</select>
		<br/>
		<label for="endDate">End Date (yyyy-mm-dd)</label>
		<input type="text" id="endDate" name="endDate" value="<fmt:formatDate value="${now}" type="both" pattern="yyyy-MM-dd" />" />
		<br/>
		<input type="submit" class="right" value="Get Report" />
	</form>
</anu:content>

<jsp:include page="footer.jsp" />