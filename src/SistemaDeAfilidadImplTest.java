import Entidades.Producto;
import Excepciones.EntidadNoExiste;
import Excepciones.EntidadYaExiste;
import org.testng.annotations.Test;
import tads.linkedlist.MyLinkedListImpl;
import tads.linkedlist.MyList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SistemaDeAfilidadImplTest {

    @Test
    void afiliarCliente() {
        SistemaDeAfilidadImpl empresaRetail = new SistemaDeAfilidadImpl();

        try {
            empresaRetail.afiliarCliente("cliente1", "12345678");
            empresaRetail.afiliarCliente("cliente2", "87654321");

            assertEquals(2, empresaRetail.getClientesPendientesAfiliar().size());

        } catch (EntidadYaExiste e) {
            System.out.println(e);
        }
    }

    @Test
    void afiliarClienteQueYaExiste(){
        SistemaDeAfilidadImpl empresaRetail = new SistemaDeAfilidadImpl();

        try {
            empresaRetail.afiliarCliente("cliente1", "12345678");
            empresaRetail.afiliarCliente("cliente1", "12345678");

            assertEquals(1, empresaRetail.getClientesPendientesAfiliar().size());

        } catch (EntidadYaExiste e) {
            System.out.println(e);
        }
    }


    @Test
    void procesarProximaAfiliacion() {
        SistemaDeAfilidadImpl empresaRetail = new SistemaDeAfilidadImpl();

        try {
            empresaRetail.afiliarCliente("cliente1", "12345678");
            empresaRetail.procesarProximaAfiliacion();

            assertEquals(1,empresaRetail.getClientesAfiliados().size());
            assertEquals(0,empresaRetail.getClientesPendientesAfiliar().size());
//            System.out.println(empresaRetail.getClientesAfiliados().get(0).getNumero());

        } catch (EntidadYaExiste | EntidadNoExiste e) {
            System.out.println(e);
        }
    }

    @Test
    void procesarProximaAfiliacionStackVacio(){
        SistemaDeAfilidadImpl empresaRetail = new SistemaDeAfilidadImpl();

        try {
//            empresaRetail.afiliarCliente("cliente1", "12345678");
            empresaRetail.procesarProximaAfiliacion();

            assertEquals(1,empresaRetail.getClientesAfiliados().size());
            assertEquals(0,empresaRetail.getClientesPendientesAfiliar().size());

        } catch ( EntidadNoExiste e) {
            System.out.println(e);
        }
    }

    @Test
    void ProcesarVariasAfiliaciones(){
        SistemaDeAfilidadImpl empresaRetail = new SistemaDeAfilidadImpl();

        try {
            empresaRetail.afiliarCliente("cliente1", "12345678");
            empresaRetail.afiliarCliente("cliente2","87654321");

            empresaRetail.procesarProximaAfiliacion();
            empresaRetail.procesarProximaAfiliacion();



            assertEquals(2,empresaRetail.getClientesAfiliados().size());
            assertEquals(0,empresaRetail.getClientesPendientesAfiliar().size());

                //descomentar esto para ver los numeros de los clientes
//            System.out.println(empresaRetail.getClientesAfiliados().get(0).getNumero());
//            System.out.println(empresaRetail.getClientesAfiliados().get(1).getNumero());


        } catch (EntidadYaExiste | EntidadNoExiste e) {
            System.out.println(e);
        }


    }

    @Test
    void registrarCompra() {
        SistemaDeAfilidadImpl empresaRetail = new SistemaDeAfilidadImpl();

        MyList<Producto> listaParaCompra = new MyLinkedListImpl<>();

        Producto prod1 = new Producto("prod1",10);
        Producto prod2 = new Producto("prod2",20);
        Producto prod3 = new Producto("prod3",30);

        listaParaCompra.add(prod1);
        listaParaCompra.add(prod2);
        listaParaCompra.add(prod3);

        try {
            empresaRetail.afiliarCliente("cliente1", "12345678");
            empresaRetail.afiliarCliente("cliente3", "87654321");

            empresaRetail.procesarProximaAfiliacion();
            empresaRetail.procesarProximaAfiliacion();

            empresaRetail.registrarCompra(1,100,listaParaCompra);
            empresaRetail.registrarCompra(2,200,listaParaCompra);

            assertEquals(100, empresaRetail.getClientesAfiliados().get(0).getCompraRealizada().getNumeroComrpa());
            assertEquals(200, empresaRetail.getClientesAfiliados().get(1).getCompraRealizada().getNumeroComrpa());
            assertEquals(2,empresaRetail.getClientesConCompras().size());


        } catch (EntidadYaExiste | EntidadNoExiste e) {
            System.out.println(e);
        }

    }

    @Test
    void buscarProducto() {
        SistemaDeAfilidadImpl empresaRetail = new SistemaDeAfilidadImpl();

        MyList<Producto> listaParaCompra = new MyLinkedListImpl<>();
        MyList<Producto> listaParaCompra2 = new MyLinkedListImpl<>();


        Producto prod1 = new Producto("prod1",10);
        Producto prod2 = new Producto("prod2",20);
        Producto prod3 = new Producto("prod3",30);

        listaParaCompra.add(prod1);
        listaParaCompra.add(prod2);
        listaParaCompra.add(prod3);

        listaParaCompra2.add(prod1);
        listaParaCompra2.add(prod2);

        try {
            empresaRetail.afiliarCliente("cliente1", "12345678");
            empresaRetail.afiliarCliente("cliente3", "87654321");

            empresaRetail.procesarProximaAfiliacion();
            empresaRetail.procesarProximaAfiliacion();

            empresaRetail.registrarCompra(1,100,listaParaCompra);
            empresaRetail.registrarCompra(2,200,listaParaCompra2);

            empresaRetail.buscarProducto();


        } catch (EntidadYaExiste | EntidadNoExiste e) {
            System.out.println(e);
        }
    }
}