<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="anu" uri="http://www.anu.edu.au/taglib"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<anu:header id="1998" title="${it.epress.title}" description="description" subject="subject" respOfficer="Doug Moncur" respOfficerContact="mailto:doug.moncur@anu.edu.au"
	ssl="true">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/default.css' />" />
	<script type="text/javascript" src="<c:url value='/js/relations.js' />"></script>
</anu:header>

<jsp:include page="/jsp/header.jsp" />

<anu:content layout="doublewide">
	<h1>${it.epress.title}</h1>
	<div>
		<span class="title text-uni">Author(s)</span> ${it.epress.authors}
	</div>
	<div>
		<span class="title text-uni">Series</span> 
		<c:forEach items="${it.epress.series}" var="series">
			${series}
		</c:forEach>
	</div>
	<c:if test="${not empty it.epress.seriesDescriptions}">
		<div>
			<span class="title text-uni">Series Description</span> 
			<c:forEach items="${it.epress.seriesDescriptions}" var="seriesDescription">
				${seriesDescription}
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${not empty it.epress.ISBNs}">
		<div>
			<span class="title text-uni">ISBN</span> 
			<c:forEach items="${it.epress.ISBNs}" var="ISBN">
				${ISBN}
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${not empty it.epress.ISSNs}">
		<div>
			<span class="title text-uni">ISSN</span> 
			<c:forEach items="${it.epress.ISSNs}" var="ISSN">
				${ISSN}
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${not empty it.epress.citationURLs}">
		<div>
			<span class="title text-uni">Citation URL</span> 
			<c:forEach items="${it.epress.citationURLs}" var="citationURL">
				${citationURL}
			</c:forEach> 
		</div>
	</c:if>
	<c:if test="${not empty it.epress.content}">
		<div>
			<span class="title text-uni">Content</span><br/>
			<c:forEach items="${it.epress.content}" var="rawContent">
				<c:set var="content" value="${fn:replace(rawContent,'--','<br/>')}" />
				${content}
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${not empty it.epress.descriptions}">
		<div>
			<span class="title text-uni">Description</span><br/>
			<c:forEach items="${it.epress.descriptions}" var="description">
				${description}
			</c:forEach>
		</div>
	</c:if>
</anu:content>

<jsp:include page="relations.jsp" />

<jsp:include page="/jsp/footer.jsp" />