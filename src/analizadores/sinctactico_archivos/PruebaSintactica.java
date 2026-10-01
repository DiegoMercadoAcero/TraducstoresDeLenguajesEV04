package analizadores.sinctactico_archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import analizadores.JavascriptLexer;

public class PruebaSintactica {

    public static void main(String[] args) {
        String baseRuta = "src/analizadores/sinctactico_archivos/";

        System.out.println("==========================================================");
        System.out.println("      1. PRUEBA: MÉTODOS Y LLAMADAS VÁLIDAS              ");
        System.out.println("==========================================================");
        ejecutarAnalisis(baseRuta + "programa_valido.txt");

        System.out.println("\n==========================================================");
        System.out.println("      2. PRUEBA: DETECCIÓN DE ERRORES EN MÉTODOS          ");
        System.out.println("==========================================================");
        ejecutarAnalisis(baseRuta + "programa_invalidos.txt");

        System.out.println("\n==========================================================");
        System.out.println("      3. PRUEBA: COMBINACIÓN Y ANIDAMIENTO DE MÉTODOS     ");
        System.out.println("==========================================================");
        ejecutarAnalisis(baseRuta + "programa_anidados.txt");

        System.out.println("\n==========================================================");
        System.out.println("      4. PRUEBA: VALIDACIÓN DE CANTIDAD DE ARGUMENTOS      ");
        System.out.println("==========================================================");
        ejecutarAnalisis(baseRuta + "programa_validacion_llamadas.txt");
    }

    private static void ejecutarAnalisis(String rutaArchivo) {
        try (Reader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            JavascriptLexer lexer = new JavascriptLexer(lector);
            ParserJavascript parser = new ParserJavascript(lexer);

            parser.parse();

        } catch (Exception e) {
            System.err.println("\n[Proceso Finalizado]: " + e.getMessage());
        }
    }
}