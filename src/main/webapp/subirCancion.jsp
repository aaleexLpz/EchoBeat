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
                <option value="ROCK">ROCK</option>
                <option value="POP">POPO</option>
                <option value="CLASICA">CLÁSICA</option>
                <option value="FOLK">FOLK</option>
                <option value="BSO">BSO</option>
                <option value="RAP">RAP</option>
                <option value="REGGAETON">REGGAETON</option>
                <option value="METAL">METAL</option>
                <option value="INDIE">INDIE</option>
                <option value="JAZZ">JAZZ</option>
                <option value="ELECTRONICA">ELECTRÓNICA</option>
                <option value="COUNTRY">COUNTRY</option>
                <option value="SALSA">SALSA</option>
                <option value="BLUES">BLUES</option>
                <option value="PUNK">PUNK</option>
                <option value="CUMBIA">CUMBIA</option>
                <option value="BACHATA">BACHATA</option>
                <option value="TANGO">TANGO</option>
                <option value="MERENGUE">MERENGUE</option>
                <option value="HOUSE">HOUSE</option>
                <option value="TECHNO">TECHNO</option>
                <option value="REGGAE">REGGAE</option>
                <option value="TRAP">TRAP</option>
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