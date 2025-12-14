package es.upm.aled.fichero;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Servlet implementation class IMC
 */
@WebServlet("/imc")
public class IMC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	InputStream file = getServletContext().getResourceAsStream("/imc.html");
	InputStreamReader reader1 = new InputStreamReader(file);
	BufferedReader html = new BufferedReader(reader1);
	String pagina="", linea;
	while((linea = html.readLine()) != null) {
		pagina += linea;
	}
	
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	out.println(pagina);
	out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream file = getServletContext().getResourceAsStream("/imc.html");
		InputStreamReader reader1 = new InputStreamReader(file);
		BufferedReader html = new BufferedReader(reader1);
		String pagina="", linea;
		while((linea = html.readLine()) != null) {
			pagina += linea;
		}
		Float peso = Float.parseFloat(request.getParameter("peso"));
		Float altura = Float.parseFloat(request.getParameter("altura"));
		Float imc = peso/(altura*altura);
		
		String resultado="normal", color="green";
		
		if(imc>30) {
			resultado="obesidad";
			color="red";
		} else if(imc>25) {
			resultado="sobrepeso";
			color="orange";
		}
		pagina = pagina.replace("<h2></h2>", "<h2 style='color:"+color+";'>El IMC calculado es " + imc + " - " + resultado);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println(pagina);
		out.close();
	}

}
