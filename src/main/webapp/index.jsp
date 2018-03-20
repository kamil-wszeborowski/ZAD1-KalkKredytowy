<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" href="plik_1.css" type="text/css" />
 <title>Kalkulator</title>
</head>
<body>

 <div class="general">
	<h1>Kalkulator finansowy</h1>
 </div>

	<form action="hello" method="get" class="form general">
		<p>Wnioskowana kwota kredytu      </p><input type="number" name="p_kwota" placeholder="min 1000, max 100000"> <br>
		<p> Ilosc rat                     </p><input type="number" name="p_ilosc_rat"  placeholder="min 6, max 100"> <br>
		<p>Oprocentowanie %               </p><input type="number" name="p_oprocentowanie" placeholder="min 1, max 12"> <br>
		<p>Oplata stala(np.Ubezpieczenie) </p><input type="number" name="p_oplata_stala" placeholder="min 0, max 1000"> <br>
		<p>Rodzaj rat : malejaca, stala   </p><input name="p_rodzaj_rat" list="p_rodzaj_rat" placeholder="Wybierz z roletki."> 
														<datalist id="p_rodzaj_rat">
														  <option value="Malejaca">
														  <option value="Stala">
														</datalist> 
		<div class="summary">
			<h2>Jezeli uzupelniles wszystkie dane uzyj przycisku "Oblicz !" </h2><input type="submit" name="wyslij" value="Oblicz !">
			<input type="hidden" name="hidden1" values="licz"/>
		</div>
	</form>
	
</body>
</html>