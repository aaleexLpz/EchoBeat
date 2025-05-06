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