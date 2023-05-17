package Excepciones;

public class EntidadYaExiste extends Throwable {
    public EntidadYaExiste(String message) {
        super(message);
    }
}
