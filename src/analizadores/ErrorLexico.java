package analizadores;

public class ErrorLexico {
    private final int linea;
    private final int columna;
    private final String lexema;
    private final String tipoError;
    private final String descripcion;

    public ErrorLexico(int linea, int columna, String lexema, String tipoError, String descripcion) {
        this.linea = linea;
        this.columna = columna;
        this.lexema = lexema;
        this.tipoError = tipoError;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return String.format("[%s] Línea: %-3d | Columna: %-3d | Lexema: %-15s | Descripción: %s",
                tipoError, linea, columna, "'" + lexema + "'", descripcion);
    }
}