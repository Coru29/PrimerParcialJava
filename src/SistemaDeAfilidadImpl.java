import Entidades.Cliente;
import Entidades.Compra;
import Entidades.Producto;
import Excepciones.EntidadNoExiste;
import Excepciones.EntidadYaExiste;
import tads.linkedlist.MyLinkedListImpl;
import tads.linkedlist.MyList;
import tads.stack.EmptyStackException;
import tads.stack.MyStack;

public class SistemaDeAfilidadImpl implements SistemaDeAfinidad {


    private MyStack<Cliente> clientesPendientesAfiliar = new MyLinkedListImpl<Cliente>();


    public MyStack<Cliente> getClientesPendientesAfiliar() {
        return clientesPendientesAfiliar;
    }

    private MyList<Cliente> clientesAfiliados = new MyLinkedListImpl<Cliente>();

    private  MyList<Cliente> clientesConCompras = new MyLinkedListImpl<>();

    private MyList<Producto> productosComprados = new MyLinkedListImpl<Producto>();

    public MyList<Cliente> getClientesConCompras() {
        return clientesConCompras;
    }

    public void setClientesConCompras(MyList<Cliente> clientesConCompras) {
        this.clientesConCompras = clientesConCompras;
    }

    public MyList<Cliente> getClientesAfiliados() {
        return clientesAfiliados;
    }

    private int contadorClientes = 1;

// -----------  -----------  -----------  -----------  -----------  -----------  -----------  -----------  --------

    @Override
    public void afiliarCliente(String nombre, String cedula) throws EntidadYaExiste{
        Cliente tempCliente = new Cliente(cedula,nombre);
        MyList<Cliente> clientesPendientesAfiliarTemporal = new MyLinkedListImpl<Cliente>();

        //meto todos los del stack original a una LL para poder usar el contains
        while (clientesPendientesAfiliar.size() != 0){
            try {
                clientesPendientesAfiliarTemporal.add(clientesPendientesAfiliar.pop());

            } catch (EmptyStackException e) {
                System.out.println("El stack clientes pendientes a afililar esta vacio");
            }
        }
        // los vuelvo a meter todos donde estaban
        for (int p = 0; p < clientesPendientesAfiliarTemporal.size(); p++) {
            clientesPendientesAfiliar.push(clientesPendientesAfiliarTemporal.get(p));
        }


        if (!clientesAfiliados.contains(tempCliente) && !clientesPendientesAfiliarTemporal.contains(tempCliente)){
            clientesPendientesAfiliar.push(tempCliente);

        }else throw new EntidadYaExiste("esta cliente ya esta en alguna de las dos listas");
    }

    @Override
    public long procesarProximaAfiliacion() throws EntidadNoExiste {
        try {
            Cliente tempCliente = clientesPendientesAfiliar.pop();
            tempCliente.setNumero(contadorClientes);
            contadorClientes ++;

            clientesAfiliados.add(tempCliente);

        } catch (EmptyStackException e) {
            throw new EntidadNoExiste("no hay mas clientes en el stack");
        }

        return 0;
    }

    @Override
    public void registrarCompra(long nroCliente, long nroCompra, MyList<Producto> productos) throws EntidadNoExiste {
        Boolean esta = false;

        Compra comprita = new Compra(nroCompra,productos);


        for (int i = 0; i < clientesAfiliados.size(); i++) {
            if (clientesAfiliados.get(i).getNumero() == nroCliente){
                esta = true;
                clientesAfiliados.get(i).setCompraRealizada(comprita);
                clientesConCompras.add(clientesAfiliados.get(i));
            }
        }

        if (!esta) throw new EntidadNoExiste("el clienete noe sta registadro en la lista de clientes afiliados");
    }

    @Override
    public Producto buscarProducto() {

        for (int cc = 0; cc < clientesConCompras.size(); cc++) {

            MyList<Producto> compritas = clientesConCompras.get(cc).getCompraRealizada().getListaDeProductos();

            for (int comp = 0; comp < compritas.size(); comp++) {
                productosComprados.add(compritas.get(comp));
                //aca tengo una lista con todos los productos q compraron todos los clientes
            }

        }

        MyList<Producto> prodRepetidos = new MyLinkedListImpl<>();
        for (int i = 0; i < productosComprados.size(); i++) {
            Producto prod = productosComprados.get(i);
            int contador = 1;

            for (int j = i + 1; j<productosComprados.size(); j++){
                if (prod.equals(productosComprados.get(i))){
                    contador ++;
                }
            }
            if (contador > 1 && !prodRepetidos.contains(prod)){
                prodRepetidos.add(prod);
                System.out.println(prod.getNombre() + " aparece " + contador + " veces.");
            }

        }

        return null;
    }
}
