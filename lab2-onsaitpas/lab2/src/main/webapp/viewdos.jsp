<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.*" %>

<% int iddos = 0;
	if (request.getParameter("selectedDos") != null) {
		iddos = Integer.parseInt(request.getParameter("selectedDos"));
	}
%>
<sql:query var="rs_dosinf" dataSource="jdbc/lab2">
	select id_dossier, id_infraction, date, niveau from dos_infraction inner join infraction on dos_infraction.id_infraction=infraction.id where id_dossier=?
	<sql:param value="<%= iddos %>" />
</sql:query>
<sql:query var="rs_maxinf" dataSource="jdbc/lab2">
	select max(niveau) as mniveau from dos_infraction inner join infraction on dos_infraction.id_infraction=infraction.id where id_dossier=?
	<sql:param value="<%= iddos %>" />
</sql:query>
<sql:query var="rs_curdos" dataSource="jdbc/lab2">
	select id, nom, prenom, nopermis, noplaque from dossier where id=?
	<sql:param value="<%= iddos %>" />
</sql:query>



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
	
	
	
	<script>
	
	$(function() {
		

	$( document ).ready(function() {
		
	}
	});	
	
	 
	</script>
</head>

<body>
	<div id="main" style="width:1300px; text-align:center; margin: 0 auto;">
		<div id="leftpanel" style="width:30%; min-height:500px; float:left; background-color:#DDD8EB;">
			<h2>Dossier: </h2>
			<div id="dossierInfo">
				<c:forEach var="row" items="${rs_curdos.rows}">
				Prenom: <label id="prenomLabel"> ${row.prenom} </label><br/>
				Nom: <label id="nomLabel"> ${row.nom} </label><br/>
				Permis: <label id="permisLabel"> ${row.nopermis} </label><br/>
				Plaque: <label id="plaqueLabel"> ${row.noplaque} </label><br/>
				</c:forEach>
				<br/>
				<h3>Niveau de gravite: 
				<c:forEach var="row3" items="${rs_maxinf.rows}">
					<label id="graviteLabel"> ${row3.mniveau} </label><br/>
				</c:forEach>
				</h3>
			</div>
			
		</div>
		
		<div id="middlepanel" style="width:45%;min-height:500px; float:left; background-color:#ADDEC5;">
			<h2>Infractions</h2>
			
			<table class="infractionsTable" border="1">
				<tr>
					<th>Infractions au dossier</th>
					<th>Niveau de gravite</th>
				</tr>
				<c:forEach var="row2" items="${rs_dosinf.rows}">
					<tr>
					<td>
						Infraction ${row2.id_infraction}<br />
						Date ${row2.date}</td>
					<td>
						Niveau ${row2.niveau}
					</td>
				</c:forEach>
			</table>
		</div>
		
		<div id="rightpanel" style="width:15%; min-height:500px; float:left; background-color:#BAF7A3;">
			<div id="buttons" style="margin-top:20px;">
				<form name="returnMain" action="MainView.jsp" method="get">
				<br/>
					<input type="submit" value="Return to main menu">
				</form>
			</div>
		</div>
	</div>
</body>

</html>
