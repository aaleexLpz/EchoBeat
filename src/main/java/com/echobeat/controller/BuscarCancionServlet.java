package com.echobeat.controller;

import java.io.IOException;
import java.util.List;

import com.echobeat.model.Cancion;
import com.echobeat.model.Usuario;
import com.echobeat.model.facade.EchoBeatFacade;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuscarCancionServlet
 */
@WebServlet("/BuscarCancion")
public class BuscarCancionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		EchoBeatFacade facade = new EchoBeatFacade();
		List<Cancion> canciones = facade.buscarCancionesPorTitulo(titulo, usuario);
		request.setAttribute("listaCanciones", canciones);
		request.getRequestDispatcher("principal.jsp").forward(request, response);
	}

}
