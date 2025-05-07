package com.echobeat.controller;

import java.io.IOException;
import java.util.List;

import com.echobeat.model.PlayList;
import com.echobeat.model.Usuario;
import com.echobeat.model.exception.ErrorInternoException;
import com.echobeat.model.exception.SinConexionException;
import com.echobeat.model.facade.EchoBeatFacade;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		try {
			Usuario usuario = facade.iniciarSesion(username, password);
			if(usuario != null) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", usuario);
				// Buscar playlists del usuario y las guardo en la memoria caché
				List<PlayList> lista = facade.buscarPlayListPorUsuario(usuario);
				sesion.setAttribute("playLists", lista);
				request.getRequestDispatcher("principal.jsp").forward(request, response);
			} else {
				request.setAttribute("errorAutenticación", "Los datos introducidos son incorrectos");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (SinConexionException e) {
			e.printStackTrace();
			request.setAttribute("error", "No se ha podido conectar con el servidor de datos");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ErrorInternoException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error interno, contacte con soporte");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
