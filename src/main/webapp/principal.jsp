<%@page import="com.echobeat.model.PlayList"%>
<%@page import="com.echobeat.model.Cancion"%>
<%@page import="java.util.List"%>
<%@page import="com.echobeat.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>EchoBeat</title>
		<link rel="shortcut icon" href="./images/favicon-EchoBeat-BlankaFont.png" type="image/x-icon">
		<link rel="stylesheet" href="echoBeat-IndexStyles.css">
		<link rel="stylesheet" href="echoBeat-PrincipalStyles.css">
	</head>
	<%@ include file="fragmentoError.jsp" %>
		<header>
			<h1>Echo-Beat</h1>
		</header>
		<main>
			<% Usuario usuario = (Usuario)session.getAttribute("usuario"); %>
			<% if (usuario != null) { %>
				<h2>Bienvenido <%=usuario.getNombre()%></h2>
			<% } else { %>
				<% response.sendRedirect("index.jsp"); %>
			<% } %>
			<a href="subirCancion.jsp">Subir canción</a>
			<a href="crearPlaylist.jsp">Nueva lista de reproducción</a>
			<a href="CerrarSesion">Cerrar sesión</a>
			<form action="BuscarCancion" method="get">
				<label for="txtBusqueda">Título:</label>
				<input type="search" name="titulo" id="txtBusqueda">
				<input type="submit" value="Buscar" id="botonBuscar">
			</form>
			<% List<Cancion> listaCanciones = (List<Cancion>)request.getAttribute("listaCanciones"); %>
			<% if (listaCanciones != null) { %>
				<% if (listaCanciones.isEmpty()) { %>
					<p>No se han encontrado coincidencias</p>
				<% } else { %>
					<% for (Cancion cancion : listaCanciones) { %>
						<div>
							<span class="titulo"><%=cancion.getTitulo()%></span>
							<audio controls>
								<source src="/CANCIONES/<%=cancion.getRutaCancion()%>">
							</audio>
							<button type="button" onclick="verPlayList(<%=cancion.getIdCancion()%>)">🙀</button>
							<button type="button" onclick="verLetra('<%=cancion.getTitulo()%>', 'Chayanne')">Ver letra</button>
						</div>
					<% } %>
				<% } %>
			<% } %>
		</main>
		<div class="divPlayList">
			<h2>PlayLists</h2>
			<% List<PlayList> listaPlayList = (List<PlayList>)session.getAttribute("playLists"); %>
			<% if(listaPlayList.size() == 0) { %>
				<div>No tienes ninguna lista de reproducción</div>
			<% } else { %>
				<ul class="listaPlayList">
				<% for(PlayList pl : listaPlayList) { %>
					<li>
						<img src="/PORTADAS/<%=pl.getPortada()%>" alt="portada lista reproducción" class="imagenPortada"><%=pl.getNombre()%>
						<input type="checkbox" value="<%=pl.getIdPlayList()%>">
					</li>
				<% } %>
				</ul>
				<input type="button" value="Añadir" onclick="addCancion()" id="botonAddCancion">
			<% } %>
		</div>
		<div id="letra">

		</div>
		<script src="principal.js"></script>
	</body>
</html>