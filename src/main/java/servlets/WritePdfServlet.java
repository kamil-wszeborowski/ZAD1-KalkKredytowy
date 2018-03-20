package servlets;

/*import java.net.URL;
import java.net.URLConnection;
import java.lang.Object;

import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.servlet.ServletException;
*/
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.html.simpleparser.HTMLWorker;
//import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.net.URL;


@WebServlet("/write")
public class WritePdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static String file = "C:/Users/Public/FirstPdf.pdf";
	String k = "<html><body> This is my Project </body></html>";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

	//	 StringBuffer requestURL = request.getRequestURL();
	//       if (request.getQueryString() != null) {
	//           requestURL.append("?").append(request.getQueryString());
	//       }
	//       String url = requestURL.toString();
		
		
		 URL tresc = new URL("http://localhost:8080/hello?p_kwota=10000&p_ilosc_rat=12&p_oprocentowanie=12&p_oplata_stala=100&p_rodzaj_rat=Malejaca&wyslij=Oblicz+%21&hidden1=");
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(tresc.openStream()));
		
		// krok 1
		Document document = new Document();
		try {
		// krok 2
		com.itextpdf.text.pdf.PdfWriter.getInstance(document,
		new FileOutputStream(file));
		// krok 3
		document.open();
		// krok 4
		
		 String inputLine;
	        while ((inputLine = in.readLine()) != null)
	        	document.add(new Paragraph(inputLine));
	        in.close();
		
		} catch (DocumentException de) {
		System.err.println(de.getMessage());
		} catch (IOException ioe) {
		System.err.println(ioe.getMessage());
		}
		// krok 5
		document.close();
		
	}
}

