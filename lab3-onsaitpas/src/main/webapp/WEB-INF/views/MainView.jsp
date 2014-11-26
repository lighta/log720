<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
  if (request.getParameter("logoff") != null) {
    session.invalidate();
    response.sendRedirect("/lab3/");
    return;
  }
%>

<%
	//Our ugly DoStuff without servlet
	String user;
	String description;
	int gravite=0;
	
	String nom, prenom;
	String permis, plaque;
	
	
	int id_infraction=0,id_dossier=0;
	 
	if(request.isUserInRole("log720_Admin")){ //user must be an admin to do those
		out.print("User is a admin");
		//Add infractions
		if (request.getParameter("gravite") != null) {
			gravite = Integer.parseInt(request.getParameter("gravite"));
		}
		description=request.getParameter("infraction");
		if(gravite != 0 
			&& description != null && !description.isEmpty())  {
			pageContext.setAttribute("gravite", gravite);
			pageContext.setAttribute("description", description);
		}
		
		//Add dossier
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		permis = request.getParameter("permis");
		plaque = request.getParameter("plaque");
		if(nom != null && !nom.isEmpty()
		 && prenom != null && !prenom.isEmpty()
		 && permis != null && !permis.isEmpty()
		 && plaque != null && !plaque.isEmpty()) 
		{
			pageContext.setAttribute("nom", nom);
			pageContext.setAttribute("prenom", prenom);
			pageContext.setAttribute("permis", permis);
			pageContext.setAttribute("plaque", plaque);
		}
	}
	else if(request.isUserInRole("log720_Policier")) { //user must be an policeman to do those
		out.print("User is a policeman");
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
	}
	else {
		out.print("You shouldn't be able to be here !!!!");
	}	
%>

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
	<%
			String desc = "Proxenetisme";
			int niveau=0;
	%>
	
	You are logged in as remote user
	<b><%= ca.etsmtl.log720.lab3.utils.HTMLFilter.filter(request.getRemoteUser()) %></b>
	in session <b><%= session.getId() %></b><br><br>
	You can log off by clicking
	<a href='<%= response.encodeURL("MainView.jsp?logoff=true") %>'><b>here</b></a>.<br>
	This should cause you to be returned to the logon page after the redirect
	that is performed.

	
	<div id="infractionDialog" title="Add infraction">
		<form id="formAddInfraction" action="" method="get">
			<label for="infraction">Infraction: </label>
			<input type="text" name="infraction" id="infraction" value="<%= ca.etsmtl.log720.lab3.utils.HTMLFilter.filter(desc) %>" class="text ui-widget-content ui-corner-all">
			<br/><br/>
			<label for="gravite">Gravite: </label>
			<input type="number" name="gravite" id="gravite" value="<%= ca.etsmtl.log720.lab3.utils.HTMLFilter.chk_gravite(niveau) %>" class="number ui-widget-content ui-corner-all">
		</form>
	</div>

	<div id="dossierDialog" title="Add dossier">
		 <form id="formAddDossier" action="" method="get">
			<%
				nom = "Doe";
				prenom="John";
		 		permis = "Doej1234";
		 		plaque="a1b2c3";
			%>
			<label for="prenom">Prenom: </label>
			<input type="text" name="prenom" id="prenom" value="<%= ca.etsmtl.log720.lab3.utils.HTMLFilter.filter(prenom) %>" class="text ui-widget-content ui-corner-all">
			<br/><br/>
			<label for="nom">Nom: </label>
			<input type="text" name="nom" id="nom" value="<%= ca.etsmtl.log720.lab3.utils.HTMLFilter.filter(nom) %>" class="text ui-widget-content ui-corner-all">
			<br/><br/>
			<label for="permis">Permis: </label>
			<input type="text" name="permis" id="permis" value="<%= ca.etsmtl.log720.lab3.utils.HTMLFilter.filter(permis) %>" class="text ui-widget-content ui-corner-all">
			<br/><br/>
			<label for="nom">Plaque: </label>
			<input type="text" name="plaque" id="plaque" value="<%= ca.etsmtl.log720.lab3.utils.HTMLFilter.filter(plaque) %>" class="text ui-widget-content ui-corner-all">
		</form>
	</div>


	<div id="main" style="width:1300px; text-align:center; margin: 0 auto;">
		<div id="leftpanel" style="width:30%; min-height:500px; float:left; background-color:#DDD8EB;">
			<h2>Infractions</h2>
			<form name="addInfraction" action="" method="get">
				<% 
				if(request.isUserInRole("log720_Admin")){ %>
					<input type="submit" id="addInfractionBtn" value="Add Infraction">
				<%
				}
				%>
			</form>
			
			<table class="infractionsTable" border="1">
	        	<th>ID</th>
	        	<th>Description</th>
	        	<th>Niveau</th>
				<c:forEach var="infractions" items="${infractions}" varStatus="status">
	        	<tr>
					<td>${infractions.id}</td>
					<td>${infractions.description}</td>
					<td>${infractions.niveau}</td>
	        	</tr>
				</c:forEach>
        	</table>
		</div>
		
		<div id="middlepanel" style="width:30%;min-height:500px; float:left; background-color:#ADDEC5;">
			<h2>Dossier</h2>
			<form name="addDossier" action="" method="get">
					<% 
					if(request.isUserInRole("log720_Admin")){ %>
						<input type="submit" id="addDossierBtn" value="Add Dossier">
					<% 
					}
					%>
			</form>
			
			<table class="dossiersTable" border="1">
	        	<th>ID</th>
	        	<th>Nom</th>
	        	<th>Prenom</th>
	        	<th>NoPermis</th>
	        	<th>NoPlaque</th>
	        	<th>Niveau</th>
				<c:forEach var="dossiers" items="${dossiers}" varStatus="status">
	        	<tr>
					<td>${dossiers.id}</td>
					<td>${dossiers.nom}</td>
					<td>${dossiers.prenom}</td>
					<td>${dossiers.nopermis}</td>
					<td>${dossiers.noplaque}</td>
					<td>${dossiers.niveau}</td>
	        	</tr>
				</c:forEach>
        	</table>
		</div>
		
		<div id="rightpanel" style="width:30%; min-height:500px; float:left; background-color:#BAF7A3;">
			<div id="buttons" style="margin-top:20px;">
				<form name="add" action="" method="get">
							<br/> Selected Dossier: <input type="text" name="selectedDos" value="" readonly><br/>
					<% 
						if(request.isUserInRole("log720_Policier")){ 
					%>
							<br/> Selected Infraction: <input type="text" name="selectedInf" value="" readonly><br/><br/>
							<input type="submit" value="Add Infraction to Dossier" id="addInfractionToDossier" disabled="true"><br/>
					<%
						}
					%>
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
