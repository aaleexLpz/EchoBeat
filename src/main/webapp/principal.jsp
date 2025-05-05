<%@page import="com.echobeat.model.Cancion"%>
<%@page import="java.util.List"%>
<%@page import="com.echobeat.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<title>Echo-Beat</title>
	</head>
	<body>
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
				<input type="submit" value="Buscar">
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
						</div>
					<% } %>
				<% } %>
			<% } %>
		</main>
	</body>
</html>