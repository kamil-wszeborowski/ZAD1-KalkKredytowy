package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

public class TestHelloServlet extends Mockito {
	
	@Test
	public void servlet_should_not_greet_the_user_if_p_rodzaj_rat_is_difrent_from_Stala_Malejaca() throws IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HelloServlet servlet = new HelloServlet();
		
		when(request.getParameter("z_rodzaj_rat")).thenReturn("cos");
		
		servlet.doGet(request, response);
		
		verify(response).sendRedirect("/");
	}
	

	@Test
	public void servlet_should_not_greet_the_user_if_z_kwota_is_less_than_1000_or_bigger_than_100000() throws IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HelloServlet servlet = new HelloServlet();
		
		when(request.getParameter("z_kwota")).thenReturn("0");
		
		servlet.doGet(request, response);
		
		verify(response).sendRedirect("/");
	}
	
	@Test
	public void servlet_should_not_greet_the_user_if_z_kwota_is_less_than_12_or_bigger_than_100() throws IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HelloServlet servlet = new HelloServlet();
		
		when(request.getParameter("z_ilosc_rat")).thenReturn("2");
		
		servlet.doGet(request, response);
		
		verify(response).sendRedirect("/");
	}
	
}
