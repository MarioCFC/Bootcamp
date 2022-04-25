package Main;

import java.util.logging.Logger;

public class Circunferencia {

    /*
	 * EJERCICIO: Dado el radio, (15), calcular el área del círculo y la longitud de
	 * la circunferencia.
     */
    private static Logger logOut = Logger.getLogger("MyLog");
    sda
    public static void main(String[] args) {
        double radio = 15;
        logOut.info("El area de la circunferencia de radio " + radio + " es: " + calcularArea(radio));

        logOut.info("La longitud de la circunferencia de radio " + radio + " es: " + calcularLongitud(radio));

    }

    private static double calcularArea(double radio) {
        return Math.PI * Math.pow(radio, 2);
    }

    private static double calcularLongitud(double radio) {
        return 2 * Math.PI * radio;
    }

}
