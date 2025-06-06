<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign in | EchoBeat</title>
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
        <form action="registrarUsuario" method="post" id="formulario2">
            <label for="username">Nombre de usuario</label>
            <input type="text" name="username" id="username" required>
            <% String usuarioRepetido = (String)request.getAttribute("usuarioRepetido"); %>
            <% if(usuarioRepetido != null) { %>
            	<span><%=usuarioRepetido%></span>
            <% } %>
            <label for="contrasenha">Contraseña</label>
            <input type="password" name="password" id="contrasenha" required minlength="8">
            <span id="verificadorContrasenha"></span>
            <label for="contrasenha2">Repetir contraseña</label>
            <input type="password" name="password2" id="contrasenha2" required minlength="8">
            <label for="nombrePila">Nombre</label>
            <input type="text" name="nombrePila" id="nombrePila" required>
            <label for="correo">Email</label>
            <input type="email" name="email" id="correo" required>
            <label for="fechaNac">Fecha de nacimiento</label>
            <input type="date" name="fechaNac" id="fechaNac" required>
            <input type="submit" value="Sign In" id="boton">
        </form>
    </div>

    <script src="signIn.js"></script>
</body>
</html>