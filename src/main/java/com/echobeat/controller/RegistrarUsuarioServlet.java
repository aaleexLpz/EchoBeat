package com.echobeat.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import com.echobeat.model.TipoUsuario;
import com.echobeat.model.Usuario;
import com.echobeat.model.exception.ErrorInternoException;
import com.echobeat.model.exception.NombreUsuarioRepetidoException;
import com.echobeat.model.exception.SinConexionException;
import com.echobeat.model.facade.EchoBeatFacade;

/**
 * Servlet implementation class RegistrarUsuarioServlet
 */
@WebServlet("/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String nombrePila = request.getParameter("nombrePila");
		String email = request.getParameter("email");
		String fechaNac = request.getParameter("fechaNac");
		
		if(username != null && !username.equals("") && password != null && !password.equals("") && password2 != null && !password2.equals("") && nombrePila != null && !nombrePila.equals("") && email != null && !email.equals("") && fechaNac != null && !fechaNac.equals("")) {
		LocalDate fechaNacimiento = LocalDate.parse(fechaNac);
		
		Usuario usuario = new Usuario(username, password, TipoUsuario.CLIENTE, false, nombrePila, email, fechaNacimiento);
		
		EchoBeatFacade facade = new EchoBeatFacade();
		
		try {
			boolean registrado = facade.registrarUsuario(usuario);
			
			if(registrado) {
				request.getRequestDispatcher("iniciarSesion").forward(request, response);
			}
			
		} catch (NombreUsuarioRepetidoException e) {
			e.printStackTrace();
			request.setAttribute("usuarioRepetido", "El nombre de usuario ya existe");
			request.getRequestDispatcher("signIn.jsp").forward(request, response);
		} catch (ErrorInternoException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error interno, contacte con soporte");
			request.getRequestDispatcher("signIn.jsp").forward(request, response);
		} catch (SinConexionException e) {
			e.printStackTrace();
			request.setAttribute("error", "No se ha podido conectar con el servidor de datos");
			request.getRequestDispatcher("signIn.jsp").forward(request, response);
		}
	}
	}

}
