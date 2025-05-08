package com.echobeat.controller;

import java.io.IOException;

import com.echobeat.model.facade.EchoBeatFacade;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCancionPlayListServlet
 */
@WebServlet("/addCancionPlayList")
public class AddCancionPlayListServlet extends HttpServlet {
	private static final long serialVersionUID = -8765720578225943899L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddCancionPlayListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cancionStr = request.getParameter("cancion");
		String[] listasStr = request.getParameterValues("lista");
		int idCancion = Integer.parseInt(cancionStr);
		int[] listas = new int[listasStr.length];
		for(int pos = 0; pos < listasStr.length; pos++) {
			listas[pos] = Integer.parseInt(listasStr[pos]);
		}
		EchoBeatFacade facade = new EchoBeatFacade();
		facade.meterCancionEnPlayLists(idCancion, listas);
	}

}
