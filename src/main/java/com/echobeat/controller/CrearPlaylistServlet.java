package com.echobeat.controller;

import java.io.File;
import java.io.IOException;

import com.echobeat.model.PlayList;
import com.echobeat.model.Usuario;
import com.echobeat.model.facade.EchoBeatFacade;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class CrearPlaylistServlet
 */
@WebServlet("/CrearPlaylist")
@MultipartConfig(maxFileSize = 3*1024*1024)
public class CrearPlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rutaServidor = request.getServletContext().getRealPath("");
		rutaServidor = rutaServidor + ".." + File.separator + "ROOT" + File.separator + "PORTADAS";
		File carpetaServidor = new File(rutaServidor);
		if (!carpetaServidor.exists()) {
			carpetaServidor.mkdirs();
		}
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		String nombre = request.getParameter("nombre");
		boolean publica = request.getParameter("publica") != null;
		Part parte = request.getPart("portada");
		String nombreFichero = extraerNombreFichero(parte);
		
		String rutaFichero = rutaServidor + File.separator + nombreFichero;
		parte.write(rutaFichero);
		
		PlayList playList = new PlayList(nombre, rutaFichero, publica, usuario);
		
		EchoBeatFacade fachada = new EchoBeatFacade();
		fachada.crearPlaylist(playList);
		response.sendRedirect("principal.jsp");
	}

	private String extraerNombreFichero(Part parte) {
		String nombreFichero = "";
		String contenidoCabecera = parte.getHeader("content-disposition");
		//System.out.println(contenidoCabecera);
		String[] fragmentos = contenidoCabecera.split(";");
		for (String fragmento : fragmentos) {
			if (fragmento.trim().startsWith("filename")) {
				nombreFichero = fragmento.substring(fragmento.indexOf("\"")+1, fragmento.length()-1);
			}
		}
		//System.out.println(nombreFichero);
		return nombreFichero;
	}

	
}
