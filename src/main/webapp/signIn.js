document.getElementById("boton").addEventListener("click", (evento) => {
    comprobarClave(evento);
});

document.getElementById("contrasenha").addEventListener("keyup", verificarContrasenha);

function comprobarClave(evento){
    let password = document.getElementById("contrasenha");
    let password2 = document.getElementById("contrasenha2");

    if(password.value != password2.value){
        password2.setCustomValidity("Las contraseñas no coinciden");
    }else{
		password2.setCustomValidity("");
	}
}

document.getElementById("contrasenha2").addEventListener("keydown", function(){
    this.setCustomValidity("");
});

function verificarContrasenha(){
    let verificador = document.getElementById("verificadorContrasenha");
    let password = document.getElementById("contrasenha");
    let mayuscula = false;
    let minuscula = false;
    let numero = false;
    let especial = false;

    for(let i = 0; i < password.value.length; i++){
        let letra = password.value.charAt(i);
        if(letra >= 'a' && letra <= 'z'){
            minuscula = true;
        } else if(letra >= 'A' && letra <= 'Z'){
            mayuscula = true;
        } else if(letra >= 0 && letra <= 9){
            numero = true;
        } else {
            especial = true;
        }
    }

    let tipos = (minuscula ? 1 : 0) + (mayuscula ? 1 : 0) + (numero ? 1 : 0) + (especial ? 1 : 0);

    if(tipos == 1){
        verificador.innerHTML = "Seguridad BAJA";
        verificador.style.color = "red";
    }else if(tipos == 2){
        verificador.innerHTML = "Seguridad REGULAR";
        verificador.style.color = "orange";
    }else{
        if(tipos == 3 || tipos == 4){
            verificador.innerHTML = "Seguridad BUENA";
            verificador.style.color = "green";
        }
    }

    if(password.value == ""){
        verificador.innerHTML = "";
    }
}