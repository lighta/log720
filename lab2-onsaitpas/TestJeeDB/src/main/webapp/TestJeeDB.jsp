<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<sql:query var="rs" dataSource="jdbc/TestJeeDB">
	select id, foo, bar from testdata
</sql:query>
<html>
<head>
	<title>Test JEE with DB</title>
</head>
<body>
	<h2>Results</h2>
	<c:forEach var="row" items="${rs.rows}">
	Foo ${row.foo}<br />
	Bar ${row.bar}<br />
	</c:forEach>
</body>
</html>
