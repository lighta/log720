<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div align="center">
        	<h1>Infractions List</h1>
        	<table border="1">
	        	<th>No</th>
	        	<th>Description</th>
	        	<th>Niveau</th>
				<c:forEach var="infractions" items="${infractions}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${infractions.description}</td>
					<td>${infractions.niveau}</td>
	        	</tr>
				</c:forEach>
        	</table>
        	<h1>Dossiers List</h1>
        	<table border="1">
	        	<th>No</th>
	        	<th>Nom</th>
	        	<th>Prenom</th>
	        	<th>NoPermis</th>
	        	<th>NoPlaque</th>
	        	<th>Niveau</th>
				<c:forEach var="dossiers" items="${dossiers}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${dossiers.nom}</td>
					<td>${dossiers.prenom}</td>
					<td>${dossiers.nopermis}</td>
					<td>${dossiers.noplaque}</td>
					<td>${dossiers.niveau}</td>
	        	</tr>
				</c:forEach>
        	</table>
        	<h1>Dossiers Infractions List</h1>
        	<table border="1">
	        	<th>ID</th>
	        	<th>dossierID</th>
	        	<th>infractionID</th>
	        	<th>date</th>
				<c:forEach var="dosInfs" items="${dosInfs}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${dosInfs.id.iddossier}</td>
					<td>${dosInfs.id.idinfraction}</td>
					<td>${dosInfs.id.date}</td>
	        	</tr>
				</c:forEach>
        	</table>
        </div>
    </body>
</html>
