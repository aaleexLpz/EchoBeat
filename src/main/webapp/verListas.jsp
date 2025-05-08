<%@page import="com.echobeat.model.PlayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Insert title here</title>
    </head>

    <body>
		<div class="mias">
			<% List<PlayList> misListas = (List<PlayList>)session.getAttribute("playLists"); %>
			<% if(misListas.isEmpty()) {%>
				<span>No tienes ninguna lista de reproducción</span>
			<% } else {%>
				<h2>Mis Listas</h2>
				<ul>
				<% for(PlayList lista : misListas) {%>
					<li>
						<%=lista.getNombre()%>
					</li>
				<% } %>
				</ul>
			<% } %>
		</div>
		<div class="sigo">
			<% List<PlayList> listasSeguidas = (List<PlayList>)request.getAttribute("listaPlayList"); %>
			<% if(listasSeguidas.isEmpty()) {%>
				<span>No sigues a ninguna lista de reproducción</span>
			<% } else {%>
				<h2>Listas que sigues</h2>
				<ul>
				<% for(PlayList lista : listasSeguidas) {%>
					<li>
						<%=lista.getNombre()%>
					</li>
				<% } %>
				</ul>
			<% } %>
		</div>
    </body>

    </html>