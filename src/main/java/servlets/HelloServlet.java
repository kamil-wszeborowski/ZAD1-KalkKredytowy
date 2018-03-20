package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.lowagie.text.*;
//import com.lowagie.text.pdf.PdfWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		ServletOutputStream out = response.getOutputStream();
		
	    double z_kwota = Double.parseDouble(request.getParameter("p_kwota"));
		int z_ilosc_rat = Integer.parseInt(request.getParameter("p_ilosc_rat"));
		double z_oprocentowanie = Double.parseDouble(request.getParameter("p_oprocentowanie"));
		double z_oplata_stala = Double.parseDouble(request.getParameter("p_oplata_stala"));
		String z_rodzaj_rat = request.getParameter("p_rodzaj_rat");
		
		
		if(z_kwota < 1000 || z_kwota > 100000){
			response.sendRedirect("/");
		}
		else if(z_ilosc_rat < 6 || z_ilosc_rat > 100){
			response.sendRedirect("/");
		}	
		else if(z_oprocentowanie < 1 || z_oprocentowanie > 12){
			response.sendRedirect("/");	
		}
		else if(z_oplata_stala < 0 || z_oplata_stala > 1000){
			response.sendRedirect("/");	
		}
		else if(z_rodzaj_rat == null || z_rodzaj_rat.equals("")){
			response.sendRedirect("/");
		}
		else if(!(z_rodzaj_rat.equals("Stala")) && !(z_rodzaj_rat.equalsIgnoreCase("Malejaca"))){
			response.sendRedirect("/");
		}
		
		DecimalFormat df = new DecimalFormat("#.##");
	
		double kwota_kapitalu;
		double kwota_odsetek;
		double oplaty_stale;
		double kwota_calkowita;
		double suma_rat=0;
		
	if(z_rodzaj_rat.equals("Stala")){	
		
		kwota_kapitalu = (z_kwota / z_ilosc_rat);
		kwota_odsetek = (z_kwota * (z_oprocentowanie * 0.01)) / z_ilosc_rat;
		oplaty_stale = (z_oplata_stala / z_ilosc_rat);
		kwota_calkowita = (kwota_kapitalu + kwota_odsetek + oplaty_stale);
		suma_rat = (kwota_calkowita * z_ilosc_rat);
				
	    out.println("<html>");
	       out.println("<head><title>Raty</title></head>");
	       out.println("<body>");
	       
	       out.println("<h1>Rozpiska rat:</h1>");
	       out.println("<table><tr><th>Numer raty</th><th>Kwota kapitalu</th><th>Kwota odsetek</th><th>Oplaty stale</th><th>Calkowita kwota raty</th></tr>");
	       for(int i=1;i <= z_ilosc_rat; i++){
	    	   out.println("<tr><td>"+i+"</td><td>"+df.format(kwota_kapitalu)+"</td><td>"+df.format(kwota_odsetek)+"</td><td>"+df.format(oplaty_stale)+"</td><td>"+df.format(kwota_calkowita)+"</td></tr>");
	       }
	       out.println("</table>");
	       out.println("<h2>Podsumownie, laczna suma do zaplaty: "+df.format(suma_rat)+" zl.<h2>");
	       
	       out.println("<form action='write' method='get'>");
	       out.println("<input type='submit' name='createPdf' value='Zapisz jako PDF'>");
	       out.println("</form>");
	       
	       out.println("</body>");
	    out.println("<html>");
	}else{
		
		double [] tablica_odsetek = new double[100];
		double [] tablica__kwot_calkowitych = new double[100];
		
		kwota_kapitalu = (z_kwota / z_ilosc_rat);
		oplaty_stale = (z_oplata_stala / z_ilosc_rat);
		for(int i=0;i < z_ilosc_rat;i++){
			tablica_odsetek[i] = (((z_kwota/z_ilosc_rat)*(1+(z_ilosc_rat - i + 1)*((z_oprocentowanie * 0.01)/12)))-kwota_kapitalu);
			tablica__kwot_calkowitych[i] = (kwota_kapitalu + tablica_odsetek[i] + oplaty_stale);
			suma_rat += tablica__kwot_calkowitych[i];
		}
		
		  out.println("<html>");
	       out.println("<head><title>Raty</title></head>");
	       out.println("<body>");
	       
	       out.println("<h1>Rozpiska rat:</h1>");
	       out.println("<table><tr><th>Numer raty</th><th>Kwota kapitalu</th><th>Kwota odsetek</th><th>Oplaty stale</th><th>Calkowita kwota raty</th></tr>");
	       for(int i=0;i < z_ilosc_rat; i++){
	    	   out.println("<tr><td>"+(i+1)+"</td><td>"+df.format(kwota_kapitalu)+"</td><td>"+df.format(tablica_odsetek[i])+"</td><td>"+df.format(oplaty_stale)+"</td><td>"+df.format(tablica__kwot_calkowitych[i])+"</td></tr>");
	       }
	       out.println("</table>");
	       out.println("<h2>Podsumownie, laczna suma do zaplaty: "+df.format(suma_rat)+" zl.<h2>");
	       
	       out.println("<form action='write' method='get'>");
	       out.println("<input type='submit' name='createPdf' value='Zapisz jako PDF'>");
	       out.println("</form>");
	     //  String url = request.getRequestURL().toString();
	       //out.println("<h3>"+url+"</h3>");
	       out.println("</body>");
	    out.println("<html>");
	}

  }
	
}

