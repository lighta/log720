<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
	<title>WebApp - Dossier</title>
	
	 <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
	
	<style type="text/css">
	.infractionsTable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}
	.infractionsTable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}
	.infractionsTable tr {background-color:#d4e3e5;}
	.infractionsTable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}
	.infractionsTable tr:hover {background-color:#ffffff;}
	
	.dossiersTable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}
	.dossiersTable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}
	.dossiersTable tr {background-color:#d4e3e5;}
	.dossiersTable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}
	.dossiersTable tr:hover {background-color:#ffffff;}
	</style>
	
</head>

<body>
	<div id="main" style="width:1300px; text-align:center; margin: 0 auto;">
		<div id="leftpanel" style="width:30%; min-height:500px; float:left; background-color:#DDD8EB;">
			<h2>Dossier: </h2>
			<div id="dossierInfo">
				Prenom: <label id="prenomLabel"> ${dossier.prenom} </label><br/>
				Nom: <label id="nomLabel"> ${dossier.nom} </label><br/>
				Permis: <label id="permisLabel"> ${dossier.nopermis} </label><br/>
				Plaque: <label id="plaqueLabel"> ${dossier.noplaque} </label><br/>
				<br/>
				<h3>Niveau de gravite: 
					<label id="graviteLabel"> ${dossier.niveau} </label><br/>
				</h3>
			</div>
			
		</div>
		
		<div id="middlepanel" style="width:45%;min-height:500px; float:left; background-color:#ADDEC5;">
			<h1>Dossiers Infractions List</h1>
        	<table border="1">
	        	<th>Infraction ID</th>
	        	<th>Infraction Desc</th>
	        	<th>Date</th>
				<c:forEach var="dosInfs" items="${dosInfs}" varStatus="status">
	        	<tr>
					<td>${dosInfs.infraction.id}</td>
					<td>${dosInfs.infraction.description}</td>
					<td>${dosInfs.id.date}</td>
	        	</tr>
				</c:forEach>
        	</table>
		</div>
		
		<div id="rightpanel" style="width:15%; min-height:500px; float:left; background-color:#BAF7A3;">
			<div id="buttons" style="margin-top:20px;">
				<form name="returnMain" action="/lab3/" method="get">
				<br/>
					<input type="submit" value="Return to main menu">
				</form>
			</div>
		</div>
	</div>
</body>

</html>
