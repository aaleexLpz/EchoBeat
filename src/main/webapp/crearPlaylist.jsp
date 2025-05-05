<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="echoBeat.css">
    <title>Echo-Beat</title>
</head>
<body>
    <header class="cabeceraPrincipal">
        <h1>Echo-Beat</h1>
    </header>
    <main class="contenedorPrincipal">
    	<header>
    		<h2>Crear Lista</h2>
    	</header>
        <!-- Se cambia el tipo MIME del formulario para poder subir el fichero a enctype="multipart/form-data" -->
        <form action="CrearPlaylist" method="post" class="contenedorFormulario" enctype="multipart/form-data">
            <label for="txtNombre">Nombre:</label>
            <input type="text" name="nombre" id="txtNombre" required>
            <label for="filePortada">Portada:</label>
            <input type="file" name="portada" id="filePortada" required>
            <div>
                <input type="checkbox" name="publica" id="chkPublica">
                <label for="chkPublica">Compartir con todos los usuarios</label>
            </div>
            <input type="submit" value="Crear lista de reproducción">
        </form>
    </main>
</body>
</html>