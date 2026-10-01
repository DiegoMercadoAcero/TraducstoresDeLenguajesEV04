package analizadores.sinctactico_archivos;

import java.io.IOException;

public class GeneradorCupSintantico {
    public static void main(String[] args) throws IOException, Exception {
        
        String[] parametros = {
            "-destdir", "src/analizadores/sinctactico_archivos",
            "-parser", "ParserJavascript", 
            "-progress", "src/analizadores/sinctactico_archivos/javascript_sintactico.cup"
        };
        
        java_cup.Main.main(parametros);
    }
}
