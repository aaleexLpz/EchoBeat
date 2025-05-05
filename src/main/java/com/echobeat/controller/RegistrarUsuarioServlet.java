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
		boolean registrado = facade.registrarUsuario(usuario);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		
		if(registrado) {
			salida.println("<!DOCTYPE html>");
			salida.println("<html lang = 'es'>");
			salida.println("<body>");
			salida.println("<h1>Usuario " + usuario.getNombreUsuario() + " registrado correctamente </h1>");
			salida.println("</body>");
			salida.println("</html>");
		} else {
			salida.println("<!DOCTYPE html>");
			salida.println("<html lang = 'es'>");
			salida.println("<body>");
			salida.println("<h1>El nombre de usuario ya existe</h1>");
			salida.println("</body>");
			salida.println("</html>");
		}
		
	}
	}

}
