package analizadores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java_cup.runtime.Symbol;
import analizadores.sinctactico_archivos.sym;

public class PruebaErroresLexicos {
    public static void main(String[] args) {
        String rutaArchivo = "src/analizadores/tokens_invalidos.txt";

       
        ManejadorErrores.limpiar();

        try (Reader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            JavascriptLexer lexer = new JavascriptLexer(lector);
            
            System.out.println("Procesando tokens...");
            Symbol token;
            while ((token = lexer.next_token()).sym != sym.EOF) {
                // Se itera consumiendo todos los tokens válidos y registrando los errores
            }

            // Impresión del informe final
            ManejadorErrores.imprimirReporte();

        } catch (Exception e) {
            System.err.println("Error ejecutando el análisis léxico: " + e.getMessage());
        }
    }
}