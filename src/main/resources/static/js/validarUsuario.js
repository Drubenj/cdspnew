const formulario = document.getElementById('formularioUsuario')
const inputs = document.querySelectorAll('#formularioUsuario input')

const expresiones = {
	direccion: /^.[a-zA-Z0-9_.\s\_\-]{5,30}$/, // Letras, numeros, guion y guion_bajo
	nombres: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
	apellidos: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
	contrasenia: /^.{8,12}$/, // 4 a 12 digitos.
	correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	telefono: /^\d{7,10}$/, // 7 a 1o numeros.
	idUsuario: /^\d{8,10}$/ // 8 a 1o numeros.
}
const validarFormulario = (e) => {
switch(e.target.name){
	case "nombres":
		validarCampo(expresiones.nombres, e.target, 'nombres');
	break;
	case "apellidos":
		validarCampo(expresiones.apellidos, e.target, 'apellidos');
	break;
	case "idUsuario":
		validarCampo(expresiones.idUsuario, e.target, 'idUsuario');
	case "correo":
		validarCampo(expresiones.correo, e.target, 'correo');
	break;
	case "telefono":
		validarCampo(expresiones.telefono, e.target, 'telefono');
	break;
	case "direccion":
		validarCampo(expresiones.direccion, e.target, 'direccion');
	break;
	case "contrasenia":
		validarCampo(expresiones.contrasenia, e.target, 'contrasenia');
		validarPassword2()
	break;
	case "contrasenia2":
		validarPassword2()

}
}

const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
	}
}

const validarPassword2 = () => {
	const inputPassword1 = document.getElementById('contrasenia');
	const inputPassword2 = document.getElementById('contrasenia2');

	if(inputPassword1.value !== inputPassword2.value){
		document.getElementById(`grupo__password2`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__password2`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__password2 i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__password2 i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__password2 .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos['contrasenia'] = false;
	} else {
		document.getElementById(`grupo__password2`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__password2`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__password2 i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__password2 i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__password2 .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos['contrasenia'] = true;
	}
}

inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});
