<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in | EchoBeat</title>
    <link rel="shortcut icon" href="./images/favicon-EchoBeat-BlankaFont.png" type="image/x-icon">
    <link rel="stylesheet" href="echoBeat-IndexStyles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Unbounded:wght@200..900&display=swap" rel="stylesheet">
</head>
<%@ include file="fragmentoError.jsp" %>
    <div id="contenedorPrincipal">
        <div id="contenedorTitulo">
            <h1><a href="index.html">EchoBeat</a></h1>
            <h5>Where every Beat leaves an Echo</h5>
        </div>
        <% String nombreUsuario = request.getParameter("nombreUsuario"); %>
        <form action="iniciarSesion" method="post" id="formulario">
            <label for="username">Nombre de usuario</label>
            <input type="text" name="username" id="username" value="<%=nombreUsuario != null ? nombreUsuario :   ""%>">
            <label for="contrasenha">Contraseña</label>
            <input type="password" name="password" id="
            contrasenha">
            <input type="submit" value="Log In" id="boton">
            <span id="nuevaCuenta">No tienes una cuenta? <a href="signIn.jsp">Crea una!</a></span>
        </form>
    </div>
</body>
</html>