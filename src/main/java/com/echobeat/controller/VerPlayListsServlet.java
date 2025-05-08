package com.echobeat.controller;

import java.io.IOException;
import java.util.List;

import com.echobeat.model.PlayList;
import com.echobeat.model.Usuario;
import com.echobeat.model.facade.EchoBeatFacade;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerPlayListsServlet
 */
@WebServlet("/verPlayLists")
public class VerPlayListsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerPlayListsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EchoBeatFacade facade = new EchoBeatFacade();
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		List<PlayList> lista = facade.buscarPlayListPorSeguidor(usuario);
		request.setAttribute("listaPlayList", lista);
		request.getRequestDispatcher("verListas.jsp").forward(request, response);
	}

}
