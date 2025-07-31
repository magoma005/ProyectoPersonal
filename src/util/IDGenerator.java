package util;

public class IDGenerator {
    private static int contadorConsulta = 0;
    private static int contadorMascota = 0;
    private static int contadorPersona = 0;

    // Consulta → C1, C2...
    public static String generarCodigoConsulta() {
        return "C" + (++contadorConsulta);
    }

    // Mascota → M1, M2...
    public static String generarCodigoMascota() {
        return "M" + (++contadorMascota);
    }

    // Persona → P1, P2...
    public static String generarCodigoPersona() {
        return "P" + (++contadorPersona);
    }
}
