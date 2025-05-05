package com.echobeat.controller;

import java.io.File;
import java.io.IOException;

import com.echobeat.model.Cancion;
import com.echobeat.model.Genero;
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
 * Servlet implementation class SubirCancionServlet
 */
@WebServlet(urlPatterns = "/SubirCancion")
@MultipartConfig(maxFileSize = 5*1024*1024)
public class SubirCancionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rutaServidor = request.getServletContext().getRealPath("");
		rutaServidor = rutaServidor + ".." + File.separator + "ROOT" + File.separator + "CANCIONES";
		System.out.println(rutaServidor);
		File carpetaServidor = new File(rutaServidor);
		if (!carpetaServidor.exists()) {
			carpetaServidor.mkdirs();
		}
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		String titulo = request.getParameter("titulo");
		String duracionString = request.getParameter("duracion");
		String anhoString = request.getParameter("anho");
		String generoString = request.getParameter("genero");
		boolean publica = request.getParameter("publica") != null;
		int duracion = Integer.parseInt(duracionString);
		int anho = Integer.parseInt(anhoString);
		Genero genero = Genero.valueOf(generoString);
		Part parte = request.getPart("cancion");
		String nombreFichero = extraerNombreFichero(parte);
		String rutaFichero = rutaServidor + File.separator + nombreFichero;
		parte.write(rutaFichero);
		
		Cancion cancion = new Cancion(titulo, nombreFichero, duracion, genero, anho, null, publica, null, usuario);
		
		EchoBeatFacade facade = new EchoBeatFacade();
		facade.subirCancion(cancion);
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
