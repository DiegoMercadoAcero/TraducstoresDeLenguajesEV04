package analizadores;

import java.io.IOException;



public class GeneradorCuplexcio {
    public static void main(String[] args) throws IOException, Exception {
        
        String[] parametros = {"-destdir", "src\\analizadores",
            "-parser", "ParserJavascript", 
            "-progress", "src\\analizadores\\Javascript_lexico.cup"};
        java_cup.Main.main(parametros);
        
    }
    
   
}
