package Objetos;

import DAO.DAOUsuario;
import Excepciones.ExcepcionCampos;
import Excepciones.ExcepcionPassword;
import Excepciones.ExcepcionUsername;

public class Check {

	public static void check(String password1, String password2, String username, String nombre, String apellidos,
			String email)throws ExcepcionPassword,ExcepcionUsername, ExcepcionCampos {
		validarCamposVacios(nombre, apellidos, username, email);
		validarUsername(username);
		validarContraseña(password1, password2);
		
	}

	private static void validarContraseña(String password1, String password2) throws ExcepcionPassword {
		if (!password1.equals(password2)) {
			throw new ExcepcionPassword("¡Las contraseñas no coinciden!");
		}
		if (!validarContraseniaMayusMinus(password1) || !validarContraseniaNumeros(password1)
				|| password1.length() < 8) {
			throw new ExcepcionPassword("¡La contraseña no cumple con los requisitos mínimos!");
		}
		
	}

	private static boolean validarContraseniaMayusMinus(String contraseña) {
		boolean mayus = false;
		boolean minus = false;
		char[] pass = contraseña.toCharArray();
		for (int i = 0; i < pass.length; i++) {
			if (!mayus && Character.isUpperCase(pass[i])) {
				mayus = true;
			} else if (!minus && Character.isLowerCase(pass[i])) {
				minus = true;
			}
			if (minus && mayus)
				break;
		}

		return mayus && minus;
	}

	private static boolean validarContraseniaNumeros(String contraseña) {
		boolean num = false;
		char[] pass = contraseña.toCharArray();
		for (int i = 0; i < pass.length; i++) {
			if (Character.isDigit(pass[i])) {
				num = true;
				break;
			}
		}
		return num;
	}

	private static void validarUsername(String username) throws ExcepcionUsername {
		if (username.length() > 15) {
			throw new ExcepcionUsername("¡El nombre de usuario sobrepasa el máximo de caracteres permitidos!");
		}

		if (DAOUsuario.comprobarUsername(username)) {
			throw new ExcepcionUsername("¡Ese nombre de usuario ya existe!");
		}
		
	}

	private static void validarCamposVacios(String nombre, String apellidos, String username, String email) throws ExcepcionCampos {
		if (nombre.isEmpty() || apellidos.isEmpty() || username.isEmpty() || email.isEmpty()) {
			throw new ExcepcionCampos("¡Debes rellenar todos los campos!");
		}
		if (username.contains(" ") || email.contains(" ")) {
			throw new ExcepcionCampos("¡No se admiten espacios en el nombre de ususario o en el email!");
		}
		if (!verificarEsapcios(nombre) || !verificarEsapcios(apellidos) || !verificarEsapcios(email)) {
			throw new ExcepcionCampos("¡No se admiten espacios al principio y al final del nombre y del apellido!");
		}
		if(!email.contains("@")) {
			throw new ExcepcionCampos("¡El email está escrito incorrectamente!");
		}
		
	}

	private static boolean verificarEsapcios(String nombre) {
		char[] aux = nombre.toCharArray();
		boolean resultado = true;
		if (Character.isSpaceChar(aux[0]))
			resultado = false;
		if (Character.isSpaceChar(aux[aux.length - 1]))
			resultado = false;
		return resultado;
	}

}
