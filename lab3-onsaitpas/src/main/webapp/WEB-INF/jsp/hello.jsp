<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
<h1><fmt:message key="heading" /></h1>
<p><fmt:message key="greeting" /> <c:out value="${model.now}" /></p>
<h3><fmt:message key="products" /></h3>
<c:forEach items="${model.products}" var="prod">
	<c:out value="${prod.description}" />
	<i>$<c:out value="${prod.price}" /></i>
	<br>
</c:forEach>
<br>
<a href="<c:url value="priceincrease.htm"/>"><fmt:message key="priceincrease.link" /></a>
<br>

<h3><fmt:message key="infractions" /></h3>
<c:forEach items="${model.infractions}" var="inf">
	<c:out value="${inf.description}" />
	<i>$<c:out value="${inf.niveau}" /></i>
	<br>
</c:forEach>
<br>
</body>
</html>
