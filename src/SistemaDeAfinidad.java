import Entidades.Producto;
import Excepciones.EntidadNoExiste;
import Excepciones.EntidadYaExiste;
import tads.linkedlist.MyList;

public interface SistemaDeAfinidad {

    void afiliarCliente(String nombre, String cedula) throws EntidadYaExiste;

    long procesarProximaAfiliacion() throws EntidadNoExiste;

    void registrarCompra(long nroCliente, long nroCompra, MyList<Producto> productos) throws EntidadNoExiste;

    Producto buscarProducto();
}
