/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java_cup.runtime.Symbol;
import analizadores.sinctactico_archivos.sym;

/**
 *
 * @author 7053
 */
public class AnalizadorLexico {
    public static void main(String[] args) throws IOException {
        
//         File archivo = new File("archivo.txt");
//        PrintWriter escribir;

System.out.println("=== INICIO DE PRUEBA LEXICA ===");
       
        try {
            //Reader lector = new BufferedReader(new FileReader("src/lexico/tokens_validos.txt"));
            Reader lector = new BufferedReader(new FileReader("src/analizadores/tokens_invalidos.txt"));
            JavascriptLexer lexer = new JavascriptLexer(lector);
            String resultado = "";
            while (true) {
                Symbol token = lexer.next_token();
//                System.out.println("jejejej"+token.toString());
                if (token.sym == sym.EOF) {
                    resultado += "FIN";
                    System.out.println(resultado);
                    break;
                }
            }
         //   System.out.println("Resultado final nuevo "+lexer.getResultado());
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
    }
}
