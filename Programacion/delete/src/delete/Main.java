package delete;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException {
		Scanner teclado = new Scanner(System.in);
		String tituloObra,tituloObraCodificada;
		String urlCaratula,urlCaratulaCodificada;
		String urlObra, urlObraCodificada;
		String codigoRepetido;
		
		System.out.println("Introduce los datos en el formato: \nTituloObra##UrlCaratula##NombreObra");
		String cadenaInput[] = teclado.nextLine().split("##");

//		System.out.println("Introduce el titulo de la obra:");
		tituloObra = cadenaInput[0];
		tituloObraCodificada = URLEncoder.encode(tituloObra, StandardCharsets.UTF_8.toString());

//		System.out.println("Introduce la url de la caratula:");
		urlCaratula = cadenaInput[1];
		urlCaratulaCodificada = URLEncoder.encode(urlCaratula, StandardCharsets.UTF_8.toString());

//		System.out.println("Introduce la url de la obra:");
		urlObra = cadenaInput[2];
		urlObraCodificada = URLEncoder.encode(urlObra, StandardCharsets.UTF_8.toString());
		
		codigoRepetido = urlObra.substring(urlObra.lastIndexOf("-"));
		
		
		String cadenaFinal = "<favourites>\n<favourite  name=\"" + tituloObra + "\" thumb=\"" + urlCaratula
				+ "\">ActivateWindow(10025,&quot;plugin://plugin.video.fmoviesto/?image=" + urlCaratulaCodificada
				+ "&mode=getseasons&movie=True&moviescount=0&page=1&title=" + tituloObraCodificada + "&url="
				+ urlCaratulaCodificada + "%7c" + codigoRepetido + "&quot;,return)</favourite>\n</favourites>";

		
		System.out.println("Elemento resultante:\n" + cadenaFinal);
	}

}
