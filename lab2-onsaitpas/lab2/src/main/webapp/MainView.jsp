<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
  if (request.getParameter("logoff") != null) {
    session.invalidate();
    response.sendRedirect("MainView.jsp");
    return;
  }
%>

<%
	//Our ugly DoStuff without servlet
	String description;
	int gravite=0;
	
	String nom, prenom;
	String permis, plaque;
	
	int id_infraction=0,id_dossier=0;
	 
	//Add infractions
	if (request.getParameter("gravite") != null) {
		gravite = Integer.parseInt(request.getParameter("gravite"));
	}
	description=request.getParameter("infraction");
	if(gravite != 0 && description != null) {
		pageContext.setAttribute("gravite", gravite);
		pageContext.setAttribute("description", description);
	}
	
	
	//Add dossier
	nom = request.getParameter("nom");
	prenom = request.getParameter("prenom");
	permis = request.getParameter("permis");
	plaque = request.getParameter("plaque");
	if(nom != null && prenom != null && permis != null && plaque != null) {
		pageContext.setAttribute("nom", nom);
		pageContext.setAttribute("prenom", prenom);
		pageContext.setAttribute("permis", permis);
		pageContext.setAttribute("plaque", plaque);
	}
	
	//Add dosinfraction
	if (request.getParameter("selectedInf") != null) {
		id_infraction = Integer.parseInt(request.getParameter("selectedInf"));
	}
	if (request.getParameter("selectedDos") != null) {
		id_dossier = Integer.parseInt(request.getParameter("selectedDos"));
	}
	if(id_dossier != 0 && id_infraction != 0) {
		pageContext.setAttribute("idinf", id_infraction);
		pageContext.setAttribute("iddos", id_dossier);
	}
%>

<c:if test="${iddos > 0}">
	<sql:update var="ins_dosinf" dataSource="jdbc/lab2">
		insert into dosinfraction(iddossier, idinfraction) values (?,?);
		<sql:param value="${iddos}" />
		<sql:param value="${idinf}" />
	</sql:update>
</c:if>
<c:if test="${!empty param.gravite}">
	<sql:update var="ins_inf" dataSource="jdbc/lab2">
		insert into infraction(description, niveau) values (?,?);
		<sql:param value="${description}" />
		<sql:param value="${gravite}" />
	</sql:update>
</c:if>
<c:if test="${!empty param.plaque}">
	<sql:update var="ins_dos" dataSource="jdbc/lab2">
		insert into dossier(nom, prenom, nopermis, noplaque) values (?,?,?,?);
		<sql:param value="${nom}" />
		<sql:param value="${prenom}" />
		<sql:param value="${permis}" />
		<sql:param value="${plaque}" />
	</sql:update>
</c:if>

<sql:query var="rs_dos" dataSource="jdbc/lab2">
	select id, nom, prenom, nopermis, noplaque from dossier
</sql:query>
<sql:query var="rs_inf" dataSource="jdbc/lab2">
	select id, description, niveau from infraction
</sql:query>


<html>

<head>
	<title>WebApp - Role</title>
	
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
		$("#addInfractionBtn").click(function (event) {
			event.preventDefault();
			$( "#infractionDialog" ).show();
			$( "#infractionDialog" ).dialog({
				resizable: true,
				height:400,
				width:500,
				modal: true,
				buttons: {
				"Add": function() {
				$("#formAddInfraction").submit();
				},
				Cancel: function() {
				$( this ).dialog( "close" );
				}
				}
			});
        });
		
		$("#addDossierBtn").click(function (event) {
			event.preventDefault();
			$( "#dossierDialog" ).show();
			$( "#dossierDialog" ).dialog({
				resizable: true,
				height:400,
				width:500,
				modal: true,
				buttons: {
				"Add": function() {
				$("#formAddDossier").submit();
				},
				Cancel: function() {
				$( this ).dialog( "close" );
				}
				}
			});
        });
		
		$("#viewDossierBtn").click(function (event) {
			event.preventDefault();
			$("#selectedDossier").val( $('input[name=selectedDos]').val() );
			$("#viewDossier").submit();
        });
	});

	$( document ).ready(function() {
	
		$( "#infractionDialog" ).hide();
		$( "#dossierDialog" ).hide();
	
		$(".infractionsTable td").click(function(){
			//alert($(this).text());
			$('input[name=selectedInf]').val($(this).attr("id"));
		});
		
		$(".dossiersTable td").click(function(){
			//alert($(this).text());
			$('input[name=selectedDos]').val($(this).attr("id"));
			$("#viewDossierBtn").removeAttr("disabled");
			
			if ( $('input[name=selectedInf]').val() != "" &&  $('input[name=selectedDos]').val() != "" )
			{
				$("#addInfractionToDossier").removeAttr("disabled");
			}
		});
		
		$(".infractionsTable td").click(function(){
			if ( $('input[name=selectedInf]').val() != "" &&  $('input[name=selectedDos]').val() != "" )
			{
				$("#addInfractionToDossier").removeAttr("disabled");
			}
		});
		
	});	
	
	 
	</script>
</head>

<body>
	
	You are logged in as remote user
	<b><%= ca.etsmtl.log720.lab2.util.HTMLFilter.filter(request.getRemoteUser()) %></b>
	in session <b><%= session.getId() %></b><br><br>
	You can log off by clicking
	<a href='<%= response.encodeURL("MainView.jsp?logoff=true") %>'><b>here</b></a>.<br>
	This should cause you to be returned to the logon page after the redirect
	that is performed.

	<div id="infractionDialog" title="Add infraction">
		<form id="formAddInfraction" action="" method="get">
			<label for="infraction">Infraction: </label>
			<input type="text" name="infraction" id="infraction" value="<%= ca.etsmtl.log720.lab2.util.HTMLFilter.filter(description) %>" class="text ui-widget-content ui-corner-all">
			<br/><br/>
			<label for="gravite">Gravite: </label>
			<input type="number" name="gravite" id="gravite" value="<%= ca.etsmtl.log720.lab2.util.HTMLFilter.chk_gravite(gravite) %>" class="number ui-widget-content ui-corner-all">
		</form>
	</div>

	<div id="dossierDialog" title="Add dossier">
		 <form id="formAddDossier" action="" method="get">
			<label for="prenom">Prenom: </label>
			<input type="text" name="prenom" id="prenom" value="<%= ca.etsmtl.log720.lab2.util.HTMLFilter.filter(prenom) %>" class="text ui-widget-content ui-corner-all">
			<br/><br/>
			<label for="nom">Nom: </label>
			<input type="text" name="nom" id="nom" value="<%= ca.etsmtl.log720.lab2.util.HTMLFilter.filter(nom) %>" class="text ui-widget-content ui-corner-all">
			<br/><br/>
			<label for="permis">Permis: </label>
			<input type="text" name="permis" id="permis" value="<%= ca.etsmtl.log720.lab2.util.HTMLFilter.filter(permis) %>" class="text ui-widget-content ui-corner-all">
			<br/><br/>
			<label for="nom">Plaque: </label>
			<input type="text" name="plaque" id="plaque" value="<%= ca.etsmtl.log720.lab2.util.HTMLFilter.filter(plaque) %>" class="text ui-widget-content ui-corner-all">
		</form>
	</div>


	<div id="main" style="width:1300px; text-align:center; margin: 0 auto;">
		<div id="leftpanel" style="width:30%; min-height:500px; float:left; background-color:#DDD8EB;">
			<h2>Infractions</h2>
			<form name="addInfraction" action="" method="get">
					<input type="submit" id="addInfractionBtn" value="Add Infraction">
			</form>
			
			<table class="infractionsTable" border="1">
				<tr><th>Liste infractions</th></tr>
					<c:forEach var="row" items="${rs_inf.rows}">
					<tr><td id="${row.id}">Id ${row.id}<br />
					Description ${row.description}<br />
					Niveau ${row.niveau}</td>
				</c:forEach>
			</table>
		</div>
		
		<div id="middlepanel" style="width:30%;min-height:500px; float:left; background-color:#ADDEC5;">
			<h2>Dossier</h2>
			<form name="addDossier" action="" method="get">
					<input type="submit" id="addDossierBtn" value="Add Dossier">
			</form>
			
			<table class="dossiersTable" border="1">
				<tr><th>Liste dossiers</th></tr>
				<c:forEach var="row" items="${rs_dos.rows}">
					<tr><td id="${row.id}">Id ${row.id}<br />
					Nom ${row.nom}<br />
					Prenom ${row.prenom}<br />
					noPermis ${row.nopermis}<br />
					noPlaque ${row.noplaque}</td>
				</c:forEach>
			</table>
		</div>
		
		<div id="rightpanel" style="width:30%; min-height:500px; float:left; background-color:#BAF7A3;">
			<div id="buttons" style="margin-top:20px;">
				<form name="add" action="" method="get">
					Selected Infraction: <input type="text" name="selectedInf" value="" readonly><br/><br/>
					Selected Dossier: <input type="text" name="selectedDos" value="" readonly><br/>
					<br/>
						<input type="submit" value="Add Infraction to Dossier" id="addInfractionToDossier" disabled="true">
				</form>
				<br/>
				<br/>
				<form id="viewDossier" action="viewdos.jsp" method="get">
					<input type="submit" value="View Dossier" id="viewDossierBtn" disabled="true">
					<input type="hidden" name="selectedDos" value="">
				</form>
			</div>
		</div>
	</div>
</body>

</html>
