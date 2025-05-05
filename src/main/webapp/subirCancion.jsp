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
    		<h2>Subir Canción</h2>
    	</header>
        <!-- Se cambia el tipo MIME del formulario para poder subir el fichero a enctype="multipart/form-data" -->
        <form action="SubirCancion" method="post" class="contenedorFormulario" enctype="multipart/form-data">
            <label for="txtTitulo">Título:</label>
            <input type="text" name="titulo" id="txtTitulo" required>
            <label for="fileCancion">Canción:</label>
            <input type="file" name="cancion" id="fileCancion" required>
            <label for="txtDuracion">Duración:</label>
            <input type="number" name="duracion" id="txtDuracion">
            <select name="genero" required>
                <option value="" disabled selected>Elige un género</option>
                <option value="ROCK">Rock</option>
                <option value="POP">Pop</option>
                <option value="CLASICA">Clásica</option>
                <option value="FOLK">Folk</option>
                <option value="BSO">Banda sonora original</option>
                <option value="RAP">RAP</option>
            </select>
            <label for="txtAnho">Año:</label>
            <input type="number" name="anho" id="txtAnho">
            <div>
                <input type="checkbox" name="publica" id="chkPublica">
                <label for="chkPublica">Compartir con todos los usuarios</label>
            </div>
            <input type="submit" value="Subir Canción">
        </form>
    </main>
</body>
</html>