package com.echobeat.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.echobeat.model.Usuario;
import com.echobeat.model.facade.EchoBeatFacade;

/**
 * Servlet implementation class IniciarSesionServlet
 */
@WebServlet("/iniciarSesion")
public class IniciarSesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("Username: " + username + "\nPassword: " + password);
		EchoBeatFacade facade = new EchoBeatFacade();
		Usuario usuario = facade.iniciarSesion(username, password);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		if(usuario != null) {
			salida.println("<!DOCTYPE html>");
			salida.println("<html lang = 'es'>");
			salida.println("<body>");
			salida.println("<h1>Bienvenido " + usuario.getNombreUsuario() + "</h1>");
			salida.println("</body>");
			salida.println("</html>");
		} else {
			salida.println("<!DOCTYPE html>");
			salida.println("<html lang = 'es'>");
			salida.println("<body>");
			salida.println("<h1>Lo siento, los datos introducidos no son correctos.</h1>");
			salida.println("</body>");
			salida.println("</html>");
		}
	}

}
