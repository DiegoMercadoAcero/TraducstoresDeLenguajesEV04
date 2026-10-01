package analizadores;

import java.util.ArrayList;
import java.util.List;

public class ManejadorErrores {
    private static final List<ErrorLexico> listaErrores = new ArrayList<>();

    public static void registrarError(int linea, int columna, String lexema, String tipo, String descripcion) {
        listaErrores.add(new ErrorLexico(linea, columna, lexema, tipo, descripcion));
    }

    public static void limpiar() {
        listaErrores.clear();
    }

    public static boolean hayErrores() {
        return !listaErrores.isEmpty();
    }

    public static void imprimirReporte() {
        System.out.println("\n===================================================================================");
        System.out.println("                         REPORTE DE ERRORES LÉXICOS                                ");
        System.out.println("===================================================================================");
        if (listaErrores.isEmpty()) {
            System.out.println(" No se encontraron errores léxicos en el código fuente.");
        } else {
            for (ErrorLexico err : listaErrores) {
                System.out.println(err.toString());
            }
        }
        System.out.println("===================================================================================\n");
    }
}