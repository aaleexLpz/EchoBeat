document.querySelectorAll("audio").forEach(
    cancion => cancion.addEventListener("play", function() {
       comprobar(this)}) 
);

function comprobar(cancion){
    let canciones = document.querySelectorAll("audio");
    for(let c of canciones){
        if(c != cancion){
            if(!c.paused){
                c.pause();
            }
        }
    }
}

var cancionSeleccionada;
function verPlayList(cancion){
    cancionSeleccionada = cancion;
    let divPlayList = document.querySelector(".divPlayList").style.display = "block";
}

function addCancion(){
    let listasSeleccionadas = document.querySelectorAll(".listaPlayList [type=checkbox]:checked");
    console.log(listasSeleccionadas);

    let xhr = new XMLHttpRequest();
    xhr.open("post", "addCancionPlayList", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = () => {
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                alert("Canción agregada correctamente");
            }else{
                alert("No se ha podido agregar. ERROR: " + xhr.status);
            }
        }            
    };
    let datos = `cancion=${cancionSeleccionada}`;
    for(let checkbox of listasSeleccionadas){
        datos = datos + `&lista=${checkbox.value}`;
    }
    console.log(datos);
    xhr.send(datos);
    
}

function verLetra(titulo, autor){
    let url = `http://localhost:8080/LetrasCancionesWS/api/letra/${titulo}/${autor}`;

    fetch(url)
    .then(respuesta => {
        respuesta.json()
        .then((datos) => {
            htmlLetra = `
                <div id="contenedorLetra">
                    <p>${datos.lyrics}</p>
                </div>
            `;
            document.getElementById("letra").innerHTML = htmlLetra;
        })
        .catch(error => console.error(error));
    })
    .catch(error => console.error(error));
}